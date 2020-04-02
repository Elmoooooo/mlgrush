package de.ips.mlgrush.game;

import com.google.common.collect.Sets;
import de.ips.mlgrush.misc.Vector3;
import de.ips.mlgrush.state.PlayerState;
import de.ips.mlgrush.state.PlayingPlayerState;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Set;

@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PRIVATE)
@Getter
public class Game {

    private final Player player;
    private final Player player2;

    private final ArenaTemplate arenaTemplate;
    private final Location location;

    public void update() {
        players().forEach($player -> {
            PlayingPlayerState playerState = (PlayingPlayerState) PlayerState.Helper.of($player);
            Vector3 offset = playerState.isRedTeam() ? arenaTemplate.getRedSpawnOffset() :
                    arenaTemplate.getBlueSpawnOffset();

            $player.teleport(location.add(offset.getX(), offset.getY(), offset.getZ()));
        });
    }

    public Set<Player> players() {
        return Sets.newHashSet(player, player2);
    }
}

