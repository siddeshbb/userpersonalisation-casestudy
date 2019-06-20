package com.trivago.casestudy.userpersonalisation.hotels;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@Data
@EqualsAndHashCode
public class Hotel {

	String hotelId;

	String hotelRegion;

}
