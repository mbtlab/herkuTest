package com.datacenter.bean;
/*
 * christ
 * 2017/05/04
 *��Ŀ��Ϣ
 */
public class ProInfoBean extends DataBeanObject{
	private String id;
	private String dev_code;
	private String dev_name;
	private String area_code;
	private String area_name;
	private String city_code;
	private String city_name;
	private String pro_code;
	private String pro_name;
	private String house_class;
	private String manager_list;
	private String sync_flag;
	private String sync_position;
	private String agent_code;
	private String agent_name;
	private String alloc_flag;
	private String cal_content;
	private String pro_dict;
	private String intr;
	private String pic;
	private String state;
	private String wechat_name;
	private String wechat_pwd;
	private String msg_back_url;
	private String get_two_pic_url;
	private String zc_days;
	private String yuqi_days;
	private String dai_gen_jin_days;
	private String gen_jin_days;
	private String qian_yue_days;
	private String pro_dict_new;
	private String recover_flag;
	private String dai_gen_jin_recover_days;
	private String gen_jin_recover_days;
	private String rule_dict;
	private String user_alloc_dict;
	private String close_flag;
	private String house_sync_flag;
	private String house_visible_flag;
	private String notify_flag;
	private String will_house_cnt;
	private String opening_user_cnt;
	private String discount;
	private String love_cnt;
	private String up_time;
	private String last_modify_on;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDev_code() {
		return dev_code;
	}
	public void setDev_code(String dev_code) {
		this.dev_code = dev_code;
	}
	public String getDev_name() {
		return dev_name;
	}
	public void setDev_name(String dev_name) {
		this.dev_name = dev_name;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getPro_code() {
		return pro_code;
	}
	public void setPro_code(String pro_code) {
		this.pro_code = pro_code;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public String getHouse_class() {
		return house_class;
	}
	public void setHouse_class(String house_class) {
		this.house_class = house_class;
	}
	public String getManager_list() {
		return manager_list;
	}
	public void setManager_list(String manager_list) {
		this.manager_list = manager_list;
	}
	public String getSync_flag() {
		return sync_flag;
	}
	public void setSync_flag(String sync_flag) {
		this.sync_flag = sync_flag;
	}
	public String getSync_position() {
		return sync_position;
	}
	public void setSync_position(String sync_position) {
		this.sync_position = sync_position;
	}
	public String getAgent_code() {
		return agent_code;
	}
	public void setAgent_code(String agent_code) {
		this.agent_code = agent_code;
	}
	public String getAgent_name() {
		return agent_name;
	}
	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}
	public String getAlloc_flag() {
		return alloc_flag;
	}
	public void setAlloc_flag(String alloc_flag) {
		this.alloc_flag = alloc_flag;
	}
	public String getCal_content() {
		return cal_content;
	}
	public void setCal_content(String cal_content) {
		this.cal_content = cal_content.replace("\\","");
	}
	public String getPro_dict() {
		return pro_dict;
	}
	public void setPro_dict(String pro_dict) {
		this.pro_dict = pro_dict.replace("\\", "");
	}
	public String getIntr() {
		return intr;
	}
	public void setIntr(String intr) {
		this.intr = intr;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getWechat_name() {
		return wechat_name;
	}
	public void setWechat_name(String wechat_name) {
		this.wechat_name = wechat_name;
	}
	public String getWechat_pwd() {
		return wechat_pwd;
	}
	public void setWechat_pwd(String wechat_pwd) {
		this.wechat_pwd = wechat_pwd;
	}
	public String getMsg_back_url() {
		return msg_back_url;
	}
	public void setMsg_back_url(String msg_back_url) {
		this.msg_back_url = msg_back_url;
	}
	public String getGet_two_pic_url() {
		return get_two_pic_url;
	}
	public void setGet_two_pic_url(String get_two_pic_url) {
		this.get_two_pic_url = get_two_pic_url;
	}
	public String getZc_days() {
		return zc_days;
	}
	public void setZc_days(String zc_days) {
		this.zc_days = zc_days;
	}
	public String getYuqi_days() {
		return yuqi_days;
	}
	public void setYuqi_days(String yuqi_days) {
		this.yuqi_days = yuqi_days;
	}
	public String getDai_gen_jin_days() {
		return dai_gen_jin_days;
	}
	public void setDai_gen_jin_days(String dai_gen_jin_days) {
		this.dai_gen_jin_days = dai_gen_jin_days;
	}
	public String getGen_jin_days() {
		return gen_jin_days;
	}
	public void setGen_jin_days(String gen_jin_days) {
		this.gen_jin_days = gen_jin_days;
	}
	public String getQian_yue_days() {
		return qian_yue_days;
	}
	public void setQian_yue_days(String qian_yue_days) {
		this.qian_yue_days = qian_yue_days;
	}
	public String getPro_dict_new() {
		return pro_dict_new;
	}
	public void setPro_dict_new(String pro_dict_new) {
		this.pro_dict_new = pro_dict_new.replace("\\", "");
	}
	public String getRecover_flag() {
		return recover_flag;
	}
	public void setRecover_flag(String recover_flag) {
		this.recover_flag = recover_flag;
	}
	public String getDai_gen_jin_recover_days() {
		return dai_gen_jin_recover_days;
	}
	public void setDai_gen_jin_recover_days(String dai_gen_jin_recover_days) {
		this.dai_gen_jin_recover_days = dai_gen_jin_recover_days;
	}
	public String getGen_jin_recover_days() {
		return gen_jin_recover_days;
	}
	public void setGen_jin_recover_days(String gen_jin_recover_days) {
		this.gen_jin_recover_days = gen_jin_recover_days;
	}
	public String getRule_dict() {
		return rule_dict;
	}
	public void setRule_dict(String rule_dict) {
		this.rule_dict = rule_dict.replace("\\","");
	}
	public String getUser_alloc_dict() {
		return user_alloc_dict;
	}
	public void setUser_alloc_dict(String user_alloc_dict) {
		this.user_alloc_dict = user_alloc_dict.replace("\\", "");
	}
	public String getClose_flag() {
		return close_flag;
	}
	public void setClose_flag(String close_flag) {
		this.close_flag = close_flag;
	}
	public String getHouse_sync_flag() {
		return house_sync_flag;
	}
	public void setHouse_sync_flag(String house_sync_flag) {
		this.house_sync_flag = house_sync_flag;
	}
	public String getHouse_visible_flag() {
		return house_visible_flag;
	}
	public void setHouse_visible_flag(String house_visible_flag) {
		this.house_visible_flag = house_visible_flag;
	}
	public String getNotify_flag() {
		return notify_flag;
	}
	public void setNotify_flag(String notify_flag) {
		this.notify_flag = notify_flag;
	}
	public String getWill_house_cnt() {
		return will_house_cnt;
	}
	public void setWill_house_cnt(String will_house_cnt) {
		this.will_house_cnt = will_house_cnt;
	}
	public String getOpening_user_cnt() {
		return opening_user_cnt;
	}
	public void setOpening_user_cnt(String opening_user_cnt) {
		this.opening_user_cnt = opening_user_cnt;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		try{
			this.discount = discount.replace("\\", "");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public String getLove_cnt() {
		return love_cnt;
	}
	public void setLove_cnt(String love_cnt) {
		this.love_cnt = love_cnt;
	}
	public String getUp_time() {
		return up_time;
	}
	public void setUp_time(String up_time) {
		this.up_time = up_time;
	}
	public String getLast_modify_on() {
		return last_modify_on;
	}
	public void setLast_modify_on(String last_modify_on) {
		this.last_modify_on = last_modify_on;
	}

}
