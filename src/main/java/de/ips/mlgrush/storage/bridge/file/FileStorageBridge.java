package de.ips.mlgrush.storage.bridge.file;

import com.google.common.reflect.TypeToken;
import de.ips.mlgrush.MLGRushPlugin;
import de.ips.mlgrush.storage.StorageIndex;
import de.ips.mlgrush.storage.bridge.StorageBridge;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class FileStorageBridge extends StorageBridge {

    private final Path path;

    private Map<String, StorageIndex> data;
    private FileTime lastModifiedTime;

    @Override
    @SneakyThrows
    public void save() {
        String json = MLGRushPlugin.getInstance().getGson().toJson(cache);
        Files.write(path, json.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
    }

    @Override
    @SneakyThrows
    public void populateCache(UUID uuid) {
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.write(path, "{}".getBytes(), StandardOpenOption.CREATE);
            return;
        }

        FileTime lastModifiedTime = Files.getLastModifiedTime(path);
        if (data == null || !lastModifiedTime.equals(this.lastModifiedTime)) {
            this.data = MLGRushPlugin.getInstance().getGson()
                    .fromJson(Files.newBufferedReader(path), new TypeToken<HashMap<String, Object>>() {
                    }.getType());
            this.lastModifiedTime = lastModifiedTime;
        }

        cache.compute(uuid, (key, value) -> data.get(uuid.toString()));
    }
}
