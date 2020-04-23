package de.ips.mlgrush.inventory;

import de.ips.mlgrush.MLGRushPlugin;
import de.ips.mlgrush.storage.config.GeneralConfig;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LobbyInventory {

    public static final ItemStack CHALLENGE_ITEM;
    public static final ItemStack SETTINGS_ITEM;

    static {
        ItemMeta meta;

        GeneralConfig.MiscData miscData = MLGRushPlugin.getInstance().getGeneralConfig().getMiscData();
        GeneralConfig.NameData nameData = MLGRushPlugin.getInstance().getGeneralConfig().getNameData();

        CHALLENGE_ITEM = new ItemStack(Material.valueOf(miscData.getChallengeMaterial().toUpperCase()));
        meta = CHALLENGE_ITEM.getItemMeta();
        meta.spigot().setUnbreakable(true);
        meta.setDisplayName(nameData.getChallengeItem());
        meta.addItemFlags(ItemFlag.values());
        CHALLENGE_ITEM.setItemMeta(meta);

        SETTINGS_ITEM = new ItemStack(Material.valueOf(miscData.getSettingsMaterial().toUpperCase()));
        meta = SETTINGS_ITEM.getItemMeta();
        meta.spigot().setUnbreakable(true);
        meta.setDisplayName(nameData.getSettingsItem());
        meta.addItemFlags(ItemFlag.values());
        SETTINGS_ITEM.setItemMeta(meta);
    }

    public static void apply(Player player) {
        player.getInventory()
                .setItem(MLGRushPlugin.getInstance().getGeneralConfig().getMiscData().getChallengeSlot(),
                        CHALLENGE_ITEM);

        player.getInventory()
                .setItem(MLGRushPlugin.getInstance().getGeneralConfig().getMiscData().getSettingSlot(),
                        SETTINGS_ITEM);
    }

}
