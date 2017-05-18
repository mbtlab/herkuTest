package com.datacenter.initConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统配置保存
 * Date: 2017-04-26
 * @author Leo
 *
 */
public class AppConfig {
	public Map<String,String> interFaceName = new HashMap<String,String>();
	public String strToken;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder strKey = new StringBuilder();
		for(String key : interFaceName.keySet()){
			strKey.append(key + "  ");
		}
		String result = new String(strKey); 
		return result;
	}
	
}
