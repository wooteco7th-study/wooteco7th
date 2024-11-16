package racingcar.car;

public class Car {
    private static final int MOVE_INITIALIZATION = 0;
    private final String name;
    private final int moveAmount;

    private Car(String name, int moveAmount) {
        validateName(name);
        this.name = name;
        this.moveAmount = moveAmount;
    }

    public static Car of (String name){
        return new Car(name,MOVE_INITIALIZATION);
    }


    public Car move(CarMovePolicy carMovePolicy){
        int moveAmount = carMovePolicy.drawOut();

        return new Car(name,this.moveAmount + moveAmount);
    }
    private void validateName(String name){
        if(name.length() < 1 || name.length() > 5){
            throw new IllegalArgumentException("[ERROR] 자동차 이름의 길이를 0~5글자 사이로 입력 해 주세요.");
        }
    }

    public String getName() {
        return name;
    }

    public int getMoveAmount() {
        return moveAmount;
    }
    public RaceInfo carInfo(){
        return new RaceInfo(this);
    }
}
