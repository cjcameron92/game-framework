package com.games.api;

import me.lucko.helper.Schedulers;

public abstract class AbstractTickablePhase extends AbstractPhase implements TickablePhase {

    @Override public void enable() {
        super.enable();
        Schedulers.sync().runRepeating(this::tick, 20L, 20L).bindWith(this);
    }
}
