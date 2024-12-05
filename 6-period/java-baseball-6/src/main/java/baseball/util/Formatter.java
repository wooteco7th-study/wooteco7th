package baseball.util;

import baseball.domain.baseball.BaseballResult;

public class Formatter {

    private static final String BALL = "%d볼 ";
    private static final String STRIKE = "%d스트라이크";
    private static final String NOTHING = "낫싱";

    public String makeResult(BaseballResult result) {
        String message = checkBall(result);
        message += checkStrike(result);
        return checkNothing(message);
    }

    private String checkBall(final BaseballResult result) {
        if (result.ball() > 0) {
            return String.format(BALL, result.ball());
        }
        return "";
    }

    private String checkStrike(final BaseballResult result) {
        if (result.strike() > 0) {
            return String.format(STRIKE, result.strike());
        }
        return "";
    }

    private String checkNothing(final String message) {
        if (message.isEmpty()) {
            return NOTHING;
        }
        return message;
    }
}
