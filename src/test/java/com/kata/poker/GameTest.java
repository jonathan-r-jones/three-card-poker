package com.kata.poker;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.junit.Ignore;
import org.junit.Test;

import static com.kata.poker.Card.Suit.*;
import static com.kata.poker.CardBuilder.*;
import static com.kata.poker.GameResult.tie;
import static org.junit.Assert.assertThat;

public class GameTest {

    @Test
    public void highest_card_wins_when_both_hands_have_a_high_card() {
        Player winningPlayer = aPlayerWithHand(sevenOf(Diamonds), fiveOf(Hearts), threeOf(Diamonds));
        Player otherPlayer = aPlayerWithHand(fiveOf(Diamonds), fourOf(Hearts), twoOf(Clubs));

        assertThat(winningPlayer, winsAgainst(otherPlayer));
    }

    @Test
    public void two_hands_with_the_same_high_card_are_tie() {
        Player player = aPlayerWithHand(sevenOf(Hearts), fourOf(Diamonds), twoOf(Clubs));
        Player otherPlayer = aPlayerWithHand(threeOf(Hearts), sevenOf(Diamonds), fiveOf(Clubs));

        assertThat(player, isTieWith(otherPlayer));
    }

    @Test
    public void a_pair_always_wins_against_a_high_card() {
        Player playerWithPair = aPlayerWithHand(threeOf(Diamonds), threeOf(Hearts), twoOf(Clubs));
        Player playerWithHighCard = aPlayerWithHand(fiveOf(Spades), threeOf(Clubs), twoOf(Spades));

        assertThat(playerWithPair, winsAgainst(playerWithHighCard));
    }

    @Test
    public void a_pair_with_highest_cards_wins_against_another_pair() {
        Player winningPlayer = aPlayerWithHand(fiveOf(Spades), fiveOf(Spades), twoOf(Clubs));
        Player otherPlayer = aPlayerWithHand(fourOf(Diamonds), fourOf(Hearts), twoOf(Spades));

        assertThat(winningPlayer, winsAgainst(otherPlayer));
    }

    @Test
    public void two_hands_with_the_same_pair_are_tie_when_they_have_the_same_kicker() {
        Player player = aPlayerWithHand(fiveOf(Spades), fiveOf(Diamonds), twoOf(Clubs));
        Player otherPlayer = aPlayerWithHand(fiveOf(Clubs), fiveOf(Hearts), twoOf(Spades));

        assertThat(player, isTieWith(otherPlayer));
    }

    @Test
    public void when_two_hands_have_the_same_pair_then_the_hand_with_the_highest_kicker_wins() {
        Player winningPlayer = aPlayerWithHand(twoOf(Spades), twoOf(Hearts), sevenOf(Diamonds));
        Player otherPlayer = aPlayerWithHand(twoOf(Diamonds), twoOf(Clubs), sixOf(Diamonds));

        assertThat(winningPlayer, winsAgainst(otherPlayer));
    }

    @Test
    public void a_three_of_a_kind_wins_against_a_high_card() {
        Player playerWithThreeOfKind = aPlayerWithHand(fourOf(Hearts), fourOf(Diamonds), fourOf(Clubs));
        Player playerWithHighCard = aPlayerWithHand(threeOf(Diamonds), fiveOf(Spades), aceOf(Clubs));

        assertThat(playerWithThreeOfKind, winsAgainst(playerWithHighCard));
    }

    @Test
    public void a_three_of_a_kind_wins_against_a_pair() {
        Player playerWithThreeOfKind = aPlayerWithHand(fourOf(Hearts), fourOf(Diamonds), fourOf(Clubs));
        Player playerWithPair = aPlayerWithHand(sevenOf(Clubs), sevenOf(Spades), fiveOf(Clubs));

        assertThat(playerWithThreeOfKind, winsAgainst(playerWithPair));
    }

    @Test
    public void a_three_of_a_kind_with_highest_cards_wins_against_another_three_of_a_kind() {
        Player winningPlayer = aPlayerWithHand(fourOf(Hearts), fourOf(Diamonds), fourOf(Clubs));
        Player otherPlayer = aPlayerWithHand(twoOf(Hearts), twoOf(Spades), twoOf(Clubs));

        assertThat(winningPlayer, winsAgainst(otherPlayer));
    }

    @Test
    public void a_straight_always_wins_against_a_high_card() {
        Player playerWithStraight = aPlayerWithHand(fourOf(Hearts), threeOf(Diamonds), twoOf(Clubs));
        Player playerWithHighCard = aPlayerWithHand(threeOf(Diamonds), fiveOf(Spades), aceOf(Clubs));

        assertThat(playerWithStraight, winsAgainst(playerWithHighCard));
    }

    @Test
    public void a_straight_always_wins_against_a_pair() {
        Player playerWithStraight = aPlayerWithHand(fourOf(Hearts), threeOf(Diamonds), twoOf(Diamonds));
        Player playerWithPair = aPlayerWithHand(sevenOf(Clubs), sevenOf(Spades), fiveOf(Clubs));

        assertThat(playerWithStraight, winsAgainst(playerWithPair));
    }

    @Test
    public void a_straight_always_wins_against_a_three_of_kind() {
        Player playerWithStraight = aPlayerWithHand(fourOf(Hearts), threeOf(Diamonds), twoOf(Diamonds));
        Player playerWithThreeOfKind = aPlayerWithHand(sevenOf(Clubs), sevenOf(Spades), sevenOf(Diamonds));

        assertThat(playerWithStraight, winsAgainst(playerWithThreeOfKind));
    }

    @Test
    public void two_hands_with_the_same_straight_are_tie() {
        Player player = aPlayerWithHand(fiveOf(Spades), fourOf(Hearts), threeOf(Clubs));
        Player otherPlayer = aPlayerWithHand(fourOf(Hearts), fiveOf(Clubs), threeOf(Spades));

        assertThat(player, isTieWith(otherPlayer));
    }

    @Test
    public void highest_card_wins_when_both_hands_have_a_straight() {
        Player winningPlayer = aPlayerWithHand(fourOf(Hearts), fiveOf(Spades), sixOf(Diamonds));
        Player otherPlayer = aPlayerWithHand(threeOf(Diamonds), fourOf(Clubs), fiveOf(Diamonds));

        assertThat(winningPlayer, winsAgainst(otherPlayer));
    }

    @Test
    public void a_flush_always_wins_against_a_high_card() {
        Player playerWithFlush = aPlayerWithHand(threeOf(Diamonds), fiveOf(Diamonds), sevenOf(Diamonds));
        Player playerWithHighCard = aPlayerWithHand(threeOf(Hearts), sevenOf(Clubs), aceOf(Clubs));

        assertThat(playerWithFlush, winsAgainst(playerWithHighCard));
    }

    @Test
    public void a_flush_always_wins_against_a_pair() {
        Player playerWithFlush = aPlayerWithHand(threeOf(Diamonds), fiveOf(Diamonds), sevenOf(Diamonds));
        Player playerWithPair = aPlayerWithHand(threeOf(Hearts), threeOf(Spades), aceOf(Clubs));

        assertThat(playerWithFlush, winsAgainst(playerWithPair));
    }

    @Test
    public void a_flush_always_wins_against_a_three_of_a_kind() {
        Player playerWithFlush = aPlayerWithHand(threeOf(Diamonds), fiveOf(Diamonds), sevenOf(Diamonds));
        Player playerWithThreeOfKind = aPlayerWithHand(threeOf(Hearts), threeOf(Spades), threeOf(Clubs));

        assertThat(playerWithFlush, winsAgainst(playerWithThreeOfKind));
    }

    @Test
    public void two_hands_with_the_same_flush_are_tie() {
        Player player = aPlayerWithHand(fourOf(Spades), sevenOf(Spades), aceOf(Spades));
        Player otherPlayer = aPlayerWithHand(fourOf(Diamonds), sevenOf(Diamonds), aceOf(Diamonds));

        assertThat(player, isTieWith(otherPlayer));
    }

    @Test
    public void a_flush_always_wins_against_a_straight() {
        Player playerWithFlush = aPlayerWithHand(twoOf(Diamonds), threeOf(Diamonds), fiveOf(Diamonds));
        Player playerWithStraight = aPlayerWithHand(threeOf(Hearts), fourOf(Spades), fiveOf(Clubs));

        assertThat(playerWithFlush, winsAgainst(playerWithStraight));
    }

    @Test
    public void highest_card_wins_when_both_hands_have_a_flush() {
        Player winningPlayer = aPlayerWithHand(fourOf(Spades), sevenOf(Spades), aceOf(Spades));
        Player otherPlayer = aPlayerWithHand(threeOf(Diamonds), fiveOf(Diamonds), sevenOf(Diamonds));

        assertThat(winningPlayer, winsAgainst(otherPlayer));
    }

    @Test
    public void a_straight_flush_always_wins_against_a_high_card() {
        Player playerWithStraightFlush = aPlayerWithHand(threeOf(Diamonds), fourOf(Diamonds), twoOf(Diamonds));
        Player playerWithHighCard = aPlayerWithHand(fourOf(Clubs), sevenOf(Hearts), aceOf(Diamonds));

        assertThat(playerWithStraightFlush, winsAgainst(playerWithHighCard));
    }

    @Test
    public void a_straight_flush_always_wins_against_a_pair() {
        Player playerWithStraightFlush = aPlayerWithHand(twoOf(Diamonds), threeOf(Diamonds), fourOf(Diamonds));
        Player playerWithPair = aPlayerWithHand(fourOf(Clubs), fourOf(Hearts), sevenOf(Hearts));

        assertThat(playerWithStraightFlush, winsAgainst(playerWithPair));
    }

    @Test
    public void a_straight_flush_always_wins_against_a_three_of_kind() {
        Player playerWithStraightFlush = aPlayerWithHand(twoOf(Diamonds), threeOf(Diamonds), fourOf(Diamonds));
        Player playerWithThreeOfKind = aPlayerWithHand(fourOf(Clubs), fourOf(Hearts), fourOf(Spades));

        assertThat(playerWithStraightFlush, winsAgainst(playerWithThreeOfKind));
    }

    @Test
    public void a_straight_flush_always_wins_against_a_straight() {
        Player playerWithStraightFlush = aPlayerWithHand(twoOf(Diamonds), threeOf(Diamonds), fourOf(Diamonds));
        Player playerWithStraight = aPlayerWithHand(fourOf(Clubs), fiveOf(Diamonds), sixOf(Hearts));

        assertThat(playerWithStraightFlush, winsAgainst(playerWithStraight));
    }

    @Test
    public void a_straight_flush_always_wins_against_a_flush() {
        Player playerWithStraightFlush = aPlayerWithHand(twoOf(Diamonds), threeOf(Diamonds), fourOf(Diamonds));
        Player playerWithFlush = aPlayerWithHand(threeOf(Clubs), fiveOf(Clubs), sevenOf(Clubs));

        assertThat(playerWithStraightFlush, winsAgainst(playerWithFlush));
    }

    @Test
    public void two_hands_with_the_same_straight_flush_are_tie() {
        Player player = aPlayerWithHand(threeOf(Diamonds), fourOf(Diamonds), twoOf(Diamonds));
        Player otherPlayer = aPlayerWithHand(threeOf(Spades), fourOf(Spades), twoOf(Spades));

        assertThat(player, isTieWith(otherPlayer));
    }

    @Test
    public void highest_card_wins_when_both_hands_have_a_straight_flush() {
        Player winningPlayer = aPlayerWithHand(fourOf(Spades), fiveOf(Spades), threeOf(Spades));
        Player otherPlayer = aPlayerWithHand(threeOf(Diamonds), fourOf(Diamonds), twoOf(Diamonds));

        assertThat(winningPlayer, winsAgainst(otherPlayer));
    }

    @Test
    public void straight_flush_beats_a_pair() {
        Player player = aPlayerWithHand(threeOf(Diamonds), fourOf(Diamonds), twoOf(Diamonds));
        Player otherPlayer = aPlayerWithHand(fiveOf(Spades), fiveOf(Hearts), twoOf(Spades));

        assertThat(player, winsAgainst(otherPlayer));
    }

    @Test
    public void three_aces_beats_three_kings() {
        Player player = aPlayerWithHand(aceOf(Diamonds), aceOf(Hearts), aceOf(Clubs));
        Player otherPlayer = aPlayerWithHand(kingOf(Spades), kingOf(Hearts), kingOf(Clubs));
        assertThat(player, winsAgainst(otherPlayer));
    }

    // Below here are 3 broken tests. qq

    // Why should this test fail? How can I have 2 ace of diamonds, an illegal hand.
    // So this test passes but should raise an exception.
    @Test
    public void a_pair_beats_high_card() {
        Player player = aPlayerWithHand(aceOf(Diamonds), aceOf(Diamonds), fourOf(Diamonds));
        Player otherPlayer = aPlayerWithHand(fiveOf(Spades), fourOf(Hearts), sevenOf(Spades));
        assertThat(player, winsAgainst(otherPlayer));
    }

    // This is also illegal hand because there are no community cards in 3 card poker
    // so the having the ace of spaded in both hands should raise an exception.
    @Test
    public void three_aces_beats_two_aces() {
        Player player = aPlayerWithHand(aceOf(Diamonds), aceOf(Hearts), aceOf(Spades));
        Player otherPlayer = aPlayerWithHand(kingOf(Spades), kingOf(Hearts), aceOf(Spades));
        assert(player, winsAgainst(otherPlayer));
    }

    // According to mycasinostrategy.com, three of a kind beats a straight in three
    // card poker so this test should pass but it fails.
    @Test
    public void three_of_a_kind_beats_a_straight() {
        Player player = aPlayerWithHand(twoOf(Diamonds), twoOf(Clubs), twoOf(Spades));
        Player otherPlayer = aPlayerWithHand(twoOf(Clubs), threeOf(Spades), fourOf(Clubs));
        assertThat(player, winsAgainst(otherPlayer));
    }

    private Player aPlayerWithHand(Card firstCard, Card secondCard, Card thirdCard) {
        return new Player("a name", new Hand(firstCard, secondCard, thirdCard));
    }

    private Matcher<Player> winsAgainst(Player player) {
        return new TypeSafeDiagnosingMatcher<Player>() {
            private final Game game = new Game(new GameRules());
            private Winner winner;

            @Override
            public void describeTo(Description description) {
                description.appendText("winner to be " + winner);
            }

            @Override
            protected boolean matchesSafely(Player winningPlayer, Description mismatchDescription) {
                winner = new Winner(winningPlayer);
                GameResult firstResult = game.play(player, winningPlayer);
                GameResult secondResult = game.play( winningPlayer, player);

//                try {
//
//                }

                if (!firstResult.equals(winner)) {
                    mismatchDescription.appendText("was " + firstResult);
                    return false;
                }
                if (!secondResult.equals(winner)) {
                    mismatchDescription.appendText("was " + secondResult);
                    return false;
                }
                return true;
            }
        };
    }

    private Matcher<Player> isTieWith(Player otherPlayer) {
        return new TypeSafeDiagnosingMatcher<Player>() {
            private final Game game = new Game(new GameRules());

            @Override
            public void describeTo(Description description) {
                description.appendText("result to be a tie");
            }

            @Override
            protected boolean matchesSafely(Player player, Description mismatchDescription) {
                GameResult result = game.play(player, otherPlayer);

                if (!result.equals(tie)) {
                    mismatchDescription.appendText("was " + result);
                    return false;
                }

                return true;
            }
        };
    }

}
