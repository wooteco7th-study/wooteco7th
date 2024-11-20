package baseball.domain.baseball;

import static baseball.domain.baseball.BaseballNumbers.SIZE;

public record BaseballResult(int strike, int ball) {

    public boolean isWin() {
        return strike == SIZE;
    }
}
