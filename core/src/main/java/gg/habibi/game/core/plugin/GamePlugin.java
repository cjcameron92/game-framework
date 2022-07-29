package gg.habibi.game.core.plugin;

import gg.habibi.game.core.menu.GameMenu;
import me.lucko.helper.Commands;
import me.lucko.helper.plugin.ExtendedJavaPlugin;

public class GamePlugin extends ExtendedJavaPlugin {

    @Override protected void enable() {
        Commands.create().assertPlayer().handler(context -> new GameMenu(context.sender()).open()).registerAndBind(this, "games");
    }

    @Override protected void disable() {

    }
}
