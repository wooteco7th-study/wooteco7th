package subway.domain.weight;

import subway.domain.route.SectionType;

@FunctionalInterface
public interface WeightFunction {

    int getWeight(SectionType sectionType);
}
