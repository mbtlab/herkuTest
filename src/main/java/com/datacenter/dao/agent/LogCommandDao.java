package com.datacenter.dao.agent;

/**
 * 操作日志表接口
 * Date: 2017-04-24
 * @author Leo
 *
 */
public interface LogCommandDao {
	//插入表
	public int insertLog(String flag,String api,String sid,long logTime,long spendTime,String requestData,String responseData);
	
}
