package gg.habibi.game.spleef.game;

import com.games.api.AbstractGame;
import com.games.api.Match;
import com.games.api.TickablePhase;
import gg.habibi.game.api.ServerData;
import gg.habibi.game.server.phase.CountdownPhase;
import gg.habibi.game.server.phase.LobbyPhase;
import gg.habibi.game.spleef.config.SpleefConf;
import me.lucko.helper.Services;
import me.lucko.helper.messaging.bungee.BungeeCord;

public class SpleefGame extends AbstractGame<SpleefArena, Match.SimpleMatch, TickablePhase> {

    public SpleefGame(ServerData serverData, SpleefArena arena, Match.SimpleMatch match, SpleefConf conf) {
        super(serverData, arena, match);
        addPhases(new LobbyPhase(match, this), new CountdownPhase(match, this));

        onDisable(() -> match.forEach(player -> Services.load(BungeeCord.class).connect(player, conf.fallbackServerId)));
    }


}
