package subway.domain.station;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import subway.exception.CustomIllegalArgumentException;
import subway.exception.ErrorMessage;

public enum StationName {

    교대역, 강남역, 역삼역, 남부터미널역, 양재역, 양재시민의숲역, 매봉역;

    public static StationName from(String input) {
        return Arrays.stream(StationName.values())
                .filter(stationName -> Objects.equals(stationName.name(), input))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_STATION_NAME));
    }

    public static List<StationName> findAll() {
        return List.of(StationName.values());
    }
}
