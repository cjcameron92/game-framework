package com.games.api;

import org.jetbrains.annotations.NotNull;

/**
 * Base component for arenas
 * {@inheritDoc}
 */
public interface Arena {

    /**
     * Function to ge the arenas id
     *
     * @return id
     */
    @NotNull String getId();
}
