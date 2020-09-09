package me.daqem.rightclickfarming.checkers;

import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.NetherWartsState;
import org.bukkit.block.Block;
import org.bukkit.material.CocoaPlant;
import org.bukkit.material.Crops;
import org.bukkit.material.MaterialData;
import org.bukkit.material.NetherWarts;

public class FullyGrownChecker {

    public boolean crops(Block block) {
        MaterialData materialData = block.getState().getData();

        if (materialData instanceof Crops) {
            return (((Crops) materialData).getState() == CropState.RIPE);
        } else if (materialData instanceof NetherWarts) {
            NetherWarts netherWarts = (NetherWarts) materialData;
            return netherWarts.getState() == NetherWartsState.RIPE;
        } else if (materialData instanceof CocoaPlant) {
            return ((CocoaPlant) materialData).getSize() == CocoaPlant.CocoaPlantSize.LARGE;
        } else return false;
    }
}
