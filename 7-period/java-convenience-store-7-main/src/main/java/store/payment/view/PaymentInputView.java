package store.payment.view;

import camp.nextstep.edu.missionutils.Console;
import store.constant.StoreCommand;

public class PaymentInputView {

    public StoreCommand readStoreCommand() {
        return StoreCommand.findByCommand(readInput());
    }

    private String readInput() {
        return Console.readLine();
    }
}
