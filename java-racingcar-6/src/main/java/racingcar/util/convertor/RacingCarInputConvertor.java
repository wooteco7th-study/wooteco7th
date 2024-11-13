package racingcar.util.convertor;

import java.util.List;

public class RacingCarInputConvertor implements InputConvertor<String> {

	private static final String DELIMITER = ",";

	private RacingCarInputConvertor() {
	}

	private static class BillPughSingleton {
		private static final RacingCarInputConvertor INSTANCE = new RacingCarInputConvertor();
	}

	public static RacingCarInputConvertor getInstance() {
		return BillPughSingleton.INSTANCE;
	}

	@Override
	public List<String> covertToList(final String input) {
		final String[] split = input.split(DELIMITER);
		return List.of(split);
	}
}

