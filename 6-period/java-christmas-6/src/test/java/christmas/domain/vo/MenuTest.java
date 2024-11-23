package christmas.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {

    @DisplayName("없는 메뉴일 때 예외 반환")
    @Test
    void 없는_메뉴일때_예외_반환() {
        // given
        String menuName = "없는메뉴";

        // expect
        Assertions.assertThatThrownBy(() -> Menu.of(menuName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Nested
    @DisplayName("에피타이저 메뉴 생성")
    class 애피타이저_메뉴_생성 {

        @DisplayName("양송이 수프 정상 생성")
        @Test
        void 양송이_수프_정상_생성() {
            // given
            String menuName = "양송이수프";

            // when
            Menu menu = Menu.of(menuName);

            // then
            assertAll(
                    () -> assertThat(menu).isEqualTo(Menu.양송이수프),
                    () -> assertThat(menu.getMenuType()).isEqualTo(MenuType.APPETIZER)
            );
        }

        @DisplayName("에피타이저 메뉴 정상 생성")
        @ParameterizedTest
        @ValueSource(strings = {"양송이수프", "타파스", "시저샐러드"})
        void 에피타이저_메뉴_정상_생성(String menuName) {

            // when
            Menu menu = Menu.of(menuName);

            // then
            assertThat(menu.getMenuType()).isEqualTo(MenuType.APPETIZER);
        }

    }

    @Nested
    @DisplayName("메인 메뉴 생성")
    class 메인_메뉴_생성 {

        @DisplayName("티본스테이크 정상 생성")
        @Test
        void 티본스테이크_정상_생성() {
            // given
            String menuName = "티본스테이크";

            // when
            Menu menu = Menu.of(menuName);

            // then
            assertAll(
                    () -> assertThat(menu).isEqualTo(Menu.티본스테이크),
                    () -> assertThat(menu.getMenuType()).isEqualTo(MenuType.MAIN)
            );
        }

        @DisplayName("메인 메뉴 정상 생성")
        @ParameterizedTest
        @ValueSource(strings = {"티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타"})
        void 메인_메뉴_정상_생성(String menuName) {

            // when
            Menu menu = Menu.of(menuName);

            // then
            assertThat(menu.getMenuType()).isEqualTo(MenuType.MAIN);
        }

    }

    @Nested
    @DisplayName("디저트 메뉴 생성")
    class 디저트_메뉴_생성 {

        @DisplayName("초코케이크 정상 생성")
        @Test
        void 초코케이크_정상_생성() {
            // given
            String menuName = "초코케이크";

            // when
            Menu menu = Menu.of(menuName);

            // then
            assertAll(
                    () -> assertThat(menu).isEqualTo(Menu.초코케이크),
                    () -> assertThat(menu.getMenuType()).isEqualTo(MenuType.DESSERT)
            );
        }

        @DisplayName("디저트 메뉴 정상 생성")
        @ParameterizedTest
        @ValueSource(strings = {"초코케이크", "아이스크림"})
        void 디저트_메뉴_정상_생성(String menuName) {

            // when
            Menu menu = Menu.of(menuName);

            // then
            assertThat(menu.getMenuType()).isEqualTo(MenuType.DESSERT);
        }

    }

    @Nested
    @DisplayName("드링크 메뉴 생성")
    class 드링크_메뉴_생성 {

        @DisplayName("제로콜라 정상 생성")
        @Test
        void 제로콜라_정상_생성() {
            // given
            String menuName = "제로콜라";

            // when
            Menu menu = Menu.of(menuName);

            // then
            assertAll(
                    () -> assertThat(menu).isEqualTo(Menu.제로콜라),
                    () -> assertThat(menu.getMenuType()).isEqualTo(MenuType.DRINK)
            );
        }

        @DisplayName("드링크 메뉴 정상 생성")
        @ParameterizedTest
        @ValueSource(strings = {"제로콜라", "레드와인", "샴페인"})
        void 드링크_메뉴_정상_생성(String menuName) {

            // when
            Menu menu = Menu.of(menuName);

            // then
            assertThat(menu.getMenuType()).isEqualTo(MenuType.DRINK);
        }

    }


}
