package com.lettuce.air.core.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lettuce.air.cache.MapCache;
import com.lettuce.air.pojo.basic.Cache;

@Component
@Configuration      
@EnableScheduling   
public class ScheduleTask {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleTask.class);
	
	@Autowired
	private MapCache<String, Cache> mapCache;
	
	/**
	 * 定时清理过期任务失败
	 */
    @Scheduled(cron = "0 0/1 * * * ?") //1分钟执行一次
    private void cleanMapTasks() {
		try {
			mapCache.cleanMap();
		} catch (Exception e) {
			LOGGER.error("定时清理任务失败", e);
		}
    }
    
}
