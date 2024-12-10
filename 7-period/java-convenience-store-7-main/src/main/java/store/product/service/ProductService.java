package store.product.service;

import java.util.ArrayList;
import java.util.List;
import store.error.AppException;
import store.error.ErrorMessage;
import store.product.domain.Product;
import store.product.domain.ProductGroup;
import store.product.domain.ProductRepository;
import store.product.dto.ProductRequest;
import store.product.dto.ProductResponse;
import store.util.ListValidator;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getProductResponses() {
        return productRepository.findAll().stream()
                .map(ProductResponse::of)
                .toList();
    }

    public ProductGroup generateProductGroup(final List<ProductRequest> productRequests) {
        validate(productRequests);
        return new ProductGroup(new ArrayList<>());
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
