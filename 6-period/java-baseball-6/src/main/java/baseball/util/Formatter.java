package baseball.util;

import baseball.domain.baseball.BaseballResult;

public class Formatter {

    public String makeResult(BaseballResult result) {
        String message = checkBall(result);
        message += checkStrike(result);
        return checkNothing(message);
    }

    private String checkBall(final BaseballResult result) {
        if (result.getBall() > 0) {
            return String.format("%d볼 ", result.getBall());
        }
        return "";
    }

    private String checkStrike(final BaseballResult result) {
        if (result.getStrike() > 0) {
            return String.format("%d스트라이크", result.getStrike());
        }
        return "";
    }

    private String checkNothing(final String message) {
        if (message.isEmpty()) {
            return "낫싱";
        }
        return message;
    }
}
