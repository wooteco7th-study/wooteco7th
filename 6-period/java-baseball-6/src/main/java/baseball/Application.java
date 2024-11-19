package baseball;

import baseball.domain.BaseballNumbers;
import java.util.Set;

public class Application {
    public static void main(String[] args) {
//        ### ✅ 랜덤 숫자 생성하기
//        1에서 9까지 **서로 다른 임의의 수 3개**를 선택한다.
        BaseballNumbers numbers = makeNumbers();
    }

    private static BaseballNumbers makeNumbers() {
        Set<Integer> pickNumbers = RandomGenerator.makeRandomNumbers();
        return new BaseballNumbers(pickNumbers);
    }
}
