package com.bootcamp.java.predicates;

import com.bootcamp.java.model.CardPatternType;
import com.bootcamp.java.model.PredicateRequest;

public class FlushPredicate implements PatternPredicate {
    @Override
    public boolean detect(PredicateRequest request) {
        return PatternPredicate.hasFlush(request);
    }

    @Override
    public CardPatternType getType() {
        return CardPatternType.FLUSH;
    }
}
