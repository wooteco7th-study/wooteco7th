package subway.domain.validator;

import static subway.message.ExceptionMessage.CANT_BE_SAME_STATION;
import static subway.message.ExceptionMessage.NO_LINK_FROM_TO;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.vo.ArrivalStation;
import subway.domain.vo.DepartureStation;


public class RouteSearchValidator {
    private RouteSearchValidator() {
    }

    public static void validate(final DepartureStation from, final ArrivalStation to,
                                final WeightedMultigraph<String, DefaultWeightedEdge> graph) {
        notSameValidate(from, to);
        linkValidate(from, to, graph);
    }

    private static void notSameValidate(final DepartureStation from, final ArrivalStation to) {
        if (from.getValue().equals(to.getValue())) {
            throw new IllegalArgumentException(CANT_BE_SAME_STATION.getMessage());
        }
    }

    private static void linkValidate(final DepartureStation from, final ArrivalStation to,
                                     final WeightedMultigraph<String, DefaultWeightedEdge> graph) {
        if (!graph.containsVertex(from.getValue()) || !graph.containsVertex(to.getValue())) {
            throw new IllegalArgumentException(NO_LINK_FROM_TO.getMessage());
        }
    }
}
