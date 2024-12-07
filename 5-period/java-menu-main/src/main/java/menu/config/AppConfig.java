package menu.config;

import menu.controller.MenuController;
import menu.repository.CoachRepository;
import menu.service.MenuRecommendGenerator;
import menu.service.MenuService;
import menu.view.RequestView;
import menu.view.ResponseView;
import menu.view.io.ConsoleInputView;
import menu.view.io.ConsoleOutputView;

public class AppConfig {
    private ConsoleInputView createInputView() {
        return new ConsoleInputView();
    }

    private ConsoleOutputView createOutputView() {
        return new ConsoleOutputView();
    }

    private RequestView createRequestView(ConsoleInputView inputView, ConsoleOutputView outputView) {
        return new RequestView(inputView, outputView);
    }

    private ResponseView createResponseView(ConsoleOutputView outputView) {
        return new ResponseView(outputView);
    }

    // service
    private MenuService createService() {
        return new MenuService(
                new CoachRepository(),
                new MenuRecommendGenerator()
        );
    }

    public MenuController initialize() {
        ConsoleInputView inputView = createInputView();
        ConsoleOutputView outputView = createOutputView();
        RequestView requestView = createRequestView(inputView, outputView);
        ResponseView responseView = createResponseView(outputView);

        MenuService service = createService();
        return new MenuController(requestView, responseView, service);
    }
}
