package de.ips.mlgrush.listener;

import de.ips.mlgrush.state.PlayerState;
import lombok.NoArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@NoArgsConstructor(staticName = "newInstance")
public class PlayerQuitEventListener implements Listener {

    @EventHandler
    public void playerQuitEventHandler(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        PlayerState.Helper.remove(player);
    }

}
