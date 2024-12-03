package lotto.service;

import java.util.List;
import java.util.stream.IntStream;
import lotto.constant.LottoRule;
import lotto.domain.UserLottos;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningStatistics;
import lotto.domain.vo.BonusNumber;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.PurchaseAmount;
import lotto.dto.UserLottosInfo;
import lotto.dto.WinningStatisticsInfo;

public class LottoService {
    private final RandomNumberGenerator randomNumberGenerator;

    public LottoService(final RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public UserLottosInfo createUserLottos(final PurchaseAmount purchaseAmount) {
        int lottoQuantity = purchaseAmount.getValue() / LottoRule.PURCHASE_AMOUNT_UNIT;

        List<Lotto> lottos = IntStream.range(0, lottoQuantity)
                .mapToObj(i -> new Lotto(randomNumberGenerator.generate()))
                .toList();
        UserLottos userLottos = new UserLottos(lottos, purchaseAmount);

        return new UserLottosInfo(userLottos, lottoQuantity);
    }

    public WinningNumbers createWinningNumbers(final Lotto lotto, final BonusNumber bonusNumber) {
        return new WinningNumbers(lotto, bonusNumber);
    }

    public WinningStatisticsInfo getWinningStatisticsInfo(final UserLottos userLottos,
                                                          final WinningNumbers winningNumbers) {
        return new WinningStatistics(userLottos, winningNumbers)
                .compile()
                .getWinningInfo();
    }
}
