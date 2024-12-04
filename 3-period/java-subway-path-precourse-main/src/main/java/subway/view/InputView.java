package subway.view;

import java.util.Scanner;

import static subway.exception.ExceptionMessage.INPUT_BLANK;

public class InputView {
    private final Scanner scanner;

    private static final String INTRO_MSG = """
            ## 메인 화면
            1. 경로 조회
            Q. 종료
            
            ## 원하는 기능을 선택하세요.""";

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public String readFirstGameCommand() {
        return getValidatedInput(INTRO_MSG);
    }

    private String getValidatedInput(String message) {
        System.out.println(message);
        String input = scanner.nextLine().strip();
        validateInput(input);
        return input;
    }

    private void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(INPUT_BLANK.getMessage());
        }
    }
}
