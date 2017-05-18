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
import com.datacenter.bean.SalesProHouseBean;
import com.datacenter.initConfig.PreparedSql;


public class SalesProHouseAction extends BeanAction{
	protected static Logger log = Logger.getLogger(SalesProHouseAction.class);
	public static final String SALESPROHOUSE = "sales_pro_house";//SalesProHouse表名


	/*
	 * christ
	 * 2017/05/04
	 * 查询SalesProHouse表
	 * */
	public List<String> selectSalesProHouse(List<SalesProHouseBean> SalesProHouseList) {
		List<String> selectList = new ArrayList<String>();
		StringBuilder strIDs = new StringBuilder();
		String strQuery = PreparedSql.SELECTSQL.replace("#tableName#", SALESPROHOUSE);
		for (SalesProHouseBean sales : SalesProHouseList){
			strIDs.append(sales.getId()+",");
		}
		log.info("查询的ID：" + strIDs);
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
			log.error(" selectSalesProHouse查询出错：" + e.getMessage(),e);
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
	 * 更新交易数据
	 * @param transList
	 * @return
	 */
	public int updateSalesProHouse(List<SalesProHouseBean> SalesProHouseList){
		String strQuery = PreparedSql.UPDATESALESPROHOUSESQL.replace("#tableName#", SALESPROHOUSE);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(strQuery);
			conn.setAutoCommit(false);
			for(SalesProHouseBean sales : SalesProHouseList){
				pstmt.setString(1, sales.getDev_code());
				pstmt.setString(2, sales.getDev_name());
				pstmt.setString(3, sales.getArea_code());
				pstmt.setString(4, sales.getArea_name());
				pstmt.setString(5, sales.getCity_code());
				pstmt.setString(6, sales.getCity_name());
				pstmt.setString(7, sales.getPro_code());
				pstmt.setString(8,  sales.getPro_name());
				pstmt.setString(9, sales.getHouse_class());
				pstmt.setString(10, sales.getAgent_code());
				pstmt.setString(11, sales.getAgent_name());
				if(!"".equals(sales.getUser_type()))pstmt.setInt(12, Integer.valueOf(sales.getUser_type()));else pstmt.setNull(12, Types.INTEGER);
				pstmt.setString(13, sales.getSales_phone());
				pstmt.setString(14, sales.getSales_name());
				pstmt.setString(15, sales.getSales_cts_id());
				pstmt.setString(16, sales.getMingyuan_id());
				pstmt.setString(17, sales.getMingyuan_acc());
				if(!"".equals(sales.getSync_cnt()))pstmt.setInt(18, Integer.valueOf(sales.getSync_cnt()));else pstmt.setNull(18, Types.INTEGER);
				if(!"".equals(sales.getSync_state()))pstmt.setInt(19, Integer.valueOf(sales.getSync_state()));else pstmt.setNull(19, Types.INTEGER);
				pstmt.setString(20, sales.getSync_msg());
				if(!"".equals(sales.getSync_time()))pstmt.setInt(21, Integer.valueOf(sales.getSync_time()));else pstmt.setNull(21, Types.INTEGER);
				if(!"".equals(sales.getSys_state()))pstmt.setInt(22, Integer.valueOf(sales.getSys_state()));else pstmt.setNull(22, Types.INTEGER);
				if(!"".equals(Integer.valueOf (sales.getDel_flag())))pstmt.setInt(23, Integer.valueOf(sales.getDel_flag()));else pstmt.setNull(23, Types.INTEGER);
				if(!"".equals(sales.getUp_time()))pstmt.setInt(24, Integer.valueOf(sales.getUp_time()));else pstmt.setNull(24, Types.INTEGER);
				pstmt.setTimestamp(25, Timestamp.valueOf(sales.getLast_modify_on()));
				pstmt.setInt(26,Integer.valueOf(sales.getId()));
				pstmt.addBatch();
			}
			int[] count = pstmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.error("SalesProHouse更新出错：" + e.getMessage(),e);
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
	 * 插入交易数据
	 * @param SalesProHouseList
	 * @return
	 */
	public int insertSalesProHouse(List<SalesProHouseBean> SalesProHouseList){
		String strQuery = PreparedSql.INSERTSALESPROHOUSESQL.replace("#tableName#", SALESPROHOUSE);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(strQuery);
			conn.setAutoCommit(false);
			for(SalesProHouseBean sales : SalesProHouseList){
				pstmt.setInt(1, Integer.valueOf(sales.getId()));
				pstmt.setString(2, sales.getDev_code());
				pstmt.setString(3, sales.getDev_name());
				pstmt.setString(4,sales.getArea_code());
				pstmt.setString(5, sales.getArea_name());
				pstmt.setString(6, sales.getCity_code());
				pstmt.setString(7,sales.getCity_name());
				pstmt.setString(8,sales.getPro_code());
				pstmt.setString(9, sales.getPro_name());
				pstmt.setString(10, sales.getHouse_class());
				pstmt.setString(11,sales.getAgent_code());
				pstmt.setString(12,sales.getAgent_name());
				if(!"".equals(sales.getUser_type()))pstmt.setInt(13, Integer.valueOf(sales.getUser_type()));else pstmt.setNull(13, Types.INTEGER);
				pstmt.setString(14, sales.getSales_phone());
				pstmt.setString(15,sales.getSales_name());
				pstmt.setString(16, sales.getSales_cts_id());
				pstmt.setString(17, sales.getMingyuan_id());
				pstmt.setString(18,sales.getMingyuan_acc());
				if(!"".equals(sales.getSync_cnt()))pstmt.setInt(19, Integer.valueOf(sales.getSync_cnt()));else pstmt.setNull(19, Types.INTEGER);
				if(!"".equals(sales.getSync_state()))pstmt.setInt(20, Integer.valueOf(sales.getSync_state()));else pstmt.setNull(20, Types.INTEGER);
				pstmt.setString(21, sales.getSync_msg());
				if(!"".equals(sales.getSync_time()))pstmt.setInt(22, Integer.valueOf(sales.getSync_time()));else pstmt.setNull(22, Types.INTEGER);
				if(!"".equals(sales.getSys_state()))pstmt.setInt(23, Integer.valueOf(sales.getSys_state()));else pstmt.setNull(23, Types.INTEGER);
				if(!"".equals(Integer.valueOf (sales.getDel_flag())))pstmt.setInt(24, Integer.valueOf(sales.getDel_flag()));else pstmt.setNull(24, Types.INTEGER);
				if(!"".equals(sales.getUp_time()))pstmt.setInt(25, Integer.valueOf(sales.getUp_time()));else pstmt.setNull(25, Types.INTEGER);
				pstmt.setTimestamp(26, Timestamp.valueOf(sales.getLast_modify_on()));
				pstmt.addBatch();
			}
			int[] count = pstmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.error("SalesProHouse插入出错：" + e.getMessage(),e);
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
		List<SalesProHouseBean> salesList = (List<SalesProHouseBean>) beanList;
		Map<String,SalesProHouseBean> userMap = new HashMap<String,SalesProHouseBean>();//用户数据信息MAP
		for(SalesProHouseBean sale : salesList){
			//如果出现数据中id为空的情况，则认为为无效数据，无视该数据
			if(sale.getId() != null && !"".equals(sale.getId())){
				userMap.put(sale.getId(),sale);
			}
		}
		int intUpdate = -1;
		int intInsert = -1;
		try{
			//根据ID查找用户表中的数据，找到已经存在系统中的数据id
			List<String> SalesProHouseIDList = this.selectSalesProHouse(salesList);
			List<SalesProHouseBean> updateDataList = new ArrayList<SalesProHouseBean>();//需要更新的用户数据
			List<SalesProHouseBean> insertDataList = new ArrayList<SalesProHouseBean>();//需要插入的用户数据
			for(String id : SalesProHouseIDList){
				updateDataList.add(userMap.get(id));
				userMap.remove(id);
			}
			insertDataList.addAll(userMap.values());
			if(!updateDataList.isEmpty())intUpdate = this.updateSalesProHouse(updateDataList);//更新操作
			if(!insertDataList.isEmpty())intInsert = this.insertSalesProHouse(insertDataList);//插入操作
		}catch(Exception e){
			e.printStackTrace();
			log.error("upsertSalesProHouse数据时出错：" + e.getMessage(),e);
			return -1;
		}

		return intInsert + intUpdate;
	}
}