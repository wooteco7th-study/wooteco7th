package subway.service;

import java.util.List;
import subway.domain.Order;
import subway.domain.path.PathFinder;
import subway.dto.ResultDto;

public class SubwayService {

    public ResultDto processMinimumTime(final Order order,
                                        final PathFinder pathFinder) {
        List<String> shortestTimePath = makeTimePath(order, pathFinder);
        return makeResult(pathFinder, shortestTimePath);
    }

    public ResultDto processShortestDistance(final Order order, final PathFinder pathFinder) {
        List<String> shortestDistancePath = makeDistancePath(order, pathFinder);
        return makeResult(pathFinder, shortestDistancePath);
    }

    private List<String> makeTimePath(final Order order, final PathFinder pathFinder) {
        String departureStation = order.getDepartureStation().getName();
        String arrivalStation = order.getArrivalStation().getName();
        pathFinder.validatePathConnected(departureStation, arrivalStation);
        return pathFinder.calculateShortestTimePath(departureStation, arrivalStation);
    }

    private List<String> makeDistancePath(final Order order, final PathFinder pathFinder) {
        String departureStation = order.getDepartureStation().getName();
        String arrivalStation = order.getArrivalStation().getName();
        pathFinder.validatePathConnected(departureStation, arrivalStation);
        return pathFinder.calculateShortestDistancePath(departureStation, arrivalStation);
    }

    private ResultDto makeResult(final PathFinder pathFinder, final List<String> shortestTimePath) {
        int totalTime = pathFinder.getTotalTime(shortestTimePath);
        int totalDistance = pathFinder.getTotalDistance(shortestTimePath);
        return new ResultDto(totalDistance, totalTime, shortestTimePath);
    }
}
