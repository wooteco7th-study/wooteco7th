package baseball.domain;

public class Reset {

    private static final String RESTART = "1";
    private static final String END = "2";

    private final String reset;

    public Reset(String reset) {
        validate(reset);
        this.reset = reset;
    }

    private void validate(String reset) {
        if (checkNotReset(reset)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean checkNotReset(String reset) {
        return !(reset.equals(RESTART) || reset.equals(END));
    }

    public boolean resetGame() {
        return reset.equals(RESTART);
    }
}
