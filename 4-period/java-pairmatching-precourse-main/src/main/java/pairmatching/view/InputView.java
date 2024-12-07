package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.util.StringParser;

import java.util.List;

import static pairmatching.exception.ExceptionMessage.INPUT_BLANK;

public class InputView {

    private static final String CHOOSE_OPTION_MSG = """
            
            기능을 선택하세요.
            1. 페어 매칭
            2. 페어 조회
            3. 페어 초기화
            Q. 종료""";

    private static final String CHOOSE_INFO_MSG = """
            
            #############################################
            과정: 백엔드 | 프론트엔드
            미션:
              - 레벨1: 자동차경주 | 로또 | 숫자야구게임
              - 레벨2: 장바구니 | 결제 | 지하철노선도
              - 레벨3:\s
              - 레벨4: 성능개선 | 배포
              - 레벨5:\s
            ############################################
            과정, 레벨, 미션을 선택하세요.
            ex) 백엔드, 레벨1, 자동차경주""";

    private static final String CHOOSE_REMATCHING_MSG = """
            
            매칭 정보가 있습니다. 다시 매칭하시겠습니까?
            네 | 아니오
            """;
    private static final String DELIMITER = ", ";


    public String readOption() {
        return getValidatedInput(CHOOSE_OPTION_MSG);
    }

    public List<String> readInfo() {
        return StringParser.parseWithDelimiter(getValidatedInput(CHOOSE_INFO_MSG), DELIMITER);
    }

    public String readRematching() {
        return getValidatedInput(CHOOSE_REMATCHING_MSG);
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
