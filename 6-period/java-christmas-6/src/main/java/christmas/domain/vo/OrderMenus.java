package christmas.domain.vo;

import christmas.domain.Discount;
import christmas.domain.DiscountInfo;
import christmas.domain.DiscountResult;
import christmas.rule.ErrorMessage;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderMenus {
    private final Map<Menu, Integer> orderMenus;

    public OrderMenus(final Map<Menu, Integer> orderMenus) {
        validateNoDuplicateMenus(orderMenus);
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

    private void validateNoDuplicateMenus(final Map<Menu, Integer> orderMenus) {
        Set<Menu> uniqueMenus = orderMenus.keySet();
        Set<String> uniqueMenuNames = uniqueMenus.stream()
                .map(Menu::getMenuName)
                .collect(Collectors.toSet());

        if (uniqueMenus.size() != uniqueMenuNames.size()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DUPLICATE_MENU.getMessage());
        }
    }

}
