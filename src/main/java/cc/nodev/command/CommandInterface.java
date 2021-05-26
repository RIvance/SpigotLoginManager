package cc.nodev.command;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public interface CommandInterface {
    boolean execute(@NotNull CommandSender sender, String[] args);
}
