package pairmatching;

import pairmatching.config.AppConfig;
import pairmatching.controller.PairmatchingController;

public class Application {
    public static void main(String[] args) {
        // TODO 구현 진행
        PairmatchingController controller = new AppConfig().initialize();
        controller.run();
    }
}
