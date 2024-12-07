package menu.domain;

import java.util.List;

import static menu.exception.ExceptionMessage.DUPLICATED_NAME;
import static menu.exception.ExceptionMessage.INVALID_COACHES_SIZE;

public class Coaches {
    public static final int MIN_SIZE = 2;
    public static final int MAX_SIZE = 5;

    private final List<CoachName> coaches;

    public Coaches(final List<CoachName> coaches) {
        validateSize(coaches);
        validateDuplicate(coaches);
        this.coaches = coaches;
    }

    public List<CoachName> getCoaches() {
        return coaches;
    }

    private void validateSize(final List<CoachName> coaches) {
        if (isOutOfRange(coaches)) {
            throw new IllegalArgumentException(INVALID_COACHES_SIZE.getMessage());
        }
    }

    private boolean isOutOfRange(final List<CoachName> coaches) {
        return coaches.size() < MIN_SIZE || coaches.size() > MAX_SIZE;
    }

    public void validateDuplicate(final List<CoachName> coaches) {
        if (isDuplicated(coaches)) {
            throw new IllegalArgumentException(DUPLICATED_NAME.getMessage());
        }
    }

    private boolean isDuplicated(final List<CoachName> coaches) {
        return coaches.stream()
                .distinct()
                .count() != coaches.size();
    }
}
