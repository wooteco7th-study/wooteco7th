package christmas.controller;

import christmas.domain.DiscountInfo;
import christmas.domain.vo.Menu;
import christmas.domain.vo.OrderMenus;
import christmas.domain.vo.VisitDate;
import christmas.service.OrderService;
import christmas.util.RetryTemplate;
import christmas.view.InputRequestVO.ProductQuantityRequest;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderController {

    private final InputView inputView;
    private final OrderService orderService;
    private final OutputView outputView;

    public OrderController() {
        this.inputView = new InputView();
        this.orderService = new OrderService();
        this.outputView = new OutputView();
    }

    public void run() {
        outputView.printRequestVisitDateMessage();
        VisitDate visitDate = getVisitDateWithRetry();

        outputView.printRequestOrderMenusMessage();
        OrderMenus orderMenus = getOrderMenusWithRetry();

        DiscountInfo discountInfo = orderService.processOrder(orderMenus, visitDate);

        outputView.printDiscountPreviewMessage(visitDate.getVisitDate().getDayOfMonth());
        outputView.printDiscountInfo(discountInfo);
    }

    private VisitDate toVisitDate(final int visitDate) {
        return new VisitDate(visitDate);
    }

    private OrderMenus toOrderMenus(final List<ProductQuantityRequest> productQuantityRequests) {

        Map<Menu, Integer> menuMap = productQuantityRequests.stream()
                .collect(Collectors.toMap(
                        request -> Menu.of(request.getName()),
                        ProductQuantityRequest::getQuantity
                ));

        return new OrderMenus(menuMap);
    }

    private VisitDate getVisitDateWithRetry() {
        return RetryTemplate.executeWithRetry(() -> {
            int date = inputView.requestVisitDate();
            return toVisitDate(date);
        });
    }

    private OrderMenus getOrderMenusWithRetry() {
        return RetryTemplate.executeWithRetry(() -> {
            List<ProductQuantityRequest> requests = inputView.requestOrderMenus();
            return toOrderMenus(requests);
        });
    }


}
