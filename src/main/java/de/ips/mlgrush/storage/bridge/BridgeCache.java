package de.ips.mlgrush.storage.bridge;

import de.ips.mlgrush.storage.StorageIndex;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.UUID;

@NoArgsConstructor(staticName = "newInstance")
public class BridgeCache extends HashMap<UUID, StorageIndex> {
}
