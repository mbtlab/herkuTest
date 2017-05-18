package com.datacenter.impl.agent;

import org.apache.log4j.Logger;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import com.datacenter.dao.agent.RedisConnectionDao;

/**
 * Redis连接实现类
 * Date: 2017-04-25
 * @author Leo
 *
 */
public class RedisConnectionDaoImpl implements RedisConnectionDao {
	protected static final Logger log = Logger.getLogger(RedisConnectionDaoImpl.class);
	private ShardedJedisPool shardedJedisPool;
	
	
	@Override
	public ShardedJedis getRedisClient() {
		// TODO Auto-generated method stub
		try{
			ShardedJedis jedis = shardedJedisPool.getResource();
			return jedis;
		}catch(Exception e){
			e.printStackTrace();
			log.error("获取Redis客户端出错：" + e.getMessage(),e);
		}
		return null;
	}

	@Override
	public void returnResource(ShardedJedis shardedJedis) {
		// TODO Auto-generated method stub
		shardedJedisPool.returnResource(shardedJedis);
	}

	@Override
	public void returnResource(ShardedJedis shardedJedis, boolean broken) {
		// TODO Auto-generated method stub
		if(broken){
			shardedJedisPool.returnBrokenResource(shardedJedis);
		}else{
			shardedJedisPool.returnResource(shardedJedis);
		}
	}

	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}

	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

}
