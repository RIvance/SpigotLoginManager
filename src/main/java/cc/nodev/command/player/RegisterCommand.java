package cc.nodev.command.player;

import cc.nodev.command.PlayerCommandInterface;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RegisterCommand implements PlayerCommandInterface {
    @Override
    public boolean execute(@NotNull Player player, String[] args) {
        return false;
    }
}
