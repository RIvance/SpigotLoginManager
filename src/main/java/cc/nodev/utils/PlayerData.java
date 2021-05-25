package cc.nodev.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.GameMode;
import org.bukkit.Location;

@Data
@AllArgsConstructor
public class PlayerData {
    private final String name;
    private GameMode gamemode;
    private Location location;
}
