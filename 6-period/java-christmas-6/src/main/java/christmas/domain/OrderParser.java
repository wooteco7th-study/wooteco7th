package christmas.domain;

import java.util.ArrayList;
import java.util.List;

public class OrderParser {

    public static final String ORDER_DELIMITER = ",";
    public static final String MENU_QUANTITY_DELIMITER = "-";

    public static OrderForm parse(VisitDate visitDate, String orders) {
        String[] menuAndQuantity = orders.split(ORDER_DELIMITER);
        List<OrderMenu> orderForm = new ArrayList<>();
        for (String order : menuAndQuantity) {
            String[] data = order.split(MENU_QUANTITY_DELIMITER);
            Menu menu = Menu.from(data[0]);
            Quantity quantity = new Quantity(data[1]);
            OrderMenu orderMenu = new OrderMenu(menu, quantity);
            orderForm.add(orderMenu);
        }
        return new OrderForm(visitDate, orderForm);
    }
}
