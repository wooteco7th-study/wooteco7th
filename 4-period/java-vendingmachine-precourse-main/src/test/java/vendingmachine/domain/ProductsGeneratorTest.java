package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductsGeneratorTest {

    @Test
    @DisplayName("상품 정보 문자열을 이용하여 상품 리스트를 생성한다. ")
    void generateTest() throws Exception {
        //given
        final List<String> inputs = List.of("[콜라,100,1]","[사이다,100,1]");

        //when
        final List<Product> products = ProductsGenerator.generate(inputs);

        //then
        assertAll(
                () -> assertThat(products).contains(new Product("콜라",100, 1)),
                () ->assertThat(products).contains(new Product("사이다", 100, 1))
        );
    }

}
