package christmas.view.console;

import christmas.view.OutputView;

public class ConsoleOutputView implements OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String INTRO = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String ASK_VISIT_DAY = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ASK_ORDER_MENU = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    @Override
    public void printIntro() {
        printlnMessage(INTRO);
    }

    @Override
    public void printAskVisitDay() {
        printlnMessage(ASK_VISIT_DAY);
    }

    @Override
    public void printAskOrderMenu() {
        printlnMessage(ASK_ORDER_MENU);
    }

    private void printlnMessage(final String message) {
        System.out.println(message);
    }

    private void printMessage(final String message) {
        System.out.print(message);
    }
}
