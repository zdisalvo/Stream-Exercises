package com.example.exercises;

import java.util.*;
import java.util.stream.Collectors;

import com.example.dao.CityDao;
import com.example.dao.CountryDao;
import com.example.dao.InMemoryWorldDao;
import com.example.domain.City;
import com.example.domain.Country;


/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise2 {
	private static final CountryDao countryDao = InMemoryWorldDao.getInstance();
	private static final CityDao cityDao = InMemoryWorldDao.getInstance();

	public static void main(String[] args) {
		// Find the most populated city of each continent
		var highPopulatedCityOfEachContinent =
				countryDao.findAllCountries()
				          .stream()
				          .map( country -> country.getCities().stream().map(city -> new ContinentCityPair(country.getContinent(),city)).toList())
				          .flatMap(Collection::stream)
				          .collect(Collectors.groupingBy(ContinentCityPair::continent,Collectors.maxBy( ContinentCityPair::compareTo )));
		highPopulatedCityOfEachContinent.forEach(ContinentCityPair::printEntry);








//	List<City> populationSortedCitiesByContinent = cityDao.findAllCities()
//			.stream().sorted(Comparator.comparingInt(City::getPopulation).reversed())
//			.collect(Collectors.toList());
//
//	//
//		System.out.println(populationSortedCitiesByContinent.size());
//
//	Map<City, String> sortedCityAndContinent = populationSortedCitiesByContinent.stream()
//			.collect(Collectors.toMap(c -> c, (c) ->
//				countryDao.findCountryByCode(c.getCountryCode()).getContinent()
//			));
//
//	//
//		System.out.println(sortedCityAndContinent.size());
//
////		String cont = "Africa";
////
////		City topC = sortedCityAndContinent.entrySet().stream()
////				.filter(e -> e.getValue().equals(cont))
////				.findFirst().get().getKey();
////
////		System.out.println(topC);
//
//
//		List<City> topCities = countryDao.getAllContinents().stream()
//			.map(c -> {
//
//				try {
//					City topCity = sortedCityAndContinent.entrySet().stream()
//							.filter(e -> e.getValue().equals(c))
//							.findFirst().get().getKey();
//					return topCity;
//				} catch (NoSuchElementException e) {
//					return new City();
//				}
//			}).collect(Collectors.toList());
//
//		Map<City, String> topCitiesAndContinents = countryDao.getAllContinents().stream()
//				.map(c -> {
//
//						Optional<Map.Entry<City, String>> city = sortedCityAndContinent.entrySet().stream()
//								.filter(e -> e.getValue().equals(c))
//								.findFirst();
//						return city.map(Map.Entry::getKey).orElse(null);
//				}).filter(Objects::nonNull)
//				.collect(Collectors.toMap(
//						city-> city,
//						city -> sortedCityAndContinent.get(city)
//				));
//
//		for (Map.Entry tc : topCitiesAndContinents.entrySet()){
//				System.out.println(tc);
//		}
//
//		System.out.println();
//
//		System.out.println(countryDao.getAllContinents());
//
//
////		for (City t : topCities) {
////			System.out.println(t);
////		}

	}

}