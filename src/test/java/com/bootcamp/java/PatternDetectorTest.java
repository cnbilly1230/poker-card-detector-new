package com.bootcamp.java;

import com.bootcamp.java.model.Card;
import com.bootcamp.java.model.CardPatternType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatternDetectorTest {

    private final PatternDetector detector = new PatternDetector(PredicateInitializer.initPokerPredicates());

    @Test
    public void shouldReturnCheating_WhenGivenCards() {
        Card card1 = Card.of(Card.Suit.SPADE, Card.Rank.ACE);
        Card card2 = Card.of(Card.Suit.SPADE, Card.Rank.ACE);
        Card[] cards = new Card[]{card1, card2};
        assertEquals(CardPatternType.CHEATING, detector.detect(cards));
    }

    @Test
    public void shouldReturnOnePair_WhenGivenCards() {
        Card card1 = Card.of(Card.Suit.SPADE, Card.Rank.ACE);
        Card card2 = Card.of(Card.Suit.HEART, Card.Rank.ACE);
        Card[] cards = new Card[]{card1, card2};
        assertEquals(CardPatternType.ONE_PAIR, detector.detect(cards));
    }

    @Test
    public void shouldReturnTwoPairs_WhenGivenCards() {
        Card[] cards = new Card[]{
                Card.of(Card.Suit.SPADE, Card.Rank.ACE),
                Card.of(Card.Suit.HEART, Card.Rank.ACE),
                Card.of(Card.Suit.CLUB, Card.Rank.KING),
                Card.of(Card.Suit.HEART, Card.Rank.KING),
                Card.of(Card.Suit.DIAMOND, Card.Rank.TEN)
        };
        assertEquals(CardPatternType.TWO_PAIRS, detector.detect(cards));
    }

    @Test
    public void shouldReturnThreeOfAKind_WhenGivenCards() {
        Card[] cards = new Card[]{
                Card.of(Card.Suit.SPADE, Card.Rank.ACE),
                Card.of(Card.Suit.HEART, Card.Rank.ACE),
                Card.of(Card.Suit.CLUB, Card.Rank.ACE),
                Card.of(Card.Suit.HEART, Card.Rank.KING),
                Card.of(Card.Suit.DIAMOND, Card.Rank.TEN)
        };
        assertEquals(CardPatternType.THREE_OF_A_KIND, detector.detect(cards));
    }

    @Test
    public void shouldReturnStraight_WhenGivenCards() {
        Card[] smallestStraight = new Card[]{
                Card.of(Card.Suit.CLUB, Card.Rank.ACE),
                Card.of(Card.Suit.HEART, Card.Rank.TWO),
                Card.of(Card.Suit.HEART, Card.Rank.THREE),
                Card.of(Card.Suit.HEART, Card.Rank.FIVE),
                Card.of(Card.Suit.HEART, Card.Rank.FOUR),
                Card.of(Card.Suit.DIAMOND, Card.Rank.KING),
                Card.of(Card.Suit.CLUB, Card.Rank.KING),
        };
        Card[] regularStraight = new Card[]{
                Card.of(Card.Suit.HEART, Card.Rank.TWO),
                Card.of(Card.Suit.CLUB, Card.Rank.THREE),
                Card.of(Card.Suit.HEART, Card.Rank.FIVE),
                Card.of(Card.Suit.HEART, Card.Rank.FOUR),
                Card.of(Card.Suit.HEART, Card.Rank.SIX),
                Card.of(Card.Suit.CLUB, Card.Rank.SIX),
                Card.of(Card.Suit.DIAMOND, Card.Rank.SIX),
        };
        Card[] biggestStraight = new Card[]{
                Card.of(Card.Suit.HEART, Card.Rank.ACE),
                Card.of(Card.Suit.HEART, Card.Rank.KING),
                Card.of(Card.Suit.HEART, Card.Rank.JACK),
                Card.of(Card.Suit.DIAMOND, Card.Rank.TEN),
                Card.of(Card.Suit.HEART, Card.Rank.QUEEN),
                Card.of(Card.Suit.DIAMOND, Card.Rank.QUEEN),
                Card.of(Card.Suit.CLUB, Card.Rank.QUEEN),
        };
        assertEquals(CardPatternType.STRAIGHT, detector.detect(smallestStraight));
        assertEquals(CardPatternType.STRAIGHT, detector.detect(regularStraight));
        assertEquals(CardPatternType.STRAIGHT, detector.detect(biggestStraight));
    }

    @Test
    public void shouldReturnFlush_WhenGivenCards() {
        Card[] cards = new Card[]{
                Card.of(Card.Suit.HEART, Card.Rank.ACE),
                Card.of(Card.Suit.HEART, Card.Rank.THREE),
                Card.of(Card.Suit.HEART, Card.Rank.FIVE),
                Card.of(Card.Suit.HEART, Card.Rank.KING),
                Card.of(Card.Suit.HEART, Card.Rank.TEN)
        };
        assertEquals(CardPatternType.FLUSH, detector.detect(cards));
    }

    @Test
    public void shouldReturnFullHouse_WhenGivenCards() {
        Card[] cards1 = new Card[]{
                Card.of(Card.Suit.HEART, Card.Rank.ACE),
                Card.of(Card.Suit.DIAMOND, Card.Rank.ACE),
                Card.of(Card.Suit.CLUB, Card.Rank.ACE),
                Card.of(Card.Suit.SPADE, Card.Rank.TEN),
                Card.of(Card.Suit.CLUB, Card.Rank.TEN),
                Card.of(Card.Suit.HEART, Card.Rank.TEN)
        };
        Card[] cards2 = new Card[]{
                Card.of(Card.Suit.HEART, Card.Rank.KING),
                Card.of(Card.Suit.DIAMOND, Card.Rank.KING),
                Card.of(Card.Suit.CLUB, Card.Rank.KING),
                Card.of(Card.Suit.SPADE, Card.Rank.THREE),
                Card.of(Card.Suit.CLUB, Card.Rank.THREE),
        };

        assertEquals(CardPatternType.FULL_HOUSE, detector.detect(cards1));
        assertEquals(CardPatternType.FULL_HOUSE, detector.detect(cards2));
    }

    @Test
    public void shouldReturnQuads_WhenGivenCards() {
        Card[] cards = new Card[]{
                Card.of(Card.Suit.SPADE, Card.Rank.ACE),
                Card.of(Card.Suit.HEART, Card.Rank.ACE),
                Card.of(Card.Suit.CLUB, Card.Rank.ACE),
                Card.of(Card.Suit.DIAMOND, Card.Rank.ACE),
                Card.of(Card.Suit.DIAMOND, Card.Rank.TEN)
        };
        assertEquals(CardPatternType.QUADS, detector.detect(cards));
    }

    @Test
    public void shouldReturnStraightFlush_WhenGivenCards() {
        Card[] cards1 = new Card[]{
                Card.of(Card.Suit.HEART, Card.Rank.TWO),
                Card.of(Card.Suit.HEART, Card.Rank.THREE),
                Card.of(Card.Suit.DIAMOND, Card.Rank.THREE),
                Card.of(Card.Suit.HEART, Card.Rank.FOUR),
                Card.of(Card.Suit.DIAMOND, Card.Rank.FOUR),
                Card.of(Card.Suit.HEART, Card.Rank.FIVE),
                Card.of(Card.Suit.CLUB, Card.Rank.FIVE),
                Card.of(Card.Suit.HEART, Card.Rank.SIX),
                Card.of(Card.Suit.SPADE, Card.Rank.SIX),
                Card.of(Card.Suit.DIAMOND, Card.Rank.SIX),
        };
        Card[] cards2 = new Card[]{
                Card.of(Card.Suit.HEART, Card.Rank.ACE),
                Card.of(Card.Suit.HEART, Card.Rank.THREE),
                Card.of(Card.Suit.HEART, Card.Rank.FIVE),
                Card.of(Card.Suit.HEART, Card.Rank.KING),
                Card.of(Card.Suit.HEART, Card.Rank.TEN),
                Card.of(Card.Suit.SPADE, Card.Rank.NINE),
                Card.of(Card.Suit.SPADE, Card.Rank.JACK),
                Card.of(Card.Suit.SPADE, Card.Rank.TEN),
                Card.of(Card.Suit.SPADE, Card.Rank.QUEEN),
                Card.of(Card.Suit.SPADE, Card.Rank.KING),
        };
        assertEquals(CardPatternType.STRAIGHT_FLUSH, detector.detect(cards1));
        assertEquals(CardPatternType.STRAIGHT_FLUSH, detector.detect(cards2));
    }
    @Test
    public void shouldReturnRoyalFlush_WhenGivenCards() {
        Card[] cards1 = new Card[]{
                Card.of(Card.Suit.SPADE, Card.Rank.ACE),
                Card.of(Card.Suit.SPADE, Card.Rank.KING),
                Card.of(Card.Suit.SPADE, Card.Rank.QUEEN),
                Card.of(Card.Suit.HEART, Card.Rank.JACK),
                Card.of(Card.Suit.DIAMOND, Card.Rank.JACK),
                Card.of(Card.Suit.SPADE, Card.Rank.JACK),
                Card.of(Card.Suit.CLUB, Card.Rank.TEN),
                Card.of(Card.Suit.SPADE, Card.Rank.TEN),
                Card.of(Card.Suit.SPADE, Card.Rank.SIX),
                Card.of(Card.Suit.DIAMOND, Card.Rank.SIX),
        };
        Card[] cards2 = new Card[]{
                Card.of(Card.Suit.HEART, Card.Rank.ACE),
                Card.of(Card.Suit.HEART, Card.Rank.JACK),
                Card.of(Card.Suit.HEART, Card.Rank.TEN),
                Card.of(Card.Suit.HEART, Card.Rank.QUEEN),
                Card.of(Card.Suit.HEART, Card.Rank.KING),
                Card.of(Card.Suit.DIAMOND, Card.Rank.ACE),
                Card.of(Card.Suit.DIAMOND, Card.Rank.JACK),
                Card.of(Card.Suit.DIAMOND, Card.Rank.TEN),
                Card.of(Card.Suit.DIAMOND, Card.Rank.QUEEN),
                Card.of(Card.Suit.DIAMOND, Card.Rank.KING),
        };
        assertEquals(CardPatternType.ROYAL_FLUSH, detector.detect(cards1));
        assertEquals(CardPatternType.ROYAL_FLUSH, detector.detect(cards2));
    }
}
