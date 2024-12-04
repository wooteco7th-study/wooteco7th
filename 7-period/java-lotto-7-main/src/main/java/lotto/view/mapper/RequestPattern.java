package lotto.view.mapper;

import lotto.view.mapper.PatternBuilder.DataType;

public class RequestPattern {
    private RequestPattern() {
    }

    /*
        사과 -2
     */
    public static String createSingleItemQuantityPattern() {
        return new PatternBuilder()
                .setDataDelimiter("-")
                .addDataType(DataType.STRING)
                .addDataType(DataType.POSITIVE_INTEGER)
                .build();
    }


    /**
     * 입력 예시: "1000" 구매 금액 패턴을 생성합니다.
     */
    public static String createPurchaseAmountPattern() {
        return new PatternBuilder()
                .addDataType(DataType.POSITIVE_INTEGER)
                .build();
    }

    /**
     * 입력 예시: "1,2,3,4,5,6" 당첨 번호 패턴을 생성합니다.
     */
    public static String createWinningNumbersPattern() {
        return new PatternBuilder()
                .setItemDelimiter(",")
                .addDataType(DataType.POSITIVE_INTEGER)
                .build();
    }

    /**
     * 입력 예시: "7" 보너스 번호 패턴을 생성합니다.
     */
    public static String createBonusNumberPattern() {
        return new PatternBuilder()
                .addDataType(DataType.POSITIVE_INTEGER)
                .build();
    }

    /**
     * 입력 예시: "[ 사탕 , 2,1000];[콜라,1,2000];[초코,1,3000]" 아이템 상세 정보(가격 포함) 패턴을 생성합니다.
     */
    public static String createItemDetailsWithPricePattern() {
        return new PatternBuilder()
                .setWrapper("[", "]")
                .setItemDelimiter(";")
                .setDataDelimiter(",")
                .addDataType(DataType.STRING)
                .addDataType(DataType.POSITIVE_INTEGER)
                .addDataType(DataType.POSITIVE_INTEGER)
                .build();
    }

    /**
     * 입력 예시: "[ 사탕 , 2];[ 콜라,1];[초코,1]" 아이템 상세 정보 패턴을 생성합니다.
     */
    public static String createItemDetailsPattern() {
        return new PatternBuilder()
                .setWrapper("[", "]")
                .setItemDelimiter(";")
                .setDataDelimiter(",")
                .addDataType(DataType.STRING)
                .addDataType(DataType.POSITIVE_INTEGER)
                .build();
    }

    /**
     * 입력 예시: "사탕-2,바나나-1,구름-2" 아이템과 수량 패턴을 생성합니다.
     */
    public static String createItemQuantitiesPattern() {
        return new PatternBuilder()
                .setItemDelimiter(",")
                .setDataDelimiter("-")
                .addDataType(DataType.STRING)
                .addDataType(DataType.POSITIVE_INTEGER)
                .build();
    }

    /**
     * 입력 예시: "구름,사탕,족발,고기,사과" 아이템 리스트 패턴을 생성합니다.
     */
    public static String createItemListPattern() {
        return new PatternBuilder()
                .setItemDelimiter(",")
                .addDataType(DataType.STRING)
                .build();
    }

    /**
     * 입력 예시: "2023-10-15" 날짜 패턴을 생성합니다.
     */
    public static String createDatePattern() {
        return new PatternBuilder()
                .addDataType(DataType.LOCAL_DATE)
                .build();
    }

    /**
     * 입력 예시: "user@example.com" 이메일 주소 패턴을 생성합니다.
     */
    public static String createEmailPattern() {
        return new PatternBuilder()
                .addDataType(DataType.EMAIL)
                .build();
    }

    /**
     * 입력 예시: "010-1234-5678" 전화번호 패턴을 생성합니다.
     */
    public static String createPhoneNumberPattern() {
        return new PatternBuilder()
                .addDataType(DataType.PHONE_NUMBER)
                .build();
    }

}
