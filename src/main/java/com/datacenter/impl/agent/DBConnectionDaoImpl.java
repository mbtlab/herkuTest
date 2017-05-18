package com.datacenter.impl.agent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.datacenter.dao.agent.DBConnectionDao;

/**
 * 数据库连接实现类
 * Date: 2017-04-24
 * @author Leo
 *
 */
public class DBConnectionDaoImpl implements DBConnectionDao {
	private DataSource ds;
	
	public DataSource getDs() {
		return ds;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public synchronized Connection getConnection() {
		if(ds != null){
			try{
				return ds.getConnection();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public void close(ResultSet rs, Statement stmt, Connection conn) throws SQLException {
		close(rs);
		close(stmt);
		close(conn);
		
	}
	//关闭结果集
	private void close(ResultSet rs){
		if(rs != null){
			try{
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	//关闭会话
	private void close(Statement stmt){
		if(stmt != null){
			try{
				stmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	//释放链接
	private void close(Connection conn){
		if(conn != null){
			try{
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
