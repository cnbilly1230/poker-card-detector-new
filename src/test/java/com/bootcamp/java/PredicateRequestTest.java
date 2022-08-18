package com.bootcamp.java;

import com.bootcamp.java.model.Card;
import com.bootcamp.java.model.PredicateRequest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PredicateRequestTest {
    private static final Card[] TEST_CARDS = new Card[]{
            Card.of(Card.Suit.SPADE, Card.Rank.ACE),
            Card.of(Card.Suit.SPADE, Card.Rank.KING),
            Card.of(Card.Suit.SPADE, Card.Rank.QUEEN),
            Card.of(Card.Suit.SPADE, Card.Rank.JACK),
            Card.of(Card.Suit.SPADE, Card.Rank.TEN),
            Card.of(Card.Suit.HEART, Card.Rank.TEN),
            Card.of(Card.Suit.HEART, Card.Rank.NINE),
            Card.of(Card.Suit.DIAMOND, Card.Rank.KING),
            Card.of(Card.Suit.DIAMOND, Card.Rank.TEN),
            Card.of(Card.Suit.CLUB, Card.Rank.TEN),
    };

    private PredicateRequest request;

    @Test
    public void shouldReturnSuitCount_WhenGivenCards() {
        request = PredicateRequest.of(TEST_CARDS);

        Map<Card.Suit, Integer> expectedSuitCount = new HashMap<>() {{
           put(Card.Suit.SPADE, 5);
           put(Card.Suit.HEART, 2);
           put(Card.Suit.DIAMOND, 2);
           put(Card.Suit.CLUB, 1);
        }};

        assertEquals(expectedSuitCount, request.getSuitCounts());
    }
    @Test
    public void shouldReturnRankCount_WhenGivenCards() {
        request = PredicateRequest.of(TEST_CARDS);

        Map<Card.Rank, Integer> expectedRankCount = new HashMap<>() {{
           put(Card.Rank.TEN, 4);
           put(Card.Rank.KING, 2);
           put(Card.Rank.NINE, 1);
           put(Card.Rank.JACK, 1);
           put(Card.Rank.QUEEN, 1);
           put(Card.Rank.ACE, 1);
        }};

        assertEquals(expectedRankCount, request.getRankCounts());
    }
}
