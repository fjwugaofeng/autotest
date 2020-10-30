/**
 * 功能：实现base64加密和解密
 */
package com.tools;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class Base64Code {
	
	/**
	 * 加密函数
	 * @param willCodeString
	 * @return
	 */
	public static String encode(String willCodeString){
		byte b[]=null;
		String string=null;
		try {
			b=willCodeString.getBytes("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (b!=null) {
			byte c[]=Base64.encodeBase64(b);
			string=new String(c);
		}
		return string;
	}
	
	/**
	 * base64解密
	 * @param encodeString
	 * @return
	 */
	public static String uncode(String encodeString){
		byte b[]=null;
		String string=null;
		try {
			b=encodeString.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		if (b!=null) {
			string=new String(Base64.decodeBase64(b));
		}
		return string;
	}
	
    /**
     * 获取md5处理后的base64编码ID
     * @param str 待md5处理的字符串
     * @return
     * @since yejch, 2017年3月3日, 新增方法
     */
    public static String getBase64Guid(String str) {
        return Base64.encodeBase64URLSafeString(DigestUtils.md5(str));
    }
    
    /**
     * 获取md5处理后的base64编码ID
     * @return
     * @since yejch, 2017年3月3日, 新增方法
     */
//    public static String getBase64Guid() {
//        return Base64.encodeBase64URLSafeString(DigestUtils.md5(getFormatGuid()));
//    }
    
    /**
     * @Description: 获取32位GUID，中间没有分隔符'-'
     * @return
     * 
     * @History 1. 2014-7-14 linjb 创建方法
     */
//    public static String getFormatGuid() {
//        return UUID.randomUUID().toString().replaceAll("-", "");
//    }
}
