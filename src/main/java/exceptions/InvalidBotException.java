package exceptions;

public class InvalidBotException extends RuntimeException {
    public InvalidBotException(String message) {
        super(message);
    }

}
