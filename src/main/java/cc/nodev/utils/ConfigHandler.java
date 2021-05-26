package cc.nodev.utils;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("unused")
public class ConfigHandler {

    public static class Node {
        private final YamlConfiguration config;
        private final String filePath;
        public final String path;

        private Node(YamlConfiguration config, String filePath, String path) {
            this.config = config;
            this.filePath = filePath;
            this.path = path;
        }

        @NotNull
        public String getValue() {
            String value = (String) config.get(path);
            return value == null ? "" : value;
        }

        public void setValue(@NotNull Object value) {
            config.set(path, value.toString());
        }

        public Node item(@NotNull String node) {
            if (node.isEmpty()) {
                return this;
            } else if (path == null || path.isEmpty()) {
                return new Node(config, filePath, node);
            }
            return new Node(config, filePath, path + "." + node);
        }

        public void saveFile() throws IOException {
            config.save(filePath);
        }
    }

    private String filePath;
    private final YamlConfiguration config = new YamlConfiguration();

    public ConfigHandler(String path) throws IOException {
        this.load(path);
    }

    @SuppressWarnings("all")
    public void load(String path) throws IOException {
        this.filePath = path;
        File file = new File(path);
        file.createNewFile();
        try {
            config.load(file);
        } catch (InvalidConfigurationException exception) {
            file.deleteOnExit();
            file.createNewFile();
        }
    }

    public Node getRoot() {
        return new Node(config, filePath, "");
    }

    public Node item(@NotNull String node) {
        return this.getRoot().item(node);
    }
}
