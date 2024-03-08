package bookmyshow.serviceApp.DTOs.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TheatreResponseDto {
    private String name;
    private String location;
}
