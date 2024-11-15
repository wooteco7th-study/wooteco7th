package racingcar;

// record 일부러 안씀. record 쓰면 해쉬코드,equals 자동 생성이기 때문에, 중복 이름 허용 무시하게됨.
public class RaceInfo {
    private final String name;
    private final int totalMoveAmount;

    public RaceInfo(Car car) {
        this.name = car.getName();
        this.totalMoveAmount = car.getMoveAmount();
    }

    public String getName() {
        return name;
    }

    public int getTotalMoveAmount() {
        return totalMoveAmount;
    }
}
