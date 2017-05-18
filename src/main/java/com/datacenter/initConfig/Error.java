package com.datacenter.initConfig;

import java.io.Serializable;

import net.sf.json.JSONObject;

/**
 * 错误码描述
 * Date: 2017-04-26
 * @author Leo
 *
 */
public enum Error implements Serializable {
	SUCCESS(0,"成功"),
	UNKNOWN(-1,"未知错误"),
	PARAMETER_MISSING(1001,"缺少必须参数"),
	PARAMETER_INVALID_TYPE(1002,"参数类型错误"),
	UNSUPPORT_REQUEST_METHOD(1003,"不支持的请求方式"),
	PARSE_PARAMETER_ERROR(1004,"请求数据格式错误"/*"解析请求参数错误"*/),
	REQUEST_DATA_MISSING(1007,"请求数据缺失"),
	REQUEST_DATA_TOO_LONG(1006,"请求数据过大"),
	UNSUPPORT_INTERFACE(1008,"不支持的接口请求"),
	SYSTEM_ERROR(1005,"系统处理失败"),
	SIGNATURE_ERROR(1009,"签名验证失败");
	private int errcode;
	private String errmsg;
	private Error(int errcode,String errmsg){
		this.errcode=errcode;
		this.errmsg=errmsg;
	}
	public int errcode() {
		return errcode;
	}
	public String errmsg() {
		return errmsg;
	}
	public void appendErrmsg(String errmsg) {
		//this.errmsg+=errmsg;
	}
	public String toJSON(){
		return String.format("{\"errcode\":%s,\"errmsg\":\"%s\"}",this.errcode,this.errmsg);
	}
	public JSONObject toJSONObject(){
		return JSONObject.fromObject(this.toJSON());
	}
	public boolean success() {
		return this == SUCCESS;
	}
}
