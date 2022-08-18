package com.bootcamp.java;

import com.bootcamp.java.predicates.*;

public class PredicateInitializer {
    public static PatternPredicate[] initPokerPredicates() {
        return new PatternPredicate[]{
                new CheatingPredicate(),
                new RoyalFlushPredicate(),
                new StraightFlushPredicate(),
                new QuadsPredicate(),
                new FullHousePredicate(),
                new FlushPredicate(),
                new StraightPredicate(),
                new ThreeOfAKindPredicate(),
                new TwoPairPredicate(),
                new OnePairPredicate()
        };
    }
}
