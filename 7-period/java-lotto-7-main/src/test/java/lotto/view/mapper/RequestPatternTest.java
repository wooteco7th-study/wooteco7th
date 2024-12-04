package lotto.view.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

public class RequestPatternTest {

    @Test
    void testCreatePurchaseAmountPattern() {
        String patternString = RequestPattern.createPurchaseAmountPattern();
        Pattern pattern = Pattern.compile(patternString);

        assertThat(pattern.matcher("1000").matches()).isTrue();
        assertThat(pattern.matcher("0").matches()).isFalse();
        assertThat(pattern.matcher("-100").matches()).isFalse();
        assertThat(pattern.matcher("abc").matches()).isFalse();
    }

    @Test
    void testCreateWinningNumbersPattern() {
        String patternString = RequestPattern.createWinningNumbersPattern();
        Pattern pattern = Pattern.compile(patternString);

        assertThat(pattern.matcher("1,2,3,4,5,6").matches()).isTrue();
        assertThat(pattern.matcher("10, 20, 30").matches()).isTrue();
        assertThat(pattern.matcher("1,2,a,4").matches()).isFalse();
        assertThat(pattern.matcher("1,2,,4").matches()).isFalse();
    }

    @Test
    void testCreateBonusNumberPattern() {
        String patternString = RequestPattern.createBonusNumberPattern();
        Pattern pattern = Pattern.compile(patternString);

        assertThat(pattern.matcher("7").matches()).isTrue();
        assertThat(pattern.matcher("0").matches()).isFalse();
        assertThat(pattern.matcher("abc").matches()).isFalse();
    }

    @Test
    void testCreateItemDetailsWithPricePattern() {
        String patternString = RequestPattern.createItemDetailsWithPricePattern();
        Pattern pattern = Pattern.compile(patternString);

        String validInput = "[ 사탕 , 2,1000];[콜라,1,2000];[초코,1,3000]";
        String invalidInput = "[ 사탕 , 2];[콜라,1];[초코,1,abc]";

        assertThat(pattern.matcher(validInput).matches()).isTrue();
        assertThat(pattern.matcher(invalidInput).matches()).isFalse();
    }

    @Test
    void testCreateItemDetailsPattern() {
        String patternString = RequestPattern.createItemDetailsPattern();
        Pattern pattern = Pattern.compile(patternString);

        String validInput = "[ 사탕 , 2];[ 콜라,1];[초코,1]";
        String invalidInput = "[ 사탕 , ];[콜라,1];[초코,abc]";

        assertThat(pattern.matcher(validInput).matches()).isTrue();
        assertThat(pattern.matcher(invalidInput).matches()).isFalse();
    }

    @Test
    void testCreateItemQuantitiesPattern() {
        String patternString = RequestPattern.createItemQuantitiesPattern();
        Pattern pattern = Pattern.compile(patternString);

        String validInput = "사탕-2,바나나-1,구름-2";
        String invalidInput = "사탕-0,바나나--1,구름-abc";

        assertThat(pattern.matcher(validInput).matches()).isTrue();
        assertThat(pattern.matcher(invalidInput).matches()).isFalse();
    }

    @Test
    void testCreateItemListPattern() {
        String patternString = RequestPattern.createItemListPattern();
        Pattern pattern = Pattern.compile(patternString);

        String validInput = "구름,사탕,족발,고기,사과";
        String invalidInput = "구름,사탕,,고기,사과";

        assertThat(pattern.matcher(validInput).matches()).isTrue();
        assertThat(pattern.matcher(invalidInput).matches()).isFalse();
    }

    @Test
    void testCreateDatePattern() {
        String patternString = RequestPattern.createDatePattern();
        Pattern pattern = Pattern.compile(patternString);

        assertThat(pattern.matcher("2023-10-15").matches()).isTrue();
//        assertThat(pattern.matcher("2023-02-29").matches()).isFalse();// 2023년은 윤년이 아님
        assertThat(pattern.matcher("2023-13-01").matches()).isFalse();
        assertThat(pattern.matcher("2023-00-01").matches()).isFalse();
        assertThat(pattern.matcher("2023-1-1").matches()).isFalse();// 월, 일이 2자리여야 함

    }

    @Test
    void testCreateEmailPattern() {
        String patternString = RequestPattern.createEmailPattern();
        Pattern pattern = Pattern.compile(patternString);

        assertThat(pattern.matcher("user@example.com").matches()).isTrue();
        assertThat(pattern.matcher("user.name+tag+sorting@example.co.kr").matches()).isTrue();
        assertThat(pattern.matcher("user@example").matches()).isFalse();
        assertThat(pattern.matcher("user@.com").matches()).isFalse();
    }

    @Test
    void testCreatePhoneNumberPattern() {
        String patternString = RequestPattern.createPhoneNumberPattern();
        Pattern pattern = Pattern.compile(patternString);

//        assertThat(pattern.matcher("+82-10-1234-5678").matches()).isTrue();
        assertThat(pattern.matcher("010-1234-5678").matches()).isTrue();
//        assertThat(pattern.matcher("82-10-1234-5678").matches()).isTrue();
//        assertThat(pattern.matcher("01012345678").matches()).isFalse();
//        assertThat(pattern.matcher("abc-1234-5678").matches()).isFalse();
    }
}
