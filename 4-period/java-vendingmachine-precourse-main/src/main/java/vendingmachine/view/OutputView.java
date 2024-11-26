package vendingmachine.view;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import vendingmachine.Coin;
import vendingmachine.Money;

/**
 * 자판기가 보유하고 있는 금액을 입력해 주세요. 450
 * <p>
 * 자판기가 보유한 동전 500원 - 0개 100원 - 4개 50원 - 1개 10원 - 0개
 * <p>
 * 상품명과 가격, 수량을 입력해 주세요. [콜라,1500,20];[사이다,1000,10]
 * <p>
 * 투입 금액을 입력해 주세요. 3000
 * <p>
 * 투입 금액: 3000원 구매할 상품명을 입력해 주세요. 콜라
 * <p>
 * 투입 금액: 1500원 구매할 상품명을 입력해 주세요. 사이다
 * <p>
 * 투입 금액: 500원 잔돈 100원 - 4개 50원 - 1개
 */
public class OutputView {
    private static final String NEW_LINE = "\n";
    private static final String COIN_STATUS_HEADER = NEW_LINE + "자판기가 보유한 동전";
    private static final String INSERTED_MONEY_FORMAT = NEW_LINE + "투입 금액: %d원";
    private static final String CHANGE_HEADER = NEW_LINE + "잔돈";
    private static final String COIN_FORMAT = "%d원 - %d개";

    /**
     * 자판기가 보유한 동전 500원 - 0개 100원 - 4개 50원 - 1개 10원 - 0개
     */
    public void printCoinStatus(Map<Coin, Integer> coinStatus) {

        println(COIN_STATUS_HEADER);
        printCoins(coinStatus);


    }

    /**
     * 투입 금액: 3000원
     */
    public void printInsertedMoney(Money money) {
        println(String.format(INSERTED_MONEY_FORMAT, money.getAmount()));

    }

    /**
     * 잔돈 100원 - 4개 50원 - 1개
     */
    public void printChange(Map<Coin, Integer> change) {
        println(CHANGE_HEADER + NEW_LINE);
        printCoins(change);


    }

    private void printCoins(Map<Coin, Integer> coins) {
        String formattedCoins = coins.entrySet().stream()
                .sorted(Entry.comparingByKey(
                        Comparator.comparingInt(Coin::getAmount).reversed()
                ))
                .map(entry -> String.format(COIN_FORMAT, entry.getKey().getAmount(), entry.getValue()))
                .collect(Collectors.joining(NEW_LINE));
    }

    private void println(String message) {
        System.out.println(message);
    }
}
