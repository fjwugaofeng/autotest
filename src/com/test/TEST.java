package com.test;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.jcraft.jsch.JSchException;
import com.tools.SSHHelper;
import com.tools.SSHResInfo;

public class TEST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testSSH();
//		test_webdriver();
	}
	
	public static void test_webdriver() {
		WebDriver wd = null;
		try {
			System.setProperty("webdriver.chrome.driver", "config/chromedriver.exe");
			wd = new ChromeDriver();
			wd.manage().window().maximize();
			System.out.println("www.baidu.com");
			wd.get("http://192.168.88.100");
			Thread.sleep(20000);
			System.out.println(wd.getTitle());
			System.out.println(wd.findElement(By.id("main-message")).getText());
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			System.out.println("www.baidu.com");
			wd.quit();
		}
	}
	
	public static void testSSH(){
        try {
            //使用目标服务器机上的用户名和密码登陆
            SSHHelper helper1 = new SSHHelper("192.168.88.199", 22, "root", "123456");
//            SSHHelper helper2 = new SSHHelper("192.168.88.199", 22, "root", "123456",2);
            String command = "ping 192.168.88.105 -c 5";
            String command1 = "ping 192.168.88.100 -c 5";
            String upload="D:\\ftp";
            try {
				String tmp=helper1.sendCmd(command, 1);
				System.out.println("tmp="+tmp);
				tmp=helper1.sendCmd(command1, 1);
				System.out.println("tmp="+tmp);
				tmp=helper1.sendCmd("date '+%Y%m%d%H%M%S'", 2);
				System.out.println("tmp="+tmp);
				
//				helper2.upload("/home", upload);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//            try {
//                SSHResInfo resInfo = helper.sendCmd(command);
//                System.out.println(resInfo.toString());
//                //System.out.println(helper.deleteRemoteFIleOrDir(command));
//                //System.out.println(helper.detectedFileExist(command));
//                helper.close();
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}