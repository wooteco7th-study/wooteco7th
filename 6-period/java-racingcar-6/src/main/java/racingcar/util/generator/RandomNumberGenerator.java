package racingcar.util.generator;

import static racingcar.constant.Rule.*;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomNumberGenerator implements NumberGenerator {

	private RandomNumberGenerator() {
	}

	private static class BillPughSingleton {
		private static final RandomNumberGenerator INSTANCE = new RandomNumberGenerator();
	}

	public static RandomNumberGenerator getInstance() {
		return BillPughSingleton.INSTANCE;
	}

	@Override
	public int generate() {
		return Randoms.pickNumberInRange(NUMBER_MIN, NUMBER_MAX);
	}
}
