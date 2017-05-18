package com.vanke.handler;


import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.datacenter.handler.AbsCommandHandler;
import com.datacenter.initConfig.AppConfig;
import com.datacenter.initConfig.Error;
import com.datacenter.initConfig.JsonInfoField;
import com.datacenter.bean.*;
import com.datacenter.common.EncodeUtil;
import com.datacenter.common.SpringContextHolder;
import com.vanke.action.BeanAction;

public class HerokuDataUpsertHandler extends AbsCommandHandler {
	private AppConfig appConfig;
	private List<DataBeanObject> beanList;
	@Override
	protected ResponseMessage process() throws Exception {
		// TODO Auto-generated method stub
		ResponseMessage respMessage = new ResponseMessage(Error.SUCCESS);
		//更新和新建数据
		try{
			BeanAction action = (BeanAction) SpringContextHolder.getBean(this.strApi);
			if(action.upsert(this.beanList) < 0)respMessage = new ResponseMessage(Error.SYSTEM_ERROR);
		}catch(Exception e){
			e.printStackTrace();
			log.error("更新报文数据出错：" + e.getMessage(),e);
			respMessage = new ResponseMessage(Error.SYSTEM_ERROR);
		}
		return respMessage;
	}

	@Override
	protected boolean validateRequiredParameters() throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void validateParameters() throws Exception {
		// TODO Auto-generated method stub
		JSONArray dataList = this.json.getJSONArray(JsonInfoField.DATA_LIST);
		int length = dataList.size();
		this.beanList = new ArrayList<DataBeanObject>();
		try{
			String strClassName = "com.datacenter.bean." + this.appConfig.interFaceName.get(this.strApi);
			//Bean类型
			@SuppressWarnings("unchecked")
			Class<? extends DataBeanObject> cls = (Class<? extends DataBeanObject>)Class.forName(strClassName);
			for(int i = 0;i < length;i ++){
				this.beanList.add((DataBeanObject)JSONObject.toBean(dataList.getJSONObject(i), cls));
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("报文解析出错:" + e.getMessage(),e);
			throw e;
		}
		
	}

	@Override
	protected Error validateMessage(){
		//验证接口是否合法
		Error error = Error.SUCCESS;
		if(this.strApi == null || this.appConfig.interFaceName.get(this.strApi) == null){
			error = Error.UNSUPPORT_INTERFACE;
		}
		//验证签名是否正确
		String sid = header.getString(JsonInfoField.SID).toString();
		String signature = header.getString(JsonInfoField.SIGNATURE).toString();
		String strCheck = sid + appConfig.strToken;
		
		
		try{
			if(!EncodeUtil.checkSignature(strCheck, signature))error = Error.SIGNATURE_ERROR;
		}catch(Exception e){
			e.printStackTrace();
			error = Error.SYSTEM_ERROR;
		}
		return error;
	}
	
	public AppConfig getAppConfig() {
		return appConfig;
	}

	public void setAppConfig(AppConfig appConfig) {
		this.appConfig = appConfig;
	}
}
