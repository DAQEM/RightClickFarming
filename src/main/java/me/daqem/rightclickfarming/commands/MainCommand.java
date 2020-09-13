package me.daqem.rightclickfarming.commands;

import me.daqem.rightclickfarming.RightClickFarming;
import me.daqem.rightclickfarming.utils.EasyTranslator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {

    private final RightClickFarming plugin;
    private final ReloadCommand reloadCommand;
    private final SetCommand setCommand;
    private final HelpCommand helpCommand;
    private final EasyTranslator et;

    public MainCommand(RightClickFarming pl) {
        this.plugin = pl;
        this.reloadCommand = new ReloadCommand(plugin);
        this.setCommand = new SetCommand(plugin);
        this.helpCommand = new HelpCommand(plugin);
        this.et = new EasyTranslator();
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
            if (args[0].equalsIgnoreCase("settings")) {
                helpCommand.helpCommand(sender, args[0].toLowerCase());
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
                if (args[0].equalsIgnoreCase("settings") && args[1].equalsIgnoreCase("hoe-required")) {
                    helpCommand.helpCommand(sender, args[0].toLowerCase());
                }
            }
            if (args.length == 3) {
                if (args[0].equalsIgnoreCase("settings")) {
                    if (args[1].equalsIgnoreCase("hoe-required")) {
                        if (args[2].equalsIgnoreCase("true")) {
                            plugin.getConfig().set("hoe-required", true);
                            et.STMTCS(sender, "&6RightClickFarming > &eHoe required has been &aenabled&e.");
                            plugin.saveConfig();
                            plugin.reloadConfig();
                        } else if (args[2].equalsIgnoreCase("false")) {
                            plugin.getConfig().set("hoe-required", false);
                            et.STMTCS(sender, "&6RightClickFarming > &eHoe required has been &cdisabled&e.");
                            plugin.saveConfig();
                            plugin.reloadConfig();
                        } else {
                            helpCommand.helpCommand(sender, args[0].toLowerCase());
                        }
                    } else {
                        helpCommand.helpCommand(sender, args[0].toLowerCase());
                    }
                }
            }
        }
        return false;
    }
}
