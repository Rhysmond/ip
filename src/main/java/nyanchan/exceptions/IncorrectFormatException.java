package nyanchan.exceptions;

public class IncorrectFormatException extends NyanException {
    private static String message = "Hiss! WAKARANAI.";

    public IncorrectFormatException() {
        super(IncorrectFormatException.message);
    }

    public IncorrectFormatException(String message) {
        super(message);
    }
}
