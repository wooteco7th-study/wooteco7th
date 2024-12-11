package lotto.controller;

import java.util.Map;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoGroup;
import lotto.domain.LottoRank;
import lotto.domain.Money;
import lotto.domain.WinningBenefit;
import lotto.domain.WinningNumber;
import lotto.domain.WinningResult;
import lotto.dto.LottoReceiptGroup;
import lotto.dto.WinningReceiptGroup;
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
        final WinningNumber winningNumber = requestWinningNumber();
        final WinningResult winningResult = new WinningResult(lottoGroup, winningNumber);
        responseResult(winningResult, money);
    }

    private void responseResult(final WinningResult winningResult, final Money money) {
        final Map<LottoRank, Integer> winningReceipt = winningResult.getWinningReceipt();
        final WinningBenefit winningBenefit = new WinningBenefit(winningReceipt);
        outputView.printWinningResultReceipts(WinningReceiptGroup.of(winningReceipt));
        outputView.printWinningRatio(winningBenefit.getWinningRatio(money));
    }

    private Money requestMoney() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskMoney();
            return new Money(inputView.readNumber());
        });
    }

    private WinningNumber requestWinningNumber() {
        final Lotto winningLotto = requestWinningLotto();
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskBonusNumber();
            final BonusNumber bonusNumber = new BonusNumber(inputView.readNumber());
            return new WinningNumber(winningLotto, bonusNumber);
        });
    }

    private Lotto requestWinningLotto() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskWinningNumber();
            return new Lotto(inputView.readNumbers());
        });
    }
}
