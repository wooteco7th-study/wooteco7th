package racingcar.domain;

@FunctionalInterface
public interface NumberGenerator {

    int generate(final int min, final int max);
}
