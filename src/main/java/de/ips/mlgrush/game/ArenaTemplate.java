package de.ips.mlgrush.game;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import de.ips.mlgrush.api.schematic.Schematic;
import de.ips.mlgrush.misc.Vector3;
import lombok.Data;

import java.lang.reflect.Type;

@Data
public class ArenaTemplate {

    public static class ArenaTemplateDeserializer implements JsonDeserializer<ArenaTemplate> {

        @Override
        public ArenaTemplate deserialize(JsonElement element, Type type,
                                         JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = element.getAsJsonObject();
            Schematic schematic = Schematic.fromFile(jsonObject.get("schematicName").getAsString());
            return new ArenaTemplate(
                    jsonObject.get("deathHeight").getAsInt(),
                    schematic,
                    context.deserialize(jsonObject.get("blueSpawnOffset"), Vector3.class),
                    context.deserialize(jsonObject.get("redSpawnOffset"), Vector3.class)
            );
        }

    }

    @SerializedName("deathHeight")
    private final int deathHeight;

    @SerializedName("schematicName")
    private String schematicName;

    private final transient Schematic schematic;

    @SerializedName("blueSpawnOffset")
    private final Vector3 blueSpawnOffset;

    @SerializedName("redSpawnOffset")
    private final Vector3 redSpawnOffset;

}
