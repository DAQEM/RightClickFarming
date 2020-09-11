package me.daqem.rightclickfarming.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class EasyTranslator {

    public void STMTCS(CommandSender sender, String message) { // Send Translated Message To Command Sender.
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
