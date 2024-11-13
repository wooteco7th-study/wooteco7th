package racingcar.view;

import java.util.List;

import racingcar.dto.CarDto;

public class OutputView {

	private static final String LINE_SEPARATOR = System.lineSeparator();
	private static final String INTRO = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
	private static final String ASK_ATTEMPT = "시도할 회수는 몇회인가요?";
	private static final String RESULT_TITLE = "실행 결과";
	private static final String COLONS = " : ";
	private static final String DISTANCE = "-";
	private static final String WINNERS = "최종 우승자";
	private static final String DELIMITER = ", ";


	public void printIntro() {
		System.out.println(INTRO);
	}

	public void printAskAttempt() {
		System.out.println(ASK_ATTEMPT);
	}

	public void printResultTitle() {
		System.out.println(RESULT_TITLE);
	}

	public void printResult(final List<CarDto> carDtos) {
		StringBuilder sb = new StringBuilder();
		for (CarDto dto : carDtos) {
			sb.append(dto.name())
				.append(COLONS)
				.append(DISTANCE.repeat(dto.score()))
				.append(LINE_SEPARATOR);
		}
		System.out.println(sb);
	}

	public void printWinners(List<String> winners) {
		StringBuilder sb = new StringBuilder();
		final String join = String.join(DELIMITER, winners);
		sb.append(WINNERS)
			.append(COLONS)
			.append(join);
		System.out.println(sb);
	}

}
