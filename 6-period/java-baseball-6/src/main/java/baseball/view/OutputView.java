package baseball.view;

import baseball.dto.ResultResponse;

public class OutputView {

    private static final String OUTPUT_GAME_START_MSG = "숫자 야구 게임을 시작합니다.";
    private static final String OUTPUT_GAME_END_MSG = "3개의 숫자를 모두 맞히셨습니다! 게임 종료";
    private static final String NEW_LINE = System.lineSeparator();

    public void printGameStartMsg() {
        System.out.println(OUTPUT_GAME_START_MSG);
    }

    public void printResult(ResultResponse resultResponse) {
        if (resultResponse.ballCount() == 0 && resultResponse.strikeCount() == 0) {
            System.out.println("낫싱");
        }
        if (resultResponse.ballCount() > 0 && resultResponse.strikeCount() == 0) {
            System.out.printf("%d볼" + NEW_LINE, resultResponse.ballCount());
        }
        if (resultResponse.ballCount() == 0 && resultResponse.strikeCount() > 0) {
            System.out.printf("%d스트라이크" + NEW_LINE, resultResponse.strikeCount());
        }
        if (resultResponse.ballCount() > 0 && resultResponse.strikeCount() > 0) {
            System.out.printf("%d볼 %d스트라이크" + NEW_LINE, resultResponse.ballCount(), resultResponse.strikeCount());
        }
    }

    public void printEndMsg() {
        System.out.println(OUTPUT_GAME_END_MSG);
    }
}
