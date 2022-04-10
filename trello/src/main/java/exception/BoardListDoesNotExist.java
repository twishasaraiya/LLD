package exception;

public class BoardListDoesNotExist extends IllegalStateException{
    public BoardListDoesNotExist(String message) {
        super(message);
    }
}
