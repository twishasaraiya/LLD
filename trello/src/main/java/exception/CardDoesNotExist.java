package exception;

public class CardDoesNotExist extends IllegalStateException{
    public CardDoesNotExist(String message) {
        super(message);
    }
}
