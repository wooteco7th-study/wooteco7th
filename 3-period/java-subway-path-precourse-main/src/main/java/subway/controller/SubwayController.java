package subway.controller;

import subway.domain.option.MainOption;
import subway.domain.option.PathCriteriaOption;
import subway.domain.vo.ArrivalStation;
import subway.domain.vo.DepartureStation;
import subway.dto.PathSearchResult;
import subway.service.PathService;
import subway.view.RequestView;
import subway.view.ResponseView;

public class SubwayController {
    private final RequestView requestView;
    private final ResponseView responseView;
    private final PathService pathService;

    public SubwayController(final RequestView requestView, final ResponseView responseView,
                            final PathService pathService) {
        this.requestView = requestView;
        this.responseView = responseView;
        this.pathService = pathService;
    }

    public void run() {
        while (true) {
            MainOption mainOption = requestView.requestMainOptionSelection();
            if (mainOption == MainOption.QUIT) {
                break;
            }
            if (mainOption == MainOption.PATH_QUERY) {
                requestPathCriteria();
            }
        }
    }

    private void requestPathCriteria() {
        while (true) {
            try {
                PathCriteriaOption criteriaOption = requestView.requestPathCriteriaSelection();
                if (criteriaOption == PathCriteriaOption.BACK) {
                    return;
                }
                getShortestPath(criteriaOption);
                return;
            } catch (IllegalArgumentException e) {
                responseView.printError(e.getMessage());
            }
        }

    }

    private void getShortestPath(PathCriteriaOption criteriaOption) {
        DepartureStation from = requestView.requestDepartureStation();
        ArrivalStation to = requestView.requestArrivalStation();

        PathSearchResult result = pathService.findPath(from, to, criteriaOption);

        responseView.printPathResult(result);
    }
}
