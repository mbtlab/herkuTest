package com.datacenter.initConfig;

/**
 * 预先定义好的SQL
 * Date: 2017-05-03
 * @author Leo
 *
 */
public class PreparedSql {
	public static final String SELECTSQL = "SELECT id FROM \"public\".#tableName# WHERE id IN ( #ID# )";
	public static final String SELECTHOUSESQL = "SELECT house_cts_id FROM \"public\".#tableName# WHERE house_cts_id IN ( #ID# )";
	public static final String SELECTPROINFOSQL = "SELECT pro_code FROM \"public\".#tableName# WHERE pro_code IN (#ID#) ";
	
	//user_info表sql
	public static final String INSERTUSERINFOSQL = "INSERT INTO \"public\".#tableName# "
			+ " (id,user_cts_id,dev_code,dev_name,area_code,area_name,city_code,city_name,pro_code,pro_name,agent_code,agent_code_name,sales_phone,sales_cts_id,sales_name,name,phone,phone1,phone2,nick,headpic,wechat,sex,age,m_1,m_2,m_3,m_4,m_5,m_6,m_7,m_8,m_9,m_10,m_11,m_12,m_13,m_14,m_15,m_16,e_1,e_2,e_3,e_4,e_5,e_6,e_7,e_8,e_9,e_10,wsd,work_addr,live_addr,vist_cnt,rectime,uptime,last_vist_time,first_daofang_time,last_daofang_time,first_huifang_time,last_huifang_time,last_tran_time,tran_type_tag,tran_housename_tag,wechat_openid,msg_back_url,wechat_last_time,yuyue_date,new_flag,new_time,addsource,agent_name,agent_phone,remark,remark_url,see_flag,del_flag,alloc_flag,today_date,tomorrow_date,sync_state,sync_cnt,sync_msg,sync_time,home_addr_info,card_type,card_id,buy_days,rengou_time,qianyue_time,m_17,m_18,m_19,m_20,m_21,m_22,m_23,m_24,m_25,m_26,m_27,m_28,m_29,m_30,m_31,m_32,m_33,m_34,m_35,m_36,m_37,m_38,m_39,pool_flag,pool_cnt,pool_see_flag,last_pool_time,sync_basic_state,sync_basic_msg,sync_oppor_state,sync_oppor_msg,sync_sales_state,sync_sales_msg,fenqi_code,fenqi_name,addsource_type,mingyuan_sync_state,tuijian_type,glr_name,glr_phone,glr_card,glr_guanxi,last_daofang_fenqicode,house_class,my_qiandao_time,my_xuanfang_time,my_rengou_time,last_renchou_time,last_rengou_time,last_qianyue_time,last_huikuan_time,is_love,yuyue_text,last_modify_on) "
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	public static final String UPDATEUSERINFOSQL = "UPDATE \"public\".#tableName# SET "
			+ " user_cts_id=?,dev_code=?,dev_name=?,area_code=?,area_name=?,city_code=?,city_name=?,pro_code=?,pro_name=?,agent_code=?,agent_code_name=?,sales_phone=?,sales_cts_id=?,sales_name=?,name=?,phone=?,phone1=?,phone2=?,nick=?,headpic=?,wechat=?,sex=?,age=?,m_1=?,m_2=?,m_3=?,m_4=?,m_5=?,m_6=?,m_7=?,m_8=?,m_9=?,m_10=?,m_11=?,m_12=?,m_13=?,m_14=?,m_15=?,m_16=?,e_1=?,e_2=?,e_3=?,e_4=?,e_5=?,e_6=?,e_7=?,e_8=?,e_9=?,e_10=?,wsd=?,work_addr=?,live_addr=?,vist_cnt=?,rectime=?,uptime=?,last_vist_time=?,first_daofang_time=?,last_daofang_time=?,first_huifang_time=?,last_huifang_time=?,last_tran_time=?,tran_type_tag=?,tran_housename_tag=?,wechat_openid=?,msg_back_url=?,wechat_last_time=?,yuyue_date=?,new_flag=?,new_time=?,addsource=?,agent_name=?,agent_phone=?,remark=?,remark_url=?,see_flag=?,del_flag=?,alloc_flag=?,today_date=?,tomorrow_date=?,sync_state=?,sync_cnt=?,sync_msg=?,sync_time=?,home_addr_info=?,card_type=?,card_id=?,buy_days=?,rengou_time=?,qianyue_time=?,m_17=?,m_18=?,m_19=?,m_20=?,m_21=?,m_22=?,m_23=?,m_24=?,m_25=?,m_26=?,m_27=?,m_28=?,m_29=?,m_30=?,m_31=?,m_32=?,m_33=?,m_34=?,m_35=?,m_36=?,m_37=?,m_38=?,m_39=?,pool_flag=?,pool_cnt=?,pool_see_flag=?,last_pool_time=?,sync_basic_state=?,sync_basic_msg=?,sync_oppor_state=?,sync_oppor_msg=?,sync_sales_state=?,sync_sales_msg=?,fenqi_code=?,fenqi_name=?,addsource_type=?,mingyuan_sync_state=?,tuijian_type=?,glr_name=?,glr_phone=?,glr_card=?,glr_guanxi=?,last_daofang_fenqicode=?,house_class=?,my_qiandao_time=?,my_xuanfang_time=?,my_rengou_time=?,last_renchou_time=?,last_rengou_time=?,last_qianyue_time=?,last_huikuan_time=?,is_love=?,yuyue_text=?,last_modify_on=? "
			+ " WHERE id=? ;";
	
	//comm表sql
	public static final String INSERTCOMMSQL = "INSERT INTO \"public\".#tableName# "
			+ "(id,dev_code,dev_name,area_code,area_name,city_code,city_name,pro_code,pro_name,agent_code,agent_name,user_id,user_cts_id,user_name,sales_cts_id,sales_phone,sales_name,comm_class,comm_type,context_type,context,url,start_time,end_time,comm_effect,remark,remark_url,seeflag,sync_state,sync_cnt,sync_msg,sync_time,fenqi_code,fenqi_name,addsource,addsource_type,cognition_type,last_modify_on) "
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	public static final String UPDATECOMMSQL = "UPDATE \"public\".#tableName# SET "
			+ " dev_code=?,dev_name=?,area_code=?,area_name=?,city_code=?,city_name=?,pro_code=?,pro_name=?,agent_code=?,agent_name=?,user_id=?,user_cts_id=?,user_name=?,sales_cts_id=?,sales_phone=?,sales_name=?,comm_class=?,comm_type=?,context_type=?,context=?,url=?,start_time=?,end_time=?,comm_effect=?,remark=?,remark_url=?,seeflag=?,sync_state=?,sync_cnt=?,sync_msg=?,sync_time=?,fenqi_code=?,fenqi_name=?,addsource=?,addsource_type=?,cognition_type=?,last_modify_on=? "
			+ " WHERE id=? ;";
	
	//user_cir表sql
	public static final String INSERTUSERCIRSQL = "INSERT INTO \"public\".#tableName# "
			+ " (id,dev_code,dev_name,area_code,area_name,city_code,city_name,pro_code,pro_name,user_id,user_cts_id,op_sales,to_sales,context,remark,cir_time,sales_cts_id,last_modify_on ) "
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ;";
	public static final String UPDATEUSERCIRSQL = "UPDATE \"public\".#tableName# SET "
			+ " dev_code=?,dev_name=?,area_code=?,area_name=?,city_code=?,city_name=?,pro_code=?,pro_name=?,user_id=?,user_cts_id=?,op_sales=?,to_sales=?,context=?,remark=?,cir_time=?,sales_cts_id=?,last_modify_on=? "
			+ "WHERE id=? ;";
	
	//pro_info表sql
	public static final String INSERTPROINFOSQL = "INSERT INTO \"public\".#tableName# "
			+ " (id,dev_code,dev_name,area_code,area_name,city_code,city_name,pro_code,pro_name,house_class,manager_list,sync_flag,sync_position,agent_code,agent_name,alloc_flag,cal_content,pro_dict,intr,pic,state,wechat_name,wechat_pwd,msg_back_url,get_two_pic_url,zc_days,yuqi_days,dai_gen_jin_days,gen_jin_days,qian_yue_days,pro_dict_new,recover_flag,dai_gen_jin_recover_days,gen_jin_recover_days,rule_dict,user_alloc_dict,close_flag,house_sync_flag,house_visible_flag,notify_flag,will_house_cnt,opening_user_cnt,discount,love_cnt,up_time,last_modify_on) "
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ;";
//	public static final String UPDATEPROINFOSQL = "UPDATE \"public\".#tableName# SET "
//			+ " dev_code=?,dev_name=?,area_code=?,area_name=?,city_code=?,city_name=?,pro_code=?,pro_name=?,house_class=?,manager_list=?,sync_flag=?,sync_position=?,agent_code=?,agent_name=?,alloc_flag=?,cal_content=?,pro_dict=?,intr=?,pic=?,state=?,wechat_name=?,wechat_pwd=?,msg_back_url=?,get_two_pic_url=?,zc_days=?,yuqi_days=?,dai_gen_jin_days=?,gen_jin_days=?,qian_yue_days=?,pro_dict_new=?,recover_flag=?,dai_gen_jin_recover_days=?,gen_jin_recover_days=?,rule_dict=?,user_alloc_dict=?,close_flag=?,house_sync_flag=?,house_visible_flag=?,notify_flag=?,will_house_cnt=?,opening_user_cnt=?,discount=?,love_cnt=?,up_time=?,last_modify_on=? "
//			+ "WHERE id=? ;";
	public static final String UPDATEPROINFOSQL = "UPDATE \"public\".#tableName# SET "
			+ " dev_code=?,dev_name=?,area_code=?,area_name=?,city_code=?,city_name=?,id=?,pro_name=?,house_class=?,manager_list=?,sync_flag=?,sync_position=?,agent_code=?,agent_name=?,alloc_flag=?,cal_content=?,pro_dict=?,intr=?,pic=?,state=?,wechat_name=?,wechat_pwd=?,msg_back_url=?,get_two_pic_url=?,zc_days=?,yuqi_days=?,dai_gen_jin_days=?,gen_jin_days=?,qian_yue_days=?,pro_dict_new=?,recover_flag=?,dai_gen_jin_recover_days=?,gen_jin_recover_days=?,rule_dict=?,user_alloc_dict=?,close_flag=?,house_sync_flag=?,house_visible_flag=?,notify_flag=?,will_house_cnt=?,opening_user_cnt=?,discount=?,love_cnt=?,up_time=?,last_modify_on=? "
			+ "WHERE  pro_code=? ;";
	
	//sys_dict表sql
	public static final String INSERTSYSDICTSQL = "INSERT INTO \"public\".#tableName# "
			+ " (id,code,name,valuejson,last_modify_on) "
			+ " VALUES (?,?,?,?,?) ;";
	public static final String UPDATESYSDICTSQL = "UPDATE \"public\".#tableName# SET "
			+ " code=?,name=?,valuejson=?,last_modify_on=? "
			+ "WHERE id=? ; ";
	
	//cognitive_channel表sql
	public static final String INSERTCONITIVECHANNELSQL = "INSERT INTO \"public\".#tableName# "
			+ " (id,pro_code,pro_name,cognitive_name,cognitive_value,class_value,del_flag,last_modify_on) "
			+ " VALUES (?,?,?,?,?,?,?,?) ;";
	public static final String UPDATECONITIVECHANNLESQL = "UPDATE \"public\".#tableName# SET "
			+ " pro_code=?,pro_name=?,cognitive_name=?,cognitive_value=?,class_value=?,del_flag=?,last_modify_on=? "
			+ " WHERE id=? ;";
	
	//user_will_new表sql
	public static final String INSERTUSERWILLNEWSQL = "INSERT INTO \"public\".#tableName# "
			+ " (id,user_id,user_name,agent_code,agent_name,sales_phone,sales_name,up_time,house_id_1,house_name_1,house_id_2,house_name_2,house_id_3,house_name_3,last_modify_on) "
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ;";
	public static final String UPDATEUSERWILLNEWSQL = "UPDATE \"public\".#tableName# SET "
			+ " user_id=?,user_name=?,agent_code=?,agent_name=?,sales_phone=?,sales_name=?,up_time=?,house_id_1=?,house_name_1=?,house_id_2=?,house_name_2=?,house_id_3=?,house_name_3=?,last_modify_on=? "
			+ " WHERE id=?;";
	
	//house表sql
	public static final String INSERTHOUSESQL = "INSERT INTO \"public\".#tableName# "
			+ " (id,house_cts_id,dev_code,dev_name,area_code,area_name,city_code,city_name,pro_code,pro_name,house_code,group_code,building_code,unit_code,floor_code,room_code,house_type,house_str,house_face,house_decor,house_haggle,house_area,room_area,house_price,total_price,house_pic,house_class,house_state,last_lock_time,last_user_id,file_no,order_code,sales_agent_code,sales_agent_name,house_remark,sales_view_on,sales_view_state,total_cals,today_cals,last_cal_time,state_edit_flag,total_1,total_2,total_3,last_modify_on) "
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ;";
//	public static final String UPDATEHOUSESQL = "UPDATE \"public\".#tableName# SET "
//			+ " house_cts_id=?,dev_code=?,dev_name=?,area_code=?,area_name=?,city_code=?,city_name=?,pro_code=?,pro_name=?,house_code=?,group_code=?,building_code=?,unit_code=?,floor_code=?,room_code=?,house_type=?,house_str=?,house_face=?,house_decor=?,house_haggle=?,house_area=?,room_area=?,house_price=?,total_price=?,house_pic=?,house_class=?,house_state=?,last_lock_time=?,last_user_id=?,file_no=?,order_code=?,sales_agent_code=?,sales_agent_name=?,house_remark=?,sales_view_on=?,sales_view_state=?,total_cals=?,today_cals=?,last_cal_time=?,state_edit_flag=?,total_1=?,total_2=?,total_3=?,last_modify_on=? "
//			+ " WHERE id=?;";
	public static final String UPDATEHOUSESQL = "UPDATE \"public\".#tableName# SET "
			+ " id=?,dev_code=?,dev_name=?,area_code=?,area_name=?,city_code=?,city_name=?,pro_code=?,pro_name=?,house_code=?,group_code=?,building_code=?,unit_code=?,floor_code=?,room_code=?,house_type=?,house_str=?,house_face=?,house_decor=?,house_haggle=?,house_area=?,room_area=?,house_price=?,total_price=?,house_pic=?,house_class=?,house_state=?,last_lock_time=?,last_user_id=?,file_no=?,order_code=?,sales_agent_code=?,sales_agent_name=?,house_remark=?,sales_view_on=?,sales_view_state=?,total_cals=?,today_cals=?,last_cal_time=?,state_edit_flag=?,total_1=?,total_2=?,total_3=?,last_modify_on=? "
			+ " WHERE house_cts_id=?;";
	
	//sales表sql
	public static final String INSERTSALESSQL = "INSERT INTO \"public\".#tableName# "
			+ " (id,dev_code,dev_name,phone,name,pwd,user_type,sex,headpic,intr,level,integral,sys_state,act_state,client_type,push_id,regtime,two_pic_url,month_target_amount,month_target_number,agent_code,agent_name,del_flag,im_flag,im_state,last_modify_on) "
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	public static final String UPDATESALESSQL = "UPDATE \"public\".#tableName# SET "
			+ " dev_code=?,dev_name=?,phone=?,name=?,pwd=?,user_type=?,sex=?,headpic=?,intr=?,level=?,integral=?,sys_state=?,act_state=?,client_type=?,push_id=?,regtime=?,two_pic_url=?,month_target_amount=?,month_target_number=?,agent_code=?,agent_name=?,del_flag=?,im_flag=?,im_state=?,last_modify_on=? "
			+ " WHERE id=?;";
	
	//sales_pro_house表sql
	public static final String INSERTSALESPROHOUSESQL = "INSERT INTO \"public\".#tableName# "
			+ " (id,dev_code,dev_name,area_code,area_name,city_code,city_name,pro_code,pro_name,house_class,agent_code,agent_name,user_type,sales_phone,sales_name,sales_cts_id,mingyuan_id,mingyuan_acc,sync_cnt,sync_state,sync_msg,sync_time,sys_state,del_flag,up_time,last_modify_on) "
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	public static final String UPDATESALESPROHOUSESQL = "UPDATE \"public\".#tableName# SET "
			+ " dev_code=?,dev_name=?,area_code=?,area_name=?,city_code=?,city_name=?,pro_code=?,pro_name=?,house_class=?,agent_code=?,agent_name=?,user_type=?,sales_phone=?,sales_name=?,sales_cts_id=?,mingyuan_id=?,mingyuan_acc=?,sync_cnt=?,sync_state=?,sync_msg=?,sync_time=?,sys_state=?,del_flag=?,up_time=?,last_modify_on=? "
			+ " WHERE id=?;";
	
	//trans表sql
	public static final String INSERTTRANSSQL = "INSERT INTO \"public\".#tableName# "
			+ " (id,dev_code,dev_name,area_code,area_name,city_code,city_name,pro_code,pro_name,agent_code,agent_name,user_id,user_name,user_cts_id,sales_phone,sales_name,sales_cts_id,house_id,house_cts_id,house_class,house_fullname,tran_type,pay_type,disinfo,amount,file_list,no_file_cnt,remark,tran_time,cts_sid,uptime,trans_cts_id,close_state,close_reason,order_type,ht_amount,sp_state,sp_human,sp_opinion,sp_time,source_type,last_modify_on) "
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	public static final String UPDATETRANSSQL = "UPDATE \"public\".#tableName# SET "
			+ " dev_code=?,dev_name=?,area_code=?,area_name=?,city_code=?,city_name=?,pro_code=?,pro_name=?,agent_code=?,agent_name=?,user_id=?,user_name=?,user_cts_id=?,sales_phone=?,sales_name=?,sales_cts_id=?,house_id=?,house_cts_id=?,house_class=?,house_fullname=?,tran_type=?,pay_type=?,disinfo=?,amount=?,file_list=?,no_file_cnt=?,remark=?,tran_time=?,cts_sid=?,uptime=?,trans_cts_id=?,close_state=?,close_reason=?,order_type=?,ht_amount=?,sp_state=?,sp_human=?,sp_opinion=?,sp_time=?,source_type=?,last_modify_on=? "
			+ " WHERE id=?;";
}
