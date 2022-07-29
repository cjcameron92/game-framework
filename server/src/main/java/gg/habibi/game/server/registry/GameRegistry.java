package gg.habibi.game.server.registry;

import com.games.api.Game;
import me.lucko.helper.redis.Identifiable;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface GameRegistry {

    void register(@NotNull Identifiable identifiable, @NotNull Game<?, ?, ?> game);

    @NotNull Optional<Game<?, ?, ?>> getGame(@NotNull Identifiable identifiable);
}
