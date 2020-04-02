package de.ips.mlgrush.api.schematic;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;

@Data
@RequiredArgsConstructor(staticName = "of")
public class BlockDef {

    @SerializedName("material")
    private final Material material;

    @SerializedName("x")
    private final int x;

    @SerializedName("y")
    private final int y;

    @SerializedName("z")
    private final int z;

    @SerializedName("data")
    private final byte data;

}
