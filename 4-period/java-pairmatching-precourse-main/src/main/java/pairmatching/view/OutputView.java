package pairmatching.view;

import java.util.List;
import pairmatching.dto.PairMatchResultDto;

public class OutputView {

    private static final String LINE = System.lineSeparator();
    private static final String TITLE_FUNCTION = """
            기능을 선택하세요.
            1. 페어 매칭
            2. 페어 조회
            3. 페어 초기화
            Q. 종료""";
    private static final String TITLE_SELECT = """
            #############################################
            과정: 백엔드 | 프론트엔드
            미션:
              - 레벨1: 자동차경주 | 로또 | 숫자야구게임
              - 레벨2: 장바구니 | 결제 | 지하철노선도
              - 레벨3:
              - 레벨4: 성능개선 | 배포
              - 레벨5:
            ############################################
            과정, 레벨, 미션을 선택하세요.
            ex) 백엔드, 레벨1, 자동차경주""";
    private static final String TITLE_RESULT = "페어 매칭 결과입니다.";
    private static final String REQUEST_RETRY = """
            매칭 정보가 있습니다. 다시 매칭하시겠습니까?
            네 | 아니오""";
    private static final String INFORM_RESET = "초기화 되었습니다.";
    private static final String FORMAT_PAIR_MATCH_RESULT = "%s : %s";
    private static final String RETRY = """
            과정, 레벨, 미션을 선택하세요.
            ex) 백엔드, 레벨1, 자동차경주""";

    public void showTitleFunction() {
        showln(TITLE_FUNCTION);
    }

    public void showRetry() {
        showln(LINE + RETRY);
    }

    public void showTitleSelect() {
        showln(LINE + TITLE_SELECT);
    }

    public void showRequestRetry() {
        showln(LINE + REQUEST_RETRY);
    }

    public void showInformReset() {
        showln(LINE + INFORM_RESET + LINE);
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

    public void showMatchResult(final PairMatchResultDto pairMatchResultDto) {
        showln(LINE + TITLE_RESULT);
        for (List<String> result : pairMatchResultDto.results()) {
            String message = String.join(" : ", result);
            showln(message);
        }
        showln("");
    }
}
