package subway;

import static subway.domain.vo.SubwayStation.values;
import static subway.domain.vo.SubwayStation.강남역;
import static subway.domain.vo.SubwayStation.교대역;
import static subway.domain.vo.SubwayStation.남부터미널역;
import static subway.domain.vo.SubwayStation.매봉역;
import static subway.domain.vo.SubwayStation.양재시민의숲역;
import static subway.domain.vo.SubwayStation.양재역;
import static subway.domain.vo.SubwayStation.역삼역;

import java.util.Arrays;
import java.util.List;
import subway.domain.Line;
import subway.domain.Station;
import subway.domain.repository.DistanceShortestPathGraph;
import subway.domain.repository.LineRepository;
import subway.domain.repository.StationRepository;
import subway.domain.repository.TimeShortestPathGraph;
import subway.domain.vo.SubwayStation;

/*
### 초기 설정

- 프로그램 시작 시 역, 노선, 구간 정보를 초기 설정 해야 한다.
- 거리와 소요 시간은 양의 정수이며 단위는 km와 분을 의미한다.
- 아래의 사전 등록 정보로 반드시 초기 설정을 한다.

```
1. 지하철역으로 교대역, 강남역, 역삼역, 남부터미널역, 양재역, 양재시민의숲역, 매봉역이 등록되어 있다.
2. 지하철 노선으로 2호선, 3호선, 신분당선이 등록되어 있다.
3. 노선에 역이 아래와 같이 등록되어 있다.(왼쪽 끝이 상행 종점)
- 2호선: 교대역 - ( 2km / 3분 ) - 강남역 - ( 2km / 3분 ) - 역삼역
- 3호선: 교대역 - ( 3km / 2분 ) - 남부터미널역 - ( 6km / 5분 ) - 양재역 - ( 1km / 1분 ) - 매봉역
- 신분당선: 강남역 - ( 2km / 8분 ) - 양재역 - ( 10km / 3분 ) - 양재시민의숲역
 */
public class InitializeApplication {
    private final StationRepository stationRepository;
    private final LineRepository lineRepository;
    private final DistanceShortestPathGraph distanceGraph = new DistanceShortestPathGraph();
    private final TimeShortestPathGraph timeGraph = new TimeShortestPathGraph();

    public InitializeApplication(final StationRepository stationRepository,
                                 final LineRepository lineRepository) {
        this.stationRepository = stationRepository;
        this.lineRepository = lineRepository;
    }

    public StationRepository getStationRepository() {
        return stationRepository;
    }

    public LineRepository getLineRepository() {
        return lineRepository;
    }

    public DistanceShortestPathGraph getDistanceGraph() {
        return distanceGraph;
    }

    public TimeShortestPathGraph getTimeGraph() {
        return timeGraph;
    }

    public void initialize() {
        initializeStationAndLine();
        initializeGraphVertex();
    }

    private void initializeStationAndLine() {
        List<SubwayStation> stations = List.of(강남역, 교대역, 역삼역, 남부터미널역, 양재역, 양재시민의숲역, 매봉역);
        for (SubwayStation station : stations) {
            stationRepository.addStation(new Station(station.getValue()));
        }
        List<String> lines = List.of("2호선", "3호선", "신분당선");
        for (String station : lines) {
            lineRepository.addLine(new Line(station));
        }

    }

    private void initializeGraphVertex() {
        Arrays.stream(values())
                .forEach(subwayStation -> distanceGraph.addVertex(subwayStation.getValue()));
        Arrays.stream(values())
                .forEach(subwayStation -> timeGraph.addVertex(subwayStation.getValue()));

        setEdgeWeightTwoHoSun();
        setEdgeWeightThreeHoSun();
        setEdgeWeightShinbundang();
    }


    private void setEdgeWeightTwoHoSun() {
        distanceGraph.setEdgeWeight(교대역.getValue(), 강남역.getValue(), 2);
        distanceGraph.setEdgeWeight(강남역.getValue(), 역삼역.getValue(), 2);

        timeGraph.setEdgeWeight(교대역.getValue(), 강남역.getValue(), 3);
        timeGraph.setEdgeWeight(강남역.getValue(), 역삼역.getValue(), 3);

    }

    private void setEdgeWeightThreeHoSun() {
        distanceGraph.setEdgeWeight(교대역.getValue(), 남부터미널역.getValue(), 3);
        distanceGraph.setEdgeWeight(남부터미널역.getValue(), 양재역.getValue(), 6);
        distanceGraph.setEdgeWeight(양재역.getValue(), 매봉역.getValue(), 1);

        timeGraph.setEdgeWeight(교대역.getValue(), 남부터미널역.getValue(), 2);
        timeGraph.setEdgeWeight(남부터미널역.getValue(), 양재역.getValue(), 5);
        timeGraph.setEdgeWeight(양재역.getValue(), 매봉역.getValue(), 1);
    }

    private void setEdgeWeightShinbundang() {
        distanceGraph.setEdgeWeight(강남역.getValue(), 양재역.getValue(), 2);
        distanceGraph.setEdgeWeight(양재역.getValue(), 양재시민의숲역.getValue(), 10);

        timeGraph.setEdgeWeight(강남역.getValue(), 양재역.getValue(), 8);
        timeGraph.setEdgeWeight(양재역.getValue(), 양재시민의숲역.getValue(), 3);

    }


}
