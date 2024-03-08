package bookmyshow.serviceApp.Exceptions;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String s) {
        super(s);
    }
}
