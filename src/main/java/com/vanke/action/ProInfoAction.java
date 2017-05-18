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
import com.datacenter.bean.ProInfoBean;
import com.datacenter.initConfig.PreparedSql;

/**
 * pro_info数据处理类
 * Date: 2017-05-05
 * @author Leo
 *
 */
public class ProInfoAction extends BeanAction {
	protected Logger log = Logger.getLogger(ProInfoAction.class);
	public static final String PROINFO = "pro_info";

	@Override
	public int upsert(List<? extends DataBeanObject> beanList) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<ProInfoBean> proInfoList = (List<ProInfoBean>) beanList;
		Map<String,ProInfoBean> proInfoMap = new HashMap<String,ProInfoBean>();
		for(ProInfoBean proInfo : proInfoList){
			//如果出现数据中id为空的情况，则认为为无效数据，无视该数据
			if(proInfo.getId() != null && !"".equals(proInfo.getId())){
//				proInfoMap.put(proInfo.getId(),proInfo);
				proInfoMap.put(proInfo.getPro_code(), proInfo);
			}
		}
		int intUpdate = -1;
		int intInsert = -1;
		try{
			List<String> proInfoIDList = this.selectProInfo(proInfoList);
			List<ProInfoBean> updateDataList = new ArrayList<ProInfoBean>();//需要更新的数据
			List<ProInfoBean> insertDataList = new ArrayList<ProInfoBean>();//需要插入的数据
			for(String id : proInfoIDList){
				updateDataList.add(proInfoMap.get(id));
				proInfoMap.remove(id);
			}
			insertDataList.addAll(proInfoMap.values());
			if(!updateDataList.isEmpty())intUpdate = this.updateProInfo(updateDataList);//更新操作
			if(!insertDataList.isEmpty())intInsert = this.insertProInfo(insertDataList);//插入操作
		}catch(Exception e){
			e.printStackTrace();
			log.error("pro_info upsert数据时出错：" + e.getMessage(),e);
			return -1;
		}
		
		
		return intInsert + intUpdate;
	}
	/**
	 * 查询pro_info表中存在的ID
	 * @param proInfoList
	 * @return
	 */
	public List<String> selectProInfo(List<ProInfoBean> proInfoList){
		List<String> selectList = new ArrayList<String>();
		//拼接好查询SQL
		String strQuery = PreparedSql.SELECTPROINFOSQL.replace("#tableName#", PROINFO);
		StringBuilder strIDs = new StringBuilder();
		for(ProInfoBean proInfo : proInfoList){
//			strIDs.append(proInfo.getId() + ",");
			strIDs.append("'" + proInfo.getPro_code() + "',");
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
			log.error("pro_info查询出错：" + e.getMessage(),e);
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
	 * 更新数据到pro_info表中
	 * @param proInfoList
	 * @return
	 */
	public int updateProInfo(List<ProInfoBean> proInfoList){
		String strQuery = PreparedSql.UPDATEPROINFOSQL.replace("#tableName#", PROINFO);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(strQuery);
			conn.setAutoCommit(false);
			for(ProInfoBean proInfo : proInfoList){
				pstmt.setString(1, proInfo.getDev_code());
				pstmt.setString(2, proInfo.getDev_name());
				pstmt.setString(3, proInfo.getArea_code());
				pstmt.setString(4, proInfo.getArea_name());
				pstmt.setString(5, proInfo.getCity_code());
				pstmt.setString(6, proInfo.getCity_name());
//				pstmt.setString(7, proInfo.getPro_code());
				pstmt.setInt(7, Integer.valueOf(proInfo.getId()));			
				pstmt.setString(8, proInfo.getPro_name());
				pstmt.setString(9, proInfo.getHouse_class());
				pstmt.setString(10, proInfo.getManager_list());
				if(!"".equals(proInfo.getSync_flag()))pstmt.setInt(11, Integer.valueOf(proInfo.getSync_flag()));else pstmt.setNull(11, Types.INTEGER);
				if(!"".equals(proInfo.getSync_position()))pstmt.setInt(12, Integer.valueOf(proInfo.getSync_position()));else pstmt.setNull(12, Types.INTEGER);
				pstmt.setString(13, proInfo.getAgent_code());
				pstmt.setString(14, proInfo.getAgent_name());
				if(!"".equals(proInfo.getAlloc_flag()))pstmt.setInt(15, Integer.valueOf(proInfo.getAlloc_flag()));else pstmt.setNull(15, Types.INTEGER);
				pstmt.setString(16, proInfo.getCal_content());
				pstmt.setString(17, proInfo.getPro_dict());
				pstmt.setString(18, proInfo.getIntr());
				pstmt.setString(19, proInfo.getPic());
				if(!"".equals(proInfo.getState()))pstmt.setInt(20, Integer.valueOf(proInfo.getState()));else pstmt.setNull(20, Types.INTEGER);
				pstmt.setString(21, proInfo.getWechat_name());
				pstmt.setString(22, proInfo.getWechat_pwd());
				pstmt.setString(23, proInfo.getMsg_back_url());
				pstmt.setString(24, proInfo.getGet_two_pic_url());
				if(!"".equals(proInfo.getZc_days()))pstmt.setInt(25, Integer.valueOf(proInfo.getZc_days()));else pstmt.setNull(25, Types.INTEGER);
				if(!"".equals(proInfo.getYuqi_days()))pstmt.setInt(26, Integer.valueOf(proInfo.getYuqi_days()));else pstmt.setNull(26, Types.INTEGER);
				if(!"".equals(proInfo.getDai_gen_jin_days()))pstmt.setInt(27, Integer.valueOf(proInfo.getDai_gen_jin_days()));else pstmt.setNull(27, Types.INTEGER);
				if(!"".equals(proInfo.getGen_jin_days()))pstmt.setInt(28, Integer.valueOf(proInfo.getGen_jin_days()));else pstmt.setNull(28, Types.INTEGER);
				if(!"".equals(proInfo.getQian_yue_days()))pstmt.setInt(29, Integer.valueOf(proInfo.getQian_yue_days()));else pstmt.setNull(29, Types.INTEGER);
				pstmt.setString(30, proInfo.getPro_dict_new());
				if(!"".equals(proInfo.getRecover_flag()))pstmt.setInt(31, Integer.valueOf(proInfo.getRecover_flag()));else pstmt.setNull(31, Types.INTEGER);
				if(!"".equals(proInfo.getDai_gen_jin_recover_days()))pstmt.setInt(32, Integer.valueOf(proInfo.getDai_gen_jin_recover_days()));else pstmt.setNull(32, Types.INTEGER);
				if(!"".equals(proInfo.getGen_jin_recover_days()))pstmt.setInt(33, Integer.valueOf(proInfo.getGen_jin_recover_days()));else pstmt.setNull(33, Types.INTEGER);
				pstmt.setString(34, proInfo.getRule_dict());
				pstmt.setString(35, proInfo.getUser_alloc_dict());
				if(!"".equals(proInfo.getClose_flag()))pstmt.setInt(36, Integer.valueOf(proInfo.getClose_flag()));else pstmt.setNull(36, Types.INTEGER);
				if(!"".equals(proInfo.getHouse_sync_flag()))pstmt.setInt(37, Integer.valueOf(proInfo.getHouse_sync_flag()));else pstmt.setNull(37, Types.INTEGER);
				pstmt.setString(38, proInfo.getHouse_visible_flag());
				pstmt.setString(39, proInfo.getNotify_flag());
				if(!"".equals(proInfo.getWill_house_cnt()))pstmt.setInt(40, Integer.valueOf(proInfo.getWill_house_cnt()));else pstmt.setNull(40, Types.INTEGER);
				if(!"".equals(proInfo.getOpening_user_cnt()))pstmt.setInt(41, Integer.valueOf(proInfo.getOpening_user_cnt()));else pstmt.setNull(41, Types.INTEGER);
				pstmt.setString(42, proInfo.getDiscount());
				if(!"".equals(proInfo.getLove_cnt()))pstmt.setInt(43, Integer.valueOf(proInfo.getLove_cnt()));else pstmt.setNull(43, Types.INTEGER);
				if(!"".equals(proInfo.getUp_time()))pstmt.setInt(44, Integer.valueOf(proInfo.getUp_time()));else pstmt.setNull(44, Types.INTEGER);
				pstmt.setTimestamp(45, Timestamp.valueOf(proInfo.getLast_modify_on()));
//				pstmt.setInt(46, Integer.valueOf(proInfo.getId()));
				pstmt.setString(46, proInfo.getPro_code());
				pstmt.addBatch();
			}
			int[] count = pstmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.error("pro_info更新出错:" + e.getMessage(),e);
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
	 * 插入数据到pro_info表中
	 * @param proInfoList
	 * @return
	 */
	public int insertProInfo(List<ProInfoBean> proInfoList){
		String strQuery = PreparedSql.INSERTPROINFOSQL.replace("#tableName#", PROINFO);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(strQuery);
			conn.setAutoCommit(false);
			for(ProInfoBean proInfo : proInfoList){
				pstmt.setInt(1, Integer.valueOf(proInfo.getId()));
				pstmt.setString(2, proInfo.getDev_code());
				pstmt.setString(3, proInfo.getDev_name());
				pstmt.setString(4, proInfo.getArea_code());
				pstmt.setString(5, proInfo.getArea_name());
				pstmt.setString(6, proInfo.getCity_code());
				pstmt.setString(7, proInfo.getCity_name());
				pstmt.setString(8, proInfo.getPro_code());
				pstmt.setString(9, proInfo.getPro_name());
				pstmt.setString(10, proInfo.getHouse_class());
				pstmt.setString(11, proInfo.getManager_list());
				if(!"".equals(proInfo.getSync_flag()))pstmt.setInt(12, Integer.valueOf(proInfo.getSync_flag()));else pstmt.setNull(12, Types.INTEGER);
				if(!"".equals(proInfo.getSync_position()))pstmt.setInt(13, Integer.valueOf(proInfo.getSync_position()));else pstmt.setNull(13, Types.INTEGER);
				pstmt.setString(14, proInfo.getAgent_code());
				pstmt.setString(15, proInfo.getAgent_name());
				if(!"".equals(proInfo.getAlloc_flag()))pstmt.setInt(16, Integer.valueOf(proInfo.getAlloc_flag()));else pstmt.setNull(16, Types.INTEGER);
				pstmt.setString(17, proInfo.getCal_content());
				pstmt.setString(18, proInfo.getPro_dict());
				pstmt.setString(19, proInfo.getIntr());
				pstmt.setString(20, proInfo.getPic());
				if(!"".equals(proInfo.getState()))pstmt.setInt(21, Integer.valueOf(proInfo.getState()));else pstmt.setNull(21, Types.INTEGER);
				pstmt.setString(22, proInfo.getWechat_name());
				pstmt.setString(23, proInfo.getWechat_pwd());
				pstmt.setString(24, proInfo.getMsg_back_url());
				pstmt.setString(25, proInfo.getGet_two_pic_url());
				if(!"".equals(proInfo.getZc_days()))pstmt.setInt(26, Integer.valueOf(proInfo.getZc_days()));else pstmt.setNull(26, Types.INTEGER);
				if(!"".equals(proInfo.getYuqi_days()))pstmt.setInt(27, Integer.valueOf(proInfo.getYuqi_days()));else pstmt.setNull(27, Types.INTEGER);
				if(!"".equals(proInfo.getDai_gen_jin_days()))pstmt.setInt(28, Integer.valueOf(proInfo.getDai_gen_jin_days()));else pstmt.setNull(28, Types.INTEGER);
				if(!"".equals(proInfo.getGen_jin_days()))pstmt.setInt(29, Integer.valueOf(proInfo.getGen_jin_days()));else pstmt.setNull(29, Types.INTEGER);
				if(!"".equals(proInfo.getQian_yue_days()))pstmt.setInt(30, Integer.valueOf(proInfo.getQian_yue_days()));else pstmt.setNull(30, Types.INTEGER);
				pstmt.setString(31, proInfo.getPro_dict_new());
				if(!"".equals(proInfo.getRecover_flag()))pstmt.setInt(32, Integer.valueOf(proInfo.getRecover_flag()));else pstmt.setNull(32, Types.INTEGER);
				if(!"".equals(proInfo.getDai_gen_jin_recover_days()))pstmt.setInt(33, Integer.valueOf(proInfo.getDai_gen_jin_recover_days()));else pstmt.setNull(33, Types.INTEGER);
				if(!"".equals(proInfo.getGen_jin_recover_days()))pstmt.setInt(34, Integer.valueOf(proInfo.getGen_jin_recover_days()));else pstmt.setNull(34, Types.INTEGER);
				pstmt.setString(35, proInfo.getRule_dict());
				pstmt.setString(36, proInfo.getUser_alloc_dict());
				if(!"".equals(proInfo.getClose_flag()))pstmt.setInt(37, Integer.valueOf(proInfo.getClose_flag()));else pstmt.setNull(37, Types.INTEGER);
				if(!"".equals(proInfo.getHouse_sync_flag()))pstmt.setInt(38, Integer.valueOf(proInfo.getHouse_sync_flag()));else pstmt.setNull(38, Types.INTEGER);
				pstmt.setString(39, proInfo.getHouse_visible_flag());
				pstmt.setString(40, proInfo.getNotify_flag());
				if(!"".equals(proInfo.getWill_house_cnt()))pstmt.setInt(41, Integer.valueOf(proInfo.getWill_house_cnt()));else pstmt.setNull(41, Types.INTEGER);
				if(!"".equals(proInfo.getOpening_user_cnt()))pstmt.setInt(42, Integer.valueOf(proInfo.getOpening_user_cnt()));else pstmt.setNull(42, Types.INTEGER);
				pstmt.setString(43, proInfo.getDiscount());
				if(!"".equals(proInfo.getLove_cnt()))pstmt.setInt(44, Integer.valueOf(proInfo.getLove_cnt()));else pstmt.setNull(44, Types.INTEGER);
				if(!"".equals(proInfo.getUp_time()))pstmt.setInt(45, Integer.valueOf(proInfo.getUp_time()));else pstmt.setNull(45, Types.INTEGER);
				pstmt.setTimestamp(46, Timestamp.valueOf(proInfo.getLast_modify_on()));
				pstmt.addBatch();
			}
			int[] count = pstmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.error("pro_info插入出错:" + e.getMessage(),e);
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
