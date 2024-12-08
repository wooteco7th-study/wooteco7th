package subway.domain;

import subway.domain.station.StationType;
import subway.exception.CustomIllegalArgumentException;
import subway.exception.ErrorMessage;

public class Order {

    private final StationType departureStation;
    private final StationType arrivalStation;

    public Order(final StationType departureStation, final StationType arrivalStation) {
        validate(departureStation, arrivalStation);
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
    }

    private void validate(final StationType departureStation, final StationType arrivalStation) {
        if (departureStation.equals(arrivalStation)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_STATION_DUPLICATED);
        }
    }

    public String getDepartureStationName() {
        return departureStation.name();
    }

    public String getArrivalStationName() {
        return arrivalStation.name();
    }
}
