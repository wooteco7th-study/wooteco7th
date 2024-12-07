package pairmatching;

import static camp.nextstep.edu.missionutils.test.Assertions.assertShuffleTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {

    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 짝수_인원_페어_매칭() {
        assertShuffleTest(
                () -> {
                    run("1", "백엔드, 레벨1, 자동차경주", "Q");
                    assertThat(output()).contains("태웅 : 백호", "치수 : 태섭");
                },
                Arrays.asList("태웅", "백호", "치수", "태섭")
        );
    }

    @Test
    void 없는_미션에_대한_예외_처리() {
        assertSimpleTest(
                () -> {
                    runException("1", "백엔드, 레벨1, 오징어게임");
                    assertThat(output()).contains(ERROR_MESSAGE);
                }
        );
    }

    @Test
    @DisplayName("3회 초과 예외 테스트")
    void applicationTest1() throws Exception {
        assertShuffleTest(
                () -> {
                    run("1", "백엔드, 레벨1, 자동차경주", "1", "백엔드, 레벨1, 로또", "Q");
                    assertThat(output()).contains("태웅 : 백호", "치수 : 태섭");
                    assertThat(output()).contains(ERROR_MESSAGE);
                },
                Arrays.asList("태웅", "백호", "치수", "태섭")
        );
    }

    @Test
    @DisplayName("홀수 인원 매칭 테스트")
    void applicationTest2() throws Exception {
        assertShuffleTest(
                () -> {
                    run("1", "백엔드, 레벨1, 자동차경주", "Q");
                    assertThat(output()).contains("태웅 : 백호", "치수 : 태섭 : 형균");
                },
                Arrays.asList("태웅", "백호", "치수", "태섭", "형균")
        );
    }

    @Test
    @DisplayName("이미 존재하는 페어 매칭 테스트")
    void applicationTest3() throws Exception {
        assertShuffleTest(
                () -> {
                    run("1", "백엔드, 레벨1, 자동차경주", "1", "백엔드, 레벨1, 자동차경주", "네", "Q");
                    assertThat(output()).contains("태웅 : 백호", "치수 : 태섭 : 형균");
                    assertThat(output()).contains("매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n"
                            + "네 | 아니오");
                    assertThat(output()).contains("치수 : 형균 : 백호");

                },
                Arrays.asList("태웅", "백호", "치수", "태섭", "형균"),
                Arrays.asList("치수", "형균", "백호")
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
