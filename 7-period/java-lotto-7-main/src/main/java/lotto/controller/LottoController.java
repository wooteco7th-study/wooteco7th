package lotto.controller;

import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;
import lotto.domain.generator.LottoGenerator;
import lotto.dto.LottoDto;
import lotto.exception.ExceptionHandler;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ExceptionHandler exceptionHandler;
    private final LottoService lottoService;
    private final LottoGenerator lottoGenerator;

    public LottoController(final InputView inputView, final OutputView outputView,
                           final ExceptionHandler exceptionHandler,
                           final LottoService lottoService, final LottoGenerator lottoGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.exceptionHandler = exceptionHandler;
        this.lottoService = lottoService;
        this.lottoGenerator = lottoGenerator;
    }

    public void process() {
        int quantity = calculateQuantity();
        Lottos purchaseLottos = makeLottos(quantity);
        showPurchaseLottos(quantity, purchaseLottos);
    }

    private void showPurchaseLottos(final int quantity, final Lottos purchaseLottos) {
        LottoDto lottoDto = lottoService.convertToLottoDto(purchaseLottos);
        outputView.showPurchaseLotto(quantity, lottoDto.numbers());
    }

    private Lottos makeLottos(final int quantity) {
        return lottoGenerator.generateMultiple(quantity);
    }

    private int calculateQuantity() {
        PurchaseAmount purchaseAmount = makeAmount();
        return purchaseAmount.calculateLottoQuantity();
    }

    private PurchaseAmount makeAmount() {
        outputView.showRequestAmount();
        return exceptionHandler.retryUntilSuccess(() -> new PurchaseAmount(inputView.readAmount()));
    }
}
