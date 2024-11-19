package baseball.view;

public class OutputView {

    public void showStartMessage() {
        System.out.println("숫자 야구 게임을 시작합니다.");
    }

    public void showCommentForInputNumbers() {
        System.out.print("숫자를 입력해주세요 : ");
    }

    public void showResult(final String result) {
        System.out.println(result);
    }

    public void showEndMessage() {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
    }
}
