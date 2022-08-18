package com.bootcamp.java.predicates;

import com.bootcamp.java.model.Card;
import com.bootcamp.java.model.CardPatternType;
import com.bootcamp.java.model.PredicateRequest;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CheatingPredicate implements PatternPredicate {
    @Override
    public boolean detect(PredicateRequest request) {
        Map<Card, Integer> cardCounts = Arrays.stream(request.getCards())
                .collect(Collectors.toMap(Function.identity(), c -> 1, Integer::sum));
        return cardCounts.values().stream().anyMatch(i -> i > 1);
    }

    @Override
    public CardPatternType getType() {
        return CardPatternType.CHEATING;
    }
}
