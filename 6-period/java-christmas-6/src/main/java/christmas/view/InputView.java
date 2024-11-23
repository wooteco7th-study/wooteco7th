package christmas.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

import christmas.view.InputRequestVO.Delimiter;
import christmas.view.InputRequestVO.ProductQuantityRequest;
import christmas.view.InputRequestVO.ProductQuantityRequestParser;
import java.util.List;

public class InputView {

    public List<ProductQuantityRequest> requestOrderMenus() {
        String input = readLine().trim();
        return ProductQuantityRequestParser.toProductQuantities(
                input,
                new Delimiter("", "", ",", "-")
        );
    }

    public int requestVisitDate() {
        return Integer.parseInt(readLine().trim());
    }
}
