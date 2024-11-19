package baseball;

import static baseball.BaseballRule.*;

import java.util.List;

/**
 *         - [ ] 컴퓨터 숫자와 유저의 숫자를 비교 후, 게임 실행 상태를 반환한다.
 *             - [ ] 게임 실행 상태에는 다음과 같이 존재한다.
 *                 - [ ] 같은 수가 같은 자리에 3개 일 경우 - 3스트라이크
 *                 - [ ] 같은 수가 같은 자리에 2개 이면서
 *                     - [ ] 나머지 1자리 수가 다른 경우 - 2스트라이크
 *                 - [ ] 같은수가 같은자리에 1개 이면서
 *                     - [ ] 나머지 같은 수가 다른 자리에 2개 있으면 - 1스트라이크 - 2볼
 *                     - [ ] 나머지 같은 수가 다른 자리에 1개 있으면 - 1스트라이크 - 1볼
 *                     - [ ] 나머지 같은 수가 없으면 - 1스트라이크
 *                 - [ ] 같은 수가 같은자리에 0개 이면서
 *                     - [ ] 나머지 같은 수가 다른 자리에 3개 있으면 - 3볼
 *                     - [ ] 나머지 같은 수가 다른 자리에 2개 있으면 - 2볼
 *                     - [ ] 나머지 같은 수가 다른 자리에 1개 있으면 - 1볼
 *                     - [ ] 나머지 같은 수가 다른 자리에 0개 있으면 - 낫싱
 */
public enum BaseballGame {
    THREE_STRIKE("3스트라이크",3,0),
    TWO_STRIKE("2스트라이크",2,0),
    ONE_STRIKE_TWO_BALL("2볼 1스트라이크",1,2),
    ONE_STRIKE_ONE_BALL("1볼 1스트라이크",1,1),
    ONE_STRIKE_ZERO_BALL("1스트라이크",1,0),
    ZERO_STRIKE_THREE_BALL("3볼",0,3),
    ZERO_STRIKE_TWO_BALL("2볼",0,2),
    ZERO_STRIKE_ONE_BALL("1볼",0,1),
    NOTHING("낫싱",0,0);

    private final String message;
    private final int strike;
    private final int ball;

    BaseballGame(final String message, final int strike, final int ball) {
        this.message = message;
        this.strike = strike;
        this.ball = ball;
    }

    public static BaseballGame of(List<Integer> computer, List<Integer> user){
        int strikeCount = countStrikes(computer,user);
        int ballsCount = countBalls(computer,user,strikeCount);
        return BaseballGame.findStatus(strikeCount,ballsCount);
    }

    public boolean isAllStrike() {
        return this.equals(THREE_STRIKE);
    }

    public String getMessage() {
        return message;
    }

    public int getStrike() {
        return strike;
    }

    public int getBall() {
        return ball;
    }

    private static int countBalls(final List<Integer> computer, final List<Integer> user, final int strikeCount) {
        int balls =0;
        for(int i =0; i < BASEBALL_SIZE.getValue(); i++)
            if(computer.contains(user.get(i)))
                balls++;
        return balls - strikeCount;
    }

    private static int countStrikes(final List<Integer> computer, final List<Integer> user) {
        int strikeCount = 0;
        for(int i =0; i < BASEBALL_SIZE.getValue(); i++){
            if(computer.get(i) == user.get(i)) strikeCount++;
        }
        return strikeCount;
    }

    private static BaseballGame findStatus(final int strike, final int ball) {
        for (BaseballGame status : values()) {
            if (status.strike == strike && status.ball == ball) {
                return status;
            }
        }
        throw new IllegalArgumentException("[ERROR] 일치하는 상태 못찾았습니다: strike=" + strike + ", ball=" + ball);
    }

}
