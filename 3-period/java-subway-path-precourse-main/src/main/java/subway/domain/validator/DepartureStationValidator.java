package subway.domain.validator;

import static subway.message.ExceptionMessage.DEPARTURE_STATION_CANT_BE_FOUND;
import static subway.util.ValidationUtils.validateNullOrEmpty;

import java.util.Arrays;
import subway.domain.vo.SubwayStation;

public class DepartureStationValidator {
    private DepartureStationValidator() {
    }

    public static void validate(String input) {
        validateNullOrEmpty(input);
        boolean result = Arrays.stream(SubwayStation.values()).anyMatch(i -> i.getValue().equals(input));

        if (result == false) {
            throw new IllegalArgumentException(DEPARTURE_STATION_CANT_BE_FOUND.getMessage());
        }
    }
}
