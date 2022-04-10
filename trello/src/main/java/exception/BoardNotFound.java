package exception;

public class BoardNotFound extends IllegalStateException{
    public BoardNotFound(String message) {
        super(message);
    }
}
