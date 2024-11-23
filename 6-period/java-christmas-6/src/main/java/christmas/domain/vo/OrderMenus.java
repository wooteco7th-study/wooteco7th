package christmas.domain.vo;

import christmas.domain.Discount;
import christmas.domain.DiscountInfo;
import christmas.domain.DiscountResult;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderMenus {
    private final Map<Menu, Integer> orderMenus;

    public OrderMenus(final Map<Menu, Integer> orderMenus) {
        this.orderMenus = orderMenus;
    }

    public Map<Menu, Integer> getOrderMenus() {
        return orderMenus;
    }

    public DiscountInfo discount(DiscountInfo discountInfo, VisitDate visitDate) {
        List<DiscountResult> discountResults = orderMenus.entrySet().stream()
                .flatMap(orderMenu ->
                        Discount.discount(visitDate, orderMenu.getKey(), orderMenu.getValue()).stream())
                .collect(Collectors.toList());

        return discountInfo.updateDiscountResults(discountResults);


    }

    public int calculatePriceBeforeDiscount() {
        return orderMenus.entrySet().stream()
                .mapToInt(i -> i.getKey().totalMoney(i.getValue()))
                .sum();
    }

    public int getSize() {
        return orderMenus.size();
    }
}
