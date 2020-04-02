package de.ips.mlgrush.storage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor(staticName = "standard")
@AllArgsConstructor(staticName = "of")
public class StorageIndex {

    private final transient UUID holder;

    private int kills;
    private int deaths;
    private int bedsDestroyed;
    private int gamesPlayed;
    private int rankedPoints;

}
