package me.daqem.rightclickfarming.listeners;

import me.daqem.rightclickfarming.RightClickFarming;
import me.daqem.rightclickfarming.doers.BreakCrops;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerInteractListener implements Listener {

    private final RightClickFarming plugin;
    private final BreakCrops breakCrops;

    public PlayerInteractListener(RightClickFarming pl) {
        this.plugin = pl;
        this.breakCrops = new BreakCrops(plugin);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        if (block == null) {
            return;
        }
        if(event.getHand() == EquipmentSlot.OFF_HAND) return;
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Material material = block.getType();
            if (plugin.getConfig().getBoolean("hoe-required")) {
                Material mainHand = player.getInventory().getItemInMainHand().getType();
                if (mainHand == Material.WOODEN_HOE || mainHand == Material.STONE_HOE || mainHand == Material.IRON_HOE || mainHand == Material.GOLDEN_HOE || mainHand == Material.DIAMOND_HOE) {
                    breakCrops.breakCrops(block, player);
                }
            } else {
                if (material == Material.SUGAR_CANE || material == Material.BAMBOO ||
                        material == Material.PUMPKIN || material == Material.MELON ||
                        material == Material.KELP || material == Material.KELP_PLANT ||
                        material == Material.CACTUS || material == Material.SWEET_BERRY_BUSH){
                    event.setCancelled(true);
                }
                breakCrops.breakCrops(block, player);
            }
        }
    }
}