package baseball.domain;

import baseball.dto.ResultResponse;

public class NumberComparator {

    public boolean isThreeStrike(Numbers userNumbers, Numbers computerNumbers) {
        return userNumbers.isThreeStrike(computerNumbers);
    }

    public ResultResponse compare(Numbers userNumbers, Numbers computerNumbers) {
        int ballCount = 0;
        int strikeCount = 0;

        for (Integer number : userNumbers.getNumbers()) {
            if (computerNumbers.getNumbers().indexOf(number) == userNumbers.getNumbers().indexOf(number)) {
                strikeCount += 1;
                continue;
            }
            if (computerNumbers.getNumbers().contains(number)) {
                ballCount += 1;
            }
        }
        return new ResultResponse(ballCount, strikeCount);
    }
}
