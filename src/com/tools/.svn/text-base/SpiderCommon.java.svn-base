/**
 * 蛛网封装方法
 */
package com.tools;

import java.util.*;
import java.io.*;
import java.sql.*;

import javax.swing.JOptionPane;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
public class SpiderCommon {
	
	/**
	 * 选中采集单位
	 * @param wd
	 * @param pcsName 要选中采集的单位
	 * @param sh
	 */
	public static void clickPcs(WebDriver wd,String pcsName,SqlHelper sh){
		ResultSet rs=null;
		try {
			String paras[]={pcsName};
			String sql="SELECT s.PATH FROM sys_department s WHERE s.ALIAS_NAME=?";
			rs=sh.sql_Query(sql, paras);			
			if (rs.next()) {				
				String pcspath = rs.getString(1);
				pcspath = pcspath.substring(0, pcspath.length() - 1);
//				String pcspaths[] =null;;
//				if(pcspath.contains("|")){
					String pcspaths[]= pcspath.split("\\|");
//				}
				
				String pcs = "";
				//获得过滤单位的所有上级单位，包括它自己
				List<String> pcsNames = new ArrayList<String>();
				for (int i = 0; i < pcspaths.length; i++) {
					pcs = pcs + pcspaths[i] + "|";
					sql = "SELECT s.ALIAS_NAME FROM sys_department s WHERE s.PATH=?";
					String[] paras1 = { pcs };
					rs = sh.sql_Query(sql, paras1);
					if (rs.next()) {
						String kkk=rs.getString(1);
						pcsNames.add(kkk);						
					}else{
						System.out.println(sql+"查询结果有误，请确认sql语句是否正确");
					}
				}
				if(!wd.findElement(By.id("pcsNameTree_parentDiv")).isDisplayed()){
					SeleniumFunction.myClick(wd, wd.findElement(By.id("pcsName")));
					SeleniumFunction.webDriver_Wait(500);
				}
				//等待页面加载完
				SpiderCommon.waitFinish(wd);
				for (int i = 0; i < pcsNames.size(); i++) {
					List<WebElement> allpcms = wd.findElement(By.id("pcsNameTree_parentDiv")).findElements(By.tagName("li"));
					for (WebElement e : allpcms) {						
						if (e.findElement(By.xpath("a[1]/span[2]")).getText().equals(pcsNames.get(i))) {
							String isOpend = e.findElement(By.xpath("span[1]")).getAttribute("class");
							if (isOpend.endsWith("close")&& (i != pcsNames.size() - 1)) {
								e.findElement(By.xpath("span[1]")).click();
							}
							if (i == pcsNames.size() - 1) {
								e.findElement(By.xpath("a[1]/span[2]")).click();
							}
							break;
						}
					}
				}
			}else{
				System.out.println("过滤的采集单位错误，请确认！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 选中采集单位中的单位
	 * @param wd
	 * @param pcsName 要选中的单位名称
	 * @param ip 要测试的网站
	 */
	public static void clickPcs(WebDriver wd,String pcsName,String ip){
		ResultSet rs=null;
		SqlHelper sh=new SqlHelper(ip, Constants.ZW_DB_ACCOUNT, Constants.ZW_DB_PASSWD, false);
		SqlHelper sh1=new SqlHelper(Constants.Local_DB_IP, Constants.Local_DB_ACCOUNT, Constants.Local_DB_PASSWD, true);
		String locatersql="SELECT * FROM locator l WHERE l.model='common'";
		Map<String, By> locators=SpiderCommon.getLocaterType(locatersql, sh1);
		try {
			String paras[]={pcsName};
			//得到单位path
			String sql="SELECT s.PATH FROM sys_department s WHERE s.ALIAS_NAME=?";
			rs=sh.sql_Query(sql, paras);			
			if (rs.next()) {				
				String pcspath = rs.getString(1);
				pcspath = pcspath.substring(0, pcspath.length() - 1);
				String pcspaths[]= pcspath.split("\\|");
				
				String pcs = "";
				//获得过滤单位的所有上级单位，包括它自己
				List<String> pcsNames = new ArrayList<String>();
				for (int i = 0; i < pcspaths.length; i++) {
					pcs = pcs + pcspaths[i] + "|";
					sql = "SELECT s.ALIAS_NAME FROM sys_department s WHERE s.PATH=?";
					String[] paras1 = { pcs };
					rs = sh.sql_Query(sql, paras1);
					if (rs.next()) {
						String kkk=rs.getString(1);
						pcsNames.add(kkk);						
					}else{
						System.out.println(sql+"查询结果有误，请确认sql语句是否正确");
					}
				}
				if(SeleniumFunction.isWebElementExist_now(wd, locators.get("common.pcs.pcsname"))){
					wd.findElement(locators.get("common.pcs.pcsname"));
					SeleniumFunction.webDriver_Wait(1000);
				}
				//假如采集单位没弹出来就强制点击
				if(!wd.findElement(locators.get("common.pcs.parentdiv")).isDisplayed()){
					SeleniumFunction.myClick(wd, wd.findElement(locators.get("common.pcs.pcsname")));
					SeleniumFunction.webDriver_Wait(500);
				}
				//等待页面加载完
				SpiderCommon.waitFinish(wd);
				for (int i = 0; i < pcsNames.size(); i++) {
					List<WebElement> allpcms = wd.findElement(locators.get("common.pcs.parentdiv")).findElements(locators.get("common.pcs.li"));
					for (WebElement e : allpcms) {						
						if (e.findElement(locators.get("common.pcs.currentname")).getText().equals(pcsNames.get(i))) {
							String isOpend = e.findElement(locators.get("common.pcs.span[1]")).getAttribute("class");
							if (isOpend.endsWith("close")&& (i != pcsNames.size() - 1)) {
								e.findElement(locators.get("common.pcs.span[1]")).click();
								SpiderCommon.waitFinish(wd);
								SeleniumFunction.webDriver_Wait(1000);
							}
							if (i == pcsNames.size() - 1) {
								e.findElement(locators.get("common.pcs.currentname")).click();
							}
							break;
						}
					}
				}
			}else{
				System.out.println("过滤的采集单位错误，请确认！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			sh.close_Resoure();
			sh1.close_Resoure();
		}
	}
	
	//在采集单位的下拉框选中要过滤的单位
	public static void clickPcs(WebDriver wd,String pcsName,String path,SqlHelper sh){
		ResultSet rs=null;
			try {
				String paras[]={pcsName,path};
				String sql="SELECT s.PATH FROM sys_department s WHERE s.ALIAS_NAME=? and s.path=?";
				rs=sh.sql_Query(sql, paras);			
				if (rs.next()) {				
					String pcspath = rs.getString(1);
					pcspath = pcspath.substring(0, pcspath.length() - 1);
//					String pcspaths[] =null;;
//					if(pcspath.contains("|")){
						String pcspaths[]= pcspath.split("\\|");
//					}
					
					String pcs = "";
					//获得过滤单位的所有上级单位，包括它自己
					List<String> pcsNames = new ArrayList<String>();
					for (int i = 0; i < pcspaths.length; i++) {
						pcs = pcs + pcspaths[i] + "|";
						sql = "SELECT s.ALIAS_NAME FROM sys_department s WHERE s.PATH=?";
						String[] paras1 = { pcs };
						rs = sh.sql_Query(sql, paras1);
						if (rs.next()) {
							String kkk=rs.getString(1);
//							if(kkk.equals("高检院")){
//								pcsNames.add("高检");
//							}else{
								pcsNames.add(kkk);
//							}
							
						}else{
							System.out.println(sql+"查询结果有误，请确认sql语句是否正确");
						}
					}
					//			findTree(pcsNames,wd.findElement(By.id("pcsNameTree")));
					if(!wd.findElement(By.id("pcsNameTree_parentDiv")).isDisplayed()){
//						wd.findElement(By.id("pcsName")).click();
						SeleniumFunction.myClick(wd, wd.findElement(By.id("pcsName")));
						SeleniumFunction.webDriver_Wait(500);
					}
					
					for (int i = 0; i < pcsNames.size(); i++) {
						List<WebElement> allpcms = wd.findElement(By.id("pcsNameTree_parentDiv")).findElements(By.tagName("li"));
						for (WebElement e : allpcms) {						
							if (e.findElement(By.xpath("a[1]/span[2]")).getText().equals(pcsNames.get(i))/*||(i==0&&e.findElement(By.xpath("a[1]/span[2]")).getText().equals("高检"))*/) {
								String isOpend = e.findElement(By.xpath("span[1]")).getAttribute("class");
								if (isOpend.endsWith("close")&& (i != pcsNames.size() - 1)) {
									e.findElement(By.xpath("span[1]")).click();
								}
								if (i == pcsNames.size() - 1) {
									e.findElement(By.xpath("a[1]/span[2]")).click();
								}
								break;
							}
						}
					}
				}else{
					System.out.println("过滤的采集单位错误，请确认！");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	/**
	 * 得到所选单位的所有下级单位名(选择的是ALIAS_NAME)
	 * @param pcsName
	 * @param ip
	 * @return
	 */
	public static List<String> getSelPcsSons(String pcsName,String ip){
		List<String> ls=new ArrayList<String>();
		ResultSet rs=null;
		SqlHelper sh=new SqlHelper(ip, Constants.ZW_DB_ACCOUNT, Constants.ZW_DB_PASSWD, false);
		String sql="SELECT d.PATH FROM sys_department d WHERE d.`NAME` =? OR d.ALIAS_NAME LIKE ?";
		String paras[]={pcsName,pcsName};
		try {
			rs=sh.sql_Query(sql,paras);
			while(rs.next()){
				String casePath=rs.getString(1);
				String sql1="SELECT d.ALIAS_NAME FROM sys_department d WHERE d.PATH LIKE ?";
				String paras1[]={casePath+"%"};
				ResultSet rs1=sh.sql_Query(sql1, paras1);
				while(rs1.next()){
					ls.add(rs1.getString(1));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			sh.close_Resoure();
		}
		return ls;
	}
	
	/**
	 * 得到所选单位的所有下级单位名(选择的是ALIAS_NAME1)
	 * @param pcsName
	 * @param ip
	 * @return
	 */
	public static List<String> getSelPcsSons1(String pcsName,String ip){
		List<String> ls=new ArrayList<String>();
		ResultSet rs=null;
		SqlHelper sh=new SqlHelper(ip, Constants.ZW_DB_ACCOUNT, Constants.ZW_DB_PASSWD, false);
		String sql="SELECT d.PATH FROM sys_department d WHERE d.`NAME` =? OR d.ALIAS_NAME LIKE ?";
		String paras[]={pcsName,pcsName};
		try {
			rs=sh.sql_Query(sql,paras);
			while(rs.next()){
				String casePath=rs.getString(1);
				String sql1="SELECT d.`NAME` FROM sys_department d WHERE d.PATH LIKE ?";
				String paras1[]={casePath+"%"};
				ResultSet rs1=sh.sql_Query(sql1, paras1);
				while(rs1.next()){
					ls.add(rs1.getString(1));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			sh.close_Resoure();
		}
		return ls;
	}
	
	/**
	 * 得到给定案件性质的所有下属案件性质
	 * @param wd
	 * @param caseName
	 * @param ip
	 * @return
	 */
	public static List<String> getSelCaseSons(String caseName,String ip){
		List<String> ls=new ArrayList<String>();
		ResultSet rs=null;
		SqlHelper sh=new SqlHelper(ip, Constants.ZW_DB_ACCOUNT, Constants.ZW_DB_PASSWD, false);
		String sql="SELECT t.PATH FROM sys_case_type t WHERE t.DETAIL =?";
		String paras[]={caseName};
		try {
			rs=sh.sql_Query(sql,paras);
			while(rs.next()){
				String casePath=rs.getString(1);
				String sql1="SELECT t.DETAIL FROM sys_case_type t WHERE t.PATH LIKE ?";
				String paras1[]={casePath+"%"};
				ResultSet rs1=sh.sql_Query(sql1, paras1);
				while(rs1.next()){
					ls.add(rs1.getString(1));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			sh.close_Resoure();
		}
		return ls;
	}
	
	/**
	 * 选中案件性质,这个必须先进行弹出框选中
	 * @param wd
	 * @param caseName 案件性质的名字
	 * @param ip 测试ip
	 */
	public static void clickCase(WebDriver wd,String caseName,String ip){
		ResultSet rs=null;
		SqlHelper sh=new SqlHelper(ip, Constants.ZW_DB_ACCOUNT, Constants.ZW_DB_PASSWD, false);
		SqlHelper sh1=new SqlHelper(Constants.Local_DB_IP, Constants.Local_DB_ACCOUNT, Constants.Local_DB_PASSWD, true);
		String locatersql="SELECT * FROM locator l WHERE l.model='common'";
		Map<String, By> locators=SpiderCommon.getLocaterType(locatersql, sh1);
		try{
			String paras[]={caseName};
			String sql="SELECT c.PATH FROM sys_case_type c WHERE c.DETAIL=?";
			rs=sh.sql_Query(sql, paras);
			if (rs.next()) {
				String casepath = rs.getString(1);
				casepath = casepath.substring(0, casepath.length() - 1);
				String casepaths[]= casepath.split("\\|");
				
				String pcs = "";
				//获得过滤案件性质的所有上级，包括它自己
				List<String> caseNames = new ArrayList<String>();
				for (int i = 0; i < casepaths.length; i++) {
					pcs = pcs + casepaths[i] + "|";
					sql = "SELECT c.DETAIL FROM sys_case_type c WHERE c.PATH=?";
					String[] paras1 = { pcs };
					rs = sh.sql_Query(sql, paras1);
					if (rs.next()) {
						String kkk=rs.getString(1);
						caseNames.add(kkk);						
					}else{
						System.out.println(sql+"查询结果有误，请确认sql语句是否正确");
					}
				}
				
				//判断案件性质选择框有么有被打开
//				if((SeleniumFunction.isWebElementExist_now(wd, locators.get("common.case.parentdiv")))&&(!wd.findElement(locators.get("common.case.parentdiv")).isDisplayed())){
//					//这个是数据导入管理的案件性质输入框，比较另类。。。
//					if (SeleniumFunction.isWebElementExist_now(wd, locators.get("common.case.casetype1"))&&wd.findElement(locators.get("common.case.casetype1")).isDisplayed()) {
//						wd.findElement(locators.get("common.case.casetype1")).click();
//					}else if (SeleniumFunction.isWebElementExist_now(wd, locators.get("common.case.casetype"))&&wd.findElement(locators.get("common.case.casetype")).isDisplayed()) {
//						//这个是通用的案件性质输入框
//						wd.findElement(locators.get("common.case.casetype")).click();
//					}
//					SeleniumFunction.webDriver_Wait(1000);
//				}
				
//				if (SeleniumFunction.isWebElementExist_now(wd, locators.get("common.casedialogdiv"))&&(!wd.findElement(locators.get("common.casedialogdiv")).isDisplayed())) {
//					if (SeleniumFunction.isWebElementExist_now(wd, locators.get("common.case.casetype2"))&&wd.findElement(locators.get("common.case.casetype2")).isDisplayed()) {
//						wd.findElement(locators.get("common.case.casetype2")).click();
//					}
//				}
				 if (SeleniumFunction.isWebElementExist_now(wd, locators.get("common.case.casetype2"))&&wd.findElement(locators.get("common.case.casetype2")).isDisplayed()) {
						if ((SeleniumFunction.isWebElementExist_now(wd, locators.get("common.casedialogdiv")))&&(!wd.findElement(locators.get("common.casedialogdiv")).isDisplayed())) {
							wd.findElement(locators.get("common.case.casetype2")).click();
							SeleniumFunction.webDriver_Wait(1000);
						}
					}else if (SeleniumFunction.isWebElementExist_now(wd, locators.get("common.case.casetype1"))&&wd.findElement(locators.get("common.case.casetype1")).isDisplayed()) {
					if ((SeleniumFunction.isWebElementExist_now(wd, locators.get("common.case.parentdiv")))&&(!wd.findElement(locators.get("common.case.parentdiv")).isDisplayed())) {
						wd.findElement(locators.get("common.case.casetype1")).click();
						SeleniumFunction.webDriver_Wait(1000);
					}
				}else if (SeleniumFunction.isWebElementExist_now(wd, locators.get("common.case.casetype"))&&wd.findElement(locators.get("common.case.casetype")).isDisplayed()) {
					//这个是通用的案件性质输入框
					if ((SeleniumFunction.isWebElementExist_now(wd, locators.get("common.case.parentdiv")))&&(!wd.findElement(locators.get("common.case.parentdiv")).isDisplayed())) {
						wd.findElement(locators.get("common.case.casetype")).click();
						SeleniumFunction.webDriver_Wait(1000);
					}					
				}
				
				//直接点击全部，选中案件性质都选择全部的，比较全
				boolean isPlugins=false;
				if (SeleniumFunction.isWebElementExist_now(wd, locators.get("common.case.all"))&&wd.findElement(locators.get("common.case.all")).isDisplayed()) {
					wd.findElement(locators.get("common.case.all")).click();
				}else if (SeleniumFunction.isWebElementExist_now(wd, locators.get("common.case.all1"))&&wd.findElement(locators.get("common.case.all1")).isDisplayed()) {
					wd.findElement(locators.get("common.case.all1")).click();
					isPlugins=true;
				}				
				SeleniumFunction.webDriver_Wait(1000);
				
				if(wd.findElement(locators.get("common.case.all")).getAttribute("class").equals("active")||wd.findElement(locators.get("common.case.all1")).getAttribute("class").equals("active")){
					for (int i = 0; i < caseNames.size(); i++) {
						List<WebElement> allpcms = null;
						if(isPlugins){
							allpcms=wd.findElement(locators.get("common.case.tree1")).findElements(locators.get("common.pcs.li"));
						}else {
							allpcms=wd.findElement(locators.get("common.case.tree")).findElements(locators.get("common.pcs.li"));
						}
						for (WebElement e : allpcms) {						
							if (e.findElement(locators.get("common.pcs.currentname")).getText().equals(caseNames.get(i))) {
								String isOpend = e.findElement(locators.get("common.pcs.span[1]")).getAttribute("class");
								if (isOpend.endsWith("close")&& (i != caseNames.size() - 1)) {
									SeleniumFunction.myClick(wd, e.findElement(locators.get("common.pcs.span[1]")));
									SpiderCommon.waitFinish(wd);
									SeleniumFunction.webDriver_Wait(1000);
//									e.findElement(By.xpath("span[1]")).click();
								}
								if (i == caseNames.size() - 1) {
									SeleniumFunction.myClick(wd, e.findElement(locators.get("common.pcs.currentname")));
//									e.findElement(By.xpath("a[1]/span[2]")).click();
								}
								break;
							}
						}
					}
				}else {
					System.out.println("不能切换到全部案件性质，请确认");
				}
			}else{
				System.out.println("过滤的案件性质错误，请确认");
			}
			//点击确定按钮
			if(SeleniumFunction.isWebElementExist_now(wd, locators.get("common.case.alltree"))&&wd.findElement(locators.get("common.case.conbtn")).isDisplayed()){
				wd.findElement(locators.get("common.case.conbtn")).click();
			}else if (SeleniumFunction.isWebElementExist_now(wd, locators.get("common.case.tree1"))&&wd.findElement(locators.get("common.case.conbtn1")).isDisplayed()) {
				wd.findElement(locators.get("common.case.conbtn1")).click();
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			sh.close_Resoure();
			sh1.close_Resoure();
		}
	}
	
	//在案件性质下拉框选中要过滤的案件类型
	public static void clickCase(WebDriver wd,String caseName,SqlHelper sh){
		ResultSet rs=null;
		try{
			String paras[]={caseName};
			String sql="SELECT c.PATH FROM sys_case_type c WHERE c.DETAIL=?";
			rs=sh.sql_Query(sql, paras);
			if (rs.next()) {
				String casepath = rs.getString(1);
				casepath = casepath.substring(0, casepath.length() - 1);
				String casepaths[]= casepath.split("\\|");
				
				String pcs = "";
				//获得过滤案件性质的所有上级，包括它自己
				List<String> caseNames = new ArrayList<String>();
				for (int i = 0; i < casepaths.length; i++) {
					pcs = pcs + casepaths[i] + "|";
					sql = "SELECT c.DETAIL FROM sys_case_type c WHERE c.PATH=?";
					String[] paras1 = { pcs };
					rs = sh.sql_Query(sql, paras1);
					if (rs.next()) {
						String kkk=rs.getString(1);
						caseNames.add(kkk);
						
					}else{
						System.out.println(sql+"查询结果有误，请确认sql语句是否正确");
					}
				}
				
				if(!wd.findElement(By.id("caseDiv")).isDisplayed()){
					wd.findElement(By.id("dataImport_caseType")).click();
					SeleniumFunction.webDriver_Wait(500);
				}
				
				String classvalue=wd.findElement(By.xpath("//*[@id='caseDiv']/div[1]/ul/li[1]")).getAttribute("class");
				if(classvalue.equals("active")){
					wd.findElement(By.xpath("//*[@id='caseDiv']/div[1]/ul/li[2]")).click();
					SeleniumFunction.webDriver_Wait(1000);
				}
				if(wd.findElement(By.xpath("//*[@id='caseDiv']/div[1]/ul/li[2]")).getAttribute("class").equals("active")){
					for (int i = 0; i < caseNames.size(); i++) {
						List<WebElement> allpcms = wd.findElement(By.id("caseTypeTree")).findElements(By.tagName("li"));
						for (WebElement e : allpcms) {						
							if (e.findElement(By.xpath("a[1]/span[2]")).getText().equals(caseNames.get(i))) {
								String isOpend = e.findElement(By.xpath("span[1]")).getAttribute("class");
								if (isOpend.endsWith("close")&& (i != caseNames.size() - 1)) {
									SeleniumFunction.myClick(wd, e.findElement(By.xpath("span[1]")));
//									e.findElement(By.xpath("span[1]")).click();
								}
								if (i == caseNames.size() - 1) {
									SeleniumFunction.myClick(wd, e.findElement(By.xpath("a[1]/span[2]")));
//									e.findElement(By.xpath("a[1]/span[2]")).click();
								}
								break;
							}
						}
					}
				}
			}else{
				System.out.println("过滤的案件性质错误，请确认");
			}
			if(SeleniumFunction.isWebElementExist_now(wd, By.id("caseTypeTreeDiv"))&&wd.findElement(By.xpath("//*[@id='caseDiv']/div[3]/div/button[1]")).isDisplayed()){
				wd.findElement(By.xpath("//*[@id='caseDiv']/div[3]/div/button[1]")).click();
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//上面的方法一样，这种适用于插件中兼容的方式
		public static void clickCase1(WebDriver wd,String caseName,SqlHelper sh){
			ResultSet rs=null;
			try{
				String paras[]={caseName};
				String sql="SELECT c.PATH FROM sys_case_type c WHERE c.DETAIL=?";
				rs=sh.sql_Query(sql, paras);
				if (rs.next()) {
					String casepath = rs.getString(1);
					casepath = casepath.substring(0, casepath.length() - 1);
					String casepaths[]= casepath.split("\\|");
					
					String pcs = "";
					//获得过滤案件性质的所有上级，包括它自己
					List<String> caseNames = new ArrayList<String>();
					for (int i = 0; i < casepaths.length; i++) {
						pcs = pcs + casepaths[i] + "|";
						sql = "SELECT c.DETAIL FROM sys_case_type c WHERE c.PATH=?";
						String[] paras1 = { pcs };
						rs = sh.sql_Query(sql, paras1);
						if (rs.next()) {
							String kkk=rs.getString(1);
							caseNames.add(kkk);
							
						}else{
							System.out.println(sql+"查询结果有误，请确认sql语句是否正确");
						}
					}
					
					String classvalue=wd.findElement(By.xpath("//*[@id='caseDialogDiv']/div[1]/ul/li[1]")).getAttribute("class");
					if(classvalue.equals("active")){
						wd.findElement(By.xpath("//*[@id='caseDialogDiv']/div[1]/ul/li[2]")).click();
						SeleniumFunction.webDriver_Wait(1000);
					}
					if(wd.findElement(By.xpath("//*[@id='caseDialogDiv']/div[1]/ul/li[2]")).getAttribute("class").equals("active")){
						for (int i = 0; i < caseNames.size(); i++) {
							List<WebElement> allpcms = wd.findElement(By.id("caseDialogTypeTree")).findElements(By.tagName("li"));
							for (WebElement e : allpcms) {						
								if (e.findElement(By.xpath("a[1]/span[2]")).getText().equals(caseNames.get(i))) {
									String isOpend = e.findElement(By.xpath("span[1]")).getAttribute("class");
									if (isOpend.endsWith("close")&& (i != caseNames.size() - 1)) {
										SeleniumFunction.myClick(wd, e.findElement(By.xpath("span[1]")));
//										e.findElement(By.xpath("span[1]")).click();
									}
									if (i == caseNames.size() - 1) {
										SeleniumFunction.myClick(wd, e.findElement(By.xpath("a[1]/span[2]")));
//										e.findElement(By.xpath("a[1]/span[2]")).click();
									}
									break;
								}
							}
						}
					}
				}else{
					System.out.println("过滤的案件性质错误，请确认");
				}
				//*[@id="caseDialogDiv"]/div[3]/div/button[1]
				if(SeleniumFunction.isWebElementExist_now(wd, By.id("caseDialogTypeTree"))&&wd.findElement(By.xpath("//*[@id='caseDialogDiv']/div[3]/div/button[1]")).isDisplayed()){
					wd.findElement(By.xpath("//*[@id='caseDialogDiv']/div[3]/div/button[1]")).click();
				}
				
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	
	//设置自定义时间
	public static boolean useTime(WebDriver wd,WebDriverWait wdw,String startTime,String endTime){
		boolean isOpen=true;
		SeleniumFunction.webDriver_Wait(1000);
		if(SeleniumFunction.isWebElementExist_now(wd, By.id("btime"))){
			wdw.until(ExpectedConditions.presenceOfElementLocated(By.id("btime"))).click();
			SeleniumFunction.webDriver_Wait(1000);
			if(SeleniumFunction.isWebElementExist_now(wd, By.tagName("iframe"))&&wd.findElement(By.tagName("iframe")).isDisplayed()){
				System.out.println("自定义开始时间设置的时间控件弹出成功");
				((JavascriptExecutor)wd).executeScript("var setDate=document.getElementById(\"btime\");setDate.removeAttribute('readonly');") ;
				wd.findElement(By.id("btime")).sendKeys(startTime);		
			}else{
				System.out.println("自定义开始时间设置的时间控件没有弹出");
				isOpen=false;
			}
		}else if(SeleniumFunction.isWebElementExist_now(wd, By.id("dataImportStartDate"))){
			wdw.until(ExpectedConditions.presenceOfElementLocated(By.id("dataImportStartDate"))).click();
			SeleniumFunction.webDriver_Wait(1000);
			if(SeleniumFunction.isWebElementExist_now(wd, By.tagName("iframe"))){
				System.out.println("自定义开始时间设置的时间控件弹出成功");
				((JavascriptExecutor)wd).executeScript("var setDate=document.getElementById(\"dataImportStartDate\");setDate.removeAttribute('readonly');") ;
				wd.findElement(By.id("dataImportStartDate")).sendKeys(startTime);		
			}else{
				System.out.println("自定义开始时间设置的时间控件没有弹出");
				isOpen=false;
			}
		}
		
		if(SeleniumFunction.isWebElementExist_now(wd, By.id("etime"))){
			wdw.until(ExpectedConditions.presenceOfElementLocated(By.id("etime"))).click();
			SeleniumFunction.webDriver_Wait(1000);
			if(SeleniumFunction.isWebElementExist_now(wd, By.tagName("iframe"))&&wd.findElement(By.tagName("iframe")).isDisplayed()){
				System.out.println("自定义结束时间设置的时间控件弹出成功");
//				wd.switchTo().frame(wd.findElement(By.tagName("iframe")));
//				wdw.until(ExpectedConditions.presenceOfElementLocated(By.id("dpClearInput"))).click();
//				wd.switchTo().defaultContent();
				((JavascriptExecutor)wd).executeScript("var setDate=document.getElementById(\"etime\");setDate.removeAttribute('readonly');") ;
				wd.findElement(By.id("etime")).sendKeys(endTime);
			}
		}else if(SeleniumFunction.isWebElementExist_now(wd, By.id("dataImportEndDate"))){
			wdw.until(ExpectedConditions.presenceOfElementLocated(By.id("dataImportEndDate"))).click();
			SeleniumFunction.webDriver_Wait(1000);
			if(SeleniumFunction.isWebElementExist_now(wd, By.tagName("iframe"))){
				System.out.println("自定义结束时间设置的时间控件弹出成功");
//				wd.switchTo().frame(wd.findElement(By.tagName("iframe")));
//				wdw.until(ExpectedConditions.presenceOfElementLocated(By.id("dpClearInput"))).click();
//				wd.switchTo().defaultContent();
				((JavascriptExecutor)wd).executeScript("var setDate=document.getElementById(\"dataImportEndDate\");setDate.removeAttribute('readonly');") ;
				wd.findElement(By.id("dataImportEndDate")).sendKeys(endTime);
		
			}else{
				System.out.println("自定义结束时间设置的时间控件没有弹出");
				isOpen=false;
			}
		}
			return isOpen;
	}
	
	//导出功能
	/**
	 * 
	 * @param wd
	 * @param wdw
	 * @param today
	 * @param expBtn 导出按钮的locate
	 * @param isMore true的时候是导出excel和csv格式，false直接导出，没有格式选择
	 */
	public static void download(WebDriver wd,WebDriverWait wdw,List<WebElement> today,By expBtn,boolean isMore){
//		List<WebElement> today = wdw.until(ExpectedConditions.presenceOfElementLocated(By.id("appTypeTotalTable"))).findElements(By.cssSelector("tr[id]"));
		String path="C:/Users/wugf/Downloads";
		if(isMore){
			try {
				wd.findElement(expBtn).click();
			}catch (Exception e){
				((JavascriptExecutor) wd).executeScript("arguments[0].click();", wd.findElement(expBtn));
			}
			
			if(today.size()==0){
				if(SeleniumFunction.isWebElementExist(wd, By.id("messageBoxDialog"))&&wd.findElement(By.id("messageBoxDialog")).isDisplayed()){
					System.out.println("列表没有数据的时候导出有提示");
				}else{
					System.out.println("列表没有数据的时候导出没有提示");
				}
			}else{
				if(SeleniumFunction.isWebElementExist(wd, By.id("commonExportDialog"))&&wd.findElement(By.id("commonExportDialog")).isDisplayed()){
					System.out.println("导出文件的格式选择弹出框正确");
					List<WebElement> seltions=wd.findElement(By.name("exportType")).findElements(By.tagName("option"));
					for(WebElement e:seltions){
						File f=new File(path);
						String []files=f.list();
						for(int i=0;i<files.length;i++){
							File son=new File(path+"/"+files[i]);
							son.delete();
						}
						e.click();
						SeleniumFunction.webDriver_Wait(1000);
						wd.findElement(By.xpath("//*[@id='commonExportDialog']/div/button")).click();
						while(wdw.until(ExpectedConditions.presenceOfElementLocated(By.id("overlayProcessingDialog"))).isDisplayed()){
							SeleniumFunction.webDriver_Wait(500);
						}
						SeleniumFunction.webDriver_Wait(1000);
						File newfile=new File(path);
						String newfiles[]=newfile.list();
						if(newfiles.length==0){
							System.out.println(e.getText()+"导出功能不通过");
						}else{
							System.out.println(e.getText()+"导出文件功能通过");
						}
					}
				}else{
					System.out.println("导出文件的格式选择弹出框不正确");
				}
			}
		}else{
			wd.findElement(expBtn).click();
			if(SeleniumFunction.isWebElementExist(wd, By.id("messageBoxDialog"))&&wd.findElement(By.id("messageBoxDialog")).isDisplayed()){
				//点击取消按钮
				wd.findElement(By.xpath("//*[@id='messageBoxDialog']/div[2]/div/button[2]")).click();
				SeleniumFunction.webDriver_Wait(500);
				boolean tem=true;
				if(wd.findElement(By.id("messageBoxDialog")).isDisplayed()){
					System.out.println("取消导出功能错误");
					tem=false;
				}else{
					System.out.println("取消导出功能正确");
					tem=true;
				}
				if(tem){
					wd.findElement(expBtn).click();
					SeleniumFunction.webDriver_Wait(500);
					File f=new File(path);
					String []files=f.list();
					for(int i=0;i<files.length;i++){
						File son=new File(path+"/"+files[i]);
						son.delete();
					}
					wd.findElement(By.xpath("//*[@id='messageBoxDialog']/div[2]/div/button[1]")).click();
					while(wdw.until(ExpectedConditions.presenceOfElementLocated(By.id("overlayProcessingDialog"))).isDisplayed()){
						SeleniumFunction.webDriver_Wait(500);
					}
					File newfile=new File(path);
					String newfiles[]=newfile.list();
					if(newfiles.length==0){
						System.out.println("导出功能不通过");
					}else{
						System.out.println("导出文件功能通过");
					}
				}else{
					System.out.println("取消导出功能错误导致确定功能不能测试");
				}
				
			}
		}
		
	}
	
	//时间过滤
/*	public static void selTime(WebDriver wd,WebDriverWait wdw,SqlHelper sh){
//		AppTypeTotal att=new AppTypeTotal();
		ResultSet rs=null;
		List<WebElement> timeses=wd.findElement(By.id("searchTime")).findElements(By.tagName("option"));
		try {
			for(WebElement timeSel:timeses){
				timeSel.click();
				wd.findElement(By.id("appTypeTotalForm_queryBtn")).click();
				SeleniumFunction.isLoad(wdw);
				String tem="0";
				
				if(!(SeleniumFunction.isWebElementExist_now(wd, By.id("appTypeTotalTable_tdNullNum"))&&wd.findElement(By.id("appTypeTotalTable_tdNullNum")).isDisplayed())){
					tem=wd.findElement(By.xpath("//*[@id='appTypeTotalPager_left']/div")).getText();
					tem=getStringInt(tem);			
				}
				int totalCount=Integer.parseInt(tem);						
				if(timeSel.getText().equals("全部")){														
					String sql="SELECT count(*) FROM sys_install_app_info a LEFT JOIN sys_case_type t ON a.CASE_TYPE = t.`CODE` LEFT JOIN sys_department d ON a.pcs_path = d.path ORDER BY a.COLLECT_TIME DESC";
					rs=sh.sql_Query(sql);
					if (rs.next()) {
						if (rs.getInt(1) == totalCount) {
							System.out.println("采集时间全部过滤正确");
						} else {
							System.out.println("采集时间全部过滤不正确");
						}
					}else{
						System.out.println(sql+"查询结果有误，请确认sql语句是否正确");
					}
				}else if(timeSel.getText().equals("本日")){
					String sql="SELECT COUNT(*) FROM sys_install_app_info a LEFT JOIN sys_case_type t ON a.CASE_TYPE = t.`CODE` LEFT JOIN sys_department d ON a.pcs_path = d.path WHERE a.COLLECT_TIME>= SUBDATE(NOW(),INTERVAL CURTIME() HOUR_SECOND) ORDER BY a.COLLECT_TIME DESC";
					String sql1="SELECT SUBDATE(NOW(),INTERVAL CURTIME() HOUR_SECOND)";
					att.exuCompare(wd, wdw, sql, sql1, timeSel.getText(), totalCount);
				}else if(timeSel.getText().equals("本月")){
					String sql="SELECT COUNT(*) FROM sys_install_app_info a LEFT JOIN sys_case_type t ON a.CASE_TYPE = t.`CODE` LEFT JOIN sys_department d ON a.pcs_path = d.path WHERE a.COLLECT_TIME>=SUBDATE(DATE_SUB(NOW(),INTERVAL CURTIME() HOUR_SECOND),INTERVAL (DAYOFMONTH(NOW())-1) DAY) ORDER BY a.COLLECT_TIME DESC";
					String sql1="SELECT SUBDATE(DATE_SUB(NOW(),INTERVAL CURTIME() HOUR_SECOND),INTERVAL (DAYOFMONTH(NOW())-1) DAY)";
					att.exuCompare(wd, wdw, sql, sql1, timeSel.getText(),totalCount);
				}else if(timeSel.getText().equals("本年")){
					String sql="SELECT COUNT(*) FROM sys_install_app_info a LEFT JOIN sys_case_type t ON a.CASE_TYPE = t.`CODE` LEFT JOIN sys_department d ON a.pcs_path = d.path WHERE a.COLLECT_TIME>=SUBDATE(DATE_SUB(NOW(),INTERVAL CURTIME() HOUR_SECOND),INTERVAL (DAYOFYEAR(NOW())-1) DAY) ORDER BY a.COLLECT_TIME DESC";
					String sql1="SELECT SUBDATE(DATE_SUB(NOW(),INTERVAL CURTIME() HOUR_SECOND),INTERVAL (DAYOFYEAR(NOW())-1) DAY)";
					att.exuCompare(wd, wdw, sql, sql1, timeSel.getText(),totalCount);							
				}else if(timeSel.getText().equals("去年")){
					String sql="SELECT COUNT(*) FROM sys_install_app_info a LEFT JOIN sys_case_type t ON a.CASE_TYPE = t.`CODE` LEFT JOIN sys_department d ON a.pcs_path = d.path WHERE a.COLLECT_TIME>=SUBDATE(SUBDATE(DATE_SUB(NOW(),INTERVAL CURTIME() HOUR_SECOND),INTERVAL (DAYOFYEAR(NOW())-1) DAY),INTERVAL 1 YEAR) AND a.COLLECT_TIME<SUBDATE(DATE_SUB(NOW(),INTERVAL CURTIME() HOUR_SECOND),INTERVAL (DAYOFYEAR(NOW())-1) DAY)  ORDER BY a.COLLECT_TIME DESC";
					String sql1="SELECT SUBDATE(SUBDATE(DATE_SUB(NOW(),INTERVAL CURTIME() HOUR_SECOND),INTERVAL (DAYOFYEAR(NOW())-1) DAY),INTERVAL 1 YEAR)";
					String sql2="SELECT SUBDATE(DATE_SUB(NOW(),INTERVAL CURTIME() HOUR_SECOND),INTERVAL (DAYOFYEAR(NOW())-1) DAY)";
					att.exuCompare(wd, wdw, sql, sql1,sql2, timeSel.getText(),totalCount);		
				}else if(timeSel.getText().equals("自定义时间")){
					String start="2015-05-04";
					String end="2016-03-28";
					boolean isOpen=att.userTime(By.xpath("//*[@id='appTypeTotalForm']/div[2]/div"),start ,end );
					if(isOpen){
						wd.findElement(By.id("appTypeTotalForm_queryBtn")).click();
						SeleniumFunction.isLoad(wdw);
						totalCount=Integer.parseInt(getStringInt(wd.findElement(By.xpath("//*[@id='appTypeTotalPager_left']/div")).getText()));
						start=start+" 00:00:00";
						end=end+" 23:59:59";
						String sql="SELECT COUNT(*) FROM sys_install_app_info a LEFT JOIN sys_case_type t ON a.CASE_TYPE = t.`CODE` LEFT JOIN sys_department d ON a.pcs_path = d.path WHERE a.COLLECT_TIME>=? AND a.COLLECT_TIME<=?  ORDER BY a.COLLECT_TIME DESC";
						String paras[]={start,end};
						att.exuCompare(wd, wdw, sql, paras, start, end, timeSel.getText(), totalCount);
					}else{
						System.out.println("自定义时间过滤失败");
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}*/
	
	/**
	 * 得到界面的总数值
	 * @param tem
	 * @return
	 */
	public static String getStringInt(String tem){
		if (!tem.equals("")) {
			int num = tem.indexOf("共");
			int num2 = tem.indexOf("条", num);
			tem = tem.substring(num + 2, num2 - 1);
			if (tem.contains(",")) {
				tem = tem.replaceAll(",", "");
			}
		}else{
			tem="0";
		}
		return tem;
	}

	//判断单位是否被选中
	public static boolean isPcsSelected(WebDriver wd){
		wd.findElement(By.id("pcsName")).click();		
		while(!wd.findElement(By.id("pcsNameTree_parentDiv")).isDisplayed()){
			SeleniumFunction.webDriver_Wait(500);
		}
		List<WebElement> pcsNames=wd.findElement(By.id("pcsNameTree_parentDiv")).findElements(By.tagName("li"));
		boolean isSelected =false;
		//判断是否具有勾选框,有选中判断节点是否勾选，没有的话判断节点是否选中
		if(SeleniumFunction.isWebElementExist_now(wd, By.id("pcsNameTree_parentDiv"),By.xpath("span[2]"))){
			//通过遍历勾选框来确定是否勾选复选框
			for(WebElement pcsName:pcsNames){
				String tem=pcsName.findElement(By.xpath("span[2]")).getAttribute("class");
				if(tem.contains("true")){
					isSelected=true;
					break;
				}
			}
		}else{
			//通过遍历来确认节点是否勾选
			for(WebElement pcsName:pcsNames){
				String tem=pcsName.findElement(By.xpath("a")).getAttribute("class");
				if(tem.contains("curSelectedNode")){
					isSelected=true;
					break;
				}
			}
		}
//		wd.findElement(By.cssSelector("label[innerText='采集单位']")).click();
		//通过点击采集单位的label来关闭单位下拉框
		wd.findElement(By.xpath("//label[contains(text(),'采集单位')]")).click();
		return isSelected;
	}
	
	//读取文件中的内容，并返回相应的值
	public static String fileContext(String filePath){
		String sql="";
		File f=new File(filePath);
		FileReader fr=null;
		BufferedReader br=null;
		try {
			 fr=new FileReader(f);
			 br=new BufferedReader(fr);
			 String string="";
			 while((string=br.readLine())!=null){
					sql=sql+string+"\r\n";
				}
		} catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(br!=null) br.close();
				if(fr!=null) fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sql;
	}
	
	/**
	 * zw等待页面加载完（通过判断load标签）
	 * @param wd 要操作的webdriver
	 */
	public static void waitFinish(WebDriver wd){
		SeleniumFunction.isPageLoad(wd);
		SeleniumFunction.isLoad(wd);
		SeleniumFunction.webDriver_Wait(100);
	}
	
	/**
	 * 得到某一个列的序标
	 * @param wd
	 * @param name 要得到列的名字
	 * @param locator 表的父元素
	 * @return 返回序标值
	 */
	public static int getTileNum(WebDriver wd,String name,By locator){
		int countPcsNameNum=0;
		List<WebElement> lls1=wd.findElement(locator).findElements(By.tagName("th"));
		for(WebElement element:lls1){
			countPcsNameNum++;
			if(element.getText().equals(name)){
				break;
			}
		}
		return countPcsNameNum;
	}
	
	/**
	 * 得到蛛网中某一列的值
	 * @param wd
	 * @param name
	 * @param locator
	 * @return
	 */
	public static List<String> getZWcloumnData(WebDriver wd,String name,By locator){
		List<String> ls=new ArrayList<String>();
		int countPcsNameNum=0;
		List<WebElement> lls1=wd.findElement(locator).findElements(By.tagName("th"));
		for(WebElement element:lls1){
			countPcsNameNum++;
			if(element.getText().equals(name)){
				break;
			}
		}
		
		List<WebElement> lls2=wd.findElement(locator).findElements(By.cssSelector("tr[id]"));
		for(WebElement element:lls2){
			ls.add(element.findElement(By.xpath("td["+countPcsNameNum+"]")).getText());
		}
		return ls;
	}
	
	/**
	 * 通过名字找到某一列的元素
	 * @param wd
	 * @param name
	 * @param locator
	 * @return
	 */
	public static List<WebElement> getZWcloumnElement(WebDriver wd,String name,By locator){
		List<WebElement> ele=new ArrayList<WebElement>();
		int countPcsNameNum=0;
		List<WebElement> lls1=wd.findElement(locator).findElements(By.tagName("th"));
		for(WebElement element:lls1){
			countPcsNameNum++;
			if(element.getText().equals(name)){
				break;
			}
		}
		
		List<WebElement> lls2=wd.findElement(locator).findElements(By.cssSelector("tr[id]"));
		for(WebElement element:lls2){
			ele.add(element.findElement(By.xpath("td["+countPcsNameNum+"]")));
		}
		return ele;
	}
	
	/**
	 * 得到表中的数据
	 * @param wd
	 * @param locator
	 */
	public static List<List<String>> getTableData(WebDriver wd,By locator){
		List<List<String>> lls=new ArrayList<List<String>>();
		List<WebElement> elements=wd.findElement(locator).findElements(By.cssSelector("tr[id]"));
		for(WebElement element:elements){
			List<String> ls=new ArrayList<String>();
			List<WebElement> elementss=element.findElements(By.tagName("td"));
			for(WebElement e:elementss){
				ls.add(e.getText().trim());
//				System.out.print(e.getText().trim()+"  ");
			}
			lls.add(ls);
//			System.out.println();
		}
		return lls;
	}
	
	/**
	 * 移动屏幕到指定的坐标位置
	 * @param wd
	 * @param type 移动的类型，1为相对当前位置移动，2为绝对位置
	 * @param width 宽度
	 * @param height 高度
	 */
	public static void scrollPage(WebDriver wd,int type,int width,int height){
		JavascriptExecutor js=(JavascriptExecutor)wd;
		if(type==1){
			//移动到当前位置的相对位置
			js.executeScript("window.scrollBy("+width+","+height+")");
		}else if(type==2){
			//移动到当前位置的绝对位置
			js.executeScript("window.scrollTo("+width+","+height+")");
		}
		SeleniumFunction.webDriver_Wait(100);
	}
	
	/**
	 * 滚动屏幕到指定元素的位置
	 * @param wd
	 * @param type 2为移动到元素element对象的顶端与当前窗口的顶部对齐 1移动到元素element对象的底端与当前窗口的底部对齐
	 * @param element
	 */
	public static void scrollPage(WebDriver wd,int type,WebElement element){
		JavascriptExecutor js=(JavascriptExecutor)wd;
		if(type==1){
			js.executeScript("arguments[0].scrollIntoView(true);",element);
		}else if(type==2){
			js.executeScript("arguments[0].scrollIntoView(false);",element);
		}
		SeleniumFunction.webDriver_Wait(100);
	}
	
	/**
	 * 滚动到屏幕最底部
	 * @param wd
	 */
	public static void scrollPage(WebDriver wd){
		JavascriptExecutor js=(JavascriptExecutor)wd;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		SeleniumFunction.webDriver_Wait(100);
	}
	
	/**
	 * 遍历单位树
	 * @param wd
	 */
	public static void deepSearch(WebDriver wd){
		List<WebElement> nodeStack=wd.findElement(By.id("pcsNameTree_parentDiv")).findElements(By.tagName("li"));
		while(!nodeStack.isEmpty()){
			WebElement node=nodeStack.get(nodeStack.size()-1);
			System.out.println(node.findElement(By.xpath("a[1]/span[2]")).getText());
			if(node.findElement(By.xpath("span[1]")).getAttribute("class").contains("close")){
				node.findElement(By.xpath("span[1]")).click();
			}
			if(SeleniumFunction.isWebElementExist_now(node,By.xpath("ul") )){
				List<WebElement> childrenList=node.findElement(By.xpath("ul")).findElements(By.tagName("li"));
				if((childrenList!=null)&&(!childrenList.isEmpty())){
					for(WebElement map:childrenList){
						if(!nodeStack.contains(map)){
							nodeStack.add(map);			
						}
					}
				}
			}			
			nodeStack.remove(node);
		}
	}
	
	/**
	 * 得到定位的键值队
	 * @param sql 传入的sql的值
	 * @param sh1 SqlHelper对象
	 * @param locators 存放键值队的map对象
	 */
	public static Map<String,By> getLocaterType(String sql,SqlHelper sh1){
		Map<String,By> locators=new HashMap<String, By>();
		ResultSet rs=sh1.sql_Query(sql);
		try {		
			while(rs.next()){
				String type=rs.getString("locate_type");
				String locater=rs.getString("location");
				By by=null;
				if(type.equals("id")){
					by=By.id(locater);
				}else if(type.equals("xpath")){
					by=By.xpath(locater);
				}else if (type.equals("css")) {
					by=By.cssSelector(locater);
				}else if (type.equals("link")) {
					by=By.linkText(locater);
				}else if (type.equals("name")) {
					by=By.name(locater);
				}else if (type.equals("tag")) {
					by=By.tagName(locater);
				}else if (type.equals("class")) {
					by=By.className(locater);
				}		
				locators.put(rs.getString("keyvalue"), by);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (rs!=null) {
				try {
					rs.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return locators;
	}
	
	/**
	 * 删除蛛网准入测试的截图，日志，和测试报告
	 */
	public static void deleteData(){
		//如果日志文件存在，则删除日志
		File logfile=new File("zwsmoketest.log");
		delR(logfile);
		
		//如果截图存在，则删除截图
		File image=new File("image");
		if(image.exists()){
			File imagefile[]=image.listFiles();
			for(File f:imagefile){
				if(f.exists()){
					f.delete();
				}			
			}		
		}
		
		//如果test-output存在，则删除
		File testoutput=new File("test-output");
		delR(testoutput);
		
	}
	
	/**
	 * 递归删除文件,假如当前是一个文件夹也会把当前文件夹也删除
	 * @param f 要删除的文件
	 */
	public static void delR(File f){
		if(f.exists()){
			//假如是一个文件就直接删除
			if(f.isFile()){
				f.delete();
			}
			
			if(f.isDirectory()){
				File files[]=f.listFiles();
				for(int i=0;i<files.length;i++){
					delR(files[i]);
				}
				f.delete();
			}
		}
	}
	
	/**
	 * 删除文件夹中的数据
	 * @param f
	 */
	public static void delDirData(File f){
		if(f.exists()){	
			
			//假如是一个文件就直接删除
			if(f.isFile()){
				f.delete();
			}
			
			if(f.isDirectory()){
				File files[]=f.listFiles();
				for(int i=0;i<files.length;i++){
					delR(files[i]);
				}
			}
		}
	}
	
	/**
	 * 添加蛛网页面指定模块
	 * @param wd
	 * @param modelName
	 */
	public static boolean addModel(WebDriver wd,String modelName){
		boolean  isAdd=false;
		wd.findElement(By.id("app_mgr_menu")).click();
		SeleniumFunction.webDriver_Wait(2000);
		if(SeleniumFunction.isWebElementExist_now(wd, By.id("appStoreDialog"))&&wd.findElement(By.id("appStoreDialog")).isDisplayed()){
			List<WebElement> modes=wd.findElement(By.id("store-tabs")).findElements(By.tagName("li"));
			for(WebElement element:modes){
				element.click();
				SeleniumFunction.webDriver_Wait(1000);
				List<WebElement> sons=new ArrayList<WebElement>();
				if(element.getText().equals("数据管理与统计")){
					sons=wd.findElement(By.id("dataManageKind")).findElements(By.tagName("li"));
				}else if (element.getText().equals("情报分析类")) {
					sons=wd.findElement(By.id("analyKind")).findElements(By.tagName("li"));
				}else if (element.getText().equals("布控预警类")) {
					sons=wd.findElement(By.id("warnKind")).findElements(By.tagName("li"));
				}else if (element.getText().equals("系统管理类")) {
					sons=wd.findElement(By.id("systemMangeKind")).findElements(By.tagName("li"));
				}else if (element.getText().equals("小工具")) {
					sons=wd.findElement(By.id("toolKind")).findElements(By.tagName("li"));
				}
				for(WebElement e:sons){
					if(e.getText().equals(modelName)){
						if(e.findElements(By.tagName("i")).size()==1){
							e.findElement(By.xpath("div/i[1]")).click();
						}else {
							e.findElement(By.xpath("div/i[2]")).click();
						}						
						isAdd=true;
						break;
					}
				}
				
				if(isAdd){
					break;
				}
			}
		}		
		wd.findElement(By.xpath("/html/body/div[28]/div[1]/button")).click();
		return isAdd;
	}
	
	/**
	 * 得到指定角色的所有菜单功能名
	 * @param ip
	 * @param role 假如是超级管理员或者运维人员为null；
	 * @return
	 */
	public static List<String> getModelNames(String ip,	String role){
		SqlHelper sh=new SqlHelper(ip, Constants.ZW_DB_ACCOUNT, Constants.ZW_DB_PASSWD, false);
		String sql="SELECT f.TITLE FROM sys_function f LEFT JOIN sys_role_function l ON f.ID=l.MENU_ID LEFT JOIN sys_role r ON l.ROLE_ID=r.ROLE_ID WHERE r.ROLE_NAME=? AND f.TYPE='MENU'";
		String sql1="SELECT f.TITLE FROM sys_function f WHERE f.TYPE='MENU'";
		ResultSet rs=null;
		List<String> ls=new ArrayList<String>();
		String paras[]={role};
		try {
			if (role!=null) {
				rs=sh.sql_Query(sql, paras);
			}else {
				rs=sh.sql_Query(sql1);
			}
			
			while(rs.next()){
				ls.add(rs.getString(1).trim());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			sh.close_Resoure();
		}
		return ls;
	}
	
	/**
	 * 把所有的模块添加到主页面
	 * @param wd
	 */
	public static void addAllModel(WebDriver wd){
		wd.findElement(By.id("app_mgr_menu")).click();
		SeleniumFunction.webDriver_Wait(2000);
		if(SeleniumFunction.isWebElementExist_now(wd, By.id("appStoreDialog"))&&wd.findElement(By.id("appStoreDialog")).isDisplayed()){
			List<WebElement> modes=wd.findElement(By.id("store-tabs")).findElements(By.tagName("li"));
			for(WebElement element:modes){
				element.click();
				SeleniumFunction.webDriver_Wait(1000);
				List<WebElement> sons=new ArrayList<WebElement>();
				if(element.getText().equals("数据管理与统计")){
					sons=wd.findElement(By.id("dataManageKind")).findElements(By.tagName("li"));
				}else if (element.getText().equals("情报分析类")) {
					sons=wd.findElement(By.id("analyKind")).findElements(By.tagName("li"));
				}else if (element.getText().equals("布控预警类")) {
					sons=wd.findElement(By.id("warnKind")).findElements(By.tagName("li"));
				}else if (element.getText().equals("系统管理类")) {
					sons=wd.findElement(By.id("systemMangeKind")).findElements(By.tagName("li"));
				}else if (element.getText().equals("小工具")) {
					sons=wd.findElement(By.id("toolKind")).findElements(By.tagName("li"));
				}
				for(WebElement e:sons){
					if(e.findElements(By.tagName("i")).size()==1){
						if (e.findElement(By.xpath("div/i[1]")).getAttribute("class").endsWith("plus")) {
							e.findElement(By.xpath("div/i[1]")).click();
						}
					}else {
						if (e.findElement(By.xpath("div/i[2]")).getAttribute("class").endsWith("plus")) {
							e.findElement(By.xpath("div/i[2]")).click();
						}
					}							
				}
			}
		}		
		wd.findElement(By.xpath("/html/body/div[28]/div[1]/button")).click();
	}
	
	/**
	 * 返回当前登录用户所能操作的所有模块
	 * @param wd
	 * @return
	 */
	public static  List<String> getAllModel(WebDriver wd){
		List<String> ls=new ArrayList<String>();
		wd.findElement(By.id("app_mgr_menu")).click();
		SeleniumFunction.webDriver_Wait(2000);
		if(SeleniumFunction.isWebElementExist_now(wd, By.id("appStoreDialog"))&&wd.findElement(By.id("appStoreDialog")).isDisplayed()){
			List<WebElement> modes=wd.findElement(By.id("store-tabs")).findElements(By.tagName("li"));
			for(WebElement element:modes){
				element.click();
				SeleniumFunction.webDriver_Wait(1000);
				List<WebElement> sons=new ArrayList<WebElement>();
				if(element.getText().equals("数据管理与统计")){
					sons=wd.findElement(By.id("dataManageKind")).findElements(By.tagName("li"));
				}else if (element.getText().equals("情报分析类")) {
					sons=wd.findElement(By.id("analyKind")).findElements(By.tagName("li"));
				}else if (element.getText().equals("布控预警类")) {
					sons=wd.findElement(By.id("warnKind")).findElements(By.tagName("li"));
				}else if (element.getText().equals("系统管理类")) {
					sons=wd.findElement(By.id("systemMangeKind")).findElements(By.tagName("li"));
				}else if (element.getText().equals("小工具")) {
					sons=wd.findElement(By.id("toolKind")).findElements(By.tagName("li"));
				}
				for(WebElement e:sons){
					ls.add(e.getText().trim());
				}
			}
		}		
		wd.findElement(By.xpath("/html/body/div[28]/div[1]/button")).click();
		return ls;
	}
	
	/**
	 * 判断测试是否已经准备好
	 * @param ip
	 * @return
	 */
	public static boolean isReady(String ip){
		boolean isReady=true;
		String message="";
		SqlHelper sh=null;
		ResultSet rs;
		try {
			sh=new SqlHelper(ip, Constants.ZW_DB_ACCOUNT, Constants.ZW_DB_PASSWD, false);
			//判断是否有单位
			rs=sh.sql_Query("SELECT COUNT(1) FROM sys_department d ");
			if(rs.next()){
				if(rs.getInt(1)<=1){
					isReady=isReady&&false;
					message=message+"没有初始化单位";
				}
			}
			//判断是否有数据
			rs=sh.sql_Query("SELECT COUNT(1) FROM gg_collect_info g");
			if (rs.next()) {
				if (rs.getInt(1)==0) {
					isReady=isReady&&false;
					message=message+"没有数据";
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isReady=false;
			message=message+e.getMessage();
		}finally{
			sh.close_Resoure();
			if(isReady){
				JOptionPane.showMessageDialog(null,"环境已经配置好");
			}else {
				JOptionPane.showMessageDialog(null, message);
			}
		}
		return isReady;
	}
	
	/**
	 * 得到顶级单位的名称，不存在的时候返回null
	 * @param ip
	 * @return
	 */
	public static String getTopPcsName(String ip){
		String toppcsname=null;
		SqlHelper sh=new SqlHelper(ip, Constants.ZW_DB_ACCOUNT, Constants.ZW_DB_PASSWD, false);
		String sql="SELECT d.ALIAS_NAME FROM sys_department d WHERE d.PATH='1|'";
		ResultSet rs=null;
		try {
			rs=sh.sql_Query(sql);
			if(rs.next()){
				toppcsname=rs.getString(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			sh.close_Resoure();
		}
		return toppcsname;
	}
	
	/**
	 * 得到采集数据管理中非顶级单位的随机一个值，不存在的时候返回null
	 * @return
	 */
	public static String getExistPcsName(String ip){
		String pcsname="null";
		SqlHelper sh=new SqlHelper(ip, Constants.ZW_DB_ACCOUNT, Constants.ZW_DB_PASSWD, false);
		String sql="SELECT d.ALIAS_NAME FROM gg_collect_info g LEFT JOIN sys_department d ON g.PCSID=d.ID WHERE g.PCSPATH!='1|'";
		ResultSet rs=null;
		try {
			rs=sh.sql_Query(sql);
			if(rs.next()){
				pcsname=rs.getString(1);
			}else {
				pcsname=SpiderCommon.getTopPcsName(ip);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			sh.close_Resoure();
		}
		return pcsname;
	}
	
	/**
	 * 得到随机一个数据的案件性质，不存在的时候返回null
	 * @param ip
	 * @return
	 */
	public static String getExistCaseName(String ip){
		String casename=null;
		SqlHelper sh=new SqlHelper(ip, Constants.ZW_DB_ACCOUNT, Constants.ZW_DB_PASSWD, false);
		String sql="SELECT t.DETAIL FROM gg_collect_info g LEFT JOIN sys_case_type t ON g.CASE_PATH=t.PATH WHERE g.CASE_PATH IS NOT NULL";
		ResultSet rs=null;
		try {
			rs=sh.sql_Query(sql);
			if(rs.next()){
				casename=rs.getString(1);
				if(casename==null||casename.equals("")){
					casename="其他";
				}
			}else {
				casename="其他";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			sh.close_Resoure();
		}
		return casename;
	}
}
