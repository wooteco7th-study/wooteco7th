package baseball.domain;

import static baseball.rule.BaseBallValidation.*;
import static java.util.Collections.*;

import java.util.List;

public record Computer(
        List<Integer> numbers
) {

    public Computer(final List<Integer> numbers) {
        validateNoDuplicate(numbers);
        validateThreeSize(numbers);
        validateInRangeNumber(numbers);
        this.numbers = unmodifiableList(numbers);
    }
}
