package lotto.controller;

import static lotto.util.RetryOnExceptionTemplate.retryOnException;

import lotto.domain.UserLottos;
import lotto.domain.WinningNumbers;
import lotto.domain.vo.BonusNumber;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.PurchaseAmount;
import lotto.service.LottoService;
import lotto.service.RandomNumberGenerator;
import lotto.view.RequestView;
import lotto.view.ResponseView;

public class LottoController {
    private final RequestView requestView;
    private final LottoService lottoService;
    private final ResponseView responseView;

    public LottoController() {
        this.requestView = new RequestView();
        this.lottoService = new LottoService(new RandomNumberGenerator());
        this.responseView = new ResponseView();
    }

    public void run() {
        var userLottos = createUserLottoTransaction();
        var winningNumbers = createWinningNumbersTransaction();

        createWinningInfoTransaction(userLottos, winningNumbers);
    }

    private void createWinningInfoTransaction(final UserLottos userLottos, final WinningNumbers winningNumbers) {
        var winningStatisticsInfo = lottoService.getWinningStatisticsInfo(userLottos, winningNumbers);
        responseView.printWinningStatisticsInfo(winningStatisticsInfo);
    }

    private WinningNumbers createWinningNumbersTransaction() {
        return retryOnException(() -> {
            Lotto lotto = requestView.requestWinningNumbers();
            BonusNumber bonusNumber = requestView.requestBonusNumber();
            var winningNumbers = lottoService.createWinningNumbers(lotto, bonusNumber);

            return winningNumbers;
        });
    }

    private UserLottos createUserLottoTransaction() {
        return retryOnException(() -> {
            PurchaseAmount purchaseAmount = requestView.requestPurchaseAmount();
            var userLottosInfo = lottoService.createUserLottos(purchaseAmount);
            responseView.responseUserLottosInfo(userLottosInfo);

            return userLottosInfo.getUserLottos();
        });
    }
}
