package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.maven.common.util.JdbcUtil;

/**
 * @author JiDanGj
 * @Description sql进行批处理
 */
public class PreparedStatementByBatch {
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	@Test
	public void add(){
	
		try {
			//获取数据库的连接
			conn=JdbcUtil.getConnection();
			String sql="insert into users(id,name,age) values(?,?,?)";
			//数据库的结果集
			ps=conn.prepareStatement(sql);
			//执行sql语句
			for(int i=10;i<100 ;i++){
				ps.setInt(1, i);
				ps.setString(2, "cc"+i);
				ps.setInt(3, i+1);
				ps.addBatch();
				if(i%2==0){
					ps.executeBatch();
					ps.clearBatch();
				}
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
			String sql="delete from users where id>=10";
			//数据结果集
			ps=conn.prepareStatement(sql);
			
			int num = ps.executeUpdate();
			if(num>0){
				System.out.println("删除成功");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
