package subway.domain.validator;

import static subway.message.ExceptionMessage.ARRIVAL_STATION_CANT_BE_FOUND;
import static subway.util.ValidationUtils.validateNullOrEmpty;

import java.util.Arrays;
import subway.domain.vo.SubwayStation;


public class ArrivalStationValidator {
    private ArrivalStationValidator() {
    }

    public static void validate(String input) {
        validateNullOrEmpty(input);
        boolean result = Arrays.stream(SubwayStation.values()).anyMatch(i -> i.getValue() == input);
        if (result == false) {
            throw new IllegalArgumentException(ARRIVAL_STATION_CANT_BE_FOUND.getMessage());
        }
    }
}
