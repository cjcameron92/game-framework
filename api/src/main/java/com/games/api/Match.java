package com.games.api;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public interface Match<Type> {

    @NotNull Set<Type> getPlayers();

    default boolean hasPlayer(@NotNull Type type) {
        return getPlayers().parallelStream().anyMatch(it -> it.equals(type));
    }

    default int getSize() {
        return getPlayers().size();
    }

    static <T> @NotNull Match<T> newMatch() {
        return new SimpleMatch<>();
    }

    class SimpleMatch<Type> implements Match<Type> {

        private final Set<Type> players;

        public SimpleMatch() {
            this.players = new HashSet<>();
        }

        @NotNull @Override public Set<Type> getPlayers() {
            return players;
        }
    }
}
