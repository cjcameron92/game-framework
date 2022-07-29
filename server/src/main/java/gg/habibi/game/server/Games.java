package gg.habibi.game.server;

import gg.habibi.game.server.registry.GameRegistry;
import gg.habibi.game.server.registry.SimpleGameRegistry;

public class Games {

    private static final GameRegistry gameRegistry;

    static {
        gameRegistry = new SimpleGameRegistry();
    }

    public static GameRegistry getGameRegistry() {
        return gameRegistry;
    }
}
