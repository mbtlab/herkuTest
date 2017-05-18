package com.datacenter.bean;
/*
 * christ
 * 2017/05/04
 *项目信息
 */
public class CognitiveChannelBean extends DataBeanObject{
	private String id;
	private String  pro_code;
	private String  pro_name;
	private String  cognitive_name;
	private String  cognitive_value;
	private String  class_value;
	private String  del_flag;
	private String  last_modify_on;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getCognitive_name() {
		return cognitive_name;
	}
	public void setCognitive_name(String cognitive_name) {
		this.cognitive_name = cognitive_name;
	}
	public String getCognitive_value() {
		return cognitive_value;
	}
	public void setCognitive_value(String cognitive_value) {
		this.cognitive_value = cognitive_value;
	}
	public String getClass_value() {
		return class_value;
	}
	public void setClass_value(String class_value) {
		this.class_value = class_value;
	}
	public String getDel_flag() {
		return del_flag;
	}
	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}
	public String getLast_modify_on() {
		return last_modify_on;
	}
	public void setLast_modify_on(String last_modify_on) {
		this.last_modify_on = last_modify_on;
	}

}
