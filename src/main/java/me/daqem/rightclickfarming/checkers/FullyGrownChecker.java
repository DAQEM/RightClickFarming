package me.daqem.rightclickfarming.checkers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;

public class FullyGrownChecker {

    public boolean crops(Block block) {
        BlockData blockData = block.getState().getBlockData();
        if (blockData instanceof Ageable) {
            Ageable ageable = (Ageable) blockData;
            if (block.getType() == Material.SWEET_BERRY_BUSH) {
                return ageable.getAge() >= ageable.getMaximumAge() || ageable.getAge() >= ageable.getMaximumAge()-1;
            } else {
                return ageable.getAge() >= ageable.getMaximumAge();
            }
        } else {
            Bukkit.broadcastMessage(ChatColor.RED + "No Instanceof.");
            return false;
        }
    }

}
