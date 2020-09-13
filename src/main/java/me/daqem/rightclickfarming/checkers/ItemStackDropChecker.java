package me.daqem.rightclickfarming.checkers;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class ItemStackDropChecker {

    public ItemStack itemStackDropChecker(Block block) {
        Material material = block.getType();
        if (material == Material.WHEAT) {
            return new ItemStack(Material.WHEAT);
        } else if (material == Material.CARROTS) {
            return new ItemStack(Material.CARROT);
        } else if (material == Material.POTATOES) {
            return new ItemStack(Material.POTATO);
        } else if (material == Material.BEETROOTS) {
            return new ItemStack(Material.BEETROOT);
        } else if (material == Material.NETHER_WART) {
            return new ItemStack(Material.NETHER_WART);
        } else if (material == Material.COCOA) {
            return new ItemStack(Material.COCOA_BEANS);
        } else if (material == Material.SUGAR_CANE) {
            return new ItemStack(Material.SUGAR_CANE);
        } else if (material == Material.CACTUS) {
            return new ItemStack(Material.CACTUS);
        } else if (material == Material.BAMBOO) {
            return new ItemStack(Material.BAMBOO);
        } else if (material == Material.KELP_PLANT) {
            return new ItemStack(Material.KELP);
        } else if (material == Material.MELON) {
            return new ItemStack(Material.MELON_SLICE);
        } else if (material == Material.PUMPKIN) {
            return new ItemStack(Material.PUMPKIN);
        } else if (material == Material.SWEET_BERRY_BUSH) {
            return new ItemStack(Material.SWEET_BERRIES);
        }
        return null;
    }
}
