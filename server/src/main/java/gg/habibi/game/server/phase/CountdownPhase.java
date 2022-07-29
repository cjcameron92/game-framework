package gg.habibi.game.server.phase;

import com.games.api.AbstractTickablePhase;
import com.games.api.Match;
import gg.habibi.game.api.GameData;

import java.util.concurrent.atomic.AtomicInteger;

public class CountdownPhase extends AbstractTickablePhase {

    private final Match match;
    private final GameData gameData;
    private final AtomicInteger counter;

    public CountdownPhase(Match match, GameData gameData) {
        this.match = match;
        this.gameData= gameData;
        this.counter = new AtomicInteger(10);
    }

    @Override public void tick() {
        if (match.getSize() < gameData.getMaxPlayers()) {
            match.msg("&eWaiting for &6" + match.getPlayers() + "/" + gameData.getMaxPlayers());
            counter.set(10);
            return;
        }

        final int count = counter.getAndDecrement();
        match.msg("&aGame starting in " + count + "s.");

        if (counter.get() <= 0)
            complete();
    }
}
