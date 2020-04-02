package de.ips.mlgrush.state;

import de.ips.mlgrush.game.Game;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
@Getter
public class PlayingPlayerState implements PlayerState {

    private final Game game;
    private final boolean redTeam;

}
