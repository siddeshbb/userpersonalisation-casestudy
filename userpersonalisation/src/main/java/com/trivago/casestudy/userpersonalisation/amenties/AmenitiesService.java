package com.trivago.casestudy.userpersonalisation.amenties;

import java.util.Set;

public interface AmenitiesService {

	public Set<String> getAllTheAmenitiesSelectedByUser(String userId);

	public Set<String> getTopNAmenitiesForUser(String userId, Integer n);

}
