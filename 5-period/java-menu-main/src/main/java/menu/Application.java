package menu;

import menu.config.AppConfig;
import menu.controller.MenuController;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        MenuController controller = new AppConfig().initialize();
        controller.run();
    }
}
