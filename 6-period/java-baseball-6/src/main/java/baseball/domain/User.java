package baseball.domain;

import static baseball.rule.BaseBallValidation.validateInRangeNumber;
import static baseball.rule.BaseBallValidation.validateNoDuplicate;
import static baseball.rule.BaseBallValidation.validateThreeSize;
import static java.util.Collections.unmodifiableList;

import java.util.List;

public record User(
        List<Integer> numbers
) {

    public User(final List<Integer> numbers) {
        validateNoDuplicate(numbers);
        validateThreeSize(numbers);
        validateInRangeNumber(numbers);
        this.numbers = unmodifiableList(numbers);
    }
}
