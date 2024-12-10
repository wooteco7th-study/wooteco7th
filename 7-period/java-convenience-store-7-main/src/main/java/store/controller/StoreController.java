package store.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import store.domain.stock.Inventory;
import store.domain.promotion.Promotion;
import store.domain.promotion.Promotions;
import store.dto.InventoryDto;
import store.exception.ErrorMessage;
import store.exception.ExceptionHandler;
import store.service.StoreService;
import store.util.FileContentParser;
import store.util.StoreFileReader;
import store.util.StringParser;
import store.view.InputView;
import store.view.OutputView;

public class StoreController {

    private static final String DELIMITER = ",";
    private final InputView inputView;
    private final OutputView outputView;
    private final ExceptionHandler exceptionHandler;
    private final StoreService storeService;

    public StoreController(final InputView inputView, final OutputView outputView,
                           final ExceptionHandler exceptionHandler,
                           final StoreService storeService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.exceptionHandler = exceptionHandler;
        this.storeService = storeService;
    }

    public void process() {
        // 보유 상품 출력 기능 구현
        outputView.showTitleWelcome();
        Promotions promotions = makePromotions();
        Inventory inventory = initializeInventory(promotions);
        List<InventoryDto> dtos = storeService.convertToInventoryDtos(inventory);
        outputView.showInventory(dtos);
    }

    private Inventory initializeInventory(final Promotions promotions) {
        Inventory inventory = new Inventory(new HashMap<>());
        List<String> inputInventories = readInventories();
        for (String inputInventory : inputInventories) {
            List<String> tokens = StringParser.parseByDelimiter(inputInventory, DELIMITER);
            inventory.put(tokens.get(0),
                    StringParser.parseToInteger(tokens.get(1), ErrorMessage.INVALID_INPUT),
                    StringParser.parseToInteger(tokens.get(2), ErrorMessage.INVALID_INPUT),
                    promotions.findByName(tokens.get(3)));
        }
        return inventory;
    }

    private Promotions makePromotions() {
        List<String> inputPromotions = readPromotions();
        Promotions promotions = new Promotions(inputPromotions.stream()
                .map(this::makePromotion)
                .toList());
        return promotions;
    }

    private Promotion makePromotion(final String inputPromotion) {
        List<String> tokens = StringParser.parseByDelimiter(inputPromotion, DELIMITER);
        return new Promotion(tokens.get(0), StringParser.parseToInteger(tokens.get(1), ErrorMessage.INVALID_INPUT),
                StringParser.parseToInteger(tokens.get(2), ErrorMessage.INVALID_INPUT),
                LocalDate.parse(tokens.get(3)), LocalDate.parse(tokens.get(4)));
    }

    private List<String> readPromotions() {
        List<String> inputPromotions = StoreFileReader.readPromotions();
        return FileContentParser.removeHeaders(inputPromotions);
    }

    private List<String> readInventories() {
        List<String> inputInventories = StoreFileReader.readInventories();
        return FileContentParser.removeHeaders(inputInventories);
    }
}
