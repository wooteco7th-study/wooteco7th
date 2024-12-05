package subway.command;

public enum ProcessingState {

    START_FROM_BEGINNING(false),
    CONTINUE_IN_THE_MIDDLE(true);

    private final boolean shouldContinue;

    ProcessingState(final boolean shouldContinue) {
        this.shouldContinue = shouldContinue;
    }

    public boolean shouldContinue() {
        return shouldContinue;
    }
}
