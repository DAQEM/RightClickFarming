package me.daqem.rightclickfarming.utils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BambooStack {

    public void bambooStack(Player player, Block block) {
        Material material = block.getType();
        int bambooAmount = 0;
        while (material == Material.BAMBOO) {
            block = block.getRelative(0, 1, 0);
            material = block.getType();
            if (material == Material.BAMBOO) {
                bambooAmount++;
            }
        }
        for (int i = 0; i < bambooAmount + 1; i++) {
            block = block.getRelative(0, -1, 0);
            material = block.getType();
            if (i == bambooAmount) {
                block.setType(Material.AIR);
                player.getInventory().addItem(new ItemStack(Material.BAMBOO, bambooAmount + 1));
                return;
            }
            if (material == Material.BAMBOO) {
                block.setType(Material.AIR);
            }
        }
    }
}
