package menu.domain;

import java.util.Collections;
import java.util.List;
import menu.error.ErrorMessage;
import menu.util.ListValidator;

public class CoachNameGroup {

    private static final int MIN = 2;
    private static final int MAX = 5;

    private final List<CoachName> coachNames;

    public CoachNameGroup(final List<CoachName> coachNames) {
        validate(coachNames);
        this.coachNames = coachNames;
    }

    private void validate(final List<CoachName> coachNames) {
        ListValidator.validateMinSize(coachNames, MIN, ErrorMessage.INVALID_COACHES_MIN_SIZE);
        ListValidator.validateMaxSize(coachNames, MAX, ErrorMessage.INVALID_COACHES_MAX_SIZE);
    }

    public List<CoachName> getCoachNames() {
        return Collections.unmodifiableList(coachNames);
    }
}
