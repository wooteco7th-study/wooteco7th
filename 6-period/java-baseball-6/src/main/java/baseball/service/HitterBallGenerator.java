package baseball.service;

import java.util.List;

public class HitterBallGenerator {

    private static final String BALL_SEPARATOR = "";

    private final List<Integer> hitterBalls;

    public HitterBallGenerator(String userInputs) {
        this.hitterBalls = generateHitterBall(userInputs);
    }

    private List<Integer> generateHitterBall(String userInputs) {
        return changeType(splitUserInput(userInputs.trim()));
    }

    private List<String> splitUserInput(String userInputs) {
        return List.of(userInputs.split(BALL_SEPARATOR));
    }

    private List<Integer> changeType(List<String> userInputs) {
        return userInputs.stream().map(this::change).toList();
    }

    private Integer change(String userInput) {
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public List<Integer> getHitterBalls() {
        return hitterBalls;
    }
}
