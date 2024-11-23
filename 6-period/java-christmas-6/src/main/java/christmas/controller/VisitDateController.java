package christmas.controller;

import christmas.Domain.VisitDate;
import christmas.service.Converter;
import christmas.view.InputView;
import christmas.view.OutputView;

public class VisitDateController {

    private final InputView inputView;
    private final OutputView outputView;

    public VisitDateController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public VisitDate run() {
        while (true) {
            try {
                return new VisitDate(Converter.getNumberDate(inputView.getVisitDate()));
            } catch (IllegalArgumentException e) {
                outputView.print(e.getMessage());
            }
        }
    }
}
