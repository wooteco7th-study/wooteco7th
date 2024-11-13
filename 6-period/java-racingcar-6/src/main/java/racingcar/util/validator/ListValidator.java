package racingcar.util.validator;

import java.util.List;

public interface ListValidator<T> {

	void validateDuplicate(final List<T> list);

}
