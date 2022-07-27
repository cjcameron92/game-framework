package com.games.api;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGame<X extends Arena, Y extends Match<?>, Z extends Phase> implements Game<X, Y, Z> {

    protected final X arena;
    protected final Y match;
    protected final List<Z> phases;

    protected boolean enabled;

    private Runnable onEnable, onDisable;

    @SafeVarargs
    public AbstractGame(X arena, Y match, Z... phases) {
        this.arena = arena;
        this.match = match;
        this.phases = new ArrayList<>(List.of(phases));
        this.enabled = false;
    }

    public void start() {
        if (enabled) throw new IllegalStateException("This game has already been started!");
        if (onEnable != null) onEnable.run();
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
        if (onDisable != null) onDisable.run();
    }

    protected void onEnable(Runnable runnable) {
        this.onEnable = runnable;
    }

    protected void onDisable(Runnable runnable) {
        this.onDisable = runnable;
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

    @Override public boolean isEnabled() {
        return enabled;
    }

    @Override public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
