package bookmyshow.serviceApp.Services;

import bookmyshow.serviceApp.DTOs.RequestDto.AddTicketDto;
import bookmyshow.serviceApp.DTOs.ResponseDto.TicketDetailsDto;
import bookmyshow.serviceApp.Exceptions.SeatNotFoundException;
import bookmyshow.serviceApp.Exceptions.ShowNotFoundException;
import bookmyshow.serviceApp.Exceptions.TicketNotFoundException;
import bookmyshow.serviceApp.Exceptions.UserNotFoundException;
import bookmyshow.serviceApp.Models.Show;
import bookmyshow.serviceApp.Models.ShowSeat;
import bookmyshow.serviceApp.Models.Ticket;
import bookmyshow.serviceApp.Models.User;
import bookmyshow.serviceApp.Repositories.ShowRepository;
import bookmyshow.serviceApp.Repositories.TicketRepository;
import bookmyshow.serviceApp.Repositories.UserRepository;
import bookmyshow.serviceApp.Transformers.TicketTranformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    ShowRepository showRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JavaMailSender emailSender;

    public TicketDetailsDto bookTicket(AddTicketDto addTicketDto)throws UserNotFoundException, ShowNotFoundException {
        Optional<User> userOptional = userRepository.findById(addTicketDto.getUserId());
        if(userOptional.isEmpty())throw new UserNotFoundException("Entered user id is incorrect");
        Optional<Show> showOptional = showRepository.findById(addTicketDto.getShowId());
        if(showOptional.isEmpty())throw new ShowNotFoundException("Entered showId is incorrect");
        User user = userOptional.get();
        Show show = showOptional.get();
        boolean isValid = this.validate(show,addTicketDto.getSeatNums());
        if(!isValid)throw new SeatNotFoundException("Added seat numbers are unavailable!");
        int price = calculateTotalPrice(show,addTicketDto.getSeatNums());
        String allTicketNums = this.createAllTicketNumsAsString(addTicketDto.getSeatNums());

        Ticket ticket = Ticket.builder().bookedSeats(allTicketNums).totalPrice(price)
                        .date(show.getDate()).user(user).show(show).build();

        ticket = ticketRepository.save(ticket);
        show.getTicketList().add(ticket);
        user.getTicketList().add(ticket);
        showRepository.save(show);
        userRepository.save(user);
        SimpleMailMessage simpleMessageMail = new SimpleMailMessage();

        String body = "Hi "+user.getName()+" ! \n"+
                "You have successfully booked a ticket. Please find the following details"+
                "booked seat No's"  + allTicketNums
                +"movie Name" + show.getMovie().getMovieName()
                +"show Date is "+show.getDate()+
                "And show time is "+show.getTime()+
                "Enjoy the Show !!!";

        simpleMessageMail.setSubject("Ticket Confirmation Mail");
        simpleMessageMail.setFrom("ig.404.breach.attempt@gmail.com");
        simpleMessageMail.setText(body);
        simpleMessageMail.setTo(user.getEmailId());

        emailSender.send(simpleMessageMail);

        return TicketTranformer.convertTicketToResponseDto(show,ticket);




    }

    private String createAllTicketNumsAsString(List<String> seatNums) {
        StringBuilder ticketList = new StringBuilder();
        for(String str : seatNums){
            ticketList.append(str);
            ticketList.append(',');
        }
        return ticketList.toString();
    }

    private int calculateTotalPrice(Show show, List<String> seatNums) {
        int price = 0;
        List<ShowSeat> showSeatList = show.getShowSeatList();
        for(ShowSeat showSeat : showSeatList){
            String seatNo = showSeat.getSeatNo();
            if(seatNums.contains(seatNo)){
                price+=showSeat.getPrice();
                showSeat.setIsAvailable(Boolean.FALSE);
            }
        }
        return price;
    }

    private boolean validate(Show show, List<String> seatNums) {
        List<ShowSeat> showSeatList = show.getShowSeatList();
        for(ShowSeat showSeat : showSeatList){
            String seatNo = showSeat.getSeatNo();
            if(seatNums.contains(seatNo) && !showSeat.getIsAvailable())return false;
        }
        return true;
    }

    public String cancelTicket(Integer ticketId) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        if(ticketOptional.isEmpty())throw new TicketNotFoundException("ticked id is invalid!");
        Ticket ticket = ticketOptional.get();
        Show show = ticket.getShow();
        User user = ticket.getUser();
        List<ShowSeat> showSeatList = show.getShowSeatList();
        String nums[] = ticket.getBookedSeats().split(",");
        for(ShowSeat showSeat : showSeatList){
            for(String seatNum:nums){
                if(seatNum.equals(showSeat.getSeatNo())){
                    showSeat.setIsAvailable(Boolean.TRUE);
                    break;
                }
            }
        }
        show.getTicketList().remove(ticket);
        user.getTicketList().remove(ticket);
        ticketRepository.delete(ticket);
        showRepository.save(show);
        userRepository.save(user);
        return "ticket has been cancelled successfully";
    }
}
