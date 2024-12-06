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
    void 여러번_매칭시_마지막_매칭_결과만_출력() {
        assertShuffleTest(
                () -> {
                    runException("1",
                            "백엔드, 레벨1, 로또",
                            "1",
                            "백엔드, 레벨1, 로또",
                            "네",
                            "2",
                            "백엔드, 레벨1, 로또",
                            "Q"
                    );

                    String output = output();
                    int lastPairResult = output.lastIndexOf("페어 매칭 결과입니다.");
                    String lastOutput = output.substring(lastPairResult);

                    assertThat(lastOutput).contains("태웅 : 치수", "백호 : 태섭");
                },
                Arrays.asList("태웅", "백호", "치수", "태섭"),
                Arrays.asList("태웅", "치수", "백호", "태섭")
        );
    }

    @Test
    @DisplayName("3회 초과 예외 테스트")
    void applicationTest1() {
        assertShuffleTest(
                () -> {
                    runException("1", "백엔드, 레벨1, 자동차경주", "1", "백엔드, 레벨1, 로또");
                    assertThat(output()).contains("태웅 : 백호", "치수 : 태섭");
                    assertThat(output()).contains(ERROR_MESSAGE);
                },
                Arrays.asList("태웅", "백호", "치수", "태섭")
        );
    }

    @Test
    @DisplayName("홀수 인원 매칭 테스트")
    void applicationTest2() {
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
    void applicationTest3() {
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

    @Test
    void 매칭_재시도_거절시_새로운_과정_선택() {
        assertShuffleTest(
                () -> {
                    runException("1",
                            "백엔드, 레벨1, 자동차경주",
                            "1",
                            "백엔드, 레벨1, 자동차경주",
                            "아니오",
                            "Q"
                    );
                    String output = output();
                    int lastIndex = output.lastIndexOf("아니오");
                    String remainingOutput = output.substring(lastIndex);

                    assertThat(remainingOutput).contains("과정, 레벨, 미션을 선택하세요.")
                            .doesNotContain("과정: 백엔드 | 프론트엔드")
                            .doesNotContain("미션:")
                            .doesNotContain("레벨1: 자동차경주");
                },
                Arrays.asList("태웅", "백호", "치수", "태섭")
        );
    }

    @Test
    void 페어_초기화_확인() {
        assertShuffleTest(
                () -> {
                    runException("1",
                            "백엔드, 레벨1, 자동차경주",
                            "3",
                            "1",
                            "백엔드, 레벨1, 자동차경주",
                            "아니오",
                            "Q"
                    );
                    String output = output();
                    int lastIndex = output.lastIndexOf("초기화 되었습니다.");
                    String remainingOutput = output.substring(lastIndex);

                    assertThat(remainingOutput)
                            .contains("태웅 : 치수")
                            .contains("백호 : 태섭");
                },
                Arrays.asList("태웅", "백호", "치수", "태섭"),
                Arrays.asList("태웅", "치수", "백호", "태섭")
        );
    }

    @Test
    void 매칭_이력_없는_조회_시도() {
        assertSimpleTest(
                () -> {
                    runException("2",
                            "백엔드, 레벨1, 자동차경주",
                            "Q"
                    );
                    assertThat(output()).contains("[ERROR] 매칭 이력이 없습니다.");
                }
        );
    }

    @Test
    void 잘못된_과정명_입력() {
        assertSimpleTest(
                () -> {
                    runException("1", "안드로이드, 레벨1, 자동차경주");
                    assertThat(output()).contains(ERROR_MESSAGE);
                }
        );
    }

    @Test
    void 잘못된_레벨_입력() {
        assertSimpleTest(
                () -> {
                    runException("1", "백엔드, 레벨6, 자동차경주");
                    assertThat(output()).contains(ERROR_MESSAGE);
                }
        );
    }

    @Test
    void 잘못된_입력_형식() {
        assertSimpleTest(
                () -> {
                    runException("1", "백엔드 레벨1 자동차경주");
                    assertThat(output()).contains(ERROR_MESSAGE);
                }
        );
    }

    @Test
    void 같은_레벨_기존_페어_매칭_존재시_예외() {
        assertShuffleTest(
                () -> {
                    runException(
                            "1",
                            "백엔드, 레벨1, 자동차경주",
                            "1",
                            "백엔드, 레벨1, 로또",
                            "Q"
                    );
                },
                Arrays.asList("태웅", "치수", "백호", "태섭"),
                Arrays.asList("태웅", "치수", "백호", "태섭"), // 다시 매칭
                Arrays.asList("태웅", "백호", "치수", "태섭")
        );
    }

    @Test
    void 재입력안내() {
        assertShuffleTest(
                () -> {
                    runException(
                            "1",
                            "백엔드, 레벨1, 자동차경주",
                            "1",
                            "백엔드, 레벨1, 자동차경주",
                            "네",
                            "1",
                            "백엔드, 레벨1, 자동차경주",
                            "아니오",
                            "백엔드, 레벨2, 자동차경주",
                            "Q"
                    );
                    assertThat(output().split("페어 매칭 결과입니다.").length).isEqualTo(4);
                    assertThat(output().split("매칭 정보가 있습니다. 다시 매칭하시겠습니까?").length).isEqualTo(3);
                },
                Arrays.asList("태웅", "치수", "백호", "태섭"),
                Arrays.asList("태웅", "태섭", "백호", "치수")
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
