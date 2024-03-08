package bookmyshow.serviceApp.Exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userListIsEmpty) {
        super(userListIsEmpty);
    }
}
