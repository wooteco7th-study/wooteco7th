package vendingmachine.domain.coin;

import java.util.ArrayList;
import java.util.List;
import vendingmachine.domain.change.Change;
import vendingmachine.domain.change.Changes;
import vendingmachine.view.PrintMessage;

public class Coins {

    private int coin500;
    private int coin100;
    private int coin50;
    private int coin10;

    public Coins() {
        this.coin500 = 0;
        this.coin100 = 0;
        this.coin50 = 0;
        this.coin10 = 0;
    }

    public void setCoin(int getCoin) {
        if (getCoin == 500) {
            this.coin500 += 1;
        }
        if (getCoin == 100) {
            this.coin100 += 1;
        }
        if (getCoin == 50) {
            this.coin50 += 1;
        }
        if (getCoin == 10) {
            this.coin10 += 1;
        }
    }

    public Changes change(int money) {
        List<Change> changes = new ArrayList<>();
        if (money / 500 > 0) {
            changes.add(new Change(500, money / 500));
            money %= 500;
        }
        if (money / 100 > 0) {
            changes.add(new Change(100, money / 100));
            money %= 100;
        }
        if (money / 50 > 0) {
            changes.add(new Change(50, money / 50));
            money %= 50;
        }
        if (money / 10 > 0) {
            changes.add(new Change(10, money / 10));
        }
        return new Changes(changes);
    }

    @Override
    public String toString() {
        return PrintMessage.HAVING_COIN.getMessage() + "\n"
                + "500원 - " + coin500 + "개\n"
                + "100원 - " + coin100 + "개\n"
                + "50원 - " + coin50 + "개\n"
                + "10원 - " + coin10 + "개\n";
    }
}
