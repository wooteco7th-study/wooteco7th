//package baseball;
//
//import java.util.List;
//
//public class GameController {
//
//    public void run(){
//        boolean isGameRunning = true;
//
//        while (isGameRunning) {
//            playOneGame();
//            isGameRunning = askGameContinue();
//        }
//
//    }
//    private void playOneGame() {
//        // given
//        List<Integer> computer = NumberGenerator.generateNumber();
//        boolean isCorrect = false;
//        System.out.println("숫자 야구 게임을 시작합니다.");
//
//        // when
//        while (!isCorrect) {
//            List<Integer> user = InputView.getUserNumber();
//            PlayStatus playStatus = BaseballGame.match(computer, user);
//
//            OutputView.printPlayStatus(playStatus);
//
//            if (playStatus.isAllStrike()) {
//                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
//                isCorrect = true;
//            }
//        }
//    }
//    private boolean askGameContinue() {
//        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
//        int choice = InputView.getUserChoice();
//
//        return choice == GAME_RESTART;
//    }
//}
