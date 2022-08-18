package com.bootcamp.java.predicates;

import com.bootcamp.java.model.Card;
import com.bootcamp.java.model.CardPatternType;
import com.bootcamp.java.model.PredicateRequest;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FullHousePredicate implements PatternPredicate {
    @Override
    public boolean detect(PredicateRequest request) {
        Set<Card.Rank> ranksWithThreeOrMoreOccurrences = request.getRankCounts()
                .entrySet().stream()
                .filter(e -> e.getValue() >= 3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        Set<Card.Rank> ranksWithTwoOrMoreOccurrences = request.getRankCounts()
                .entrySet().stream()
                .filter(e -> e.getValue() >= 2)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        boolean multipleThreeOfAKind = ranksWithThreeOrMoreOccurrences.size() > 1;
        boolean marginalFullHouse = ranksWithThreeOrMoreOccurrences.size() == 1
                && ranksWithTwoOrMoreOccurrences.size() >= 1
                && !ranksWithThreeOrMoreOccurrences.equals(ranksWithTwoOrMoreOccurrences);

        return multipleThreeOfAKind || marginalFullHouse;
    }

    @Override
    public CardPatternType getType() {
        return CardPatternType.FULL_HOUSE;
    }
}
