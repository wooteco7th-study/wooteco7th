package baseball;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {

    @Test
    void 기본_게임_진행_테스트() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("123", "956", "789", "147", "2");
                    assertThat(output()).contains("1볼", "낫싱", "1볼", "3스트라이크", "게임 종료");
                },
                1, 4, 7
        );
    }

    @Test
    void 게임종료_후_재시작() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("246", "135", "1", "597", "589", "2");
                    assertThat(output()).contains("낫싱", "3스트라이크", "1볼 1스트라이크", "3스트라이크", "게임 종료");
                },
                1, 3, 5, 5, 8, 9
        );
    }

    @Test
    void 숫자_범위_초과_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("012"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("1부터 9까지의 숫자만 입력할 수 있습니다.")
        );
    }

    @Test
    void 중복_숫자_입력_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("112"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("3자리 숫자만 입력할 수 있습니다.")
        );
    }

    @Test
    void 잘못된_길이_입력_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1234"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("3자리 숫자만 입력할 수 있습니다.")
        );
    }

    @Test
    void 문자_입력_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("abc"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("숫자만 입력할 수 있습니다.")
        );
    }

    @Test
    void 재시작_입력_예외() {
        assertRandomNumberInRangeTest(
                () -> {
                    assertThatThrownBy(() -> runException("147", "3"))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessageContaining("1 또는 2만 입력할 수 있습니다.");
                },
                1, 4, 7
        );
    }

    @Test
    void 모든_경우의_수_출력_테스트() {
        assertRandomNumberInRangeTest(
                () -> {
                    run(
                            "235",
                            "164",
                            "174",
                            "417",
                            "714",
                            "2"
                    );
                    assertThat(output()).contains(
                            "낫싱",
                            "1볼 1스트라이크",
                            "2볼 1스트라이크",
                            "2볼 1스트라이크",
                            "3스트라이크",
                            "게임 종료"
                    );
                },
                7, 1, 4  // 컴퓨터의 숫자: 714
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
