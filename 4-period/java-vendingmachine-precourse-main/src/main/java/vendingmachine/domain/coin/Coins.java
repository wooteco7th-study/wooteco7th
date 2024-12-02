package vendingmachine.domain.coin;

import java.util.ArrayList;
import java.util.Arrays;
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

    private int getCoin(int coin) {
        if (coin == 500) {
            return coin500;
        }
        if (coin == 100) {
            return coin100;
        }
        if (coin == 50) {
            return coin50;
        }
        return coin10;
    }

    private int calculateCoin(int coin, int money) {
        int canChange = money / coin;
        return Math.min(getCoin(coin), canChange);
    }

    private int calculateMoney(int coin, int money) {
        return money - calculateCoin(coin, money) * coin;
    }

    public Changes change(int money) {
        List<Change> changes = new ArrayList<>();
        List<Integer> coins = Arrays.asList(500, 100, 50, 10);

        for (int coin : coins) {
            if (money / coin > 0) {
                changes.add(new Change(coin, calculateCoin(coin, money)));
                money = calculateMoney(coin, money);
            }
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
