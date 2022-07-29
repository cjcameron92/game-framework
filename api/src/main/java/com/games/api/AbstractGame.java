package com.games.api;

import com.google.gson.JsonElement;
import gg.habibi.game.api.GameApi;
import gg.habibi.game.api.ServerData;
import me.lucko.helper.Services;
import me.lucko.helper.gson.JsonBuilder;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class AbstractGame<X extends Arena, Y extends Match, Z extends Phase> implements Game<X, Y, Z> {

    private final UUID uuid;
    private final GameApi gameApi;

    protected final X arena;
    protected final Y match;
    protected final List<Z> phases;

    protected boolean started;

    private Runnable enabled, disabled;
    private ServerData serverData;

    @SafeVarargs
    public AbstractGame(ServerData serverData, X arena, Y match, Z... phases) {
        this.uuid = UUID.randomUUID();
        this.gameApi = Services.load(GameApi.class);
        this.arena = arena;
        this.match = match;
        this.phases = new ArrayList<>(List.of(phases));
        this.started = false;

        gameApi.addGame(this);
        gameApi.updateGame(this);
    }

    public void start() {
        if (started) throw new IllegalStateException("This game has already been started!");
        if (enabled != null) enabled.run();

        started = true;
        gameApi.updateGame(this);

        for (int i = 0; i < phases.size(); i++) {
            final Z phase = phases.get(i);
            final int index = i + 1;
            if (index >= phases.size()) {
                stop();
                break;
            }
            phase.onComplete(() -> phases.get(index).enable());
        }
    }

    public void stop() {
        if (disabled != null) disabled.run();
    }

    protected void onEnable(Runnable runnable) {
        this.enabled = runnable;
    }

    protected void onDisable(Runnable runnable) {
        this.disabled = runnable;
    }

    protected void addPhases(@NotNull Z... phases) {
        this.phases.addAll(List.of(phases));
    }

    @NotNull @Override public X getArena() {
        return arena;
    }

    @NotNull @Override public Y getMatch() {
        return match;
    }

    @NotNull @Override public List<Z> getPhases() {
        return phases;
    }

    @NotNull @Override public ServerData getServerData() {
        return serverData;
    }

    @Override public void setStarted(boolean started) {
        this.started = started;
        gameApi.updateGame(this);
    }

    @Override public boolean hasStarted() {
        return started;
    }

    @Override public int getPlayers() {
        return match.getSize();
    }

    @Override public int getMaxPlayers() {
        return 2;
    }


    @Nonnull @Override public JsonElement serialize() {
        return JsonBuilder.object()
                .add("id", getId())
                .add("serverData", serverData.serialize())
                .add("maxPlayers", getMaxPlayers())
                .add("players", match.getSize())
                .add("started", started)
                .build();
    }

    @Nonnull @Override public String getId() {
        return uuid.toString();
    }
}
