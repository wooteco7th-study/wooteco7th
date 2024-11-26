package vendingmachine.domain;

public class Drinker {

    private int money;

    public Drinker(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        if (money < 10) {
            throw new IllegalArgumentException();
        }
    }

    public void setMoney(int price) {
        this.money -= price;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "투입 금액 : " + money + "원\n";
    }
}
