package com.vanke.action;

import java.util.List;

import com.datacenter.bean.DataBeanObject;
import com.datacenter.dao.agent.DBConnectionDao;

/**
 * 数据处理的基类
 * Date: 2017-05-03
 * @author Leo
 *
 */
public abstract class BeanAction {
	protected DBConnectionDao dbConn;
	/**
	 * 更新和新建对应数据
	 * @param beanList
	 * @return
	 */
	public abstract int upsert(List<? extends DataBeanObject> beanList);
	public DBConnectionDao getDbConn() {
		return dbConn;
	}
	public void setDbConn(DBConnectionDao dbConn) {
		this.dbConn = dbConn;
	}
	
}
