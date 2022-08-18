package com.bootcamp.java;

import com.bootcamp.java.model.Card;
import com.bootcamp.java.model.CardPatternType;
import com.bootcamp.java.model.Deck;

import java.util.Arrays;

public class PokerGame {
    private final PatternDetector patternDetector;
    private final Deck deck;

    public PokerGame(PatternDetector patternDetector, Deck deck) {
        this.patternDetector = patternDetector;
        this.deck = deck;
    }

    public static void main(String[] args) {
        PokerGame pokerGame = new PokerGame(
                new PatternDetector(PredicateInitializer.initPokerPredicates()),
                Deck.createDeck());
        System.out.println(pokerGame.start(5));
    }

    public CardPatternType start(int numOfCards) {
        Card[] cards = new Card[numOfCards];

        deck.shuffle();
        for (int i = 0; i < numOfCards; i++) {
            cards[i] = deck.deal();
        }

        System.out.println(Arrays.toString(cards));

        return patternDetector.detect(cards);
    }
}
