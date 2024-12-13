package pairmatching.dto;

import pairmatching.domain.order.PairOrder;

public record TotalPairMatchResultDto(PairOrder pairOrder, PairMatchResultDto pairMatchResultDto) {

}
