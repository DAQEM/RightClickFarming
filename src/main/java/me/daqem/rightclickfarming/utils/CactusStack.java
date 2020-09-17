package me.daqem.rightclickfarming.utils;

import me.daqem.rightclickfarming.RightClickFarming;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class CactusStack {

    private final RightClickFarming plugin;

    public CactusStack(RightClickFarming pl) {
        this.plugin = pl;

    }
    public void cactusStack(Player player, Block block) {
        Material material = block.getType();
        int caneAmount = 0;
        while (material == Material.CACTUS) {
            block = block.getRelative(0, 1, 0);
            material = block.getType();
            if (material == Material.CACTUS) {
                caneAmount++;
            }
        }
        for (int i = 0; i < caneAmount + 1; i++) {
            block = block.getRelative(0, -1, 0);
            material = block.getType();
            if (i == caneAmount) {
                block.setType(Material.AIR);
                if (plugin.getConfig().getBoolean("drop-items-on-ground")) {
                    Objects.requireNonNull(block.getLocation().getWorld()).dropItem(block.getLocation(), new ItemStack(Material.CACTUS, (caneAmount + 1) * plugin.getConfig().getInt("crops.cactus.multiplier")));
                } else {
                    player.getInventory().addItem(new ItemStack(Material.CACTUS, (caneAmount + 1) * plugin.getConfig().getInt("crops.cactus.multiplier")));
                }
                return;
            }
            if (material == Material.CACTUS) {
                block.setType(Material.AIR);
            }
        }
    }
}
