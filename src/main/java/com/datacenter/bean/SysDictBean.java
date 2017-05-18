package com.datacenter.bean;
/*
 * christ
 * 2017/05/04
 *ϵͳ��Ϣ
 */
public class SysDictBean extends DataBeanObject{
	private String id;
	private String code;
	private String name;
	private String valuejson;
	private String last_modify_on;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValuejson() {
		return valuejson;
	}
	public void setValuejson(String valuejson) {
		this.valuejson = valuejson.replace("\\", "");
	}
	public String getLast_modify_on() {
		return last_modify_on;
	}
	public void setLast_modify_on(String last_modify_on) {
		this.last_modify_on = last_modify_on;
	}

}
