package bookmyshow.serviceApp.Exceptions;

public class SeatNotFoundException extends RuntimeException {
    public SeatNotFoundException(String addedSeatNumbersAreInvalid) {
        super(addedSeatNumbersAreInvalid);
    }
}
