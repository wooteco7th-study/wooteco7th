package vendingmachine.view;


import java.util.List;
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
public class VendingMachineController {
    private final InputView inputView;
    private final OutputView outputView;

    public VendingMachineController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        //  자판기가 보유하고 있는 금액을 입력해 주세요.
        Money money = inputView.requestInitialAmount();
        //  자판기가 보유한 동전
        outputView.printCoinStatus(null);

        //  상품명과 가격, 수량을 입력해 주세요.
        List<ProductRegistrationRequest> productRegistrationRequests = inputView.requestProductRegistration();

        //  투입 금액을 입력해 주세요.
        inputView.requestUserMoney();

        //  투입 금액: 3000원
        outputView.printInsertedMoney(null);

        //TODO: 반복

        //  투입 금액: 3000원
        outputView.printInsertedMoney(null);
        //  구매할 상품명을 입력해 주세요. 콜라
        inputView.requestProductName();
        //  투입 금액: 1500원
        outputView.printInsertedMoney(null);
        //  구매할 상품명을 입력해 주세요. 사이다
        inputView.requestProductName();

        // TODO: 더 이상 구매 못할시
        //  투입 금액: 500원
        outputView.printInsertedMoney(null);
        // 잔돈
        outputView.printChange(null);


    }
}
