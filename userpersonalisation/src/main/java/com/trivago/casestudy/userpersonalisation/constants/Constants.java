package com.trivago.casestudy.userpersonalisation.constants;

public class Constants {

	public static final String AMENITIES_CACHE_NAME = "amenities";

	public static final String HOTELS_CACHE_NAME = "hotels";

	public static final String AMENITIES_CACHE_KEY = "#userId.concat('-').concat(#n)";

	public static final String HOTELS_CACHE_KEY = "#userId.concat('-').concat(#k)";

	public static final long TEN_MINUTES_IN_MS = 600000;

}
