package subway.service;

import java.util.List;
import subway.command.RouteCriteriaCommand;
import subway.domain.Order;
import subway.domain.path.DistancePathFinder;
import subway.domain.path.PathFinder;
import subway.domain.path.TimePathFinder;
import subway.dto.ResultDto;

public class SubwayService {

    private final TimePathFinder timePathFinder;
    private final DistancePathFinder distancePathFinder;

    public SubwayService(final TimePathFinder timePathFinder, final DistancePathFinder distancePathFinder) {
        this.timePathFinder = timePathFinder;
        this.distancePathFinder = distancePathFinder;
    }

    public ResultDto process(final Order order, final RouteCriteriaCommand command) {
        PathFinder pathFinder = getPathFinderByCommand(command);
        List<String> shortestTimePath = makePath(order, pathFinder);
        return makeResult(pathFinder, shortestTimePath);
    }

    private PathFinder getPathFinderByCommand(final RouteCriteriaCommand command) {
        if (command.isShortestDistance()) {
            return distancePathFinder;
        }
        return timePathFinder;
    }

    private List<String> makePath(final Order order, final PathFinder pathFinder) {
        String departureStation = order.getDepartureStationName();
        String arrivalStation = order.getArrivalStationName();
        pathFinder.validatePath(departureStation, arrivalStation);
        return pathFinder.calculateShortestPath(departureStation, arrivalStation);
    }

    private ResultDto makeResult(final PathFinder pathFinder, final List<String> shortestTimePath) {
        int totalTime = pathFinder.getTotalTime(shortestTimePath);
        int totalDistance = pathFinder.getTotalDistance(shortestTimePath);
        return new ResultDto(totalDistance, totalTime, shortestTimePath);
    }
}
