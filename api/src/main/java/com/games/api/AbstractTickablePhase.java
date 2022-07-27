package com.games.api;

public abstract class AbstractTickablePhase extends AbstractPhase implements TickablePhase {

    @Override public void enable() {
        super.enable();
        /// TODO: 2022-07-27  implement scheduler to tick 
    }
}
