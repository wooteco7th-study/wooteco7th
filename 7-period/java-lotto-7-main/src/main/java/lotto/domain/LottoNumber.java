package lotto.domain;

import java.util.Objects;
import lotto.exception.CustomIllegalArgumentException;
import lotto.exception.ErrorMessage;

public class LottoNumber implements Comparable<LottoNumber>{

    public static final int START_INCLUSIVE = 1;
    public static final int END_INCLUSIVE = 45;

    private final int value;

    public LottoNumber(final int value) {
        validate(value);
        this.value = value;
    }

    private void validate(final int value) {
        if (value < START_INCLUSIVE || value > END_INCLUSIVE) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_LOTTO_NUMBER);
        }
    }

    @Override
    public int compareTo(final LottoNumber compared) {
        return Integer.compare(this.value, compared.value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LottoNumber that)) {
            return false;
        }
        return getValue() == that.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    public int getValue() {
        return value;
    }
}
