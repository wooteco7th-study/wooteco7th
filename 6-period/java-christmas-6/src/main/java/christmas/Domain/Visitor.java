package christmas.Domain;

public class Visitor {

    private final VisitDate visitDate;
    private final Orders orders;

    public Visitor(VisitDate visitDate, Orders orders) {
        this.visitDate = visitDate;
        this.orders = orders;
    }
}
