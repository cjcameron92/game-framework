package com.games.api;

import gg.habibi.game.api.GameData;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Allows customizable arenas, matches, and phases
 *
 * @param <X> Arena type
 * @param <Y> Match type
 * @param <Z> Phase type
 *
 * {@inheritDoc}
 */
public interface Game<X extends Arena, Y extends Match, Z extends Phase> extends GameData {

    /**
     * Function to get the arena
     *
     * @return arena
     */
    @NotNull X getArena();

    /**
     * Function to get the match
     *
     * @return match
     */
    @NotNull Y getMatch();

    /**
     * Function to get the phases
     *
     * @return phases
     */
    @NotNull List<Z> getPhases();

}
