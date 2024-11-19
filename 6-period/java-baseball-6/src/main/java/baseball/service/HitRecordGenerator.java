package baseball.service;

import baseball.domain.HitRecord;
import java.util.List;

public class HitRecordGenerator {

    private static final Integer BALL_SIZE = 3;

    private final List<Integer> computerBalls;

    public HitRecordGenerator(List<Integer> computerBalls) {
        this.computerBalls = computerBalls;
    }

    public HitRecord generate(List<Integer> hitterBalls) {
        HitRecord record = new HitRecord();
        for (int inning = 0; inning < BALL_SIZE; inning++) {
            if (hitStrike(computerBalls.get(inning), hitterBalls.get(inning))) {
                record.addStrike();
                continue;
            }
            if (hitBall(hitterBalls.get(inning))) {
                record.addBall();
            }
        }
        return record;
    }

    private boolean hitStrike(Integer computerBall, Integer hitterBall) {
        return computerBall.equals(hitterBall);
    }

    private boolean hitBall(Integer hitterBall) {
        return computerBalls.contains(hitterBall);
    }
}
