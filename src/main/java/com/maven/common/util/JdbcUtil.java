package com.maven.common.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;



/**
 * @author JIdangb
 * @Description 创建JDBC工具类
 */
public class JdbcUtil {
	private static String driver = "driver";
	private static String url = "url";
	private static String username = "username";
	private static String password = "password";
	
	
	
	static{
		//读取配置文件的数据库连接信息
		InputStream resourceAsStream = JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
		Properties prop=new Properties();
		
		
		try {
			prop.load(resourceAsStream);
			//获取数据库的驱动
			driver = prop.getProperty(driver);
			//获取数据库的url
			url=prop.getProperty(url);
			//获取数据库的用户名
			username=prop.getProperty(username);
			//获取数据库的密码
			password=prop.getProperty(password);
			
			//加载数据库驱动
			Class.forName(driver);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
		
	}
	/**
	 * 获取数据库连接对象
	 * @author JiDangb
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		        return DriverManager.getConnection(url, username,password);
	    }
	
	
	 /**
	  * 释放资源
	 * @author JiDangb
	 * @param conn 数据库连接回话
	 * @param ps 发送sql语句
	 * @param rs	结果集
	 */
	public static void release(Connection conn,Statement ps,ResultSet rs){
		if(rs !=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps !=null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn !=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	  }
}
	
	

	
	

