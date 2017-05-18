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
import com.datacenter.bean.UserInfoBean;
import com.datacenter.initConfig.PreparedSql;

/**
 * 更新和新建User_Info处理class
 * Date: 2017-05-03
 * @author Leo
 *
 */
public class UserInfoAction extends BeanAction {
	protected static Logger log = Logger.getLogger(UserInfoAction.class);
	public static final String USERINFO = "user_info";//用户表名
	
	@Override
	public int upsert(List<? extends DataBeanObject> beanList) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<UserInfoBean> userList = (List<UserInfoBean>) beanList;
		Map<String,UserInfoBean> userMap = new HashMap<String,UserInfoBean>();//用户数据信息MAP
		for(UserInfoBean user : userList){
			//如果出现数据中id为空的情况，则认为为无效数据，无视该数据
			if(user.getId() != null && !"".equals(user.getId())){
				userMap.put(user.getId(),user);
			}
		}
		int intUpdate = -1;
		int intInsert = -1;
		try{
			//根据ID查找用户表中的数据，找到已经存在系统中的数据id
			List<String> userIDList = this.selectUserInfo(userList);
			List<UserInfoBean> updateDataList = new ArrayList<UserInfoBean>();//需要更新的用户数据
			List<UserInfoBean> insertDataList = new ArrayList<UserInfoBean>();//需要插入的用户数据
			for(String id : userIDList){
				updateDataList.add(userMap.get(id));
				userMap.remove(id);
			}
			insertDataList.addAll(userMap.values());
			if(!updateDataList.isEmpty())intUpdate = this.updateUserInfo(updateDataList);//更新操作
			if(!insertDataList.isEmpty())intInsert = this.insertUserInfo(insertDataList);//插入操作
		}catch(Exception e){
			e.printStackTrace();
			log.error("upsert用户数据时出错：" + e.getMessage(),e);
			return -1;
		}
		
		
		return intInsert + intUpdate;
	}

	/**
	 * 查询用户表
	 * @param userList
	 * @return
	 */
	public List<String> selectUserInfo(List<UserInfoBean> userList){
		List<String> selectList = new ArrayList<String>();
		//拼接好查询SQL
		String strQuery = PreparedSql.SELECTSQL.replace("#tableName#", USERINFO);
		StringBuilder strIDs = new StringBuilder();
		for(UserInfoBean user : userList){
			strIDs.append(user.getId() + ",");
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
			log.error("user_info查询出错：" + e.getMessage(),e);
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
	 * 更新用户数据
	 * @param userList
	 * @return
	 */
	public int updateUserInfo(List<UserInfoBean> userList){
		String strQuery = PreparedSql.UPDATEUSERINFOSQL.replace("#tableName#", USERINFO);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(strQuery);
			conn.setAutoCommit(false);
			for(UserInfoBean user : userList){
				pstmt.setString(1, user.getUser_cts_id());
				pstmt.setString(2, user.getDev_code());
				pstmt.setString(3, user.getDev_name());
				pstmt.setString(4, user.getArea_code());
				pstmt.setString(5, user.getArea_name());
				pstmt.setString(6, user.getCity_code());
				pstmt.setString(7, user.getCity_name());
				pstmt.setString(8, user.getPro_code());
				pstmt.setString(9, user.getPro_name());
				pstmt.setString(10, user.getAgent_code());
				pstmt.setString(11, user.getAgent_code_name());
				pstmt.setString(12, user.getSales_phone());
				pstmt.setString(13, user.getSales_cts_id());
				pstmt.setString(14, user.getSales_name());
				pstmt.setString(15, user.getName());
				pstmt.setString(16, user.getPhone());
				pstmt.setString(17, user.getPhone1());
				pstmt.setString(18, user.getPhone2());
				pstmt.setString(19, user.getNick());
				pstmt.setString(20, user.getHeadpic());
				pstmt.setString(21, user.getWechat());
				pstmt.setString(22, user.getSex());
				pstmt.setString(23, user.getAge());
				pstmt.setString(24, user.getM_1());
				pstmt.setString(25, user.getM_2());
				pstmt.setString(26, user.getM_3());
				pstmt.setString(27, user.getM_4());
				pstmt.setString(28, user.getM_5());
				pstmt.setString(29, user.getM_6());
				pstmt.setString(30, user.getM_7());
				pstmt.setString(31, user.getM_8());
				pstmt.setString(32, user.getM_9());
				pstmt.setString(33, user.getM_10());
				pstmt.setString(34, user.getM_11());
				pstmt.setString(35, user.getM_12());
				pstmt.setString(36, user.getM_13());
				pstmt.setString(37, user.getM_14());
				pstmt.setString(38, user.getM_15());
				pstmt.setString(39, user.getM_16());
				if(!"".equals(user.getE_1()))pstmt.setInt(40, Integer.valueOf(user.getE_1()));else pstmt.setNull(40, Types.INTEGER);
				if(!"".equals(user.getE_2()))pstmt.setInt(41, Integer.valueOf(user.getE_2()));else pstmt.setNull(41, Types.INTEGER);
				if(!"".equals(user.getE_3()))pstmt.setInt(42, Integer.valueOf(user.getE_3()));else pstmt.setNull(42, Types.INTEGER);
				if(!"".equals(user.getE_4()))pstmt.setInt(43, Integer.valueOf(user.getE_4()));else pstmt.setNull(43, Types.INTEGER);
				if(!"".equals(user.getE_5()))pstmt.setInt(44, Integer.valueOf(user.getE_5()));else pstmt.setNull(44, Types.INTEGER);
				if(!"".equals(user.getE_6()))pstmt.setInt(45, Integer.valueOf(user.getE_6()));else pstmt.setNull(45, Types.INTEGER);
				if(!"".equals(user.getE_7()))pstmt.setInt(46, Integer.valueOf(user.getE_7()));else pstmt.setNull(46, Types.INTEGER);
				if(!"".equals(user.getE_8()))pstmt.setInt(47, Integer.valueOf(user.getE_8()));else pstmt.setNull(47, Types.INTEGER);
				if(!"".equals(user.getE_9()))pstmt.setInt(48, Integer.valueOf(user.getE_9()));else pstmt.setNull(48, Types.INTEGER);
				if(!"".equals(user.getE_10()))pstmt.setInt(49, Integer.valueOf(user.getE_10()));else pstmt.setNull(49, Types.INTEGER);
				if(!"".equals(user.getWsd()))pstmt.setInt(50, Integer.valueOf(user.getWsd()));else pstmt.setNull(50, Types.INTEGER);
				if(!"".equals(user.getWork_addr()))pstmt.setInt(51, Integer.valueOf(user.getWork_addr()));else pstmt.setNull(51, Types.INTEGER);
				if(!"".equals(user.getLive_addr()))pstmt.setInt(52, Integer.valueOf(user.getLive_addr()));else pstmt.setNull(52, Types.INTEGER);
				if(!"".equals(user.getVist_cnt()))pstmt.setInt(53, Integer.valueOf(user.getVist_cnt()));else pstmt.setNull(53, Types.INTEGER);
				if(!"".equals(user.getRectime()))pstmt.setInt(54, Integer.valueOf(user.getRectime()));else pstmt.setNull(54, Types.INTEGER);
				if(!"".equals(user.getUptime()))pstmt.setInt(55, Integer.valueOf(user.getUptime()));else pstmt.setNull(55, Types.INTEGER);
				if(!"".equals(user.getLast_vist_time()))pstmt.setInt(56, Integer.valueOf(user.getLast_vist_time()));else pstmt.setNull(56, Types.INTEGER);
				if(!"".equals(user.getFirst_daofang_time()))pstmt.setInt(57, Integer.valueOf(user.getFirst_daofang_time()));else pstmt.setNull(57, Types.INTEGER);
				if(!"".equals(user.getLast_daofang_time()))pstmt.setInt(58, Integer.valueOf(user.getLast_daofang_time()));else pstmt.setNull(58, Types.INTEGER);
				if(!"".equals(user.getFirst_huifang_time()))pstmt.setInt(59, Integer.valueOf(user.getFirst_huifang_time()));else pstmt.setNull(59, Types.INTEGER);
				if(!"".equals(user.getLast_huifang_time()))pstmt.setInt(60, Integer.valueOf(user.getLast_huifang_time()));else pstmt.setNull(60, Types.INTEGER);
				if(!"".equals(user.getLast_tran_time()))pstmt.setInt(61, Integer.valueOf(user.getLast_tran_time()));else pstmt.setNull(61, Types.INTEGER);
				pstmt.setString(62, user.getTran_type_tag());
				pstmt.setString(63, user.getTran_housename_tag());
				pstmt.setString(64, user.getWechat_openid());
				pstmt.setString(65, user.getMsg_back_url());
				if(!"".equals(user.getWechat_last_time()))pstmt.setInt(66, Integer.valueOf(user.getWechat_last_time()));else pstmt.setNull(66, Types.INTEGER);
				if(!"".equals(user.getYuyue_date()))pstmt.setInt(67, Integer.valueOf(user.getYuyue_date()));else pstmt.setNull(67, Types.INTEGER);
				if(!"".equals(user.getNew_flag()))pstmt.setInt(68, Integer.valueOf(user.getNew_flag()));else pstmt.setNull(68, Types.INTEGER);
				if(!"".equals(user.getNew_time()))pstmt.setInt(69, Integer.valueOf(user.getNew_time()));else pstmt.setNull(69, Types.INTEGER);
				pstmt.setString(70, user.getAddsource());
				pstmt.setString(71, user.getAgent_name());
				pstmt.setString(72, user.getAgent_phone());
				pstmt.setString(73, user.getRemark());
				pstmt.setString(74, user.getRemark_url());
				if(!"".equals(user.getSee_flag()))pstmt.setInt(75, Integer.valueOf(user.getSee_flag()));else pstmt.setNull(75, Types.INTEGER);
				if(!"".equals(user.getDel_flag()))pstmt.setInt(76, Integer.valueOf(user.getDel_flag()));else pstmt.setNull(76, Types.INTEGER);
				if(!"".equals(user.getAlloc_flag()))pstmt.setInt(77, Integer.valueOf(user.getAlloc_flag()));else pstmt.setNull(77, Types.INTEGER);
				pstmt.setString(78, user.getToday_date());
				pstmt.setString(79, user.getTomorrow_date());
				if(!"".equals(user.getSync_state()))pstmt.setInt(80, Integer.valueOf(user.getSync_state()));else pstmt.setNull(80, Types.INTEGER);
				if(!"".equals(user.getSync_cnt()))pstmt.setInt(81, Integer.valueOf(user.getSync_cnt()));else pstmt.setNull(81, Types.INTEGER);
				pstmt.setString(82, user.getSync_msg());
				if(!"".equals(user.getSync_time()))pstmt.setInt(83, Integer.valueOf(user.getSync_time()));else pstmt.setNull(83, Types.INTEGER);
				pstmt.setString(84, user.getHome_addr_info());
				if(!"".equals(user.getCard_type()))pstmt.setInt(85, Integer.valueOf(user.getCard_type()));else pstmt.setNull(85, Types.INTEGER);
				pstmt.setString(86, user.getCard_id());
				if(!"".equals(user.getBuy_days()))pstmt.setInt(87, Integer.valueOf(user.getBuy_days()));else pstmt.setNull(87, Types.INTEGER);
				if(!"".equals(user.getRengou_time()))pstmt.setInt(88, Integer.valueOf(user.getRengou_time()));else pstmt.setNull(88, Types.INTEGER);
				if(!"".equals(user.getQianyue_time()))pstmt.setInt(89, Integer.valueOf(user.getQianyue_time()));else pstmt.setNull(89, Types.INTEGER);
				pstmt.setString(90, user.getM_17());
				pstmt.setString(91, user.getM_18());
				pstmt.setString(92, user.getM_19());
				pstmt.setString(93, user.getM_20());
				pstmt.setString(94, user.getM_21());
				pstmt.setString(95, user.getM_22());
				pstmt.setString(96, user.getM_23());
				pstmt.setString(97, user.getM_24());
				pstmt.setString(98, user.getM_25());
				pstmt.setString(99, user.getM_26());
				pstmt.setString(100, user.getM_27());
				pstmt.setString(101, user.getM_28());
				pstmt.setString(102, user.getM_29());
				pstmt.setString(103, user.getM_30());
				pstmt.setString(104, user.getM_31());
				pstmt.setString(105, user.getM_32());
				pstmt.setString(106, user.getM_33());
				pstmt.setString(107, user.getM_34());
				pstmt.setString(108, user.getM_35());
				pstmt.setString(109, user.getM_36());
				pstmt.setString(110, user.getM_37());
				pstmt.setString(111, user.getM_38());
				pstmt.setString(112, user.getM_39());
				if(!"".equals(user.getPool_flag()))pstmt.setInt(113, Integer.valueOf(user.getPool_flag()));else pstmt.setNull(113, Types.INTEGER);
				if(!"".equals(user.getPool_cnt()))pstmt.setInt(114, Integer.valueOf(user.getPool_cnt()));else pstmt.setNull(114, Types.INTEGER);
				if(!"".equals(user.getPool_see_flag()))pstmt.setInt(115, Integer.valueOf(user.getPool_see_flag()));else pstmt.setNull(115, Types.INTEGER);
				if(!"".equals(user.getLast_pool_time()))pstmt.setInt(116, Integer.valueOf(user.getLast_pool_time()));else pstmt.setNull(116, Types.INTEGER);
				if(!"".equals(user.getSync_basic_state()))pstmt.setInt(117, Integer.valueOf(user.getSync_basic_state()));else pstmt.setNull(117, Types.INTEGER);
				pstmt.setString(118, user.getSync_basic_msg());
				if(!"".equals(user.getSync_oppor_state()))pstmt.setInt(119, Integer.valueOf(user.getSync_oppor_state()));else pstmt.setNull(119, Types.INTEGER);
				pstmt.setString(120, user.getSync_oppor_msg());
				if(!"".equals(user.getSync_sales_state()))pstmt.setInt(121, Integer.valueOf(user.getSync_sales_state()));else pstmt.setNull(121, Types.INTEGER);
				pstmt.setString(122, user.getSync_sales_msg());
				pstmt.setString(123, user.getFenqi_code());
				pstmt.setString(124, user.getFenqi_name());
				pstmt.setString(125, user.getAddsource_type());
				pstmt.setString(126, user.getMingyuan_sync_state());
				pstmt.setString(127, user.getTuijian_type());
				pstmt.setString(128, user.getGlr_name());
				pstmt.setString(129, user.getGlr_phone());
				pstmt.setString(130, user.getGlr_card());
				pstmt.setString(131, user.getGlr_guanxi());
				pstmt.setString(132, user.getLast_daofang_fenqicode());
				if(!"".equals(user.getHouse_class()))pstmt.setInt(133, Integer.valueOf(user.getHouse_class()));else pstmt.setNull(133, Types.INTEGER);
				if(!"".equals(user.getMy_qiandao_time()))pstmt.setInt(134, Integer.valueOf(user.getMy_qiandao_time()));else pstmt.setNull(134, Types.INTEGER);
				if(!"".equals(user.getMy_xuanfang_time()))pstmt.setInt(135, Integer.valueOf(user.getMy_xuanfang_time()));else pstmt.setNull(135, Types.INTEGER);
				if(!"".equals(user.getMy_rengou_time()))pstmt.setInt(136, Integer.valueOf(user.getMy_rengou_time()));else pstmt.setNull(136, Types.INTEGER);
				if(!"".equals(user.getLast_renchou_time()))pstmt.setInt(137, Integer.valueOf(user.getLast_renchou_time()));else pstmt.setNull(137, Types.INTEGER);
				if(!"".equals(user.getLast_rengou_time()))pstmt.setInt(138, Integer.valueOf(user.getLast_rengou_time()));else pstmt.setNull(138, Types.INTEGER);
				if(!"".equals(user.getLast_qianyue_time()))pstmt.setInt(139, Integer.valueOf(user.getLast_qianyue_time()));else pstmt.setNull(139, Types.INTEGER);
				if(!"".equals(user.getLast_huikuan_time()))pstmt.setInt(140, Integer.valueOf(user.getLast_huikuan_time()));else pstmt.setNull(140, Types.INTEGER);
				pstmt.setString(141, user.getIs_love());
				pstmt.setString(142, user.getYuyue_text());
				pstmt.setTimestamp(143, Timestamp.valueOf(user.getLast_modify_on()));
				pstmt.setInt(144, Integer.valueOf(user.getId()));
				pstmt.setString(1, user.getUser_cts_id());
				pstmt.setString(2, user.getDev_code());
				pstmt.setString(3, user.getDev_name());
				pstmt.setString(4, user.getArea_code());
				pstmt.setString(5, user.getArea_name());
				pstmt.setString(6, user.getCity_code());
				pstmt.setString(7, user.getCity_name());
				pstmt.setString(8, user.getPro_code());
				pstmt.setString(9, user.getPro_name());
				pstmt.setString(10, user.getAgent_code());
				pstmt.setString(11, user.getAgent_code_name());
				pstmt.setString(12, user.getSales_phone());
				pstmt.setString(13, user.getSales_cts_id());
				pstmt.setString(14, user.getSales_name());
				pstmt.setString(15, user.getName());
				pstmt.setString(16, user.getPhone());
				pstmt.setString(17, user.getPhone1());
				pstmt.setString(18, user.getPhone2());
				pstmt.setString(19, user.getNick());
				pstmt.setString(20, user.getHeadpic());
				pstmt.setString(21, user.getWechat());
				pstmt.setString(22, user.getSex());
				pstmt.setString(23, user.getAge());
				pstmt.setString(24, user.getM_1());
				pstmt.setString(25, user.getM_2());
				pstmt.setString(26, user.getM_3());
				pstmt.setString(27, user.getM_4());
				pstmt.setString(28, user.getM_5());
				pstmt.setString(29, user.getM_6());
				pstmt.setString(30, user.getM_7());
				pstmt.setString(31, user.getM_8());
				pstmt.setString(32, user.getM_9());
				pstmt.setString(33, user.getM_10());
				pstmt.setString(34, user.getM_11());
				pstmt.setString(35, user.getM_12());
				pstmt.setString(36, user.getM_13());
				pstmt.setString(37, user.getM_14());
				pstmt.setString(38, user.getM_15());
				if(!"".equals(user.getM_16()))pstmt.setInt(39, Integer.valueOf(user.getM_16()));else pstmt.setNull(39, Types.INTEGER);
				if(!"".equals(user.getE_1()))pstmt.setInt(40, Integer.valueOf(user.getE_1()));else pstmt.setNull(40, Types.INTEGER);
				if(!"".equals(user.getE_2()))pstmt.setInt(41, Integer.valueOf(user.getE_2()));else pstmt.setNull(41, Types.INTEGER);
				if(!"".equals(user.getE_3()))pstmt.setInt(42, Integer.valueOf(user.getE_3()));else pstmt.setNull(42, Types.INTEGER);
				if(!"".equals(user.getE_4()))pstmt.setInt(43, Integer.valueOf(user.getE_4()));else pstmt.setNull(43, Types.INTEGER);
				if(!"".equals(user.getE_5()))pstmt.setInt(44, Integer.valueOf(user.getE_5()));else pstmt.setNull(44, Types.INTEGER);
				if(!"".equals(user.getE_6()))pstmt.setInt(45, Integer.valueOf(user.getE_6()));else pstmt.setNull(45, Types.INTEGER);
				if(!"".equals(user.getE_7()))pstmt.setInt(46, Integer.valueOf(user.getE_7()));else pstmt.setNull(46, Types.INTEGER);
				if(!"".equals(user.getE_8()))pstmt.setInt(47, Integer.valueOf(user.getE_8()));else pstmt.setNull(47, Types.INTEGER);
				if(!"".equals(user.getE_9()))pstmt.setInt(48, Integer.valueOf(user.getE_9()));else pstmt.setNull(48, Types.INTEGER);
				if(!"".equals(user.getE_10()))pstmt.setInt(49, Integer.valueOf(user.getE_10()));else pstmt.setNull(49, Types.INTEGER);
				if(!"".equals(user.getWsd()))pstmt.setInt(50, Integer.valueOf(user.getWsd()));else pstmt.setNull(50, Types.INTEGER);
				if(!"".equals(user.getWork_addr()))pstmt.setInt(51, Integer.valueOf(user.getWork_addr()));else pstmt.setNull(51, Types.INTEGER);
				if(!"".equals(user.getLive_addr()))pstmt.setInt(52, Integer.valueOf(user.getLive_addr()));else pstmt.setNull(52, Types.INTEGER);
				if(!"".equals(user.getVist_cnt()))pstmt.setInt(53, Integer.valueOf(user.getVist_cnt()));else pstmt.setNull(53, Types.INTEGER);
				if(!"".equals(user.getRectime()))pstmt.setInt(54, Integer.valueOf(user.getRectime()));else pstmt.setNull(54, Types.INTEGER);
				if(!"".equals(user.getUptime()))pstmt.setInt(55, Integer.valueOf(user.getUptime()));else pstmt.setNull(55, Types.INTEGER);
				if(!"".equals(user.getLast_vist_time()))pstmt.setInt(56, Integer.valueOf(user.getLast_vist_time()));else pstmt.setNull(56, Types.INTEGER);
				if(!"".equals(user.getFirst_daofang_time()))pstmt.setInt(57, Integer.valueOf(user.getFirst_daofang_time()));else pstmt.setNull(57, Types.INTEGER);
				if(!"".equals(user.getLast_daofang_time()))pstmt.setInt(58, Integer.valueOf(user.getLast_daofang_time()));else pstmt.setNull(58, Types.INTEGER);
				if(!"".equals(user.getFirst_huifang_time()))pstmt.setInt(59, Integer.valueOf(user.getFirst_huifang_time()));else pstmt.setNull(59, Types.INTEGER);
				if(!"".equals(user.getLast_huifang_time()))pstmt.setInt(60, Integer.valueOf(user.getLast_huifang_time()));else pstmt.setNull(60, Types.INTEGER);
				if(!"".equals(user.getLast_tran_time()))pstmt.setInt(61, Integer.valueOf(user.getLast_tran_time()));else pstmt.setNull(61, Types.INTEGER);
				pstmt.setString(62, user.getTran_type_tag());
				pstmt.setString(63, user.getTran_housename_tag());
				pstmt.setString(64, user.getWechat_openid());
				pstmt.setString(65, user.getMsg_back_url());
				if(!"".equals(user.getWechat_last_time()))pstmt.setInt(66, Integer.valueOf(user.getWechat_last_time()));else pstmt.setNull(66, Types.INTEGER);
				if(!"".equals(user.getYuyue_date()))pstmt.setInt(67, Integer.valueOf(user.getYuyue_date()));else pstmt.setNull(67, Types.INTEGER);
				if(!"".equals(user.getNew_flag()))pstmt.setInt(68, Integer.valueOf(user.getNew_flag()));else pstmt.setNull(68, Types.INTEGER);
				if(!"".equals(user.getNew_time()))pstmt.setInt(69, Integer.valueOf(user.getNew_time()));else pstmt.setNull(69, Types.INTEGER);
				pstmt.setString(70, user.getAddsource());
				pstmt.setString(71, user.getAgent_name());
				pstmt.setString(72, user.getAgent_phone());
				pstmt.setString(73, user.getRemark());
				pstmt.setString(74, user.getRemark_url());
				if(!"".equals(user.getSee_flag()))pstmt.setInt(75, Integer.valueOf(user.getSee_flag()));else pstmt.setNull(75, Types.INTEGER);
				if(!"".equals(user.getDel_flag()))pstmt.setInt(76, Integer.valueOf(user.getDel_flag()));else pstmt.setNull(76, Types.INTEGER);
				if(!"".equals(user.getAlloc_flag()))pstmt.setInt(77, Integer.valueOf(user.getAlloc_flag()));else pstmt.setNull(77, Types.INTEGER);
				pstmt.setString(78, user.getToday_date());
				pstmt.setString(79, user.getTomorrow_date());
				if(!"".equals(user.getSync_state()))pstmt.setInt(80, Integer.valueOf(user.getSync_state()));else pstmt.setNull(80, Types.INTEGER);
				if(!"".equals(user.getSync_cnt()))pstmt.setInt(81, Integer.valueOf(user.getSync_cnt()));else pstmt.setNull(81, Types.INTEGER);
				pstmt.setString(82, user.getSync_msg());
				if(!"".equals(user.getSync_time()))pstmt.setInt(83, Integer.valueOf(user.getSync_time()));else pstmt.setNull(83, Types.INTEGER);
				pstmt.setString(84, user.getHome_addr_info());
				if(!"".equals(user.getCard_type()))pstmt.setInt(85, Integer.valueOf(user.getCard_type()));else pstmt.setNull(85, Types.INTEGER);
				pstmt.setString(86, user.getCard_id());
				if(!"".equals(user.getBuy_days()))pstmt.setInt(87, Integer.valueOf(user.getBuy_days()));else pstmt.setNull(87, Types.INTEGER);
				if(!"".equals(user.getRengou_time()))pstmt.setInt(88, Integer.valueOf(user.getRengou_time()));else pstmt.setNull(88, Types.INTEGER);
				if(!"".equals(user.getQianyue_time()))pstmt.setInt(89, Integer.valueOf(user.getQianyue_time()));else pstmt.setNull(89, Types.INTEGER);
				pstmt.setString(90, user.getM_17());
				pstmt.setString(91, user.getM_18());
				pstmt.setString(92, user.getM_19());
				pstmt.setString(93, user.getM_20());
				pstmt.setString(94, user.getM_21());
				pstmt.setString(95, user.getM_22());
				pstmt.setString(96, user.getM_23());
				pstmt.setString(97, user.getM_24());
				pstmt.setString(98, user.getM_25());
				pstmt.setString(99, user.getM_26());
				pstmt.setString(100, user.getM_27());
				pstmt.setString(101, user.getM_28());
				pstmt.setString(102, user.getM_29());
				pstmt.setString(103, user.getM_30());
				pstmt.setString(104, user.getM_31());
				pstmt.setString(105, user.getM_32());
				pstmt.setString(106, user.getM_33());
				pstmt.setString(107, user.getM_34());
				pstmt.setString(108, user.getM_35());
				pstmt.setString(109, user.getM_36());
				pstmt.setString(110, user.getM_37());
				pstmt.setString(111, user.getM_38());
				pstmt.setString(112, user.getM_39());
				if(!"".equals(user.getPool_flag()))pstmt.setInt(113, Integer.valueOf(user.getPool_flag()));else pstmt.setNull(113, Types.INTEGER);
				if(!"".equals(user.getPool_cnt()))pstmt.setInt(114, Integer.valueOf(user.getPool_cnt()));else pstmt.setNull(114, Types.INTEGER);
				if(!"".equals(user.getPool_see_flag()))pstmt.setInt(115, Integer.valueOf(user.getPool_see_flag()));else pstmt.setNull(115, Types.INTEGER);
				if(!"".equals(user.getLast_pool_time()))pstmt.setInt(116, Integer.valueOf(user.getLast_pool_time()));else pstmt.setNull(116, Types.INTEGER);
				if(!"".equals(user.getSync_basic_state()))pstmt.setInt(117, Integer.valueOf(user.getSync_basic_state()));else pstmt.setNull(117, Types.INTEGER);
				pstmt.setString(118, user.getSync_basic_msg());
				if(!"".equals(user.getSync_oppor_state()))pstmt.setInt(119, Integer.valueOf(user.getSync_oppor_state()));else pstmt.setNull(119, Types.INTEGER);
				pstmt.setString(120, user.getSync_oppor_msg());
				if(!"".equals(user.getSync_sales_state()))pstmt.setInt(121, Integer.valueOf(user.getSync_sales_state()));else pstmt.setNull(121, Types.INTEGER);
				pstmt.setString(122, user.getSync_sales_msg());
				pstmt.setString(123, user.getFenqi_code());
				pstmt.setString(124, user.getFenqi_name());
				pstmt.setString(125, user.getAddsource_type());
				pstmt.setString(126, user.getMingyuan_sync_state());
				pstmt.setString(127, user.getTuijian_type());
				pstmt.setString(128, user.getGlr_name());
				pstmt.setString(129, user.getGlr_phone());
				pstmt.setString(130, user.getGlr_card());
				pstmt.setString(131, user.getGlr_guanxi());
				pstmt.setString(132, user.getLast_daofang_fenqicode());
				if(!"".equals(user.getHouse_class()))pstmt.setInt(133, Integer.valueOf(user.getHouse_class()));else pstmt.setNull(133, Types.INTEGER);
				if(!"".equals(user.getMy_qiandao_time()))pstmt.setInt(134, Integer.valueOf(user.getMy_qiandao_time()));else pstmt.setNull(134, Types.INTEGER);
				if(!"".equals(user.getMy_xuanfang_time()))pstmt.setInt(135, Integer.valueOf(user.getMy_xuanfang_time()));else pstmt.setNull(135, Types.INTEGER);
				if(!"".equals(user.getMy_rengou_time()))pstmt.setInt(136, Integer.valueOf(user.getMy_rengou_time()));else pstmt.setNull(136, Types.INTEGER);
				if(!"".equals(user.getLast_renchou_time()))pstmt.setInt(137, Integer.valueOf(user.getLast_renchou_time()));else pstmt.setNull(137, Types.INTEGER);
				if(!"".equals(user.getLast_rengou_time()))pstmt.setInt(138, Integer.valueOf(user.getLast_rengou_time()));else pstmt.setNull(138, Types.INTEGER);
				if(!"".equals(user.getLast_qianyue_time()))pstmt.setInt(139, Integer.valueOf(user.getLast_qianyue_time()));else pstmt.setNull(139, Types.INTEGER);
				if(!"".equals(user.getLast_huikuan_time()))pstmt.setInt(140, Integer.valueOf(user.getLast_huikuan_time()));else pstmt.setNull(140, Types.INTEGER);
				pstmt.setString(141, user.getIs_love());
				pstmt.setString(142, user.getYuyue_text());
				pstmt.setTimestamp(143, Timestamp.valueOf(user.getLast_modify_on()));
				pstmt.setInt(144, Integer.valueOf(user.getId()));
				pstmt.addBatch();
			}
			int[] count = pstmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.error("user_info更新出错：" + e.getMessage(),e);
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
	 * 插入用户数据
	 * @param userList
	 * @return
	 */
	public int insertUserInfo(List<UserInfoBean> userList){
		String strQuery = PreparedSql.INSERTUSERINFOSQL.replace("#tableName#", USERINFO);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(strQuery);
			conn.setAutoCommit(false);//设置不即时提交事务
			for(UserInfoBean user : userList){
				pstmt.setInt(1, Integer.valueOf(user.getId()));
				pstmt.setString(2, user.getUser_cts_id());
				pstmt.setString(3, user.getDev_code());
				pstmt.setString(4, user.getDev_name());
				pstmt.setString(5, user.getArea_code());
				pstmt.setString(6, user.getArea_name());
				pstmt.setString(7, user.getCity_code());
				pstmt.setString(8, user.getCity_name());
				pstmt.setString(9, user.getPro_code());
				pstmt.setString(10, user.getPro_name());
				pstmt.setString(11, user.getAgent_code());
				pstmt.setString(12, user.getAgent_code_name());
				pstmt.setString(13, user.getSales_phone());
				pstmt.setString(14, user.getSales_cts_id());
				pstmt.setString(15, user.getSales_name());
				pstmt.setString(16, user.getName());
				pstmt.setString(17, user.getPhone());
				pstmt.setString(18, user.getPhone1());
				pstmt.setString(19, user.getPhone2());
				pstmt.setString(20, user.getNick());
				pstmt.setString(21, user.getHeadpic());
				pstmt.setString(22, user.getWechat());
				pstmt.setString(23, user.getSex());
				pstmt.setString(24, user.getAge());
				pstmt.setString(25, user.getM_1());
				pstmt.setString(26, user.getM_2());
				pstmt.setString(27, user.getM_3());
				pstmt.setString(28, user.getM_4());
				pstmt.setString(29, user.getM_5());
				pstmt.setString(30, user.getM_6());
				pstmt.setString(31, user.getM_7());
				pstmt.setString(32, user.getM_8());
				pstmt.setString(33, user.getM_9());
				pstmt.setString(34, user.getM_10());
				pstmt.setString(35, user.getM_11());
				pstmt.setString(36, user.getM_12());
				pstmt.setString(37, user.getM_13());
				pstmt.setString(38, user.getM_14());
				pstmt.setString(39, user.getM_15());
				if(!"".equals(user.getM_16()))pstmt.setInt(40, Integer.valueOf(user.getM_16()));else pstmt.setNull(40, Types.INTEGER);
				if(!"".equals(user.getE_1()))pstmt.setInt(41, Integer.valueOf(user.getE_1()));else pstmt.setNull(41, Types.INTEGER);
				if(!"".equals(user.getE_2()))pstmt.setInt(42, Integer.valueOf(user.getE_2()));else pstmt.setNull(42, Types.INTEGER);
				if(!"".equals(user.getE_3()))pstmt.setInt(43, Integer.valueOf(user.getE_3()));else pstmt.setNull(43, Types.INTEGER);
				if(!"".equals(user.getE_4()))pstmt.setInt(44, Integer.valueOf(user.getE_4()));else pstmt.setNull(44, Types.INTEGER);
				if(!"".equals(user.getE_5()))pstmt.setInt(45, Integer.valueOf(user.getE_5()));else pstmt.setNull(45, Types.INTEGER);
				if(!"".equals(user.getE_6()))pstmt.setInt(46, Integer.valueOf(user.getE_6()));else pstmt.setNull(46, Types.INTEGER);
				if(!"".equals(user.getE_7()))pstmt.setInt(47, Integer.valueOf(user.getE_7()));else pstmt.setNull(47, Types.INTEGER);
				if(!"".equals(user.getE_8()))pstmt.setInt(48, Integer.valueOf(user.getE_8()));else pstmt.setNull(48, Types.INTEGER);
				if(!"".equals(user.getE_9()))pstmt.setInt(49, Integer.valueOf(user.getE_9()));else pstmt.setNull(49, Types.INTEGER);
				if(!"".equals(user.getE_10()))pstmt.setInt(50, Integer.valueOf(user.getE_10()));else pstmt.setNull(50, Types.INTEGER);
				if(!"".equals(user.getWsd()))pstmt.setInt(51, Integer.valueOf(user.getWsd()));else pstmt.setNull(51, Types.INTEGER);
				if(!"".equals(user.getWork_addr()))pstmt.setInt(52, Integer.valueOf(user.getWork_addr()));else pstmt.setNull(52, Types.INTEGER);
				if(!"".equals(user.getLive_addr()))pstmt.setInt(53, Integer.valueOf(user.getLive_addr()));else pstmt.setNull(53, Types.INTEGER);
				if(!"".equals(user.getVist_cnt()))pstmt.setInt(54, Integer.valueOf(user.getVist_cnt()));else pstmt.setNull(54, Types.INTEGER);
				if(!"".equals(user.getRectime()))pstmt.setInt(55, Integer.valueOf(user.getRectime()));else pstmt.setNull(55, Types.INTEGER);
				if(!"".equals(user.getUptime()))pstmt.setInt(56, Integer.valueOf(user.getUptime()));else pstmt.setNull(56, Types.INTEGER);
				if(!"".equals(user.getLast_vist_time()))pstmt.setInt(57, Integer.valueOf(user.getLast_vist_time()));else pstmt.setNull(57, Types.INTEGER);
				if(!"".equals(user.getFirst_daofang_time()))pstmt.setInt(58, Integer.valueOf(user.getFirst_daofang_time()));else pstmt.setNull(58, Types.INTEGER);
				if(!"".equals(user.getLast_daofang_time()))pstmt.setInt(59, Integer.valueOf(user.getLast_daofang_time()));else pstmt.setNull(59, Types.INTEGER);
				if(!"".equals(user.getFirst_huifang_time()))pstmt.setInt(60, Integer.valueOf(user.getFirst_huifang_time()));else pstmt.setNull(60, Types.INTEGER);
				if(!"".equals(user.getLast_huifang_time()))pstmt.setInt(61, Integer.valueOf(user.getLast_huifang_time()));else pstmt.setNull(61, Types.INTEGER);
				if(!"".equals(user.getLast_tran_time()))pstmt.setInt(62, Integer.valueOf(user.getLast_tran_time()));else pstmt.setNull(62, Types.INTEGER);
				pstmt.setString(63, user.getTran_type_tag());
				pstmt.setString(64, user.getTran_housename_tag());
				pstmt.setString(65, user.getWechat_openid());
				pstmt.setString(66, user.getMsg_back_url());
				if(!"".equals(user.getWechat_last_time()))pstmt.setInt(67, Integer.valueOf(user.getWechat_last_time()));else pstmt.setNull(67, Types.INTEGER);
				if(!"".equals(user.getYuyue_date()))pstmt.setInt(68, Integer.valueOf(user.getYuyue_date()));else pstmt.setNull(68, Types.INTEGER);
				if(!"".equals(user.getNew_flag()))pstmt.setInt(69, Integer.valueOf(user.getNew_flag()));else pstmt.setNull(69, Types.INTEGER);
				if(!"".equals(user.getNew_time()))pstmt.setInt(70, Integer.valueOf(user.getNew_time()));else pstmt.setNull(70, Types.INTEGER);
				pstmt.setString(71, user.getAddsource());
				pstmt.setString(72, user.getAgent_name());
				pstmt.setString(73, user.getAgent_phone());
				pstmt.setString(74, user.getRemark());
				pstmt.setString(75, user.getRemark_url());
				if(!"".equals(user.getSee_flag()))pstmt.setInt(76, Integer.valueOf(user.getSee_flag()));else pstmt.setNull(76, Types.INTEGER);
				if(!"".equals(user.getDel_flag()))pstmt.setInt(77, Integer.valueOf(user.getDel_flag()));else pstmt.setNull(77, Types.INTEGER);
				if(!"".equals(user.getAlloc_flag()))pstmt.setInt(78, Integer.valueOf(user.getAlloc_flag()));else pstmt.setNull(78, Types.INTEGER);
				pstmt.setString(79, user.getToday_date());
				pstmt.setString(80, user.getTomorrow_date());
				if(!"".equals(user.getSync_state()))pstmt.setInt(81, Integer.valueOf(user.getSync_state()));else pstmt.setNull(81, Types.INTEGER);
				if(!"".equals(user.getSync_cnt()))pstmt.setInt(82, Integer.valueOf(user.getSync_cnt()));else pstmt.setNull(82, Types.INTEGER);
				pstmt.setString(83, user.getSync_msg());
				if(!"".equals(user.getSync_time()))pstmt.setInt(84, Integer.valueOf(user.getSync_time()));else pstmt.setNull(84, Types.INTEGER);
				pstmt.setString(85, user.getHome_addr_info());
				if(!"".equals(user.getCard_type()))pstmt.setInt(86, Integer.valueOf(user.getCard_type()));else pstmt.setNull(86, Types.INTEGER);
				pstmt.setString(87, user.getCard_id());
				if(!"".equals(user.getBuy_days()))pstmt.setInt(88, Integer.valueOf(user.getBuy_days()));else pstmt.setNull(88, Types.INTEGER);
				if(!"".equals(user.getRengou_time()))pstmt.setInt(89, Integer.valueOf(user.getRengou_time()));else pstmt.setNull(89, Types.INTEGER);
				if(!"".equals(user.getQianyue_time()))pstmt.setInt(90, Integer.valueOf(user.getQianyue_time()));else pstmt.setNull(90, Types.INTEGER);
				pstmt.setString(91, user.getM_17());
				pstmt.setString(92, user.getM_18());
				pstmt.setString(93, user.getM_19());
				pstmt.setString(94, user.getM_20());
				pstmt.setString(95, user.getM_21());
				pstmt.setString(96, user.getM_22());
				pstmt.setString(97, user.getM_23());
				pstmt.setString(98, user.getM_24());
				pstmt.setString(99, user.getM_25());
				pstmt.setString(100, user.getM_26());
				pstmt.setString(101, user.getM_27());
				pstmt.setString(102, user.getM_28());
				pstmt.setString(103, user.getM_29());
				pstmt.setString(104, user.getM_30());
				pstmt.setString(105, user.getM_31());
				pstmt.setString(106, user.getM_32());
				pstmt.setString(107, user.getM_33());
				pstmt.setString(108, user.getM_34());
				pstmt.setString(109, user.getM_35());
				pstmt.setString(110, user.getM_36());
				pstmt.setString(111, user.getM_37());
				pstmt.setString(112, user.getM_38());
				pstmt.setString(113, user.getM_39());
				if(!"".equals(user.getPool_flag()))pstmt.setInt(114, Integer.valueOf(user.getPool_flag()));else pstmt.setNull(114, Types.INTEGER);
				if(!"".equals(user.getPool_cnt()))pstmt.setInt(115, Integer.valueOf(user.getPool_cnt()));else pstmt.setNull(115, Types.INTEGER);
				if(!"".equals(user.getPool_see_flag()))pstmt.setInt(116, Integer.valueOf(user.getPool_see_flag()));else pstmt.setNull(116, Types.INTEGER);
				if(!"".equals(user.getLast_pool_time()))pstmt.setInt(117, Integer.valueOf(user.getLast_pool_time()));else pstmt.setNull(117, Types.INTEGER);
				if(!"".equals(user.getSync_basic_state()))pstmt.setInt(118, Integer.valueOf(user.getSync_basic_state()));else pstmt.setNull(118, Types.INTEGER);
				pstmt.setString(119, user.getSync_basic_msg());
				if(!"".equals(user.getSync_oppor_state()))pstmt.setInt(120, Integer.valueOf(user.getSync_oppor_state()));else pstmt.setNull(120, Types.INTEGER);
				pstmt.setString(121, user.getSync_oppor_msg());
				if(!"".equals(user.getSync_sales_state()))pstmt.setInt(122, Integer.valueOf(user.getSync_sales_state()));else pstmt.setNull(122, Types.INTEGER);
				pstmt.setString(123, user.getSync_sales_msg());
				pstmt.setString(124, user.getFenqi_code());
				pstmt.setString(125, user.getFenqi_name());
				pstmt.setString(126, user.getAddsource_type());
				pstmt.setString(127, user.getMingyuan_sync_state());
				pstmt.setString(128, user.getTuijian_type());
				pstmt.setString(129, user.getGlr_name());
				pstmt.setString(130, user.getGlr_phone());
				pstmt.setString(131, user.getGlr_card());
				pstmt.setString(132, user.getGlr_guanxi());
				pstmt.setString(133, user.getLast_daofang_fenqicode());
				if(!"".equals(user.getHouse_class()))pstmt.setInt(134, Integer.valueOf(user.getHouse_class()));else pstmt.setNull(134, Types.INTEGER);
				if(!"".equals(user.getMy_qiandao_time()))pstmt.setInt(135, Integer.valueOf(user.getMy_qiandao_time()));else pstmt.setNull(135, Types.INTEGER);
				if(!"".equals(user.getMy_xuanfang_time()))pstmt.setInt(136, Integer.valueOf(user.getMy_xuanfang_time()));else pstmt.setNull(136, Types.INTEGER);
				if(!"".equals(user.getMy_rengou_time()))pstmt.setInt(137, Integer.valueOf(user.getMy_rengou_time()));else pstmt.setNull(137, Types.INTEGER);
				if(!"".equals(user.getLast_renchou_time()))pstmt.setInt(138, Integer.valueOf(user.getLast_renchou_time()));else pstmt.setNull(138, Types.INTEGER);
				if(!"".equals(user.getLast_rengou_time()))pstmt.setInt(139, Integer.valueOf(user.getLast_rengou_time()));else pstmt.setNull(139, Types.INTEGER);
				if(!"".equals(user.getLast_qianyue_time()))pstmt.setInt(140, Integer.valueOf(user.getLast_qianyue_time()));else pstmt.setNull(140, Types.INTEGER);
				if(!"".equals(user.getLast_huikuan_time()))pstmt.setInt(141, Integer.valueOf(user.getLast_huikuan_time()));else pstmt.setNull(141, Types.INTEGER);
				pstmt.setString(142, user.getIs_love());
				pstmt.setString(143, user.getYuyue_text());
				pstmt.setTimestamp(144, Timestamp.valueOf(user.getLast_modify_on()));
				pstmt.addBatch();
			}
			int[] count = pstmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.error("user_info插入出错：" + e.getMessage(),e);
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
