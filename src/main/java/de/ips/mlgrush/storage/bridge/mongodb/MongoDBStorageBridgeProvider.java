package de.ips.mlgrush.storage.bridge.mongodb;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import de.ips.mlgrush.storage.bridge.BridgeProvider;
import de.ips.mlgrush.storage.config.DatabaseConfig;
import lombok.NoArgsConstructor;
import org.bson.Document;

@NoArgsConstructor(staticName = "newInstance")
public class MongoDBStorageBridgeProvider implements BridgeProvider<MongoDBStorageBridge> {

    @Override
    public MongoDBStorageBridge buildBridge(DatabaseConfig config) {
        DatabaseConfig.MongoDBData data = config.getMongoDBData();

        MongoClientSettings.Builder clientSettingsBuilder = MongoClientSettings
                .builder()
                .applicationName("MLGRush");

        if (data.isAuthEnabled())
            clientSettingsBuilder
                    .credential(MongoCredential.createCredential(data.getUsername(), data.getUserDb(),
                            data.getPassword().toCharArray()));

        MongoClient client = MongoClients.create(clientSettingsBuilder.build());
        MongoDatabase database = client.getDatabase(data.getStorageDb());
        MongoCollection<Document> collection = database.getCollection(data.getStorageCollection());

        return new MongoDBStorageBridge(collection);
    }
}
