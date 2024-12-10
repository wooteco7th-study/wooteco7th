package store.promotion.view;

import camp.nextstep.edu.missionutils.Console;
import store.constant.StoreCommand;

public class PromotionInputView {

    public StoreCommand readStoreCommand() {
        return StoreCommand.findByCommand(readInput());
    }

    private String readInput() {
        return Console.readLine();
    }

}
