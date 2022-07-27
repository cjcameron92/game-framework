package com.games.api;

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
public interface Game<X extends Arena, Y extends Match<?>, Z extends Phase> {

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

    /**
     * Function to check if the game is enabled
     *
     * @return enabled
     */
    boolean isEnabled();

    /**
     * Method to set the enabled state
     *
     * @param b state
     */
    void setEnabled(boolean b);
}
