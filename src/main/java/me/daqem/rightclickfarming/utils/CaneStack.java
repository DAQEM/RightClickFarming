package me.daqem.rightclickfarming.utils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CaneStack {

    public void caneStack(Player player, Block block) {
        Material material = block.getType();
        int caneAmount = 0;
        while (material == Material.SUGAR_CANE) {
            block = block.getRelative(0, 1, 0);
            material = block.getType();
            if (material == Material.SUGAR_CANE) {
                caneAmount++;
            }
        }
        for (int i = 0; i < caneAmount + 1; i++) {
            block = block.getRelative(0, -1, 0);
            material = block.getType();
            if (i == caneAmount) {
                block.setType(Material.AIR);
                player.getInventory().addItem(new ItemStack(Material.SUGAR_CANE, caneAmount + 1));
                return;
            }
            if (material == Material.SUGAR_CANE) {
                block.setType(Material.AIR);
            }
        }
    }

}
