package bridge.domain;

public enum GameResult {

    CLEAR("성공"),
    FAIL("실패"),
    ;

    private final String expression;

    GameResult(final String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}
