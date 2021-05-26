<<<<<<< HEAD:static/TemplatePlayerCommand.java
package cc.nodev.command.specific;
=======
package cc.nodev.command.player;
>>>>>>> b8959ae0eeb5cfcdd0b37039abfe8b5fe2487f0d:src/main/java/cc/nodev/command/player/RegisterCommand.java

import cc.nodev.command.PlayerCommandInterface;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RegisterCommand implements PlayerCommandInterface {
    @Override
    public boolean execute(@NotNull Player player, String[] args) {
        return false;
    }
}
