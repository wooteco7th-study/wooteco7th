package pairmatching.view;

import java.util.List;
import pairmatching.dto.CrewDto;
import pairmatching.dto.PairMatchResultDto;

public class OutputView {

    private static final String LINE = System.lineSeparator();
    private static final String TITLE_FUNCTION = """
            기능을 선택하세요.
            1. 크루 추가
            2. 크루 조회
            3. 크루 초기화
            4. 페어 매칭
            5. 페어 조회
            6. 페어 초기화
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
    private static final String RETRY = """
            과정, 레벨, 미션을 선택하세요.
            ex) 백엔드, 레벨1, 자동차경주""";
    private static final String REQUEST_COURSE = """
            과정을 입력하세요.
            ex) 백엔드, 프론트엔드""";
    private static final String REQUEST_CREW_NAME = """
            크루원을 입력하세요. (이름은 쉼표로 구분)
            ex) 포비,준,제이""";
    private static final String REQUEST_CREW_NAME_RETRY = """
            크루가 추가되었습니다.
            계속해서 다른 과정의 크루를 추가하시겠습니까?
            네 | 아니오""";
    private static final String REQUEST_SAVE_CREW = """
            크루 정보를 파일에 저장하시겠습니까?
            네 | 아니오""";
    private static final String REQUEST_SAVE_PAIR_MATCHING = """
            페어 매칭 정보를 파일에 저장하시겠습니까?
            네 | 아니오""";
    private static final String INFORM_EXIT = "프로그램을 종료합니다.";
    private static final String DELIMITER = ", ";
    private static final String EXCLUDE_FUNCTION_MESSAGE = "제외된 기능입니다.";

    public void showRequestCrewName() {
        showln(LINE + REQUEST_CREW_NAME);
    }

    public void showRequestCrewNameRetry() {
        showln(LINE + REQUEST_CREW_NAME_RETRY);
    }

    public void showRequestSaveCrew() {
        showln(LINE + REQUEST_SAVE_CREW);
    }

    public void showRequestSavePairMatching() {
        showln(LINE + REQUEST_SAVE_PAIR_MATCHING);
    }

    public void showInformExit() {
        showln(INFORM_EXIT);
    }

    public void showRequestCourse() {
        showln(LINE + REQUEST_COURSE);
    }

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
        showln(LINE + INFORM_RESET);
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
    }

    public void showCrew(final CrewDto crewDto) {
        String message = String.join(DELIMITER, crewDto.names());
        showln(message);
    }

    public void showBlank() {
        showln("");
    }

    public void informExcludeFunction() {
        showln(EXCLUDE_FUNCTION_MESSAGE);
    }
}
