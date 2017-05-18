package com.datacenter.handler;

import java.io.IOException;
import java.io.InputStream;

import com.datacenter.initConfig.Error;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.datacenter.bean.ResponseMessage;
import com.datacenter.common.SpringContextHolder;
import com.datacenter.dao.agent.LogCommandDao;
import com.datacenter.initConfig.JsonInfoField;

public abstract class AbsCommandHandler {
	protected static final Logger log = Logger.getLogger(AbsCommandHandler.class);
	protected static final long MAX_CONTENT_LENGTH = 10 * 1024 * 1024;//最大请求包大小 10M
	protected JSONObject json;//请求报文json
	protected JSONObject header;//请求报文header
	protected String strApi;//请求报文接口名称
	protected String strJson;//请求报文文本格式
	
	/**
	 * 默认构造方法
	 */
	public AbsCommandHandler(){
	};
	/**
	 * 记录请求日志
	 * @param beginTime
	 * @param requestData
	 * @param repMessage
	 * @param sid
	 * @param api
	 * @return
	 */
	protected String response(long beginTime,String requestData,ResponseMessage repMessage){		
		String strResp = repMessage.getJson().toString();
		long now = System.currentTimeMillis();
		long spendTime = now - beginTime;
		try{
			LogCommandDao logCommand = (LogCommandDao) SpringContextHolder.getBean("logCommand");
			String flag = repMessage.getErrCode() + "";
			String sid = header.getString(JsonInfoField.SID).toString();
			String api = strApi;
			logCommand.insertLog(flag, api, sid, beginTime, spendTime, this.strJson, strResp);
		}catch(Exception e){
			e.printStackTrace();
			log.error("保存日志记录出错：" + e.getMessage(),e);
			strResp = Error.UNKNOWN.toJSON();
		}
		return strResp;
	}
	/**
	 * 记录请求日志
	 * @param beginTime 开始时间
	 * @param requestData 请求报文
	 * @param err 响应信息
	 * @param sid 
	 * @param api
	 * @return
	 */
	protected String response(long beginTime,String requestData,Error err){
		ResponseMessage rpm = new ResponseMessage(err);
		return response(beginTime,requestData,rpm);
	}
	/**
	 * 读取请求消息体
	 * @return
	 */
	protected JSONObject retriveMessage(HttpServletRequest request) {
		try {
			int contentLength = request.getContentLength();
			// 读取消息体
			InputStream is = request.getInputStream();
			byte[] buf = new byte[contentLength];
			int total = 0, read = 0;
			// 按contentLength循环读取数据，多余的丢弃
			while (total != contentLength) {
				if ((read = is.read(buf, total, contentLength - total)) == 0) {
					break;
				}
				total += read;
			}
			String jsonStr = new String(buf, "UTF-8");
			this.strJson = jsonStr;
			log.info("request protocol body:" + jsonStr);
			return JSONObject.fromObject(jsonStr);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	/**
	 * 验证请求
	 * @return
	 */
	protected Error validateRequset(HttpServletRequest request) {
		Error err = Error.SUCCESS;
		try {
			int contentLength = request.getContentLength();
			// 请求数据丢失
			if (contentLength <= 0) {
				err = Error.REQUEST_DATA_MISSING;
				return err;
			}
			// 最大请求数据长度
			if (contentLength > MAX_CONTENT_LENGTH) {
				err = Error.REQUEST_DATA_TOO_LONG;
				return err;
			}
		} catch (Exception e) {
			e.printStackTrace();
			err = Error.UNKNOWN;
		}
		return err;
	}
	/**
	 * 处理请求报文函数
	 * @return
	 * @throws Exception
	 */
	protected ResponseMessage handleRequest() throws Exception {
		ResponseMessage response = null;
		try {
			
			if(!validateRequiredParameters()){
				//验证参数完整性
				return response = new ResponseMessage(Error.PARAMETER_MISSING);
			}
			try{
				//对传入参数进行处理
				validateParameters();
			}catch(Exception e){
				return new ResponseMessage(Error.PARSE_PARAMETER_ERROR);
			}
			// 业务处理
			response = process();
		} catch (Exception e) {
			// log.error(e.getMessage(),e);
			e.printStackTrace();
			log.error("接口处理出错：" + e.getMessage(),e);
			response = new ResponseMessage(Error.SYSTEM_ERROR);
		}
		log.info("response:" + response.getJson());
		return response;
	}
	
	/**
	 * 处理函数入口
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String handler(HttpServletRequest request) throws ServletException, IOException {
		long beginTime = System.currentTimeMillis();//获取当前时间戳
		try{
			//校验请求报文（报文是否为空和长度是否在5M以下）
			Error err = validateRequset(request);
			if(!err.success()){
				//请求验证不通过 -- 由于报文为空或者过大，所以不存入日志表中
				return err.toJSON();
			}
			//读取请求报文信息
			this.json = this.retriveMessage(request);
			if(this.json == null || this.strJson == null){
				//解析错误的情况 由于读取不到正确的json报文，所以保存到日志表中不存在任何有效信息，所以不予保存
				log.error("解析请求request出错!!!");
				return Error.PARSE_PARAMETER_ERROR.toJSON();
			}
			//报文信息获取后，将必要信息保存到变量中
			this.header = this.json.getJSONObject(JsonInfoField.HEADER);
			this.strApi = this.json.getString(JsonInfoField.CMD);
			//验证报文格式
			err = this.validateMessage();
			if(!err.success()){
				//出现验证报文格式错误一般为接口名，数据字段有误
				return this.response(beginTime, this.strJson, err);
			}
			//所有验证完成后，可以进行实际处理
			ResponseMessage respResult = this.handleRequest();
			return this.response(beginTime, this.strJson, respResult);
		}catch(Exception e){
			log.error(e.getMessage(), e);
			//如果处理过程中出错，保存日志并返回系统处理失败
			return this.response(beginTime, this.strJson, Error.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 业务处理逻辑
	 * @return
	 * @throws Exception
	 */
	protected abstract ResponseMessage process() throws Exception;	
	/**
	 * 验证参数有效性(数据参数数据是否缺失)
	 * @return
	 * @throws Exception
	 */
	protected abstract boolean validateRequiredParameters() throws Exception;
	/**
	 * 传入参数处理
	 * @throws Exception
	 */
	protected abstract void validateParameters() throws Exception;
	/**
	 * 验证报文格式 （接口名称，报文格式）
	 * @return
	 */
	protected abstract Error validateMessage();
	
}
