package baseball.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

import static baseball.domain.Number.MAX_RANGE;
import static baseball.domain.Number.MIN_RANGE;
import static baseball.domain.Numbers.NUMBERS_SIZE;

public class RandomNumbersGenerator implements NumbersGenerator {

    @Override
    public Numbers generate() {
        List<Integer> computer = new ArrayList<>();

        while (computer.size() < NUMBERS_SIZE) {
            putNumber(computer);
        }
        return new Numbers(computer);
    }

    private void putNumber(final List<Integer> computer) {
        int randomNumber = Randoms.pickNumberInRange(MIN_RANGE, MAX_RANGE);

        if (isNotDuplicated(computer, randomNumber)) {
            computer.add(randomNumber);
        }
    }

    private boolean isNotDuplicated(final List<Integer> computer, final int randomNumber) {
        return !computer.contains(randomNumber);
    }
}