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
}
