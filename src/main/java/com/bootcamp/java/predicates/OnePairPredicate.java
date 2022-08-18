package com.bootcamp.java.predicates;

import com.bootcamp.java.model.CardPatternType;
import com.bootcamp.java.model.PredicateRequest;

public class OnePairPredicate implements PatternPredicate {
    @Override
    public boolean detect(PredicateRequest request) {
        return request.getRankCounts().values().stream()
                .anyMatch(frequency -> frequency >= 2);
    }

    @Override
    public CardPatternType getType() {
        return CardPatternType.ONE_PAIR;
    }
}
