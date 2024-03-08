package bookmyshow.serviceApp.DTOs.RequestDto;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data


public class AddShowDto {

    private Date date;
    private LocalTime time;
    private int theatreId;
    private int movieId;
}
