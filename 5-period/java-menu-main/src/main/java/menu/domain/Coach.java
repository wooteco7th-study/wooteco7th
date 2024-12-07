package menu.domain;

import menu.domain.validator.CoachValidator;

public class Coach {
    private final String name;

    public Coach(final String name) {
        CoachValidator.validateCoachNameRange(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Coach{" +
                "name='" + name + '\'' +
                '}';
    }
}
