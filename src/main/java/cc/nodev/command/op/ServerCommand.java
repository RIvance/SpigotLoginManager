package cc.nodev.command.op;

import cc.nodev.command.PlayerCommandInterface;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public enum ServerCommand implements PlayerCommandInterface {
    INSTANCE;
    @Override
    public boolean execute(@NotNull Player player, String[] args) {
        if (player.isOp()) {

        }
        return false;
    }
}
