package gg.habibi.game.spleef.game;

import com.games.api.AbstractArena;
import org.bukkit.Location;

public class SpleefArena extends AbstractArena {

    private final Location spawnPoint;

    public SpleefArena(String id, Location spawnPoint) {
        super(id);
        this.spawnPoint = spawnPoint;
    }

    public Location getSpawnPoint() {
        return spawnPoint;
    }
}
