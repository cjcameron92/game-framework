package gg.habibi.game.spleef.config;

import java.util.HashSet;
import java.util.Set;

public class SpleefConf {

    public String serverId = "game-server-1";
    public int port = 22;

    public String fallbackServerId = "smp";

    public Set<String> winCommands = new HashSet<>() {{
       add("eco give {player} 500");
    }};


}
