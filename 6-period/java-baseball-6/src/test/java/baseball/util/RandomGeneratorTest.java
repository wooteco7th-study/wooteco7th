package baseball.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("랜덤 생성기 테스트")
class RandomGeneratorTest {

    @Test
    @DisplayName("랜덤으로 3개의 숫자를 생성한다.")
    void 성공_랜덤생성() {
        // Given

        // When
        List<Integer> numbers = RandomGenerator.makeRandomNumbers();

        // Then
        assertAll(
                () -> assertThat(numbers.size()).isEqualTo(3),
                () -> assertThat(numbers.stream().distinct().count()).isEqualTo(3),
                () -> assertThat(numbers).allMatch(number -> number >= 1 && number <= 9)
        );
    }
}
