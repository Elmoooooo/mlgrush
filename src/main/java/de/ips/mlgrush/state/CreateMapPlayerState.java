package de.ips.mlgrush.state;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@RequiredArgsConstructor(staticName = "of")
@Getter
@Setter
public class CreateMapPlayerState implements PlayerState {

    private final Player player;

    private int internalState = 0;
    private int deathHeight;

    private String name;

    private Location location1;
    private Location location2;

    private Location blueSpawn;
    private Location redSpawn;

}
