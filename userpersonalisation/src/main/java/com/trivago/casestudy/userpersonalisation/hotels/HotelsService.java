package com.trivago.casestudy.userpersonalisation.hotels;

import java.util.Set;

public interface HotelsService {

	public Set<Hotel> getAllTheHotelsSelectedByUser(String userId);

	public Set<Hotel> getTopKHotelsSelectedByUser(String userId, Integer k);
}
