package com.datacenter.initConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 系统初始化时获取基本配置文件信息，如数据库配置文件
 * Date: 2017-04-24
 * @author Leo
 *
 */
public class SysParamsPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {
	
	protected static final Logger log = Logger.getLogger(SysParamsPropertyPlaceholderConfigurer.class);
	private static final int APPNAME = 0;
	private static final int BEANNAME = 1;
	private AppConfig appConfig;
	@Override
	/**
	 * 读取配置文件
	 */
	protected void processProperties(ConfigurableListableBeanFactory arg0,
			Properties arg1) throws BeansException {
		// TODO Auto-generated method stub
		log.info("===============配置文件获取开始=====================");
		log.info("数据库用户账号：" + arg1.getProperty("db.username") + " 密码：" + arg1.getProperty("db.password"));
		log.info("日志表账号：" + arg1.getProperty("log.username") + " 密码：" + arg1.getProperty("log.password"));
		log.info("redis配置" + arg1.getProperty("redis.host"));
		
		int count = Integer.valueOf(arg1.getProperty("interFace.count")); 
		Map<String,String> mapInterFace = new HashMap<String,String>();
		for(int i = 0;i < count;i ++){
			String strKey = "interFace.name_" + (i + 1);
			String[] strIfNames = arg1.getProperty(strKey).split(":");
			if(strIfNames.length == 2){
				mapInterFace.put(strIfNames[APPNAME],strIfNames[BEANNAME]);
			}
		}
		
		appConfig.strToken = arg1.getProperty("token");
		appConfig.interFaceName = mapInterFace;
		log.info("===============配置文件获取结束=====================");
		
		super.processProperties(arg0, arg1);
	}
	public AppConfig getAppConfig() {
		return appConfig;
	}
	public void setAppConfig(AppConfig appConfig) {
		this.appConfig = appConfig;
	}
	
	
}
