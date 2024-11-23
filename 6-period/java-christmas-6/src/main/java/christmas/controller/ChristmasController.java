package christmas.controller;

import christmas.domain.discount.Discount;
import christmas.domain.discount.EventPlanner;
import christmas.domain.order.Order;
import christmas.domain.order.OrderGroup;
import christmas.domain.visit.VisitDate;
import christmas.dto.BenefitMenuReceipt;
import christmas.dto.DiscountReceipt;
import christmas.dto.OrderMenuReceipt;
import christmas.service.ChristmasService;
import christmas.util.LoopTemplate;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class ChristmasController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChristmasService christmasService;

    public ChristmasController(final InputView inputView, final OutputView outputView,
                               final ChristmasService christmasService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.christmasService = christmasService;
    }

    public void run() {
        outputView.printIntro();
        final VisitDate visitDate = requestVisitDate();
        final OrderGroup orderGroup = requestOrderMenus();
        final List<Discount> discounts = christmasService.generateDiscounts(orderGroup, visitDate);
        final EventPlanner eventPlanner = new EventPlanner(discounts);
        responseResult(eventPlanner, orderGroup);
    }

    private void responseResult(final EventPlanner eventPlanner, final OrderGroup orderGroup) {
        responseOrderResult(orderGroup);
        responseBenefitMenuReceipt(eventPlanner);
        responseDiscountReceipts(eventPlanner);
        responseTotalDiscount(eventPlanner);
        responseAfterDiscountTotalPrice(eventPlanner, orderGroup);
        responseBadgeName(eventPlanner);
    }

    private void responseBadgeName(final EventPlanner eventPlanner) {
        final String badgeName = eventPlanner.getBadgeName();
        outputView.printBadgeName(badgeName);
    }

    private void responseAfterDiscountTotalPrice(final EventPlanner eventPlanner, final OrderGroup orderGroup) {
        final long totalPrice = orderGroup.calculateOrdersTotalPrice() - eventPlanner.calculateTotalDiscount();
        outputView.printAfterDiscountTotalPrice(totalPrice);
    }

    private void responseTotalDiscount(final EventPlanner eventPlanner) {
        final int totalDiscount = eventPlanner.calculateTotalDiscount();
        outputView.printTotalDiscount(totalDiscount);
    }

    private void responseDiscountReceipts(final EventPlanner eventPlanner) {
        final List<DiscountReceipt> discountReceipts = christmasService.generateDiscountReceipts(eventPlanner);
        outputView.printDiscountReceipts(discountReceipts);
    }

    private void responseBenefitMenuReceipt(final EventPlanner eventPlanner) {
        final BenefitMenuReceipt benefitMenuReceipt = christmasService.generateBenefitMenuReceipt(eventPlanner);
        outputView.printBenefitMenuReceipt(benefitMenuReceipt);
    }


    private void responseOrderResult(final OrderGroup orderGroup) {
        responseOrderReceipts(orderGroup);
        outputView.printBeforeDiscountTotalPrice(orderGroup.calculateOrdersTotalPrice());
    }

    private void responseOrderReceipts(final OrderGroup orderGroup) {
        final List<OrderMenuReceipt> orderMenuReceipts = christmasService.generateOrderMenuReceipts(
                orderGroup.getOrders());
        outputView.printOrderMenuReceipts(orderMenuReceipts);

    }

    private VisitDate requestVisitDate() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskVisitDay();
            final int day = inputView.readVisitDay();
            return VisitDate.of(day);
        });
    }

    private OrderGroup requestOrderMenus() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskOrderMenu();
            final List<String> orderMenus = inputView.readOrderMenu();
            final List<Order> orders = christmasService.generateOrders(orderMenus);
            return new OrderGroup(orders);
        });
    }

}
