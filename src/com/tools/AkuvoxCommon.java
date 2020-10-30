package com.tools;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AkuvoxCommon {
	
	/**
	 * 寻找左边对应的类别
	 * @param wd webdriver
	 * @param firstname 左边列表第一级目录
	 * @param secondname 左边列表二级目录
	 */
	public static void find_PageUL(WebDriver wd,String firstname,String secondname,Map<String, By> locator) {
		//循环遍历找到类别
		List<WebElement> first=wd.findElement(locator.get("PageUL")).findElements(By.xpath("li"));
		boolean flag = false;
		try {
			for(int i=0;i<first.size();i++) {
				WebElement we = first.get(i);
				if (we.findElement(By.tagName("label")).getText().equals(firstname)) {
					SeleniumFunction.myClick(wd, we);
					Thread.sleep(1000);
					first=wd.findElement(locator.get("PageUL")).findElements(By.xpath("li"));
					
					List<WebElement> second=first.get(i).findElements(By.xpath("ul/li"));
					for (int j = 0; j < second.size(); j++) {
						WebElement wes = second.get(j);
						if (wes.findElement(By.tagName("a")).getText().equals(secondname)) {
							SeleniumFunction.myClick(wd, wes);
							flag = true;
							Thread.sleep(1000);
							break;
						}
						second=we.findElements(By.xpath("ul/li"));
					}
					if (flag) {
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 点击全局的global submit按钮
	 * @param wd
	 */
	public static void click_global_submit(WebDriver wd,Map<String, By> Locator) {
		wd.findElement(Locator.get("fPageSubmit")).click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		//等待加载完成
		while(true) {
			try {
				wd.findElement(Locator.get("fPageSubmit")).getTagName();
				break;
			} catch (Exception e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 获取特定标签的值
	 * @param fileName
	 * @param tag
	 * @return
	 */
	public static Map<String, By> getLocator(String fileName,String tag){
		Map<String, By> map=new HashMap<String, By>();
		File inputXml = new File(fileName);
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(inputXml);
			Element root_element = document.getRootElement();
			// 获取特定标签的数据
			get_xml_value(root_element, tag, map);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 
	 * @param root_element
	 * @param tagname
	 * @param map
	 */
	public static void get_xml_value(Element root_element,String tagname,Map<String, By> map) {
		@SuppressWarnings("unchecked")
		List<Element> ls = root_element.elements();
		for(Element e:ls) {
			if (e.getName().equals(tagname)) {
				@SuppressWarnings("unchecked")
				List<Attribute> attributes = e.attributes();
				String id="";
				String locator="";
				String type = "";
				for(Attribute attribute:attributes){
					if (attribute.getName().equals("id")) {
						id=attribute.getValue();
					}else if (attribute.getName().equals("locate")) {
						locator=attribute.getValue();
					} else if (attribute.getName().equals("locatetype")) {
						type = attribute.getValue();
					}
				}
				map.put(id, get_locator_by(locator, type));				
			}else {
				get_xml_value(e, tagname,map);
			}
//			System.out.println(e.getName());
		}
		
	}
	
	/**
	 * 通过定位类型和定位方式返回By的对象
	 * @param locator_value 定位值
	 * @param locator_type 定位方法
	 * @return
	 */
	public static By get_locator_by(String locator_value,String locator_type){
		By by = null;
		if(locator_type.equals("id")){
			by=By.id(locator_value);
		}else if(locator_type.equals("xpath")){
			by=By.xpath(locator_value);
		}else if (locator_type.equals("css")) {
			by=By.cssSelector(locator_value);
		}else if (locator_type.equals("link")) {
			by=By.linkText(locator_value);
		}else if (locator_type.equals("name")) {
			by=By.name(locator_value);
		}else if (locator_type.equals("tag")) {
			by=By.tagName(locator_value);
		}else if (locator_type.equals("class")) {
			by=By.className(locator_value);
		}	
		return by;
	}
	
	/**
	 * 开启远程日志服务
	 * @param wd
	 * @param wdw
	 * @param locator
	 * @param config
	 */
	public static void set_remote_log(WebDriver wd,WebDriverWait wdw, Map<String, By> locator,Map<String, String> config) {
		try {
			find_PageUL(wd, "Upgrade", "Advanced", locator);
			Thread.sleep(1000);
			SpiderCommon.scrollPage(wd, 2, wd.findElement(locator.get("cSystemLogLevel")));
			Select select = new Select(wd.findElement(locator.get("cSystemLogLevel")));
			select.selectByVisibleText("7");
			Thread.sleep(1000);
			SeleniumFunction.untilElementHappan(wdw, locator.get("cSystemLogLevel"));
			Select select1 = new Select(wd.findElement(locator.get("cRemoteSystemLog")));
			select1.selectByVisibleText("Enabled");
			wd.findElement(locator.get("cRemoteSystemServer")).clear();
			wd.findElement(locator.get("cRemoteSystemServer")).sendKeys(config.get("server_ip"));
			click_global_submit(wd, locator);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
