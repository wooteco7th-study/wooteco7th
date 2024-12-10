package pairmatching.config;

import pairmatching.controller.PairmatchingController;
import pairmatching.repository.CrewRepository;
import pairmatching.service.PairmatchingService;
import pairmatching.view.RequestView;
import pairmatching.view.ResponseView;
import pairmatching.view.io.ConsoleInputView;
import pairmatching.view.io.ConsoleOutputView;

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
    private PairmatchingService createService() {
        return new PairmatchingService(new CrewRepository());
    }

    public PairmatchingController initialize() {
        ConsoleInputView inputView = createInputView();
        ConsoleOutputView outputView = createOutputView();
        RequestView requestView = createRequestView(inputView, outputView);
        ResponseView responseView = createResponseView(outputView);

        PairmatchingService service = createService();
        return new PairmatchingController(requestView, responseView, service);
    }
}
