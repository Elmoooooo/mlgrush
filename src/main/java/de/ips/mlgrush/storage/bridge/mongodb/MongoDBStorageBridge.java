package de.ips.mlgrush.storage.bridge.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import de.ips.mlgrush.storage.StorageIndex;
import de.ips.mlgrush.storage.bridge.StorageBridge;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.bson.Document;

import java.util.UUID;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class MongoDBStorageBridge extends StorageBridge {

    private final MongoCollection<Document> collection;

    @Override
    public void save() {
        cache.keySet().parallelStream()
                .forEach(uuid -> {
                    StorageIndex index = cache.get(uuid);
                    String uuidString = uuid.toString();

                    Document document = new Document("_id", uuidString)
                            .append("kills", index.getKills())
                            .append("gamesPlayed", index.getGamesPlayed())
                            .append("deaths", index.getDeaths())
                            .append("bedsDestroyed", index.getBedsDestroyed())
                            .append("rankedPoints", index.getRankedPoints());

                    if (collection.countDocuments(Filters.eq("_id", uuidString)) > 0)
                        collection.replaceOne(Filters.eq("_id", uuidString), document);
                    else
                        collection.insertOne(document);
                });
    }

    @Override
    public void populateCache(UUID uuid) {
        StorageIndex index;

        Document document = collection.find(Filters.eq("_id", uuid.toString())).first();
        if (document == null)
            index = StorageIndex.standard(uuid);
        else
            index = StorageIndex.of(
                    UUID.fromString(document.getString("_id")),
                    document.getInteger("kills"),
                    document.getInteger("gamesPlayed"),
                    document.getInteger("deaths"),
                    document.getInteger("bedsDestroyed"),
                    document.getInteger("rankedPoints")
            );

        cache.compute(uuid, (key, value) -> index);
    }
}
