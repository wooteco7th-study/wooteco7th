package racingcar;

public class Car {
    private final String name;
    private final int moveAmount;

    public Car(String name, int moveAmount) {
        this.name = name;
        this.moveAmount = moveAmount;
    }

    public Car move(CarMovePolicy carMovePolicy){
        int moveAmount = carMovePolicy.drawOut();

        return new Car(name,this.moveAmount + moveAmount);
    }
    private void validateName(){
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
}
