package pairmatching.view.mapper;

import static pairmatching.constant.ExceptionMessage.INVALID_INPUT_FORM;

import java.util.List;
import pairmatching.domain.vo.FunctionOption;
import pairmatching.domain.vo.RematchOption;
import pairmatching.dto.FairMatchingRequestDto;

public class ModelMapper {

    private ModelMapper() {
    }

    public static FunctionOption toFunctionOption(final String input) {
        String pattern = RequestPattern.createOptionPattern();
        RequestValidator.validateInput(input, pattern, INVALID_INPUT_FORM.getMessage());

        return FunctionOption.toFunctionOption(input);
    }

    public static FairMatchingRequestDto toFaitMatchingDto(String input) {
        String pattern = RequestPattern.createPairMatchDtoPattern();
        RequestValidator.validateInput(input, pattern, INVALID_INPUT_FORM.getMessage());

        List<String> items = RequestParser.parseItemList(input);

        return new FairMatchingRequestDto(items.get(0), items.get(1), items.get(2));
    }


    public static RematchOption toRematchOption(final String input) {
        String pattern = RequestPattern.createOptionPattern();
        RequestValidator.validateInput(input, pattern, INVALID_INPUT_FORM.getMessage());
        return RematchOption.toRematchOption(input);
    }
}
