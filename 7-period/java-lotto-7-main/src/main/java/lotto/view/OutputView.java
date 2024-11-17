package lotto.view;

import java.util.List;
import lotto.dto.LottoNumber;
import lotto.dto.WinningLottoRecipe;

public interface OutputView {

    void printAskMoney();

    void printLottoNumbers(final List<LottoNumber> lottoNumbers);

    void printAskWinningNumber();

    void printAskBonusNumber();

    void printWinningLottoRecipes(final List<WinningLottoRecipe> winningLottoRecipes);

    void printPrizeResultRatio(final double prizeResultRatio);
}
