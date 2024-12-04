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

    private static final String PATH_MSG = """
            
            ## 경로 기준
            1. 최단 거리
            2. 최소 시간
            B. 돌아가기
            
            ## 원하는 기능을 선택하세요.""";


    private static final String NEW_LINE = System.lineSeparator();
    private static final String START_STATION_MSG = NEW_LINE + "## 출발역을 입력하세요.";
    private static final String END_STATION_MSG = NEW_LINE + "## 도착역을 입력하세요.";


    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public String readFirstGameCommand() {
        return getValidatedInput(INTRO_MSG);
    }

    public String readPathCommand() {
        return getValidatedInput(PATH_MSG);
    }

    public String readStartStation() {
        return getValidatedInput(START_STATION_MSG);
    }

    public String readEndStation() {
        return getValidatedInput(END_STATION_MSG);
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
