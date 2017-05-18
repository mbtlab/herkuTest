package com.datacenter.bean;

import net.sf.json.JSONObject;
import com.datacenter.initConfig.Error;

/**
 * 相应消息封装类
 * Date: 2017-04-26
 * @author Leo
 *
 */
public class ResponseMessage {
	private int errCode;
	private String errMsg;
	private JSONObject json;
	
	public ResponseMessage(){
	}
	public ResponseMessage(Error e){
		this.errCode = e.errcode();
		this.errMsg = e.errmsg();
		this.json = e.toJSONObject();
	}
	
	public int getErrCode() {
		return errCode;
	}
	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public JSONObject getJson() {
		return json;
	}
	public void setJson(JSONObject json) {
		this.json = json;
	}
}
