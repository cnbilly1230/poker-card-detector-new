package com.bootcamp.java.predicates;

import com.bootcamp.java.model.CardPatternType;
import com.bootcamp.java.model.PredicateRequest;

public class TwoPairPredicate implements PatternPredicate {
    @Override
    public boolean detect(PredicateRequest request) {
        return request.getRankCounts().values().stream()
                .filter(frequency -> frequency >= 2)
                .count() >= 2;
    }

    @Override
    public CardPatternType getType() {
        return CardPatternType.TWO_PAIRS;
    }
}
