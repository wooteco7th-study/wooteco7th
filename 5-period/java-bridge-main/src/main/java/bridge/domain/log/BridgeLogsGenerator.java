package bridge.domain.log;

public class BridgeLogsGenerator {

    private BridgeLogsGenerator() {
        
    }

    public static BridgeLogGroup generate() {
        return new BridgeLogGroup(
                new UpBridgeLog(),
                new DownBridgeLog()
        );
    }
}
