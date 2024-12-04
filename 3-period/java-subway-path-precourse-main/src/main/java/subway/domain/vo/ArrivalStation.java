package subway.domain.vo;

import subway.domain.validator.ArrivalStationValidator;

public class ArrivalStation {
    private final String value;

    public ArrivalStation(final String value) {
        ArrivalStationValidator.validate(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
