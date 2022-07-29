package com.games.api;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface MatchRegistry {

    void register(@NotNull Match<?> match);

    @NotNull Collection<Match<?>> getAll();
}
