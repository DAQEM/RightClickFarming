package me.daqem.rightclickfarming.utils;

import me.daqem.rightclickfarming.RightClickFarming;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class BambooStack {

    private final RightClickFarming plugin;

    public BambooStack(RightClickFarming pl) {
        this.plugin = pl;

    }

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
                if (plugin.getConfig().getBoolean("drop-items-on-ground")) {
                    Objects.requireNonNull(block.getLocation().getWorld()).dropItem(block.getLocation(), new ItemStack(Material.BAMBOO, (bambooAmount + 1) * plugin.getConfig().getInt("crops.bamboo.multiplier")));
                } else {
                    player.getInventory().addItem(new ItemStack(Material.BAMBOO, (bambooAmount + 1) * plugin.getConfig().getInt("crops.bamboo.multiplier")));
                }
                return;
            }
            if (material == Material.BAMBOO) {
                block.setType(Material.AIR);
            }
        }
    }
}
