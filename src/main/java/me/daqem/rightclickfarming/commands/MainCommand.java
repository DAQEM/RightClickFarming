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
        this.setCommand = new SetCommand();
        this.helpCommand = new HelpCommand();
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            helpCommand.basicHelpCommand(sender);
        }
        if (args[0].equalsIgnoreCase("reload")) {
            if (args.length == 1) {
                reloadCommand.reloadCommand(sender);
            } else {
                helpCommand.basicHelpCommand(sender);
            }
        }
        if (args[0].equalsIgnoreCase("set")) {
            setCommand.setCommand(sender, args);
        }
        if (args[0].equalsIgnoreCase("help")) {
            helpCommand.basicHelpCommand(sender);
        }

        return false;
    }
}
