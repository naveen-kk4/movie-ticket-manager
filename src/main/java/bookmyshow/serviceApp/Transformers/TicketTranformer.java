package bookmyshow.serviceApp.Transformers;

import bookmyshow.serviceApp.DTOs.ResponseDto.TicketDetailsDto;
import bookmyshow.serviceApp.Models.Show;
import bookmyshow.serviceApp.Models.Ticket;

public class TicketTranformer {

    public static TicketDetailsDto convertTicketToResponseDto(Show show, Ticket ticket) {
        TicketDetailsDto ticketDetailsDto = TicketDetailsDto.builder().location(show.getTheatre().getLocation()).
                                            date(show.getDate()).showTime(show.getTime()).bookedSeats(ticket.getBookedSeats())
                                    .theatreName(show.getTheatre().getName()).movieName(show.getMovie().getMovieName()).
                                     statusMessage("successfull").build();
        return ticketDetailsDto;

    }
}
