package bookmyshow.serviceApp.Controllers;

import bookmyshow.serviceApp.DTOs.RequestDto.AddShowDto;
import bookmyshow.serviceApp.DTOs.RequestDto.AddShowSeatsDto;
import bookmyshow.serviceApp.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shows")

public class ShowController {
    @Autowired
    ShowService showService;
    @PostMapping("/add-show")
    public String addShow(@RequestBody AddShowDto addShowDto) throws Exception{
        try{
            return showService.addShow(addShowDto);
        }
        catch (Exception e){
            return e.getMessage();
        }


    }
    @PostMapping("/add-show-seats")
    public String addShowSeats(@RequestBody AddShowSeatsDto addShowSeatsDto){
        return showService.addshowSeats(addShowSeatsDto);
    }
    @GetMapping("/most-recommended-movie-name")
    public String getMovieName(AddShowDto addShowDto){

        return showService.getMovieName(addShowDto);
    }
}
