package com.games.demo;

import com.games.api.*;

public class DemoGame extends AbstractGame<Arena, Match<Player>, TickablePhase> {

    public DemoGame(Arena arena, Match<Player> match, TickablePhase... phases) {
        super(arena, match, phases);

        onEnable(() -> {
            /// TODO: 2022-07-27 implement some enabling component for a demo
            match.getPlayers().forEach(player -> player.sendMessage("Game", "Has", "Started"));
        });

        onDisable(() -> {
            /// TODO: 2022-07-27 implement some resetting component
        });
    }

}
