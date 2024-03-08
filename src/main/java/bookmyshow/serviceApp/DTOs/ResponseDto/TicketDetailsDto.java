package bookmyshow.serviceApp.DTOs.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TicketDetailsDto {
    private String statusMessage;
    private String location;
    private String theatreName;
    private String movieName;
    private Date date;
    private LocalTime showTime;
    private String bookedSeats;

}
