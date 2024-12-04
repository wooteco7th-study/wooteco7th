package subway.view.mapper;

import subway.domain.option.MainOption;
import subway.domain.option.PathCriteriaOption;
import subway.domain.vo.ArrivalStation;
import subway.domain.vo.DepartureStation;

public class ModelMapper {

    private ModelMapper() {
    }

    public static MainOption toMainOption(String input) {
        return MainOption.from(input);
    }

    public static PathCriteriaOption toPathCriteriaOption(String input) {
        return PathCriteriaOption.from(input);
    }

    public static DepartureStation toDepartureStation(String input) {

        return new DepartureStation(input);
    }

    public static ArrivalStation toArrivalStation(String input) {

        return new ArrivalStation(input);
    }

}
