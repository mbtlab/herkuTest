package com.vanke.handler;

import net.sf.json.JSONArray;

import com.datacenter.bean.ResponseMessage;
import com.datacenter.common.SpringContextHolder;
import com.datacenter.dao.agent.RedisCommandDao;
import com.datacenter.handler.AbsCommandHandler;
import com.datacenter.initConfig.AppConfig;
import com.datacenter.initConfig.Error;
import com.datacenter.initConfig.JsonInfoField;

public class HerokuDataHandler extends AbsCommandHandler {
	private AppConfig appConfig;

	private JSONArray dataList;//请求报文数据部分
	@Override
	protected ResponseMessage process() throws Exception {
		// TODO Auto-generated method stub
		ResponseMessage respMessage = new ResponseMessage(Error.SUCCESS);
		
		//将获取到的dataList放入Redis对应队列中
		try{
			RedisCommandDao redisCommand = (RedisCommandDao) SpringContextHolder.getBean("redisCommand");//获取redis客户端
			int intDataLength = this.dataList.size();
			log.info("请求报文数据长度：" + intDataLength);
			String[] strItems = new String[intDataLength];
			for(int i = 0;i < intDataLength;i ++){
				strItems[i] = this.dataList.getString(i);
			}
			Long longCount = redisCommand.pushItem(this.strApi, strItems);
			if(longCount < 0){
				log.error("插入数据失败：" + longCount);
				respMessage = new ResponseMessage(Error.SYSTEM_ERROR);
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("处理报文数据出错：" + e.getMessage(),e);
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
		this.dataList = json.getJSONArray(JsonInfoField.DATA_LIST);
	}

	@Override
	protected Error validateMessage() {
		// TODO Auto-generated method stub
		//验证接口是否合法
		Error error = Error.SUCCESS;
		if(this.strApi == null || this.appConfig.interFaceName.get(this.strApi) == null){
			error = Error.UNSUPPORT_INTERFACE;
		}
		//验证报文数据报文是否符合
		
			
		return error;
	}

	public AppConfig getAppConfig() {
		return appConfig;
	}

	public void setAppConfig(AppConfig appConfig) {
		this.appConfig = appConfig;
	}

	
}
