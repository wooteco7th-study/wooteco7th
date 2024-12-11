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
import store.domain.stock.Inventory;
import store.domain.stock.Stocks;
import store.dto.InventoryDto;
import store.dto.ReceiptProductDto;
import store.dto.ReceiptResultDto;
import store.dto.PurchaseStateDto;
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
        Inventory inventory = processInventory();
        do {
            showInventory(inventory);
            Orders orders = makeOrders(inventory);
            List<PurchaseStateDto> dtos = processOrderWithResult(inventory, orders);
            ReceiptResultDto receiptResultDto = processMembership(dtos);
            outputView.showReceipt(ReceiptProductDto.of(dtos), receiptResultDto);
        } while (shouldContinue());
    }

    private void showInventory(final Inventory inventory) {
        List<InventoryDto> dtos = storeService.convertToInventoryDtos(inventory);
        outputView.showInventory(dtos);
    }

    private boolean shouldContinue() {
        outputView.showRequestRetry();
        boolean isYes = isYes();
        outputView.showBlank();
        return isYes;
    }

    private ReceiptResultDto processMembership(final List<PurchaseStateDto> dtos) {
        outputView.showRequestMembership();
        if (isYes()) {
            return storeService.convertToReceiptResultDtoWithMembership(dtos);
        }
        return storeService.convertToReceiptResultDto(dtos);
    }

    private List<PurchaseStateDto> processOrderWithResult(final Inventory inventory, final Orders orders) {
        return orders.getOrders().stream()
                .map(order -> processOrderWithInventory(inventory, order))
                .toList();
    }

    private PurchaseStateDto processOrderWithInventory(final Inventory inventory, final Order order) {
        Stocks stocks = inventory.get(order.getName());
        PurchaseStateDto purchaseStateDto = storeService.processOrder(order, stocks);
        if (doesGuideNeeded(purchaseStateDto)) {
            return guide(purchaseStateDto, stocks);
        }
        return purchaseStateDto;
    }

    private PurchaseStateDto guide(final PurchaseStateDto purchaseStateDto, final Stocks stocks) {
        if (purchaseStateDto.type() == ProcessType.MIXED) {
            return guideIfMixed(purchaseStateDto, stocks);
        }
        if (purchaseStateDto.type() == ProcessType.CAN_GIFT) {
            return guideIfGift(purchaseStateDto, stocks);
        }
        return purchaseStateDto;
    }

    private PurchaseStateDto guideIfGift(final PurchaseStateDto purchaseStateDto, final Stocks stocks) {
        outputView.showRequestGift(purchaseStateDto.productName());
        if (isYes()) {
            return storeService.addGiftQuantity(purchaseStateDto, stocks);
        }
        return storeService.continuePurchaseWithoutAddingGift(purchaseStateDto, stocks);
    }

    private PurchaseStateDto guideIfMixed(final PurchaseStateDto purchaseStateDto, final Stocks stocks) {
        outputView.showRequestRegularPrice(purchaseStateDto.productName(), purchaseStateDto.regularPurchaseQuantity());
        if (isYes()) {
            return storeService.processMixedPurchase(purchaseStateDto, stocks);
        }
        return storeService.excludeRegularPurchaseQuantity(purchaseStateDto, stocks);
    }

    private boolean isYes() {
        return exceptionHandler.retryUntilSuccess(() -> Answer.from(inputView.readAnswer()).isYes());
    }

    private boolean doesGuideNeeded(final PurchaseStateDto purchaseStateDto) {
        return purchaseStateDto.type().doesGuideNeeded();
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
        return initializeInventory(promotions);
    }

    private Inventory initializeInventory(final Promotions promotions) {
        Inventory inventory = new Inventory(new HashMap<>());
        List<String> inputInventories = readInventories();
        for (String inputInventory : inputInventories) {
            addProductToInventory(promotions, inventory, inputInventory);
        }
        return inventory;
    }

    private void addProductToInventory(final Promotions promotions, final Inventory inventory, final String inputInventory) {
        List<String> tokens = StringParser.parseByDelimiter(inputInventory, DELIMITER);
        inventory.put(tokens.get(0),
                StringParser.parseToInteger(tokens.get(1), ErrorMessage.INVALID_INPUT),
                StringParser.parseToInteger(tokens.get(2), ErrorMessage.INVALID_INPUT),
                promotions.findByName(tokens.get(3)));
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
