package baseball.domain;

import baseball.constant.GameRule;
import baseball.error.ErrorMessage;
import baseball.util.ListValidator;
import baseball.util.NumberValidator;
import java.util.List;

public class UserNumber {

    private final List<Integer> values;

    public UserNumber(final List<Integer> values) {
        validate(values);
        this.values = values;
    }

    private void validate(final List<Integer> values) {
        ListValidator.validateDuplicate(values, ErrorMessage.INVALID_DUPLICATED_NUMBER);
        ListValidator.validateSize(values, GameRule.NUMBERS_SIZE, ErrorMessage.INVALID_EXCEEDS_NUMBERS_SIZE);
        ListValidator.validateRange(values, value -> NumberValidator.validateRange(value, GameRule.MIN_NUMBER, GameRule.MAX_NUMBER,
                ErrorMessage.INVALID_WRONG_NUMBER_FORMAT));
    }

    public int getNumberByIdx(final int idx) {
        return values.get(idx);
    }
}
