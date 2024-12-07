package menu.view;

import java.util.List;
import java.util.stream.Collectors;
import menu.dto.ResultDto;

public class OutputView {

    private static final String LINE = System.lineSeparator();
    private static final String TITLE_WELCOME = """
            점심 메뉴 추천을 시작합니다.

            코치의 이름을 입력해 주세요. (, 로 구분)""";
    private static final String REQUEST_MENU = "%s(이)가 못 먹는 메뉴를 입력해 주세요.";
    private static final String TITLE_RESULT = """
            메뉴 추천 결과입니다.
            [ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]
            [ 카테고리 | 한식 | 한식 | 일식 | 중식 | 아시안 ]""";
    private static final String TITLE_END_RESULT = "추천을 완료했습니다.";
    private static final String DELIMITER = " | ";
    private static final String PREFIX = "[ ";
    private static final String SUFFIX = " ]";

    // TITLE_ : 제목
    // REQUEST_ : 요청메세지
    // show : 출력 형식

    public void showTitleWelcome() {
        showln(TITLE_WELCOME);
    }

    public void showRequestMenu(String name) {
        showln(LINE + format(REQUEST_MENU, name));
    }

    public void showTitleResult(final ResultDto resultDto) {
        showln(LINE + TITLE_RESULT);
        resultDto.menus().stream()
                .map(this::makeMessage)
                .forEach(this::showln);
    }

    private String makeMessage(final List<String> menu) {
        return menu.stream()
                .collect(Collectors.joining(DELIMITER, PREFIX, SUFFIX));
    }

    public void showTitleEndResult() {
        showln(LINE + TITLE_END_RESULT);
    }

    public void showException(Exception exception) {
        showln(exception.getMessage());
    }

    private String format(String format, Object... args) {
        return String.format(format, args);
    }

    private void showln(String message) {
        System.out.println(message);
    }
}
