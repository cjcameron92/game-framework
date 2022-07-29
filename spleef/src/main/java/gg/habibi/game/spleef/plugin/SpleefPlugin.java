package gg.habibi.game.spleef.plugin;

import com.games.api.Game;
import com.games.api.Match;
import gg.habibi.game.api.ServerData;
import gg.habibi.game.server.Games;
import gg.habibi.game.spleef.config.SpleefConf;
import gg.habibi.game.spleef.game.SpleefArena;
import gg.habibi.game.spleef.game.SpleefGame;
import me.lucko.helper.plugin.ExtendedJavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class SpleefPlugin extends ExtendedJavaPlugin {

    @Override protected void enable() {
        final SpleefConf spleefConf = new SpleefConf();
        final Game<?, ?, ?> spleefGame = new SpleefGame(ServerData.newServerData(spleefConf.serverId, spleefConf.port), new SpleefArena("default-arena", new Location(Bukkit.getWorld("world"),1, 100, 1)), new Match.SimpleMatch(), spleefConf);

        Games.getGameRegistry().register(spleefGame, spleefGame);

    }
}
