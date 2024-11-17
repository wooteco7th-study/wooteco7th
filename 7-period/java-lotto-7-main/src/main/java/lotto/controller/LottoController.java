package lotto.controller;

import java.util.List;
import java.util.Map;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.LottoStore;
import lotto.domain.Money;
import lotto.domain.PrizeResult;
import lotto.domain.WinningNumber;
import lotto.domain.WinningResult;
import lotto.dto.LottoNumber;
import lotto.dto.WinningLottoRecipe;
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
        final List<Lotto> lottos = LottoStore.generateLottos(money);
        responseLottoNumbers(lottos);
        final WinningNumber winningNumber = requestWinningNumber();
        final WinningResult winningResult = new WinningResult(lottos, winningNumber);
        responseWinningResult(winningResult);
        final PrizeResult prizeResult = new PrizeResult(winningResult, money);
        responsePrizeResult(prizeResult);
    }

    private void responsePrizeResult(final PrizeResult prizeResult) {
        outputView.printPrizeResultRatio(prizeResult.getPrizeResultRatio());
    }

    private void responseWinningResult(final WinningResult winningResult) {
        final Map<LottoRank, Integer> lottoRankResult = winningResult.getLottoRankResult();
        final List<WinningLottoRecipe> winningLottoRecipes = lottoRankResult.entrySet()
                .stream()
                .map(WinningLottoRecipe::of)
                .toList();
        outputView.printWinningLottoRecipes(winningLottoRecipes);
    }


    private void responseLottoNumbers(final List<Lotto> lottos) {
        final List<LottoNumber> lottoNumbers = lottos.stream()
                .map(LottoNumber::of)
                .toList();
        outputView.printLottoNumbers(lottoNumbers);
    }

    private Money requestMoney() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskMoney();
            final int number = inputView.readNumber();
            return new Money(number);
        });
    }

    private WinningNumber requestWinningNumber() {
        final Lotto winningLotto = requestWinningLotto();
        return LoopTemplate.tryCatchLoop(() -> {
            final BonusNumber bonusNumber = requestBonusNumber();
            return new WinningNumber(winningLotto, bonusNumber);
        });
    }

    private Lotto requestWinningLotto() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskWinningNumber();
            final List<Integer> numbers = inputView.readNumbers();
            return new Lotto(numbers);
        });
    }

    private BonusNumber requestBonusNumber() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskBonusNumber();
            final int number = inputView.readNumber();
            return new BonusNumber(number);
        });
    }
}
