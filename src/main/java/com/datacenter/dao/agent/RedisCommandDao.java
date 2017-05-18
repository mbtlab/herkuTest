package com.datacenter.dao.agent;

/**
 * Redis客户端操作接口
 * Date: 2017-04-25
 * @author Leo
 *
 */
public interface RedisCommandDao {
	/**
	 * 插入一个键值对
	 * @param key 
	 * @param value
	 * @return
	 */
	public String insertKeyValue(String key,String value);
	/**
	 * 根据键获取值
	 * @param key 
 	 * @return
	 */
	public String selectKeyValue(String key);
	/**
	 * 将元素压入链表尾部
	 * @param key 链表名称
	 * @param Item 元素
	 * @return 返回插入数据后的链表长度
	 */
	public Long pushItem(String key,String Item);
	/**
	 * 将元素压入链表尾部（接收一个数组参数）
	 * @param key
	 * @param Items
	 * @return 返回插入数据后的链表长度
	 */
	public Long pushItem(String key,String[] Items);
	/**
	 * 获得链表的长度
	 * @param key
	 * @return
	 */
	public Long getLength(String key);
}
