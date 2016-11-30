package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

import org.junit.Test;

import com.maven.common.util.JdbcUtil;




/**
 * @author JiDanGj
 * @Description	JDBC事务 Trasaction
 * 模拟A账户与B账户转账
 */
public class TestTrasaction {
	
	
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	@Test
	public void trasactionDemo(){
		try {
			//连接数据库
			conn=JdbcUtil.getConnection();
			
			//开启事务
			conn.setAutoCommit(false);
			//创建sql语句
			String sql ="update account set money=money-100 where name='A'";
			//数据库的结果集
			ps=conn.prepareStatement(sql);
			//执行sql语句
			ps.executeUpdate();
			String sql2="update account set money=money+100 where name='B'";
			//数据库的结果集
			ps=conn.prepareStatement(sql2);
			ps.executeUpdate();
			
			
			//提交事务
			conn.commit();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(conn, ps, rs);
		}
	}
	/**
	 * 测试一部分sql执行失败后让数据库自动回滚事务
	 * @author JiDanGb
	 */
	@Test
	public void testTrasactionRoBack(){
		try {
			//获取数据库的连接
			conn=JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			
			String sql="update account set money=money+100 where name='A'";
			//数据库的结果集
			ps=conn.prepareStatement(sql);
			//执行sql语句
			ps.executeUpdate();
			
			//模拟执行中会出现异常情况  导致程序无法继续执行
			int m=1/0;
			
			//创建sql2语句
			String sql2="update account set money=money-100 where name='B'";
			ps=conn.prepareStatement(sql2);
			//执行sql语句
			ps.executeUpdate();
			
			//提交事务
			conn.commit();
			System.out.println("更新数据库成功!");
		} catch (SQLException e) {
			
		}finally {
			JdbcUtil.release(conn, ps, rs);
		}
	}
	@Test
	public void testTrasactionRoBack2(){
		
		try {
			//获取数据库连接
			conn=JdbcUtil.getConnection();
			//开启事务
			conn.setAutoCommit(false);
			//创建sql语句
			String sql="update account set money=money-100 where name='A'";
			//数据库的结果集
			ps=conn.prepareStatement(sql);
			//执行
			ps.executeUpdate();
			
			
			//程序中出现异常
			int num=1/0;
			
			//创建sql语句
			String sql2="update account set money=money+100 where name='B'";
			//数据库的结果集
			ps=conn.prepareStatement(sql2);
			ps.executeUpdate();
			conn.commit();
			System.out.println("两条语句操作成功!!");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}


}
