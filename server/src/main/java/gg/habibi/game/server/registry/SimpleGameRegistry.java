package gg.habibi.game.server.registry;

import com.games.api.Game;
import me.lucko.helper.redis.Identifiable;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleGameRegistry implements GameRegistry {

    private final Map<String, Game<?,?,?>> games = new ConcurrentHashMap<>();

    @Override public void register(@NotNull Identifiable identifiable, @NotNull Game<?, ?, ?> game) {
        this.games.put(identifiable.getId(), game);
    }

    @Override public @NotNull Optional<Game<?, ?, ?>> getGame(@NotNull Identifiable identifiable) {
        return games.containsKey(identifiable.getId()) ? Optional.of(games.get(identifiable.getId())) : Optional.empty();
    }
}
