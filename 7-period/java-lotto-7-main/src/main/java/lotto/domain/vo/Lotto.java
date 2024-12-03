package lotto.domain.vo;

import static lotto.domain.validator.LottoValidator.validateLotto;
import static lotto.util.ValidationUtils.validateNullOrEmpty;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<Integer> numbers) {
        validateNullOrEmpty(numbers);
        List<LottoNumber> lottoNumbers = mapped(numbers);
        validateLotto(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private List<LottoNumber> mapped(final List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .sorted(Comparator.naturalOrder())
                .map(LottoNumber::new)
                .toList();
        return lottoNumbers;
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers.stream().map(LottoNumber::getValue).collect(Collectors.toList());
    }
}
