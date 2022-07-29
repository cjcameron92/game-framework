package com.games.api;

import me.lucko.helper.terminable.composite.CompositeTerminable;
import org.jetbrains.annotations.NotNull;

/**
 * Component for having multi-game phases
 */
public interface Phase extends CompositeTerminable {

    /**
     * To enable the phase
     */
    void enable();

    /**
     * To disable or complete the phase
     */
    void complete();

    /**
     * When the phase is enabled run some runnable
     *
     * @param runnable Runnable
     * @return Phase
     */
    @NotNull Phase onEnable(@NotNull Runnable runnable);

    /**
     * When the phase is disabled run some runnable
     *
     * @param runnable Runnable
     * @return Phase
     */
    @NotNull Phase onComplete(@NotNull Runnable runnable);
}
