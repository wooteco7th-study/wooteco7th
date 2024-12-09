package oncall.domain.turn;

import java.util.Collections;
import java.util.List;
import oncall.domain.Name;
import oncall.exception.CustomIllegalArgumentException;
import oncall.exception.ErrorMessage;

public class Turn {

    private static final int MIN_WORKER_NUMBER = 5;
    private static final int MAX_WORKER_NUMBER = 35;

    private final List<Name> names;
    private int pos = -1;
    private boolean isChanged = false;

    public Turn(final List<String> names) {
        validate(names);
        this.names = Name.of(names);
    }

    public Name getNextName(final String pastName) {
        pos = calculateNextPos();
        Name nextName = names.get(pos);
        if (isChanged) {
            Collections.swap(names, pos, calculatePreviousPos());
            isChanged = false;
            return nextName;
        }
        if (nextName.getName().equals(pastName)) {
            Collections.swap(names, pos, calculateNextPos());
            isChanged = true;
            return names.get(pos);
        }
        return nextName;
    }

    private int calculateNextPos() {
        return (pos + 1) % getSize();
    }

    private int calculatePreviousPos() {
        return (getSize() + pos - 1) % getSize();
    }

    private void validate(final List<String> names) {
        validateRange(names);
        validateDuplicated(names);
    }

    private void validateRange(final List<String> names) {
        if (names.size() < MIN_WORKER_NUMBER || names.size() > MAX_WORKER_NUMBER) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
    }

    private void validateDuplicated(final List<String> names) {
        if (names.size() != names.stream().distinct().count()) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
    }

    public int getSize() {
        return names.size();
    }
}
