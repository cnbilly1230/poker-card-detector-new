package com.bootcamp.java;

import com.bootcamp.java.model.Card;
import com.bootcamp.java.model.CardPatternType;
import com.bootcamp.java.model.PredicateRequest;
import com.bootcamp.java.predicates.PatternPredicate;

public class PatternDetector {
    private final PatternPredicate[] predicates;

    public PatternDetector(PatternPredicate[] predicates) {
        this.predicates = predicates;
    }

    public CardPatternType detect(Card[] cards) {
        PredicateRequest request = PredicateRequest.of(cards);

        for (PatternPredicate predicate : predicates) {
            if (predicate.detect(request)) {
                return predicate.getType();
            }
        }

        return CardPatternType.NO_PATTERN;
    }
}
