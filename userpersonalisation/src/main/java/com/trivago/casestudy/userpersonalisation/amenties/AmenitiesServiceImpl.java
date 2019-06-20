package com.trivago.casestudy.userpersonalisation.amenties;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmenitiesServiceImpl implements AmenitiesService {

	@Autowired
	AmenitiesDAO amenitiesDAO;

	/**
	 * 
	 * Get all the amenities selected by the user
	 * 
	 */
	@Override
	public Set<String> getAllTheAmenitiesSelectedByUser(String userId) {
		return amenitiesDAO.getAllTheAmenitiesSelectedByUser(userId);
	}

	/**
	 * 
	 * Get all the amenities selected by the user
	 * 
	 */
	@Override
	public Set<String> getTopNAmenitiesForUser(String userId, Integer n) {
		return amenitiesDAO.getTopNAmenitiesForUser(userId, n);
	}

}
