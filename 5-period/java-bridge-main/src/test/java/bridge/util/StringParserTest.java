package bridge.util;

import static bridge.support.CustomAssertions.assertIllegalArgument;
import static org.assertj.core.api.Assertions.assertThat;

import bridge.exception.ErrorMessage;
import java.util.List;
import java.util.regex.Pattern;
import org.assertj.core.api.Assertions;
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
            Assertions.assertThat(StringParser.parseToInteger("123", ErrorMessage.INVALID_RESTART_COMMAND))
                    .isEqualTo(123);
        }

        @Test
        @DisplayName("앞뒤 공백이 있는 숫자 문자열을 정수로 변환한다")
        void parseNumberWithWhitespace() {
            assertThat(StringParser.parseToInteger(" 123 ", ErrorMessage.INVALID_RESTART_COMMAND)).isEqualTo(123);
        }

        @ParameterizedTest
        @ValueSource(strings = {"abc", "12.34", "123a", ""})
        @DisplayName("숫자가 아닌 문자열은 예외가 발생한다")
        void parseInvalidNumber(String input) {
            assertIllegalArgument(() -> StringParser.parseToInteger(input, ErrorMessage.INVALID_RESTART_COMMAND),
                    ErrorMessage.INVALID_RESTART_COMMAND);
        }
    }

    @DisplayName("문자열에서 특정 패턴을 제거한다")
    @Test
    void removePattern() {
        // given
        String text = "Hello123World456";
        String regex = "\\d+"; // 숫자 패턴

        // when
        String result = StringParser.removePattern(text, regex);

        // then
        assertThat(result).isEqualTo("HelloWorld");
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

    @Test
    @DisplayName("정규식 패턴으로 문자열에서 그룹을 추출할 수 있다")
    void extractByGroup() {
        // given
        String input = "티본스테이크-1";
        Pattern pattern = Pattern.compile("^([가-힣]+)-(\\d+)$");

        // when
        List<String> result = StringParser.findMatchingGroups(input, pattern);

        // then
        assertThat(result).containsExactly("티본스테이크", "1");
    }

    @Test
    @DisplayName("정규식 패턴으로 여러 매칭 그룹을 추출할 수 있다")
    void extractMultipleGroups() {
        // given
        String input = "이름: 홍길동, 나이: 20\n이름: 김철수, 나이: 25";
        Pattern pattern = Pattern.compile("이름: (.*), 나이: (\\d+)");

        // when
        List<String> result = StringParser.findMatchingGroups(input, pattern);

        // then
        assertThat(result).containsExactly("홍길동", "20", "김철수", "25");
    }

    @Test
    @DisplayName("패턴이 매칭되지 않으면 빈 리스트를 반환한다")
    void returnEmptyListWhenNoMatch() {
        // given
        String input = "잘못된 형식의 문자열";
        Pattern pattern = Pattern.compile("이름: (.*), 나이: (\\d+)");

        // when
        List<String> result = StringParser.findMatchingGroups(input, pattern);

        // then
        assertThat(result).isEmpty();
    }
}
