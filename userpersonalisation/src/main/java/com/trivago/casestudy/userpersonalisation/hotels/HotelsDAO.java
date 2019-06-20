package com.trivago.casestudy.userpersonalisation.hotels;

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
public class HotelsDAO {

	/**
	 * 
	 * Get all the hotels selected by user in the past sorted by time stamp
	 * 
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	public Set<Hotel> getAllTheHotelsSelectedByUser(String userId) {

		List<Clicks> clicks = getAllTheHotelsSelectedByUserHelper(userId);

		return clicks.stream().sorted((o1, o2) -> o2.getTimestamp().compareTo(o1.getTimestamp()))
				.map(s -> Hotel.builder().hotelId(s.getHotelId()).hotelRegion(s.getHotelRegion())
						.build())
				.collect(Collectors.toSet());

	}

	/**
	 * 
	 * Out of all the hotels selected by user, Get the top k hotels selected by the user.
	 * 
	 * @param userId
	 * @param k
	 * @return
	 * @throws IOException
	 */
	public Set<Hotel> getTopKHotelsForUser(String userId, int k) {
		List<Clicks> selections = getAllTheHotelsSelectedByUserHelper(userId);

		List<Clicks> sortedList = new ArrayList<>(selections);


		sortedList.sort((o1, o2) -> o2.getTimestamp().compareTo(o1.getTimestamp()));


		HashMap<Hotel, Integer> map = new HashMap<>();
		for (Clicks sel : sortedList) {
			Hotel hotel = Hotel.builder().hotelId(sel.getHotelId())
					.hotelRegion(sel.getHotelRegion()).build();
			map.put(hotel, map.getOrDefault(hotel, 0) + 1);
		}

		return map.entrySet().stream()
				.sorted(Comparator.comparing(e -> e.getValue(), Comparator.reverseOrder())).limit(k)
				.map(h -> Hotel.builder().hotelId(h.getKey().getHotelId())
						.hotelRegion(h.getKey().getHotelRegion()).build())
				.collect(Collectors.toSet());


	}

	public List<Clicks> getAllTheHotelsSelectedByUserHelper(String userId) {

		Pattern pattern = Pattern.compile(",");
		List<Clicks> clicks = new ArrayList<>();
		try (BufferedReader in = new BufferedReader(new InputStreamReader(
				new FileInputStream(ClassLoader.getSystemResource("clicks.csv").getFile())));) {
			clicks = in.lines().map(line -> {
				String[] arr = pattern.split(line);
				if (arr.length == 4) {
					return Clicks.builder().timestamp(arr[0]).userId(arr[1]).hotelId(arr[2])
							.hotelRegion(arr[3]).build();

				} else {
					return Clicks.builder().timestamp(arr[0]).userId(arr[1]).hotelId(arr[2])
							.hotelRegion("").build();
				}
			}).collect(Collectors.toList());

			clicks = clicks.stream().filter(i -> i.getUserId().equals(userId))
					.collect(Collectors.toList());
		} catch (IOException ex) {
			log.error("Error occurred while reading CSV:{}", ex);
		}
		return clicks;

	}

}
