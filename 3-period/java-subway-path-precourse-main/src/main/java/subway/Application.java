package subway;

import java.util.Scanner;
import subway.controller.SubwayController;
import subway.domain.repository.LineRepository;
import subway.domain.repository.StationRepository;
import subway.service.DistanceShortestPath;
import subway.service.PathFinder;
import subway.service.PathResultCalculator;
import subway.service.PathService;
import subway.service.TimeShortestPath;
import subway.view.RequestView;
import subway.view.ResponseView;

public class Application {
    public static void main(String[] args) {
        SubwayController controller = createSubwayController(new Scanner(System.in));
        controller.run();
    }

    private static SubwayController createSubwayController(Scanner scanner) {
        InitializeApplication initApp = createInitializedApp();
        PathService pathService = createPathService(initApp);

        return new SubwayController(
                new RequestView(scanner),
                new ResponseView(),
                pathService
        );
    }

    private static InitializeApplication createInitializedApp() {
        var initApp = new InitializeApplication(
                new StationRepository(),
                new LineRepository()
        );
        initApp.initialize();
        return initApp;
    }

    private static PathService createPathService(InitializeApplication initApp) {
        var distanceShortestPath = new DistanceShortestPath(initApp.getDistanceGraph().getGraph());
        var timeShortestPath = new TimeShortestPath(initApp.getTimeGraph().getGraph());

        return new PathService(
                new PathFinder(
                        distanceShortestPath,
                        timeShortestPath,
                        initApp.getDistanceGraph(),
                        initApp.getTimeGraph()
                ),
                new PathResultCalculator(
                        distanceShortestPath,
                        timeShortestPath
                )
        );
    }
}

