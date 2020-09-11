package me.daqem.rightclickfarming.utils;

import me.daqem.rightclickfarming.RightClickFarming;
import me.daqem.rightclickfarming.checkers.FullyGrownChecker;
import me.daqem.rightclickfarming.doers.PlantSeeds;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CaneStack {

    private final RightClickFarming plugin;

    public CaneStack(RightClickFarming pl) {
        this.plugin = pl;

    }

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
                player.getInventory().addItem(new ItemStack(Material.SUGAR_CANE, (caneAmount + 1) * plugin.getConfig().getInt("crops.sugarcane.multiplier")));
                return;
            }
            if (material == Material.SUGAR_CANE) {
                block.setType(Material.AIR);
            }
        }
    }
}
