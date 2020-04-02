package de.ips.mlgrush.storage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.BiConsumer;
import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public enum TrackedStatistic {

    KILLS(StorageIndex::getKills, StorageIndex::setKills),
    DEATHS(StorageIndex::getDeaths, StorageIndex::setDeaths),
    BEDS_DESTROYED(StorageIndex::getBedsDestroyed, StorageIndex::setBedsDestroyed),
    GAMES_PLAYED(StorageIndex::getGamesPlayed, StorageIndex::setGamesPlayed),
    RANKED_POINTS(StorageIndex::getRankedPoints, StorageIndex::setRankedPoints);

    private final Function<StorageIndex, Integer> getter;
    private final BiConsumer<StorageIndex, Integer> setter;
}
