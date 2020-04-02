package de.ips.mlgrush.storage.bridge.file;

import de.ips.mlgrush.storage.bridge.BridgeProvider;
import de.ips.mlgrush.storage.config.DatabaseConfig;
import lombok.NoArgsConstructor;

import java.nio.file.Path;
import java.nio.file.Paths;

@NoArgsConstructor(staticName = "newInstance")
public class FileStorageBridgeProvider implements BridgeProvider<FileStorageBridge> {

    @Override
    public FileStorageBridge buildBridge(DatabaseConfig config) {
        Path path = Paths.get("plugins", "MLGRush", "storage",
                config.getFileStorageData().getFileName() + ".json");

        return new FileStorageBridge(path);
    }
}
