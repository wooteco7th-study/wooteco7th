package lotto.domain.lotto;

import java.util.EnumMap;
import java.util.Map;
import lotto.domain.amount.PurchaseAmount;
import lotto.exception.CustomIllegalArgumentException;
import lotto.exception.ErrorMessage;

public class ResultCalculator {

    private static final int FULL_PERCENTAGE = 100;

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;
    private final Map<LottoAward, Integer> result;

    public ResultCalculator(final Lotto winningLotto, final LottoNumber bonusNumber) {
        validate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
        this.result = initialize();
    }

    public Map<LottoAward, Integer> calculateWinningResult(final Lottos purchaseLottos) {
        for (Lotto purchaseLotto : purchaseLottos.getLottos()) {
            calculateEachLotto(purchaseLotto);
        }
        return result;
    }

    public double calculateProfitRate(final PurchaseAmount purchaseAmount) {
        int profit = calculateProfit();
        return (double) profit / purchaseAmount.getValue() * FULL_PERCENTAGE;
    }

    private Map<LottoAward, Integer> initialize() {
        EnumMap<LottoAward, Integer> result = new EnumMap<>(LottoAward.class);
        for (LottoAward lottoAward : LottoAward.getValuedLottoAward()) {
            result.put(lottoAward, 0);
        }
        return result;
    }

    private void validate(final Lotto winningLotto, final LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_LOTTO_BONUS_NUMBER_DUPLICATED);
        }
    }

    private int calculateProfit() {
        return result.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
    }

    private void calculateEachLotto(final Lotto purchaseLotto) {
        int matchingCount = purchaseLotto.countMatchingNumber(winningLotto);
        boolean matchBonus = purchaseLotto.doesMatchBonus(bonusNumber);
        LottoAward award = LottoAward.from(matchingCount);
        result.merge(award, 1, Integer::sum);
    }
}
