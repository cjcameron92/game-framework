package gg.habibi.game.core.menu;

import gg.habibi.game.api.GameApi;
import gg.habibi.game.api.GameData;
import gg.habibi.game.api.Gamer;
import me.lucko.helper.Services;
import me.lucko.helper.item.ItemStackBuilder;
import me.lucko.helper.menu.Gui;
import me.lucko.helper.messaging.bungee.BungeeCord;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class GameMenu extends Gui {

    private final GameApi gameApi;

    public GameMenu(Player player) {
        super(player, 1, "&8Games");
        this.gameApi = Services.load(GameApi.class);
    }

    @Override public void redraw() {
        setItem(4, ItemStackBuilder.of(Material.CLOCK).name("&a&lCustom Event").lore("&a-> Click to join the event.").build(() -> {
            final Collection<GameData> games = gameApi.getAvailableGames();
            if (games.isEmpty()) {
                getPlayer().sendMessage(ChatColor.RED + "There are no games for you :(");
                return;
            }

            for (GameData gameData : games) {
                if (gameData.hasStarted() || gameData.getPlayers() >= gameData.getMaxPlayers()) continue;
                handleJoin(gameData);
                break;
            }
        }));
    }

    private void handleJoin(@NotNull GameData gameData) {
        final BungeeCord bungeeCord = Services.load(BungeeCord.class);
        // send player to server

        // cache player to what game they're joining
        gameApi.addGamer(Gamer.newGamer(getPlayer().getUniqueId().toString(), gameData.getId()));
        bungeeCord.connect(getPlayer(), gameData.getServerData().getServerId());
    }
}
