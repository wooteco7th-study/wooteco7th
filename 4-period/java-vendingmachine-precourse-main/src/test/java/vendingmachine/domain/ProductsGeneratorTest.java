package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import vendingmachine.error.ErrorMessage;

class ProductsGeneratorTest {

    @Test
    @DisplayName("상품 정보 문자열을 이용하여 상품 리스트를 생성한다. ")
    void generateTest() throws Exception {
        //given
        final List<String> inputs = Arrays.asList("[콜라,100,1]", "[사이다,100,1]");

        //when
        final List<Product> products = ProductsGenerator.generate(inputs);

        //then
        assertAll(
                () -> assertThat(products).contains(new Product("콜라", 100, 1)),
                () -> assertThat(products).contains(new Product("사이다", 100, 1))
        );
    }

    @ParameterizedTest
    @MethodSource("invalidProductFormats")
    @DisplayName("상품 형식이 잘못되어 예외가 발생한다.")
    void validateFormatTest(final List<String> inputs) throws Exception {

        //should
        assertThatIllegalArgumentException().isThrownBy(() -> ProductsGenerator.generate(inputs))
                .withMessageContaining(ErrorMessage.INVALID_PRODUCT_FORMAT.getMessage());

    }

    private static Stream<List<String>> invalidProductFormats() {
        return Stream.of(
                Arrays.asList("[[콜라,100,1]]", "[사이다,100,1]"),
                Arrays.asList("콜라,100,1", "[사이다.100,1]"),
                Arrays.asList("[콜라,100,1", "[사이다.100,1]")
        );
    }

}
