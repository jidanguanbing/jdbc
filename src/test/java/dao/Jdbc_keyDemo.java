package dao;

import com.maven.common.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc_keyDemo {
	Connection conn=null;
	PreparedStatement ps=null;
	
	ResultSet rs=null;
	
	@Test
	public void demo(){
		
		try {
			//获取数据库的链接
			conn=JdbcUtil.getConnection();
			
			//执行的sql语句
			String sql="insert into test1(name) value(?)";
			
			//数据库的结果集
			ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, "小张");
			
			//执行sql语句
				ps.executeUpdate();
				//获取数据库自动获取的主键
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()){
					System.out.println("主键"+ rs.getInt(1));
				}
				
		
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(conn, ps, rs);
		}
	}	

}
