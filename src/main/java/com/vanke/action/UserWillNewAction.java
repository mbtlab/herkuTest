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
import com.datacenter.bean.UserWillNewBean;
import com.datacenter.initConfig.PreparedSql;

public class UserWillNewAction extends BeanAction{
	protected static Logger log = Logger.getLogger(UserWillNewAction.class);
	public static final String USERWILLNEW = "user_will_new";//SalesProHouse表名


	/*
	 * christ
	 * 2017/05/04
	 * 查询UserWillNewAction表
	 * */
	public List<String> selectUserWillNew(List<UserWillNewBean> userWillNewList) {
		List<String> selectList = new ArrayList<String>();
		StringBuilder strIDs = new StringBuilder();
		String strQuery = PreparedSql.SELECTSQL.replace("#tableName#", USERWILLNEW);
		for (UserWillNewBean uwn : userWillNewList){
			strIDs.append(uwn.getId()+",");
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
			log.error(" UserWillNew查询出错：" + e.getMessage(),e);
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
	public int updateUserWillNew(List<UserWillNewBean> userWillNewList){
		String strQuery = PreparedSql.UPDATEUSERWILLNEWSQL.replace("#tableName#", USERWILLNEW);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(strQuery);
			conn.setAutoCommit(false);
			for(UserWillNewBean uwn : userWillNewList){
				if(!"".equals(uwn.getUser_id()))pstmt.setInt(1, Integer.valueOf(uwn.getUser_id()));else pstmt.setNull(1, Types.INTEGER);
				pstmt.setString(2,uwn.getUser_name());
				pstmt.setString(3, uwn.getAgent_code());
				pstmt.setString(4,uwn.getAgent_name());
				pstmt.setString(5, uwn.getSales_phone());
				pstmt.setString(6,uwn.getSales_name());
				if(!"".equals(uwn.getUp_time()))pstmt.setInt(7, Integer.valueOf(uwn.getUp_time()));else pstmt.setNull(7, Types.INTEGER);
				if(!"".equals(uwn.getHouse_id_1()))pstmt.setInt(8, Integer.valueOf(uwn.getHouse_id_1()));else pstmt.setNull(8, Types.INTEGER);
				pstmt.setString(9, uwn.getHouse_name_1());
				if(!"".equals(uwn.getHouse_id_2()))pstmt.setInt(10, Integer.valueOf(uwn.getHouse_id_2()));else pstmt.setNull(10, Types.INTEGER);
				pstmt.setString(11, uwn.getHouse_name_2());
				if(!"".equals(uwn.getHouse_id_3()))pstmt.setInt(12, Integer.valueOf(uwn.getHouse_id_3()));else pstmt.setNull(12, Types.INTEGER);
				pstmt.setString(13, uwn.getHouse_name_3());
				pstmt.setTimestamp(14, Timestamp.valueOf(uwn.getLast_modify_on()));
				pstmt.setInt(15,Integer.valueOf(uwn.getId()));
				pstmt.addBatch();
			}
			int[] count = pstmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.error("UserWillNew更新出错：" + e.getMessage(),e);
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
	public int insertUserWillNew(List<UserWillNewBean> userWillNewBeanList){
		String strQuery = PreparedSql.INSERTUSERWILLNEWSQL.replace("#tableName#", USERWILLNEW);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(strQuery);
			conn.setAutoCommit(false);
			for(UserWillNewBean uwn : userWillNewBeanList){
				pstmt.setInt(1,Integer.valueOf(uwn.getId()));
				if(!"".equals(uwn.getUser_id()))pstmt.setInt(2, Integer.valueOf(uwn.getUser_id()));else pstmt.setNull(2, Types.INTEGER);
				pstmt.setString(3,uwn.getUser_name());
				pstmt.setString(4, uwn.getAgent_code());
				pstmt.setString(5,uwn.getAgent_name());
				pstmt.setString(6, uwn.getSales_phone());
				pstmt.setString(7,uwn.getSales_name());
				if(!"".equals(uwn.getUp_time()))pstmt.setInt(8, Integer.valueOf(uwn.getUp_time()));else pstmt.setNull(8, Types.INTEGER);
				if(!"".equals(uwn.getHouse_id_1()))pstmt.setInt(9, Integer.valueOf(uwn.getHouse_id_1()));else pstmt.setNull(9, Types.INTEGER);
				pstmt.setString(10, uwn.getHouse_name_1());
				if(!"".equals(uwn.getHouse_id_2()))pstmt.setInt(11, Integer.valueOf(uwn.getHouse_id_2()));else pstmt.setNull(11, Types.INTEGER);
				pstmt.setString(12, uwn.getHouse_name_2());
				if(!"".equals(uwn.getHouse_id_3()))pstmt.setInt(13, Integer.valueOf(uwn.getHouse_id_3()));else pstmt.setNull(13, Types.INTEGER);
				pstmt.setString(14, uwn.getHouse_name_3());
				pstmt.setTimestamp(15, Timestamp.valueOf(uwn.getLast_modify_on()));
				pstmt.addBatch();
			}
			int[] count = pstmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.error("userWillNew插入出错：" + e.getMessage(),e);
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
		List<UserWillNewBean> userWillNewList = (List<UserWillNewBean>) beanList;
		Map<String,UserWillNewBean> userMap = new HashMap<String,UserWillNewBean>();//用户数据信息MAP
		for(UserWillNewBean uwn : userWillNewList){
			//如果出现数据中id为空的情况，则认为为无效数据，无视该数据
			if(uwn.getId() != null && !"".equals(uwn.getId())){
				userMap.put(uwn.getId(),uwn);
			}
		}
		int intUpdate = -1;
		int intInsert = -1;
		try{
			//根据ID查找用户表中的数据，找到已经存在系统中的数据id
			List<String> userWillNewBeanIDList = this.selectUserWillNew(userWillNewList);
			List<UserWillNewBean> updateDataList = new ArrayList<UserWillNewBean>();//需要更新的用户数据
			List<UserWillNewBean> insertDataList = new ArrayList<UserWillNewBean>();//需要插入的用户数据
			for(String id : userWillNewBeanIDList){
				updateDataList.add(userMap.get(id));
				userMap.remove(id);
			}
			insertDataList.addAll(userMap.values());
			if(!updateDataList.isEmpty())intUpdate = this.updateUserWillNew(updateDataList);//更新操作
			if(!insertDataList.isEmpty())intInsert = this.insertUserWillNew(insertDataList);//插入操作
		}catch(Exception e){
			e.printStackTrace();
			log.error("upseruserWillNew数据时出错：" + e.getMessage(),e);
			return -1;
		}

		return intInsert + intUpdate;
	}
}
