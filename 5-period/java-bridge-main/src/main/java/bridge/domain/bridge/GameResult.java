package bridge.domain.bridge;

public enum GameResult {

    성공, 실패;

    public static String from(boolean isSuccess) {
        if (isSuccess) {
            return 성공.name();
        }
        return 실패.name();
    }
}
