package subway;

import java.util.Scanner;
import subway.controller.SubwayController;
import subway.domain.repository.LineRepository;
import subway.domain.repository.StationRepository;
import subway.service.DistanceShortestPath;
import subway.service.PathService;
import subway.service.TimeShortestPath;
import subway.view.RequestView;
import subway.view.ResponseView;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        // TODO: 프로그램 구현
        var stationRepository = new StationRepository();
        var lineRepository = new LineRepository();
        var initializeApplication = new InitializeApplication(stationRepository, lineRepository);
        initializeApplication.initialize();

        var distanceGraph = initializeApplication.getDistanceGraph();
        var timeGraph = initializeApplication.getTimeGraph();

        DistanceShortestPath distanceShortestPath = new DistanceShortestPath(distanceGraph.getGraph());
        TimeShortestPath timeShortestPath = new TimeShortestPath(timeGraph.getGraph());

        new SubwayController(
                new RequestView(scanner),
                new ResponseView(),
                new PathService(
                        distanceShortestPath,
                        timeShortestPath,
                        distanceGraph,
                        timeGraph
                )
        ).run();

    }
}
