package oncall.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import oncall.exception.CustomIllegalArgumentException;
import oncall.exception.ErrorMessage;

public class Name {

    private static final int MAX = 5;

    private final String name;

    public Name(final String name) {
        validate(name);
        this.name = name;
    }

    public static List<Name> of(List<String> input) {
        return input.stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    private void validate(final String name) {
        if (name.length() > MAX) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Name name1)) {
            return false;
        }
        return Objects.equals(getName(), name1.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    public String getName() {
        return name;
    }
}
