package bookmyshow.serviceApp.Controllers;

import bookmyshow.serviceApp.DTOs.RequestDto.AddTicketDto;
import bookmyshow.serviceApp.DTOs.ResponseDto.TicketDetailsDto;
import bookmyshow.serviceApp.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")

public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/book-ticket")
    public TicketDetailsDto bookTicket(@RequestBody AddTicketDto addTicketDto){

            try{
                return ticketService.bookTicket(addTicketDto);
            }
            catch(Exception e){
                TicketDetailsDto ticketDetailsDto = new TicketDetailsDto();
                ticketDetailsDto.setStatusMessage(e.getMessage());
                return ticketDetailsDto;
            }

    }
    @DeleteMapping("/cancel-ticket")
    public String cancelTicket(@RequestParam Integer ticketId){
        return ticketService.cancelTicket(ticketId);
    }
}
