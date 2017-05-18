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
import com.datacenter.bean.TransBean;
import com.datacenter.initConfig.PreparedSql;
/**
 * 更新和新建Trans处理class
 * Date: 2017-05-03
 * @author christ
 *
 */
public class TransAction extends BeanAction {
	protected static Logger log = Logger.getLogger(TransAction.class);
	public static final String TRANS = "trans";//交易表表名
	/**
	 * 查询trans表
	 * @param transList
	 * @return
	 */
	public List<String> selectTrans(List<TransBean> transList){
		List<String> selectList = new ArrayList<String>();
		//拼接好查询SQL
		String strQuery = PreparedSql.SELECTSQL.replace("#tableName#", TRANS);
		StringBuilder strIDs = new StringBuilder();
		for(TransBean trans : transList){
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
			log.error("trans查询出错：" + e.getMessage(),e);
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
	 * 插入交易数据
	 * @param transList
	 * @return
	 */
	public int insertTrans(List<TransBean> transList){
		String strQuery = PreparedSql.INSERTTRANSSQL.replace("#tableName#", TRANS);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(strQuery);
			conn.setAutoCommit(false);
			for(TransBean trans : transList){
				pstmt.setInt(1, Integer.valueOf(trans.getId()));
				pstmt.setString(2, trans.getDev_code());
				pstmt.setString(3,trans.getDev_name());
				pstmt.setString(4,trans.getArea_code());
				pstmt.setString(5, trans.getArea_name());
				pstmt.setString(6, trans.getCity_code());
				pstmt.setString(7,trans.getCity_name());
				pstmt.setString(8,trans.getPro_code());
				pstmt.setString(9, trans.getPro_name());
				pstmt.setString(10, trans.getAgent_code());
				pstmt.setString(11,trans.getAgent_name());
				if(!"".equals(trans.getUser_id()))pstmt.setInt(12, Integer.valueOf(trans.getUser_id()));else pstmt.setNull(12, Types.INTEGER);
				pstmt.setString(13, trans.getUser_name());
				pstmt.setString(14, trans.getUser_cts_id());
				pstmt.setString(15,trans.getSales_phone());
				pstmt.setString(16,trans.getSales_name());
				pstmt.setString(17, trans.getSales_cts_id());
				if(!"".equals(trans.getHouse_id()))pstmt.setInt(18, Integer.valueOf(trans.getHouse_id()));else pstmt.setNull(18, Types.INTEGER);
				pstmt.setString(19,trans.getHouse_cts_id());
				if(!"".equals(trans.getHouse_class()))pstmt.setInt(20, Integer.valueOf(trans.getHouse_class()));else pstmt.setNull(20, Types.INTEGER);
				pstmt.setString(21, trans.getHouse_fullname());
				if(!"".equals(trans.getTran_type()))pstmt.setInt(22, Integer.valueOf(trans.getTran_type()));else pstmt.setNull(22, Types.INTEGER);
				if(!"".equals(trans.getPay_type()))pstmt.setInt(23, Integer.valueOf(trans.getPay_type()));else pstmt.setNull(23, Types.INTEGER);
				pstmt.setString(24,trans.getDisinfo());
//				if(!"".equals(trans.getAmount()))pstmt.setFloat(25, Float.valueOf(trans.getAmount()));else pstmt.setNull(25, Types.FLOAT);
				if(!"".equals(trans.getAmount()))pstmt.setDouble(25, Double.valueOf(trans.getAmount()));else pstmt.setNull(25, Types.DOUBLE);
				pstmt.setString(26, trans.getFile_list());
				if(!"".equals(trans.getNo_file_cnt()))pstmt.setInt(27, Integer.valueOf(trans.getNo_file_cnt()));else pstmt.setNull(27, Types.INTEGER);
				pstmt.setString(28,trans.getRemark());
				if(!"".equals(trans.getTran_time()))pstmt.setInt(29, Integer.valueOf(trans.getTran_time()));else pstmt.setNull(29, Types.INTEGER);
				pstmt.setString(30, trans.getCts_sid());
				if(!"".equals(trans.getUptime()))pstmt.setInt(31, Integer.valueOf(trans.getUptime()));else pstmt.setNull(31, Types.INTEGER);
				pstmt.setString(32,trans.getTrans_cts_id());
				if(!"".equals(trans.getClose_state()))pstmt.setInt(33, Integer.valueOf(trans.getClose_state()));else pstmt.setNull(33, Types.INTEGER);
				pstmt.setString(34, trans.getClose_reason());
				pstmt.setString(35,trans.getOrder_type());
//				if(!"".equals(trans.getHt_amount()))pstmt.setFloat(36, Float.valueOf(trans.getHt_amount()));else pstmt.setFloat(36, Types.FLOAT);
				if(!"".equals(trans.getHt_amount()))pstmt.setDouble(36, Double.valueOf(trans.getHt_amount()));else pstmt.setFloat(36, Types.DOUBLE);
				if(!"".equals(trans.getSp_state()))pstmt.setInt(37, Integer.valueOf(trans.getSp_state()));else pstmt.setNull(37, Types.INTEGER);
				pstmt.setString(38, trans.getSp_human());
				pstmt.setString(39,trans.getSp_opinion());
				if(!"".equals(trans.getSp_time()))pstmt.setInt(40, Integer.valueOf(trans.getSp_time()));else pstmt.setNull(40, Types.INTEGER);
				if(!"".equals(trans.getSource_type()))pstmt.setInt(41, Integer.valueOf(trans.getSource_type()));else pstmt.setNull(41, Types.INTEGER);
				pstmt.setTimestamp(42,  Timestamp.valueOf(trans.getLast_modify_on()));
				pstmt.addBatch();
			}
			int[] count = pstmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.error("trans插入出错：" + e.getMessage(),e);
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
	 * 更新用户数据
	 * @param transList
	 * @return
	 */
	public int updateTrans(List<TransBean> transList){
		String strQuery = PreparedSql.UPDATETRANSSQL.replace("#tableName#", TRANS);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(strQuery);
			conn.setAutoCommit(false);
			for (TransBean trans : transList) {
				pstmt.setString(1,  trans.getDev_code());
				pstmt.setString(2, trans.getDev_name());
				pstmt.setString(3, trans.getArea_code());
				pstmt.setString(4,  trans.getArea_name());
				pstmt.setString(5,  trans.getCity_code());
				pstmt.setString(6, trans.getCity_name());
				pstmt.setString(7, trans.getPro_code());
				pstmt.setString(8,  trans.getPro_name());
				pstmt.setString(9,  trans.getAgent_code());
				pstmt.setString(10, trans.getAgent_name());
				if(!"".equals(trans.getUser_id()))pstmt.setInt(11, Integer.valueOf(trans.getUser_id()));else pstmt.setNull(11, Types.INTEGER);
				pstmt.setString(12,  trans.getUser_name());
				pstmt.setString(13,  trans.getUser_cts_id());
				pstmt.setString(14, trans.getSales_phone());
				pstmt.setString(15, trans.getSales_name());
				pstmt.setString(16,  trans.getSales_cts_id());
				if(!"".equals(trans.getHouse_id()))pstmt.setInt(17, Integer.valueOf(trans.getHouse_id()));else pstmt.setNull(17, Types.INTEGER);
				pstmt.setString(18, trans.getHouse_cts_id());
				if(!"".equals(trans.getHouse_class()))pstmt.setInt(19, Integer.valueOf(trans.getHouse_class()));else pstmt.setNull(19, Types.INTEGER);
				pstmt.setString(20,  trans.getHouse_fullname());
				if(!"".equals(trans.getTran_type()))pstmt.setInt(21, Integer.valueOf(trans.getTran_type()));else pstmt.setNull(21, Types.INTEGER);
				if(!"".equals(trans.getPay_type()))pstmt.setInt(22, Integer.valueOf(trans.getPay_type()));else pstmt.setNull(22, Types.INTEGER);
				pstmt.setString(23, trans.getDisinfo());
//				if(!"".equals(trans.getAmount()))pstmt.setFloat(24, Float.valueOf(trans.getAmount()));else pstmt.setNull(24, Types.FLOAT);
				if(!"".equals(trans.getAmount()))pstmt.setDouble(24, Double.valueOf(trans.getAmount()));else pstmt.setNull(24, Types.DOUBLE);
				pstmt.setString(25,  trans.getFile_list());
				if(!"".equals(trans.getNo_file_cnt()))pstmt.setInt(26, Integer.valueOf(trans.getNo_file_cnt()));else pstmt.setNull(26, Types.INTEGER);
				pstmt.setString(27, trans.getRemark());
				if(!"".equals(trans.getTran_time()))pstmt.setInt(28, Integer.valueOf(trans.getTran_time()));else pstmt.setNull(28, Types.INTEGER);
				pstmt.setString(29,  trans.getCts_sid());
				if(!"".equals(trans.getUptime()))pstmt.setInt(30, Integer.valueOf(trans.getUptime()));else pstmt.setNull(30, Types.INTEGER);
				pstmt.setString(31, trans.getTrans_cts_id());
				if(!"".equals(trans.getClose_state()))pstmt.setInt(32, Integer.valueOf(trans.getClose_state()));else pstmt.setNull(32, Types.INTEGER);
				pstmt.setString(33,  trans.getClose_reason());
				pstmt.setString(34, trans.getOrder_type());
//				if(!"".equals(trans.getHt_amount()))pstmt.setFloat(35, Float.valueOf(trans.getHt_amount()));else pstmt.setNull(35, Types.FLOAT);
				if(!"".equals(trans.getHt_amount()))pstmt.setDouble(35, Double.valueOf(trans.getHt_amount()));else pstmt.setNull(35, Types.DOUBLE);
				if(!"".equals(trans.getSp_state()))pstmt.setInt(36, Integer.valueOf(trans.getSp_state()));else pstmt.setNull(36, Types.INTEGER);
				pstmt.setString(37,  trans.getSp_human());
				pstmt.setString(38, trans.getSp_opinion());
				if(!"".equals(trans.getSp_time()))pstmt.setInt(39, Integer.valueOf(trans.getSp_time()));else pstmt.setNull(39, Types.INTEGER);
				if(!"".equals(trans.getSource_type()))pstmt.setInt(40, Integer.valueOf(trans.getSource_type()));else pstmt.setNull(40, Types.INTEGER);
				pstmt.setTimestamp(41,  Timestamp.valueOf(trans.getLast_modify_on()));
				pstmt.setInt(42,  Integer.valueOf(trans.getId()));
				pstmt.addBatch();
			}
			int[] count = pstmt.executeBatch();
			conn.commit();
		} catch (Exception e) {
			log.error("trans更新出错：" + e.getMessage(),e);
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
		List<TransBean> transList = (List<TransBean>) beanList;
		Map<String,TransBean> userMap = new HashMap<String,TransBean>();//用户数据信息MAP
		for(TransBean trans: transList){
			//如果出现数据中id为空的情况，则认为为无效数据，无视该数据
			if(trans.getId() != null && !"".equals(trans.getId())){
				userMap.put(trans.getId(),trans);
			}
		}
		int intUpdate = -1;
		int intInsert = -1;
		try{
			//根据ID查找用户表中的数据，找到已经存在系统中的数据id
			List<String> transIDList = this.selectTrans(transList);
			List<TransBean> updateDataList = new ArrayList<TransBean>();//需要更新的用户数据
			List<TransBean> insertDataList = new ArrayList<TransBean>();//需要插入的用户数据
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
			log.error("upserttrans交易数据时出错：" + e.getMessage(),e);
			return -1;
		}


		return intInsert + intUpdate;
	}
	

}
