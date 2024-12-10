package subway.service;

import java.util.List;

public class MainValidator {

    private final List<String> choices = List.of("1", "Q");
    private final String main;

    public MainValidator(final String main) {
        validate(main);
        this.main = main;
    }

    private void validate(final String main) {
        if (isNotContain(main)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isNotContain(final String main) {
        return !choices.contains(main);
    }

    public boolean isNotTrue() {
        return main.equals("Q");
    }
}
