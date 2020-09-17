package me.daqem.rightclickfarming.utils;

import me.daqem.rightclickfarming.RightClickFarming;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class KelpStack {

    private final RightClickFarming plugin;

    public KelpStack(RightClickFarming pl) {
        this.plugin = pl;

    }
    public void kelpStack(Player player, Block block) {
        Material material = block.getType();
        int kelpAmount = 0;
        while (material == Material.KELP_PLANT || material == Material.KELP) {
            block = block.getRelative(0, 1, 0);
            material = block.getType();
            if (material == Material.KELP_PLANT || material == Material.KELP) {
                kelpAmount++;
            }
        }
        for (int i = 0; i < kelpAmount + 1; i++) {
            block = block.getRelative(0, -1, 0);
            material = block.getType();
            if (i == kelpAmount) {
                block.setType(Material.WATER);
                if (plugin.getConfig().getBoolean("drop-items-on-ground")) {
                    Objects.requireNonNull(block.getLocation().getWorld()).dropItem(block.getLocation(), new ItemStack(Material.KELP, (kelpAmount + 1) * plugin.getConfig().getInt("crops.kelp.multiplier")));
                } else {
                    player.getInventory().addItem(new ItemStack(Material.KELP, (kelpAmount + 1) * plugin.getConfig().getInt("crops.kelp.multiplier")));
                }
                return;
            }
            if (material == Material.KELP_PLANT || material == Material.KELP) {
                block.setType(Material.WATER);
            }
        }
    }
}
