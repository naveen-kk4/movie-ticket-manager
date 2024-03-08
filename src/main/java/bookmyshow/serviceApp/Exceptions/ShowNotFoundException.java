package bookmyshow.serviceApp.Exceptions;

public class ShowNotFoundException extends RuntimeException {
    public ShowNotFoundException(String s) {
        super(s);
    }
}
