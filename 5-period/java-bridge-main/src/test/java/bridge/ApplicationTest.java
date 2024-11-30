package bridge;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;

import bridge.domain.bridge.BridgeMaker;
import bridge.domain.generator.BridgeNumberGenerator;
import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {

    private static final String ERROR_MESSAGE = "[ERROR]";
    private static final String RETRY_MESSAGE = "게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)";
    private static final String MOVE_MESSAGE = "이동할 칸을 선택해주세요. (위: U, 아래: D)";

    @Test
    void 다리_생성_테스트() {
        BridgeNumberGenerator numberGenerator = new TestNumberGenerator(newArrayList(1, 0, 0));
        BridgeMaker bridgeMaker = new BridgeMaker(numberGenerator);
        List<String> bridge = bridgeMaker.makeBridge(3);
        assertThat(bridge).containsExactly("U", "D", "D");
    }

    @Test
    void 기능_테스트() {
        assertRandomNumberInRangeTest(() -> {
            run("3", "U", "D", "U");
            assertThat(output()).contains(
                    "최종 게임 결과",
                    "[ O |   | O ]",
                    "[   | O |   ]",
                    "게임 성공 여부: 성공",
                    "총 시도한 횟수: 1"
            );

            int upSideIndex = output().indexOf("[ O |   | O ]");
            int downSideIndex = output().indexOf("[   | O |   ]");
            assertThat(upSideIndex).isLessThan(downSideIndex);
        }, 1, 0, 1);
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() -> {
            runException("a");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 게임_재시도_후_성공_테스트() {
        assertRandomNumberInRangeTest(() -> {
            run("3", "U", "U", "R", "U", "D", "D");
            assertThat(output()).contains(
                    "최종 게임 결과",
                    "[ O |   |   ]",
                    "[   | O | O ]",
                    "게임 성공 여부: 성공",
                    "총 시도한 횟수: 2"
            );
        }, 1, 0, 0);
    }

    @Test
    void 게임_실패_후_종료_테스트() {
        assertRandomNumberInRangeTest(() -> {
            run("3", "U", "D", "D", "Q");
            assertThat(output()).contains(
                    "최종 게임 결과",
                    "[ O |   ]",
                    "[   | X ]",
                    "게임 성공 여부: 실패",
                    "총 시도한 횟수: 1"
            );
        }, 1, 1, 0);
    }

    @Test
    void 다리_길이_예외_테스트() {
        assertSimpleTest(() -> {
            runException("2");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 이동_입력_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "X");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 재시도_커맨드_예외_테스트() {
        assertRandomNumberInRangeTest(() -> {
            runException("3", "U", "D", "X");
            assertThat(output()).contains(ERROR_MESSAGE);
        }, 1, 0);
    }

    @Test
    void 최대_다리_길이_성공_테스트() {
        assertRandomNumberInRangeTest(() -> {
            String[] inputs = new String[21];  // 20(다리 길이) + 1(초기 입력)
            inputs[0] = "20";  // 다리 길이
            for (int i = 1; i <= 20; i++) {
                inputs[i] = "U";
            }
            run(inputs);
            assertThat(output()).contains(
                    "게임 성공 여부: 성공",
                    "총 시도한 횟수: 1"
            );
        }, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);  // 모두 위 칸으로 설정
    }

    @Test
    void 게임_성공시_재시도_메시지_없음_테스트() {
        assertRandomNumberInRangeTest(() -> {
            run("3", "U", "D", "D");
            assertThat(output())
                    .contains("게임 성공 여부: 성공")
                    .doesNotContain(RETRY_MESSAGE);
        }, 1, 0, 0);
    }

    @Test
    void 게임_실패시_재시도_메시지_출력_테스트() {
        assertRandomNumberInRangeTest(() -> {
            run("3", "U", "U", "Q");
            assertThat(output())
                    .contains(RETRY_MESSAGE)
                    .doesNotContain("게임 성공 여부: 성공");

            String outputString = output();
            int lastMovePrompt = outputString.lastIndexOf(MOVE_MESSAGE);
            int retryPrompt = outputString.lastIndexOf(RETRY_MESSAGE);

            assertThat(lastMovePrompt).isLessThan(retryPrompt);
        }, 1, 0, 0);
    }


    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }

    static class TestNumberGenerator implements BridgeNumberGenerator {

        private final List<Integer> numbers;

        TestNumberGenerator(List<Integer> numbers) {
            this.numbers = numbers;
        }

        @Override
        public int generate() {
            return numbers.remove(0);
        }
    }
}
