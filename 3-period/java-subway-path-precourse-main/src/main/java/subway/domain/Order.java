package subway.domain;

import subway.exception.CustomIllegalArgumentException;
import subway.exception.ErrorMessage;

public class Order {

    private final Station departureStation;
    private final Station arrivalStation;

    public Order(final Station departureStation, final Station arrivalStation, final RouteRepository routeRepository) {
        validate(departureStation, arrivalStation, routeRepository);
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
    }

    private void validate(final Station departureStation, final Station arrivalStation,
                          final RouteRepository routeRepository) {
        if (departureStation.equals(arrivalStation)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_STATION_DUPLICATED);
        }
        routeRepository.validatePathConnected(departureStation, arrivalStation);
    }

    public Station getDepartureStation() {
        return departureStation;
    }

    public Station getArrivalStation() {
        return arrivalStation;
    }
}
