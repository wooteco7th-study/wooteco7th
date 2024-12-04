package subway.view;

public class OutputView {

    private static final String LINE = System.lineSeparator();

    private static final String WELCOME = """
            ## 메인 화면
            1. 경로 조회
            Q. 종료
                        
            ## 원하는 기능을 선택하세요.""";

    private static final String SELECT_ROUTE_CRITERIA = """
            ## 경로 기준
            1. 최단 거리
            2. 최소 시간
            B. 돌아가기

            ## 원하는 기능을 선택하세요.""";

    public void welcome() {
        showln(WELCOME);
    }

    public void selectRouteCriteria() {
        showln(LINE + SELECT_ROUTE_CRITERIA);
    }

    public void showException(Exception exception) {
        showln(exception.getMessage());
    }

    private String format(String format, Object... args) {
        return String.format(format, args);
    }

    public void showBlank() {
        System.out.println();
    }

    private void showln(String message) {
        System.out.println(message);
    }
}
