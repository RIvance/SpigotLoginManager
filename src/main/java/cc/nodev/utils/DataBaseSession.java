package cc.nodev.utils;

import org.bukkit.entity.Player;

import java.util.Properties;

public enum DataBaseSession {
    INSTANCE;

    private String address;
    private String password;

    private DataBaseSession() {

    }

    private DataBaseSession(String address, String password) {
        Properties databaseProperties = new Properties();
        //databaseProperties.load();
    }

    public boolean checkPlayerExists(Player player) {
        return false;
    }

    public void registerPlayer(Player player, String password) {

    }

    public boolean checkPassword(Player player, String password) {
        return false;
    }

    public PlayerData getPlayerData(String player) {
        return null;
    }

    public PlayerData getPlayerData(Player player) {
        return getPlayerData(player.getName());
    }

}
