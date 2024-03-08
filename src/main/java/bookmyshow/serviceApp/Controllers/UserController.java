package bookmyshow.serviceApp.Controllers;

import bookmyshow.serviceApp.DTOs.RequestDto.AddUserDto;
import bookmyshow.serviceApp.DTOs.RequestDto.UserResponseDto;
import bookmyshow.serviceApp.DTOs.ResponseDto.TicketDetailsDto;
import bookmyshow.serviceApp.Models.User;
import bookmyshow.serviceApp.Services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Log4j2


public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestBody AddUserDto addUserDto){
        String response =  userService.addUser(addUserDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/all-users-above-age")
    public List<User> findAllUsersOverAge(@RequestParam Integer age){
        List<User> userList = userService.findAllUsersAboveAge(age);
        return userList;
    }
    @GetMapping("/oldest-user")
    public UserResponseDto getOldestUser(){
        try{
            UserResponseDto response = userService.getOldestUser();
            return response;
        }
        catch(Exception e){
            UserResponseDto responseDto = new UserResponseDto();
            responseDto.setStatus("404");
            responseDto.setMessage(e.getMessage());
            return responseDto;
        }

    }
    @GetMapping("/all-tickets")
    public List<TicketDetailsDto> getAllTickets(@RequestParam int userId){
        try{
            return userService.getAllTickets(userId);
        }
        catch(Exception e){
            log.warn(e.getMessage());
            return null;
        }
    }

}
