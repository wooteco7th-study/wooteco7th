package pairmatching.controller;

import static pairmatching.domain.vo.FunctionOption.FAIR_CHECK;
import static pairmatching.domain.vo.FunctionOption.FAIR_MATCHING;
import static pairmatching.domain.vo.FunctionOption.FAIR_RESET;
import static pairmatching.domain.vo.FunctionOption.QUIT;
import static pairmatching.domain.vo.RematchOption.YES;

import pairmatching.domain.vo.FunctionOption;
import pairmatching.domain.vo.RematchOption;
import pairmatching.dto.FairMatchingRequestDto;
import pairmatching.dto.FairMatchingResponseDto;
import pairmatching.service.PairmatchinService;
import pairmatching.util.RetryOnExceptionTemplate;
import pairmatching.view.RequestView;
import pairmatching.view.ResponseView;

public class PairmatchingController {
    private final RequestView requestView;
    private final ResponseView responseView;
    private final PairmatchinService pairmatchingService;

    public PairmatchingController(final RequestView requestView, final ResponseView responseView,
                                  final PairmatchinService pairmatchingService) {
        this.requestView = requestView;
        this.responseView = responseView;
        this.pairmatchingService = pairmatchingService;
    }

    public void run() {
        while (true) {
            try {
                FunctionOption functionOption = requestView.requestFunctionOption();
                if (functionOption == QUIT) {
                    break;
                }
                if (functionOption == FAIR_MATCHING) {
                    requestFairMatching();
                }
                if (functionOption == FAIR_CHECK) {
                    requestGetFair();
                }
                if (functionOption == FAIR_RESET) {
                    deleteFair();
                }
            } catch (IllegalArgumentException e) {
                responseView.printError(e.getMessage());
            }

        }
    }

    private void requestFairMatching() {
        responseView.printFairMatchingBoard();
        RetryOnExceptionTemplate.repeatUntilSuccess(() -> {
            FairMatchingRequestDto requestDto = requestView.requestFairMatching();
            boolean alreadyMatch = pairmatchingService.isAlreadyMatch();
            isYes(requestDto, alreadyMatch);
            isNo(requestDto, alreadyMatch);
        });

    }

    private void isYes(final FairMatchingRequestDto requestDto, final boolean alreadyMatch) {
        if (alreadyMatch == true) {
            RematchOption rematchOption = requestView.aksPairRematch();
            if (rematchOption.equals(YES)) {
                FairMatchingResponseDto responseDto = pairmatchingService.createFairMatch(requestDto);
                responseView.printFairMatchingResult(responseDto);
            }
        }
    }

    private void isNo(final FairMatchingRequestDto requestDto, final boolean alreadyMatch) {
        if (alreadyMatch == false) {
            FairMatchingResponseDto responseDto = pairmatchingService.createFairMatch(requestDto);
            responseView.printFairMatchingResult(responseDto);
        }
    }

    private void requestGetFair() {
        FairMatchingRequestDto requestDto = requestView.requestFairMatching();
        FairMatchingResponseDto responseDto = pairmatchingService.getFair(requestDto);
        responseView.printFairMatchingResult(responseDto);

    }

    private void deleteFair() {
        pairmatchingService.deleteAll();
        responseView.printFormatMessage();
    }

}
