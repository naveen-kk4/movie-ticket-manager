package bookmyshow.serviceApp.DTOs.RequestDto;

import lombok.Data;

import java.util.List;

@Data
public class AddTicketDto {

    private int userId;
    private int showId;
    private List<String> seatNums;
}
