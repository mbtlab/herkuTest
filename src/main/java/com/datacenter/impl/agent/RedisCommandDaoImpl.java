package com.datacenter.impl.agent;

import org.apache.log4j.Logger;

import redis.clients.jedis.ShardedJedis;

import com.datacenter.dao.agent.RedisCommandDao;
import com.datacenter.dao.agent.RedisConnectionDao;

public class RedisCommandDaoImpl implements RedisCommandDao {
	protected static final Logger log = Logger.getLogger(RedisCommandDaoImpl.class);
	private RedisConnectionDao redisConnection;

	@Override
	public String insertKeyValue(String key, String value) {
		// TODO Auto-generated method stub
		String result = null;
		ShardedJedis jedis = redisConnection.getRedisClient();
		if(jedis == null)return result;
		boolean broken = false;
		try{
			result = jedis.set(key, value);
		}catch(Exception e){
			e.printStackTrace();
			log.error("插入键值对出错：" + e.getMessage(),e);
			broken = true;
		}finally{
			redisConnection.returnResource(jedis, broken);
		}
		return result;
	}

	@Override
	public String selectKeyValue(String key) {
		// TODO Auto-generated method stub
		String value = null;
		ShardedJedis jedis = redisConnection.getRedisClient();
		if(jedis == null)return value;
		boolean broken = false;
		try{
			value = jedis.get(key);
		}catch(Exception e){
			e.printStackTrace();
			log.error("查询键值对出错：" + e.getMessage(),e);
			broken = true;
		}finally{
			redisConnection.returnResource(jedis, broken);
		}
		return value;
	}
	
	

	@Override
	public Long pushItem(String key, String Item) {
		// TODO Auto-generated method stub
		Long result = new Long(-1);
		Boolean broken = false;
		ShardedJedis jedis = redisConnection.getRedisClient();
		if(jedis == null)return result;
		try{
			result = jedis.rpush(key, Item);
		}catch(Exception e){
			e.printStackTrace();
			log.error("插入元素到链表出错：" + e.getMessage(),e);
			broken = true;
		}finally{
			redisConnection.returnResource(jedis, broken);
		}
		return result;
	}
	
	@Override
	public Long pushItem(String key, String[] Items) {
		// TODO Auto-generated method stub
		Long result = new Long(-1);
		Boolean broken = false;
		ShardedJedis jedis = redisConnection.getRedisClient();
		if(jedis == null)return result;
		try{
			result = jedis.rpush(key, Items);
		}catch(Exception e){
			e.printStackTrace();
			log.error("插入元素数组到链表出错：" + e.getMessage(),e);
			broken = true;
		}finally{
			redisConnection.returnResource(jedis, broken);
		}
		return result;
	}

	@Override
	public Long getLength(String key) {
		// TODO Auto-generated method stub
		boolean broken = false;
		Long result = new Long(-1);
		ShardedJedis jedis = redisConnection.getRedisClient();
		if(jedis == null)return result;
		try{
			result = jedis.llen(key);
		}catch(Exception e){
			e.printStackTrace();
			log.error("查询链表长度出错：" + e.getMessage(),e); 
			broken = true;
		}finally{
			redisConnection.returnResource(jedis, broken);
		}
		return result;
	}

		
	
	public RedisConnectionDao getRedisConnection() {
		return redisConnection;
	}

	public void setRedisConnection(RedisConnectionDao redisConnection) {
		this.redisConnection = redisConnection;
	}


	
	

}
