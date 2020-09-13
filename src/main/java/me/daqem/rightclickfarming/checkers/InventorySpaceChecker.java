package me.daqem.rightclickfarming.checkers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventorySpaceChecker {

    public int getInventorySpace(Player player, ItemStack item) {
        int freeSpace = 0;
        byte b;
        int i;
        if (player.getInventory().getHelmet() == null) {
            freeSpace -= 64;
        }
        if (player.getInventory().getChestplate() == null) {
            freeSpace -= 64;
        }
        if (player.getInventory().getLeggings() == null) {
            freeSpace -= 64;
        }
        if (player.getInventory().getBoots() == null) {
            freeSpace -= 64;
        }
        freeSpace -= 64;
        ItemStack[] arrayOfItemStack;
        for (i = (arrayOfItemStack = player.getInventory().getContents()).length, b = 0; b < i; ) {
            ItemStack itemStack = arrayOfItemStack[b];
            if (itemStack == null || itemStack.getType() == Material.AIR) {
                freeSpace += item.getMaxStackSize();
            } else if (itemStack.isSimilar(item)) {
                freeSpace += item.getMaxStackSize() - itemStack.getAmount();
            }
            b++;
        }
        return freeSpace;
    }
}
