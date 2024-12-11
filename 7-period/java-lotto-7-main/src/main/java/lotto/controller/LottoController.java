package lotto.controller;

import lotto.domain.LottoGroup;
import lotto.domain.Money;
import lotto.dto.LottoReceiptGroup;
import lotto.util.LoopTemplate;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Money money = requestMoney();
        final LottoGroup lottoGroup = LottoGroup.of(money);
        outputView.printLottoReceiptGroup(LottoReceiptGroup.of(lottoGroup));
    }

    private Money requestMoney() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskMoney();
            return new Money(inputView.readNumber());
        });
    }
}
