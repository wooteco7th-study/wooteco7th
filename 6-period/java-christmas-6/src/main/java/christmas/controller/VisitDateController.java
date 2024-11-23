package christmas.controller;

import christmas.Domain.VisitDate;
import christmas.service.Converter;
import christmas.view.InputView;

public class VisitDateController {

    private final InputView inputView;

    public VisitDateController(InputView inputView) {
        this.inputView = inputView;
    }

    public VisitDate run() {
        return new VisitDate(Converter.getNumber(inputView.getVisitDate()));
    }
}
