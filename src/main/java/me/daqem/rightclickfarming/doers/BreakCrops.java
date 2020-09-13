package me.daqem.rightclickfarming.doers;

import me.daqem.rightclickfarming.RightClickFarming;
import me.daqem.rightclickfarming.checkers.FullyGrownChecker;
import me.daqem.rightclickfarming.checkers.InventorySpaceChecker;
import me.daqem.rightclickfarming.checkers.ItemStackDropChecker;
import me.daqem.rightclickfarming.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.CocoaPlant;

public class BreakCrops {

    private final RightClickFarming plugin;
    private final FullyGrownChecker fullyGrownChecker;
    private final DropMath dropMath;
    private final PlantSeeds plantSeeds;
    private final CaneStack caneStack;
    private final BambooStack bambooStack;
    private final CactusStack cactusStack;
    private final KelpStack kelpStack;
    private final InventorySpaceChecker inventorySpaceChecker;
    private final ItemStackDropChecker itemStackDropChecker;
    private final EasyTranslator et;

    public BreakCrops(RightClickFarming pl) {
        this.plugin = pl;
        this.fullyGrownChecker = new FullyGrownChecker();
        this.dropMath = new DropMath();
        this.plantSeeds = new PlantSeeds();
        this.caneStack = new CaneStack(plugin);
        this.bambooStack = new BambooStack(plugin);
        this.cactusStack = new CactusStack(plugin);
        this.kelpStack = new KelpStack(plugin);
        this.inventorySpaceChecker = new InventorySpaceChecker();
        this.itemStackDropChecker = new ItemStackDropChecker();
        this.et = new EasyTranslator();

    }

    public void breakCrops(Block block, Player player) {
        Material material = block.getType();
        if (itemStackDropChecker.itemStackDropChecker(block) != null) {
            if (inventorySpaceChecker.getInventorySpace(player, itemStackDropChecker.itemStackDropChecker(block)) >= 1) {
                if (material == Material.WHEAT ||
                        material == Material.CARROTS ||
                        material == Material.POTATOES ||
                        material == Material.BEETROOTS ||
                        material == Material.NETHER_WART ||
                        material == Material.COCOA ||
                        material == Material.SWEET_BERRY_BUSH) {
                    if (fullyGrownChecker.crops(block)) {
                        if (material == Material.WHEAT && plugin.getConfig().getBoolean("crops.wheat.enabled")) {
                            block.setType(Material.AIR);
                            plantSeeds.plantSeeds("Wheat", block);
                            player.getInventory().addItem(new ItemStack(Material.WHEAT, plugin.getConfig().getInt("crops.wheat.multiplier")));
                            if (plugin.getConfig().getBoolean("crops.wheat.seed-drops")) {
                                player.getInventory().addItem(new ItemStack(Material.WHEAT_SEEDS, dropMath.getRandomNumberInRange(plugin.getConfig().getInt("crops.wheat.min-seed-drops"), plugin.getConfig().getInt("crops.wheat.max-seed-drops"))));
                            }
                        } else if (material == Material.CARROTS && plugin.getConfig().getBoolean("crops.carrot.enabled")) {
                            block.setType(Material.AIR);
                            plantSeeds.plantSeeds("Carrot", block);
                            player.getInventory().addItem(new ItemStack(Material.CARROT, dropMath.getRandomNumberInRange(plugin.getConfig().getInt("crops.carrot.min-drops"), plugin.getConfig().getInt("crops.carrot.max-drops"))));

                        } else if (material == Material.POTATOES && plugin.getConfig().getBoolean("crops.potato.enabled")) {
                            block.setType(Material.AIR);
                            plantSeeds.plantSeeds("Potato", block);
                            player.getInventory().addItem(new ItemStack(Material.POTATO, dropMath.getRandomNumberInRange(plugin.getConfig().getInt("crops.potato.min-drops"), plugin.getConfig().getInt("crops.potato.max-drops"))));
                            if (plugin.getConfig().getBoolean("crops.potato.poisonous-potato.enabled")) {
                                int percentage = dropMath.getRandomNumberInRange(0, 100);
                                if (percentage <= plugin.getConfig().getInt("crops.potato.poisonous-potato.drop-percentage")) {
                                    player.getInventory().addItem(new ItemStack(Material.POISONOUS_POTATO, dropMath.getRandomNumberInRange(plugin.getConfig().getInt("crops.potato.poisonous-potato.min-drops"), plugin.getConfig().getInt("crops.potato.poisonous-potato.max-drops"))));
                                }
                            }
                        } else if (material == Material.BEETROOTS && plugin.getConfig().getBoolean("crops.beetroot.enabled")) {
                            block.setType(Material.AIR);
                            plantSeeds.plantSeeds("Beetroot", block);
                            player.getInventory().addItem(new ItemStack(Material.BEETROOT, plugin.getConfig().getInt("crops.beetroot.multiplier")));
                            if (plugin.getConfig().getBoolean("crops.beetroot.seed-drops")) {
                                player.getInventory().addItem(new ItemStack(Material.BEETROOT_SEEDS, dropMath.getRandomNumberInRange(plugin.getConfig().getInt("crops.beetroot.min-seed-drops"), plugin.getConfig().getInt("crops.beetroot.max-seed-drops"))));

                            }
                        } else if (material == Material.NETHER_WART && plugin.getConfig().getBoolean("crops.netherwart.enabled")) {
                            block.setType(Material.AIR);
                            plantSeeds.plantSeeds("Nether Wart", block);
                            player.getInventory().addItem(new ItemStack(Material.NETHER_WART, dropMath.getRandomNumberInRange(plugin.getConfig().getInt("crops.netherwart.min-drops"), plugin.getConfig().getInt("crops.netherwart.max-drops"))));

                        } else if (material == Material.COCOA && plugin.getConfig().getBoolean("crops.cocoabeans.enabled")) {
                            final BlockFace face = ((CocoaPlant) block.getState().getData()).getFacing();
                            BlockState blockState = block.getState();
                            CocoaPlant cocoaPlant = new CocoaPlant(CocoaPlant.CocoaPlantSize.SMALL, face);
                            block.setType(material);
                            blockState.setData(cocoaPlant);
                            blockState.update();
                            player.getInventory().addItem(new ItemStack(Material.COCOA_BEANS, dropMath.getRandomNumberInRange(plugin.getConfig().getInt("crops.cocoabeans.min-drops"), plugin.getConfig().getInt("crops.cocoabeans.max-drops"))));
                        } else if (material == Material.SWEET_BERRY_BUSH && plugin.getConfig().getBoolean("crops.sweetberries.enabled")) {
                            Ageable ageable = (Ageable) block.getState().getBlockData();
                            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                                if (ageable.getAge() == 2) {
                                    player.getInventory().addItem(new ItemStack(Material.SWEET_BERRIES, dropMath.getRandomNumberInRange(plugin.getConfig().getInt("crops.sweetberries.half-grown.min-drops"), plugin.getConfig().getInt("crops.sweetberries.half-grown.max-drops"))));
                                } else {
                                    player.getInventory().addItem(new ItemStack(Material.SWEET_BERRIES, dropMath.getRandomNumberInRange(plugin.getConfig().getInt("crops.sweetberries.fully-grown.min-drops"), plugin.getConfig().getInt("crops.sweetberries.fully-grown.max-drops"))));
                                }
                                ageable.setAge(ageable.getAge() - 2);
                                block.setBlockData(ageable);
                            }, 1L);
                        }
                    }
                } else if (material == Material.SUGAR_CANE && plugin.getConfig().getBoolean("crops.sugarcane.enabled")) {
                    caneStack.caneStack(player, block);
                } else if (material == Material.CACTUS && plugin.getConfig().getBoolean("crops.cactus.enabled")) {
                    cactusStack.cactusStack(player, block);
                } else if (material == Material.BAMBOO && plugin.getConfig().getBoolean("crops.bamboo.enabled")) {
                    bambooStack.bambooStack(player, block);
                } else if (material == Material.KELP_PLANT && plugin.getConfig().getBoolean("crops.kelp.enabled")) {
                    kelpStack.kelpStack(player, block);
                } else if (material == Material.MELON && plugin.getConfig().getBoolean("crops.melon.enabled")) {
                    block.setType(Material.AIR);
                    player.getInventory().addItem(new ItemStack(Material.MELON_SLICE, dropMath.getRandomNumberInRange(plugin.getConfig().getInt("crops.melon.min-drops"), plugin.getConfig().getInt("crops.melon.max-drops"))));
                } else if (material == Material.PUMPKIN && plugin.getConfig().getBoolean("crops.pumpkin.enabled")) {
                    block.setType(Material.AIR);
                    player.getInventory().addItem(new ItemStack(Material.PUMPKIN, plugin.getConfig().getInt("crops.pumpkin.multiplier")));
                }
            } else {
                et.STMTCS(player, "&6RightClickFarming > &cYour inventory is full.");
            }
        }
    }
}
