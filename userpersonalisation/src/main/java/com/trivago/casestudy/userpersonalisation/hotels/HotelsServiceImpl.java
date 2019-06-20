package com.trivago.casestudy.userpersonalisation.hotels;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelsServiceImpl implements HotelsService {

	@Autowired
	HotelsDAO hotelsDAO;

	/**
	 * Gets all the hotels selected by the user.
	 * 
	 */
	@Override
	public Set<Hotel> getAllTheHotelsSelectedByUser(String userId) {
		return hotelsDAO.getAllTheHotelsSelectedByUser(userId);
	}

	/**
	 * Gets the top k hotels selected by the user
	 *
	 */
	@Override
	public Set<Hotel> getTopKHotelsSelectedByUser(String userId, Integer k) {
		return hotelsDAO.getTopKHotelsForUser(userId, k);
	}

}
