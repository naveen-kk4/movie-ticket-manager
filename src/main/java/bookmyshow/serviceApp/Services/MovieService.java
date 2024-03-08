package bookmyshow.serviceApp.Services;

import bookmyshow.serviceApp.DTOs.RequestDto.AddMovieDto;
import bookmyshow.serviceApp.Exceptions.MovieNotFoundException;
import bookmyshow.serviceApp.Models.Movie;
import bookmyshow.serviceApp.Models.Show;
import bookmyshow.serviceApp.Models.Ticket;
import bookmyshow.serviceApp.Repositories.MovieRepository;
import bookmyshow.serviceApp.Transformers.MovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(AddMovieDto addMovieDto) {
        Movie movie = MovieTransformer.convertDtoToEntity(addMovieDto);
        movieRepository.save(movie);
        return "Movie added successfully with id"+movie.getId();
    }

    public List<LocalTime> getShowTimeList(String movieName, String theatreName) throws MovieNotFoundException {
        List<LocalTime> localTimes = new ArrayList<>();
        Optional<Movie> movieOptional = movieRepository.findByMovieName(movieName);
        if(movieOptional.isEmpty())throw new MovieNotFoundException("requested movie is not present!");
        Movie movie = movieOptional.get();
        List<Show> showList = movie.getShowList();
        for(Show show : showList){
            if(show.getTheatre().getName().equals(theatreName))localTimes.add(show.getTime());
        }
        return localTimes;

            }

    public long totalRevenue(Integer id) throws MovieNotFoundException {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if(movieOptional.isEmpty())throw new MovieNotFoundException("the requested movie is not released");
        Movie movie = movieOptional.get();
        List<Show> showList = movie.getShowList();
        long total = 0;
        for(Show show : showList){
            for(Ticket ticket : show.getTicketList()){
                total+=ticket.getTotalPrice();
            }
        }
        return total;
    }

    public String hitOrFlop(Integer id) throws  MovieNotFoundException {
        long revenue = this.totalRevenue(id);
       if(revenue>=(1L*30000000))return "HIT";
       return "FLOP";
    }
}
