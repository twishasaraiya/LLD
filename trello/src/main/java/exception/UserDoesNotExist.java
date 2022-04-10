package exception;

public class UserDoesNotExist extends IllegalStateException{
    public UserDoesNotExist(String message) {
        super(message);
    }
}
