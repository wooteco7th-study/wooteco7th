package vendingmachine.domain;

public class Change {

    private final int coin;
    private final int count;

    public Change(int coin, int count) {
        this.coin = coin;
        this.count = count;
    }

    @Override
    public String toString() {
        return coin + "원 - " + count + "개\n";
    }
}
