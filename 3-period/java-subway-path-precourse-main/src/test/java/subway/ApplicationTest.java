package subway;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest extends NsTest {

    private static final String ERROR_PREFIX = "[ERROR]";

    @Test
    void 최단_거리_경로_조회_성공() {
        assertSimpleTest(() -> {
            run("1", "1", "교대역", "양재역", "Q");
            assertThat(output()).contains(
                    "총 거리: 4km",
                    "총 소요 시간: 11분",
                    "교대역",
                    "강남역",
                    "양재역"
            );
        });
    }

    @Test
    void 최소_시간_경로_조회_성공() {
        assertSimpleTest(() -> {
            run("1", "2", "교대역", "양재역", "Q");
            assertThat(output()).contains(
                    "총 거리: 9km",
                    "총 소요 시간: 7분",
                    "교대역",
                    "남부터미널역",
                    "양재역"
            );
        });
    }

    @Test
    void 환승_경로_최단_거리_조회_성공() {
        assertSimpleTest(() -> {
            run("1", "1", "교대역", "양재시민의숲역", "Q");
            assertThat(output()).contains(
                    "총 거리: 14km",
                    "총 소요 시간: 14분",
                    "교대역",
                    "강남역",
                    "양재역",
                    "양재시민의숲역"
            );
        });
    }

    @Test
    void 환승_경로_최소_시간_조회_성공() {
        assertSimpleTest(() -> {
            run("1", "2", "교대역", "양재시민의숲역", "Q");
            assertThat(output()).contains(
                    "총 거리: 19km",
                    "총 소요 시간: 10분",
                    "교대역",
                    "남부터미널역",
                    "양재역",
                    "양재시민의숲역"
            );
        });
    }

    @Test
    void 출발역과_도착역_동일_오류() {
        assertSimpleTest(() -> {
            runException("1", "1", "강남역", "강남역");
            assertThat(output()).contains(
                    ERROR_PREFIX + " 출발역과 도착역이 동일합니다."
            );
        });
    }

    @Test
    void 존재하지_않는_역_입력_오류() {
        assertSimpleTest(() -> {
            runException("1", "1", "없는역", "강남역");
            assertThat(output()).contains(
                    ERROR_PREFIX
            );
        });
    }

    @Test
    void 잘못된_메인_메뉴_선택_오류() {
        assertSimpleTest(() -> {
            runException("3");
            assertThat(output()).contains(
                    ERROR_PREFIX
            );
        });
    }

    @Test
    void 잘못된_경로_기준_선택_오류() {
        assertSimpleTest(() -> {
            runException("1", "3");
            assertThat(output()).contains(
                    ERROR_PREFIX
            );
        });
    }

    @Test
    void 메인_메뉴로_돌아가기_성공() {
        assertSimpleTest(() -> {
            run("1", "B", "Q");
            assertThat(output()).contains(
                    "## 메인 화면",
                    "1. 경로 조회",
                    "Q. 종료"
            );
        });
    }

    @Test
    void 연결되지_않은_역_조회_오류_상행역으로_이동불가() {
        assertSimpleTest(() -> {
            runException("1", "1", "역삼역", "강남역");
            assertThat(output()).contains(
                    ERROR_PREFIX
            );
        });
    }

    @Test
    void 연결되지_않은_역_조회_오류_해당_노선으로_이동불가() {
        assertSimpleTest(() -> {
            runException("1", "1", "역삼역", "남부터미널역");
            assertThat(output()).contains(
                    ERROR_PREFIX
            );
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
