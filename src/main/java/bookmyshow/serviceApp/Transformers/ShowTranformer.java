package bookmyshow.serviceApp.Transformers;

import bookmyshow.serviceApp.DTOs.RequestDto.AddShowDto;
import bookmyshow.serviceApp.Models.Show;

public class ShowTranformer {

    public static Show convertDtoToEntity(AddShowDto addShowDto) {
        Show show = Show.builder().date(addShowDto.getDate()).time(addShowDto.getTime()).build();
        return show;

    }
}
