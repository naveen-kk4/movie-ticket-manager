package bookmyshow.serviceApp.Exceptions;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String movieNotFound) {
        super(movieNotFound);
    }
}
