package com.trivago.casestudy.userpersonalisation.amenties;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class AmenitiesDAO {

	/**
	 * 
	 * Get all the amenities selected by user
	 * 
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	public Set<String> getAllTheAmenitiesSelectedByUser(String userId) {

		List<Selections> selections = getAllTheAmenitiesSelectedByUserHelper(userId);

		return selections.stream()
				.sorted((o1, o2) -> o2.getTimestamp().compareTo(o1.getTimestamp()))
				.map(s -> String.valueOf(s.getAmenityId())).collect(Collectors.toSet());

	}

	public Set<String> getTopNAmenitiesForUser(String userId, int n) {
		List<Selections> selections = getAllTheAmenitiesSelectedByUserHelper(userId);


		selections.sort((o1, o2) -> o2.getTimestamp().compareTo(o1.getTimestamp()));


		HashMap<String, Integer> map = new HashMap<>();
		for (Selections sel : selections) {
			map.put(sel.getAmenityId(), map.getOrDefault(sel.getAmenityId(), 0) + 1);
		}

		return map.entrySet().stream()
				.sorted(Comparator.comparing(e -> e.getValue(), Comparator.reverseOrder())).limit(n)
				.map(e -> String.valueOf(e.getKey())).collect(Collectors.toSet());


	}

	public List<Selections> getAllTheAmenitiesSelectedByUserHelper(String userId) {

		List<Selections> selections = new ArrayList<>();
		Pattern pattern = Pattern.compile(",");
		try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(
				ClassLoader.getSystemResource("asset-selections.csv").getFile())));) {
			selections = in.lines().map(line -> {
				String[] arr = pattern.split(line);
				return Selections.builder().timestamp(arr[0]).userId(arr[1]).amenityId(arr[2])
						.build();
			}).collect(Collectors.toList());
			selections = selections.stream().filter(i -> i.getUserId().equals(userId))
					.collect(Collectors.toList());
		} catch (IOException ex) {
			log.error("Error occurred while reading CSV:{}", ex);
		}

		return selections;

	}

}
