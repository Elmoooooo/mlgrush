package de.ips.mlgrush.listener;

import de.ips.mlgrush.MLGRushPlugin;
import de.ips.mlgrush.state.CreateMapPlayerState;
import de.ips.mlgrush.state.PlayerState;
import lombok.NoArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@NoArgsConstructor(staticName = "newInstance")
public class AsyncPlayerChatEventListener implements Listener {

    @EventHandler
    public void asyncPlayerChatEventHandler(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        PlayerState playerState = PlayerState.Helper.of(player);
        if (playerState instanceof CreateMapPlayerState) {
            CreateMapPlayerState state = (CreateMapPlayerState) playerState;
            int internalState = state.getInternalState();

            switch (internalState) {
                case 0:
                    state.setLocation1(player.getLocation());
                    player.sendMessage(MLGRushPlugin.getInstance().getGeneralConfig().getMessageData().getSecondLocation());
                    break;

                case 1:
                    Location location1 = state.getLocation1();
                    Location location2 = player.getLocation();

                    int x, y, z;
                    x = Math.min(location1.getBlockX(), location2.getBlockX());
                    y = Math.min(location1.getBlockY(), location2.getBlockY());
                    z = Math.min(location1.getBlockZ(), location2.getBlockZ());

                    state.setLocation1(new Location(location1.getWorld(), x, y, z));

                    x = Math.max(location1.getBlockX(), location2.getBlockX());
                    y = Math.max(location1.getBlockY(), location2.getBlockY());
                    z = Math.max(location1.getBlockZ(), location2.getBlockZ());

                    state.setLocation2(new Location(location1.getWorld(), x, y, z));

                    player.sendMessage(MLGRushPlugin.getInstance().getGeneralConfig().getMessageData().getBlueSpawn());
                    break;

                case 2:
                    state.setBlueSpawn(player.getLocation().subtract(state.getLocation1()));
                    player.sendMessage(MLGRushPlugin.getInstance().getGeneralConfig().getMessageData().getRedSpawn());
                    break;

                case 3:
                    state.setRedSpawn(player.getLocation().subtract(state.getLocation1()));
                    player.sendMessage(MLGRushPlugin.getInstance().getGeneralConfig().getMessageData().getEnterName());
                    break;

                case 4:
                    state.setName(message);
                    player.sendMessage(MLGRushPlugin.getInstance().getGeneralConfig().getMessageData().getDeathHeight());
                    break;

                case 5:
                    state.setDeathHeight(player.getLocation().getBlockY() - state.getLocation1().getBlockY());
                    PlayerState.Helper.remove(player);
                    break;
            }

            state.setInternalState(internalState + 1);
            event.setCancelled(true);
        }
    }

}
