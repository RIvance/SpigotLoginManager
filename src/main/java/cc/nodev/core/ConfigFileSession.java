package cc.nodev.core;

import cc.nodev.utils.ConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ConfigFileSession implements PlayerDataSession {

    Map<Player, ConfigHandler> playerConfigs = new HashMap<>();
    
    private void checkNewPlayer(Player player) {
        if (!playerConfigs.containsKey(player)) {
            try {
                String path = "players/" + player.getName() + ".yml";
                playerConfigs.put(player, new ConfigHandler(path));
            } catch (IOException exception) {
                System.out.println("Unable to load or create config file of player " + player.getName());
                exception.printStackTrace();
            }
        }
    }

    private ConfigHandler getConfig(Player player) {
        return playerConfigs.get(player);
    }

    @Override
    public void setOp(Player player, boolean isOp) {
        checkNewPlayer(player);
        getConfig(player).item("op").setValue(true);
    }

    private void saveLocation(ConfigHandler.Node locationNode, Location location) {
        locationNode.item("world").setValue(Objects.requireNonNull(location.getWorld()).getName());
        locationNode.item("yaw").setValue(location.getYaw());
        locationNode.item("pitch").setValue(location.getPitch());

        Vector direction = location.getDirection();
        ConfigHandler.Node directionNode = locationNode.item("direction");
        directionNode.item("x").setValue(direction.getX());
        directionNode.item("y").setValue(direction.getY());
        directionNode.item("z").setValue(direction.getZ());

        ConfigHandler.Node coordNode = locationNode.item("coord");
        coordNode.item("x").setValue(location.getX());
        coordNode.item("y").setValue(location.getY());
        coordNode.item("z").setValue(location.getZ());
    }

    private Location loadLocation(ConfigHandler.Node locationNode) {
        String world = locationNode.item("world").getValue();

        ConfigHandler.Node coordNode = locationNode.item("coord");
        double x = Double.parseDouble(coordNode.item("x").getValue());
        double y = Double.parseDouble(coordNode.item("y").getValue());
        double z = Double.parseDouble(coordNode.item("z").getValue());

        Location location = new Location(Bukkit.getWorld(world), x, y, z);
        location.setYaw(Float.parseFloat(locationNode.item("yaw").getValue()));
        location.setPitch(Float.parseFloat(locationNode.item("pitch").getValue()));

        Vector direction = new Vector();
        ConfigHandler.Node directionNode = locationNode.item("direction");
        direction.setX(Double.parseDouble(directionNode.item("x").getValue()));
        direction.setY(Double.parseDouble(directionNode.item("y").getValue()));
        direction.setZ(Double.parseDouble(directionNode.item("z").getValue()));
        location.setDirection(direction);

        return location;
    }

    @Override
    public void updateLastLocation(Player player) {
        checkNewPlayer(player);
        ConfigHandler.Node locationNode = getConfig(player).item("location");
        Location location = player.getLocation();
        saveLocation(locationNode, location);
    }

    @Override
    public void updateSpawnLocation(Player player) {
        checkNewPlayer(player);
        ConfigHandler.Node spawnNode = getConfig(player).item("spawn");
        Location location = player.getLocation();
        saveLocation(spawnNode, location);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void updateGameMode(Player player) {
        getConfig(player).item("gamemode").setValue(player.getGameMode().getValue());
    }

    @Override
    public boolean isNew(Player player) {
        return getConfig(player).item("pin").getValue().isEmpty();
    }

    @Override
    public boolean isOp(Player player) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public GameMode getGameMode(Player player) {
        int gamemode = Integer.parseInt(playerConfigs.get(player).item("gamemode").getValue());
        return GameMode.getByValue(gamemode);
    }

    @Override
    public Location getLastLocation(Player player) {
        return loadLocation(getConfig(player).item("location"));
    }

    @Override
    public Location getSpawnLocation(Player player) {
        return loadLocation(getConfig(player).item("spawn"));
    }

    @Override
    public void newPlayer(Player player, int pin) {
        getConfig(player).item("pin").setValue(String.valueOf(pin));
    }

    @Override
    public boolean checkPin(Player player, int pin) {
        // TODO
        return false;
    }
}
