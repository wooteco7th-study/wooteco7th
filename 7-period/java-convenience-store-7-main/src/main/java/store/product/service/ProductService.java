package store.product.service;

import java.util.List;
import store.product.domain.ProductRepository;
import store.product.dto.ProductResponse;

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

}
