package vendingmachine.domain.product;

import static org.assertj.core.api.Assertions.assertThat;
import static vendingmachine.support.CustomExceptionAssertions.assertIllegalArgument;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import vendingmachine.domain.price.ProductPrice;
import vendingmachine.exception.ErrorMessage;

class ProductsTest {

    @Test
    @DisplayName("상품이 중복되면 예외가 발생한다.")
    void test() {
        // Given

        // When & Then
        assertIllegalArgument(() -> new Products(List.of(makeCoke(), makeCoke())), ErrorMessage.DUPLICATED_PRODUCT);
    }

    @Test
    @DisplayName("이름으로 상품을 찾는다.")
    void findByName() {
        // Given
        Products products = new Products(List.of(makeCoke(), makeCider()));

        // When
        Product coke = products.findByName("콜라");

        // Then
        assertThat(coke).extracting("name", "price", "quantity")
                .containsExactly("콜라", new ProductPrice(1000), new Quantity(10));
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("모든 상품의 재고가 없는지 확인한다")
    void isAllOutOfStock(Products products, boolean expected) {
        // Given

        // When & Then
        assertThat(products.isAllOutOfStock()).isEqualTo(expected);
    }

    private static Stream<Arguments> isAllOutOfStock() {
        return Stream.of(
                Arguments.of(new Products(List.of(makeCoke(), makeCider())), false),
                Arguments.of(new Products(Collections.emptyList()), true)
        );
    }

    @Test
    @DisplayName("상품을 구매한다.")
    void buy() {
        // Given
        Products products = new Products(List.of(makeCoke(), makeCider()));

        // When
        int productPrice = products.buy(new Product("콜라", new ProductPrice(1000), new Quantity(10)));

        // Then
        assertThat(productPrice).isEqualTo(1000);
    }

    @Test
    @DisplayName("가장 싼 상품의 금액을 보여준다.")
    void getLowestProductPrice() {
        // Given
        Products products = new Products(List.of(makeCoke(), makeCider()));

        // When
        int lowestProductPrice = products.getLowestProductPrice();

        // Then
        assertThat(lowestProductPrice).isEqualTo(1000);
    }

    private static Product makeCoke() {
        return new Product("콜라", new ProductPrice(1000), new Quantity(10));
    }

    private static Product makeCider() {
        return new Product("사이다", new ProductPrice(1500), new Quantity(20));
    }
}
