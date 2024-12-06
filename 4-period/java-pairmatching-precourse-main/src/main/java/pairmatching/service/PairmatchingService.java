package pairmatching.service;

import static pairmatching.constant.ExceptionMessage.NO_MATCHING_HISTORY;
import static pairmatching.constant.ExceptionMessage.OVER_THREE_SHUFFLE;
import static pairmatching.constant.PairMatchingRule.TRY_SHUFFLE_COUNT_MAX;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import pairmatching.domain.Team;
import pairmatching.domain.vo.Level;
import pairmatching.dto.FairMatchingRequestDto;
import pairmatching.dto.FairMatchingResponseDto;
import pairmatching.dto.MatchDto;
import pairmatching.repository.CrewRepository;

public class PairmatchingService {
    private final Map<Level, List<Team>> matchRepository = new HashMap<>();
    private final CrewRepository crewRepository;

    public PairmatchingService(final CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    public FairMatchingResponseDto createFairMatch(final FairMatchingRequestDto requestDto) {
        Level level = requestDto.getLevel();
        List<String> crewNames = crewRepository.findAllByCourse(requestDto.getCourse());

        List<List<String>> pairs = createPairs(crewNames, level);
        List<Team> teams = pairs.stream()
                .map(pair -> new Team(level, pair))
                .collect(Collectors.toList());

        matchRepository.put(level, teams);
        return createResponse(teams);
    }

    private List<List<String>> createPairs(List<String> crews, Level level) {
        for (int attempt = 0; attempt < TRY_SHUFFLE_COUNT_MAX; attempt++) {
            List<List<String>> pairs = tryCreatePairs(crews);
            if (!hasMatchedBefore(pairs, level)) {
                return pairs;
            }
        }
        throw new IllegalArgumentException(OVER_THREE_SHUFFLE.getMessage());
    }

    private List<List<String>> tryCreatePairs(List<String> crews) {
        List<String> shuffledCrew = Randoms.shuffle(new ArrayList<>(crews));
        List<List<String>> pairs = new ArrayList<>();

        for (int i = 0; i < shuffledCrew.size() - 1; i += 2) {
            List<String> pair = new ArrayList<>();
            pair.add(shuffledCrew.get(i));
            pair.add(shuffledCrew.get(i + 1));
            if (i == shuffledCrew.size() - 3 && shuffledCrew.size() % 2 == 1) {
                pair.add(shuffledCrew.get(shuffledCrew.size() - 1));
            }

            pairs.add(pair);
        }
        return pairs;
    }

    private boolean hasMatchedBefore(List<List<String>> newPairs, Level level) {
        if (!matchRepository.containsKey(level)) {
            return false;
        }

        List<Team> existingTeams = matchRepository.get(level);
        for (List<String> newPair : newPairs) {
            for (Team existingTeam : existingTeams) {
                if (hasDuplicatePair(newPair, existingTeam.getCrewNames())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasDuplicatePair(List<String> pair, List<String> existingPair) {
        return pair.stream()
                .filter(existingPair::contains)
                .count() >= 2;
    }

    private FairMatchingResponseDto createResponse(List<Team> teams) {
        List<MatchDto> matchDtos = teams.stream()
                .map(team -> new MatchDto(team.getCrewNames()))
                .collect(Collectors.toList());
        return new FairMatchingResponseDto(matchDtos);
    }

    public FairMatchingResponseDto getFair(final FairMatchingRequestDto requestDto) {
        Level level = requestDto.getLevel();
        if (!matchRepository.containsKey(level)) {
            throw new IllegalArgumentException(NO_MATCHING_HISTORY.getMessage());
        }
        return createResponse(matchRepository.get(level));
    }

    public void deleteAll() {
        matchRepository.clear();
    }


    public boolean isAlreadyMatch(Level level) {
        return matchRepository.containsKey(level);
    }

}
