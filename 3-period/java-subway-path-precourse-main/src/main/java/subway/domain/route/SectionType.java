package subway.domain.route;

import java.util.List;
import subway.domain.station.StationType;

public enum SectionType {

    교대_강남(StationType.교대역, StationType.강남역, 2, 3),
    강남_역삼(StationType.강남역, StationType.역삼역, 2, 3),
    교대_남부터미널(StationType.교대역, StationType.남부터미널역, 3, 2),
    남부터미널_양재(StationType.남부터미널역, StationType.양재역, 6, 5),
    양재_매봉(StationType.양재역, StationType.매봉역, 1, 1),
    강남_양재(StationType.강남역, StationType.양재역, 2, 8),
    양재_양재숲(StationType.양재역, StationType.양재시민의숲역, 10, 3);

    private final StationType departureStation;
    private final StationType arrivalStation;
    private final int distance;
    private final int takenTime;

    SectionType(final StationType departureStation, final StationType arrivalStation, final int distance,
                final int takenTime) {
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.distance = distance;
        this.takenTime = takenTime;
    }

    public static boolean existsStartAndEnd(final StationType start, final StationType end) {
        return findAll().stream()
                .anyMatch(sectionType -> sectionType.getDepartureStation().equals(start)
                        && sectionType.getArrivalStation().equals(end));
    }

    public static List<SectionType> findAll() {
        return List.of(SectionType.values());
    }

    public String getDepartureStationName() {
        return departureStation.name();
    }

    public String getArrivalStationName() {
        return arrivalStation.name();
    }

    public StationType getDepartureStation() {
        return departureStation;
    }

    public StationType getArrivalStation() {
        return arrivalStation;
    }

    public int getDistance() {
        return distance;
    }

    public int getTakenTime() {
        return takenTime;
    }
}
