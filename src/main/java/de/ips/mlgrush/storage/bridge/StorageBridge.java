package de.ips.mlgrush.storage.bridge;

import de.ips.mlgrush.storage.StorageIndex;
import de.ips.mlgrush.storage.TrackedStatistic;
import lombok.SneakyThrows;

import java.util.UUID;

public abstract class StorageBridge {

    protected BridgeCache cache;

    public void increment(UUID uuid, TrackedStatistic statistic, int incrementor) {
        set(uuid, statistic, get(uuid, statistic) + incrementor);
    }

    public void decrement(UUID uuid, TrackedStatistic statistic, int decrementor) {
        set(uuid, statistic, get(uuid, statistic) - decrementor);
    }

    @SneakyThrows
    public void set(UUID uuid, TrackedStatistic statistic, int value) {
        if (cache == null)
            cache = BridgeCache.newInstance();

        if (!cache.containsKey(uuid))
            populateCache(uuid);

        cache.putIfAbsent(uuid, StorageIndex.standard(uuid));
        StorageIndex index = cache.get(uuid);
        statistic.getSetter().accept(index, value);
    }

    @SneakyThrows
    public int get(UUID uuid, TrackedStatistic statistic) {
        if (cache == null)
            cache = BridgeCache.newInstance();

        if (!cache.containsKey(uuid))
            populateCache(uuid);

        cache.putIfAbsent(uuid, StorageIndex.standard(uuid));
        StorageIndex index = cache.get(uuid);
        return statistic.getGetter().apply(index);
    }

    public abstract void save();

    public abstract void populateCache(UUID uuid);

}
