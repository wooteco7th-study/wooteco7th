package vendingmachine.controller;


import static camp.nextstep.edu.missionutils.Console.readLine;

import java.util.List;
import vendingmachine.domain.money.Money;
import vendingmachine.service.VendingMachineService;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;
import vendingmachine.view.ProductRegistrationRequest;

/*
자판기가 보유하고 있는 금액을 입력해 주세요.
450

자판기가 보유한 동전
500원 - 0개
100원 - 4개
50원 - 1개
10원 - 0개

상품명과 가격, 수량을 입력해 주세요.
[콜라,1500,20];[사이다,1000,10]

투입 금액을 입력해 주세요.
3000

투입 금액: 3000원
구매할 상품명을 입력해 주세요.
콜라

투입 금액: 1500원
구매할 상품명을 입력해 주세요.
사이다

투입 금액: 500원
잔돈
100원 - 4개
50원 - 1개

 */
public class VendingMachineController {
    private final InputView inputView;
    private final VendingMachineService service;
    private final OutputView outputView;

    public VendingMachineController() {
        this.inputView = new InputView();
        this.service = new VendingMachineService(initializeVendingMachine());
        this.outputView = new OutputView();
    }

    public static void main(String[] args) {
        VendingMachineController vendingMachineController = new VendingMachineController();
        vendingMachineController.run();

    }

    public void run() {
        outputView.printCoinStatus(service.getCoinStatus());

        String s = readLine();
        System.out.println(":" + s);
//        registerProducts();

//        insertMoneyToMachine();
//
//        //TODO: 반복
//
//        //  투입 금액: 3000원
//        outputView.printInsertedMoney(null);
//        //  구매할 상품명을 입력해 주세요. 콜라
//        inputView.requestProductName();
//        //  투입 금액: 1500원
//        outputView.printInsertedMoney(null);
//        //  구매할 상품명을 입력해 주세요. 사이다
//        inputView.requestProductName();
//
//        // TODO: 더 이상 자판기의 남은 돈으로 상품을 구매하지 못하거나, 상품 수량이 부족하여 구매 못할시
//        //  투입 금액: 500원
//        outputView.printInsertedMoney(null);
//        // 잔돈
//        outputView.printChange(null);

    }

    private void registerProducts() {
        //  상품명과 가격, 수량을 입력해 주세요.
        List<ProductRegistrationRequest> productRegistrationRequests = inputView.requestProductRegistration();
        service.addProducts(productRegistrationRequests);
    }

    private void insertMoneyToMachine() {
        //  투입 금액을 입력해 주세요.
        Money money = inputView.requestUserMoney();

        //  투입 금액: 3000원
        outputView.printInsertedMoney(money);
    }

    private Money initializeVendingMachine() {

        //  자판기가 보유하고 있는 금액을 입력해 주세요.
        return inputView.requestInitialAmount();
    }

}
