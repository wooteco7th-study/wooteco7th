package subway.domain;

// 출발 지하철역(정점)에서 도착 지하철역까지의 거리, 시간
// - 출발 지하철역
//- 종료 지하철역
//- 걸린 시간
//- 이동 거리
public class Route {

    private final Line line;
    private final Station departureStation;
    private final Station arrivalStation;
    private final int takenTime;
    private final int distance;

    public Route(final Line line, final Station departureStation, final Station arrivalStation, final int takenTime,
                 final int distance) {
        this.line = line;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.takenTime = takenTime;
        this.distance = distance;
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
