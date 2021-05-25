package cc.nodev.core;

import org.bukkit.entity.Player;

public enum Players {
    INSTANCE;

    private final PlayerDataSession dataSession = new DemoSession();

    public static void restorePlayerState(Player player) {
        player.setGameMode(INSTANCE.dataSession.getGameMode(player));
        player.teleport(INSTANCE.dataSession.getLastLocation(player));
        player.setBedSpawnLocation(INSTANCE.dataSession.getSpawnLocation(player));
    }

    public static void updateSession(Player player) {
        INSTANCE.dataSession.updateGameMode(player);
        INSTANCE.dataSession.updateLastLocation(player);
        INSTANCE.dataSession.updateSpawnLocation(player);
    }

    public static void setOp(Player player) {
        INSTANCE.dataSession.setOp(player, true);
    }

    public static void removeOp(Player player) {
        INSTANCE.dataSession.setOp(player, false);
    }

    public static boolean isOp(Player player) {
        return INSTANCE.dataSession.isOp(player);
    }

    public static boolean checkPin(Player player, int pin) {
        return INSTANCE.dataSession.checkPin(player, pin);
    }
}
