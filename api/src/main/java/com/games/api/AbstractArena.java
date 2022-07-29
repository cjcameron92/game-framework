package com.games.api;

import javax.annotation.Nonnull;

public abstract class AbstractArena implements Arena {

    private final String id;

    public AbstractArena(String id) {
        this.id = id;
    }

    @Nonnull @Override public String getId() {
        return id;
    }
}
