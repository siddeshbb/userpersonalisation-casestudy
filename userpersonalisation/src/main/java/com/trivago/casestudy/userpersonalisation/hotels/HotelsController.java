package com.trivago.casestudy.userpersonalisation.hotels;

import static com.trivago.casestudy.userpersonalisation.constants.Constants.HOTELS_CACHE_KEY;
import static com.trivago.casestudy.userpersonalisation.constants.Constants.HOTELS_CACHE_NAME;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@CacheConfig(cacheNames = {HOTELS_CACHE_NAME})
@RestController
@RequestMapping("/services/api/v1")
@Slf4j
public class HotelsController {

	@Autowired
	HotelsService hotelsService;

	/**
	 * 
	 * 
	 * @param userId
	 * @param k
	 * @return
	 * @throws IOException
	 */
	@Cacheable(value = HOTELS_CACHE_NAME, key = HOTELS_CACHE_KEY)
	@GetMapping("/hotels")
	public Set<Hotel> getAllHotels(@RequestParam String userId,
			@RequestParam(required = false, defaultValue = "0") Integer k) {

		log.info("Hotels Service started");

		return (k == 0) ? hotelsService.getAllTheHotelsSelectedByUser(userId)
				: hotelsService.getTopKHotelsSelectedByUser(userId, k);
	}

	/**
	 * 
	 * @param userId
	 * @param k
	 * @return
	 * @throws IOException
	 */
	@Cacheable(value = HOTELS_CACHE_NAME, key = HOTELS_CACHE_KEY)
	@GetMapping("/top-hotels")
	public Set<Hotel> getTopKHotelsForUser(@RequestParam String userId, @RequestParam int k) {
		log.info("Hotels Top Service started");

		return hotelsService.getTopKHotelsSelectedByUser(userId, k);
	}

}
