package com.tools;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;

public class SmokeCommon {
	
	/**
	 * 用例断言失败的处理
	 * @param wd
	 * @param e
	 * @param casename
	 * @param ls 存放测试结果的集合
	 */
	public static void doError(WebDriver wd,Error e,String casename,Map<String, String> ls){
		ls.put(casename,Constants.TestResult_FAIL);
		Log.info("错误信息为："+e.getMessage());
		SeleniumFunction.printScreen_Name(wd,casename+ "错误");
		Assert.fail("用例执行失败");
	}
	
	/**
	 * 用例异常的处理
	 * @param wd
	 * @param e
	 * @param casename
	 */
	public static void doException(WebDriver wd,Exception e,String casename,Map<String, String> ls){
		//假如断言错误就判断添加用例执行失败
		ls.put(casename,Constants.TestResult_NA);
		//打印异常信息
		Log.info("异常信息为："+e.getMessage());
		//对异常进行截图
		SeleniumFunction.printScreen_Name(wd, casename+"异常");
		//断言用例执行失败
		throw new SkipException("出现异常，用例Skip处理");
	}
	
	/**
	 * 返回调用此方法的方法的名字
	 * @return
	 */
	public static String getMethodName(){
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}
	
	/**
	 *选择时间组：入库OR采集+统计时间类型
	 * @param upORcol 采集时间或入库时间类型的元素
	 * @param timeType 过滤时间类型，如本日，本周。。。
	 * @param type 以1结尾本日，2为本周，3为本月，4为本年，5为去年，6为全部，7为自定义；只有一位数的时候表示入库时间，2位数表示采集时间
	 */
	public static void selectTimeGroupType(WebElement upORcol,WebElement timeType,int type){
		Select s1=new Select(upORcol);
		Select s2=new Select(timeType);
		if(type==1){
			s1.selectByVisibleText("入库时间");
			s2.selectByVisibleText("本日");
		}else if (type==2) {
			s1.selectByVisibleText("入库时间");
			s2.selectByVisibleText("本周");
		}else if (type==3) {
			s1.selectByVisibleText("入库时间");
			s2.selectByVisibleText("本月");
		}else if (type==4) {
			s1.selectByVisibleText("入库时间");
			s2.selectByVisibleText("本年");
		}else if (type==5) {
			s1.selectByVisibleText("入库时间");
			s2.selectByVisibleText("去年");
		}else if (type==6) {
			s1.selectByVisibleText("入库时间");
			s2.selectByVisibleText("全部");
		}else if (type==7) {
			s1.selectByVisibleText("入库时间");
			s2.selectByVisibleText("自定义时间");
		}else if (type==11) {
			s1.selectByVisibleText("采集时间");
			s2.selectByVisibleText("本日");
		}else if (type==12) {
			s1.selectByVisibleText("采集时间");
			s2.selectByVisibleText("本周");
		}else if (type==13) {
			s1.selectByVisibleText("采集时间");
			s2.selectByVisibleText("本月");
		}else if (type==14) {
			s1.selectByVisibleText("采集时间");
			s2.selectByVisibleText("本年");
		}else if (type==15) {
			s1.selectByVisibleText("采集时间");
			s2.selectByVisibleText("去年");
		}else if (type==16) {
			s1.selectByVisibleText("采集时间");
			s2.selectByVisibleText("全部");
		}else if (type==17) {
			s1.selectByVisibleText("采集时间");
			s2.selectByVisibleText("自定义时间");
		}
	}
	
	/**
	 * 从数据库获取各种时间值
	 * @param ip 数据库ip
	 * @param type 1为现在，2为本日，3为本周，4为本月，5为本年，6为去年
	 * @return
	 */
	public static String getTimeValue(String ip,int type){
		SqlHelper sh=new SqlHelper(ip, Constants.ZW_DB_ACCOUNT, Constants.ZW_DB_PASSWD, false);
		ResultSet rs=null;
		String sql=null;
		String string=null;
		try {
			if(type==1){
				//现在
				sql="SELECT NOW()";
			}else if (type==2) {
				//本日
				sql="SELECT SUBDATE(NOW(),INTERVAL CURTIME() HOUR_SECOND)";
			}else if (type==3) {
				//本周
				sql="SELECT SUBDATE(DATE_SUB(NOW(),INTERVAL CURTIME() HOUR_SECOND),INTERVAL (DAYOFWEEK(NOW())-2) DAY)";
			}else if (type==4) {
				//本月
				sql="SELECT SUBDATE(DATE_SUB(NOW(),INTERVAL CURTIME() HOUR_SECOND),INTERVAL (DAYOFMONTH(NOW())-1) DAY)";
			}else if (type==5) {
				//本年
				sql="SELECT SUBDATE(DATE_SUB(NOW(),INTERVAL CURTIME() HOUR_SECOND),INTERVAL (DAYOFYEAR(NOW())-1) DAY)";
			}else if (type==6) {
				//去年
				sql="SELECT SUBDATE(SUBDATE(DATE_SUB(NOW(),INTERVAL CURTIME() HOUR_SECOND),INTERVAL (DAYOFYEAR(NOW())-1) DAY),INTERVAL 1 YEAR)";
			}
			rs=sh.sql_Query(sql);
			if(rs.next()){
				string=rs.getString(1);
				if(string.contains(".")){
					string=string.substring(0,string.length()-2);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			sh.close_Resoure();
		}
		return string;
	}
	
	/**
	 * 把字符串转换成标准时间格式
	 * @param timeString 传入的时间字符串
	 * @return
	 * @throws ParseException
	 */
	public static Date stringTOdate(String timeString) {
		Date d=null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			d=sdf.parse(timeString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
	
	/**
	 * 判断给定的日期是否在期望的日期之内，是返回true，不是返回false
	 * @param start 开始日期，可以为空
	 * @param end 结束日期，可以为空
	 * @param value 给定的日期
	 * @return
	 */
	public static boolean isTrue(Date start,Date end,Date value){
		boolean istrue=true;
		Calendar startcal=Calendar.getInstance();
		Calendar endcal=Calendar.getInstance();
		Calendar valcal=Calendar.getInstance();
		if (value!=null) {
			valcal.setTime(value);
		}
		if(start!=null){
			startcal.setTime(start);
			if (!(valcal.after(startcal)||valcal.equals(startcal))) {
				istrue=istrue&&false;
			}
		}
		if (end!=null) {
			endcal.setTime(end);
			if(!(valcal.before(endcal)||valcal.equals(endcal))){
				istrue=istrue&&false;
			}
		}
		
		return istrue;
	}
	
	/**
	 * 得到按入库时间来统计各个时间段在采集数据管理的数据量
	 * @param ip 测试ip地址
	 * @param type 1表示本日，2表示本周，3表示本月，4表示本年，5表示去年，6表示全部
	 * @return
	 */
	public static int getCollectNums(String ip,int type){
		int nums=0;
		SqlHelper sh=new SqlHelper(ip,Constants.ZW_DB_ACCOUNT, Constants.ZW_DB_PASSWD, false);
		ResultSet rs=null;
		String sql="";
		try {
			if (type==1) {
				//本日
				sql="SELECT COUNT(1) FROM gg_collect_info g WHERE g.DATA_TYPE=0 AND g.COLLECT_MODE IS NULL AND g.UPLOAD_TIME >= ? ";
				String paras[]={SmokeCommon.getTimeValue(ip, 2)};
				rs=sh.sql_Query(sql,paras);
			}else if (type==2) {
				//本周
				sql="SELECT COUNT(1) FROM gg_collect_info g WHERE g.DATA_TYPE=0 AND g.COLLECT_MODE IS NULL AND g.UPLOAD_TIME >= ? ";
				String paras[]={SmokeCommon.getTimeValue(ip, 3)};
				rs=sh.sql_Query(sql,paras);
			}else if (type==3) {
				//本月
				sql="SELECT COUNT(1) FROM gg_collect_info g WHERE g.DATA_TYPE=0 AND g.COLLECT_MODE IS NULL AND g.UPLOAD_TIME >= ? ";
				String paras[]={SmokeCommon.getTimeValue(ip, 4)};
				rs=sh.sql_Query(sql,paras);
			}else if (type==4) {
				//本年
				sql="SELECT COUNT(1) FROM gg_collect_info g WHERE g.DATA_TYPE=0 AND g.COLLECT_MODE IS NULL AND g.UPLOAD_TIME >= ? ";
				String paras[]={SmokeCommon.getTimeValue(ip, 5)};
				rs=sh.sql_Query(sql,paras);
			}else if (type==5) {
				//去年
				sql="SELECT COUNT(1) FROM gg_collect_info g WHERE g.DATA_TYPE=0 AND g.COLLECT_MODE IS NULL AND g.UPLOAD_TIME >= ? AND g.UPLOAD_TIME < ?";
				String paras[]={SmokeCommon.getTimeValue(ip, 6),SmokeCommon.getTimeValue(ip, 5)};
				rs=sh.sql_Query(sql,paras);
			}else if (type==6) {
				//全部
				sql="SELECT COUNT(1) FROM gg_collect_info g WHERE g.DATA_TYPE=0 AND g.COLLECT_MODE IS NULL";
				rs=sh.sql_Query(sql);
			}
			
			if(rs.next()){
				nums=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			sh.close_Resoure();
		}
		
		return nums;
	}
	
	/**
	 * 得到按采集时间来统计各个时间段在采集数据管理的数据量
	 * @param ip 测试ip地址
	 * @param type 1表示本日，2表示本周，3表示本月，4表示本年，5表示去年，6表示全部
	 * @return
	 */
	public static int getCollectNums_collect(String ip,int type){
		int nums=0;
		SqlHelper sh=new SqlHelper(ip,Constants.ZW_DB_ACCOUNT, Constants.ZW_DB_PASSWD, false);
		ResultSet rs=null;
		String sql="";
		try {
			if (type==1) {
				//本日
				sql="SELECT COUNT(1) FROM gg_collect_info g WHERE g.DATA_TYPE=0 AND g.COLLECT_MODE IS NULL AND g.COLLECT_TIME >= ? ";
				String paras[]={SmokeCommon.getTimeValue(ip, 2)};
				rs=sh.sql_Query(sql,paras);
			}else if (type==2) {
				//本周
				sql="SELECT COUNT(1) FROM gg_collect_info g WHERE g.DATA_TYPE=0 AND g.COLLECT_MODE IS NULL AND g.COLLECT_TIME >= ? ";
				String paras[]={SmokeCommon.getTimeValue(ip, 3)};
				rs=sh.sql_Query(sql,paras);
			}else if (type==3) {
				//本月
				sql="SELECT COUNT(1) FROM gg_collect_info g WHERE g.DATA_TYPE=0 AND g.COLLECT_MODE IS NULL AND g.COLLECT_TIME >= ? ";
				String paras[]={SmokeCommon.getTimeValue(ip, 4)};
				rs=sh.sql_Query(sql,paras);
			}else if (type==4) {
				//本年
				sql="SELECT COUNT(1) FROM gg_collect_info g WHERE g.DATA_TYPE=0 AND g.COLLECT_MODE IS NULL AND g.COLLECT_TIME >= ? ";
				String paras[]={SmokeCommon.getTimeValue(ip, 5)};
				rs=sh.sql_Query(sql,paras);
			}else if (type==5) {
				//去年
				sql="SELECT COUNT(1) FROM gg_collect_info g WHERE g.DATA_TYPE=0 AND g.COLLECT_MODE IS NULL AND g.COLLECT_TIME >= ? AND g.COLLECT_TIME < ?";
				String paras[]={SmokeCommon.getTimeValue(ip, 6),SmokeCommon.getTimeValue(ip, 5)};
				rs=sh.sql_Query(sql,paras);
			}else if (type==6) {
				//全部
				sql="SELECT COUNT(1) FROM gg_collect_info g WHERE g.DATA_TYPE=0 AND g.COLLECT_MODE IS NULL";
				rs=sh.sql_Query(sql);
			}
			
			if(rs.next()){
				nums=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			sh.close_Resoure();
		}
		
		return nums;
	}
	
	/**
	 * 返回按入库或采集时间统计时间段内的数据总数
	 * @param ip
	 * @param begin开始时间
	 * @param end 结束时间
	 * @param ColOrUp 采集时间or入库时间，true的时候是入库时间，false的时候是采集时间
	 * @return
	 */
	public static int getCollectNums(String ip,String begin,String end,boolean ColOrUp){
		int nums=0;
		SqlHelper sh=new SqlHelper(ip,Constants.ZW_DB_ACCOUNT, Constants.ZW_DB_PASSWD, false);
		ResultSet rs=null;
		String sql="";
		try {
			if (ColOrUp) {
				if (begin==null && end==null) {
					sql="SELECT COUNT(1) FROM gg_collect_info g WHERE g.DATA_TYPE=0 AND g.COLLECT_MODE IS NULL";
				} else if (begin==null &&end!=null) {
					sql="SELECT COUNT(1) FROM gg_collect_info g WHERE g.DATA_TYPE=0 AND g.COLLECT_MODE IS NULL AND g.UPLOAD_TIME < ?";
				}else if (begin!=null &&end==null) {
					sql="SELECT COUNT(1) FROM gg_collect_info g WHERE g.DATA_TYPE=0 AND g.COLLECT_MODE IS NULL AND g.UPLOAD_TIME >= ? ";
				}else {
					 sql="SELECT COUNT(1) FROM gg_collect_info g WHERE g.DATA_TYPE=0 AND g.COLLECT_MODE IS NULL AND g.UPLOAD_TIME >= ? AND g.UPLOAD_TIME < ?";
				}				
			}else {
				if (begin==null && end==null) {
					 sql="SELECT COUNT(1) FROM gg_collect_info g WHERE g.DATA_TYPE=0 AND g.COLLECT_MODE IS NULL";
				} else if (begin==null &&end!=null) {
					 sql="SELECT COUNT(1) FROM gg_collect_info g WHERE g.DATA_TYPE=0 AND g.COLLECT_MODE IS NULL AND g.COLLECT_TIME < ?";
				}else if (begin!=null &&end==null) {
					 sql="SELECT COUNT(1) FROM gg_collect_info g WHERE g.DATA_TYPE=0 AND g.COLLECT_MODE IS NULL AND g.COLLECT_TIME >= ?";
				}else {
					 sql="SELECT COUNT(1) FROM gg_collect_info g WHERE g.DATA_TYPE=0 AND g.COLLECT_MODE IS NULL AND g.COLLECT_TIME >= ? AND g.COLLECT_TIME < ?";
				}
			}
			String paras[]={begin,end};
			rs=sh.sql_Query(sql,paras);
			if(rs.next()){
				nums=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			sh.close_Resoure();
		}
		
		return nums;
	}
	
	/**
	 * 得到当前日期相差days天数
	 * @param ip 测试地址
	 * @param days 为+表示当前日期往后退days天，-表示当前天往前days天
	 * @return
	 * @param isFormat 是否是标准格式,标准格式为年月日时分秒
	 */
	public static String getYouTime(String ip,int days,boolean isFormat){
		String nums="";
		SqlHelper sh=new SqlHelper(ip,Constants.ZW_DB_ACCOUNT, Constants.ZW_DB_PASSWD, false);
		ResultSet rs=null;
		String sql="";
		try {
			if (isFormat) {
				sql="select SUBDATE(NOW(),INTERVAL "+days+" DAY)";
			}else {
				sql="select SUBDATE(CURDATE(),INTERVAL "+days+" DAY)";
			}
			rs=sh.sql_Query(sql);
			if (rs.next()) {
				nums=rs.getString(1);
			}
			if(nums.contains(".")){
				nums=nums.substring(0,nums.length()-2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			sh.close_Resoure();
		}		
		return nums;
	}
	
	/**
	 * 把自定义时间中的只读属性移除
	 * @param wd
	 * @param kind 1表示btime自定义开始，2表示etime结尾，3表示dataimport开始，4表示dataimport结尾
	 */
	public static void removeReadonly(WebDriver wd,int kind){
		if (kind==1) {
			((JavascriptExecutor)wd).executeScript("var setDate=document.getElementById(\"btime\");setDate.removeAttribute('readonly');") ;
		}else if (kind==2) {
			((JavascriptExecutor)wd).executeScript("var setDate=document.getElementById(\"etime\");setDate.removeAttribute('readonly');") ;			
		}else if (kind==3) {
			((JavascriptExecutor)wd).executeScript("var setDate=document.getElementById(\"dataImportStartDate\");setDate.removeAttribute('readonly');") ;
		}else if (kind==4) {
			((JavascriptExecutor)wd).executeScript("var setDate=document.getElementById(\"dataImportEndDate\");setDate.removeAttribute('readonly');") ;
		}
	}
	
	/**
	 * 把time为年月日格式的添加时分秒格式
	 * @param time
	 * @param isBegin
	 * @return
	 */
	public static String changeTimeFormat(String time,boolean isBegin){
		time=time.trim();
		if (isBegin) {
			time=time+" 00:00:00";
		}else {
			time=time+" 23:59:59";
		}		
		return time;
	}
	
	/**
	 * 得到一个集合中值不为空的值，并返回
	 * @param ls 要检查的集合
	 * @return
	 */
	public static String getExistValue(List<String> ls){
		String string=null;
		for(String str:ls){
			if (!str.trim().equals("")) {
				string=str;
				break;
			}
		}				
		return string;
	}
	
	/**
	 * 随机取得给定字符串的一部分
	 * @param string
	 * @return
	 */
	public static String getPartOfString(String string){
		String returnvalue="";
		int length=string.length();
		int begin=(int) (Math.random()*(length+1));
		int end=(int) (Math.random()*(length+1));
		int c=0;
		if(begin==end){
			if (begin==0) {
				while((c=(int) (Math.random()*(length+1)))!=0){
					returnvalue=string.substring(0,c);
					break;
				}
			}else if (begin==length) {
				while((c=(int) (Math.random()*(length+1)))<length){
					returnvalue=string.substring(c,length);
					break;
				}
			}else{
				while((c=(int) (Math.random()*(length+1)))!=begin){
					if (c>begin) {
						returnvalue=string.substring(length,c);
					}else {
						returnvalue=string.substring(c,length);
					}					
					break;
				}
			}
		}else if (begin>end) {
			returnvalue=string.substring(end,begin);
		}else {
			returnvalue=string.substring(begin,end);
		}
		
		
		return returnvalue;
	}
	
	/**
	 * 取得字符串中()的内容
	 * @param string
	 * @return
	 */
	public static List<String> getBracketValues(String string){
		List<String> ls=new ArrayList<String>();
		String string2[]=string.split("\\(");
		for (int i = 0; i < string2.length; i++) {
			if(string2[i].endsWith(")")){
				ls.add(string2[i].substring(0, string2[i].length()-1));
			}
		}
		
		return ls;
	}
	
	/**
	 * 得到系统的一个非顶级的单位或者案件性质
	 * @param ip
	 * @param type 1为案件性质，2为单位
	 * @return
	 */
	public static String get_NoTop_PcsOrCaseType(String ip,int type){
		String returnValue=null;
		SqlHelper shtem=new SqlHelper(ip, Constants.ZW_DB_ACCOUNT, Constants.ZW_DB_PASSWD, false);
		ResultSet rs=null;
		String sql="";
		try {
			if (type==1) {
				sql="SELECT t.DETAIL FROM sys_case_type t WHERE LENGTH(t.PATH)-LENGTH(REPLACE(t.PATH,'|',''))>1";
			}else if (type==2) {
				sql="SELECT d.ALIAS_NAME FROM sys_department d WHERE LENGTH(d.PATH)-LENGTH(REPLACE(d.PATH,'|',''))>1";
			}
			rs=shtem.sql_Query(sql);
			while(rs.next()){
				returnValue=rs.getString(1);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			shtem.close_Resoure();
		}
		
		return returnValue;
	}
	
}
