package com.example.exercises;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.example.dao.CountryDao;
import com.example.dao.InMemoryWorldDao;
import com.example.domain.Country;


/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise6 {
	private static final CountryDao countryDao = InMemoryWorldDao.getInstance();

	public static void main(String[] args) {
		// Sort the countries by number of their cities in descending order
//		Function<CountryCityCountPair, Integer> countExtracter = CountryCityCountPair::count;
//		var countriesWithCityCountInDescOrder = countryDao.findAllCountries()
//		          .stream()
//		          .map(country -> new CountryCityCountPair(country,country.getCities().size()))
//		          .sorted(Comparator.comparing(countExtracter).reversed())
//		          .toList();
//		countriesWithCityCountInDescOrder.forEach(System.out::println);
















//		var countriesSortedByNumberOfCities = countryDao.findAllCountries().stream()
//				.map(country -> new CountryCityCountPair(country, country.getCities().size()))
//				.sorted(Comparator.comparingInt(CountryCityCountPair::count).reversed());
//
//		countriesSortedByNumberOfCities.forEach(
//				countryCityCountPair -> System.out.printf("\n%s has \t%d cities", countryCityCountPair.country().getName(), countryCityCountPair.count())
//		);

//		countriesSortedByNumberOfCities.forEach(
//				countryCityCountPair -> System.out.printf("\n%s has \t%d cities", countryCityCountPair.country().getName(), countryCityCountPair.count())
//		);


		var countriesSortedByNumberOfCities = countryDao.findAllCountries().stream()
				.collect(Collectors.toMap(
						country -> country.getName(),
						country -> country.getCities().size()
				)).entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toMap(
						Map.Entry::getKey,
						Map.Entry::getValue,
						(oldValue, newValue) -> oldValue,
						LinkedHashMap::new
				));

		//var countriesSortedByNumberOfCities = countriesByNumberOfCities.entrySet().stream()

		countriesSortedByNumberOfCities.forEach(
				(key, value) -> System.out.printf("\n%s has \t%d cities",key, value)
		);

	}

}
