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
                if (args[1].equalsIgnoreCase(cropType)) {
                    helpCommand.helpCommand(sender, cropType);
                } else {
                    helpCommand.helpCommand(sender, "set");
                }
            }
        } else if (args.length == 4) {
            for (String cropType : cropsArray) {
                if (args[1].equalsIgnoreCase(cropType)) {
                    if (args[1].equalsIgnoreCase("wheat") || args[1].equalsIgnoreCase("beetroot")) {
                        if (args[3].equalsIgnoreCase("true") || args[3].equalsIgnoreCase("false")) {
                            if (args[2].equalsIgnoreCase("enabled")) {
                                switchEnable(sender, args);
                            } else if (args[2].equalsIgnoreCase("seed-drops")) {
                                if (args[3].equalsIgnoreCase("true")) {
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
                        } else if (integerCheck.isInteger(args[3])) {
                            int args3 = Integer.parseInt(args[3]);
                            if (args[2].equalsIgnoreCase("multiplier")) {
                                plugin.getConfig().set("crops." + args[1].toLowerCase() + ".multiplier", args3);
                                et.STMTCS(sender, "&6RightClickFarming > &eMultiplier for " + args[1].toLowerCase() + " has been set to &6" + args3 + "&e.");
                                plugin.saveConfig();
                                plugin.reloadConfig();
                            } else if (args[2].equalsIgnoreCase("min-seed-drops")) {
                                if (args3 > plugin.getConfig().getInt("crops." + args[1].toLowerCase() + ".max-seed-drops")) {
                                    et.STMTCS(sender, "&6RightClickFarming > &eThe minimum seed drops must be less than or equal to the maximum seed drops.");
                                    return;
                                }
                                plugin.getConfig().set("crops." + args[1].toLowerCase() + ".min-seed-drops", args3);
                                et.STMTCS(sender, "&6RightClickFarming > &eMinimum seed drops for " + args[1].toLowerCase() + " has been set to &6" + args3 + "&e.");
                                plugin.saveConfig();
                                plugin.reloadConfig();
                            } else if (args[2].equalsIgnoreCase("max-seed-drops")) {
                                if (args3 < plugin.getConfig().getInt("crops." + args[1].toLowerCase() + ".min-seed-drops")) {
                                    et.STMTCS(sender, "&6RightClickFarming > &eThe maximum seed drops must be greater than or equal to the minimum seed drops.");
                                    return;
                                }
                                plugin.getConfig().set("crops." + args[1].toLowerCase() + ".max-seed-drops", args3);
                                et.STMTCS(sender, "&6RightClickFarming > &eMaximum seed drops for " + args[1].toLowerCase() + " has been set to &6" + args3 + "&e.");
                                plugin.saveConfig();
                                plugin.reloadConfig();
                            } else {
                                helpCommand.helpCommand(sender, args[1].toLowerCase());
                            }
                        } else {
                            helpCommand.helpCommand(sender, args[1].toLowerCase());
                        }
                    } else if (args[1].equalsIgnoreCase("carrot") || args[1].equalsIgnoreCase("netherwart") || args[1].equalsIgnoreCase("melon") || args[1].equalsIgnoreCase("cocoabeans") || args[1].equalsIgnoreCase("potato")) {
                        if ((args[3].equalsIgnoreCase("true") || args[3].equalsIgnoreCase("false")) && args[2].equalsIgnoreCase("enabled")) {
                            switchEnable(sender, args);
                        } else if (integerCheck.isInteger(args[3])) {
                            int args3 = Integer.parseInt(args[3]);
                            if (args[2].equalsIgnoreCase("min-drops")) {
                                if (args3 > plugin.getConfig().getInt("crops." + args[1].toLowerCase() + ".max-drops")) {
                                    et.STMTCS(sender, "&6RightClickFarming > &eThe minimum drops must be less than or equal to the maximum drops.");
                                    return;
                                }
                                plugin.getConfig().set("crops." + args[1].toLowerCase() + ".min-drops", args3);
                                et.STMTCS(sender, "&6RightClickFarming > &eMinimum drops for " + args[1].toLowerCase() + " has been set to &6" + args3 + "&e.");
                                plugin.saveConfig();
                                plugin.reloadConfig();
                            } else if (args[2].equalsIgnoreCase("max-drops")) {
                                if (args3 < plugin.getConfig().getInt("crops." + args[1].toLowerCase() + ".min-drops")) {
                                    et.STMTCS(sender, "&6RightClickFarming > &eThe maximum drops must be greater than or equal to the minimum drops.");
                                    return;
                                }
                                plugin.getConfig().set("crops." + args[1].toLowerCase() + ".max-drops", args3);
                                et.STMTCS(sender, "&6RightClickFarming > &eMaximum drops for " + args[1].toLowerCase() + " has been set to &6" + args3 + "&e.");
                                plugin.saveConfig();
                                plugin.reloadConfig();
                            } else {
                                helpCommand.helpCommand(sender, args[1].toLowerCase());
                            }
                        } else {
                            helpCommand.helpCommand(sender, args[1].toLowerCase());
                        }
                    } else if (args[1].equalsIgnoreCase("sugarcane") || args[1].equalsIgnoreCase("cactus") || args[1].equalsIgnoreCase("bamboo") || args[1].equalsIgnoreCase("kelp") || args[1].equalsIgnoreCase("pumpkin")) {
                        if ((args[3].equalsIgnoreCase("true") || args[3].equalsIgnoreCase("false")) && args[2].equalsIgnoreCase("enabled")) {
                            switchEnable(sender, args);
                        } else if (integerCheck.isInteger(args[3])) {
                            int args3 = Integer.parseInt(args[3]);
                            if (args[2].equalsIgnoreCase("multiplier")) {
                                plugin.getConfig().set("crops." + args[1].toLowerCase() + ".multiplier", args3);
                                et.STMTCS(sender, "&6RightClickFarming > &eMultiplier for " + args[1].toLowerCase() + " has been set to &6" + args3 + "&e.");
                                plugin.saveConfig();
                                plugin.reloadConfig();
                            }
                        }
                    } else if (args[1].equalsIgnoreCase("sweetberries")) {
                        if ((args[3].equalsIgnoreCase("true") || args[3].equalsIgnoreCase("false")) && args[2].equalsIgnoreCase("enabled")) {
                            switchEnable(sender, args);
                        } else {
                            helpCommand.helpCommand(sender, args[1].toLowerCase());
                        }
                    } else {
                        helpCommand.helpCommand(sender, "set");
                    }

                }
            }
        } else if (args.length == 5) {
            if (args[1].equalsIgnoreCase("potato") && args[2].equalsIgnoreCase("poisonous-potato")) {
                if ((args[4].equalsIgnoreCase("true") || args[4].equalsIgnoreCase("false")) && args[3].equalsIgnoreCase("enabled")) {
                    if (args[4].equalsIgnoreCase("true")) {
                        plugin.getConfig().set("crops.potato.poisonous-potato.enabled", true);
                        et.STMTCS(sender, "&6RightClickFarming > &ePoisonous potato has been &aenabled&e.");
                    } else {
                        plugin.getConfig().set("crops.potato.poisonous-potato.enabled", false);
                        et.STMTCS(sender, "&6RightClickFarming > &ePoisonous potato has been &cdisabled&e.");
                    }
                    plugin.saveConfig();
                    plugin.reloadConfig();
                } else if (integerCheck.isInteger(args[4])) {
                    int args4 = Integer.parseInt(args[4]);
                    if (args[3].equalsIgnoreCase("drop-percentage")) {
                        if (args4 <= 100) {
                            plugin.getConfig().set("crops.potato.poisonous-potato.drop-percentage", args4);
                            et.STMTCS(sender, "&6RightClickFarming > &ePoisonous potato drop percentage has been set to &6" + args4 + "%&e.");
                            plugin.saveConfig();
                            plugin.reloadConfig();
                        } else {
                            et.STMTCS(sender, "&6RightClickFarming > &ePoisonous potato drop percentage must be 100 or lower.");
                        }
                    } else if (args[3].equalsIgnoreCase("min-drops")) {
                        if (args4 > plugin.getConfig().getInt("crops.potato.poisonous-potato.max-drops")) {
                            et.STMTCS(sender, "&6RightClickFarming > &eThe minimum drops must be less than or equal to the maximum drops.");
                            return;
                        }
                        plugin.getConfig().set("crops.potato.poisonous-potato.min-drops", args4);
                        et.STMTCS(sender, "&6RightClickFarming > &eMinimum drops for poisonous potato has been set to &6" + args4 + "&e.");
                        plugin.saveConfig();
                        plugin.reloadConfig();
                    } else if (args[3].equalsIgnoreCase("max-drops")) {
                        if (args4 < plugin.getConfig().getInt("crops.potato.poisonous-potato.min-drops")) {
                            et.STMTCS(sender, "&6RightClickFarming > &eThe maximum drops must be greater than or equal to the minimum drops.");
                            return;
                        }
                        plugin.getConfig().set("crops.potato.poisonous-potato.max-drops", args4);
                        et.STMTCS(sender, "&6RightClickFarming > &eMaximum drops for poisonous potato has been set to &6" + args4 + "&e.");
                        plugin.saveConfig();
                        plugin.reloadConfig();
                    } else {
                        helpCommand.helpCommand(sender, args[2].toLowerCase());
                    }
                } else {
                    helpCommand.helpCommand(sender, args[2].toLowerCase());
                }
            } else if (args[1].equalsIgnoreCase("sweetberries")) {

            } else {
                helpCommand.helpCommand(sender, "set");
            }
        }

    }

    public void switchEnable(CommandSender sender, String[] args) {
        if (args[3].equalsIgnoreCase("true")) {
            plugin.getConfig().set("crops." + args[1].toLowerCase() + ".enabled", true);
            et.STMTCS(sender, "&6RightClickFarming > &e" + capitalizeLetter.capitalize(args[1].toLowerCase()) + " has been &aenabled&e.");
        } else {
            plugin.getConfig().set("crops." + args[1].toLowerCase() + ".enabled", false);
            et.STMTCS(sender, "&6RightClickFarming > &e" + capitalizeLetter.capitalize(args[1].toLowerCase()) + " has been &cdisabled&e.");
        }
        plugin.saveConfig();
        plugin.reloadConfig();
    }
}
