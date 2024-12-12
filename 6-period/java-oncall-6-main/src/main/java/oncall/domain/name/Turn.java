package oncall.domain.name;

import java.util.Collections;
import java.util.List;
import oncall.exception.CustomIllegalArgumentException;
import oncall.exception.ErrorMessage;

public class Turn {

    private static final int MIN_LENGTH = 5;
    private static final int MAX_LENGTH = 35;

    private final List<Name> names;

    public Turn(final List<String> names) {
        validate(names);
        this.names = Turn.of(names);
    }

    private static List<Name> of(final List<String> names) {
        return names.stream()
                .map(Name::new)
                .toList();
    }

    public boolean hasOnlyOne(final Name name) {
        int frequency = Collections.frequency(names, name);
        return frequency == 1;
    }

    private void validate(final List<String> values) {
        validateDuplicated(values);
        validateSize(values);
    }

    private void validateSize(final List<String> values) {
        if (values.size() < MIN_LENGTH || values.size() > MAX_LENGTH) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
    }

    private void validateDuplicated(final List<String> values) {
        if (values.size() != values.stream().distinct().count()) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
    }

    public int getSize() {
        return names.size();
    }

    public List<Name> getNames() {
        return Collections.unmodifiableList(names);
    }
}
