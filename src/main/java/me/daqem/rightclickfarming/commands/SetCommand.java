package me.daqem.rightclickfarming.commands;

import me.daqem.rightclickfarming.RightClickFarming;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class SetCommand {

    private final RightClickFarming plugin;
    private final HelpCommand helpCommand;

    public SetCommand(RightClickFarming pl) {
        this.plugin = pl;
        this.helpCommand = new HelpCommand(plugin);
    }


    public void setCommand(CommandSender sender, String[] args) {
        Set<String> cropsSet = Objects.requireNonNull(plugin.getConfig().getConfigurationSection("crops")).getKeys(false);
        List<String> cropsArray = new ArrayList<>(cropsSet);

        if (args.length == 1) {
            helpCommand.helpCommand(sender, "set");
        }
        if (args.length == 2) {
            for (String cropType : cropsArray) {
                if (args[1].equals(cropType)) {
                    helpCommand.helpCommand(sender, cropType);
                }
            }
        }
    }
}
