package com.trivago.casestudy.userpersonalisation.amenties;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@Data
public class Selections {

	@EqualsAndHashCode.Exclude
	String timestamp;

	String userId;

	@EqualsAndHashCode.Exclude
	String amenityId;

}
