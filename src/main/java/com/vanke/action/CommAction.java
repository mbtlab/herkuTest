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

import com.datacenter.bean.CommBean;
import com.datacenter.bean.DataBeanObject;
import com.datacenter.initConfig.PreparedSql;

/**
 * Comm 处理类
 * Date: 2017-05-05
 * @author Leo
 *
 */
public class CommAction extends BeanAction {
	protected static Logger log = Logger.getLogger(CommAction.class);
	public static final String COMM = "comm";
	
	
	@Override
	public int upsert(List<? extends DataBeanObject> beanList) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<CommBean> commList = (List<CommBean>) beanList;
		Map<String,CommBean> commMap = new HashMap<String,CommBean>();
		for(CommBean comm : commList){
			//如果出现数据中id为空的情况，则认为为无效数据，无视该数据
			if(comm.getId() != null && !"".equals(comm.getId())){
				commMap.put(comm.getId(),comm);
			}
		}
		int intUpdate = -1;
		int intInsert = -1;
		try{
			List<String> commIDList = this.selectComm(commList);
			List<CommBean> updateDataList = new ArrayList<CommBean>();//需要更新的数据
			List<CommBean> insertDataList = new ArrayList<CommBean>();//需要插入的数据
			for(String id : commIDList){
				updateDataList.add(commMap.get(id));
				commMap.remove(id);
			}
			insertDataList.addAll(commMap.values());
			if(!updateDataList.isEmpty())intUpdate = this.updateComm(updateDataList);//更新操作
			if(!insertDataList.isEmpty())intInsert = this.insertComm(insertDataList);//插入操作
		}catch(Exception e){
			e.printStackTrace();
			log.error("comm upsert数据时出错：" + e.getMessage(),e);
			return -1;
		}
		
		
		return intInsert + intUpdate;
	}
	/**
	 * 查询comm已有ID
	 * @param commList
	 * @return
	 */
	public List<String> selectComm(List<CommBean> commList){
		List<String> selectList = new ArrayList<String>();
		//拼接好查询SQL
		String strQuery = PreparedSql.SELECTSQL.replace("#tableName#", COMM);
		StringBuilder strIDs = new StringBuilder();
		for(CommBean comm : commList){
			strIDs.append(comm.getId() + ",");
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
			log.error("comm查询出错：" + e.getMessage(),e);
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
	 * 更新Comm数据
	 * @param commList
	 * @return
	 */
	public int updateComm(List<CommBean> commList){
		String strQuery = PreparedSql.UPDATECOMMSQL.replace("#tableName#", COMM);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(strQuery);
			conn.setAutoCommit(false);
			for(CommBean comm : commList){
				pstmt.setString(1, comm.getDev_code());
				pstmt.setString(2, comm.getDev_name());
				pstmt.setString(3, comm.getArea_code());
				pstmt.setString(4, comm.getArea_name());
				pstmt.setString(5, comm.getCity_code());
				pstmt.setString(6, comm.getCity_name());
				pstmt.setString(7, comm.getPro_code());
				pstmt.setString(8, comm.getPro_name());
				pstmt.setString(9, comm.getAgent_code());
				pstmt.setString(10, comm.getAgent_name());
				if(!"".equals(comm.getUser_id()))pstmt.setInt(11, Integer.valueOf(comm.getUser_id()));else pstmt.setNull(11, Types.INTEGER);
				pstmt.setString(12, comm.getUser_cts_id());
				pstmt.setString(13, comm.getUser_name());
				pstmt.setString(14, comm.getSales_cts_id());
				pstmt.setString(15, comm.getSales_phone());
				pstmt.setString(16, comm.getSales_name());
				if(!"".equals(comm.getComm_class()))pstmt.setInt(17, Integer.valueOf(comm.getComm_class()));else pstmt.setNull(17, Types.INTEGER);
				if(!"".equals(comm.getComm_type()))pstmt.setInt(18, Integer.valueOf(comm.getComm_type()));else pstmt.setNull(18, Types.INTEGER);
				if(!"".equals(comm.getContext_type()))pstmt.setInt(19, Integer.valueOf(comm.getContext_type()));else pstmt.setNull(19, Types.INTEGER);
				pstmt.setString(20, comm.getContext());
				pstmt.setString(21, comm.getUrl());
				if(!"".equals(comm.getStart_time()))pstmt.setInt(22, Integer.valueOf(comm.getStart_time()));else pstmt.setNull(22, Types.INTEGER);
				if(!"".equals(comm.getEnd_time()))pstmt.setInt(23, Integer.valueOf(comm.getEnd_time()));else pstmt.setNull(23, Types.INTEGER);
				if(!"".equals(comm.getComm_effect()))pstmt.setInt(24, Integer.valueOf(comm.getComm_effect()));else pstmt.setNull(24, Types.INTEGER);
				pstmt.setString(25, comm.getRemark());
				pstmt.setString(26, comm.getRemark_url());
				if(!"".equals(comm.getSeeflag()))pstmt.setInt(27, Integer.valueOf(comm.getSeeflag()));else pstmt.setNull(27, Types.INTEGER);
				if(!"".equals(comm.getSync_state()))pstmt.setInt(28, Integer.valueOf(comm.getSync_state()));else pstmt.setNull(28, Types.INTEGER);
				if(!"".equals(comm.getSync_cnt()))pstmt.setInt(29, Integer.valueOf(comm.getSync_cnt()));else pstmt.setNull(29, Types.INTEGER);
				pstmt.setString(30, comm.getSync_msg());
				if(!"".equals(comm.getSync_time()))pstmt.setInt(31, Integer.valueOf(comm.getSync_time()));else pstmt.setNull(31, Types.INTEGER);
				pstmt.setString(32, comm.getFenqi_code());
				pstmt.setString(33, comm.getFenqi_name());
				pstmt.setString(34, comm.getAddsource());
				pstmt.setString(35, comm.getAddsource_type());
				pstmt.setString(36, comm.getCognition_type());
				pstmt.setTimestamp(37, Timestamp.valueOf(comm.getLast_modify_on()));
				pstmt.setInt(38, Integer.valueOf(comm.getId()));
				pstmt.addBatch();
			}
			int[] count = pstmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.error("comm更新出错:" + e.getMessage(),e);
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
	 * 插入Comm数据
	 * @param commList
	 * @return
	 */
	public int insertComm(List<CommBean> commList){
		String strQuery = PreparedSql.INSERTCOMMSQL.replace("#tableName#", COMM);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(strQuery);
			conn.setAutoCommit(false);
			for(CommBean comm : commList){
				pstmt.setInt(1, Integer.valueOf(comm.getId()));
				pstmt.setString(2, comm.getDev_code());
				pstmt.setString(3, comm.getDev_name());
				pstmt.setString(4, comm.getArea_code());
				pstmt.setString(5, comm.getArea_name());
				pstmt.setString(6, comm.getCity_code());
				pstmt.setString(7, comm.getCity_name());
				pstmt.setString(8, comm.getPro_code());
				pstmt.setString(9, comm.getPro_name());
				pstmt.setString(10, comm.getAgent_code());
				pstmt.setString(11, comm.getAgent_name());
				if(!"".equals(comm.getUser_id()))pstmt.setInt(12, Integer.valueOf(comm.getUser_id()));else pstmt.setNull(12, Types.INTEGER);
				pstmt.setString(13, comm.getUser_cts_id());
				pstmt.setString(14, comm.getUser_name());
				pstmt.setString(15, comm.getSales_cts_id());
				pstmt.setString(16, comm.getSales_phone());
				pstmt.setString(17, comm.getSales_name());
				if(!"".equals(comm.getComm_class()))pstmt.setInt(18, Integer.valueOf(comm.getComm_class()));else pstmt.setNull(18, Types.INTEGER);
				if(!"".equals(comm.getComm_type()))pstmt.setInt(19, Integer.valueOf(comm.getComm_type()));else pstmt.setNull(19, Types.INTEGER);
				if(!"".equals(comm.getContext_type()))pstmt.setInt(20, Integer.valueOf(comm.getContext_type()));else pstmt.setNull(20, Types.INTEGER);
				pstmt.setString(21, comm.getContext());
				pstmt.setString(22, comm.getUrl());
				if(!"".equals(comm.getStart_time()))pstmt.setInt(23, Integer.valueOf(comm.getStart_time()));else pstmt.setNull(23, Types.INTEGER);
				if(!"".equals(comm.getEnd_time()))pstmt.setInt(24, Integer.valueOf(comm.getEnd_time()));else pstmt.setNull(24, Types.INTEGER);
				if(!"".equals(comm.getComm_effect()))pstmt.setInt(25, Integer.valueOf(comm.getComm_effect()));else pstmt.setNull(25, Types.INTEGER);
				pstmt.setString(26, comm.getRemark());
				pstmt.setString(27, comm.getRemark_url());
				if(!"".equals(comm.getSeeflag()))pstmt.setInt(28, Integer.valueOf(comm.getSeeflag()));else pstmt.setNull(28, Types.INTEGER);
				if(!"".equals(comm.getSync_state()))pstmt.setInt(29, Integer.valueOf(comm.getSync_state()));else pstmt.setNull(29, Types.INTEGER);
				if(!"".equals(comm.getSync_cnt()))pstmt.setInt(30, Integer.valueOf(comm.getSync_cnt()));else pstmt.setNull(30, Types.INTEGER);
				pstmt.setString(31, comm.getSync_msg());
				if(!"".equals(comm.getSync_time()))pstmt.setInt(32, Integer.valueOf(comm.getSync_time()));else pstmt.setNull(32, Types.INTEGER);
				pstmt.setString(33, comm.getFenqi_code());
				pstmt.setString(34, comm.getFenqi_name());
				pstmt.setString(35, comm.getAddsource());
				pstmt.setString(36, comm.getAddsource_type());
				pstmt.setString(37, comm.getCognition_type());
				pstmt.setTimestamp(38, Timestamp.valueOf(comm.getLast_modify_on()));
				pstmt.addBatch();
			}
			int[] count = pstmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.error("comm插入出错:" + e.getMessage(),e);
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
