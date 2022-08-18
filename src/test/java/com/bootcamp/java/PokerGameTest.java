package com.bootcamp.java;

import com.bootcamp.java.model.Card;
import com.bootcamp.java.model.Deck;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class PokerGameTest {
    @Spy
    private Deck deck = Deck.createDeck();

    @Mock
    private PatternDetector patternDetector;

    @Test
    public void shouldDetectPattern_WhenStartPokerGame() {
        List<Card> stubbedCards = new ArrayList<>();
        stubbedCards.add(Card.of(Card.Suit.SPADE, Card.Rank.ACE));
        stubbedCards.add(Card.of(Card.Suit.HEART, Card.Rank.ACE));
        stubbedCards.add(Card.of(Card.Suit.SPADE, Card.Rank.KING));
        stubbedCards.add(Card.of(Card.Suit.SPADE, Card.Rank.JACK));
        stubbedCards.add(Card.of(Card.Suit.SPADE, Card.Rank.QUEEN));

        deck.setCards(new ArrayList<>(stubbedCards));
        Mockito.doNothing().when(deck).shuffle();

        PokerGame pokerGame = new PokerGame(patternDetector, deck);
        pokerGame.start(5);

        Mockito.verify(deck, Mockito.times(5)).deal();

        ArgumentCaptor<Card[]> argumentCaptor = ArgumentCaptor.forClass(Card[].class);
        Mockito.verify(patternDetector).detect(argumentCaptor.capture());

        Card[] actualCards = argumentCaptor.getValue();
        assertArrayEquals(stubbedCards.toArray(Card[]::new), actualCards);
    }
}
