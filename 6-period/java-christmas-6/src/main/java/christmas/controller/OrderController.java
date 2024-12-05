package christmas.controller;

import christmas.domain.Benefit;
import christmas.domain.OrderForm;
import christmas.domain.OrderParser;
import christmas.domain.OrderProcessor;
import christmas.domain.VisitDate;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.function.Supplier;

public class OrderController {

    private final InputView inputView;
    private final OutputView outputView;
    private final OrderProcessor orderProcessor;

    public OrderController(final InputView inputView, final OutputView outputView, final OrderProcessor orderProcessor) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.orderProcessor = orderProcessor;
    }

    public void run() {
        VisitDate visitDate = retryOnInvalidInput(this::getVisitDate);
        OrderForm orderForm = retryOnInvalidInput(() -> getOrderForm(visitDate));
        Benefit benefit = orderProcessor.process(orderForm);
        outputView.printReceipt(orderForm, benefit);
    }

    private OrderForm getOrderForm(final VisitDate visitDate) {
        String menuAndQuantity = inputView.readMenuAndQuantity();
        return OrderParser.parse(visitDate, menuAndQuantity);
    }

    private VisitDate getVisitDate() {
        return new VisitDate(inputView.readVisitDate());
    }

    private <T> T retryOnInvalidInput(Supplier<T> input) {
        while (true) {
            try {
                return input.get();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
