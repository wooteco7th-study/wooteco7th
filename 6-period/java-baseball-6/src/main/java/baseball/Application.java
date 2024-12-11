package baseball;

import baseball.config.Config;
import baseball.controller.Controller;
import camp.nextstep.edu.missionutils.Console;

public class Application {

    public static void main(String[] args) {
        Config config = new Config();
        Controller controller = config.createController();
        try {
            controller.process();
        } finally {
            Console.close();
        }
    }
}
