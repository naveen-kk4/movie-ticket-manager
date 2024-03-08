package bookmyshow.serviceApp.Services;

import bookmyshow.serviceApp.DTOs.RequestDto.AddUserDto;
import bookmyshow.serviceApp.DTOs.RequestDto.UserResponseDto;
import bookmyshow.serviceApp.DTOs.ResponseDto.TicketDetailsDto;
import bookmyshow.serviceApp.Exceptions.UserNotFoundException;
import bookmyshow.serviceApp.Models.Theatre;
import bookmyshow.serviceApp.Models.Ticket;
import bookmyshow.serviceApp.Models.User;
import bookmyshow.serviceApp.Repositories.UserRepository;
import bookmyshow.serviceApp.Transformers.TicketTranformer;
import bookmyshow.serviceApp.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service

public class UserService {
    @Autowired
    UserRepository userRepository;

    public String addUser(AddUserDto addUserDto) {
        User user = UserTransformer.convertDtoToEntity(addUserDto);
        userRepository.save(user);
        return "user added successfully";
    }

    public List<User> findAllUsersAboveAge(Integer age) throws UserNotFoundException {
        List<User> userList = userRepository.findAllAboveAge(age);
        return userList;
    }

    public UserResponseDto getOldestUser() {
        List<User> userList = userRepository.findAll();
        User userObj = null;
        int age = 0;
        for(User user : userList){
            if(user.getAge()>age){
                userObj=user;
                age = user.getAge();
            }
        }
        if(Objects.isNull(userObj))throw new UserNotFoundException("User list is empty");
        UserResponseDto userResponseDto = UserTransformer.convertEntityToDto(userObj);
        return userResponseDto;

    }

    public List<TicketDetailsDto> getAllTickets(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty())throw new UserNotFoundException("the requested user is not present in DB");
        List<TicketDetailsDto> ticketDetailsDtos = new ArrayList<>();
        User user = userOptional.get();
        List< Ticket> ticketList = user.getTicketList();
        for(Ticket ticket : ticketList){
            TicketDetailsDto ticketDetailsDto = TicketTranformer.convertTicketToResponseDto(ticket.getShow(),ticket);
            ticketDetailsDtos.add(ticketDetailsDto);
        }
        return ticketDetailsDtos;
    }
}
