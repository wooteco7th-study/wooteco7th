package racingcar.util.convertor;

import java.util.List;

public interface InputConvertor<T> {

	List<T> covertToList(final String input);
}
