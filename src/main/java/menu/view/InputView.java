package menu.view;

import camp.nextstep.edu.missionutils.Console;
import menu.util.StringParser;

import java.util.List;

import static menu.exception.ExceptionMessage.INPUT_BLANK;

public class InputView {
    private static final String DELIMITER = ",";

    private static final String NEW_LINE = System.lineSeparator();
    private static final String INTRO_MSG = NEW_LINE + "점심 메뉴 추천을 시작합니다.";
    private static final String INPUT_COACH_NAME_MSG = NEW_LINE + "코치의 이름을 입력해 주세요. (, 로 구분)";

    private static final String INPUT_COACH_CANT_EAT_MENU_MSG = "%s(이)가 못 먹는 메뉴를 입력해 주세요." + NEW_LINE;

    public List<String> readCoachNames() {
        String input = getValidatedInput();
        return StringParser.parseWithDelimiter(input, DELIMITER);
    }

    public List<String> readCoachCantEatMenu(final String name) {
        System.out.printf(INPUT_COACH_CANT_EAT_MENU_MSG, name);
        String input = getInput();
        return StringParser.parseWithDelimiter(input, DELIMITER);
    }

    private String getValidatedInput() {
        System.out.println(INTRO_MSG);
        System.out.println(INPUT_COACH_NAME_MSG);
        String input = Console.readLine().strip();
        validateInput(input);
        return input;
    }

    private void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(INPUT_BLANK.getMessage());
        }
    }

    private String getInput() {
        return Console.readLine().strip();
    }
}
