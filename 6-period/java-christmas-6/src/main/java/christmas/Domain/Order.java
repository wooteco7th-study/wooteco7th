package christmas.Domain;

public class Order {

    private final String menu;
    private final int count;

    public Order(String menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return menu + " " + count + "개"; //format 설정 필요
    }
}
