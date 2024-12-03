package lotto.view;

import static lotto.util.RetryOnExceptionTemplate.retryOnException;

import camp.nextstep.edu.missionutils.Console;
import java.util.function.Function;
import lotto.domain.vo.BonusNumber;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.PurchaseAmount;
import lotto.view.mapper.ModelMapper;

/*
구입금액 입력 받기
"구입금액을 입력해 주세요." 메시지 출력
구입급액을 입력 받는다

당첨 번호 입력 받기
"당첨 번호를 입력해 주세요." 메시지 출력
당첨 번호를 입력 받는다.

보너스 번호 입력 받기
"보너스 번호를 입력해 주세요." 메시지 출력
보너스 번호를 입력 받는다.

 */
public class RequestView {
    private final String NEW_LINE = System.lineSeparator();
    private final String REQUEST_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    private final String REQUEST_WINNING_NUMBERS = "당첨 번호를 입력해 주세요";
    private final String REQUEST_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";

    public PurchaseAmount requestPurchaseAmount() {
        return request(REQUEST_PURCHASE_AMOUNT, ModelMapper::toPurchaseAmount);
    }

    public Lotto requestWinningNumbers() {
        return request(REQUEST_WINNING_NUMBERS, ModelMapper::toWinningNumbers);
    }

    public BonusNumber requestBonusNumber() {
        return request(REQUEST_BONUS_NUMBER, ModelMapper::toBonusNumber);
    }


    private <T> T request(String ExceptionMessage, Function<String, T> mapper) {
        return retryOnException(() -> {
            System.out.println(ExceptionMessage);
            String input = Console.readLine();
            System.out.println();
            return mapper.apply(input);
        });
    }

}
