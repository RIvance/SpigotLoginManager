package cc.nodev.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// setspawn

public class SetSpawnCommand extends Command {
    protected SetSpawnCommand(String name, String description, String usageMessage, List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.setBedSpawnLocation(player.getLocation());
            return true;
        }
        return false;
    }
}
