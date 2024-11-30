package bridge.view;

import bridge.domain.BridgeGame;
import bridge.domain.CurrentMap;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String DELIMITER = " | ";
    private static final String MAP_FORMAT = "[ %s ]" + NEW_LINE;
    private static final String RESULT_MSG = NEW_LINE + "최종 게임 결과";
    private static final String STATISTICS_MSG = """
            
            게임 성공 여부: %s
            총 시도한 횟수: %d""";

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(final CurrentMap currentMap) {
        getMap(currentMap);
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(final BridgeGame bridgeGame, final CurrentMap currentMap) {
        System.out.println(RESULT_MSG);
        getMap(currentMap);
        printStatistics(bridgeGame);
    }

    public void printError(String error) {
        System.out.println(error);
    }

    private void getMap(final CurrentMap currentMap) {
        System.out.printf(MAP_FORMAT, String.join(DELIMITER, currentMap.getUpMap()));
        System.out.printf(MAP_FORMAT, String.join(DELIMITER, currentMap.getDownMap()));
    }

    private static void printStatistics(final BridgeGame bridgeGame) {
        if (bridgeGame.isSuccess()) {
            System.out.printf(STATISTICS_MSG, "성공", bridgeGame.getTotalTrialCount());
        }
        if (!bridgeGame.isSuccess()) {
            System.out.printf(STATISTICS_MSG, "실패", bridgeGame.getTotalTrialCount());
        }
    }
}
