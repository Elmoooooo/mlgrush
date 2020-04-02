package de.ips.mlgrush.state;

import de.ips.mlgrush.MLGRushPlugin;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public interface PlayerState {

    class Helper {

        public static PlayerState of(Player player) {
            PlayerState result = null;
            if (player.hasMetadata("playerState"))
                result = (PlayerState) player.getMetadata("playerState").get(0).value();

            return result;
        }

        public static PlayerState remove(Player player) {
            PlayerState result = null;

            if (player.hasMetadata("playerState")) {
                result = (PlayerState) player.getMetadata("playerState").get(0).value();
                player.removeMetadata("playerState", MLGRushPlugin.getInstance());
            }

            return result;
        }

        public static void set(Player player, PlayerState state) {
            if (player.hasMetadata("playerState"))
                player.removeMetadata("playerState", MLGRushPlugin.getInstance());

            player.setMetadata("playerState", new FixedMetadataValue(MLGRushPlugin.getInstance(), state));
        }

    }

}
