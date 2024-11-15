package racingcar.game;

import static camp.nextstep.edu.missionutils.Console.readLine;

import java.util.Arrays;
import java.util.List;

public class InputView {

    public List<String> requestCarNames(){
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        return Arrays.stream(readLine().trim().split(",")).toList();
    }
    public int requestRoundCount(){
        System.out.println("시도할 횟수는 몇회 인가요?");
        return Integer.parseInt(readLine().trim());
    }
    public void messageInitiateGameResult(){
        System.out.println("실행 결과");
    }

}
