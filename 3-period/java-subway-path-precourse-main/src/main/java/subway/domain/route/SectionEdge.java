package subway.domain.route;

import java.util.List;
import subway.domain.station.StationName;

public enum SectionEdge {

    교대_강남(StationName.교대역, StationName.강남역, 2, 3),
    강남_역삼(StationName.강남역, StationName.역삼역, 2, 3),
    교대_남부터미널(StationName.교대역, StationName.남부터미널역, 3, 2),
    남부터미널_양재(StationName.남부터미널역, StationName.양재역, 6, 5),
    양재_매봉(StationName.양재역, StationName.매봉역, 1, 1),
    강남_양재(StationName.강남역, StationName.양재역, 2, 8),
    양재_양재숲(StationName.양재역, StationName.양재시민의숲역, 10, 3);

    private final StationName departureStation;
    private final StationName arrivalStation;
    private final int distance;
    private final int takenTime;

    SectionEdge(final StationName departureStation, final StationName arrivalStation, final int distance,
                final int takenTime) {
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.distance = distance;
        this.takenTime = takenTime;
    }

    public static List<SectionEdge> findAll() {
        return List.of(SectionEdge.values());
    }

    public String getDepartureStationName() {
        return departureStation.name();
    }

    public String getArrivalStationName() {
        return arrivalStation.name();
    }

    public int getDistance() {
        return distance;
    }

    public int getTakenTime() {
        return takenTime;
    }
}
