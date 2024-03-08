package bookmyshow.serviceApp.DTOs.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data


public class AddUserDto {
    private String name;

    private Integer age;

    private String mobNo;

    private String emailId;
}
