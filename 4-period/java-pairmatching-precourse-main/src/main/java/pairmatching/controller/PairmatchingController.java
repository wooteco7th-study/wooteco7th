package pairmatching.controller;

import pairmatching.service.PairmatchinService;
import pairmatching.view.RequestView;
import pairmatching.view.ResponseView;

public class PairmatchingController {
    private final RequestView requestView;
    private final ResponseView responseView;
    private final PairmatchinService pairmatchinService;

    public PairmatchingController(final RequestView requestView, final ResponseView responseView,
                                  final PairmatchinService pairmatchinService) {
        this.requestView = requestView;
        this.responseView = responseView;
        this.pairmatchinService = pairmatchinService;
    }

    public void run() {
//        while (true) {
//            MainOption mainOption = requestView.requestMainOptionSelection();
//            if (mainOption == MainOption.QUIT) {
//                break;
//            }
//            if (mainOption == MainOption.PATH_QUERY) {
//                requestPathCriteria();
//            }
//        }
        requestView.requestFunctionOption();
        requestView.requestFairMatching();
        requestView.aksPairRematch();
    }

//    private void requestPathCriteria() {
//        while (true) {
//            try {
//                PathCriteriaOption criteriaOption = requestView.requestPathCriteriaSelection();
//                if (criteriaOption == PathCriteriaOption.BACK) {
//                    return;
//                }
//                getShortestPath(criteriaOption);
//                return;
//            } catch (IllegalArgumentException e) {
//                responseView.printError(e.getMessage());
//            }
//        }
//
//    }
//
//    private void getShortestPath(PathCriteriaOption criteriaOption) {
//        DepartureStation from = requestView.requestDepartureStation();
//        ArrivalStation to = requestView.requestArrivalStation();
//
//        PathSearchResult result = pathService.findPath(from, to, criteriaOption);
//
//        responseView.printPathResult(result);
//    }
}
