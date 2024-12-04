package subway.domain.vo;

import subway.domain.validator.DepartureStationValidator;

public class DepartureStation {
    private final String value;

    public DepartureStation(final String value) {
        DepartureStationValidator.validate(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
