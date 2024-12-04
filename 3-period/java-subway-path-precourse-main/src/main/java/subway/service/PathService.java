package subway.service;

import java.util.List;
import subway.domain.option.PathCriteriaOption;
import subway.domain.vo.ArrivalStation;
import subway.domain.vo.DepartureStation;
import subway.dto.PathSearchResult;

public class PathService {
    private final PathFinder pathFinder;
    private final PathResultCalculator pathResultCalculator;

    public PathService(PathFinder pathFinder, PathResultCalculator pathResultCalculator) {
        this.pathFinder = pathFinder;
        this.pathResultCalculator = pathResultCalculator;
    }

    public PathSearchResult findPath(DepartureStation departure, ArrivalStation arrival,
                                     PathCriteriaOption criteriaOption) {
        List<String> path = pathFinder.findPath(departure, arrival, criteriaOption);
        return pathResultCalculator.calculateResult(path);
    }
}
