package com.example.exercises;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.service.InMemoryMovieService;
import com.example.service.MovieService;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise7 {
	private static final MovieService movieService = InMemoryMovieService.getInstance();

	public static void main(String[] args) {
		// Find the list of movies having the genres "Drama" and "Comedy" only
//		var listOfGenreNames = List.of("Drama", "Comedy");
//		var listOfGenres = listOfGenreNames.stream().map(movieService::findGenreByName).toList();
//		var moviesInDramaAndComedyOnly =
//		movieService.findAllMovies()
//		            .stream()
//		            .filter( movie -> movie.getGenres().size() == 2)
//		            .filter( movie -> movie.getGenres().containsAll(listOfGenres))
//		            .toList();
//		moviesInDramaAndComedyOnly.forEach(movie -> System.out.printf("%s %s\n",movie,movie.getGenres()));














		var dramaAndComedyMovies = movieService.findAllMovies().stream()
				.collect(Collectors.toMap(
						movie -> movie,
						movie -> movie.getGenres().stream().map(genre -> genre.getName()).toList()
				)).entrySet().stream()
						.filter(movieListEntry ->
								movieListEntry.getValue().contains("Drama")
										&& movieListEntry.getValue().contains("Comedy"))
				.filter(movieListEntry -> movieListEntry.getValue().size() == 2)
						.collect(Collectors.toMap(
								Map.Entry::getKey,
								Map.Entry::getValue

		));

		dramaAndComedyMovies.forEach(
				(key, value) -> System.out.printf("\n%s \t%s", key, value)
		);
	}

}
