package subway.domain.station;

import java.util.Arrays;
import subway.exception.CustomIllegalArgumentException;
import subway.exception.ErrorMessage;

public enum StationType {

    교대역, 강남역, 역삼역, 남부터미널역, 양재역, 양재시민의숲역, 매봉역;

    public static StationType of(final String input) {
        return Arrays.stream(values())
                .filter(stationType -> stationType.name().equals(input))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_STATION_NAME));
    }
}
