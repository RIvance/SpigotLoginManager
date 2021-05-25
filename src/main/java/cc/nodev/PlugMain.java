package cc.nodev;

import cc.nodev.command.CommandInterface;
import cc.nodev.command.PlayerCommandInterface;
import cc.nodev.event.JoinListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PlugMain extends JavaPlugin {

    public void setCommand(@NotNull String commandLabel, final CommandInterface commandImpl) {
        Objects.requireNonNull(this.getCommand(commandLabel)).setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
                return commandImpl.execute(sender, args);
            }
        });
    }

    public void setPlayerCommand(@NotNull String commandLabel, final PlayerCommandInterface commandImpl) {
        Objects.requireNonNull(this.getCommand(commandLabel)).setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
                if (!(sender instanceof Player)) return false;
                return commandImpl.execute((Player) sender, args);
            }
        });
    }

    private void initCommands() {

    }

    @Override
    public void onEnable() {
        initCommands();
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
    }
}
