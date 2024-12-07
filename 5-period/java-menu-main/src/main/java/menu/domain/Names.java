package menu.domain;

import java.util.List;
import menu.exception.CustomIllegalArgumentException;
import menu.exception.ErrorMessage;

public class Names {

    private final List<Name> names;

    public Names(final List<String> names) {
        validate(names);
        this.names = makeNames(names);
    }

    private List<Name> makeNames(final List<String> names) {
        return names.stream()
                .map(Name::new)
                .toList();
    }

    private void validate(final List<String> names) {
        validateLength(names);
        validateDuplicated(names);
    }

    private void validateDuplicated(final List<String> names) {
        if (names.size() != names.stream().distinct().count()) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_COACH_NAME_DUPLICATED);
        }
    }

    private void validateLength(final List<String> names) {
        if (names.size() < 2) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_COACH_NUMBER_MIN);
        }
        if (names.size() > 5) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_COACH_NUMBER_MAX);
        }
    }
}
