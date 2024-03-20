package com.example.exercises;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.example.dao.CityDao;
import com.example.dao.CountryDao;
import com.example.dao.InMemoryWorldDao;
import com.example.domain.City;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise5 {
	private static final CountryDao countryDao = InMemoryWorldDao.getInstance();
	private static final CityDao cityDao = InMemoryWorldDao.getInstance();

	public static void main(String[] args) {
		// Find the highest populated capital city of each continent
//		Function<ContinentCityPair, City> extractCity = ContinentCityPair::city;
//		var highPopulatedCapitalCityOfEachContinent =
//				countryDao.findAllCountries()
//				          .stream()
//				          .map(country -> new ContinentCityPair(country.getContinent(),cityDao.findCityById(country.getCapital())))
//				          .filter(pair -> Objects.nonNull(pair.city()))
//				          .collect(Collectors.groupingBy(ContinentCityPair::continent,Collectors.maxBy(Comparator.comparing(extractCity.andThen(City::getPopulation)))));
//		highPopulatedCapitalCityOfEachContinent.forEach((continent,pair) -> System.out.printf("%s: %s\n",continent,pair.get().city()));
//	}

		var mostPopulatedCapitalEachContinent = countryDao.getAllContinents().stream()
				.map(continent -> new ContinentCityPair(continent, countryDao.findCountriesByContinent(continent).stream()
						.map(country -> cityDao.findCityById(country.getCapital())).filter(Objects::nonNull).max(Comparator.comparingInt(City::getPopulation))
						.filter(Objects::nonNull)
						.stream().findFirst().orElse(null))).filter(continentCityPair -> Objects.nonNull(continentCityPair.city()));

		mostPopulatedCapitalEachContinent.filter(continentCityPair -> Objects.nonNull(continentCityPair.city()))
		.forEach(continentCityPair ->
				System.out.printf("%s\t %s\t %d\n", continentCityPair.continent(), continentCityPair.city().getName(), continentCityPair.city().getPopulation()));
	}
}