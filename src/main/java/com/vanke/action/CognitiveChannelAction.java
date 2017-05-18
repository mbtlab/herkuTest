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

import com.datacenter.bean.CognitiveChannelBean;
import com.datacenter.bean.DataBeanObject;
import com.datacenter.initConfig.PreparedSql;

public class CognitiveChannelAction extends BeanAction{

	protected static Logger log = Logger.getLogger(CognitiveChannelAction.class);
	public static final String CONGNITIVECHANNEL= "cognitive_channel";//SalesProHouse表名


	/*
	 * christ
	 * 2017/05/04
	 * 查询CognitiveChanne表
	 * */
	public List<String> selectCognitiveChannel(List<CognitiveChannelBean> CognitiveChannelList) {
		List<String> selectList = new ArrayList<String>();
		StringBuilder strIDs = new StringBuilder();
		String strQuery = PreparedSql.SELECTSQL.replace("#tableName#", CONGNITIVECHANNEL);
		for (CognitiveChannelBean ccb : CognitiveChannelList){
			strIDs.append(ccb.getId()+",");
		}
		String strID = strIDs.substring(0,strIDs.length()-1);
		strQuery = strQuery.replace("#ID#", strID);

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{ 
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(strQuery);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				selectList.add(rs.getString(1));	
			}
		}
		catch(Exception e){
			e.printStackTrace();
			log.error(" CognitiveChannel查询出错：" + e.getMessage(),e);
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
	 * 更新数据
	 * @param transList
	 * @return
	 */
	public int updateSalesProHouse(List<CognitiveChannelBean> CognitiveChannelList){
		String strQuery = PreparedSql.UPDATECONITIVECHANNLESQL.replace("#tableName#", CONGNITIVECHANNEL);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(strQuery);
			conn.setAutoCommit(false);
			for(CognitiveChannelBean ccb : CognitiveChannelList){
				pstmt.setString(1, ccb.getPro_code());
				pstmt.setString(2, ccb.getPro_name());
				pstmt.setString(3, ccb.getCognitive_name());
				pstmt.setString(4, ccb.getCognitive_value());
				pstmt.setString(5, ccb.getClass_value());
				if(!"".equals(ccb.getDel_flag()))pstmt.setInt(6, Integer.valueOf(ccb.getDel_flag()));else pstmt.setNull(6, Types.INTEGER);
				pstmt.setTimestamp(7, Timestamp.valueOf(ccb.getLast_modify_on()));
				pstmt.setInt(8, Integer.valueOf(ccb.getId()));
				pstmt.addBatch();
			}
			int[] count = pstmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.error("CognitiveChannel更新出错：" + e.getMessage(),e);
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
	 * 插入数据
	 * @param SalesProHouseList
	 * @return
	 */
	public int insertSalesProHouse(List<CognitiveChannelBean> cognitiveChannelList){
		String strQuery = PreparedSql.INSERTCONITIVECHANNELSQL.replace("#tableName#", CONGNITIVECHANNEL);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(strQuery);
			conn.setAutoCommit(false);
			for(CognitiveChannelBean ccb : cognitiveChannelList){
				pstmt.setInt(1, Integer.valueOf(ccb.getId()));
				pstmt.setString(2, ccb.getPro_code());
				pstmt.setString(3, ccb.getPro_name());
				pstmt.setString(4, ccb.getCognitive_name());
				pstmt.setString(5, ccb.getCognitive_value());
				pstmt.setString(6, ccb.getClass_value());
				if(!"".equals(ccb.getDel_flag()))pstmt.setInt(7, Integer.valueOf(ccb.getDel_flag()));else pstmt.setNull(7, Types.INTEGER);
				pstmt.setTimestamp(8, Timestamp.valueOf(ccb.getLast_modify_on()));
				pstmt.addBatch();
			}
			int[] count = pstmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.error("cognitiveChanne插入出错：" + e.getMessage(),e);
			return -1;
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

	@Override
	public int upsert(java.util.List<? extends DataBeanObject> beanList) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<CognitiveChannelBean> cognitiveChannelList = (List<CognitiveChannelBean>) beanList;
		Map<String,CognitiveChannelBean> userMap = new HashMap<String,CognitiveChannelBean>();//用户数据信息MAP
		for(CognitiveChannelBean ccb: cognitiveChannelList){
			//如果出现数据中id为空的情况，则认为为无效数据，无视该数据
			if(ccb.getId() != null && !"".equals(ccb.getId())){
				userMap.put(ccb.getId(),ccb);
			}
		}
		int intUpdate = -1;
		int intInsert = -1;
		try{
			//根据ID查找用户表中的数据，找到已经存在系统中的数据id
			List<String> SalesProHouseIDList = this.selectCognitiveChannel(cognitiveChannelList);
			List<CognitiveChannelBean> updateDataList = new ArrayList<CognitiveChannelBean>();//需要更新的用户数据
			List<CognitiveChannelBean> insertDataList = new ArrayList<CognitiveChannelBean>();//需要插入的用户数据
			for(String id : SalesProHouseIDList){
				updateDataList.add(userMap.get(id));
				userMap.remove(id);
			}
			insertDataList.addAll(userMap.values());
			if(!updateDataList.isEmpty())intUpdate = this.updateSalesProHouse(updateDataList);//更新操作
			if(!insertDataList.isEmpty())intInsert = this.insertSalesProHouse(insertDataList);//插入操作
		}catch(Exception e){
			e.printStackTrace();
			log.error("CognitiveChannel数据时出错：" + e.getMessage(),e);
			return -1;
		}

		return intInsert + intUpdate;
	}
}
