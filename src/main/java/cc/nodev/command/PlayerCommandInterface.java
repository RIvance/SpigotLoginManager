package cc.nodev.command;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface PlayerCommandInterface {
    boolean execute(@NotNull Player player, String[] args);
}
