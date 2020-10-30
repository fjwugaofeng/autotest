/**
 * 这是一个常量类，用例定义可能用的倒的常量
 * 假如常量有变化直接在这边修改就可以了
 */
package com.tools;

public class Constants {
	//蛛网数据库账号
	public static String ZW_DB_ACCOUNT="cobweb";
	//蛛网数据库密码
	public static String ZW_DB_PASSWD="spider@1314";
	//mysql驱动
	public static String ZW_DB_DBName="cobweb";
	//mysql驱动	
	public static String MySQL_DB_Driver="com.mysql.jdbc.Driver";
	//本地数据库账号
	public static String Local_DB_ACCOUNT="root";
	//本地数据库的密码
	public static String Local_DB_PASSWD="spider@test03";
	//本地数据库的密码
	public static String Local_DB_IP="172.16.2.191";
	//本地数据库的密码
	public static String Local_DB_DBName="autotest";
	//测试通过表示
	public static String TestResult_PASS="PASS";
	//测试失败表示
	public static String TestResult_FAIL="FAIL";
	//测试其他表示
	public static String TestResult_NA="NA";
	//测试用例开始的行数
	public static int Case_Start_Row=2;
	//测试用例所在的列
	public static int Case_CaseNO=1;
	//测试用例写入的列
	public static int Case_Resutl_Column_Num=8;
	//测试用例判断是否执行的列
	public static int Case_Result_Column_IsRun=9;
	//测试用例要执行的状态表示
	public static String Exe_Case="是";
	//测试用例不要执行的状态表示
	public static String No_Exe_Case="否";
	//登录模块在准入用例所在的sheet
	public  static int Login_Sheet_Num=0;
	//用例所在的目录
	public static String case_Path="testcase/case.xls";
	//时间类型的类型：采集时间还是入库时间
	public static String Time_Type="入库时间";
	//全部
	public static String all_Time="全部";
	//定义采集数据管理所在的sheet
	public static int DataManage_Sheet_Num=1;
	//自定义开始开始是当前时间减去天数
	public static int Custom_TimeB_Days=90;
	//自定义开始结束是当前时间减去天数
	public static int Custom_TimeE_Days=3;
	//手机号码常量
	public static String Mobile_Phone_Name="手机号码";
	//证件号码常量
	public static String ID_Card_Name="证件号码";
	//IMSI/IMEI常量
	public static String IMSI_OR_IMEI="物品特征码";
	//案件信息常量
	public static String Case_Information="案件信息";
	//警号常量
	public static String Police_ID_NumberString="警号";
	//采集员常量
	public static String Police_Name="采集员";
	//来源常量
	public static String Source_Data="来源";
	//采集编号常量
	public static String Collect_NO="采集编号";
	//案件性质常量
	public static String Case_Type_Value="案件性质";
	//采集单位常量
	public static String Collect_Department_Value="采集单位";
	//姓名常量
	public static String Collected_People_Name="姓名";
	//入库时间常量
	public static String Upload_Time="入库时间";
	//上传采集数据管理的exe文件的路径
	public static String Upload_Path="D:/testData/";
	//任务状态常量
	public static String Opreate_Process_Status="任务状态";
	//导出excel格式
	public static String Export_Excel="Excel格式";
	//导出CSV格式
	public static String Export_CSV="CSV格式";
	//下载文件的路径
	public static String Download_Path="C:/Users/wugf/Downloads";
	//第一个上传文件嫌疑人名字
	public static String Upload_File1_Name="autotest1";
	//第二个上传文件嫌疑人名字
	public static String Upload_File2_Name="autotest2";
	//第三个上传文件嫌疑人名字
	public static String Upload_File3_Name="autotest3";
	//第四个上传文件嫌疑人名字
	public static String Upload_File4_Name="autotest4";
	//第一个上传文件的exe
	public static String Upload_Exe1_Name="autotest1.exe";
	//第二个上传文件的exe
	public static String Upload_Exe2_Name="autotest2.exe";
	//第三个上传文件的exe
	public static String Upload_Exe3_Name="autotest3.exe";
	//第四个上传文件的exe
	public static String Upload_Exe4_Name="autotest4.exe";
	//修改采集员的数据后缀
	public static String Modify_tail="_modify";
	//居民身份证常量
	public static String IDCard_Constant="居民身份证";
	//证件号码值
	public static String IDCard_Value="444346197501089959";
	//证件号码值
	public static String Handset_Value="13599920063";
	//编辑案件性质常量为
	public static String CaseType_Set_Value="走私毒品案";
	//编辑案件性质常量为
	public static String CaseType_Special_Value="部督专案";
	//一键搜常量
	public static String OneSearch_Value="一键搜";
	//一键搜常量
	public static String FullSearch_Value="全库搜";
	//一键搜常量
	public static String AddToAnaPool_Value="加入分析池";
	//一键搜常量
	public static String Copy_Value="复制";
	
	
	//数据比对中取得html数据的保存目录
	public static String Html_DataSave_Path="W:/SeleniumHtml";
	//数据比对中取得html数据的log目录
	public static String Html_DataLog_Path="W:/Seleniumlog/";
	//数据比对中XML存放目录
	public static String XML_Source_Path="W:/SeleniumXml";
	//数据比对中取得html数据的log目录
	public static String XMLtoExcel_Source_Path="W:/SeleniumExcel";
	//数据比对中xml与html合并显示结果
	public static String XMLHtml_TestResult_Path="W:/SeleniumOutput";
	//是否测试基本信息
	public static boolean IsTest_Datadt_Baseinfo=true;
	//是否测试关系人
	public static boolean IsTest_Datadt_Realation=true;
	//是否测试通联纪录
	public static boolean IsTest_Datadt_MSG=true;
	//是否测试群成员
	public static boolean IsTest_Datadt_GroupMember=true;
	//是否测试群聊天
	public static boolean IsTest_Datadt_GroupMSG=true;
	//是否测试活动轨迹
	public static boolean IsTest_Datadt_Activity=true;
	//是否测试动态信息
	public static boolean IsTest_Datadt_Danaty=true;
	//是否测试搜索记录
	public static boolean IsTest_Datadt_Search=true;
	//是否测试搜索记录
	public static boolean IsTest_Datadt_FileInfo=true;	
	//是否测试搜索记录
	public static boolean IsTest_Datadt_SysInfo=true;
	//是否测试搜索记录
	public static boolean IsTest_Datadt_Other=true;
}
