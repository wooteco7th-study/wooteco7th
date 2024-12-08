package subway.service;

import java.util.List;
import subway.domain.Order;
import subway.domain.path.PathFinder;
import subway.dto.ResultDto;

public class SubwayService {

    public ResultDto process(final Order order, final PathFinder pathFinder) {
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
