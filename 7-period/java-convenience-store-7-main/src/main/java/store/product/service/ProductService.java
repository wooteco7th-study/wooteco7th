package store.product.service;

import java.util.ArrayList;
import java.util.List;
import store.error.AppException;
import store.error.ErrorMessage;
import store.product.domain.Product;
import store.product.domain.PurchaseProductGroup;
import store.product.domain.ProductRepository;
import store.product.domain.ProductType;
import store.product.domain.PurchaseProduct;
import store.product.domain.PurchaseProductQuantity;
import store.product.dto.ProductRequest;
import store.product.dto.ProductResponse;
import store.util.ListValidator;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getProductResponses() {
        final List<ProductResponse> productResponses = new ArrayList<>();
        final List<Product> products = productRepository.findAll();
        for (Product product : products) {
            final ProductResponse productResponse = ProductResponse.of(product);
            productResponses.add(productResponse);
            addNonPromotionProduct(productResponse, productResponses);
        }
        return productResponses;
    }

    private void addNonPromotionProduct(final ProductResponse productResponse,
                                        final List<ProductResponse> productResponses) {
        if (!productRepository.hasNonPromotionProduct(productResponse.name()) && productRepository.hasPromotionProduct(
                productResponse.name())) {
            productResponses.add(ProductResponse.of(
                    new Product(productResponse.name(), ProductType.NON_PROMOTION,0,  productResponse.price(), "")));
        }
    }

    public PurchaseProductGroup generatePurchaseProductGroup(final List<ProductRequest> productRequests) {
        validate(productRequests);
        final List<PurchaseProduct> purchaseProducts = productRequests.stream()
                .map(this::createPurchaseProduct)
                .toList();
        return new PurchaseProductGroup(purchaseProducts);
    }

    private PurchaseProduct createPurchaseProduct(final ProductRequest productRequest) {
        final int promotionQuantity = calculatePromotionQuantity(productRequest);
        int nonPromotionQuantity = 0;
        if (productRequest.quantity() > promotionQuantity) {
            nonPromotionQuantity = productRequest.quantity() - promotionQuantity;
        }
        final PurchaseProductQuantity purchaseProductQuantity = new PurchaseProductQuantity(promotionQuantity,
                nonPromotionQuantity, 0);
        return new PurchaseProduct(productRequest.name(), getPromotionName(productRequest), purchaseProductQuantity);
    }

    private String getPromotionName(final ProductRequest productRequest) {
        if (productRepository.hasPromotionProduct(productRequest.name())) {
            return productRepository.findPromotionProductByProductName(productRequest.name()).getPromotionName();
        }
        return "";
    }

    private int calculatePromotionQuantity(final ProductRequest productRequest) {
        final Product promotionProduct = productRepository.findPromotionProductByProductName(
                productRequest.name());
        return Math.min(promotionProduct.getQuantity(), productRequest.quantity());
    }

    private void validate(final List<ProductRequest> productRequests) {
        ListValidator.validateDuplicate(productRequests, ErrorMessage.INVALID_INPUT);
        validateExist(productRequests);
        validateQuantity(productRequests);
    }

    private void validateExist(final List<ProductRequest> productRequests) {
        if (hasNotProduct(productRequests)) {
            throw new AppException(ErrorMessage.INVALID_INPUT);
        }
    }

    private void validateQuantity(final List<ProductRequest> productRequests) {
        if (hasNotQuantity(productRequests)) {
            throw new AppException(ErrorMessage.INVALID_INPUT);
        }
    }

    private boolean hasNotQuantity(final List<ProductRequest> productRequests) {
        return !productRequests.stream()
                .allMatch(productRequest -> productRepository.countQuantityByProductName(productRequest.name())
                        >= productRequest.quantity());
    }

    private boolean hasNotProduct(final List<ProductRequest> productRequests) {
        return !productRequests.stream()
                .allMatch(productRequest -> productRepository.existByProductName(productRequest.name()));
    }
}
