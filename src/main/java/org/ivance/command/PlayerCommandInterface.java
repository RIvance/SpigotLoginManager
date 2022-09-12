package org.ivance.command;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public interface PlayerCommandInterface {
    boolean execute(@NotNull Player player, String[] args);
}
