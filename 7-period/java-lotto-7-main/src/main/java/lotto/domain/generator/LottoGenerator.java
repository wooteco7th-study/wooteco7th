package lotto.domain.generator;

import java.util.stream.IntStream;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;

public class LottoGenerator {

    private final LottoNumberGenerator lottoNumberGenerator;

    public LottoGenerator(final LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public Lotto generate() {
        return new Lotto(lottoNumberGenerator.generate());
    }

    public Lottos generateMultiple(final int quantity) {
        return new Lottos(IntStream.range(0, quantity)
                .mapToObj(i -> generate())
                .toList());
    }
}
