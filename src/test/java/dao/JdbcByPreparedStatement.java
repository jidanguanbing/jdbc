package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.maven.common.util.JdbcUtil;

/**
 * @author JiDanGj
 * @Description 使用preparestement防止sql注入
 */
public class JdbcByPreparedStatement {
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	
	/**插入数据
	 * @author JiDanGb
	 */
	@Test
	public void insert(){
		//连接数据库
		try {
			conn=JdbcUtil.getConnection();
			//sql语句
			String sql="insert into users(id,name,age)values(?,?,?)";
			//数据库的结果集
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, 10);
			
			ps.setString(2, "可可");
			
			ps.setInt(3, 22);
			int num = ps.executeUpdate();
			if(num>0){
				System.out.println("增加成功!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(conn, ps, rs);
		}
		
	}
	
	/**删除语句
	 * @author JiDanGb
	 */
	@Test
	public void delete(){
		try {
			//连接数据库
			conn=JdbcUtil.getConnection();
			//创建sql语句
			String sql="delete  from users where id=?";
			//数据结果集
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, 10);
			
			int num = ps.executeUpdate();
			if(num>0){
				System.out.println("删除成功");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 修改语句
	 * @author JiDanGb
	 */
	@Test
	public void update(){
		
		try {
			//获取数据库连接
			conn=JdbcUtil.getConnection();
			
			//创建sql语句
			String sql="update  users set name=? where id=? ";
			//创建数据库结果集
			ps=conn.prepareStatement(sql);
			
			
			ps.setString(1, "煎饼果子");
			
			ps.setInt(2, 1);
			int num = ps.executeUpdate();
			if(num>0){
				System.out.println("修改成功");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @author JiDanGb
	 * 查询数据信息
	 */
	@Test
	public void find(){
		try {
			//获取数据库的链接
			conn=JdbcUtil.getConnection();
			
			//创建sql语句
			String sql="select * from users where id=?";
			
			
			//数据库的结果集
			ps=conn.prepareStatement(sql);
			
			
			ps.setInt(1, 1);
			ResultSet query = ps.executeQuery();
			while(query.next()){
				System.out.println("查询成功!"+query.getObject("name"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
