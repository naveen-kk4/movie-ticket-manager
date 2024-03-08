package bookmyshow.serviceApp.DTOs.RequestDto;

import lombok.Data;

@Data

public class AddTheatreSeatsDto {
    private int classicSeats;
    private int premiumSeats;
    private int seatsInARow;
    private String location;
}
