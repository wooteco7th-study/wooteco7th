package baseball;

import static baseball.InputView.*;
import static baseball.InputView.YESorNO.GAME_RESTART;
import static baseball.OutputView.*;
import static baseball.rule.BaseballRule.MAX_NUMBER;
import static baseball.rule.BaseballRule.MIN_NUMBER;

import baseball.domain.BaseballGame;
import baseball.domain.Computer;
import baseball.domain.User;
import baseball.infrastructure.BaseBallNumberGenerator;

public class GameController {
    private final BaseBallNumberGenerator numberGenerator;

    public GameController() {
        this.numberGenerator = new BaseBallNumberGenerator(
                MIN_NUMBER.getValue(),
                MAX_NUMBER.getValue()
        );
    }

    public void run(){
        boolean isGameRunning = true;

        while (isGameRunning) {
            // 3스트라이크 이전
            playOneGame();
            // 3스트라이크 이후 Y/N 물어봄
            isGameRunning = requestGameContinue();
        }

    }
    private void playOneGame() {
        // 콘솔 야구 번호 입력 받기
        printStartMessage();
        Computer computer = initializeComputerNums();
        // 게임시작
        playUntilGameOver(computer);
    }

    private Computer initializeComputerNums() {
        Computer computer = new Computer(numberGenerator.pickUniqueThreeNumbers());
        return computer;
    }

    private static void playUntilGameOver(final Computer computer) {
        boolean isThreeStrike = false;

        while (!isThreeStrike) {
            printRequestNumberMessage();
            User user = createUserNums();

            // when
            BaseballGame baseballGame = BaseballGame.of(computer, user);
            printResultStatus(baseballGame);

            // then
            isThreeStrike = isThreeStrike(isThreeStrike, baseballGame);
        }
    }

    private static boolean isThreeStrike(boolean isThreeStrike, final BaseballGame baseballGame) {
        if (baseballGame.isAllStrike()) {
            printEndingMessage();
            isThreeStrike = true;
        }
        return isThreeStrike;
    }

    private static User createUserNums() {
        User user = new User(requestUserNumber());
        return user;
    }

    private boolean requestGameContinue() {
        printReplayMessage();
        YESorNO choice = requestRePlayChoice();
        return choice == GAME_RESTART;
    }
}
