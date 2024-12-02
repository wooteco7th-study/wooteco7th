package vendingmachine.excpetion;

public class InputException extends IllegalArgumentException {

    public InputException() {
        super(ErrorMessage.INPUT_ERROR.getMessage());
    }
}
