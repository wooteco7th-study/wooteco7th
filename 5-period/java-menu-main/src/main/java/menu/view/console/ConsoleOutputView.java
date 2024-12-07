package menu.view.console;

import java.util.List;
import java.util.stream.Collectors;
import menu.dto.MenuResult;
import menu.view.OutputView;

public class ConsoleOutputView implements OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String INTRO = "점심 메뉴 추천을 시작합니다.";
    private static final String ASK_COACHES_NAME = "코치의 이름을 입력해 주세요. (, 로 구분)";
    private static final String ASK_HATE_MENU = "%s(이)가 못 먹는 메뉴를 입력해 주세요.";
    private static final String RESULT_HEADER = "메뉴 추천 결과입니다.";
    private static final String DAY_FORMAT = "[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]";
    private static final String MENU_TYPE_FORMAT = "[ 카테고리 | %s ]";
    private static final String MENU_FORMAT = "[ %s | %s ]";
    private static final String DELIMITER = " | ";
    private static final String CLEAR = "추천을 완료했습니다.";

    @Override
    public void printIntro() {
        printlnMessage(INTRO);
    }

    @Override
    public void printAskCoachesName() {
        printlnMessage(LINE_SEPARATOR + ASK_COACHES_NAME);
    }

    @Override
    public void printAskHateMenu(final String name) {
        printlnMessage(LINE_SEPARATOR + String.format(ASK_HATE_MENU, name));
    }

    @Override
    public void printResult(final List<String> menuTypes, final List<MenuResult> menuResults) {
        final String menuTypesMessage = String.format(MENU_TYPE_FORMAT, String.join(DELIMITER, menuTypes));
        final String menuResultMessage = menuResults.stream()
                .map(menuResult -> String.format(MENU_FORMAT, menuResult.coachName(),
                        String.join(DELIMITER, menuResult.menus())))
                .collect(Collectors.joining(LINE_SEPARATOR));
        printlnMessage(LINE_SEPARATOR + RESULT_HEADER + LINE_SEPARATOR + DAY_FORMAT + LINE_SEPARATOR + menuTypesMessage
                + LINE_SEPARATOR + menuResultMessage);
    }

    @Override
    public void printClear() {
        printlnMessage(LINE_SEPARATOR + CLEAR);
    }

    private void printlnMessage(final String message) {
        System.out.println(message);
    }

    private void printMessage(final String message) {
        System.out.print(message);
    }
}
