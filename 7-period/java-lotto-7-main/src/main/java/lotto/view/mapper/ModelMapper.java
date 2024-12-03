package lotto.view.mapper;

import static lotto.constant.ExceptionMessage.INVALID_INPUT_FORM;

import java.util.List;
import lotto.domain.vo.BonusNumber;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.PurchaseAmount;

public class ModelMapper {

    private ModelMapper() {
    }

    public static PurchaseAmount toPurchaseAmount(String input) {
        // 패턴 제작
        String pattern = RequestPattern.createPurchaseAmountPattern();
        // 검증 시작
        RequestValidator.validateInput(input, pattern, INVALID_INPUT_FORM.getMessage());

        // 팟싱해서 매핑시작
        int purchaseAmount = RequestParser.parseToInt(input);

        // 매핑
        return new PurchaseAmount(purchaseAmount);

    }

    public static Lotto toWinningNumbers(String input) {
        // 패턴 제작
        String pattern = RequestPattern.createWinningNumbersPattern();
        // 검증 시작
        RequestValidator.validateInput(input, pattern, INVALID_INPUT_FORM.getMessage());

        // 팟싱해서 매핑시작
        List<Integer> lottoNumbers = RequestParser.parserList(input);
        return new Lotto(lottoNumbers);
    }


    public static BonusNumber toBonusNumber(final String input) {
        // 패턴 제작
        String pattern = RequestPattern.createWinningNumbersPattern();
        // 검증 시작
        RequestValidator.validateInput(input, pattern, INVALID_INPUT_FORM.getMessage());

        // 팟싱해서 매핑시작
        int bonusNumber = RequestParser.parseToInt(input);
        return new BonusNumber(bonusNumber);
    }
}
