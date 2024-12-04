package subway.service;

import java.util.List;

public class StandardValidator {

    private final List<String> standards = List.of("1", "2", "B");
    private final String standard;

    public StandardValidator(final String standard) {
        validate(standard);
        this.standard = standard;
    }

    private void validate(final String standard) {
        if (isNotContain(standard)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isNotContain(final String standard) {
        return !standards.contains(standard);
    }

    public boolean isBack() {
        return standard.equals("B");
    }
}
