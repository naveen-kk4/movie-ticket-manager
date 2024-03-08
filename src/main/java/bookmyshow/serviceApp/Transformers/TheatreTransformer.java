package bookmyshow.serviceApp.Transformers;

import bookmyshow.serviceApp.DTOs.RequestDto.AddTheatreDto;
import bookmyshow.serviceApp.DTOs.ResponseDto.TheatreResponseDto;
import bookmyshow.serviceApp.Models.Theatre;

public class TheatreTransformer {
    public static Theatre convertDtoToEntity(AddTheatreDto addTheatreDto) {
        Theatre theatre = Theatre.builder().name(addTheatreDto.getName()).location(addTheatreDto.getLocation()).build();
        return theatre;
    }

    public static TheatreResponseDto convertEntityToDto(Theatre theatre) {
        TheatreResponseDto theatreResponseDto = TheatreResponseDto.builder().name(theatre.getName()).
                                                location(theatre.getLocation()).build();
        return theatreResponseDto;
    }
}
