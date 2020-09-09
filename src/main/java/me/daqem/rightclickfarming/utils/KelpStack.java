package me.daqem.rightclickfarming.utils;

import me.daqem.rightclickfarming.RightClickFarming;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KelpStack {

    private final RightClickFarming plugin;

    public KelpStack(RightClickFarming pl) {
        this.plugin = pl;

    }
    public void kelpStack(Player player, Block block) {
        Material material = block.getType();
        int caneAmount = 0;
        while (material == Material.KELP_PLANT || material == Material.KELP) {
            block = block.getRelative(0, 1, 0);
            material = block.getType();
            if (material == Material.KELP_PLANT || material == Material.KELP) {
                caneAmount++;
            }
        }
        for (int i = 0; i < caneAmount + 1; i++) {
            block = block.getRelative(0, -1, 0);
            material = block.getType();
            if (i == caneAmount) {
                block.setType(Material.WATER);
                player.getInventory().addItem(new ItemStack(Material.KELP, (caneAmount + 1) * plugin.getConfig().getInt("kelp.multiplier")));
                return;
            }
            if (material == Material.KELP_PLANT || material == Material.KELP) {
                block.setType(Material.WATER);
            }
        }
    }
}
