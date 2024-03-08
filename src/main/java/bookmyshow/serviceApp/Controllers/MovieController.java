package bookmyshow.serviceApp.Controllers;

import bookmyshow.serviceApp.DTOs.RequestDto.AddMovieDto;
import bookmyshow.serviceApp.Services.MovieService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/movies")
@Log4j2

public class MovieController {

    @Autowired
    MovieService movieService;
    @PostMapping("/add-movie")
    public String addMovie(@RequestBody AddMovieDto addMovieDto){
        return movieService.addMovie(addMovieDto);

    }
    @GetMapping("/show-time-list")
    public ResponseEntity<List<LocalTime>> getShowTimeList(@RequestParam String movieName, @RequestParam String theatreName){
        try {
            List<LocalTime> timings =  movieService.getShowTimeList(movieName, theatreName);
            return new ResponseEntity<>(timings, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/total-revenue")
    public long totalRevenue(@RequestParam Integer id){
        try {
            return movieService.totalRevenue(id);
        }
        catch(Exception e){
            log.warn(e.getMessage());
            return 0;
        }
    }
    @GetMapping("/hit-flop")
    public String isHitOrFlop(@RequestParam Integer id){
        try{
            return movieService.hitOrFlop(id);
        }
        catch(Exception e){
            log.warn(e.getMessage());
            return null;
        }
    }

}
