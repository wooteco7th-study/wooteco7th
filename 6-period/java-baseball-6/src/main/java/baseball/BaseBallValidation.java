package baseball;

import static baseball.BaseballErrorMessage.*;
import static baseball.BaseballRule.*;
import static baseball.template.RangeValidator.*;

import baseball.template.RangeValidator;
import baseball.template.Validator;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.internal.ErrorMessages;

public class BaseBallValidation {

    public static void validateNoDuplicate(List<Integer> numbers){
        Validator.check(numbers.stream().distinct().toList().size() != numbers.size())
                .withError(new IllegalArgumentException(INVALID_DUPLICATE_NUMBER.getMessage()))
                .validate();
    }

    public static void validateThreeSize(List<Integer> numbers){
        Validator.check(numbers.size() == BASEBALL_SIZE.getValue())
                .withError(new IllegalArgumentException(INVALID_THREE_COUNT_NUMBER.getMessage()))
                .validate();
    }
    public static void validateInRangeNumber(List<Integer> numbers){
        numbers.stream().forEach(number -> Validator.check(isNumberInRange(
                number
                ,MIN_NUMBER.getValue(),
                MAX_NUMBER.getValue()
                )).withError(new IllegalArgumentException(INVALID_RANGE_NUMBER.getMessage()))
                        .validate()
        );
    }

}
