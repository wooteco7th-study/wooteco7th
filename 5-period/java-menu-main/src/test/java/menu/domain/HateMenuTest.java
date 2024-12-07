package menu.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import java.util.stream.Stream;
import menu.error.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class HateMenuTest {

    @ParameterizedTest
    @MethodSource("notExistMenu")
    @DisplayName("존재하지 않은 메뉴이므로 예외가 발생한다.")
    void validateExistTest(final List<String> menus) throws Exception {
        //should
        assertThatIllegalArgumentException().isThrownBy(() -> new HateMenu(menus))
                .withMessageContaining(ErrorMessage.INVALID_NOT_FOUND_MENU.getMessage());

    }

    private static Stream<List<String>> notExistMenu() {
        return Stream.of(
                List.of("수달", "우동"),
                List.of("우동", "빵"),
                List.of("수", "달")
        );
    }

    @Test
    @DisplayName("중복 메뉴이므로 예외가 발생한다.")
    void validateDuplicateTest() throws Exception {
        //given
        final List<String> menus = List.of("우동", "우동");
        //should
        assertThatIllegalArgumentException().isThrownBy(() -> new HateMenu(menus))
                .withMessageContaining(ErrorMessage.INVALID_DUPLICATED_HATE_MENU.getMessage());

    }

    @Test
    @DisplayName("메뉴가 2개를 초과하여 예외가 발생한다.")
    void validateSizeTest() throws Exception {
        //given
        final List<String> menus = List.of("우동", "뇨끼", "된장찌개");
        //should
        assertThatIllegalArgumentException().isThrownBy(() -> new HateMenu(menus))
                .withMessageContaining(ErrorMessage.INVALID_HATE_MENU.getMessage());
    }

    @Test
    @DisplayName("빈문자열은 못먹는 메뉴가 없는걸로 판단한다.")
    void validateAllEmptyTest() throws Exception {
        //given
        final List<String> menu = List.of("");
        //should
        assertThatCode(() -> {
            new HateMenu(menu);
        }).doesNotThrowAnyException();
    }
}
