package baseball.domain;

public class Reset {

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
        return !(reset.equals("1") || reset.equals("2"));
    }

    public boolean resetGame() {
        return reset.equals("1");
    }
}
