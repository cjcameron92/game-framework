package gg.habibi.game.server.phase;

import com.games.api.AbstractTickablePhase;
import com.games.api.Match;
import gg.habibi.game.api.GameData;

public class LobbyPhase extends AbstractTickablePhase {

    private final Match match;
    private final GameData gameData;

    private long timestamp;

    public LobbyPhase(Match match, GameData gameData) {
        this.match = match;
        this.gameData = gameData;
        this.timestamp = 0L;
    }

    @Override public void tick() {
        if (match.getSize() >= gameData.getMaxPlayers()) {
            complete();
        } else {
            if (System.currentTimeMillis() > timestamp) {
                match.msg("&eWaiting for &6" + match.getPlayers() + "/" + gameData.getMaxPlayers());
                timestamp = System.currentTimeMillis() + 7_500L;
            }
        }
    }
}
