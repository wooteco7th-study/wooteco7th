package lotto.domain.vo;

import static lotto.domain.validator.LottoNumberValidator.validateLottoNumber;

import java.util.Objects;

public class LottoNumber {
    private final int value;

    public LottoNumber(final int value) {
        validateLottoNumber(value);
        this.value = value;
    }


    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
