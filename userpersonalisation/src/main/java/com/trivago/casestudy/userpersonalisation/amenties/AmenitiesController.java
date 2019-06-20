package com.trivago.casestudy.userpersonalisation.amenties;

import static com.trivago.casestudy.userpersonalisation.constants.Constants.AMENITIES_CACHE_KEY;
import static com.trivago.casestudy.userpersonalisation.constants.Constants.AMENITIES_CACHE_NAME;

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

@CacheConfig(cacheNames = {AMENITIES_CACHE_NAME})
@RestController
@RequestMapping("/services/api/v1")
@Slf4j
public class AmenitiesController {

	@Autowired
	AmenitiesService amenitiesService;

	/**
	 * 
	 * Get all the amenities for the user
	 * 
	 * @param userId
	 * @param n
	 * @return
	 * @throws IOException
	 */
	@Cacheable(value = AMENITIES_CACHE_NAME, key = AMENITIES_CACHE_KEY)
	@GetMapping("/amenities")
	public Set<String> getAllAmenties(@RequestParam final String userId,
			@RequestParam(required = false, defaultValue = "0") final int n) {

		log.info("Amenties Service started");

		return (n == 0) ? amenitiesService.getAllTheAmenitiesSelectedByUser(userId)
				: amenitiesService.getTopNAmenitiesForUser(userId, n);
	}

	/**
	 * 
	 * Get all the top N amenities for the user
	 * 
	 * @param userId
	 * @param n
	 * @return
	 * @throws IOException
	 */
	@Cacheable(value = AMENITIES_CACHE_NAME, key = AMENITIES_CACHE_KEY)
	@GetMapping("/top-amenities")
	public Set<String> getTopNAmentiesForUser(@RequestParam final String userId,
			@RequestParam final int n) {
		log.info("Amenties Service started");

		return amenitiesService.getTopNAmenitiesForUser(userId, n);
	}

}
