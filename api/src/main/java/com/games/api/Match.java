package com.games.api;

import me.lucko.helper.text3.Text;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public interface Match {

    @NotNull Set<Player> getPlayers();

    void addPlayer(@NotNull Player player);

    default boolean hasPlayer(@NotNull Player type) {
        return getPlayers().parallelStream().anyMatch(it -> it.equals(type));
    }

    default int getSize() {
        return getPlayers().size();
    }

    default void msg(@NotNull String... lines) {
        getPlayers().forEach(player -> {
            for (String str : lines) {
                player.sendMessage(Text.colorize(str));
            }
        });
    }

    default void forEach(@NotNull Consumer<Player> consumer) {
        getPlayers().forEach(consumer);
    }

    static @NotNull Match newMatch() {
        return new SimpleMatch();
    }

    class SimpleMatch implements Match {

        private final Set<Player> players;

        public SimpleMatch() {
            this.players = new HashSet<>();
        }

        @NotNull @Override public Set<Player> getPlayers() {
            return players;
        }

        @Override public void addPlayer(@NotNull Player player) {
            this.players.add(player);

        }
    }
}
