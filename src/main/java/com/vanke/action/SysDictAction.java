package com.vanke.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.datacenter.bean.DataBeanObject;
import com.datacenter.bean.SysDictBean;
import com.datacenter.initConfig.PreparedSql;

/**
 * 更新/新建sys_dict数据
 * Date: 2017-05-04
 * @author Leo
 *
 */
public class SysDictAction extends BeanAction {
	protected static Logger log = Logger.getLogger(SysDictAction.class);
	public static final String SYSDICT = "sys_dict";

	@Override
	public int upsert(List<? extends DataBeanObject> beanList) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<SysDictBean> sysDictList = (List<SysDictBean>) beanList;
		Map<String,SysDictBean> sysMap = new HashMap<String,SysDictBean>();
		for(SysDictBean sys : sysDictList){
			//如果出现数据中id为空的情况，则认为为无效数据，无视该数据
			if(sys.getId() != null && !"".equals(sys.getId())){
				sysMap.put(sys.getId(),sys);
			}
		}
		int intUpdate = -1;
		int intInsert = -1;
		try{
			List<String> sysIDList = this.selectSysDict(sysDictList);
			List<SysDictBean> updateDataList = new ArrayList<SysDictBean>();//需要更新的数据
			List<SysDictBean> insertDataList = new ArrayList<SysDictBean>();//需要插入的数据
			for(String id : sysIDList){
				updateDataList.add(sysMap.get(id));
				sysMap.remove(id);
			}
			insertDataList.addAll(sysMap.values());
			if(!updateDataList.isEmpty())intUpdate = this.updateSysDict(updateDataList);//更新操作
			if(!insertDataList.isEmpty())intInsert = this.insertSysDict(insertDataList);//插入操作
		}catch(Exception e){
			e.printStackTrace();
			log.error("sys_dict upsert数据时出错：" + e.getMessage(),e);
			return -1;
		}
		
		
		return intInsert + intUpdate;
	}
	/**
	 * 查询表中存在的数据，并返回ID
	 * @param sysDictList
	 * @return
	 */
	public List<String> selectSysDict(List<SysDictBean> sysDictList){
		List<String> selectList = new ArrayList<String>();
		//拼接好查询SQL
		String strQuery = PreparedSql.SELECTSQL.replace("#tableName#", SYSDICT);
		StringBuilder strIDs = new StringBuilder();
		for(SysDictBean sys : sysDictList){
			strIDs.append(sys.getId() + ",");
		}
		String strID = strIDs.substring(0,strIDs.length() - 1);
		strQuery = strQuery.replace("#ID#", strID);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = dbConn.getConnection();//获取连接
			pstmt = conn.prepareStatement(strQuery);
			rs = pstmt.executeQuery();
			while(rs.next()){
				selectList.add(rs.getString(1));
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("sys_dict查询出错：" + e.getMessage(),e);
		}finally{
			try{
				dbConn.close(rs, pstmt, conn);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return selectList;
	}
	/**
	 * 更新表中数据
	 * @param sysDictList
	 * @return
	 */
	public int updateSysDict(List<SysDictBean> sysDictList){
		String strQuery = PreparedSql.UPDATESYSDICTSQL.replace("#tableName#", SYSDICT);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(strQuery);
			conn.setAutoCommit(false);
			for(SysDictBean sys : sysDictList){
				pstmt.setString(1, sys.getCode());
				pstmt.setString(2, sys.getName());
				pstmt.setString(3, sys.getValuejson());
				pstmt.setTimestamp(4, Timestamp.valueOf(sys.getLast_modify_on()));
				pstmt.setInt(5, Integer.valueOf(sys.getId()));
				pstmt.addBatch();
			}
			int[] count = pstmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.error("sys_dict更新出错:" + e.getMessage(),e);
			return -10;
		}finally{
			try {
				dbConn.close(null, pstmt, conn);
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		return 1;
	}
	/**
	 * 插入数据到表中
	 * @param sysDictList
	 * @return
	 */
	public int insertSysDict(List<SysDictBean> sysDictList){
		String strQuery = PreparedSql.INSERTSYSDICTSQL.replace("#tableName#", SYSDICT);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(strQuery);
			conn.setAutoCommit(false);
			for(SysDictBean sys : sysDictList){
				pstmt.setInt(1, Integer.valueOf(sys.getId()));
				pstmt.setString(2, sys.getCode());
				pstmt.setString(3, sys.getName());
				pstmt.setString(4, sys.getValuejson());
				pstmt.setTimestamp(5, Timestamp.valueOf(sys.getLast_modify_on()));
				pstmt.addBatch();
			}
			int[] count = pstmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.error("sys_dict插入出错:" + e.getMessage(),e);
			return -10;
		}finally{
			try {
				dbConn.close(null, pstmt, conn);
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			
		}
		return 1;
	}

	

	
	
}
