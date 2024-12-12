package oncall.util;

import static oncall.support.CustomAssert.assertIllegalArgument;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import oncall.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StringParserTest {

    @Nested
    @DisplayName("문자열을 정수로 변환하는 기능")
    class ParseToInteger {

        @Test
        @DisplayName("정상적인 숫자 문자열을 정수로 변환한다")
        void parseValidNumber() {
            assertThat(StringParser.parseToInteger("123", ErrorMessage.INVALID_INPUT))
                    .isEqualTo(123);
        }

        @Test
        @DisplayName("앞뒤 공백이 있는 숫자 문자열을 정수로 변환한다")
        void parseNumberWithWhitespace() {
            assertThat(StringParser.parseToInteger(" 123 ", ErrorMessage.INVALID_INPUT)).isEqualTo(123);
        }

        @ParameterizedTest
        @ValueSource(strings = {"abc", "12.34", "123a", ""})
        @DisplayName("숫자가 아닌 문자열은 예외가 발생한다")
        void parseInvalidNumber(String input) {
            assertIllegalArgument(() -> StringParser.parseToInteger(input, ErrorMessage.INVALID_INPUT),
                    ErrorMessage.INVALID_INPUT);
        }
    }

    @Nested
    @DisplayName("문자열을 구분자로 분리하는 기능")
    class ParseByDelimiter {

        @Test
        @DisplayName("쉼표로 구분된 문자열을 분리한다")
        void parseCommaDelimitedString() {
            String input = "apple,banana,orange";
            List<String> result = StringParser.parseByDelimiter(input, ",");
            assertThat(result).containsExactly("apple", "banana", "orange");
        }

        @Test
        @DisplayName("공백이 포함된 문자열을 분리하고 trim 처리한다")
        void parseAndTrimString() {
            String input = "apple , banana , orange";
            List<String> result = StringParser.parseByDelimiter(input, ",");
            assertThat(result).containsExactly("apple", "banana", "orange");
        }

        @Test
        @DisplayName("빈 값을 포함한 문자열을 분리한다")
        void parseWithEmptyValues() {
            String input = "apple,,orange";
            List<String> result = StringParser.parseByDelimiter(input, ",");
            assertThat(result).containsExactly("apple", "", "orange");
        }

        @Test
        @DisplayName("구분자가 없는 문자열은 단일 요소 리스트를 반환한다")
        void parseWithNoDelimiter() {
            String input = "apple";
            List<String> result = StringParser.parseByDelimiter(input, ",");
            assertThat(result).containsExactly("apple");
        }
    }
}
