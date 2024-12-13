package pairmatching.domain.crew;

import java.util.Objects;
import java.util.regex.Pattern;
import pairmatching.domain.order.Course;
import pairmatching.exception.CustomIllegalArgumentException;
import pairmatching.exception.ErrorMessage;
import pairmatching.util.InputValidator;

public class Crew {

    private static final int MAX_LENGTH = 5;
    private static final String NAME_REGEX = "[가-힣]{1,5}";
    private static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);
    private static final int MIN_LENGTH = 2;

    private final Course course;
    private final String name;

    public Crew(final Course course, final String name) {
        validate(name);
        this.course = course;
        this.name = name;
    }

    private void validate(final String name) {
        if (exceedLength(name)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_NAME_LENGTH);
        }
        if (isInvalidPattern(name)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_NAME_FORMAT);
        }
    }

    private boolean isInvalidPattern(final String name) {
        return InputValidator.isInvalidPattern(name, NAME_PATTERN);
    }

    private boolean exceedLength(final String name) {
        return name.length() < MIN_LENGTH || name.length() > MAX_LENGTH;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Crew crew)) {
            return false;
        }
        return getCourse() == crew.getCourse() && Objects.equals(getName(), crew.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCourse(), getName());
    }

    public Course getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }
}
