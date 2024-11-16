package racingcar.game;

import static camp.nextstep.edu.missionutils.Console.readLine;

import java.util.Arrays;
import java.util.List;
import racingcar.util.validator.Validator;

public class InputView {

    public List<String> requestCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        List<String> cars = Arrays.stream(readLine().trim().split(",")).toList();
        Validator.check(!(cars.size() < 1 || cars.size() > 10))
                .withError(new IllegalArgumentException("[ERROR] 자동차는 1~10대만 가능합니다."))
                .validate();
        return cars;
    }

    public int requestRoundCount() {
        System.out.println("시도할 횟수는 몇회 인가요?");
        int count = Integer.parseInt(readLine().trim());
        Validator.check(!(count < 1 || count > 10))
                .withError(new IllegalArgumentException("[ERROR] 시도 횟수는 1~10만 가능합니다."))
                .validate();
        return count;
    }

    public void messageInitiateGameResult() {
        System.out.println("실행 결과");
    }

}
