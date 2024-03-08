package bookmyshow.serviceApp.Services;

import bookmyshow.serviceApp.DTOs.RequestDto.AddTheatreDto;
import bookmyshow.serviceApp.DTOs.RequestDto.AddTheatreSeatsDto;
import bookmyshow.serviceApp.DTOs.ResponseDto.TheatreResponseDto;
import bookmyshow.serviceApp.Enums.SeatType;
import bookmyshow.serviceApp.Exceptions.TheatreNotFoundException;
import bookmyshow.serviceApp.Models.Show;
import bookmyshow.serviceApp.Models.Theatre;
import bookmyshow.serviceApp.Models.TheatreSeat;
import bookmyshow.serviceApp.Repositories.ShowRepository;
import bookmyshow.serviceApp.Repositories.TheatreRepository;
import bookmyshow.serviceApp.Transformers.TheatreTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class TheatreService {
    @Autowired
    TheatreRepository theatreRepository;
    @Autowired
    ShowRepository showRepository;
    public String addTheatre(AddTheatreDto addTheatreDto) {
        Theatre theatre = TheatreTransformer.convertDtoToEntity(addTheatreDto);
        theatreRepository.save(theatre);
        return "theatre added successfully";

    }

    public String addTheatreSeats(AddTheatreSeatsDto addTheatreSeatsDto) {
        int column = addTheatreSeatsDto.getSeatsInARow();
        int classicSeats = addTheatreSeatsDto.getClassicSeats();
        int premiumSeats = addTheatreSeatsDto.getPremiumSeats();
        Theatre theatre = theatreRepository.findByLocation(addTheatreSeatsDto.getLocation());
        System.out.println(theatre.toString());
        List<TheatreSeat> theatreSeatList = theatre.getTheatreSeatList();
        char seatName = 'A';
        int colNum = 1;
        int totalInARow=0;
        for(int i = 0;i<classicSeats;i++){
            if(totalInARow==column){
                seatName='A';
                colNum++;
                totalInARow=0;
            }
            String seatNo = String.valueOf(colNum)+seatName+"";

            TheatreSeat theatreSeat = new TheatreSeat();
            theatreSeat.setSeatType(SeatType.CLASSIC);
            theatreSeat.setSeatNo(seatNo);
            theatreSeat.setTheatre(theatre);
            theatreSeatList.add(theatreSeat);
            seatName++;
            totalInARow++;

        }
        for(int i = 0;i<premiumSeats;i++){
            if(totalInARow==column){
                seatName='A';
                colNum++;
                totalInARow=0;
            }
            String seatNo = String.valueOf(colNum)+seatName+"";
            TheatreSeat theatreSeat = new TheatreSeat();
            theatreSeat.setSeatType(SeatType.PREMIUM);
            theatreSeat.setSeatNo(seatNo);
            theatreSeat.setTheatre(theatre);
            theatreSeatList.add(theatreSeat);
            seatName++;
            totalInARow++;

        }
        theatreRepository.save(theatre);
        return "all seats added successfully";



    }

    public Integer theatreLocations(String theatreName) {
        int ans = 0;
        List<Theatre> theatreList = theatreRepository.findAll();
        for(Theatre theatre : theatreList){
            if(theatre.getName().equals(theatreName))ans++;
        }
        return ans;

    }

    public List<TheatreResponseDto> theatresShowingAtTime(LocalTime time) {
        List<TheatreResponseDto> theatreResponseDtos = new ArrayList<>();
        List<Show> showList = showRepository.findAll();
        for(Show show : showList){
            if(show.getTime().equals(time)){
                TheatreResponseDto theatreResponseDto = TheatreTransformer.convertEntityToDto(show.getTheatre());
                theatreResponseDtos.add(theatreResponseDto);
            }
        }
        return theatreResponseDtos;
    }
}
