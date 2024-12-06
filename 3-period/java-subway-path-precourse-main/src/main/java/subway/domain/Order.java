package subway.domain;

import subway.exception.CustomIllegalArgumentException;
import subway.exception.ErrorMessage;

public class Order {

    private final Station departureStation;
    private final Station arrivalStation;

    public Order(final Station departureStation, final Station arrivalStation) {
        validate(departureStation, arrivalStation);
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
    }

    private void validate(final Station departureStation, final Station arrivalStation) {
        if (departureStation.equals(arrivalStation)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_STATION_DUPLICATED);
        }
    }

    public Station getDepartureStation() {
        return departureStation;
    }

    public Station getArrivalStation() {
        return arrivalStation;
    }
}
