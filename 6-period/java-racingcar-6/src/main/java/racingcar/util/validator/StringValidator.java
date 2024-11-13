package racingcar.util.validator;

public interface StringValidator {

	StringValidator validateLength(final String str);
	StringValidator validateFormat(final String str);
}
