package menu.domain.validator;

import static menu.constant.ApplicationRule.COACH_NAME_MAX;
import static menu.constant.ApplicationRule.COACH_NAME_MIN;
import static menu.constant.ApplicationRule.COACH_SIZE_MAX;
import static menu.constant.ApplicationRule.COACH_SIZE_MIN;
import static menu.constant.ExceptionMessage.INVALID_COACH_NAME_RANGE;
import static menu.constant.ExceptionMessage.INVALID_COACH_SIZE;

import java.util.List;
import menu.util.StringUtils;


public class CoachValidator {

    private CoachValidator() {
    }

    public static void validateCoachNameRange(String name) {
        if (!StringUtils.isBetweenLength(name, COACH_NAME_MIN, COACH_NAME_MAX)) {
            throw new IllegalArgumentException(INVALID_COACH_NAME_RANGE.getMessage());
        }
    }

    public static void validateCoachSize(List<String> coaches) {
        int size = coaches.size();
        if (!(COACH_SIZE_MIN <= size && size <= COACH_SIZE_MAX)) {
            throw new IllegalArgumentException(INVALID_COACH_SIZE.getMessage());
        }
    }

}
