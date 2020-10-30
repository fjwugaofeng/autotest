/**
 * 生成一个日志类作为公共类（必须在根目录有一个xml文件进行配置）
 */
package com.tools;

import org.apache.log4j.Logger;

public class Log {
	//定义一个日志对象
	public static Logger log=Logger.getLogger(Logger.class.getName());
	
	/**
	 * 定义一个开始测试的日志方法
	 * @param testCaseName
	 */
	public static void startTestCase(String testCaseName){
		log.info("----------------------------------------------------------------------------");
		log.info("**********         "+testCaseName+"          **********");
	}

	/**
	 * 定义一个结束测试的日志方法
	 * @param testCaseName
	 */
	public static void endTestCase(String testCaseName){
		log.info("**********         "+testCaseName+"          **********");
		log.info("----------------------------------------------------------------------------");
	}
	
	/**
	 * 生成一个info信息的方法
	 * @param message 日志内容
	 */
	public static void info(String message){
		log.info(message);
	}
	
	/**
	 * 定义打印warm级别的日志方法
	 * @param message 日志内容
	 */
	public static void warm(String message){
		log.info(message);
	}
	
	/**
	 * 定义一个打印error的日志方法
	 * @param message 日志内容
	 */
	public static void error(String message){
		log.info(message);
	}
	
	/**
	 * 定义一个打印fatal日志方法
	 * @param message 日志内容
	 */
	public static void fatal(String message){
		log.info(message);
	}
	
	/**
	 * 定义一个打印debug日志的方法
	 * @param message 日志内容
	 */
	public static void debug(String message){
		log.info(message);
	}
}
