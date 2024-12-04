package subway.service;

import java.util.List;
import subway.domain.Info;
import subway.domain.Line;
import subway.domain.LineInfo;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;

public class SubwayCreator {

    private final List<Info> infos = List.of(
            new Info("2호선", "교대역", "강남역", 2, 3),
            new Info("2호선", "강남역", "역삼역", 2, 3),
            new Info("3호선", "교대역", "남부터미널역", 3, 2),
            new Info("3호선", "남부터미널역", "양재역", 6, 5),
            new Info("3호선", "양재역", "매봉역", 1, 1),
            new Info("신분당역", "강남역", "양재역", 2, 8),
            new Info("신분당역", "양재역", "양재시민의숲역", 10, 3)
    );

    public SubwayCreator() {
        init();
    }

    private void init() {
        for (Info info : infos) {
            StationRepository.addStation(new Station(info.getStartStation()));
            StationRepository.addStation(new Station(info.getEndStation()));

            Station startStation = StationRepository.findStation(info.getStartStation());
            Station endStation = StationRepository.findStation(info.getEndStation());

            LineRepository.addLine(new Line(info.getLine()));
            Line line = LineRepository.findLine(info.getLine());

            LineInfo lineInfo = new LineInfo(startStation, endStation, info.getDistance(), info.getMinute());
            LineRepository.addLineInfo(line, lineInfo);
        }
    }
}
