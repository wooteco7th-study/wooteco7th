package lotto.dto;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoGroup;

public record LottoReceiptGroup(
        List<LottoReceipt> lottoReceipts
) {
    public static LottoReceiptGroup of(final LottoGroup lottoGroup) {
        return new LottoReceiptGroup(lottoGroup.getLottos().stream()
                .map(LottoReceipt::of)
                .toList());
    }

    public record LottoReceipt(
            List<Integer> numbers
    ) {
        public static LottoReceipt of(final Lotto lotto) {
            return new LottoReceipt(lotto.getNumbers());
        }
    }
}
