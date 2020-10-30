package com.test;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jcraft.jsch.JSchException;
import com.tools.AkuvoxCommon;
import com.tools.SSHHelper;
import com.tools.SeleniumFunction;

public class AutopStress {
	
//	public String new_version = "113.30.4.176";
//	public String old_version = "113.30.4.126";
//	public String ip="192.168.0.104";
//	public String account="admin";
//	public String passwd="admin";
//	public String autop_url="tftp://192.168.0.110";
//	public String server_ip = "192.168.0.110";
//	public int server_port = 22;
//	public String server_account = "root";
//	public String server_passwd = "123456";
//	public String test_result = "/file/test_result.info";
//	public  int run_count=5000; //设置升级的次数
//	public boolean is_first_clear_testresult = true; //第一次师傅清除之前的结果
	public WebDriver wd=null;
	public WebDriverWait wdw=null;	
	public Map<String, String> config = null;
	public Map<String, By> locator = null;
	SSHHelper helper1  = null;
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Map<String, By> locator = AkuvoxCommon.getLocator("config/locator.xml","Record");
//		System.out.println(locator.size());
//	}
	
	/**
	 * 构造方法，初始化webdriver和webdriverwait
	 * @throws JSchException 
	 */
	public AutopStress(WebDriver wd,WebDriverWait wdw,Map<String, String> config) {		
		this.wd=wd;
		this.wdw=wdw;
		this.config = config;
		config = SeleniumFunction.getXML("config/akuvox_common_config.xml");
		helper1 = new SSHHelper(config.get("server_ip"), Integer.parseInt(config.get("server_port")), config.get("server_account"), config.get("server_passwd"));
		locator = AkuvoxCommon.getLocator("config/locator.xml","Record");
	}
	
	public void autop_upgrade(int number,String protocol,String config_name,String version1,String version2,String ip) {
		String upgradeversion ="";
		String shezhi_version="";
		String currversion = "";
		String t="";
		try {
//			login();
//			wd.navigate().refresh();
			Thread.sleep(2000);
			currversion=get_version();

			if (currversion.equals(version1)) {
				shezhi_version = version2;
			}else {
				shezhi_version = version1;
			}
			
			//设置autop升级的升级方式
			update_autop_upgrade_config(shezhi_version, protocol, config_name);
			
			String time1=execute_autop_opteration(protocol);
			wait_until_finish(wd,ip);	
			String time2=execute_ssh_cmd("date +%s", 2);
			int time3=Integer.parseInt(time2)-Integer.parseInt(time1);
			t = "开始时间:"+time1+",结束时间:"+time2+",总共花费时间:"+time3;
			judge_login(ip);
//			String number = execute_ssh_cmd("cat "+config.get("test_result")+" |wc -l", 2);
			//校验结果
			upgradeversion=get_version();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (upgradeversion.equals(shezhi_version)&&!upgradeversion.equals("")) {
				execute_ssh_cmd("echo 第"+number+"次"+currversion+"--\\>"+shezhi_version+":success "+protocol+" "+t+" >>"+config.get("test_result"), 1);
			}else {
				System.out.println("upgrade fail");
				execute_ssh_cmd("echo 第"+number+"次"+currversion+"--\\>"+shezhi_version+":fail "+protocol+" "+t+" >>"+config.get("test_result"), 1);
				SeleniumFunction.printScreen_Time(wd, "test-output");
			}
		}
	}
	
	/**
	 * 更新autop升级的配置
	 * @param version 升级的版本号
	 * @param protocol autop的协议
	 * @param autop_config_name 
	 */
	public void update_autop_upgrade_config(String version,String protocol,String autop_config_name) {
		
		//获取autop配置处于多少行，然后进行修改对应配置项的内容
		String num=execute_ssh_cmd("sed -n '/"+autop_config_name+"/=' "+config.get("autop_config_file"),2);
		String commond = "";
		if (protocol.equals("ftp")||protocol.equals("http")||protocol.equals("https")) {
			commond = "sed -i '"+num+"c "+autop_config_name+" = "+protocol+"://"+config.get("server_account")+":"+config.get("server_passwd")+"@"+config.get("server_ip")+"/"+version+".rom' "+config.get("autop_config_file");
		}else if (protocol.equals("tftp")) {
			commond = "sed -i '"+num+"c "+autop_config_name+" = "+protocol+"://"+config.get("server_ip")+"/"+version+".rom' "+config.get("autop_config_file");
		}else {
			commond = "sed -i '"+num+"c "+autop_config_name+" = "+protocol+"://"+config.get("server_ip")+"/"+version+".rom' "+config.get("autop_config_file");
		}
		
//		System.out.println("sed -i '"+num+"c Config.Firmware.Url = tftp://192.168.88.199/"+shezhi_version+".rom' /file/r000000000113.cfg");
		execute_ssh_cmd(commond, 1);
		
	}
	
	/**
	 * 更新autop的配置
	 * @param autop_config 是一个完整的配置,包括 a = b这种形式
	 */
	public void update_autop_config(String autop_config) {
		String config_name = autop_config.substring(0, autop_config.indexOf("=")).trim();
//		String config_value = autop_config.substring(autop_config.indexOf("=")+1).trim();
		//获取autop配置处于多少行，然后进行修改对应配置项的内容
		String num=execute_ssh_cmd("sed -n '/"+config_name+"/=' "+config.get("autop_config_file"),2);
		System.out.println("sed -i '"+num+"c "+autop_config+"' "+config.get("autop_config_file"));
		execute_ssh_cmd("sed -i '"+num+"c "+autop_config+"' "+config.get("autop_config_file"), 1);
				
	}
	
	
	/**
	 * 登录到系统
	 */
	public void login(String ipaddr) throws Exception{
		wd.get("http://"+ipaddr);
		wd.navigate().refresh();
		Thread.sleep(3000);
		wd.findElement(locator.get("username")).clear();
		wd.findElement(locator.get("username")).sendKeys(config.get("account"));
		wd.findElement(locator.get("password")).clear();
		wd.findElement(locator.get("password")).sendKeys(config.get("passwd"));
//		wd.findElement(locator.get("Login")).click();
		SeleniumFunction.myClick(wd, wd.findElement(locator.get("Login")));
		Thread.sleep(2000);
		//直到页面登录成功
		SeleniumFunction.untilElementHappan(wdw, locator.get("tPageLogOut"));
	}
	
	/**
	 * 退出登录
	 */
	public void logout() {
		SeleniumFunction.myClick(wd, wd.findElement(locator.get("tPageLogOut")));
		SeleniumFunction.webDriver_Wait(2000);
	}
	
	/**
	 * 获取升级界面的autop_url
	 */
	public String get_autop_url(String type) {
		String returnV = "";
//		String type=config.get("autop_protocol");
		if (type.equals("all")) {
			returnV = "tftp://"+config.get("server_ip")+"||"+"ftp://"+config.get("server_ip")+"||"+"http://"+config.get("server_ip")+"||"+"https://"+config.get("server_ip");
		}else {
			returnV = type+"://"+config.get("server_ip");
		}
		return returnV;
	}
	
	/**
	 * 执行autop操作直到操作完成
	 * @throws InterruptedException
	 */
	public String  execute_autop_opteration(String protocol) throws Exception {
//		String time="";
		//刷新下界面
//		wd.navigate().refresh();
//		Thread.sleep(2000);
//		judge_login();
		AkuvoxCommon.find_PageUL(wd, "Upgrade", "Advanced",locator);
		String inputvalue = wd.findElement(locator.get("cManualUpdateURL")).getAttribute("value");
		String autop_url=get_autop_url(protocol);
		
		if (!inputvalue.equals(autop_url)) {
			wd.findElement(locator.get("cManualUpdateURL")).clear();
			wd.findElement(locator.get("cManualUpdateURL")).sendKeys(autop_url);
		}
		
		//假如不是tftp协议就需要设置密码
		if (!autop_url.substring(0, autop_url.indexOf(":")).equals("tftp")) {
			wd.findElement(locator.get("cServerUserName")).clear();
			wd.findElement(locator.get("cServerUserName")).sendKeys(config.get("server_account"));
			wd.findElement(locator.get("cServerPwd")).clear();
			wd.findElement(locator.get("cServerPwd")).sendKeys(config.get("server_passwd"));		
//			AkuvoxCommon.click_global_submit(wd,locator);
		}
		
//		wd.findElement(locator.get("AutoPConfirmBtn")).click();
		SeleniumFunction.myClick(wd, wd.findElement(locator.get("AutoPConfirmBtn")));
		Thread.sleep(1000);
		wd.switchTo().alert().accept();
		Thread.sleep(2000);
		String time1=execute_ssh_cmd("date +%s", 2);
		return time1;
//		wait_until_finish(wd);	
//		String time2=execute_ssh_cmd("date +%s", 2);
//		int time3=Integer.parseInt(time2)-Integer.parseInt(time1);
//		return "开始时间:"+time1+",结束时间:"+time2+",总共花费时间:"+time3;
	}
	
	/**
	 * 得到当前版本信息
	 * @return
	 */
	public String get_version() {
		AkuvoxCommon.find_PageUL(wd, "Status", "Basic",locator);
		String version=wd.findElement(locator.get("cFirmwareVersion")).getText();
		return version;
	}
	
	public String execute_ssh_cmd(String command,int code) {
		String result=helper1.sendCmd(command, code);
		return result;
	}
	
	/*public void autop(WebDriver ww) {
		ww.findElement(By.name("cManualUpdateURL")).clear();
		ww.findElement(By.name("cManualUpdateURL")).sendKeys(autop_url);
		
	}*/
	
	public boolean wait_until_finish(WebDriver wd,String ip) throws Exception {
		boolean result =false;
		Thread.sleep(6000);
//		int count = 0;
		String currenturl = wd.getCurrentUrl();
		
		// autop的时候对应设备的IP，会先ping的通然后ping不通，最后在ping的通，依据这个来判断
		String ping_command = "ping "+ip+" -c 3";
		int ping_succ = 0;
		boolean  is_ping = true;
		int num=0;
		while(true) {
			num++;
			String tmp=helper1.sendCmd(ping_command, 1);
			if (is_ping&&tmp.equals("0")) {
				is_ping =false;
				ping_succ++;
			}else if (!is_ping&&!tmp.equals("0")) {
				is_ping = true;
				ping_succ++;
			}
			//假如IP由ping的通到ping不通，再到ping的通，或者一直陷入死循环，差不多5分钟的时候退出
			if (ping_succ==3||num==150) {
				Thread.sleep(8000);
				break;
			}
		}
		
		wd.get(currenturl);
		
		/*for (int i = 0; i < 300; i++) {
			count++;
			try {
				if (!wd.findElement(locator.get("mid_cont1")).isDisplayed()&&wd.findElement(locator.get("mid_cont2")).isDisplayed()) {
					wd.navigate().refresh();
					Thread.sleep(2000);
					result =true;
					break;
				}else {
					Thread.sleep(1000);
				}
			} catch (Exception e) {
//				wd.navigate().refresh();
				Thread.sleep(2000);
				judge_login();
			}
		}*/
		
		/*for (int i = 0; i < 300; i++) {
//			System.out.println("等待第"+i+"次");
			count = i;
			if (!wd.findElement(locator.get("mid_cont1")).isDisplayed()&&wd.findElement(locator.get("tShowForAutoP")).getAttribute("innerText").equals("")) {
				// 首先必须要ping的通
//				String tmp=helper1.sendCmd("ping "+config.get("ip")+" -c 3", 1);
//				if (tmp.equals("0")) {
					result =true;
					break;
//				}else {
//					Thread.sleep(1000);
//				}
			}else {
				if (SeleniumFunction.isWebElementExist_now(wd, locator.get("main-message"))&&wd.findElement(locator.get("main-message")).getText().contains("无法访问此网站")) {
					wd.navigate().refresh();
					Thread.sleep(3000);
					judge_login();
				}
				// 假如所有的都不存在则，进行刷新页面
//				if (!SeleniumFunction.isWebElementExist_now(wd, locator.get("mid_cont1"))&&!SeleniumFunction.isWebElementExist_now(wd, locator.get("tShowForAutoP"))) {
//					wd.navigate().refresh();
//					Thread.sleep(3000);
//					judge_login();
//				}else {
					Thread.sleep(1000);
//				}
			}
		}*/
//		wd.navigate().refresh();
		Thread.sleep(2000);
//		judge_login();
//		System.out.println("总共执行等待："+count);
		return result;
	}
	
	public void judge_login(String ipaddr) throws Exception{
		if (SeleniumFunction.isWebElementExist_now(wd, locator.get("Login"))&&wd.findElement(locator.get("Login")).isDisplayed()) {
			login(ipaddr);
		}
	}
	
	/**
	 * 通过读取日志判断autop是否成功，这个首先需要进入高级设置日志远程服务
	 */
	public void judge_autop_end() {
		String log_file = "/var/log/messages";
		String command1 = "cat /dev/null > "+log_file;
		String command2 = "cat " + log_file+" | grep upgrader | grep completed |wc -l";
		helper1.sendCmd(command1,1);
		
		try {
			for (int i = 0; i < 60; i++) {
				Thread.sleep(5000);
				String tmp=helper1.sendCmd(command2, 2);
				if (!tmp.equals("0")) {
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
