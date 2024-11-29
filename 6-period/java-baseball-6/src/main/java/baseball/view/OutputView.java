package baseball.view;

public interface OutputView {

    void printIntro();

    void printAskNumbers();

    void printGameClear();

    void printAskGameCommand();

    void printBallCount(final int ballCount);

    void printStrikeCount(final int strikeCount);

    void printBallAndStrikeCount(final int ballCount, final int strikeCount);

    void printNothing();
}
