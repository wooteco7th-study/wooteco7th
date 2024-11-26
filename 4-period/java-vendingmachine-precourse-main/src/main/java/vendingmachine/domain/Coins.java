package vendingmachine.domain;

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

    public void setCoin(int getCoin, int makeCoin) {
        if (getCoin == 500) {
            this.coin500 += makeCoin;
        }
        if (getCoin == 100) {
            this.coin100 += makeCoin;
        }
        if (getCoin == 50) {
            this.coin50 += makeCoin;
        }
        if (getCoin == 10) {
            this.coin10 += makeCoin;
        }
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
