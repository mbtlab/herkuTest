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
import com.datacenter.bean.HouseBean;
import com.datacenter.initConfig.PreparedSql;


public class HouseAction extends BeanAction {
	protected static Logger log = Logger.getLogger(HouseAction.class);
	public static final String HOUSE = "house";

	/*
	 * christ
	 * 2017/05/04
	 * 查询SalesProHouse表
	 * */
	public List<String> selectHouse(List<HouseBean> houseList) {
		List<String> selectList = new ArrayList<String>();
		StringBuilder strIDs = new StringBuilder();
		String strQuery = PreparedSql.SELECTHOUSESQL.replace("#tableName#",HOUSE); 
		for (HouseBean house : houseList) {
//			strIDs.append(house.getId()+",");//拼接查询语言
			if(!"".equals(house.getHouse_cts_id())){
				strIDs.append("'" + house.getHouse_cts_id() + "',");
			}
		}

		String strID = strIDs.substring(0,strIDs.length()-1);
		log.info("查询到房间ID" + strID);
		strQuery = strQuery.replace("#ID#",strID);
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(strQuery);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				selectList.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("house查询错误"+e.getMessage());
		}finally{
			try {
				dbConn.close(rs, pstmt, conn);

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return selectList;
	}

	public int updateHouse(List<HouseBean> houseList){
		String strQuery = PreparedSql.UPDATEHOUSESQL.replace("#tableName#", HOUSE);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(strQuery);
			conn.setAutoCommit(false);
			for(HouseBean house : houseList){
				pstmt.setInt(1, Integer.valueOf(house.getId()));
//				pstmt.setString(1, house.getHouse_cts_id());
				pstmt.setString(2, house.getDev_code());
				pstmt.setString(3, house.getDev_name());
				pstmt.setString(4, house.getArea_code());
				pstmt.setString(5, house.getArea_name());
				pstmt.setString(6, house.getCity_code());
				pstmt.setString(7, house.getCity_name());
				pstmt.setString(8, house.getPro_code());
				pstmt.setString(9, house.getPro_name());
				pstmt.setString(10, house.getHouse_code());
				pstmt.setString(11, house.getGroup_code());
				pstmt.setString(12, house.getBuilding_code());
				pstmt.setString(13, house.getUnit_code());
				pstmt.setString(14, house.getFloor_code());
				pstmt.setString(15, house.getRoom_code());
				pstmt.setString(16, house.getHouse_type());
				if(!"".equals((house.getHouse_str())))pstmt.setInt(17, Integer.valueOf(house.getHouse_str()));else pstmt.setNull(17, Types.INTEGER);
				if(!"".equals((house.getHouse_face())))pstmt.setInt(18, Integer.valueOf(house.getHouse_face()));else pstmt.setNull(18, Types.INTEGER);
				if(!"".equals((house.getHouse_decor())))pstmt.setInt(19, Integer.valueOf(house.getHouse_decor()));else pstmt.setNull(19, Types.INTEGER);
				if(!"".equals((house.getHouse_haggle())))pstmt.setInt(20, Integer.valueOf(house.getHouse_haggle()));else pstmt.setNull(20, Types.INTEGER);
//				if(!"".equals(house.getHouse_area()))pstmt.setFloat(21, Float.valueOf(house.getHouse_area()));else pstmt.setNull(21, Types.FLOAT);
				if(!"".equals(house.getHouse_area()))pstmt.setDouble(21, Double.valueOf(house.getHouse_area()));else pstmt.setNull(21, Types.DOUBLE);
//				if(!"".equals(house.getRoom_area()))pstmt.setFloat(22, Float.valueOf(house.getRoom_area()));else pstmt.setNull(22, Types.FLOAT);
//				if(!"".equals(house.getHouse_price()))pstmt.setFloat(23, Float.valueOf(house.getHouse_price()));else pstmt.setNull(23, Types.FLOAT);
//				if(!"".equals(house.getTotal_price()))pstmt.setFloat(24, Float.valueOf(house.getTotal_price()));else pstmt.setNull(24, Types.FLOAT);
				if(!"".equals(house.getRoom_area()))pstmt.setDouble(22, Double.valueOf(house.getRoom_area()));else pstmt.setNull(22, Types.DOUBLE);
				if(!"".equals(house.getHouse_price()))pstmt.setDouble(23, Double.valueOf(house.getHouse_price()));else pstmt.setNull(23, Types.DOUBLE);
				if(!"".equals(house.getTotal_price()))pstmt.setDouble(24, Double.valueOf(house.getTotal_price()));else pstmt.setNull(24, Types.DOUBLE);
				pstmt.setString(25, house.getHouse_pic());
				if(!"".equals((house.getHouse_class())))pstmt.setInt(26, Integer.valueOf(house.getHouse_class()));else pstmt.setNull(26, Types.INTEGER);
				if(!"".equals((house.getHouse_state())))pstmt.setInt(27, Integer.valueOf(house.getHouse_state()));else pstmt.setNull(27, Types.INTEGER);
				if(!"".equals((house.getLast_lock_time())))pstmt.setInt(28, Integer.valueOf(house.getLast_lock_time()));else pstmt.setNull(28, Types.INTEGER);
				if(!"".equals((house.getLast_user_id())))pstmt.setInt(29, Integer.valueOf(house.getLast_user_id()));else pstmt.setNull(29, Types.INTEGER);
				pstmt.setString(30, house.getFile_no());
				pstmt.setString(31, house.getOrder_code());
				pstmt.setString(32, house.getSales_agent_code());
				pstmt.setString(33, house.getSales_agent_name());
				pstmt.setString(34, house.getHouse_remark());
				if(!"".equals(house.getSales_view_on()))pstmt.setInt(35, Integer.valueOf(house.getSales_view_on()));else pstmt.setNull(35, Types.INTEGER);
				if(!"".equals(house.getSales_view_state()))pstmt.setInt(36, Integer.valueOf(house.getSales_view_state()));else pstmt.setNull(36, Types.INTEGER);
				if(!"".equals((house.getTotal_cals())))pstmt.setInt(37, Integer.valueOf(house.getTotal_cals()));else pstmt.setNull(37, Types.INTEGER);
				if(!"".equals((house.getToday_cals())))pstmt.setInt(38, Integer.valueOf(house.getToday_cals()));else pstmt.setNull(38, Types.INTEGER);
				if(!"".equals((house.getLast_cal_time())))pstmt.setInt(39, Integer.valueOf(house.getLast_cal_time()));else pstmt.setNull(39, Types.INTEGER);
				pstmt.setString(40, house.getState_edit_flag());
				if(!"".equals((house.getTotal_1())))pstmt.setInt(41, Integer.valueOf(house.getTotal_1()));else pstmt.setNull(41, Types.INTEGER);
				if(!"".equals((house.getTotal_2())))pstmt.setInt(42, Integer.valueOf(house.getTotal_2()));else pstmt.setNull(42, Types.INTEGER);
				if(!"".equals((house.getTotal_3())))pstmt.setInt(43, Integer.valueOf(house.getTotal_3()));else pstmt.setNull(43, Types.INTEGER);
				pstmt.setTimestamp(44, Timestamp.valueOf(house.getLast_modify_on()));
//				pstmt.setInt(45, Integer.valueOf(house.getId()));
				pstmt.setString(45, house.getHouse_cts_id());
				pstmt.addBatch();
			}
			int[] count = pstmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.error("House更新出错：" + e.getMessage(),e);
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

//插入数据
	public int  insertHouse(List<HouseBean> houseList){
		String strQuery = PreparedSql.INSERTHOUSESQL.replace("#tableName#", HOUSE);
		Connection conn =null;
		PreparedStatement pstmt = null;
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(strQuery);
			conn.setAutoCommit(false);
			for (HouseBean house : houseList) {
				pstmt.setInt(1, Integer.valueOf(house.getId()));
				pstmt.setString(2, house.getHouse_cts_id());
				pstmt.setString(3, house.getDev_code());
				pstmt.setString(4, house.getDev_name());
				pstmt.setString(5, house.getArea_code());
				pstmt.setString(6, house.getArea_name());
				pstmt.setString(7, house.getCity_code());
				pstmt.setString(8, house.getCity_name());
				pstmt.setString(9, house.getPro_code());
				pstmt.setString(10, house.getPro_name());
				pstmt.setString(11, house.getHouse_code());
				pstmt.setString(12, house.getGroup_code());
				pstmt.setString(13, house.getBuilding_code());
				pstmt.setString(14, house.getUnit_code());
				pstmt.setString(15, house.getFloor_code());
				pstmt.setString(16, house.getRoom_code());
				pstmt.setString(17, house.getHouse_type());
				if(!"".equals((house.getHouse_str())))pstmt.setInt(18, Integer.valueOf(house.getHouse_str()));else pstmt.setNull(18, Types.INTEGER);
				if(!"".equals((house.getHouse_face())))pstmt.setInt(19, Integer.valueOf(house.getHouse_face()));else pstmt.setNull(19, Types.INTEGER);
				if(!"".equals((house.getHouse_decor())))pstmt.setInt(20, Integer.valueOf(house.getHouse_decor()));else pstmt.setNull(20, Types.INTEGER);
				if(!"".equals((house.getHouse_haggle())))pstmt.setInt(21, Integer.valueOf(house.getHouse_haggle()));else pstmt.setNull(21, Types.INTEGER);
//				if(!"".equals(house.getHouse_area()))pstmt.setFloat(22, Float.valueOf(house.getHouse_area()));else pstmt.setNull(22, Types.FLOAT);
				if(!"".equals(house.getHouse_area()))pstmt.setDouble(22, Double.valueOf(house.getHouse_area()));else pstmt.setNull(22, Types.DOUBLE);
//				if(!"".equals(house.getRoom_area()))pstmt.setFloat(23, Float.valueOf(house.getRoom_area()));else pstmt.setNull(23, Types.FLOAT);
				if(!"".equals(house.getRoom_area()))pstmt.setDouble(23, Double.valueOf(house.getRoom_area()));else pstmt.setNull(23, Types.DOUBLE);
//				if(!"".equals(house.getHouse_price()))pstmt.setFloat(24, Float.valueOf(house.getHouse_price()));else pstmt.setNull(24, Types.FLOAT);
//				if(!"".equals(house.getTotal_price()))pstmt.setFloat(25, Float.valueOf(house.getTotal_price()));else pstmt.setNull(25, Types.FLOAT);
				if(!"".equals(house.getHouse_price()))pstmt.setDouble(24, Double.valueOf(house.getHouse_price()));else pstmt.setNull(24, Types.DOUBLE);
				if(!"".equals(house.getTotal_price()))pstmt.setDouble(25, Double.valueOf(house.getTotal_price()));else pstmt.setNull(25, Types.DOUBLE);
				pstmt.setString(26, house.getHouse_pic());
				if(!"".equals((house.getHouse_class())))pstmt.setInt(27, Integer.valueOf(house.getHouse_class()));else pstmt.setNull(27, Types.INTEGER);
				if(!"".equals((house.getHouse_state())))pstmt.setInt(28, Integer.valueOf(house.getHouse_state()));else pstmt.setNull(28, Types.INTEGER);
				if(!"".equals((house.getLast_lock_time())))pstmt.setInt(29, Integer.valueOf(house.getLast_lock_time()));else pstmt.setNull(29, Types.INTEGER);
				if(!"".equals((house.getLast_user_id())))pstmt.setInt(30, Integer.valueOf(house.getLast_user_id()));else pstmt.setNull(30, Types.INTEGER);
				pstmt.setString(31, house.getFile_no());
				pstmt.setString(32, house.getOrder_code());
				pstmt.setString(33, house.getSales_agent_code());
				pstmt.setString(34, house.getSales_agent_name());
				pstmt.setString(35, house.getHouse_remark());
				if(!"".equals(house.getSales_view_on()))pstmt.setInt(36, Integer.valueOf(house.getSales_view_on()));else pstmt.setNull(36, Types.INTEGER);
				if(!"".equals(house.getSales_view_state()))pstmt.setInt(37, Integer.valueOf(house.getSales_view_state()));else pstmt.setNull(37, Types.INTEGER);
				if(!"".equals((house.getTotal_cals())))pstmt.setInt(38, Integer.valueOf(house.getTotal_cals()));else pstmt.setNull(38, Types.INTEGER);
				if(!"".equals((house.getToday_cals())))pstmt.setInt(39, Integer.valueOf(house.getToday_cals()));else pstmt.setNull(39, Types.INTEGER);
				if(!"".equals((house.getLast_cal_time())))pstmt.setInt(40, Integer.valueOf(house.getLast_cal_time()));else pstmt.setNull(40, Types.INTEGER);
				pstmt.setString(41, house.getState_edit_flag());
				if(!"".equals((house.getTotal_1())))pstmt.setInt(42, Integer.valueOf(house.getTotal_1()));else pstmt.setNull(42, Types.INTEGER);
				if(!"".equals((house.getTotal_2())))pstmt.setInt(43, Integer.valueOf(house.getTotal_2()));else pstmt.setNull(43, Types.INTEGER);
				if(!"".equals((house.getTotal_3())))pstmt.setInt(44, Integer.valueOf(house.getTotal_3()));else pstmt.setNull(44, Types.INTEGER);
				pstmt.setTimestamp(45, Timestamp.valueOf(house.getLast_modify_on()));
				pstmt.addBatch();
			}
			int[] count = pstmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.error("house插入出错：" + e.getMessage(),e);
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
	public int upsert(List<? extends DataBeanObject> beanList) {
		// TODO Auto-generated method stub
				@SuppressWarnings("unchecked")
				List<HouseBean> houseList = (List<HouseBean>) beanList;
				Map<String,HouseBean> userMap = new HashMap<String,HouseBean>();//用户数据信息MAP
				for(HouseBean house : houseList){
					//如果出现数据中id为空的情况，则认为为无效数据，无视该数据
					if(house.getId() != null && !"".equals(house.getId())){
						userMap.put(house.getHouse_cts_id(),house);
					}
				}
				int intUpdate = -1;
				int intInsert = -1;
				try{
					//根据ID查找用户表中的数据，找到已经存在系统中的数据id
					List<String> HouseIDList = this.selectHouse(houseList);
					List<HouseBean> updateDataList = new ArrayList<HouseBean>();//需要更新的用户数据
					List<HouseBean> insertDataList = new ArrayList<HouseBean>();//需要插入的用户数据
					for(String id :HouseIDList){
						updateDataList.add(userMap.get(id));
						userMap.remove(id);
					}
					insertDataList.addAll(userMap.values());
					if(!updateDataList.isEmpty())intUpdate = this.updateHouse(updateDataList);//更新操作
					if(!insertDataList.isEmpty())intInsert = this.insertHouse(insertDataList);//插入操作
				}catch(Exception e){
					e.printStackTrace();
					log.error("upsertHouse数据时出错：" + e.getMessage(),e);
					return -1;
				}
				return intInsert + intUpdate;
	}

}
