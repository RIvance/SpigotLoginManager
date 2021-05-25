package cc.nodev.utils;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public enum DataBaseSession {
    INSTANCE;

    private final String address;
    private final String password;

    DataBaseSession() {
        address = "";
        password = "";
    }

    public static boolean checkPlayerExists(Player player) {
        return false;
    }

    public static void registerPlayer(Player player, String password) {

    }

    public static boolean checkPassword(Player player, String password) {
        return false;
    }

    public static PlayerData getPlayerData(String playerName) {
        return new PlayerData(playerName, GameMode.CREATIVE, null);
    }

    public PlayerData getPlayerData(Player player) {
        return getPlayerData(player.getName());
    }

}
