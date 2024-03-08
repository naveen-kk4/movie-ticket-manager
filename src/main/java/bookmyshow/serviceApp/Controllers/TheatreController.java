package bookmyshow.serviceApp.Controllers;

import bookmyshow.serviceApp.DTOs.RequestDto.AddTheatreDto;
import bookmyshow.serviceApp.DTOs.RequestDto.AddTheatreSeatsDto;
import bookmyshow.serviceApp.DTOs.ResponseDto.TheatreResponseDto;
import bookmyshow.serviceApp.Services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/theatre")

public class TheatreController {
    @Autowired
    TheatreService theatreService;

    @PostMapping("/add-theatre")
    public String addTheatre(@RequestBody AddTheatreDto addTheatreDto){
       return theatreService.addTheatre(addTheatreDto);

    }
    @PostMapping("/add-theatreSeats")
    public String addTheatreSeats(@RequestBody AddTheatreSeatsDto addTheatreSeatsDto){
        return theatreService.addTheatreSeats(addTheatreSeatsDto);
    }
    @GetMapping("/theatre-locations")
    public Integer theatreLocations(@RequestParam String theatreName){
        return theatreService.theatreLocations(theatreName);
    }
    @GetMapping("/theatres-at-a-showTime")
    public List<TheatreResponseDto> theatresShowingAtTime(@RequestParam LocalTime time){
        return theatreService.theatresShowingAtTime(time);
    }
}
