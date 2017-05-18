package com.datacenter.impl.agent;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.datacenter.dao.agent.DBConnectionDao;
import com.datacenter.dao.agent.LogCommandDao;

/**
 * 操作日志表实现
 * Date: 2017-04-24
 * @author Leo
 *
 */
public class LogCommandDaoImpl implements LogCommandDao {
	protected static final Logger log = Logger.getLogger(LogCommandDaoImpl.class);
	private DBConnectionDao logConn;
//	private static final String createSQL = "CREATE TABLE if not exists  #tableName# ( `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '日志编号',"
//			+ "`flag` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '成功标志',"
//			+ "`api` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '接口',"
//			+ "`sid` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,"
//			+ "`logTime` bigint(20) DEFAULT NULL COMMENT '日志记录时间',"
//			+ "`spendTime` bigint(20) DEFAULT NULL COMMENT '花费时间',"
//			+ "`requestData` mediumtext COLLATE utf8_unicode_ci COMMENT '请求日志',"
//			+ "`responseData` mediumtext COLLATE utf8_unicode_ci COMMENT '响应日志',"
//			+ "PRIMARY KEY (`id`),"
//			+ "KEY `logTime` (`logTime`),"
//			+ "KEY `flag` (`flag`),"
//			+ "KEY `api` (`api`)"
//			+ " ) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;";
	private static final String createSQL = "CREATE TABLE if not exists \"public\".#tableName# ( "
			+ " \"id\" serial NOT NULL, "
			+ " \"flag\" VARCHAR (10) COLLATE \"default\" NOT NULL, "
			+ " \"api\" VARCHAR (64) COLLATE \"default\" NOT NULL, "
			+ " \"sid\" VARCHAR (32) COLLATE \"default\", "
			+ " \"logtime\" bigint NOT NULL, "
			+ " \"spendtime\" bigint, "
			+ " \"requestdata\" TEXT COLLATE \"default\", "
			+ " \"responsedata\" TEXT COLLATE \"default\", "
			+ " PRIMARY KEY ( \"id\", \"flag\", \"api\", \"logtime\" ) "
			+ " ) WITH (OIDS = FALSE); ";
	private static final String insertSQL = "INSERT INTO \"public\".#tableName# (flag,api,sid,logtime,spendtime,requestdata,responsedata) VALUES (?,?,?,?,?,?,?);";
	private static final String tableFirstName = "interface_log_";
	private static ThreadLocal<SimpleDateFormat> yyyyMMTL=new ThreadLocal<SimpleDateFormat>();
	private static final int MAXLENGTH = 1000;
	

	@Override
	public int insertLog(String flag, String api, String sid, long logTime,
			long spendTime, String requestData, String responseData) {
		int resultCount = 0;
		//根据日期拼接好表名
		if(yyyyMMTL.get()==null){
			yyyyMMTL.set(new SimpleDateFormat("yyyyMM"));
		}
		String tableName = tableFirstName + yyyyMMTL.get().format(new Date()).toLowerCase();
		log.info("数据表表名：" + tableName);
		//检查表是否存在
		if(!hasTable(tableName)){
			log.info("创建表：" + tableName);
			creatLogTable(tableName);
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = logConn.getConnection();
			pstmt = conn.prepareStatement(insertSQL.replace("#tableName#", tableName));
			pstmt.setString(1, flag);
			pstmt.setString(2, api);
			pstmt.setString(3, sid);
			pstmt.setLong(4, logTime);
			pstmt.setLong(5,spendTime);
			if(requestData.length() > MAXLENGTH){
				pstmt.setString(6, requestData.substring(0,MAXLENGTH));
			}else{
				pstmt.setString(6, requestData);
			}
			pstmt.setString(7, responseData);
			resultCount = pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			log.error("插入到日志表错误：" + e.getMessage(),e);
			resultCount = 0;
		}finally{
			try {
				logConn.close(null, pstmt, conn);
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		
		
		return resultCount;
	}
	
	/**
	 * 创建日志表
	 * @param tableName
	 * @return
	 */
	public synchronized boolean creatLogTable(String tableName){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = logConn.getConnection();
			pstmt = conn.prepareStatement(createSQL.replace("#tableName#", tableName));
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			log.error("创建日志表错误：" + e.getMessage(),e);
			return false;
		}finally{
			try {
				logConn.close(null, pstmt, conn);
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		return true;
	}
	
	/**
	 * 检查数据表是否存在
	 * @param tableName
	 * @return
	 */
	public boolean hasTable(String tableName){
		boolean result = false;
		Connection conn = null;
		DatabaseMetaData dbmd = null;
		ResultSet rs = null;
		try{
			conn = logConn.getConnection();
			dbmd = conn.getMetaData();
			rs = dbmd.getTables(null, null, tableName, null);
			while(rs.next())result = true;
		}catch(SQLException e){
			e.printStackTrace();
			log.error("检查表存在错误：" + e.getMessage(),e);
			result = false;
		}finally{
			try {
				logConn.close(rs, null, conn);
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	public DBConnectionDao getLogConn() {
		return logConn;
	}

	public void setLogConn(DBConnectionDao logConn) {
		this.logConn = logConn;
	}

	
}
