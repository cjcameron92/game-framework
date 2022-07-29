package gg.habibi.game.spleef.game;

import com.games.api.AbstractPhase;
import com.games.api.AbstractTickablePhase;
import com.games.api.Match;
import gg.habibi.game.spleef.config.SpleefConf;
import me.lucko.helper.Helper;
import me.lucko.helper.item.ItemStackBuilder;
import me.lucko.helper.utils.Players;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class SpleefPhase extends AbstractTickablePhase {

    private final Match match;
    private final SpleefConf spleefConf;
    private final List<UUID> playing = new ArrayList<>();

    public SpleefPhase(Match match, SpleefArena arena, SpleefConf spleefConf) {
        this.match = match;
        this.spleefConf = spleefConf;
        playing.addAll(match.getPlayers().stream().map(Player::getUniqueId).collect(Collectors.toSet()));
        match.msg("&aGame starting now...");

        match.forEach(player -> {
            player.teleport(arena.getSpawnPoint());
            player.getInventory().clear();
            player.getInventory().addItem(ItemStackBuilder.of(Material.DIAMOND_SHOVEL).transformMeta(meta -> meta.setUnbreakable(true)).build());
        });
    }

    @Override public void tick() {
        if (playing.isEmpty() || playing.size() == 1) {
            if (playing.size() == 1) {
                Players.get(playing.get(0)).ifPresent(player -> {
                    match.msg("&aThe winning of this match is " + player.getName());
                    spleefConf.winCommands.forEach(command -> Helper.executeCommand(command.replace("{player}", player.getName())));
                });
            } else {
                match.msg("&cThere was no winner of this match :(");
            }
            complete();
        }
    }
}
