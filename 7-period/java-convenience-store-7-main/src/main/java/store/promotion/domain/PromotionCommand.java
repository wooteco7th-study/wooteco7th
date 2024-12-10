package store.promotion.domain;

import java.util.Arrays;
import java.util.Objects;
import store.error.AppException;
import store.error.ErrorMessage;

public enum PromotionCommand {
    YES("Y"),
    NO("N");

    private final String command;

    PromotionCommand(final String command) {
        this.command = command;
    }

    public static PromotionCommand findByCommand(final String command) {
        return Arrays.stream(PromotionCommand.values())
                .filter(promotionCommand -> Objects.equals(promotionCommand.command, command))
                .findAny()
                .orElseThrow(() -> new AppException(ErrorMessage.INVALID_INPUT));
    }
}
