package de.ips.mlgrush.api.schematic;

import com.google.common.reflect.TypeToken;
import com.google.gson.annotations.SerializedName;
import de.ips.mlgrush.MLGRushPlugin;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Schematic {

    @SerializedName("name")
    private final String name;

    @SerializedName("blocks")
    private final Set<BlockDef> blockDefs;

    public static Schematic from(String name, Location min, Location max) {
        if (!min.getWorld().equals(max.getWorld()))
            return null;

        Set<BlockDef> blockDefs = new HashSet<>();

        for (int x = min.getBlockX(); x < max.getBlockX(); ++x) {
            for (int y = min.getBlockY(); y < max.getBlockY(); ++y) {
                for (int z = min.getBlockZ(); x < max.getBlockZ(); ++z) {
                    Block block = min.getWorld().getBlockAt(x, y, z);
                    blockDefs.add(BlockDef.of(block.getType(), x - min.getBlockX(), y - min.getBlockY(),
                            z - min.getBlockY(), block.getData()));
                }
            }
        }

        return new Schematic(name, blockDefs);
    }

    @SneakyThrows
    public static Schematic fromFile(String name) {
        return MLGRushPlugin.getInstance().getGson().fromJson(
                Files.newBufferedReader(Paths.get("plugins", "MLGRush", "schematic", name + ".json"),
                        StandardCharsets.UTF_8), new TypeToken<Schematic>() {
                }.getType());
    }

    public void paste(Location location) {
        blockDefs.forEach(blockDef -> {
            Block block = location.getWorld().getBlockAt(
                    blockDef.getX() + location.getBlockX(),
                    blockDef.getY() + location.getBlockY(),
                    blockDef.getZ() + location.getBlockZ()
            );

            block.setType(blockDef.getMaterial());
            block.setData(blockDef.getData());
        });
    }
}
