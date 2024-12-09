package oncall.domain;

import java.util.List;
import oncall.exception.CustomIllegalArgumentException;
import oncall.exception.ErrorMessage;

public class Turn {

    private static final int MIN_WORKER_NUMBER = 5;
    private static final int MAX_WORKER_NUMBER = 35;

    private final List<Name> names;

    public Turn(final List<String> names) {
        validate(names);
        this.names = Name.of(names);
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
