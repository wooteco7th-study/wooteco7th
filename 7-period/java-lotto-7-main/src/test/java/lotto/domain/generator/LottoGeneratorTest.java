package lotto.domain.generator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.Lottos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoGeneratorTest {

    @Test
    @DisplayName("로또를 생성한다.")
    void generateMultiple() {
        // Given
        LottoGenerator lottoGenerator = new LottoGenerator(() -> List.of(6, 5, 7, 8, 4, 9));

        // When
        Lottos lottos = lottoGenerator.generateMultiple(3);

        // Then
        assertAll(
                () -> assertThat(lottos.getLottos()).hasSize(3),
                () -> assertThat(lottos.getLottos().getFirst()).isEqualTo(makeLotto())
        );
    }

    public Lotto makeLotto() {
        return new Lotto(List.of(4, 5, 6, 7, 8, 9));
    }
}
