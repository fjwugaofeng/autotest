/**
 * 这是连接数据库的类
 */
package com.tools;
import java.sql.*;

public class SqlHelper {
	ResultSet rs=null;
	Connection ct=null;
	PreparedStatement ps=null;
	String sqldriver="com.microsoft.jdbc.sqlserver.SQLServerDriver";
	String sqlurl="jdbc:microsoft:sqlserver://127.0.0.1:1433;databaseName=wuy";
	String sql_Account="sa";
	String sql_passwd="123456";
	
	String sqldriver1="com.mysql.jdbc.Driver";
	String sqlurl1="jdbc:mysql://"+Constants.Local_DB_IP+":3306/"+Constants.Local_DB_DBName;
	String sql_Account1=Constants.Local_DB_ACCOUNT;
	String sql_passwd1=Constants.Local_DB_PASSWD;
	
	String driver;
	String url;
	String account;
	String passwd;
	
	/**
	 * 连接到数据库的对象
	 * @param url 连接数据库的连接字符串，例如：jdbc:mysql:127.0.0.1:3306/cobweb
	 * @param account 数据库的用户名
	 * @param passwd 数据库的密码
	 */
	public SqlHelper(String url,String account,String passwd){
//		this.url=url;
//		this.account=account;
//		this.passwd=passwd;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			ct=DriverManager.getConnection(url,account,passwd);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 构造连接mysql数据库的连接方式
	 * @param ip 要连接mysql的ip地址
	 * @param account 数据库的用户名
	 * @param passwd 数据库的密码
	 * @param isLocal 是否是本地（localhost）数据库
	 */
	public  SqlHelper(String ip,String account,String passwd, boolean isLocal){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if(isLocal){
				ct=DriverManager.getConnection("jdbc:mysql://"+ip+":3306/"+Constants.Local_DB_DBName,account,passwd);
			}else {
				ct=DriverManager.getConnection("jdbc:mysql://"+ip+":3306/"+Constants.ZW_DB_DBName,account,passwd);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 连接本地sqlserver数据库
	 */
	public SqlHelper()
	{
		try {
			Class.forName(sqldriver);
			ct=DriverManager.getConnection(this.sqlurl,this.sql_Account,this.sql_passwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 连接到数据库
	 * @param dbType sqlserver为本地sqlserver数据库，mysql为本地mysql数据库
	 */
	public SqlHelper(String dbType)
	{
		if (dbType.equals("sqlserver")) {
			try {
				Class.forName(sqldriver);
				ct = DriverManager.getConnection(sqlurl, sql_Account,sql_passwd);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(dbType.equals("mysql")){
			try {
				Class.forName(Constants.MySQL_DB_Driver);
				ct = DriverManager.getConnection(sqlurl1, Constants.Local_DB_ACCOUNT,
						Constants.Local_DB_PASSWD);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 查询操作，返回一个查询结果集
	 * @param sql 查询的sql语句
	 * @param paras 查询的sql参数
	 * @return 返回一个查询的结果集
	 */
	public ResultSet sql_Query(String sql,String []paras)
	{
		try {
			ps=ct.prepareStatement(sql);
			for(int i=0;i<paras.length;i++)
			{
				ps.setString(i+1, paras[i]);
			}
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return rs;
	}
	
	public ResultSet sql_Query(String sql)
	{
		try {
			ps=ct.prepareStatement(sql);
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		return rs;
	}
	
	/**
	 * sql进行（cud）增删改操作
	 * @param sql查询的sql语句
	 * @param paras sql查询的参数
	 * @return 返回是否查询到结果，返回真查询数据，返回假更新操作失败
	 */
	public boolean sql_Update(String sql,String []paras)
	{
		boolean b=true;
		int num=0;
		try {
			ps=ct.prepareStatement(sql);
			for (int i = 0; i < paras.length; i++) {
				ps.setString(i+1, paras[i]);
			}
			num=ps.executeUpdate();
			if(num!=1) b=false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			b=false;
			e.printStackTrace();
		}finally{
//			this.close_Resoure();
		}
		return b;
	}
	
	//关闭打开的资源
	public void close_Resoure()
	{
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(ct!=null) ct.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
