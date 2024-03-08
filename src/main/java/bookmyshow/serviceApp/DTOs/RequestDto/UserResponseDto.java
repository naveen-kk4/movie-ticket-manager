package bookmyshow.serviceApp.DTOs.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserResponseDto {

    private String name;

    private Integer age;

    private String mobNo;

    private String emailId;
    private String status;
    private String message;
}
