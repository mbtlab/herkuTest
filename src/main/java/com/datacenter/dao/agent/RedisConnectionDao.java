package com.datacenter.dao.agent;

import redis.clients.jedis.ShardedJedis;

/**
 * Redis资源链接接口
 * Date:2017-04-25
 * @author Leo
 *
 */
public interface RedisConnectionDao {
	/**
	 * 取得Redis客户端
	 * @return
	 */
	public abstract ShardedJedis getRedisClient();
	/**
	 * 释放资源pool
	 * @param shardedJedis
	 */
	public void returnResource(ShardedJedis shardedJedis);
	/**
	 * 出现异常后，释放资源回pool
	 * @param shardedJedis
	 * @param broken
	 */
	public void returnResource(ShardedJedis shardedJedis,boolean broken);
}
