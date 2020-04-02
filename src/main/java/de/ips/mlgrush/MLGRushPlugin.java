package de.ips.mlgrush;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.ips.mlgrush.command.CreateArenaCommand;
import de.ips.mlgrush.game.ArenaTemplate;
import de.ips.mlgrush.listener.AsyncPlayerChatEventListener;
import de.ips.mlgrush.listener.PlayerJoinEventListener;
import de.ips.mlgrush.listener.PlayerQuitEventListener;
import de.ips.mlgrush.storage.bridge.StorageBridge;
import de.ips.mlgrush.storage.bridge.file.FileStorageBridgeProvider;
import de.ips.mlgrush.storage.bridge.mongodb.MongoDBStorageBridgeProvider;
import de.ips.mlgrush.storage.config.ConfigReader;
import de.ips.mlgrush.storage.config.DatabaseConfig;
import de.ips.mlgrush.storage.config.GeneralConfig;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.event.Listener;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Random;

@Getter
public class MLGRushPlugin extends JavaPlugin implements Listener {

    @Getter
    private static MLGRushPlugin instance;

    private Gson gson;

    private StorageBridge bridge;
    private GeneralConfig generalConfig;
    private World arenaWorld;

    @Override
    @SneakyThrows
    public void onEnable() {
        instance = this;

        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(ArenaTemplate.class, ArenaTemplate.ArenaTemplateDeserializer.class)
                .create();

        FileUtils.deleteDirectory(new File("arenas"));

        this.arenaWorld = WorldCreator.name("arenas")
                .generator(new ChunkGenerator() {
                    @Override
                    public byte[] generate(World world, Random random, int x, int z) {
                        return new byte[Short.MAX_VALUE];
                    }
                }).createWorld();

        getServer().getPluginManager().registerEvents(PlayerJoinEventListener.newInstance(), this);
        getServer().getPluginManager().registerEvents(PlayerQuitEventListener.newInstance(), this);
        getServer().getPluginManager().registerEvents(AsyncPlayerChatEventListener.newInstance(), this);

        DatabaseConfig databaseConfig = ConfigReader.read("database", DatabaseConfig.class,
                DatabaseConfig.DEFAULT_JSON);

        generalConfig = ConfigReader.read("general", GeneralConfig.class, GeneralConfig.DEFAULT_JSON);

        if (databaseConfig.getStorageSystem().equalsIgnoreCase("file"))
            bridge = FileStorageBridgeProvider.newInstance().buildBridge(databaseConfig);
        else if (databaseConfig.getStorageSystem().equalsIgnoreCase("mongodb"))
            bridge = MongoDBStorageBridgeProvider.newInstance().buildBridge(databaseConfig);

        getCommand("createarena").setExecutor(CreateArenaCommand.newInstance());
    }

}
