package com.games.api;

import me.lucko.helper.terminable.composite.AbstractCompositeTerminable;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractPhase extends AbstractCompositeTerminable implements Phase {

    private Runnable enabled, completed;

    @Override public void enable() {
        if (enabled != null) enabled.run();
    }

    @Override public void complete() {
        if (completed != null) completed.run();
    }

    @Override public @NotNull Phase onEnable(@NotNull Runnable runnable) {
        this.enabled = runnable;
        return this;
    }

    @Override public @NotNull Phase onComplete(@NotNull Runnable runnable) {
        this.completed = runnable;
        return this;
    }
}
