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
import com.datacenter.bean.UserCirBean;
import com.datacenter.initConfig.PreparedSql;

/**
 * user_cir处理类
 * Date: 2017-05-05
 * @author Leo
 *
 */
public class UserCirAction extends BeanAction {
	protected static Logger log = Logger.getLogger(UserCirAction.class);
	public static final String USERCIR = "user_cir";

	@Override
	public int upsert(List<? extends DataBeanObject> beanList) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<UserCirBean> userCirList = (List<UserCirBean>) beanList;
		Map<String,UserCirBean> userCirMap = new HashMap<String,UserCirBean>();
		for(UserCirBean userCir : userCirList){
			//如果出现数据中id为空的情况，则认为为无效数据，无视该数据
			if(userCir.getId() != null && !"".equals(userCir.getId())){
				userCirMap.put(userCir.getId(),userCir);
			}
		}
		int intUpdate = -1;
		int intInsert = -1;
		try{
			List<String> userCirIDList = this.selectUserCir(userCirList);
			List<UserCirBean> updateDataList = new ArrayList<UserCirBean>();//需要更新的数据
			List<UserCirBean> insertDataList = new ArrayList<UserCirBean>();//需要插入的数据
			for(String id : userCirIDList){
				updateDataList.add(userCirMap.get(id));
				userCirMap.remove(id);
			}
			insertDataList.addAll(userCirMap.values());
			if(!updateDataList.isEmpty())intUpdate = this.updateUserCir(updateDataList);//更新操作
			if(!insertDataList.isEmpty())intInsert = this.insertUserCir(insertDataList);//插入操作
		}catch(Exception e){
			e.printStackTrace();
			log.error("user_cir upsert数据时出错：" + e.getMessage(),e);
			return -1;
		}
		
		
		return intInsert + intUpdate;
	}
	/**
	 * 查询user_cir中存在的ID
	 * @param userCirList
	 * @return
	 */
	public List<String> selectUserCir(List<UserCirBean> userCirList){
		List<String> selectList = new ArrayList<String>();
		//拼接好查询SQL
		String strQuery = PreparedSql.SELECTSQL.replace("#tableName#", USERCIR);
		StringBuilder strIDs = new StringBuilder();
		for(UserCirBean userCir : userCirList){
			strIDs.append(userCir.getId() + ",");
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
			log.error("user_cir查询出错：" + e.getMessage(),e);
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
	 * 更新指定数据
	 * @param userCirList
	 * @return
	 */
	public int updateUserCir(List<UserCirBean> userCirList){
		String strQuery = PreparedSql.UPDATEUSERCIRSQL.replace("#tableName#", USERCIR);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(strQuery);
			conn.setAutoCommit(false);
			for(UserCirBean userCir : userCirList){
				pstmt.setString(1, userCir.getDev_code());
				pstmt.setString(2, userCir.getDev_name());
				pstmt.setString(3, userCir.getArea_code());
				pstmt.setString(4, userCir.getArea_name());
				pstmt.setString(5, userCir.getCity_code());
				pstmt.setString(6, userCir.getCity_name());
				pstmt.setString(7, userCir.getPro_code());
				pstmt.setString(8, userCir.getPro_name());
				if(!"".equals(userCir.getUser_id()))pstmt.setInt(9, Integer.valueOf(userCir.getUser_id()));else pstmt.setNull(9, Types.INTEGER);
				pstmt.setString(10, userCir.getUser_cts_id());
				pstmt.setString(11, userCir.getOp_sales());
				pstmt.setString(12, userCir.getTo_sales());
				pstmt.setString(13, userCir.getContext());
				pstmt.setString(14, userCir.getRemark());
				if(!"".equals(userCir.getCir_time()))pstmt.setInt(15, Integer.valueOf(userCir.getCir_time()));else pstmt.setNull(15, Types.INTEGER);
				pstmt.setString(16, userCir.getSales_cts_id());
				pstmt.setTimestamp(17, Timestamp.valueOf(userCir.getLast_modify_on()));
				pstmt.setInt(18, Integer.valueOf(userCir.getId()));
				pstmt.addBatch();
			}
			int[] count = pstmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.error("user_cir更新出错:" + e.getMessage(),e);
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
	 * 插入指定数据
	 * @param userCirList
	 * @return
	 */
	public int insertUserCir(List<UserCirBean> userCirList){
		String strQuery = PreparedSql.INSERTUSERCIRSQL.replace("#tableName#", USERCIR);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(strQuery);
			conn.setAutoCommit(false);
			for(UserCirBean userCir : userCirList){
				pstmt.setInt(1, Integer.valueOf(userCir.getId()));
				pstmt.setString(2, userCir.getDev_code());
				pstmt.setString(3, userCir.getDev_name());
				pstmt.setString(4, userCir.getArea_code());
				pstmt.setString(5, userCir.getArea_name());
				pstmt.setString(6, userCir.getCity_code());
				pstmt.setString(7, userCir.getCity_name());
				pstmt.setString(8, userCir.getPro_code());
				pstmt.setString(9, userCir.getPro_name());
				if(!"".equals(userCir.getUser_id()))pstmt.setInt(10, Integer.valueOf(userCir.getUser_id()));else pstmt.setNull(10, Types.INTEGER);
				pstmt.setString(11, userCir.getUser_cts_id());
				pstmt.setString(12, userCir.getOp_sales());
				pstmt.setString(13, userCir.getTo_sales());
				pstmt.setString(14, userCir.getContext());
				pstmt.setString(15, userCir.getRemark());
				if(!"".equals(userCir.getCir_time()))pstmt.setInt(16, Integer.valueOf(userCir.getCir_time()));else pstmt.setNull(16, Types.INTEGER);
				pstmt.setString(17, userCir.getSales_cts_id());
				pstmt.setTimestamp(18, Timestamp.valueOf(userCir.getLast_modify_on()));
				pstmt.addBatch();
			}
			int[] count = pstmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.error("user_cir插入出错:" + e.getMessage(),e);
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
