package subway.service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import subway.command.RouteCriteriaCommand;
import subway.domain.Order;
import subway.domain.path.DistancePathFinder;
import subway.domain.path.PathFinder;
import subway.domain.path.TimePathFinder;
import subway.dto.ResultDto;

public class SubwayService {

    private final TimePathFinder timePathFinder;
    private final DistancePathFinder distancePathFinder;
    private final Map<RouteCriteriaCommand, PathFinder> pathFinders;

    public SubwayService(final TimePathFinder timePathFinder, final DistancePathFinder distancePathFinder) {
        this.timePathFinder = timePathFinder;
        this.distancePathFinder = distancePathFinder;
        this.pathFinders = initializePathFinders();
    }

    private Map<RouteCriteriaCommand, PathFinder> initializePathFinders() {
        EnumMap<RouteCriteriaCommand, PathFinder> pathFinders = new EnumMap<>(
                RouteCriteriaCommand.class);
        pathFinders.put(RouteCriteriaCommand.최단거리, distancePathFinder);
        pathFinders.put(RouteCriteriaCommand.최소시간, timePathFinder);
        return pathFinders;
    }

    public ResultDto process(final Order order, final RouteCriteriaCommand command) {
        PathFinder pathFinder = pathFinders.get(command);
        List<String> shortestTimePath = makePath(order, pathFinder);
        return makeResult(pathFinder, shortestTimePath);
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
