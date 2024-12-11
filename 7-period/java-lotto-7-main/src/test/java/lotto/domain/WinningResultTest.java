package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningResultTest {


    @Test
    @DisplayName("1등과 2등에 당첨 된다.")
    void getWinningResultTest() {
        //given
        final List<Lotto> lottos = List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Lotto(List.of(1, 2, 3, 4, 5, 8)));
        final LottoGroup lottoGroup = new LottoGroup(lottos);
        final WinningNumber winningNumber = new WinningNumber(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new BonusNumber(8));

        //when
        final WinningResult winningResult = new WinningResult(lottoGroup, winningNumber);
        final Map<LottoRank, Integer> winningReceipt = winningResult.getWinningReceipt();

        //then
        assertAll(
                () -> assertThat(winningReceipt).containsEntry(LottoRank.RANK_1, 1),
                () -> assertThat(winningReceipt).containsEntry(LottoRank.RANK_2, 1),
                () -> assertThat(winningReceipt.get(LottoRank.RANK_3)).isZero(),
                () -> assertThat(winningReceipt.get(LottoRank.RANK_4)).isZero(),
                () -> assertThat(winningReceipt.get(LottoRank.RANK_5)).isZero()
        );
    }

}
