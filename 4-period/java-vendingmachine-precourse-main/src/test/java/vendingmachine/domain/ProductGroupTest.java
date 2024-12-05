package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.error.ErrorMessage;

class ProductGroupTest {

    @DisplayName("구매할 수 있는 상품이 존재한다.")
    @Test
    void true_hasPurchaseAbleProductTest() {
        //given
        final int money = 500;
        final int quantity = 1;
        final List<Product> products = Arrays.asList(new Product("콜라", 100, 10), new Product("사이다", 100, 10));

        //when
        final ProductGroup productGroup = new ProductGroup(products);
        final boolean result = productGroup.hasPurchaseAbleProduct(money, quantity);

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("금액이 부족하여 구매할 수 있는 상품이 존재하지 않는다.")
    void false_hasPurchaseAbleProductTest() throws Exception {
        //given
        final int money = 10;
        final int quantity = 1;
        final List<Product> products = Arrays.asList(new Product("콜라", 100, 10), new Product("사이다", 100, 10));

        //when
        final ProductGroup productGroup = new ProductGroup(products);
        final boolean result = productGroup.hasPurchaseAbleProduct(money, quantity);

        //then
        assertThat(result).isFalse();
    }


    @Test
    @DisplayName("상품이름이 존재하지 않아 예외가 발생한다.")
    void fail_findByProductNameAndQuantityTest1() {
        //given
        final String name = "물";
        final List<Product> products = Arrays.asList(new Product("콜라", 100, 10), new Product("사이다", 100, 10));

        //when
        final ProductGroup productGroup = new ProductGroup(products);

        //then
        assertThatIllegalArgumentException().isThrownBy(() -> productGroup.findByProductNameAndQuantity(name, 1))
                .withMessageContaining(
                        ErrorMessage.INVALID_CAN_NOT_PURCHASE_PRODUCT.getMessage());
    }

    @Test
    @DisplayName("상품 수량이 부족하여 예외가 발생한다.")
    void fail_findByProductNameAndQuantityTest2() throws Exception {
        //given
        final String name = "콜라";
        final int quantity = 100;
        final List<Product> products = Arrays.asList(new Product("콜라", 100, 10), new Product("사이다", 100, 10));

        //when
        final ProductGroup productGroup = new ProductGroup(products);

        //then
        assertThatIllegalArgumentException().isThrownBy(() -> productGroup.findByProductNameAndQuantity(name, quantity))
                .withMessageContaining(
                        ErrorMessage.INVALID_CAN_NOT_PURCHASE_PRODUCT.getMessage());
    }

    @Test
    @DisplayName("상품이 중복되어 예외가 발생한다.")
    void validateDuplicateTest() throws Exception {
        //given
        final List<Product> products = Arrays.asList(new Product("콜라", 100, 10), new Product("콜라", 100, 10));

        //should
        assertThatIllegalArgumentException().isThrownBy(() -> new ProductGroup(products))
                .withMessageContaining(ErrorMessage.INVALID_DUPLICATED_PRODUCT.getMessage());
    }
}
