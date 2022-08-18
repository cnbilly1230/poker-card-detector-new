package com.bootcamp.java.predicates;

import com.bootcamp.java.model.CardPatternType;
import com.bootcamp.java.model.PredicateRequest;

public class ThreeOfAKindPredicate implements PatternPredicate {
    @Override
    public boolean detect(PredicateRequest request) {
        return request.getRankCounts().values().stream()
                .anyMatch(frequency -> frequency >= 3);
    }

    @Override
    public CardPatternType getType() {
        return CardPatternType.THREE_OF_A_KIND;
    }
}
