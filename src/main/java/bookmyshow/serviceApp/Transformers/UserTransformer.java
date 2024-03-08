package bookmyshow.serviceApp.Transformers;

import bookmyshow.serviceApp.DTOs.RequestDto.AddUserDto;
import bookmyshow.serviceApp.DTOs.RequestDto.UserResponseDto;
import bookmyshow.serviceApp.Models.User;

public class UserTransformer {
    public static User convertDtoToEntity(AddUserDto userDto){
        User userObj = User.builder().name(userDto.getName()).emailId(userDto.getEmailId()).
                mobNo(userDto.getMobNo()).age(userDto.getAge()).build();
        return userObj;
    }
    public static UserResponseDto convertEntityToDto(User user){
        UserResponseDto userResponseDto = UserResponseDto.builder().name(user.getName()).
                                          age(user.getAge()).mobNo(user.getMobNo()).
                                          emailId(user.getEmailId()).status("200").
                                           message("user found successfully").build();
        return  userResponseDto;
    }
}
