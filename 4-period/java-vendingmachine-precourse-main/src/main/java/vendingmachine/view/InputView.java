package vendingmachine.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import vendingmachine.domain.money.Money;
import vendingmachine.exception.ErrorMessage;

/**
 * (인풋) 자판기가 보유하고 있는 금액을 입력해 주세요. 450
 * <p>
 * (인풋) 상품명과 가격, 수량을 입력해 주세요. [콜라,1500,20];[사이다,1000,10]
 * <p>
 * (인풋) 투입 금액을 입력해 주세요. 3000
 * <p>
 * (인풋) 구매할 상품명을 입력해 주세요. 콜라
 */
public class InputView {

    private static final String NEW_LINE = "\n";
    private static final String INITIAL_AMOUNT_MESSAGE = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private static final String PRODUCT_REGISTRATION_MESSAGE = NEW_LINE + "상품명과 가격, 수량을 입력해 주세요.";
    private static final String INSERT_MONEY_MESSAGE = NEW_LINE + "투입 금액을 입력해 주세요.";
    private static final String PRODUCT_NAME_MESSAGE = "구매할 상품명을 입력해 주세요.";
    //

    /**
     * (인풋) * 자판기가 보유하고 있는 금액을 입력해 주세요. * 450
     */
    public Money requestInitialAmount() {
        println(INITIAL_AMOUNT_MESSAGE);
        try {
            return Money.of(Integer.parseInt(readLine().trim()));
        } catch (Exception e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_REQUEST_AMOUNT.getMessage());
        }
    }

    /**
     * (인풋) * 상품명과 가격, 수량을 입력해 주세요. * [콜라,1500,20];[사이다,1000,10]
     */
    public List<ProductRegistrationRequest> requestProductRegistration() {
        println(PRODUCT_REGISTRATION_MESSAGE);
        String input = readLine().trim();
        try {
            return ProductRegistrationRequest.from(input);

        } catch (Exception e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_REQUEST_PRODUCT_FORM.getMessage());
        }
    }


    /**
     * (인풋) * 투입 금액을 입력해 주세요. * 3000
     */
    public Money requestUserMoney() {
        println(INSERT_MONEY_MESSAGE);
        try {
            String input = Console.readLine();
            int amount = Integer.parseInt(input);
            validateAmount(amount);
            return Money.of(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_REQUEST_AMOUNT.getMessage());
        }
    }

    /**
     * (인풋) * 구매할 상품명을 입력해 주세요. * 콜라
     */
    public String requestProductName() {
        System.out.println(PRODUCT_NAME_MESSAGE);
        String input = Console.readLine();
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_REQUEST_PRODUCT_NAME.getMessage());
        }
        return input;
    }

    private void validateAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_REQUEST_AMOUNT_RANGE.getMessage());
        }
        if (amount % 10 != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_REQUEST_AMOUNT_UNIT.getMessage());
        }
    }

    public void println(String message) {
        System.out.println(message);
    }
}
