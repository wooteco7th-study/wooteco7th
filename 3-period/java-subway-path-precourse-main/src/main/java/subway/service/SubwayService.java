package subway.service;

import java.util.List;
import subway.domain.Order;
import subway.domain.path.PathFinder;
import subway.domain.route.RoutesRepository;
import subway.domain.station.Station;
import subway.dto.ResultDto;

public class SubwayService {

    public ResultDto processMinimumTime(final Order order,
                                        final PathFinder pathFinder) {
        Station departureStation = order.getDepartureStation();
        Station arrivalStation = order.getArrivalStation();
        List<String> shortestTimePath = pathFinder.getShortestTimePath(departureStation.getName(),
                arrivalStation.getName());
        int totalTime = RoutesRepository.getTotalTime(shortestTimePath);
        int totalDistance = RoutesRepository.getTotalDistance(shortestTimePath);
        return new ResultDto(totalDistance, totalTime, shortestTimePath);
    }

    public ResultDto processShortestDistance(final Order order, final PathFinder pathFinder) {
        Station departureStation = order.getDepartureStation();
        Station arrivalStation = order.getArrivalStation();
        List<String> shortestDistancePath = pathFinder.getShortestDistancePath(departureStation.getName(),
                arrivalStation.getName());
        int totalDistance = RoutesRepository.getTotalDistance(shortestDistancePath);
        int totalTime = RoutesRepository.getTotalTime(shortestDistancePath);
        return new ResultDto(totalDistance, totalTime, shortestDistancePath);
    }

}
