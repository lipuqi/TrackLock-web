package com.lettuce.air.cache;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.lettuce.air.pojo.basic.Cache;

/**
 * 存储类
 * @author Lando
 *
 * @param <K>
 * @param <V>
 */
@Component
public class MapCache<K, V extends Cache> {

	private ConcurrentHashMap<K, V> concurrentHashMap = new ConcurrentHashMap<K, V>();

	/**
	 * 存入数据
	 * @param key
	 * @param value
	 */
	public void put(K key, V value) {
		concurrentHashMap.put(key, value);
	}
	
	/**
	 * 取出数据
	 * @param key
	 * @return
	 */
	public V get(K key) {
		return concurrentHashMap.get(key);
	}
	
	/**
	 * 清除存储
	 * 主要用于清理掉过期的缓存
	 */
	public void cleanMap() {
		if (concurrentHashMap.size() != 0) {
			for (Entry<K, V> entry : concurrentHashMap.entrySet()) {
				Cache cache = (Cache) entry.getValue();
				if (cache.getExpiresIn() == null) {
					continue;
				}
				if (System.currentTimeMillis() >= cache.getExpiresIn()) {
					concurrentHashMap.remove(entry.getKey());
				}
			}
		}
	}

}
