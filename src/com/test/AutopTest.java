package com.test;


import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.tools.SeleniumFunction;

public class AutopTest {
	
	public Map<String, String> config = null;
	WebDriver wd = null;
	WebDriverWait wdw = null;
	AutopStress as=null;
	
  @Test(dependsOnMethods= {"auto_test"},priority=4)
  public void stress_test() {
	  try {
		as.login(config.get("ip"));
	} catch (Exception e1) {
		e1.printStackTrace();
	}
	  while (true) {
			boolean flag = false;
			String version[] = {config.get("old_version"),config.get("new_version")};
			int run_count = Integer.parseInt(config.get("run_count"));			
			try {
				for (int i = 0; i < run_count; i++) {
					as.autop_upgrade(i,"tftp","Config.Firmware.Url",version[0],version[1],config.get("ip"));
					if(i==run_count-1) {
						flag =true;
						config.put("is_first_clear_testresult", "false");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail();
			} finally {
				if (flag) {
					break;
				}
			}
		}
  }
  
  @Test(priority=1)
  public void auto_test() {
	  String protocol[] = {"tftp","tftp","ftp","ftp","http","http","https","https"}; 
	  String version[] = {config.get("old_version"),config.get("new_version")};
	  try {
		  as.login(config.get("ip"));
		  for (int i = 0; i < protocol.length; i++) {
//			  int index_i = i % 2;
			  as.autop_upgrade(i, protocol[i], "Config.Firmware.Url",version[0],version[1],config.get("ip"));
	//			System.out.println(i);
		  }
	} catch (Exception e) {
		e.printStackTrace();
		Assert.fail();
	}
  }
  
  @Test(priority=2)
  public void test_protect() {
	  try {
		as.login(config.get("ip_new"));
//		String protocol[] = {"tftp","tftp"}; 
		String version[] = {config.get("new_version"),config.get("no_protect_version"),config.get("protect_version"),config.get("new_version")};
		for (int i = 0; i < version.length; i++) {
			as.autop_upgrade(i, "tftp", "Config.Firmware.Url",version[i],version[i], config.get("ip_new"));
		}
	} catch (Exception e) {
		e.printStackTrace();
		Assert.fail();
	}
  }
  
  @Test(priority=3)
  public void test_no_protect() {
	  try {
		as.login(config.get("ip"));
//		String protocol[] = {"tftp","tftp"}; 
		String version[] = {config.get("new_version"),config.get("no_protect_version"),config.get("new_version"),config.get("protect_version"),config.get("new_version")};
		for (int i = 0; i < version.length; i++) {
			as.autop_upgrade(i, "tftp", "Config.Firmware.Url",version[i],version[i], config.get("ip"));
		}
	} catch (Exception e) {
		e.printStackTrace();
		Assert.fail();
	}
  }
  
	@BeforeClass
	public void before_class() {
		config = SeleniumFunction.getXML("config/akuvox_common_config.xml");
		System.setProperty("webdriver.chrome.driver", "config/chromedriver.exe");
		wd = new ChromeDriver();
		wdw = new WebDriverWait(wd, 20);
		wd.manage().window().maximize();
		as=new AutopStress(wd, wdw,config);
		if (config.get("is_first_clear_testresult").equals("true")) {
			config.put("is_first_clear_testresult", "false");
			as.execute_ssh_cmd("cat /dev/null > "+config.get("test_result"), 1);
		}		
	
//		System.out.println(SeleniumFunction.isWebElementExist_now(wd, as.locator.get("mid_cont1")));
//		System.out.println(wd.findElement(as.locator.get("mid_cont1")).isDisplayed());
//		System.out.println(SeleniumFunction.isWebElementExist_now(wd, as.locator.get("mid_cont2")));
//		System.out.println(wd.findElement(as.locator.get("mid_cont2")).isDisplayed());
//		AkuvoxCommon.set_remote_log(wd, wdw, as.locator, config);
	}
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("开始执行用例");
	}
  
  @AfterMethod
  public void afterMethod() {
	  System.out.println("结束执行用例");
	  as.logout();
  }
  
  @AfterClass
  public void after_test() {
	  //保存测试结果
	  String time=as.helper1.sendCmd("date '+%Y%m%d%H%M%S'", 2);
	  as.helper1.sendCmd("mv "+config.get("test_result")+" "+config.get("test_result")+time, 1);
	  //关闭ssh连接
	  as.helper1.close();
	  if (wd!=null) {
		  wd.close();
		  wd.quit();
	} else {
		System.out.println("webdriver is null");
	}
  }
}
