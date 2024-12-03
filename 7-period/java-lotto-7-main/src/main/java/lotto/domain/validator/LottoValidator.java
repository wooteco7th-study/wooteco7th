package lotto.domain.validator;

import static lotto.constant.ExceptionMessage.DUPLICATE_MESSAGE;
import static lotto.constant.ExceptionMessage.OUT_OF_SIZE_MESSAGE;
import static lotto.constant.LottoRule.LOTTO_SIZE;
import static lotto.util.ValidationUtils.validateNullOrEmpty;

import java.util.List;
import lotto.domain.vo.LottoNumber;
import lotto.util.ListUtil;

public class LottoValidator {
    private LottoValidator() {
    }

    public static void validateLotto(List<LottoNumber> numbers) {
        validateNullOrEmpty(numbers);
        validateSize(numbers);
        validateDuplicate(numbers);
    }

    private static void validateDuplicate(final List<LottoNumber> numbers) {
        if (ListUtil.hasDuplicates(numbers)) {
            throw new IllegalArgumentException(DUPLICATE_MESSAGE.getMessage());
        }
    }

    private static void validateSize(final List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(OUT_OF_SIZE_MESSAGE.getMessage());
        }
    }

}
