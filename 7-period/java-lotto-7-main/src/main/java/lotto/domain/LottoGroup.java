package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class LottoGroup {

    private final List<Lotto> lottos;

    public LottoGroup(final List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static LottoGroup of(final Money money) {
        final int count = money.calculateQuotient(Money.UNIT_AND_TICKET_PRICE);
        return new LottoGroup(IntStream.range(0, count)
                .mapToObj(idx -> QuickLottoGenerator.generate(Lotto.NUMBER_MIN, Lotto.NUMBER_MAX, Lotto.NUMBERS_SIZE))
                .toList());
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
