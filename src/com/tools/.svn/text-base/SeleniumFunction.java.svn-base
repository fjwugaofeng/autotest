/**
 * 封装的功能方法
 */
package com.tools;
import java.text.*;
import java.util.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.*;

import org.apache.commons.io.*;
import org.dom4j.*;
import org.dom4j.io.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumFunction {
	
	/**
	 * 这是一个把WebDriver切换到新打开窗口的方法
	 * 其中wd为WebDriver,set1为打开窗口之前所有的窗口句柄；
	 */
	public static void SwitchToNewWin(WebDriver wd,Set<String> set1)
	{
		Set<String> set=wd.getWindowHandles();
		
		Iterator<String> it=set.iterator();
		while(it.hasNext()){
			String string=it.next();
			if(!(set1.contains(string))){
				wd.switchTo().window(string);
				break;
			}
		}
	}
	
	public static WebDriver SwitchToNewWinRet(WebDriver wd,Set<String> set1)
	{
		WebDriver wd1=null;
		Set<String> set=wd.getWindowHandles();
		
		Iterator<String> it=set.iterator();
		while(it.hasNext()){
			String string=it.next();
			if(!(set1.contains(string))){
				wd1=wd.switchTo().window(string);
				break;
			}
		}
		return wd1;
	}

	/**
	 * 判断界面元素是否存在，存在返回true，不存在返回false
	 * @param wd webdriver
	 * @param loactor 定位元素
	 * @return
	 */
	public static boolean isWebElementExist(WebDriver wd,By loactor)
	{
		int count=0;
		while (true) {
			try {
				wd.findElement(loactor);
				return true;
			} catch (Exception e) {
				SeleniumFunction.webDriver_Wait(500);
			}
			if(count==11){
				return false;
			}
			count++;
		}
	}
	
	/**
	 * 判断页面元素是否存在
	 * @param wd webdriver
	 * @param loactor 元素的定位地址
	 * @return
	 */
	public static boolean isWebElementExist_now(WebDriver wd,By loactor)
	{
		try {
			wd.findElement(loactor);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 判断界面元素是否存在，存在返回true，不存在返回false
	 * @param wd
	 * @param father 父元素
	 * @param sonloactor 子元素
	 * @return
	 */
	public static boolean isWebElementExist(WebDriver wd,By father,By sonloactor)
	{
		int count=0;
		while (true) {
			try {
				wd.findElement(father).findElement(sonloactor);
				return true;
			} catch (Exception e) {
				SeleniumFunction.webDriver_Wait(500);
			}
			if(count==11){
				return false;
			}
			count++;
		}
	}
	
	public static boolean isWebElementExist_now(WebDriver wd,By father,By sonloactor)
	{
		try {
			wd.findElement(father).findElement(sonloactor);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isWebElementExist(WebElement w,By loactor)
	{
		int count=0;
		while (true) {
			try {
				w.findElement(loactor);
				return true;
			} catch (Exception e) {
				SeleniumFunction.webDriver_Wait(500);
			}
			if(count==11){
				return false;
			}
			count++;
		}
	}
	
	public static boolean isWebElementExist_now(WebElement w){
		try {
			w.getText();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isWebElementExist_now(WebElement w,By loactor)
	{
		try {
			w.findElement(loactor);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 判断是否含有特定的属性值
	 * @param element 相应的元素
	 * @param AttributeName 想要判断的属性
	 * @return 加油有相应的属性则返回true，否则返回false
	 */
	public static boolean isAttributeExit(WebElement element,String AttributeName){
		try {
			element.getAttribute(AttributeName);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 直到找到某个元素
	 * @param wd
	 * @param loactor
	 */
	public static void utilElementExist(WebDriver wd,By loactor){
		int times=0;
		while(times<=30){
			if(isWebElementExist(wd,loactor)){
				break;
			}else{
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			times++;
		}
	}
	
	/**
	 * 直到某个属性的值等于
	 * @param wd
	 * @param loactor
	 * @param attValue
	 */
	public static void untilAttributeCome(WebDriver wd,By loactor,String attValue){
		int times=0;
		
		while(times<=30){
			String string=wd.findElement(loactor).getText();
			if(string.equals(attValue)){
				break;
			}else{
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			times++;
		}
	}
	
	/**
	 * 用系统当前时间进行截图
	 * @param wd
	 * @param path存放图片所在的文件夹的绝对路径（假如文件夹下有图片会把里面的图片先进行删除）
	 */
	public static void printScreen_Time(WebDriver wd,String path){
		Date date=new  Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
		String datestring=sdf.format(date);
		File f=new File(path);
		if(!f.exists()){
			f.mkdir();
		}else{
			String string[]=f.list();
			for(int i=0;i<string.length;i++){
				File f1=new File(path+"/"+string[i]);
				f1.delete();
			}
		}
		File srcFile=((TakesScreenshot)wd).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
		File screenshot=new  File(path+"/"+datestring+".png");
		try {
			FileUtils.copyFile(srcFile, screenshot);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * TestNG测试截图，截图直接放在image目录下
	 * @param wd 
	 * @param name 截图名
	 */
	public static void printScreen_Name(WebDriver wd,String name){
		File f=new File("image");
		if(!f.exists()){
			f.mkdir();
		}
		//假如本地有截图已经存在，则删除
		File file1=new File("image/"+name+".jpg");
		if (file1.exists()) {
			file1.delete();
		}
		
		File srcFile=((TakesScreenshot)wd).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
		File screenshot=new  File("image/"+name+".jpg");
		try {
			FileUtils.copyFile(srcFile, screenshot);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 直到某个元素出现
	 * @param wdw
	 * @param loactor
	 */
	public static void untilElementHappan(WebDriverWait wdw,final By loactor){
		wdw.until(new ExpectedCondition<Boolean>(){
			public Boolean apply(WebDriver wd){
				return wd.findElement(loactor).isDisplayed();
			}
		});
	}
	
	public static void untilElementHappan(WebDriverWait wdw,final WebElement e){
		wdw.until(new ExpectedCondition<Boolean>(){
			public Boolean apply(WebDriver wd){
				return e.isDisplayed();
			}
		});
	}
	
	/**
	 * 界面出现某个文字
	 * @param wd
	 * @param content
	 * @return
	 */
	public static boolean isContentAppeared(WebDriver wd,String content)
	{
		boolean status=false;
		try {
			wd.findElement(By.xpath("//*[contains(.,'"+content+"')]"));
			status=true;
		} catch (Exception e) {
			status=false;
		}
		return status;
	}
	
	/**
	 * 生成xml文件
	 * @param fileName
	 * @param tableName
	 * @param dataNum
	 */
	public void createXml(String fileName,String tableName,int dataNum) {
		//创建文档对象
		Document document = DocumentHelper.createDocument();
		//添加元素，第一个元素为根元素
		Element first = document.addElement("UploadData");
		//在根元素下添加其它元素
		Element second = first.addElement("Table");
		//为元素添加数据(第一个参数为属性名称，第二个参数为属性值)
		second.addAttribute("Name", tableName);
		for(int i=0;i<dataNum;i++){
			Element third=second.addElement("Record");
			third.addAttribute("Src_Alww_Account", "");
			third.addAttribute("Friend_Account", "");
			third.addAttribute("Friend_NickName", "");
			third.addAttribute("Signature", "");
			
		}
  
		try {
			//创建写XML文档的Writer对象
			Writer fileWriter = new FileWriter(fileName);
			//创建美化文档的format对象，如果没有这个对象，生成的XML文档的元素不会换行，不太美观
			OutputFormat format = OutputFormat.createPrettyPrint();  
			XMLWriter xmlWriter = new XMLWriter(fileWriter, format);
			xmlWriter.write(document);
			xmlWriter.close();
		} catch (IOException e) {
		e.printStackTrace();
		}
	}
	
	/**
	 * 转换文件大小
	 * @param temHtmlString
	 * @return
	 */
	public static String convertFileSize(String temHtmlString){
		try {
			long num=Integer.parseInt(temHtmlString);
			long kb=1024;
			long mb=kb*1024;
			long gb=mb*1024;
			if(num>=gb){
				return String.format("%.2fGB", (float) num/gb);
			}else if(num>=mb){
				float f=(float)num/mb;
				return String.format("%.2fMB", f);
			}else if(num>=kb){
				float f=(float)num/kb;
				return String.format("%.2fKB", f);
			}else{
				return String.format("%dB",  num);
			}
		} catch (NumberFormatException e) {
			return temHtmlString;
		}
		
	}
	
	/**
	 * 转换成统一的电话号码格式
	 * @param temHtmlString
	 * @return
	 */
	public static String commonPhone(String temHtmlString){
		//假如含有" "则用""代替
//		temHtmlString=temHtmlString.trim();
		if(temHtmlString.contains(" ")){
			temHtmlString=temHtmlString.replace(" ", "");
		}
		if(temHtmlString.contains("-")){
			temHtmlString=temHtmlString.replace("-", "");
		}
		if(temHtmlString.contains("+86")){
			temHtmlString=temHtmlString.replace("+86", "");
		}
		if(temHtmlString.startsWith("86")){
			temHtmlString=temHtmlString.replace("86", "");
		}
		if(temHtmlString.contains(";")){
			temHtmlString=temHtmlString.replace(";", "");
		}
		return temHtmlString.trim();
	}
	
	/**
	 * 转换成统一的格式
	 * @param temHtmlString
	 * @return
	 */
	public static String commonString(String temHtmlString){
		String returnValue="";
		if(temHtmlString.endsWith("GMT+8:00")){
			temHtmlString=temHtmlString.replace("GMT+8:00", "").trim();
		}
		
		if(temHtmlString.contains("/")){
			temHtmlString=temHtmlString.replace("/", "-").trim();
		}
			
		if(temHtmlString.contains("年")){
			if(temHtmlString.contains("月")){
				int year_month=temHtmlString.indexOf("月")-temHtmlString.indexOf("年")-1;
				if(year_month==2){
					temHtmlString=temHtmlString.replace("年", "");
				}else if(year_month==1){
					temHtmlString=temHtmlString.replace("年", "0");
				}else{
					temHtmlString=temHtmlString.replace("年", "");
				}
				if(temHtmlString.contains("日")){
					int month_day=temHtmlString.indexOf("日")-temHtmlString.indexOf("月")-1;
					if(month_day==2){
						temHtmlString=temHtmlString.replace("月", "");
					}else if(month_day==1){
						temHtmlString=temHtmlString.replace("月", "0");
					}else{
						temHtmlString=temHtmlString.replace("月", "");
					}
				}
				temHtmlString=temHtmlString.replace("日", "");
			}
		}
		//判断是否含有-号
		if(temHtmlString.contains("-")){
			int indexFirst=temHtmlString.indexOf('-');
			int indexLast=temHtmlString.lastIndexOf('-');
			if((indexLast-indexFirst)==3){
				temHtmlString=temHtmlString.replaceFirst("-", "");
			}else if((indexLast-indexFirst)==2){
				temHtmlString=temHtmlString.replaceFirst("-", "0");
			}else{
				temHtmlString=temHtmlString.replaceFirst("-", "");
//				System.out.println("这个时间有问题");
			}
			if(temHtmlString.contains(" ")){
				int indexSec=temHtmlString.indexOf('-');
				int indexspance=temHtmlString.indexOf(' ');
				if((indexspance-indexSec)==3){
					temHtmlString=temHtmlString.replaceFirst("-", "");
				}else if((indexspance-indexSec)==2){
					temHtmlString=temHtmlString.replaceFirst("-", "0");
				}else{
					temHtmlString=temHtmlString.replaceFirst("-", "");
				}
			}else {
				temHtmlString=temHtmlString.replace("-", "");
			}
		}
		//判断是否含有：
		if(temHtmlString.contains(":")){
			if(temHtmlString.contains(" ")){
				int indexspace=temHtmlString.indexOf(" ");
				int indexFirst=temHtmlString.indexOf(':');
				if((indexFirst-indexspace)==3){
					temHtmlString=temHtmlString.replace(" ", "");
				}else if ((indexFirst-indexspace)==2) {
					temHtmlString=temHtmlString.replace(" ", "0");
				}else {
					temHtmlString=temHtmlString.replace(" ", "");
				}
			}
			int indexFirst=temHtmlString.indexOf(':');
			int indexLast=temHtmlString.lastIndexOf(':');
			if(indexFirst==indexLast){
				temHtmlString=temHtmlString+":";
				indexFirst=temHtmlString.indexOf(':');
				indexLast=temHtmlString.lastIndexOf(':');
			}
			if((indexLast-indexFirst)==3){
				temHtmlString=temHtmlString.replaceFirst(":", "");
			}else if((indexLast-indexFirst)==2){
				temHtmlString=temHtmlString.replaceFirst(":", "0");
			}else{
//				return returnValue;
				temHtmlString=temHtmlString.replaceFirst(":", "");
				System.out.println(temHtmlString+"这个时间有问题");
			}
			int indexSec=temHtmlString.indexOf(':');
			int indexspance=temHtmlString.length();
			if((indexspance-indexSec)==3){
				temHtmlString=temHtmlString.replaceFirst(":", "");
			}else if((indexspance-indexSec)==2){
				temHtmlString=temHtmlString.replaceFirst(":", "0");
			}else{
				temHtmlString=temHtmlString.replaceFirst(":", "");
			}			
		}
		
//		try {
//			Integer.parseInt(temHtmlString);
//			returnValue=temHtmlString;
//		} catch (NumberFormatException e) {
//			returnValue="";
//		}
		return returnValue;
	}

	/**
	 * 封装线程等待时间函数
	 * @param time
	 */
	public static void webDriver_Wait(int time){
		try {
			Thread.sleep(time);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//直到夜幕加载完成
//	public void waitForPageLoad(WebDriver wd){
//		WebDriverWait wdw=new WebDriverWait(wd,30);
//		wdw.until(isPagLoad());
//	}
	
//	public Function<WebDriver,Boolean> isPagLoad(){
//		return new Function<WebDriver,Boolean>(){
//			public Boolean apply(WebDriver driver){
////				System.out.println(((JavascriptExecutor) driver).executeScript("return document.readyState"));
//				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
//			}
//		};
//	}
	
	//直到页面加载完成
	public static void isPageLoad(WebDriver wd){
		JavascriptExecutor js=(JavascriptExecutor) wd;
		while(true){
			String string=(js.executeScript("return document.readyState")).toString().trim();
//			System.out.println(string);
			if(string.equals("complete")){
				SeleniumFunction.webDriver_Wait(500);
				break;
			}else{
				SeleniumFunction.webDriver_Wait(500);
			}
		}
		
	}
	
	//直到id以load的开始的加载完成了
	public static void isLoad(WebDriver wd){
		WebDriverWait wdw=new WebDriverWait(wd, 20);
		if(SeleniumFunction.isWebElementExist_now(wd, By.cssSelector("div[id^=load]"))&&wd.findElement(By.cssSelector("div[id^=load]")).isDisplayed()){
			for(WebElement sonLoad:wdw.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[id^=load]")))){
				while(sonLoad.isDisplayed()){
					SeleniumFunction.webDriver_Wait(500);
				}
			}
		}		
	}
	
	/**
	 * 强制点击某个元素
	 * @param wd webdriver
	 * @param webElement 要点击的元素
	 */
	public static void myClick(WebDriver wd,WebElement webElement){
		try {
			webElement.click();
		}catch (Exception e){
			((JavascriptExecutor) wd).executeScript("arguments[0].click();", webElement);
		}
	}
	
	/**
	 * 得到随机字符串
	 * @param type 字符串类型：0数字，小写大写字母；1大小写字母；2大写字母；3小写字母；4数字
	 * @param stringlength 字符长度
	 * @return
	 */
	public static String getRondomString(int type,int stringlength)
	{
		String string0="1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
		String string1 ="qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
		String string2 ="QWERTYUIOPASDFGHJKLZXCVBNM";
		String string3 ="qwertyuiopasdfghjklzxcvbnm";
		String string4 ="1234567890";
		StringBuffer sb=new StringBuffer();
//		Random rd=new Random();
		for(int i=0;i<stringlength;i++)
		{
//			int num=rd.nextInt(string.length());
			if(type==0){
				int num=(int)(Math.random()*(string0.length()));
				sb.append(string0.charAt(num));
			}else if(type==1){
				int num=(int)(Math.random()*(string1.length()));
				sb.append(string1.charAt(num));
			}else if(type==2){
				int num=(int)(Math.random()*(string2.length()));
				sb.append(string2.charAt(num));
			}else if(type==3){
				int num=(int)(Math.random()*(string3.length()));
				sb.append(string3.charAt(num));
			}else if(type==4){
				int num=(int)(Math.random()*(string4.length()));
				sb.append(string4.charAt(num));
			}
		}
		return sb.toString();
	}
	
	//高亮元素
	public static void highLightElement(WebDriver wd,WebElement element){
		WrapsDriver wrapsDriver=(WrapsDriver)element;
		JavascriptExecutor js=(JavascriptExecutor)wrapsDriver.getWrappedDriver();
		for(int i=0;i<6;i++){
			js.executeScript("arguments[0].setAttribute('style',arguments[1]);", element,"color:red;border:2px solid yellow;");
			SeleniumFunction.webDriver_Wait(500);
			js.executeScript("arguments[0].setAttribute('style',arguments[1]);", element,"color:green;border:2px solid yellow;");
			SeleniumFunction.webDriver_Wait(500);
		}
		js.executeScript("arguments[0].setAttribute('style',arguments[1]);", element,"");
	}
	
	/**
	 * 得到zw自动化测试的配置信息的键值对（属性和值）
	 * @param fileName 配置信息的详细地址
	 * @return 返回得到的键值对
	 */
	public static Map<String, String> getXML(String fileName){
		Map<String, String> map=new HashMap<String, String>();
		File inputXml = new File(fileName);
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(inputXml);
			// 获取文档的根元素
			@SuppressWarnings("unchecked")
			List<Element> employees = document.getRootElement().elements("Record");
			for(Element element:employees){	
				@SuppressWarnings("unchecked")
				List<Attribute> attributes = element.attributes();
				String att="";
				String val="";
				for(Attribute attribute:attributes){
					if (attribute.getName().equals("name")) {
						att=attribute.getValue();
					}else if (attribute.getName().equals("value")) {
						val=attribute.getValue();
					}
				}
				map.put(att, val);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 封装模拟键盘执行黏贴操作（robot类实现）
	 */
	public static void ctrlV(){
		Robot robot=null;
		try {
			robot=new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 对某元素进行右键操作
	 * @param wd webdriver
	 * @param onElement 要右键的元素
	 */
	public static void rightMouse(WebDriver wd,WebElement onElement){
		Actions ac=new Actions(wd);
//		//直接移到元素进行邮件点击
		ac.contextClick(onElement).build().perform();
		webDriver_Wait(1000);
	}
	
	/**
	 * 经纬度是否合法的方法
	 * @param string1 纬度
	 * @param string2 经度
	 * @return
	 */
	public static boolean isLatLonRight(String string1,String string2){
		float num1= -1;
		float num2=-1;
		boolean isRight=true;
		try {
			num1=Float.parseFloat(string1);
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}
		try {
			num2=Float.parseFloat(string2);
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}
		//判断纬度是否合法,纬度必须在0到90之间
		if (num1<0||num1>90) {
			isRight=false;
		}
		//判断经纬是否合法，经纬必须是0到180之间
		if (num2<0||num2>180) {
			isRight=false;
		}
		
		return isRight;
	}
	
}
