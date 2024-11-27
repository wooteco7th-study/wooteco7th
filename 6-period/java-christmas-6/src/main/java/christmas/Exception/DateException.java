package christmas.Exception;

public class DateException extends IllegalArgumentException {

    public DateException() {
        super(ExceptionMessage.DATE_MESSAGE.getMessage());
    }
}
