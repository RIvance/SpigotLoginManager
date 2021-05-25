package cc.nodev.command;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public interface CommandInterface {
    boolean execute(@NotNull CommandSender sender, String[] args);
}
