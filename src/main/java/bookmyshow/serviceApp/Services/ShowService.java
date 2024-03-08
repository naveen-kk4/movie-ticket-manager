package bookmyshow.serviceApp.Services;

import bookmyshow.serviceApp.DTOs.RequestDto.AddShowDto;
import bookmyshow.serviceApp.DTOs.RequestDto.AddShowSeatsDto;
import bookmyshow.serviceApp.Enums.SeatType;
import bookmyshow.serviceApp.Exceptions.MovieNotFoundException;
import bookmyshow.serviceApp.Exceptions.ShowNotFoundException;
import bookmyshow.serviceApp.Exceptions.TheatreNotFoundException;
import bookmyshow.serviceApp.Models.*;
import bookmyshow.serviceApp.Repositories.MovieRepository;
import bookmyshow.serviceApp.Repositories.ShowRepository;
import bookmyshow.serviceApp.Repositories.TheatreRepository;
import bookmyshow.serviceApp.Transformers.ShowTranformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ShowService {

    @Autowired
    ShowRepository showRepository;
    @Autowired
    TheatreRepository theatreRepository;
    @Autowired
    MovieRepository movieRepository;

    public String addShow(AddShowDto addShowDto) throws TheatreNotFoundException,MovieNotFoundException {
        Show show = ShowTranformer.convertDtoToEntity(addShowDto);
        Optional<Theatre> theatreOptional = theatreRepository.findById(addShowDto.getTheatreId());
        if(theatreOptional.isEmpty())throw new TheatreNotFoundException("Theatre not found");
        Optional<Movie> movieOptional = movieRepository.findById(addShowDto.getMovieId());
        if(movieOptional.isEmpty())throw new MovieNotFoundException("Movie not found");
        Theatre theatre = theatreOptional.get();
        Movie movie = movieOptional.get();
        show.setMovie(movie);
        show.setTheatre(theatre);
        showRepository.save(show);
        movie.getShowList().add(show);
        theatre.getShowList().add(show);
        movieRepository.save(movie);
        theatreRepository.save(theatre);
        return "New Show added successfully";


    }
    public String getMovieName(AddShowDto showDto){

        Integer movieId = showRepository.getMostShowedMovie(showDto.getDate());

        Movie movie = movieRepository.findById(movieId).get();

        return movie.getMovieName();


    }

    public String addshowSeats(AddShowSeatsDto addShowSeatsDto) {
        Optional<Show> showOptional = showRepository.findById(addShowSeatsDto.getShowId());
        if(showOptional.isEmpty())throw new ShowNotFoundException("Show is not present in DB");
        Show show = showOptional.get();
        int premiumPrice = addShowSeatsDto.getPremiumPrice();
        int classicPrice = addShowSeatsDto.getClassicPrice();
        Theatre theatre = show.getTheatre();
        List<TheatreSeat> theatreSeatList = theatre.getTheatreSeatList();
        List<ShowSeat> showSeatList = show.getShowSeatList();
        for(TheatreSeat theatreSeat : theatreSeatList){
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeatNo(theatreSeat.getSeatNo());
            showSeat.setIsAvailable(true);
            showSeat.setIsFoodAttached(false);
            showSeat.setSeatType(theatreSeat.getSeatType());
            if(theatreSeat.getSeatType()== SeatType.CLASSIC)showSeat.setPrice(classicPrice);
            if(theatreSeat.getSeatType()==SeatType.PREMIUM)showSeat.setPrice(premiumPrice);
            showSeat.setShow(show);
            showSeatList.add(showSeat);
        }
        showRepository.save(show);
        return "All seats added successfully";

    }
}
