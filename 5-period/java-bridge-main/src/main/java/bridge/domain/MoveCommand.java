package bridge.domain;

public enum MoveCommand {

    UP("U"),
    DOWN("D"),
    ;

    private final String expression;

    MoveCommand(final String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}
