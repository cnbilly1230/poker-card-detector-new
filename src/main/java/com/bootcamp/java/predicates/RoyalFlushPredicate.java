package com.bootcamp.java.predicates;

import com.bootcamp.java.model.Card;
import com.bootcamp.java.model.CardPatternType;
import com.bootcamp.java.model.PredicateRequest;

import java.util.*;

public class RoyalFlushPredicate implements PatternPredicate {
    @Override
    public boolean detect(PredicateRequest request) {
        // quick check to speed up performance
        if (!(PatternPredicate.hasFlush(request)
                && PatternPredicate.hasStraight(request))) {
            return false;
        }

        Map<Card.Suit, Set<Card>> cardsBySuit = request.getCardsBySuit();

        for (Set<Card> cards : cardsBySuit.values()) {
            // process only if flush
            if (cards.size() >= 5) {
                if (PatternPredicate.hasLargestStraight(cards)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public CardPatternType getType() {
        return CardPatternType.ROYAL_FLUSH;
    }
}

