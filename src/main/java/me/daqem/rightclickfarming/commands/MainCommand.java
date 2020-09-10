package me.daqem.rightclickfarming.commands;

import me.daqem.rightclickfarming.RightClickFarming;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {

    private final RightClickFarming plugin;
    private final ReloadCommand reloadCommand;

    public MainCommand(RightClickFarming pl) {
        this.plugin = pl;
        this.reloadCommand = new ReloadCommand(plugin);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            //HELP COMMAND
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                reloadCommand.reloadCommand(sender);
            }
        }
        return false;
    }
}
