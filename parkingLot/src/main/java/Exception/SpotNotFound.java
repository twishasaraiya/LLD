package Exception;

public class SpotNotFound extends IllegalStateException{
    public SpotNotFound(String message) {
        super(message);
    }
}
