package com.bootcamp.java.model;

import java.util.*;

public class Deck {
    public static final int DEFAULT_DECK_SIZE = 52;

    private static final Comparator<Card> DEFAULT_CARD_ORDER = new Comparator<Card>() {
        @Override
        public int compare(Card o1, Card o2) {
            int result = o2.getSuit().getValue() - o1.getSuit().getValue();
            if (result != 0) {
                return result;
            }
            if (o1.getRank() == Card.Rank.ACE) {
                return -1;
            }
            if (o2.getRank() == Card.Rank.ACE) {
                return 1;
            }
            return o1.getRank().getValue() - o2.getRank().getValue();
        }
    };

    private List<Card> cards;

    public static Deck createDeck() {
        List<Card> cards = new ArrayList<>(DEFAULT_DECK_SIZE);
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cards.add(Card.of(suit, rank));
            }
        }
        cards.sort(DEFAULT_CARD_ORDER);
        return new Deck(cards);
    }

    private Deck(List<Card> cards) {
       this.cards = cards;
    }

    public void reset() {
        cards.sort(DEFAULT_CARD_ORDER);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card deal() {
        if (cards.isEmpty()) {
            throw new NoSuchElementException("Deck is empty");
        }
        return cards.remove(0);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int size() {
        return cards.size();
    }

    public boolean contains(Card card) {
        return cards.contains(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    // for unit testing
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
