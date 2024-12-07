package bridge.domain;

public enum GameResult {
    SUCCESS("성공"),
    FAIL("실패"),
    ;

    private final String description;

    GameResult(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
