package lotto.controller;

import lotto.domain.Amount;
import lotto.exception.ExceptionHandler;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ExceptionHandler exceptionHandler;
    private final LottoService lottoService;

    public LottoController(final InputView inputView, final OutputView outputView,
                           final ExceptionHandler exceptionHandler,
                           final LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.exceptionHandler = exceptionHandler;
        this.lottoService = lottoService;
    }

    public void process() {
        Amount purchaseAmount = makeAmount();

    }

    private Amount makeAmount() {
        // 구입금액 입력
        outputView.showRequestAmount();
        return new Amount(inputView.readAmount());
    }
}
