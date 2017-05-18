package com.datacenter.dao.agent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库连接接口
 * Date: 2017-04-24
 * @author Leo
 *
 */
public interface DBConnectionDao {
	public Connection getConnection();//获取数据库链接
	public void close(ResultSet rs,Statement stmt,Connection conn)  throws SQLException ;//关闭会话，释放数据库链接
}
