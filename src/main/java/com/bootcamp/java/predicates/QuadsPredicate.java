package com.bootcamp.java.predicates;

import com.bootcamp.java.model.CardPatternType;
import com.bootcamp.java.model.PredicateRequest;

public class QuadsPredicate implements PatternPredicate {
    @Override
    public boolean detect(PredicateRequest request) {
        return request.getRankCounts().values().stream()
                .anyMatch(frequency -> frequency >= 4);
    }

    @Override
    public CardPatternType getType() {
        return CardPatternType.QUADS;
    }
}
