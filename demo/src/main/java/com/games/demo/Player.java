package com.games.demo;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record Player(UUID uuid, String name) {

    public void sendMessage(@NotNull String... lines) {}

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }
}
