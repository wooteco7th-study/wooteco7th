package baseball.service;

import baseball.domain.HitRecord;
import java.util.List;

public class HitRecordGenerator {

    private final List<Integer> computerBalls;

    public HitRecordGenerator(List<Integer> computerBalls) {
        this.computerBalls = computerBalls;
    }

    public HitRecord generate(List<Integer> hitterBalls) {
        HitRecord record = new HitRecord();
        for (int inning = 0; inning < 3; inning++) {
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
