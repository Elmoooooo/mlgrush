package de.ips.mlgrush.state;

import com.google.common.collect.Sets;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.util.Set;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LobbyPlayerState implements PlayerState {

    private final Set<Player> challenged;

    public static LobbyPlayerState newInstance() {
        return new LobbyPlayerState(Sets.newHashSet());
    }

}
