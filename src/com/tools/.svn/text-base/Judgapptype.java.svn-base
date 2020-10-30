/**
 * 这是一个判断app类型的类
 */
package com.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Judgapptype {
	
	/**
	 * 把通用APP转换成具体的类型
	 * @param filename  xml中的app类型
	 * @param fileName xml中的app转换成html的文件类型
	 * @param allAtt 属性名
	 * @param ftf 
	 * @return
	 */
	public String appType(String filename,String fileName,String allAtt,Writestring ftf){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String returnValue="";
		if(fileName.contains("浏览器")){
			//替换通用
			if((allAtt.equals(""))||(allAtt.toUpperCase().equals("DEFAULT"))){
				returnValue=fileName.replace("通用", "默认");
			}else if(allAtt.toUpperCase().equals("UC")){
				returnValue=fileName.replace("通用", "UC");
			}else if(allAtt.toUpperCase().equals("QQ")){
				returnValue=fileName.replace("通用", "QQ");
			}else if(allAtt.toUpperCase().equals("OPERA")){
				returnValue=fileName.replace("通用", "欧朋");
			}else if(allAtt.toUpperCase().equals("BAIDU")){
				returnValue=fileName.replace("通用", "百度");
			}else if(allAtt.toUpperCase().equals("DOLPHIN")){
				returnValue=fileName.replace("通用", "海豚");
			}else if(allAtt.toUpperCase().equals("IE")){
				returnValue=fileName.replace("通用", "IE");
			}else{
				ftf.write(sdf.format(new Date())+"："+filename+"中对应HTML的文件(属性"+allAtt+")没有找到映射信息！\r\n");
			}
			
			if(returnValue.equals("")){
				ftf.write(sdf.format(new Date())+"："+filename+"中对应HTML的文件为"+returnValue+"！\r\n");
			}
		}else if(fileName.contains("地图")){
			//替换通用
			if((allAtt.equals("0"))||(allAtt.equals("00"))){
				returnValue=fileName.replace("通用", "GOOGLE");
			}else if((allAtt.equals("1"))||(allAtt.equals("01"))){
				returnValue=fileName.replace("通用", "百度");
			}else if((allAtt.equals("2"))||(allAtt.equals("02"))){
				returnValue=fileName.replace("通用", "高德");
			}else{
				ftf.write(sdf.format(new Date())+"："+filename+"中对应HTML的文件(属性"+allAtt+")没有找到映射信息！\r\n");
			}
			if(returnValue.equals("")){
				ftf.write(sdf.format(new Date())+"："+filename+"中对应HTML的文件为"+returnValue+"！\r\n");
			}
		}else if(fileName.contains("EC")){
			//替换通用EC
			if(allAtt.equals("TMALL")){
				returnValue=fileName.replace("通用EC", "天猫");
			}else if(allAtt.equals("JD")){
				returnValue=fileName.replace("通用EC", "京东");
			}else if(allAtt.equals("TAOBAO")){
				returnValue=fileName.replace("通用EC", "淘宝");
			}else{
				ftf.write(sdf.format(new Date())+"："+filename+"中对应HTML的文件(属性"+allAtt+")没有找到映射信息！\r\n");
			}
			if(returnValue.equals("")){
				ftf.write(sdf.format(new Date())+"："+filename+"中对应HTML的文件为"+returnValue+"！\r\n");
			}
		}else if(fileName.contains("微博")||fileName.contains("博文")){
			//替换通用
			if(allAtt.equals("0")||allAtt.equals("00")){
				returnValue=fileName.replace("通用", "新浪");
			}else if(allAtt.equals("1")||allAtt.equals("01")){
				returnValue=fileName.replace("通用", "腾讯");
			}else{
				ftf.write(sdf.format(new Date())+"："+filename+"中对应HTML的文件(属性"+allAtt+")没有找到映射信息！\r\n");
			}
			if(returnValue.equals("")){
				ftf.write(sdf.format(new Date())+"："+filename+"中对应HTML的文件为"+returnValue+"！\r\n");
			}
		}else if(fileName.contains("邮箱")){
			//替换通用
			if(allAtt.equals("DEFAULT")){
				returnValue=fileName.replace("通用", "默认");
			}else if(allAtt.equals("QQ")){
				returnValue=fileName.replace("通用", "QQ");
			}else if(allAtt.equals("139")){
				returnValue=fileName.replace("通用", "139");
			}else if(allAtt.equals("126")){
				returnValue=fileName.replace("通用", "126");
			}else if(allAtt.equals("189")){
				returnValue=fileName.replace("通用", "189");
			}else if(allAtt.equals("163")){
				returnValue=fileName.replace("通用", "163");
			}else if(allAtt.equals("Foxmail")){
				returnValue=fileName.replace("通用", "Foxmail");
			}else if(allAtt.equals("绑定")){
				returnValue=fileName.replace("通用", "绑定");
			}else if(allAtt.equals("WO")){
				returnValue=fileName.replace("通用", "WO");
			}else if(allAtt.equals("Outlook")){
				returnValue=fileName.replace("通用", "Outlook");
			}else if(allAtt.equals("NetEase")){
				returnValue=fileName.replace("通用", "网易");
			}else{
				ftf.write(sdf.format(new Date())+"："+filename+"中对应HTML的文件(属性"+allAtt+")没有找到映射信息！\r\n");
			}
			if(returnValue.equals("")){
				ftf.write(sdf.format(new Date())+"："+filename+"中对应HTML的文件为"+returnValue+"！\r\n");
			}
		}else if(fileName.contains("IM")){
			//替换通用IM
			if(allAtt.toUpperCase().equals("VOXER")){
				returnValue=fileName.replace("通用IM", "Voxer");
			}else if(allAtt.toUpperCase().equals("VIBER")){
				returnValue=fileName.replace("通用IM", "Viber");
			}else if(allAtt.toUpperCase().equals("TALKBOX")){
				returnValue=fileName.replace("通用IM", "Talkbox");
			}else if(allAtt.toUpperCase().equals("SKYPE")){
				returnValue=fileName.replace("通用IM", "Skype");
			}else if(allAtt.toUpperCase().equals("LAIWANG")){
				returnValue=fileName.replace("通用IM", "来往");
			}else if(allAtt.toUpperCase().equals("MOMO")){
				returnValue=fileName.replace("通用IM", "陌陌");
			}else if(allAtt.toUpperCase().equals("WANGXIN")){
				returnValue=fileName.replace("通用IM", "旺信");
			}else if(allAtt.toUpperCase().equals("YIXIN")){
				returnValue=fileName.replace("通用IM", "易信");
			}else if(allAtt.toUpperCase().equals("VPHONE")){
				returnValue=fileName.replace("通用IM", "微话");
			}else if(allAtt.toUpperCase().equals("IAROUND")){
				returnValue=fileName.replace("通用IM", "iAround");
			}else if(allAtt.toUpperCase().equals("LINE")){
				returnValue=fileName.replace("通用IM", "LINE");
			}else if(allAtt.toUpperCase().equals("DIDITALK")){
				returnValue=fileName.replace("通用IM", "DidiTalk");
			}else if(allAtt.toUpperCase().equals("MSN")){
				returnValue=fileName.replace("通用IM", "MSN");
			}else if(allAtt.toUpperCase().equals("YY")){
				returnValue=fileName.replace("通用IM", "YY");
			}else if(allAtt.toUpperCase().equals("MITALK")){
				returnValue=fileName.replace("通用IM", "米聊");
			}else if(allAtt.toUpperCase().equals("FETION")){
				returnValue=fileName.replace("通用IM", "飞信");
			}else if(allAtt.toUpperCase().equals("FACEBOOK")){
				returnValue=fileName.replace("通用IM", "Facebook");
			}else if(allAtt.toUpperCase().equals("WHATSAPP")){
				returnValue=fileName.replace("通用IM", "WhatsApp");
			}else if(allAtt.toUpperCase().equals("RENREN")){
				returnValue=fileName.replace("通用IM", "人人网");
			}else if(allAtt.toUpperCase().equals("ZELLO")){
				returnValue=fileName.replace("通用IM", "ZELLO");
			}else if(allAtt.toUpperCase().equals("YOUXIN")){
				returnValue=fileName.replace("通用IM", "有信");
			}else if(allAtt.toUpperCase().equals("TELEGRAM")){
				returnValue=fileName.replace("通用IM", "TELEGRAM");
			}else if(allAtt.toUpperCase().equals("QQ")||allAtt.toUpperCase().equals("TENCENT")){
				returnValue=fileName.replace("通用IM", "QQ");
			}else if(allAtt.toUpperCase().equals("SINA")){
				returnValue=fileName.replace("通用IM", "新浪");
			}else{
				ftf.write(sdf.format(new Date())+"："+filename+"中对应HTML的文件(属性"+allAtt+")没有找到映射信息！\r\n");
			}
			if(returnValue.equals("")){
				ftf.write(sdf.format(new Date())+"："+filename+"中对应HTML的文件为"+returnValue+"！\r\n");
			}
		}else if(fileName.contains("打车")){
			//替换通用
			if(allAtt.toUpperCase().equals("DIDI")){
				returnValue=fileName.replace("通用", "滴滴");
			}else if(allAtt.toUpperCase().equals("KUAIDI")){
				returnValue=fileName.replace("通用", "快滴");
			}else{
				ftf.write(sdf.format(new Date())+"："+filename+"中对应HTML的文件(属性"+allAtt+")没有找到映射信息！\r\n");
			}
			if(returnValue.equals("")){
				ftf.write(sdf.format(new Date())+"："+filename+"中对应HTML的文件为"+returnValue+"！\r\n");
			}
		}else if(fileName.contains("旅游")){
			//替换通用旅游
			if(allAtt.toUpperCase().equals("QUNAR")){
				returnValue=fileName.replace("通用", "去哪儿");
			}else{
				ftf.write(sdf.format(new Date())+"："+filename+"中对应HTML的文件(属性"+allAtt+")没有找到映射信息！\r\n");
			}
			if(returnValue.equals("")){
				ftf.write(sdf.format(new Date())+"："+filename+"中对应HTML的文件为"+returnValue+"！\r\n");
			}
		}
		
		return returnValue;
	}
	
	public String appType(String fileName,String allAtt){
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String returnValue="";
		if(fileName.contains("浏览器")){
			//替换通用
			if((allAtt.equals(""))||(allAtt.toUpperCase().equals("DEFAULT"))){
				returnValue=fileName.replace("通用", "默认");
			}else if(allAtt.toUpperCase().contains("UC")){
				returnValue=fileName.replace("通用", "UC");
			}else if(allAtt.toUpperCase().contains("QQ")){
				returnValue=fileName.replace("通用", "QQ");
			}else if(allAtt.toUpperCase().contains("OPERA")){
				returnValue=fileName.replace("通用", "欧朋");
			}else if(allAtt.toUpperCase().contains("BAIDU")){
				returnValue=fileName.replace("通用", "百度");
			}else if(allAtt.toUpperCase().contains("DOLPHIN")){
				returnValue=fileName.replace("通用", "海豚");
			}else if(allAtt.toUpperCase().contains("IE")){
				returnValue=fileName.replace("通用", "IE");
			}else if(allAtt.contains("遨游")){
				returnValue=fileName.replace("通用", "遨游");
			}else if(allAtt.toUpperCase().contains("FIREFOX")){
				returnValue=fileName.replace("通用", "火狐");
			}else if(allAtt.toUpperCase().contains("CHROME")){
				returnValue=fileName.replace("通用", "谷歌");
			}else{
				returnValue=fileName.replace("通用", "默认");
//				System.out.println(allAtt+"没找到对应的浏览器类型，请先配置类型！");
//				returnValue=allAtt;
			}
			
//			if(returnValue.equals("")){
//				System.out.println("浏览器类型为空，请确认！");
//			}
		}else if(fileName.contains("地图")){
			//替换通用
			if((allAtt.equals("0"))||(allAtt.equals("00"))||(allAtt.equals("谷歌地图"))){
				returnValue=fileName.replace("通用", "谷歌");
			}else if((allAtt.equals("1"))||(allAtt.equals("01"))||(allAtt.equals("百度地图"))){
				returnValue=fileName.replace("通用", "百度");
			}else if((allAtt.equals("2"))||(allAtt.equals("02"))||(allAtt.equals("高德地图"))){
				returnValue=fileName.replace("通用", "高德");
			}else{
				System.out.println(allAtt+"没找到对应的地图类型，请先配置类型！");
				returnValue=allAtt;
			}
			if(returnValue.equals("")){
				System.out.println("地图类型为空，请确认！");
			}
		}else if(fileName.contains("EC")){
			//替换通用EC
			if(allAtt.equals("TMALL")){
				returnValue=fileName.replace("通用EC", "天猫");
			}else if(allAtt.equals("JD")){
				returnValue=fileName.replace("通用EC", "京东");
			}else if(allAtt.equals("TAOBAO")){
				returnValue=fileName.replace("通用EC", "淘宝");
			}else{
				System.out.println(allAtt+"没找到对应的EC类型，请先配置类型！");
				returnValue=allAtt;
			}
			if(returnValue.equals("")){
				System.out.println("EC类型为空，请确认！");
			}
		}else if(fileName.contains("微博")||fileName.contains("博文")){
			//替换通用
			if(allAtt.equals("0")||allAtt.equals("00")||allAtt.equals("SINA")||allAtt.equals("")){
				returnValue=fileName.replace("通用", "新浪");
			}else if(allAtt.equals("1")||allAtt.equals("01")||allAtt.equals("TENCENT")){
				returnValue=fileName.replace("通用", "腾讯");
			}else{
				System.out.println(allAtt+"没找到对应的微博类型，请先配置类型！");
				returnValue=allAtt;
			}
			if(returnValue.equals("")){
				System.out.println("微博类型为空，请确认！");
			}
		}else if(fileName.contains("邮箱")){
			//替换通用
			if(allAtt.equals("DEFAULT")){
				returnValue=fileName.replace("通用", "默认");
			}else if(allAtt.equals("QQ")){
				returnValue=fileName.replace("通用", "QQ");
			}else if(allAtt.equals("139")){
				returnValue=fileName.replace("通用", "139");
			}else if(allAtt.equals("126")){
				returnValue=fileName.replace("通用", "126");
			}else if(allAtt.equals("189")){
				returnValue=fileName.replace("通用", "189");
			}else if(allAtt.equals("163")){
				returnValue=fileName.replace("通用", "163");
			}else if(allAtt.equals("Foxmail")){
				returnValue=fileName.replace("通用", "Foxmail");
			}else if(allAtt.equals("绑定")){
				returnValue=fileName.replace("通用", "绑定");
			}else if(allAtt.equals("WO")){
				returnValue=fileName.replace("通用", "WO");
			}else if(allAtt.equals("Outlook")){
				returnValue=fileName.replace("通用", "Outlook");
			}else if(allAtt.equals("SINA")){
				returnValue=fileName.replace("通用", "新浪");
			}else if(allAtt.equals("NETEASE")){
				returnValue=fileName.replace("通用", "网易");
			}else{
				System.out.println(allAtt+"没找到对应的邮箱类型，请先配置类型！");
				returnValue=allAtt;
			}
			if(returnValue.equals("")){
				System.out.println("邮箱类型为空，请确认！");
			}
		}else if(fileName.contains("IM")){
			//替换通用IM
			if(allAtt.toUpperCase().equals("VOXER")){
				returnValue=fileName.replace("通用IM", "Voxer");
			}else if(allAtt.toUpperCase().equals("VIBER")){
				returnValue=fileName.replace("通用IM", "Viber");
			}else if(allAtt.toUpperCase().equals("TALKBOX")){
				returnValue=fileName.replace("通用IM", "Talkbox");
			}else if(allAtt.toUpperCase().equals("SKYPE")){
				returnValue=fileName.replace("通用IM", "Skype");
			}else if(allAtt.toUpperCase().equals("LAIWANG")){
				returnValue=fileName.replace("通用IM", "来往");
			}else if(allAtt.toUpperCase().equals("MOMO")){
				returnValue=fileName.replace("通用IM", "陌陌");
			}else if(allAtt.toUpperCase().equals("WANGXIN")){
				returnValue=fileName.replace("通用IM", "旺信");
			}else if(allAtt.toUpperCase().equals("YIXIN")){
				returnValue=fileName.replace("通用IM", "易信");
			}else if(allAtt.toUpperCase().equals("VPHONE")){
				returnValue=fileName.replace("通用IM", "微话");
			}else if(allAtt.toUpperCase().equals("IAROUND")){
				returnValue=fileName.replace("通用IM", "iAround");
			}else if(allAtt.toUpperCase().equals("LINE")){
				returnValue=fileName.replace("通用IM", "LINE");
			}else if(allAtt.toUpperCase().equals("DIDITALK")){
				returnValue=fileName.replace("通用IM", "DidiTalk");
			}else if(allAtt.toUpperCase().equals("MSN")){
				returnValue=fileName.replace("通用IM", "MSN");
			}else if(allAtt.toUpperCase().equals("YY")){
				returnValue=fileName.replace("通用IM", "YY");
			}else if(allAtt.toUpperCase().equals("MITALK")){
				returnValue=fileName.replace("通用IM", "米聊");
			}else if(allAtt.toUpperCase().equals("FETION")){
				returnValue=fileName.replace("通用IM", "飞信");
			}else if(allAtt.toUpperCase().equals("FACEBOOK")){
				returnValue=fileName.replace("通用IM", "Facebook");
			}else if(allAtt.toUpperCase().equals("WHATSAPP")){
				returnValue=fileName.replace("通用IM", "WhatsApp");
			}else if(allAtt.toUpperCase().equals("RENREN")){
				returnValue=fileName.replace("通用IM", "人人网");
			}else if(allAtt.toUpperCase().equals("ZELLO")){
				returnValue=fileName.replace("通用IM", "ZELLO");
			}else if(allAtt.toUpperCase().equals("YOUXIN")){
				returnValue=fileName.replace("通用IM", "有信");
			}else if(allAtt.toUpperCase().equals("TELEGRAM")){
				returnValue=fileName.replace("通用IM", "TELEGRAM");
			}else if(allAtt.toUpperCase().equals("QQ")){
//				if(fileName.equals("通用IM搜索记录")){
//					returnValue=fileName.replace("通用IM", "QQ浏览器");
//				}else{
					returnValue=fileName.replace("通用IM", "QQ");
//				}
			}else if(allAtt.toUpperCase().equals("TENCENT")){
				returnValue=fileName.replace("通用IM", "腾讯");
			}else if(allAtt.toUpperCase().equals("SINA")){
				returnValue=fileName.replace("通用IM", "新浪");
			}else if(allAtt.toUpperCase().equals("SINAUC")){
				returnValue=fileName.replace("通用IM", "新浪UC");
			}else if(allAtt.toUpperCase().equals("WEIXIN")){
				returnValue=fileName.replace("通用IM", "微信");
			}else{
				System.out.println(allAtt+"没找到对应的IM类型，请先配置类型！");
				returnValue=allAtt;
			}
			
			if(returnValue.equals("")){
				System.out.println("IM类型为空，请确认！");
			}
		}else if(fileName.contains("打车")){
			//替换通用
			if(allAtt.toUpperCase().equals("DIDI")){
				returnValue=fileName.replace("通用", "滴滴");
			}else if(allAtt.toUpperCase().equals("KUAIDI")){
				returnValue=fileName.replace("通用", "快的");
			}else{
				System.out.println(allAtt+"没找到对应的打车类型，请先配置类型！");
				returnValue=allAtt;
			}
			if(returnValue.equals("")){
				System.out.println(allAtt+"打车类型为空，请确认！");
			}
		}else if(fileName.contains("旅游")){
			//替换通用旅游
			if(allAtt.toUpperCase().equals("QUNAR")){
				returnValue=fileName.replace("通用旅游", "去哪儿");
			}else{
				System.out.println(allAtt+"没找到对应的旅游类型，请先配置类型！");
				returnValue=allAtt;
			}
			if(returnValue.equals("")){
				System.out.println("旅游类型为空，请确认！");
			}
		}
		
		return returnValue;
	}
	
	/**
	 * 通过数据库配置来查询得到app类型
	 * @param fileName
	 * @param allAtt
	 * @param sh
	 * @param rs
	 * @return
	 */
	public String appType(String fileName,String allAtt,SqlHelper sh,ResultSet rs){
		String ls="";
		if(fileName.equals("手机通联_短信_彩信")){
			if (allAtt.equals("1")) {
				ls="手机通联_彩信";
			}else{
				ls="手机通联_短信";
			}
		}else if (fileName.endsWith("聊天_群聊天")) {
			if(allAtt.equals("1")||allAtt.equals("2")||allAtt.equalsIgnoreCase("GROUP")){
				ls=fileName.replace("聊天_", "");
			}else{
				ls=fileName.replace("_群聊天", "");
			}			
		}else{
			String returnValue="";
			if(fileName.contains("浏览器")){
				returnValue="WEB";
			}else if(fileName.contains("地图")){
				returnValue="MAP";
			}else if(fileName.contains("EC")){
				returnValue="EC";
			}else if(fileName.contains("微博")||fileName.contains("博文")){
				returnValue="WEIBO";
			}else if(fileName.contains("邮箱")){
				returnValue="MAIL";
			}else if(fileName.contains("IM")){
				returnValue="IM";
			}else if(fileName.contains("打车")){
				returnValue="TAXI";
			}else if(fileName.contains("旅游")){
				returnValue="TRAVEL";
			}
			String sql="SELECT * FROM app_type a WHERE a.app_big_type=? AND (a.app_spilt=? OR a.app_type=?)";
			if (allAtt.toUpperCase().contains("BROWSER")) {
				if (allAtt.contains(" ")) {
					allAtt=allAtt.substring(0,allAtt.indexOf(" "));
				}
			}
			String paras[]={returnValue,allAtt,allAtt};
			rs=sh.sql_Query(sql, paras);
			try {
				//有在数据库配置，则取出相应的类型
				if(rs.next()){
					ls=rs.getString(1);
					if(fileName.contains("通用地图")){
						ls=fileName.replace("通用", ls);
					}else if(fileName.contains("通用浏览器")){
						ls=fileName.replace("通用", ls);
					}else if(fileName.contains("通用IM")){
						ls=fileName.replace("通用IM", ls);
					}else if(fileName.contains("通用EC")){
						ls=fileName.replace("通用EC", ls);
					}else if(fileName.contains("通用微博")||fileName.contains("通用博文")){
						ls=fileName.replace("通用",ls);
					}else if(fileName.contains("通用邮箱")){
						ls=fileName.replace("通用", ls);
					}else if(fileName.contains("通用打车")){
						ls=fileName.replace("通用", ls);
					}else if(fileName.contains("通用旅游")){
						ls=fileName.replace("通用旅游",ls);
					}				
				}else{
					//数据库没有配置，则按原样返回
					ls=allAtt;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//		if (ls.contains("微话")&&!ls.contains("聊天记录")) {
//			ls=ls.replace("聊天", "聊天记录");
//		}
		return ls;
	}
	
//	public String getDefalutApp(String excelName){
//		String nnn="";
//		if(excelName.contains("通用地图")){
//			nnn=excelName.replace("通用", "谷歌");
//		}else if(excelName.contains("通用浏览器")){
//			nnn=excelName.replace("通用", "默认");
//		}else if(excelName.contains("通用IM")){
//			nnn=excelName.replace("通用", "谷歌");
//		}else if(excelName.contains("通用EC")){
//			nnn=excelName.replace("通用", "淘宝");
//		}else if(excelName.contains("通用微博")||excelName.contains("通用博文")){
//			nnn=excelName.replace("通用", "新浪");
//		}else if(excelName.contains("通用邮箱")){
//			nnn=excelName.replace("通用", "默认");
//		}else if(excelName.contains("通用打车")){
//			nnn=excelName.replace("通用", "滴滴");
//		}else if(excelName.contains("通用旅游")){
//			nnn=excelName.replace("通用", "去哪儿");
//		}
//		return nnn;
//	}
	
	/**
	 * 得到通联的app类型
	 * @param fileName app的文件的名字，例如QQ聊天
	 * @return
	 */
	public String msgAppType(String fileName){
		String type="";
		if(fileName.startsWith("手机通联")){
			type=fileName.substring(0,fileName.indexOf("通"));
		}else if (fileName.contains("最后会话")) {
			type=fileName.replace("最后会话", "");
		}else if (fileName.contains("邮箱")) {
			type=fileName.substring(0, fileName.indexOf("记"));
		}else if(fileName.contains("私信")){
			type=fileName.substring(0, fileName.indexOf("私"));			
		}else if(fileName.endsWith("聊天")){
			type=fileName.replace("聊天", "");			
		} else {
			System.out.println(fileName+"这种app类型还无法判断，请先进行设置！");
		}
		
		return type;
	}
}
