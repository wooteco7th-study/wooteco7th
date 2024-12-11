package lotto.domain.lotto;

import static lotto.domain.lotto.LottoAward.FIRST;
import static lotto.domain.lotto.LottoAward.FOURTH;
import static lotto.domain.lotto.LottoAward.SECOND;
import static lotto.domain.lotto.LottoAward.THIRD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.Map;
import lotto.domain.amount.PurchaseAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultCalculatorTest {

    @Test
    @DisplayName("우승 결과를 계산한다.")
    void calculateWinningResult() {
        // Given
        ResultCalculator resultCalculator = makeResultCalculator();
        Lottos purchaseLottos = makeLottos();

        // When
        Map<LottoAward, Integer> result = resultCalculator.calculateWinningResult(purchaseLottos);

        // Then
        assertAll(
                () -> assertThat(result.containsKey(LottoAward.FIFTH)).isTrue(),
                () -> assertThat(result.get(FIRST)).isEqualTo(0),
                () -> assertThat(result.get(SECOND)).isEqualTo(0),
                () -> assertThat(result.get(THIRD)).isEqualTo(0),
                () -> assertThat(result.get(FOURTH)).isEqualTo(0),
                () -> assertThat(result.get(LottoAward.NONE)).isEqualTo(1)
        );
    }

    private Lottos makeLottos() {
        List<Lotto> lottos = List.of(new Lotto(List.of(1, 2, 3, 11, 12, 13)),
                new Lotto(List.of(20, 21, 22, 23, 24, 25)));
        return new Lottos(lottos);
    }

    private ResultCalculator makeResultCalculator() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(7);
        return new ResultCalculator(winningLotto, bonusNumber);
    }

    @Test
    @DisplayName("수익률을 계산한다.")
    void calculateProfitRate() {
        // Given
        ResultCalculator resultCalculator = makeResultCalculator();
        Lottos purchaseLottos = makeLottos();
        resultCalculator.calculateWinningResult(purchaseLottos);

        // When & Then
        double profitRate = resultCalculator.calculateProfitRate(new PurchaseAmount(3000));

        assertThat(profitRate).isEqualTo(166.66666666666669);
    }
}
