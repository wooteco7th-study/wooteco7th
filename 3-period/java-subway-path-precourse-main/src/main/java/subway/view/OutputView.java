package subway.view;

public class OutputView {

    private static final String LINE = System.lineSeparator();

    private static final String WELCOME = """
            ## 메인 화면
            1. 경로 조회
            Q. 종료
                        
            ## 원하는 기능을 선택하세요.""";

    public void welcome(){
        showln(WELCOME);
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
