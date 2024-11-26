package vendingmachine;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInListTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 기능_테스트() {
        assertRandomNumberInListTest(
            () -> {
                run("450", "[콜라,1500,20];[사이다,1000,10]", "3000", "콜라", "사이다");
                assertThat(output()).contains(
                    "자판기가 보유한 동전", "500원 - 0개", "100원 - 4개", "50원 - 1개", "10원 - 0개",
                    "투입 금액: 3000원", "투입 금액: 1500원"
                );
            },
            100, 100, 100, 100, 50
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(
            () -> {
                runException("-1");
                assertThat(output()).contains(ERROR_MESSAGE);
            }
        );
    }

    @Test
    @DisplayName("금액이 숫자가 아니므로 예외가 발생한다.")
    void applicationTest3() throws Exception {
        assertSimpleTest(
                () -> {
                    runException("a");
                    assertThat(output()).contains(ERROR_MESSAGE);
                }
        );
    }

    @Test
    @DisplayName("투입 금액이 0원 이므로 애플리케이션 종료 된다.")
    void applicationTest1() throws Exception {
        assertRandomNumberInListTest(
                () -> {
                    run("450", "[콜라,1500,20];[사이다,1000,10]", "0");
                    assertThat(output()).contains(
                            "자판기가 보유한 동전", "500원 - 0개", "100원 - 4개", "50원 - 1개", "10원 - 0개",
                            "투입 금액: 0원", "잔돈"
                    );
                },
                100, 100, 100, 100, 50
        );

    }

    @Test
    @DisplayName("구입할 수 있는 상품이 없어 애플리케이션이 종료 된다.")
    void applicationTest2() throws Exception {
        assertRandomNumberInListTest(
                () -> {
                    run("450", "[콜라,1500,20];[사이다,1000,10]", "400");
                    assertThat(output()).contains(
                            "자판기가 보유한 동전", "500원 - 0개", "100원 - 4개", "50원 - 1개", "10원 - 0개",
                            "투입 금액: 400원", "잔돈", "100원 - 4개"
                    );
                },
                100, 100, 100, 100, 50
        );

    }

    @Test
    @DisplayName("랜덤 라이브러리 테스트")
    void randomLibraryTest() throws Exception {
        //given
        final List<Integer> numbers = Arrays.asList(500, 100, 50, 10);
        final int number = Randoms.pickNumberInList(numbers);

        //should
        System.out.println(number);

    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
