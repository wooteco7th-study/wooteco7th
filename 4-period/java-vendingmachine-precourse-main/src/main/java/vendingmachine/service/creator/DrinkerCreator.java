package vendingmachine.service.creator;

import vendingmachine.domain.Drinker;
import vendingmachine.util.Convertor;

public class DrinkerCreator {

    private final int money;

    public DrinkerCreator(String input) {
        this.money = Convertor.changeType(input);
    }

    public Drinker create() {
        return new Drinker(money);
    }
}
