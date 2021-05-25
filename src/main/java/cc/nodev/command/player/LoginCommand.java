package cc.nodev.command.player;

import cc.nodev.command.PlayerCommandInterface;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public enum LoginCommand implements PlayerCommandInterface {
    INSTANCE;
    @Override
    public boolean execute(@NotNull Player player, String[] args) {
        return false;
    }
}
