package exception;

import com.sun.jdi.InvalidTypeException;

// TODO: Not a good practice to use Exception
public class DataTypeException extends InvalidTypeException {
    public DataTypeException(String message) {
        super(message);
    }
}
