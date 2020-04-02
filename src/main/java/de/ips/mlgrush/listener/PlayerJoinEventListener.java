package de.ips.mlgrush.listener;

import de.ips.mlgrush.inventory.LobbyInventory;
import de.ips.mlgrush.misc.Players;
import de.ips.mlgrush.state.LobbyPlayerState;
import de.ips.mlgrush.state.PlayerState;
import lombok.NoArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@NoArgsConstructor(staticName = "newInstance")
public class PlayerJoinEventListener implements Listener {

    @EventHandler
    public void playerJoinEventHandler(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Players.resetPlayer(player);

        PlayerState.Helper.set(player, LobbyPlayerState.newInstance());
        LobbyInventory.apply(player);

        event.setJoinMessage(null);
    }

}
