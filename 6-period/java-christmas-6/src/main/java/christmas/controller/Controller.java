package christmas.controller;

import christmas.domain.Day;
import christmas.util.Converter;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void process() {
        outputView.commentWelcomeMessage();
        Day visitDay = createDay();
    }

    private Day createDay() {
        outputView.commentVisitDate();
        String day = inputView.readLine();
        return new Day(Converter.convertToInteger(day));
    }
}
