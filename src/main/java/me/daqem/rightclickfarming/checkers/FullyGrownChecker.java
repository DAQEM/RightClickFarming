package me.daqem.rightclickfarming.checkers;

import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.NetherWartsState;
import org.bukkit.block.Block;
import org.bukkit.material.Crops;
import org.bukkit.material.MaterialData;
import org.bukkit.material.NetherWarts;

public class FullyGrownChecker {

    public boolean isFullyGrown(Block block) {
        if (block.getType() == Material.WHEAT ||
                block.getType() == Material.CARROTS ||
                block.getType() == Material.POTATOES ||
                block.getType() == Material.BEETROOTS ||
                block.getType() == Material.NETHER_WART) {
            MaterialData materialData = block.getState().getData();

            if (materialData instanceof Crops) {
                return (((Crops) materialData).getState() == CropState.RIPE);
            } else if (materialData instanceof NetherWarts) {
                NetherWarts netherWarts = (NetherWarts)materialData;
                return netherWarts.getState() == NetherWartsState.RIPE;
            } else return false;

        }
        return false;
    }
}
