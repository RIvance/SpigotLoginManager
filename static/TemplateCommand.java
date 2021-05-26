package cc.nodev.command.specific;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public enum TemplateCommand implements CommandInterface {
    INSTANCE;
    @Override
    public boolean execute(@NotNull CommandSender sender, String[] args) {
        return false;
    }
}