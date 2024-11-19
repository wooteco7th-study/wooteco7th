package baseball;

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
public enum PlayRoundStatus {
    THREE_STRIKE(3,0),
    TWO_STRIKE(2,0),
    ONE_STRIKE_TWO_BALL(1,2),
    ONE_STRIKE_ONE_BALL(1,1),
    ZERO_STRIKE_THREE_BALL(0,3),
    ZERO_STRIKE_TWO_BALL(0,2),
    ZERO_STRIKE_ONE_BALL(0,1),
    NOTHING(0,0);

    private final int strike;
    private final int ball;

    PlayRoundStatus(final int strike, final int ball) {
        this.strike = strike;
        this.ball = ball;
    }
}
