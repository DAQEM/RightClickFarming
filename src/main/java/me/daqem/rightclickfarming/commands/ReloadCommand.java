package me.daqem.rightclickfarming.commands;

import me.daqem.rightclickfarming.RightClickFarming;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class ReloadCommand {

    private final RightClickFarming plugin;

    public ReloadCommand(RightClickFarming pl) {
        this.plugin = pl;
    }

    public void reloadCommand(CommandSender sender) {
        if (sender.hasPermission("rightclickfarming.reload")) {
            plugin.reloadConfig();
            sender.sendMessage("CONFIG RELOADED!");
        }
    }
}
