package org.ivance;

import org.ivance.command.CommandInterface;
import org.ivance.command.PlayerCommandInterface;
import org.ivance.command.server.SetOpCommand;
import org.ivance.command.player.OpLoginCommand;
import org.ivance.command.player.LoginCommand;
import org.ivance.command.player.LogoutCommand;
import org.ivance.command.player.RegisterCommand;
import org.ivance.event.JoinListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@SuppressWarnings("unused")
public class PlugMain extends JavaPlugin {

    public static final String NAME = "NodevServerManager";

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
        // Non-Op Commands
        setPlayerCommand("register", RegisterCommand.INSTANCE);
        setPlayerCommand("login",    LoginCommand.INSTANCE);
        setPlayerCommand("oplogin",  OpLoginCommand.INSTANCE);
        setPlayerCommand("logout",   LogoutCommand.INSTANCE);
        // Op Commands
        setPlayerCommand("setop",    SetOpCommand.INSTANCE);
    }

    @Override
    public void onEnable() {
        initCommands();
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
    }
}
