package de.ips.mlgrush.storage.config;

import de.ips.mlgrush.MLGRushPlugin;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.function.Supplier;

public class ConfigReader {

    @SneakyThrows
    public static <T extends Config> T read(String ident, Class<T> clazz, Supplier<String> defaultJson) {
        Path configPath = Paths.get("plugins", "MLGRush", ident + ".json");

        if (!Files.exists(configPath)) {
            Files.createDirectories(configPath.getParent());
            Files.write(configPath, defaultJson.get().getBytes(), StandardOpenOption.CREATE);

            Constructor<? extends Config> defaultConstructor = clazz.getDeclaredConstructor();
            return (T) defaultConstructor.newInstance();
        }

        return MLGRushPlugin.getInstance().getGson()
                .fromJson(Files.newBufferedReader(configPath, StandardCharsets.UTF_8), clazz);
    }

}
