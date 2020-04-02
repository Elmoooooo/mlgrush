package de.ips.mlgrush.storage.config;

import com.google.gson.annotations.SerializedName;
import de.ips.mlgrush.MLGRushPlugin;
import lombok.Data;
import org.bukkit.Material;

import java.util.function.Supplier;

@Data
public class GeneralConfig implements Config {

    @SerializedName("messages")
    private MessageData messageData = new MessageData();

    @SerializedName("names")
    private NameData nameData = new NameData();

    @SerializedName("misc")
    private MiscData miscData = new MiscData();

    @Data
    public static class MessageData {
        @SerializedName("playerChallenged")
        private String playerChallenged = "Spieler herausgefordert";

        @SerializedName("playerChallenging")
        private String playerChallenging = "Du wurdest herausgefordert";

        @SerializedName("firstLocation")
        private String firstLocation = "Geh zur ersten Location und schreibe \"m\"";

        @SerializedName("secondLocation")
        private String secondLocation = "Geh zur ersten Location und schreibe \"m\"";

        @SerializedName("blueSpawn")
        private String blueSpawn = "Geh zum blauen Spawn und schreibe \"m\"";

        @SerializedName("redSpawn")
        private String redSpawn = "Geh zum roten Spawn und schreibe \"m\"";

        @SerializedName("enterName")
        private String enterName = "Schreibe jetzt den Namen \"m\"";

        @SerializedName("mapSetUp")
        private String mapSetUp = "Map erfolgreicht aufgesetzt";

        @SerializedName("setUpStarted")
        private String setUpStarted = "Setup gestartet.";

        @SerializedName("deathHeight")
        private String deathHeight = "Gehe jetzt auf die Todeshöhe";
    }

    @Data
    public static class NameData {
        @SerializedName("challengeItem")
        private String challengeItem = "§cHerausfordern";

        @SerializedName("settingsItem")
        private String settingsItem = "§cEinstellungen";
    }

    @Data
    public static class MiscData {
        @SerializedName("challengeMaterial")
        private String challengeMaterial = Material.DIAMOND_SWORD.toString();

        @SerializedName("settingsMaterial")
        private String settingsMaterial = Material.REDSTONE_COMPARATOR.toString();

        @SerializedName("challengeSlot")
        private int challengeSlot = 0;

        @SerializedName("settingsSlot")
        private int settingSlot = 8;

        @SerializedName("noPermission")
        private String noPermission = "Du hast keine Berechtigung hierzu";

        @SerializedName("createArenaPermission")
        private String createArenaPermission = "mlgrush.command.createarena";
    }

    public static final Supplier<String> DEFAULT_JSON = () ->
            MLGRushPlugin.getInstance().
                    getGson().
                    toJson(new GeneralConfig());

}
