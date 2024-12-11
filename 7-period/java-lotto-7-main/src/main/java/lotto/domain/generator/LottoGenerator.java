package lotto.domain.generator;

import java.util.stream.IntStream;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;

public class LottoGenerator {

    private final NumberGenerator lottoNumberGenerator;

    public LottoGenerator(final NumberGenerator lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public Lottos generateMultiple(final int quantity) {
        return new Lottos(IntStream.range(0, quantity)
                .mapToObj(i -> generate())
                .toList());
    }

    private Lotto generate() {
        return new Lotto(lottoNumberGenerator.generate());
    }
}
