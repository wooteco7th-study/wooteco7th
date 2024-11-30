package bridge.domain;

import java.util.List;

public class BridgeGenerator {

    private BridgeGenerator() {
        
    }

    public static Bridge generate(final BridgeLength bridgeLength) {
        final BridgeRandomNumberGenerator bridgeRandomNumberGenerator = new BridgeRandomNumberGenerator();
        final BridgeMaker bridgeMaker = new BridgeMaker(bridgeRandomNumberGenerator);
        final List<String> bridge = bridgeMaker.makeBridge(bridgeLength.getValue());
        return new Bridge(bridge);
    }
}
