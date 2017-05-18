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
import com.datacenter.bean.SalesBean;
import com.datacenter.initConfig.PreparedSql;
/**
 * 更新和新建Trans处理class
 * Date: 2017-05-03
 * @author christ
 *
 */
public class SalesAction extends BeanAction {
	protected static Logger log = Logger.getLogger(SalesBean.class);
	public static final String SALES = "sales";//交易表表名
	/**
	 * 查询表
	 * @param transList
	 * @return
	 */
	public List<String> selectTrans(List<SalesBean> SalesList){
		List<String> selectList = new ArrayList<String>();
		//拼接好查询SQL
		String strQuery = PreparedSql.SELECTSQL.replace("#tableName#", SALES);
		StringBuilder strIDs = new StringBuilder();
		for(SalesBean trans : SalesList){
			strIDs.append(trans.getId() + ",");
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
			log.error("Sales查询出错：" + e.getMessage(),e);
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
	 * 插入数据
	 * @param transList
	 * @return
	 */
	public int insertTrans(List<SalesBean> SalesList){
		String strQuery = PreparedSql.INSERTSALESSQL.replace("#tableName#", SALES);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(strQuery);
			conn.setAutoCommit(false);
			for(SalesBean sales : SalesList){
				pstmt.setInt(1, Integer.valueOf(sales.getId()));
				pstmt.setString(2, sales.getDev_code());
				pstmt.setString(3, sales.getDev_name());
				pstmt.setString(4, sales.getPhone());
				pstmt.setString(5, sales.getName());
				pstmt.setString(6, sales.getPwd());
				if(!"".equals(sales.getUser_type()))pstmt.setInt(7, Integer.valueOf(sales.getUser_type()));else pstmt.setNull(7, Types.INTEGER);
				if(!"".equals(sales.getSex()))pstmt.setInt(8, Integer.valueOf(sales.getSex()));else pstmt.setNull(8, Types.INTEGER);
				pstmt.setString(9,  sales.getHeadpic());
				pstmt.setString(10, sales.getIntr());
				pstmt.setString(11, sales.getLevel());
				if(!"".equals(sales.getIntegral()))pstmt.setInt(12, Integer.valueOf(sales.getIntegral()));else pstmt.setNull(12, Types.INTEGER);
				if(!"".equals(sales.getSys_state()))pstmt.setInt(13, Integer.valueOf(sales.getSys_state()));else pstmt.setNull(13, Types.INTEGER);
				if(!"".equals(sales.getAct_state()))pstmt.setInt(14, Integer.valueOf(sales.getAct_state()));else pstmt.setNull(14, Types.INTEGER);
				if(!"".equals(sales.getClient_type()))pstmt.setInt(15, Integer.valueOf(sales.getClient_type()));else pstmt.setNull(15, Types.INTEGER);
				pstmt.setString(16, sales.getPush_id());
				if(!"".equals(sales.getRegtime()))pstmt.setInt(17, Integer.valueOf(sales.getRegtime()));else pstmt.setNull(17, Types.INTEGER);
				pstmt.setString(18, sales.getTwo_pic_url());
//				if(!"".equals(sales.getMonth_target_amount()))pstmt.setFloat(19, Float.valueOf(sales.getMonth_target_amount()));else pstmt.setNull(19, Types.FLOAT);
				if(!"".equals(sales.getMonth_target_amount()))pstmt.setDouble(19, Double.valueOf(sales.getMonth_target_amount()));else pstmt.setNull(19, Types.DOUBLE);
				if(!"".equals(sales.getMonth_target_number()))pstmt.setInt(20, Integer.valueOf(sales.getMonth_target_number()));else pstmt.setNull(20, Types.INTEGER);
				pstmt.setString(21, sales.getAgent_code());
				pstmt.setString(22, sales.getAgent_name());
				if(!"".equals(sales.getDel_flag()))pstmt.setInt(23, Integer.valueOf(sales.getDel_flag()));else pstmt.setNull(23, Types.INTEGER);
				if(!"".equals(sales.getIm_flag()))pstmt.setInt(24, Integer.valueOf(sales.getIm_flag()));else pstmt.setNull(24, Types.INTEGER);
				if(!"".equals(sales.getIm_state()))pstmt.setInt(25, Integer.valueOf(sales.getIm_state()));else pstmt.setNull(25, Types.INTEGER);
				pstmt.setTimestamp(26, Timestamp.valueOf(sales.getLast_modify_on()));
				pstmt.addBatch();
			}
			int[] count = pstmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.error("Sales插入出错：" + e.getMessage(),e);
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
	/**
	 * 更新数据
	 * @param transList
	 * @return
	 */
	public int updateTrans(List<SalesBean> SalesList){
		String strQuery = PreparedSql.UPDATESALESSQL.replace("#tableName#", SALES);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(strQuery);
			conn.setAutoCommit(false);
			for (SalesBean sales : SalesList) {
				pstmt.setString(1, sales.getDev_code());
				pstmt.setString(2, sales.getDev_name());
				pstmt.setString(3, sales.getPhone());
				pstmt.setString(4, sales.getName());
				pstmt.setString(5, sales.getPwd());
				if(!"".equals(sales.getUser_type()))pstmt.setInt(6, Integer.valueOf(sales.getUser_type()));else pstmt.setNull(6, Types.INTEGER);
				if(!"".equals(sales.getSex()))pstmt.setInt(7, Integer.valueOf(sales.getSex()));else pstmt.setNull(7, Types.INTEGER);
				pstmt.setString(8, sales.getHeadpic());
				pstmt.setString(9, sales.getIntr());
				pstmt.setString(10,sales.getLevel());
				if(!"".equals(sales.getIntegral()))pstmt.setInt(11, Integer.valueOf(sales.getIntegral()));else pstmt.setNull(11, Types.INTEGER);
				if(!"".equals(sales.getSys_state()))pstmt.setInt(12, Integer.valueOf(sales.getSys_state()));else pstmt.setNull(12, Types.INTEGER);
				if(!"".equals(sales.getAct_state()))pstmt.setInt(13, Integer.valueOf(sales.getAct_state()));else pstmt.setNull(13, Types.INTEGER);
				if(!"".equals(sales.getClient_type()))pstmt.setInt(14, Integer.valueOf(sales.getClient_type()));else pstmt.setNull(14, Types.INTEGER);
				pstmt.setString(15, sales.getPush_id());
				if(!"".equals(sales.getRegtime()))pstmt.setInt(16, Integer.valueOf(sales.getRegtime()));else pstmt.setNull(16, Types.INTEGER);
				pstmt.setString(17, sales.getTwo_pic_url());
//				if(!"".equals(sales.getMonth_target_amount()))pstmt.setFloat(18,Float.valueOf(sales.getMonth_target_amount()));else pstmt.setNull(18, Types.FLOAT);
				if(!"".equals(sales.getMonth_target_amount()))pstmt.setDouble(18, Double.valueOf(sales.getMonth_target_amount()));else pstmt.setNull(18, Types.DOUBLE);
				if(!"".equals(sales.getMonth_target_number()))pstmt.setInt(19, Integer.valueOf(sales.getMonth_target_number()));else pstmt.setNull(19, Types.INTEGER);
				pstmt.setString(20, sales.getAgent_code());
				pstmt.setString(21, sales.getAgent_name());
				if(!"".equals(sales.getDel_flag()))pstmt.setInt(22, Integer.valueOf(sales.getDel_flag()));else pstmt.setNull(22, Types.INTEGER);
				if(!"".equals(sales.getIm_flag()))pstmt.setInt(23, Integer.valueOf(sales.getIm_flag()));else pstmt.setNull(23, Types.INTEGER);
				if(!"".equals(sales.getIm_state()))pstmt.setInt(24, Integer.valueOf(sales.getIm_state()));else pstmt.setNull(24, Types.INTEGER);
				pstmt.setTimestamp(25, Timestamp.valueOf(sales.getLast_modify_on()));
				pstmt.setInt(26, Integer.valueOf(sales.getId()));
				pstmt.addBatch();
			}
			int[] count = pstmt.executeBatch();
			conn.commit();
		} catch (Exception e) {
			log.error("Sales更新出错：" + e.getMessage(),e);
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

	public int upsert(List<? extends DataBeanObject> beanList) {
		@SuppressWarnings("unchecked")
		List<SalesBean> SalesList = (List<SalesBean>) beanList;
		Map<String,SalesBean> userMap = new HashMap<String,SalesBean>();//用户数据信息MAP
		for(SalesBean sales: SalesList){
			//如果出现数据中id为空的情况，则认为为无效数据，无视该数据
			if(sales.getId() != null && !"".equals(sales.getId())){
				userMap.put(sales.getId(),sales);
			}
		}
		int intUpdate = -1;
		int intInsert = -1;
		try{
			//根据ID查找用户表中的数据，找到已经存在系统中的数据id
			List<String> transIDList = this.selectTrans(SalesList);
			List<SalesBean> updateDataList = new ArrayList<SalesBean>();//需要更新的用户数据
			List<SalesBean> insertDataList = new ArrayList<SalesBean>();//需要插入的用户数据
			for(String id : transIDList){
				updateDataList.add(userMap.get(id));
				userMap.remove(id);
			}
			insertDataList.addAll(userMap.values());
			if(updateDataList.size()!=0){
				intUpdate = this.updateTrans(updateDataList);//更新操作
			}
			if(insertDataList.size()!=0){
				intInsert = this.insertTrans(insertDataList);//插入操作
			}

		}catch(Exception e){
			e.printStackTrace();
			log.error("upsertSales交易数据时出错：" + e.getMessage(),e);
			return -1;
		}


		return intInsert + intUpdate;
	}
}
