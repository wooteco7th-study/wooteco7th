package store.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import store.domain.command.Answer;
import store.domain.order.Order;
import store.domain.order.Orders;
import store.domain.promotion.ProcessType;
import store.domain.promotion.Promotion;
import store.domain.promotion.Promotions;
import store.dto.ReceiptResultDto;
import store.dto.ResultDto;
import store.domain.stock.Inventory;
import store.domain.stock.Stocks;
import store.dto.InventoryDto;
import store.dto.ReceiptProductDto;
import store.exception.CustomIllegalArgumentException;
import store.exception.ErrorMessage;
import store.exception.ExceptionHandler;
import store.service.StoreService;
import store.util.FileContentParser;
import store.util.InputValidator;
import store.util.StoreFileReader;
import store.util.StringParser;
import store.view.InputView;
import store.view.OutputView;

public class StoreController {

    private static final String DELIMITER = ",";
    private static final String REGEX = "^\\[([가-힣a-zA-z]+)-([1-9]\\d*)\\]$";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

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
        Inventory inventory = processInventory();
        // 구매할 상품명과 수량 입력
        Orders orders = makeOrders(inventory);
        // 프로모션에 따른 안내
        List<ResultDto> dtos = processOrderWithResult(inventory, orders);
        ReceiptResultDto receiptResultDto = processMembership(dtos);
        outputView.showReceipt(ReceiptProductDto.of(dtos), receiptResultDto);
    }

    private ReceiptResultDto processMembership(final List<ResultDto> dtos) {
        outputView.showRequestMembership();
        if (isYes()) {
            return storeService.convertToReceiptResultDtoWithMembership(dtos);
        }
        return storeService.convertToReceiptResultDto(dtos);
    }

    private List<ResultDto> processOrderWithResult(final Inventory inventory, final Orders orders) {
        return orders.getOrders().stream()
                .map(order -> processOrderWithInventory(inventory, order))
                .toList();
    }

    private ResultDto processOrderWithInventory(final Inventory inventory, final Order order) {
        Stocks stocks = inventory.get(order.getName());
        ResultDto resultDto = storeService.processOrder(order, stocks);
        if (doesGuideNeeded(resultDto)) {
            return guide(resultDto, stocks);
        }
        return resultDto;
    }

    private ResultDto guide(final ResultDto resultDto, final Stocks stocks) {
        if (resultDto.type() == ProcessType.MIXED) {
            return guideIfMixed(resultDto, stocks);
        }
        if (resultDto.type() == ProcessType.CAN_GIFT) {
            return guideIfGift(resultDto, stocks);
        }
        return resultDto;
    }

    private ResultDto guideIfGift(final ResultDto resultDto, final Stocks stocks) {
        outputView.showRequestGift();
        if (isYes()) {
            return storeService.addGiftQuantity(resultDto, stocks);
        }
        return storeService.continuePurchase(resultDto, stocks);
    }

    private ResultDto guideIfMixed(final ResultDto resultDto, final Stocks stocks) {
        outputView.showRequestRegularPrice();
        if (isYes()) {
            return storeService.processMixedPurchase(resultDto, stocks);
        }
        return storeService.excludeRegularPurchaseQuantity(resultDto, stocks);
    }

    private boolean isYes() {
        return exceptionHandler.retryUntilSuccess(() -> Answer.from(inputView.readAnswer()).isYes());
    }

    private boolean doesGuideNeeded(final ResultDto resultDto) {
        return resultDto.type().doesGuideNeeded();
    }

    private Orders makeOrders(final Inventory inventory) {
        outputView.showRequestOrder();
        return exceptionHandler.retryUntilSuccess(() -> new Orders(
                inputView.readOrder().stream()
                        .map(token -> makeOrder(token, inventory))
                        .toList()));
    }

    private Order makeOrder(final String token, final Inventory inventory) {
        boolean isInvalidPattern = InputValidator.isInvalidPattern(token, PATTERN);
        if (isInvalidPattern) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_ORDER_FORMAT);
        }
        List<String> matchingGroups = StringParser.findMatchingGroups(token, PATTERN);
        String name = matchingGroups.get(0);
        int quantity = StringParser.parseToInteger(matchingGroups.get(1), ErrorMessage.INVALID_INPUT);
        return new Order(name, quantity, inventory);
    }

    private Inventory processInventory() {
        outputView.showTitleWelcome();
        Promotions promotions = makePromotions();
        Inventory inventory = initializeInventory(promotions);
        List<InventoryDto> dtos = storeService.convertToInventoryDtos(inventory);
        outputView.showInventory(dtos);
        return inventory;
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
