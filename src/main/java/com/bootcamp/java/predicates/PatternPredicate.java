package com.bootcamp.java.predicates;

import com.bootcamp.java.model.Card;
import com.bootcamp.java.model.CardPatternType;
import com.bootcamp.java.model.PredicateRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface PatternPredicate {
    boolean detect(PredicateRequest request);

    CardPatternType getType();

    static boolean hasFlush(PredicateRequest request) {
        return request.getSuitCounts().values().stream()
                .anyMatch(frequency -> frequency >= 5);
    }

    static boolean hasLargestStraight(Set<Card> cards) {
        return cards.stream()
                .map(card -> card.getRank().getValue())
                .collect(Collectors.toSet())
                .containsAll(LARGEST_STRAIGHT_RANK_VALUES);
    }

    static boolean hasStraight(PredicateRequest request) {
        Set<Integer> uniqueRankValues = request.getRankCounts().keySet().stream()
                .map(Card.Rank::getValue).collect(Collectors.toSet());
        return PatternPredicate._hasStraight(uniqueRankValues);
    }

    static boolean hasStraight(Set<Card> cards) {
        Set<Integer> uniqueRankValues = cards.stream()
                .map(card -> card.getRank().getValue())
                .collect(Collectors.toSet());
        return PatternPredicate._hasStraight(uniqueRankValues);
    }

    private static boolean _hasStraight(Set<Integer> uniqueRankValues) {
        if (uniqueRankValues.containsAll(SMALLEST_STRAIGHT_RANK_VALUES)) {
            return true;
        }
        Integer[] uniqueRankValuesArray = uniqueRankValues.toArray(Integer[]::new);
        Arrays.sort(uniqueRankValuesArray);
        int consecutiveCount = 0;
        for (int i = 1, len = uniqueRankValuesArray.length; i < len; i++) {
            if (uniqueRankValuesArray[i] - uniqueRankValuesArray[i - 1] == 1) {
                consecutiveCount++;
            } else {
                consecutiveCount = 0;
            }
        }
        return consecutiveCount >= 4;
    }

    List<Integer> SMALLEST_STRAIGHT_RANK_VALUES = new ArrayList<>() {{
        add(14);
        add(2);
        add(3);
        add(4);
        add(5);
    }};
    List<Integer> LARGEST_STRAIGHT_RANK_VALUES = new ArrayList<>() {{
        add(14);
        add(13);
        add(12);
        add(11);
        add(10);
    }};
}
