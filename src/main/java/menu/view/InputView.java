package menu.view;

import camp.nextstep.edu.missionutils.Console;
import menu.util.StringParser;

import java.util.List;

import static menu.exception.ExceptionMessage.INPUT_BLANK;

public class InputView {
    private static final String NAME_DELIMITER = ",";

    private static final String INPUT_COACH_NAME_MSG = """
            점심 메뉴 추천을 시작합니다.
            
            코치의 이름을 입력해 주세요. (, 로 구분)""";

    private static final String INPUT_COACH_CANT_EAT_MENU_MSG = "%s(이)가 못 먹는 메뉴를 입력해 주세요.";

    public List<String> readCoachNames() {
        String input = getValidatedInput(INPUT_COACH_NAME_MSG);
        return StringParser.parseWithDelimiter(input, NAME_DELIMITER);
    }

    private String getValidatedInput(String message) {
        System.out.println(message);
        String input = Console.readLine().strip();
        validateInput(input);
        return input;
    }

    private void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(INPUT_BLANK.getMessage());
        }
    }
}
