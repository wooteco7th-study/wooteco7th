package baseball.infrastructure;

import static baseball.rule.BaseballRule.*;

import java.util.ArrayList;
import java.util.List;

public class BaseBallNumberGenerator {
    private final RandomNumberGenerator randomNumberGenerator;

    public BaseBallNumberGenerator(final int minNumberValue, final int maxNumberValue) {
        this.randomNumberGenerator = new RandomNumberGenerator(minNumberValue,maxNumberValue);
    }

    public final List<Integer> pickUniqueThreeNumbers(){
        List<Integer> computer = new ArrayList<>();
        while (computer.size() < BASEBALL_SIZE.getValue()) {
            int randomNumber = randomNumberGenerator.generate();
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
        return  computer;
    }
}
