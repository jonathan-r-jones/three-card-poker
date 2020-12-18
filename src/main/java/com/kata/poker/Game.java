package com.kata.poker;

import java.util.HashSet;

public class Game {

    private final GameRules rules;

    public Game(GameRules rules) {
        this.rules = rules;
    }

        GameResult play(Player firstPlayer, Player secondPlayer) {
        HashSet<Card> sharedCards = new HashSet<>();
        for(int i=0; i<3; i++) {
            sharedCards.add(firstPlayer.hand.getCards().get(i));
            sharedCards.add(secondPlayer.hand.getCards().get(i));
        }


        Rank rank = rules.evaluate(firstPlayer.hand);
        Rank otherRank = rules.evaluate(secondPlayer.hand);

        if (rank.higherThan(otherRank)) {
            return new Winner(firstPlayer);
        }

        if (otherRank.higherThan(rank)) {
            return new Winner(secondPlayer);
        }

        return GameResult.tie;
    }
}
