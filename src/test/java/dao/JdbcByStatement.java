package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.maven.common.util.JdbcUtil;

public class JdbcByStatement {
	Connection conn=null;
	Statement st=null;
	
	@Test
	public void insert(){
		try {
			//获取数据库连接
			conn=JdbcUtil.getConnection();
			//创建发送结果集
			st=conn.createStatement();
			
			//插入数据
			String insql="insert into users(name,age)values('打打',21)";
			
			int num = st.executeUpdate(insql);
			if(num>0){
				System.out.println("插入成功!");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void delete(){
		
		try {
			//获取数据库连接
			conn=JdbcUtil.getConnection();
			//创建发送结果集
			st=conn.createStatement();
			
			//删除数据
			String sql="delete from users where id=8";
			int num = st.executeUpdate(sql);
			if(num>0){
				System.out.println("删除成功!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void update(){
		
		try {
			//获取数据库连接
			conn=JdbcUtil.getConnection();
			//创建结果集
			st=conn.createStatement();
			
			String sql="update users set name='小小' where id=9";
			int num = st.executeUpdate(sql);
			
			if(num>0){
				System.out.println("更新成功!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
