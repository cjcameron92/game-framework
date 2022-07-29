package gg.habibi.game.server.plugin;

import gg.habibi.game.api.GameApi;
import gg.habibi.game.server.Games;
import me.lucko.helper.Events;
import me.lucko.helper.Services;
import me.lucko.helper.plugin.ExtendedJavaPlugin;
import me.lucko.helper.redis.Identifiable;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Optional;
import java.util.UUID;

public class GameServerPlugin extends ExtendedJavaPlugin {

    @Override protected void enable() {
        final GameApi gameApi = Services.load(GameApi.class);

        Events.subscribe(PlayerJoinEvent.class).handler(event -> {
            final Player player = event.getPlayer();
            final UUID uuid = player.getUniqueId();
            gameApi.getGamer(uuid::toString).ifPresent(gamer -> {
                final Optional<String> optionalGameId = gamer.getGameId();
                if (optionalGameId.isPresent()) {
                    final Identifiable identifiable = optionalGameId::get;
                    Games.getGameRegistry().getGame(identifiable).ifPresent(game -> game.getMatch().addPlayer(player));
                }
            });
        }).bindWith(this);

        Events.merge(PlayerEvent.class, PlayerQuitEvent.class, PlayerKickEvent.class).handler(event -> gameApi.getGamer(() -> event.getPlayer().getUniqueId().toString()).ifPresent(gamer -> {
            final Optional<String> optionalGameId = gamer.getGameId();
            if (optionalGameId.isPresent()) {
                final Identifiable identifiable = optionalGameId::get;
                Games.getGameRegistry().getGame(identifiable).ifPresent(game -> game.getMatch().getPlayers().remove(event.getPlayer()));
            }
            gameApi.removeGamer(gamer);
        })).bindWith(this);
    }
}
