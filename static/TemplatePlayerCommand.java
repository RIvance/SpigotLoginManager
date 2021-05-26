package cc.nodev.command.specific;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public enum TemplatePlayerCommand implements PlayerCommandInterface {
    INSTANCE;
    @Override
    public boolean execute(@NotNull Player player, String[] args) {
        return false;
    }
}