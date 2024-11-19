package baseball.domain;

public class HitRecord {

    private Integer strike;
    private Integer ball;

    public HitRecord() {
        this.strike = 0;
        this.ball = 0;
    }

    public void addStrike() {
        strike += 1;
    }

    public void addBall() {
        ball += 1;
    }

    public Integer getStrike() {
        return strike;
    }

    public Integer getBall() {
        return ball;
    }
}
