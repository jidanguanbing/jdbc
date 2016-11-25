package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		}
		
	}
}
