package com.bootcamp.java.model;

import java.util.*;
import java.util.stream.Collectors;

public class PredicateRequest {
    private final Card[] cards;

    private final Map<Card.Suit, Integer> suitCounts;

    private final Map<Card.Rank, Integer> rankCounts;

    public static PredicateRequest of(Card[] cards) {
        return new PredicateRequest(cards);
    }

    public Card[] getCards() {
        return cards;
    }

    public Map<Card.Suit, Integer> getSuitCounts() {
        return suitCounts;
    }

    public Map<Card.Rank, Integer> getRankCounts() {
        return rankCounts;
    }

    // Not pre-calculated due to rarity
    public Map<Card.Suit, Set<Card>> getCardsBySuit() {
        return Arrays.stream(cards)
                .collect(Collectors.toMap(
                        Card::getSuit,
                        card -> new HashSet<>(List.of(card)),
                        (setOfCards1, setOfCards2) -> {
                            setOfCards1.addAll(setOfCards2);
                            return setOfCards1;
                        }
                ));
    }

    private PredicateRequest(Card[] cards) {
        this.cards = cards;
        this.suitCounts = initSuitCount(cards);
        this.rankCounts = initRankCount(cards);
    }

    private Map<Card.Suit, Integer> initSuitCount(Card[] cards) {
        return Arrays.stream(cards).collect(Collectors.toMap(
                Card::getSuit,
                card -> 1,
                Integer::sum
        ));
    }

    private Map<Card.Rank, Integer> initRankCount(Card[] cards) {
        return Arrays.stream(cards).collect(Collectors.toMap(
                Card::getRank,
                card -> 1,
                Integer::sum
        ));
    }
}
