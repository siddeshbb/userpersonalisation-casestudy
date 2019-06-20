package com.trivago.casestudy.userpersonalisation.hotels;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@Data
public class Clicks {

	@EqualsAndHashCode.Exclude
	String timestamp;

	String userId;

	String hotelId;

	@EqualsAndHashCode.Exclude
	String hotelRegion;

}
