package bookmyshow.serviceApp.DTOs.RequestDto;

import lombok.Data;

@Data

public class AddShowSeatsDto {
    private int showId;
    private int premiumPrice;
    private int classicPrice;
}
