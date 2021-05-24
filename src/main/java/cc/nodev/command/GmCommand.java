package cc.nodev.command;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GmCommand extends Command {
    protected GmCommand(String name, String description, String usageMessage, List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            switch (args[0]) {
                case "0":
                    player.setGameMode(GameMode.SURVIVAL);
                    break;
                case "1":
                    player.setGameMode(GameMode.CREATIVE);
                    break;
                case "2":
                    player.setGameMode(GameMode.ADVENTURE);
                    break;
                case "3":
                    player.setGameMode(GameMode.SPECTATOR);
                    break;
                default:
                    return false;
            }

            return true;
        }

        return false;
    }
}
