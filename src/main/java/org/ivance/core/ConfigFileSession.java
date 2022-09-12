package org.ivance.core;

import org.ivance.utils.ConfigHandler;
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
    
    private void configFileCheck(Player player) {
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
        configFileCheck(player);
        return playerConfigs.get(player);
    }

    @Override
    public void setOp(Player player, boolean isOp) {
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

    private Location loadLocation(ConfigHandler.Node locationNode) throws ClassCastException {
        String world = (String) locationNode.item("world").getValue();

        ConfigHandler.Node coordNode = locationNode.item("coord");
        Double x = (Double) coordNode.item("x").getValue();
        Double y = (Double) coordNode.item("y").getValue();
        Double z = (Double) coordNode.item("z").getValue();

        Location location = new Location(Bukkit.getWorld(world), x, y, z);

        Number yaw   = (Number) locationNode.item("yaw").getValue();
        Number pitch = (Number) locationNode.item("pitch").getValue();

        location.setYaw(yaw.floatValue());
        location.setPitch(pitch.floatValue());

        Vector direction = new Vector();
        ConfigHandler.Node directionNode = locationNode.item("direction");
        direction.setX((Double) directionNode.item("x").getValue());
        direction.setY((Double) directionNode.item("y").getValue());
        direction.setZ((Double) directionNode.item("z").getValue());
        location.setDirection(direction);

        return location;
    }

    @Override
    public void updateLastLocation(Player player) {
        ConfigHandler.Node locationNode = getConfig(player).item("location");
        Location location = player.getLocation();
        saveLocation(locationNode, location);
    }

    @Override
    public void updateSpawnLocation(Player player) {
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
        return getConfig(player).item("pin").getValue() == ConfigHandler.NullObject;
    }

    @Override
    public boolean isOp(Player player) {
        try {
            return (Boolean) getConfig(player).item("op").getValue();
        } catch (ClassCastException exception) {
            return false;
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public GameMode getGameMode(Player player) {
        try {
            Integer gamemode = (Integer) getConfig(player).item("gamemode").getValue();
            return GameMode.getByValue(gamemode);
        } catch (ClassCastException exception) {
            return player.getGameMode();
        }
    }

    @Override
    public Location getLastLocation(Player player) {
        try {
            return loadLocation(getConfig(player).item("location"));
        } catch (ClassCastException exception) {
            return player.getLocation();
        }
    }

    @Override
    public Location getSpawnLocation(Player player) {
        try {
            return loadLocation(getConfig(player).item("spawn"));
        } catch (ClassCastException exception) {
            return player.getBedSpawnLocation();
        }
    }

    @Override
    public void newPlayer(Player player, int pin) {
        getConfig(player).item("pin").setValue(pin);
        getConfig(player).item("op").setValue(false);
        getConfig(player).item("gamemode").setValue(0);
    }

    @Override
    public boolean checkPin(Player player, int pin) {
        try {
            Integer pinData = (Integer) getConfig(player).item("pin").getValue();
            return pin == pinData;
        } catch (ClassCastException exception) {
            return false;
        }
    }

    @Override
    public void trySaveData(Player player) throws IOException {
        getConfig(player).getRoot().saveFile();
    }
}
