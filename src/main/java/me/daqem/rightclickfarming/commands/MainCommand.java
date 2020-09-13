package me.daqem.rightclickfarming.commands;

import me.daqem.rightclickfarming.RightClickFarming;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {

    private final RightClickFarming plugin;
    private final ReloadCommand reloadCommand;
    private final SetCommand setCommand;
    private final HelpCommand helpCommand;

    public MainCommand(RightClickFarming pl) {
        this.plugin = pl;
        this.reloadCommand = new ReloadCommand(plugin);
        this.setCommand = new SetCommand(plugin);
        this.helpCommand = new HelpCommand(plugin);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            helpCommand.helpCommand(sender, null);
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                reloadCommand.reloadCommand(sender);
            }
            if (args[0].equalsIgnoreCase("set")) {
                setCommand.setCommand(sender, args);
            }
            if (args[0].equalsIgnoreCase("help")) {
                helpCommand.helpCommand(sender, null);
            }
        }
        if (args.length == 2 || args.length == 3 || args.length == 4 || args.length == 5) {
            if (args[0].equalsIgnoreCase("set")) {
                setCommand.setCommand(sender, args);
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("help")) {
                    helpCommand.helpCommand(sender, args[1].toLowerCase());
                }
            }
        }
        return false;
    }
}
