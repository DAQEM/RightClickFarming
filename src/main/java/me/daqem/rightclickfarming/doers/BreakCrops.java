package me.daqem.rightclickfarming.doers;

import me.daqem.rightclickfarming.RightClickFarming;
import me.daqem.rightclickfarming.checkers.FullyGrownChecker;
import me.daqem.rightclickfarming.utils.BambooStack;
import me.daqem.rightclickfarming.utils.CaneStack;
import me.daqem.rightclickfarming.utils.DropMath;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BreakCrops {

    private final RightClickFarming plugin;
    private final FullyGrownChecker fullyGrownChecker;
    private final DropMath dropMath;
    private final PlantSeeds plantSeeds;
    private final CaneStack caneStack;
    private final BambooStack bambooStack;

    public BreakCrops(RightClickFarming pl) {
        this.plugin = pl;
        this.fullyGrownChecker = new FullyGrownChecker();
        this.dropMath = new DropMath();
        this.plantSeeds = new PlantSeeds();
        this.caneStack = new CaneStack(plugin);
        this.bambooStack = new BambooStack(plugin);

    }

    public void breakCrops(Block block, Player player) {
        Material material = block.getType();
        if (fullyGrownChecker.isFullyGrown(block)) {
            if (material == Material.WHEAT && plugin.getConfig().getBoolean("wheat.enabled")) {
                block.setType(Material.AIR);
                plantSeeds.plantSeeds("Wheat", block);
                player.getInventory().addItem(new ItemStack(Material.WHEAT, plugin.getConfig().getInt("wheat.wheat-drop-amount")));
                if (plugin.getConfig().getBoolean("wheat.seed-drops")) {
                    player.getInventory().addItem(new ItemStack(Material.WHEAT_SEEDS, dropMath.getRandomNumberInRange(plugin.getConfig().getInt("wheat.min-seed-drops"), plugin.getConfig().getInt("wheat.max-seed-drops"))));

                }
            }
            else if (material == Material.CARROT && plugin.getConfig().getBoolean("carrot.enabled")) {
                block.setType(Material.AIR);
                plantSeeds.plantSeeds("Carrot", block);
                player.getInventory().addItem(new ItemStack(Material.CARROT, dropMath.getRandomNumberInRange(plugin.getConfig().getInt("carrot.min-drops"), plugin.getConfig().getInt("carrot.max-drops"))));

            }
            else if (material == Material.POTATOES && plugin.getConfig().getBoolean("potato.enabled")) {
                block.setType(Material.AIR);
                plantSeeds.plantSeeds("Potato", block);
                player.getInventory().addItem(new ItemStack(Material.POTATO, dropMath.getRandomNumberInRange(plugin.getConfig().getInt("potato.min-drops"), plugin.getConfig().getInt("potato.max-drops"))));
                if (plugin.getConfig().getBoolean("potato.poisonous-potato.enabled")) {
                    int percentage = dropMath.getRandomNumberInRange(0, 100);
                    if (percentage <= plugin.getConfig().getInt("potato.poisonous-potato.drop-percentage")) {
                        player.getInventory().addItem(new ItemStack(Material.POISONOUS_POTATO, dropMath.getRandomNumberInRange(plugin.getConfig().getInt("potato.poisonous-potato.min-drops"), plugin.getConfig().getInt("potato.poisonous-potato.max-drops"))));
                    }
                }
            }
            else if (material == Material.BEETROOTS && plugin.getConfig().getBoolean("beetroot.enabled")) {
                block.setType(Material.AIR);
                plantSeeds.plantSeeds("Beetroot", block);
                player.getInventory().addItem(new ItemStack(Material.BEETROOT, plugin.getConfig().getInt("beetroot.beetroot-drop-amount")));
                if (plugin.getConfig().getBoolean("beetroot.seed-drops")) {
                    player.getInventory().addItem(new ItemStack(Material.BEETROOT_SEEDS, dropMath.getRandomNumberInRange(plugin.getConfig().getInt("beetroot.min-seed-drops"), plugin.getConfig().getInt("beetroot.max-seed-drops"))));

                }
            }
            else if (material == Material.NETHER_WART && plugin.getConfig().getBoolean("netherwart.enabled")) {
                block.setType(Material.AIR);
                plantSeeds.plantSeeds("Nether Wart", block);
                player.getInventory().addItem(new ItemStack(Material.NETHER_WART, dropMath.getRandomNumberInRange(plugin.getConfig().getInt("netherwart.min-drops"), plugin.getConfig().getInt("netherwart.max-drops"))));

            }
        } else if (material == Material.SUGAR_CANE && plugin.getConfig().getBoolean("sugarcane.enabled")) {
            caneStack.caneStack(player, block);
        } else if (material == Material.BAMBOO && plugin.getConfig().getBoolean("bamboo.enabled")) {
            bambooStack.bambooStack(player, block);
        } else {
            player.sendMessage("NOT A KNOW CROP YET.");
        }
    }
}
