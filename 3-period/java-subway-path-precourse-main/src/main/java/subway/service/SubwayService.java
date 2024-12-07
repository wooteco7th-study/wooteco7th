package subway.service;

import java.util.List;
import subway.domain.Order;
import subway.domain.path.PathFinder;
import subway.domain.route.SectionRepository;
import subway.domain.station.Station;
import subway.dto.ResultDto;

public class SubwayService {

    public ResultDto processMinimumTime(final Order order,
                                        final PathFinder pathFinder) {
        Station departureStation = order.getDepartureStation();
        Station arrivalStation = order.getArrivalStation();
        List<String> shortestTimePath = pathFinder.calculateShortestTimePath(departureStation.getName(),
                arrivalStation.getName());
        int totalTime = SectionRepository.getTotalTime(shortestTimePath);
        int totalDistance = SectionRepository.getTotalDistance(shortestTimePath);
        return new ResultDto(totalDistance, totalTime, shortestTimePath);
    }

    public ResultDto processShortestDistance(final Order order, final PathFinder pathFinder) {
        Station departureStation = order.getDepartureStation();
        Station arrivalStation = order.getArrivalStation();
        List<String> shortestDistancePath = pathFinder.calculateShortestDistancePath(departureStation.getName(),
                arrivalStation.getName());
        int totalDistance = SectionRepository.getTotalDistance(shortestDistancePath);
        int totalTime = SectionRepository.getTotalTime(shortestDistancePath);
        return new ResultDto(totalDistance, totalTime, shortestDistancePath);
    }
}
