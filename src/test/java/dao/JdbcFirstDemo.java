package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcFirstDemo {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//连接数据库的url;
		String url="jdbc:mysql://localhost:3306/mybatis";
		//数据库使用的用户名
		String username="root";
		//数据库使用的密码
		String password="pass10";
		//1.加载驱动
		Class.forName("com.mysql.jdbc.Driver");//使用这种方式来加载驱动 防止重复
		Connection connection = DriverManager.getConnection(url, username, password);
		//创建往数据库发送的结果集
		Statement st = connection.createStatement();
		String sql="select id,name,age from users";
		//CURD
		String insql="insert into users(name,age)values('小小',21)";
		//发送sql
		int update = st.executeUpdate(insql);
		if(update>0){
			System.out.println("修改成功!!!!");
		}
		ResultSet executeQuery = st.executeQuery(sql);
		
		//取出结果集
		while(executeQuery.next()){
			System.out.println("id"+"="+executeQuery.getObject("id"));
			System.out.println("name"+"="+executeQuery.getObject("name"));
			System.out.println("age"+"="+executeQuery.getObject("age"));
			System.out.println("hello JDBC");
		}
		
	
	
	}
}
