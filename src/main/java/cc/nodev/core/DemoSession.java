package cc.nodev.core;

import org.bukkit.entity.Player;

public class DemoSession extends ConfigFileSession {
    @Override
    public boolean checkPin(Player player, int pin) {
        return true;
    }
}
