package bookmyshow.serviceApp.Exceptions;

public class TheatreNotFoundException extends RuntimeException {
    public TheatreNotFoundException(String theatreNotFound) {
        super(theatreNotFound);
    }
}
