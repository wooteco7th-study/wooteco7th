package christmas.Exception;

public class InputException extends IllegalArgumentException {

    public InputException() {
        super(ExceptionMessage.RE_INPUT_MESSAGE.getMessage());
    }
}
