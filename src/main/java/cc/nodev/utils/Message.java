package cc.nodev.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Message {

    public static final String ERROR   = ChatColor.RED       + "[Error]"   + ChatColor.GREEN;
    public static final String INFO    = ChatColor.GRAY      + "[Info]"    + ChatColor.GREEN;
    public static final String WARNING = ChatColor.GOLD      + "[Warning]" + ChatColor.GREEN;
    public static final String DEBUG   = ChatColor.DARK_GRAY + "[Debug]"   + ChatColor.GREEN;

    public static String generate(String format, Object ... objects) {
        for (Object obj : objects) {
            String str;
            if (obj instanceof String) {
                str = (String) obj + ChatColor.GREEN;
            } else if (obj instanceof Player) {
                str = ChatColor.YELLOW + ((Player) obj).getName() + ChatColor.GREEN;
            } else {
                str = "";
            }
            format = format.replaceFirst("\\{[a-z]*\\}", str);
        }
        return format;
    }

    public static String error(String format, Object ... objects) {
        return ERROR + " " + generate(format, objects);
    }

    public static String info(String format, Object ... objects) {
        return INFO + " " + generate(format, objects);
    }

    public static String debug(String format, Object ... objects) {
        return DEBUG + " " + generate(format, objects);
    }

    public static String warning(String format, Object ... objects) {
        return WARNING + " " + generate(format, objects);
    }

    public static String playerName(String playerName) {
        return ChatColor.YELLOW + playerName;
    }
}
