package menu.domain;

import java.util.List;

import static menu.exception.ExceptionMessage.DUPLICATED_NAME;
import static menu.exception.ExceptionMessage.INVALID_COACHES_SIZE;

public class Coaches {
    public static final int MIN_SIZE = 2;
    public static final int MAX_SIZE = 5;

    private final List<Coach> coaches;

    public Coaches(final List<Coach> coaches) {
        validateSize(coaches);
        validateDuplicate(coaches);
        this.coaches = coaches;
    }

    public int getSize() {
        return coaches.size();
    }

    public CantEatMenu getCantEatMenu(int index) {
        return coaches.get(index).getCantEatMenu();
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public List<CoachName> getCoachNames() {
        return coaches.stream()
                .map(Coach::getCoachName)
                .toList();
    }

    private void validateSize(final List<Coach> coaches) {
        if (isOutOfRange(coaches)) {
            throw new IllegalArgumentException(INVALID_COACHES_SIZE.getMessage());
        }
    }

    private boolean isOutOfRange(final List<Coach> coaches) {
        return coaches.size() < MIN_SIZE || coaches.size() > MAX_SIZE;
    }

    public void validateDuplicate(final List<Coach> coaches) {
        if (isDuplicated(coaches)) {
            throw new IllegalArgumentException(DUPLICATED_NAME.getMessage());
        }
    }

    private boolean isDuplicated(final List<Coach> coaches) {
        return coaches.stream()
                .distinct()
                .count() != coaches.size();
    }
}
