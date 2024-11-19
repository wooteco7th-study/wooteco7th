package baseball.error;

import baseball.constant.ErrorMessage;

public class AppException extends IllegalArgumentException {

    public AppException(final ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
