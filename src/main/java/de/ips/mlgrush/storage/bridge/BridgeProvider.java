package de.ips.mlgrush.storage.bridge;

import de.ips.mlgrush.storage.config.DatabaseConfig;

public interface BridgeProvider<T extends StorageBridge> {

    T buildBridge(DatabaseConfig config);

}
