package me.daqem.rightclickfarming.commands;

import me.daqem.rightclickfarming.RightClickFarming;
import me.daqem.rightclickfarming.checkers.IntegerCheck;
import me.daqem.rightclickfarming.utils.CapitalizeLetter;
import me.daqem.rightclickfarming.utils.EasyTranslator;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class SetCommand {

    private final RightClickFarming plugin;
    private final HelpCommand helpCommand;
    private final IntegerCheck integerCheck;
    private final EasyTranslator et;
    private final CapitalizeLetter capitalizeLetter;

    public SetCommand(RightClickFarming pl) {
        this.plugin = pl;
        this.helpCommand = new HelpCommand(plugin);
        this.integerCheck = new IntegerCheck();
        this.et = new EasyTranslator();
        this.capitalizeLetter = new CapitalizeLetter();
    }


    public void setCommand(CommandSender sender, String[] args) {
        Set<String> cropsSet = Objects.requireNonNull(plugin.getConfig().getConfigurationSection("crops")).getKeys(false);
        List<String> cropsArray = new ArrayList<>(cropsSet);

        if (args.length == 1) {
            helpCommand.helpCommand(sender, "set");
        } else if (args.length == 2 || args.length == 3) {
            for (String cropType : cropsArray) {
                if (args[1].equals(cropType)) {
                    helpCommand.helpCommand(sender, cropType);
                }
            }
        } else if (args.length == 4) {
            for (String cropType : cropsArray) {
                if (args[1].equalsIgnoreCase(cropType)) {
                    if (args[1].equalsIgnoreCase("wheat") || args[1].equalsIgnoreCase("beetroot")) {
                        if (args[3].equalsIgnoreCase("true") || args[3].equalsIgnoreCase("false")) {
                            if (args[2].equalsIgnoreCase("enabled")) {
                                if (args[3].equals("true")) {
                                    plugin.getConfig().set("crops." + args[1].toLowerCase() + ".enabled", true);
                                    et.STMTCS(sender, "&6RightClickFarming > &e" + capitalizeLetter.capitalize(args[1].toLowerCase()) + " has been &aenabled&e.");
                                } else {
                                    plugin.getConfig().set("crops." + args[1].toLowerCase() + ".enabled", false);
                                    et.STMTCS(sender, "&6RightClickFarming > &e" + capitalizeLetter.capitalize(args[1].toLowerCase()) + " has been &cdisabled&e.");
                                }
                                plugin.saveConfig();
                                plugin.reloadConfig();
                            } else if (args[2].equalsIgnoreCase("seed-drops")) {
                                if (args[3].equals("true")) {
                                    plugin.getConfig().set("crops." + args[1].toLowerCase() + ".seed-drops", true);
                                    et.STMTCS(sender, "&6RightClickFarming > &eSeed drops for " + args[1].toLowerCase() + " have been &aenabled&e.");
                                } else {
                                    plugin.getConfig().set("crops." + args[1].toLowerCase() + ".seed-drops", false);
                                    et.STMTCS(sender, "&6RightClickFarming > &eSeed drops for " + args[1].toLowerCase() + " have been &cdisabled&e.");
                                }
                                plugin.saveConfig();
                                plugin.reloadConfig();
                            } else {
                                helpCommand.helpCommand(sender, args[1].toLowerCase());
                            }
                        }
                        else if (integerCheck.isInteger(args[3])) {
                            int args3 = Integer.parseInt(args[3]);
                            if (args[2].equalsIgnoreCase("multiplier")) {
                                plugin.getConfig().set("crops." + args[1].toLowerCase() + ".multiplier", args3);
                                et.STMTCS(sender, "&6RightClickFarming > &eMultiplier for " + args[1].toLowerCase() + " have been set to &6" + args3 + "&e.");
                                plugin.saveConfig();
                                plugin.reloadConfig();
                            } else if (args[2].equalsIgnoreCase("min-seed-drops")) {
                                if (args3 >= plugin.getConfig().getInt("crops." + args[1].toLowerCase() + ".max-seed-drops")) {
                                    et.STMTCS(sender, "&6RightClickFarming > &eThe minimum seed drops must not be higher or equal to the maximum seed drops.");
                                    return;
                                }
                                plugin.getConfig().set("crops." + args[1].toLowerCase() + ".min-seed-drops", args3);
                                et.STMTCS(sender, "&6RightClickFarming > &eMinimum seed drops for " + args[1].toLowerCase() + " have been set to &6" + args3 + "&e.");
                                plugin.saveConfig();
                                plugin.reloadConfig();
                            } else if (args[2].equalsIgnoreCase("max-seed-drops")) {
                                if (args3 <= plugin.getConfig().getInt("crops." + args[1].toLowerCase() + ".min-seed-drops")) {
                                    et.STMTCS(sender, "&6RightClickFarming > &eThe maximum seed drops must not be lower or equal to the minimum seed drops.");
                                    return;
                                }
                                plugin.getConfig().set("crops." + args[1].toLowerCase() + ".max-seed-drops", args3);
                                et.STMTCS(sender, "&6RightClickFarming > &eMaximum seed drops for " + args[1].toLowerCase() + " have been set to &6" + args3 + "&e.");
                                plugin.saveConfig();
                                plugin.reloadConfig();
                            } else {
                                helpCommand.helpCommand(sender, args[1].toLowerCase());
                            }
                        } else {
                            helpCommand.helpCommand(sender, args[1].toLowerCase());
                        }
                    } else if (args[1].equalsIgnoreCase("carrot") || args[1].equalsIgnoreCase("netherwart") || args[1].equalsIgnoreCase("melon") || args[1].equalsIgnoreCase("cocoabeans")) {

                    } else if (args[1].equalsIgnoreCase("potato")) {

                    } else if (args[1].equalsIgnoreCase("sugarcane") || args[1].equalsIgnoreCase("cactus") || args[1].equalsIgnoreCase("bamboo") || args[1].equalsIgnoreCase("kelp") || args[1].equalsIgnoreCase("pumpkin")) {

                    } else if (args[1].equalsIgnoreCase("sweetberries")) {

                    } else {
                        helpCommand.helpCommand(sender, "set");
                    }
                    //rcf set wheat enabled false
                }
            }
        }
    }
}
