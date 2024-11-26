package vendingmachine.controller;


import java.util.List;
import vendingmachine.Money;
import vendingmachine.service.VendingMachineService;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;
import vendingmachine.view.ProductRegistrationRequest;

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
    private final VendingMachineService service;
    private final OutputView outputView;

    public VendingMachineController(final InputView inputView,
                                    final VendingMachineService vendingMachineService,
                                    final OutputView outputView) {
        this.inputView = inputView;
        this.service = new VendingMachineService(initializeVendingMachine());
        this.outputView = outputView;
    }

    public void run() {
        initializeVendingMachine();
        outputView.printCoinStatus(service.getCoinStatus());

        registerProducts();

        insertMoneyToMachine();

        //TODO: 반복

        //  투입 금액: 3000원
        outputView.printInsertedMoney(null);
        //  구매할 상품명을 입력해 주세요. 콜라
        inputView.requestProductName();
        //  투입 금액: 1500원
        outputView.printInsertedMoney(null);
        //  구매할 상품명을 입력해 주세요. 사이다
        inputView.requestProductName();

        // TODO: 더 이상 자판기의 남은 돈으로 상품을 구매하지 못하거나, 상품 수량이 부족하여 구매 못할시
        //  투입 금액: 500원
        outputView.printInsertedMoney(null);
        // 잔돈
        outputView.printChange(null);


    }

    private void registerProducts() {
        //  상품명과 가격, 수량을 입력해 주세요.
        List<ProductRegistrationRequest> productRegistrationRequests = inputView.requestProductRegistration();
        service.addProducts(productRegistrationRequests);
    }

    private void insertMoneyToMachine() {
        //  투입 금액을 입력해 주세요.
        inputView.requestUserMoney();

        //  투입 금액: 3000원
        outputView.printInsertedMoney(null);
    }

    private Money initializeVendingMachine() {
        while (true) {
            try {
                //  자판기가 보유하고 있는 금액을 입력해 주세요.
                Money money = inputView.requestInitialAmount();
                return money;
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

}
