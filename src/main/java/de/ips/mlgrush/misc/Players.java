package de.ips.mlgrush.misc;

import de.ips.mlgrush.state.PlayerState;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class Players {

    public static void resetPlayer(Player player) {
        player.setGameMode(GameMode.SURVIVAL);

        player.setHealth(20);
        player.setMaxHealth(20);
        player.setFoodLevel(20);
        player.setHealthScaled(false);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.getInventory().clear();
        player.setFireTicks(0);

        player.getActivePotionEffects().clear();

        PlayerState.Helper.remove(player);
    }
}
