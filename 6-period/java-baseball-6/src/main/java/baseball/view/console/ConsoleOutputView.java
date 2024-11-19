package baseball.view.console;

import baseball.view.OutputView;

public class ConsoleOutputView implements OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String INTRO = "숫자 야구 게임을 시작합니다.";
    private static final String ASK_NUMBERS = "숫자를 입력해주세요 : ";
    private static final String BALL = "%d볼";
    private static final String STRIKE = "%d스트라이크";
    private static final String BALL_AND_STRIKE = "%d볼 %d스트라이크";
    private static final String NOTHING = "낫싱";
    private static final String GAME_CLEAR = "3개의 숫자를 모두 맞히셨습니다! 게임 종료";
    private static final String ASK_GAME_COMMAND = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";


    @Override
    public void printIntro() {
        printlnMessage(INTRO);
    }

    @Override
    public void printAskNumbers() {
        printMessage(ASK_NUMBERS);
    }

    @Override
    public void printGameClear() {
        printlnMessage(GAME_CLEAR);
    }

    @Override
    public void printAskGameCommand() {
        printlnMessage(ASK_GAME_COMMAND);
    }

    @Override
    public void printBallCount(final int ballCount) {
        printlnMessage(String.format(BALL, ballCount));
    }

    @Override
    public void printStrikeCount(final int strikeCount) {
        printlnMessage(String.format(STRIKE, strikeCount));
    }

    @Override
    public void printBallAndStrikeCount(final int ballCount, final int strikeCount) {
        printlnMessage(String.format(BALL_AND_STRIKE, ballCount, strikeCount));
    }

    @Override
    public void printNothing() {
        printlnMessage(NOTHING);
    }

    private void printMessage(final String message) {
        System.out.print(message);

    }

    private void printlnMessage(final String message) {
        System.out.println(message);
    }
}
