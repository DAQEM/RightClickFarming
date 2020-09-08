package me.daqem.rightclickfarming.doers;

import me.daqem.rightclickfarming.RightClickFarming;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class PlantSeeds {

    private final RightClickFarming plugin;

    public PlantSeeds(RightClickFarming pl) {
        this.plugin = pl;
    }

    public void plantSeeds(String crop, Block block) {
        Block blockLoc = block.getLocation().getBlock();
        if (crop.equals("Wheat")) {
            blockLoc.setType(Material.WHEAT);
        }
        if (crop.equals("Carrot")) {
            blockLoc.setType(Material.CARROTS);
        }
        if (crop.equals("Potato")) {
            blockLoc.setType(Material.POTATOES);
        }
        if (crop.equals("Beetroot")) {
            blockLoc.setType(Material.BEETROOTS);
        }
        if (crop.equals("Nether Wart")) {
            blockLoc.setType(Material.NETHER_WART);
        }
    }
}
