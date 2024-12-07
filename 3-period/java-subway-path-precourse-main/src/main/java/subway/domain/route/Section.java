package subway.domain.route;

import java.util.Objects;
import subway.domain.station.Station;

// 출발 지하철역(정점)에서 도착 지하철역까지의 거리, 시간
// - 출발 지하철역
//- 종료 지하철역
//- 걸린 시간
//- 이동 거리
public class Section {

    private final Station departureStation;
    private final Station arrivalStation;
    private final int takenTime;
    private final int distance;

    public Section(final Station departureStation, final Station arrivalStation, final int takenTime,
                   final int distance) {
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.takenTime = takenTime;
        this.distance = distance;
    }

    public boolean findRoute(final Station departureStation, final Station arrivalStation) {
        return this.departureStation.equals(departureStation) && this.arrivalStation.equals(arrivalStation);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Section section)) {
            return false;
        }
        return getTakenTime() == section.getTakenTime() && getDistance() == section.getDistance() && Objects.equals(
                getDepartureStation(), section.getDepartureStation()) && Objects.equals(getArrivalStation(),
                section.getArrivalStation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDepartureStation(), getArrivalStation(), getTakenTime(), getDistance());
    }

    public Station getDepartureStation() {
        return departureStation;
    }

    public Station getArrivalStation() {
        return arrivalStation;
    }

    public int getTakenTime() {
        return takenTime;
    }

    public int getDistance() {
        return distance;
    }
}
