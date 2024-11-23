package christmas.Domain;

public class Visitor {

    private final VisitDate visitDate;
    private final Orders orders;

    public Visitor(VisitDate visitDate, Orders orders) {
        this.visitDate = visitDate;
        this.orders = orders;
    }

    public int getTotalPrice() {
        return orders.calculateTotalPrice();
    }

    public int getDessertCount() {
        return orders.countDessert();
    }

    public int getMainCount() {
        return orders.countMain();
    }

    public int getWillVisitDate() {
        return visitDate.visitDate();
    }

    public String getDateName() {
        return visitDate.calculateDate();
    }

    public String getPriceMessage() {
        return String.format("%,d원", getTotalPrice());
    }

    @Override
    public String toString() {
        return orders.toString();
    }
}
