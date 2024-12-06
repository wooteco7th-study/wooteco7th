package pairmatching;

import pairmatching.controller.ProgramController;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ProgramController programController = new ProgramController(
                new InputView(),
                new OutputView()
        );
        
        programController.run();
    }
}
