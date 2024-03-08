package bookmyshow.serviceApp.Transformers;

import bookmyshow.serviceApp.DTOs.RequestDto.AddMovieDto;
import bookmyshow.serviceApp.Models.Movie;

public class MovieTransformer {
    public static Movie convertDtoToEntity(AddMovieDto addMovieDto) {
        Movie movie = Movie.builder().movieName(addMovieDto.getMovieName())
                     .genre(addMovieDto.getGenre()).language(addMovieDto.getLanguage())
                      .duration(addMovieDto.getDuration()).rating(addMovieDto.getRating())
                        .releaseDate(addMovieDto.getReleaseDate()).director(addMovieDto.getDirector()).build();
                return movie;
    }
}
