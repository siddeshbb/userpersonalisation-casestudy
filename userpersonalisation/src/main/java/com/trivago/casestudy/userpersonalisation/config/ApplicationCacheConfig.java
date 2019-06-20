package com.trivago.casestudy.userpersonalisation.config;

import static com.trivago.casestudy.userpersonalisation.constants.Constants.AMENITIES_CACHE_NAME;
import static com.trivago.casestudy.userpersonalisation.constants.Constants.HOTELS_CACHE_NAME;
import static com.trivago.casestudy.userpersonalisation.constants.Constants.TEN_MINUTES_IN_MS;

import java.util.Arrays;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableCaching
@Slf4j
public class ApplicationCacheConfig {

	@Bean
	public CacheManager cacheManager() {
		final SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache(AMENITIES_CACHE_NAME),
				new ConcurrentMapCache(HOTELS_CACHE_NAME)));
		return cacheManager;
	}

	/**
	 * Method to evict all caches
	 */
	public void evictAllCaches() {
		log.info("Clearing of cache started: {}", this.cacheManager().getCacheNames());
		this.cacheManager().getCacheNames().stream()
				.forEach(cacheName -> this.cacheManager().getCache(AMENITIES_CACHE_NAME).clear());

		this.cacheManager().getCacheNames().stream()
				.forEach(cacheName -> this.cacheManager().getCache(HOTELS_CACHE_NAME).clear());
	}

	/**
	 * Scheduled Job to evict all caches after certain interval (5 seconds).
	 */
	@Scheduled(fixedRate = TEN_MINUTES_IN_MS)
	@Caching(evict = {@CacheEvict(AMENITIES_CACHE_NAME), @CacheEvict(value = HOTELS_CACHE_NAME)})
	public void evictAllcachesAtIntervals() {
		log.info("Clearing of cache started");
		evictAllCaches();
		log.info("Clearing of cache ended");
	}

}
