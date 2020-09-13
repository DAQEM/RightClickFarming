package me.daqem.rightclickfarming.commands;

import me.daqem.rightclickfarming.RightClickFarming;
import me.daqem.rightclickfarming.utils.EasyTranslator;
import org.bukkit.command.CommandSender;

import java.util.*;

public class HelpCommand {

    private final RightClickFarming plugin;
    private final EasyTranslator et;

    public HelpCommand(RightClickFarming pl) {
        this.plugin = pl;
        this.et = new EasyTranslator();
    }

    private final String header = "&6&m==============&e&m[&r&e RightClickFarming Help &m]&6&m==============";

    public void helpCommand(CommandSender sender, String type) {
        Set<String> cropsSet = Objects.requireNonNull(plugin.getConfig().getConfigurationSection("crops")).getKeys(false);
        List<String> cropsArray = new ArrayList<>(cropsSet);

        String footer = "&6&m=================================================";
        if (type == null) {
            et.STMTCS(sender, header);
            et.STMTCS(sender, "");
            et.STMTCS(sender, "&6Available commands:");
            et.STMTCS(sender, "&e/rcf help [crop type] &7(Shows help page)");
            et.STMTCS(sender, "&e/rcf set &7(Sets Plugin Configuration)");
            et.STMTCS(sender, "&e/rcf reload &7(Reloads config)");
            et.STMTCS(sender, "");
            et.STMTCS(sender, "&7(The config will automatically be reloaded after using the set command)");
            et.STMTCS(sender, "");
            et.STMTCS(sender, footer);
        }
        else if (type.equals("set")) {
            et.STMTCS(sender, header);
            et.STMTCS(sender, "");
            et.STMTCS(sender, "&6Usage:");
            et.STMTCS(sender, "&e/rcf set [crop type]");
            et.STMTCS(sender, "");
            et.STMTCS(sender, "&6Available Crop Types:");
            et.STMTCS(sender, ("&e" + cropsArray.toString().replace("[", "").replace("]", "").trim()));
            et.STMTCS(sender, "");
            et.STMTCS(sender, footer);
        }
        else if (type.equals("wheat") || type.equals("beetroot")) {
            et.STMTCS(sender, header);
            enableMessage(sender,type);
            multiplierMessage(sender, type);
            et.STMTCS(sender, "&e/rcf set " + type + " seed-drops [true/false]");
            et.STMTCS(sender, "&7(Enable/disable seeds drops)");
            et.STMTCS(sender, "");
            et.STMTCS(sender, "&e/rcf set " + type + " min-seed-drops [number]");
            et.STMTCS(sender, "&7(Change the minimum amount of seeds that drop every time you harvest " + type + ")");
            et.STMTCS(sender, "");
            et.STMTCS(sender, "&e/rcf set " + type + " max-seed-drops [number]");
            et.STMTCS(sender, "&7(Change the maximum amount of seeds that drop every time you harvest " + type + ")");
            et.STMTCS(sender, "");
            et.STMTCS(sender, footer);
        }
        else if (type.equals("carrot") || type.equals("netherwart") || type.equals("melon")) {
            enableMessage(sender,type);
            et.STMTCS(sender, "&e/rcf set " + type + " min-drops [number]");
            et.STMTCS(sender, "&7(Change the minimum amount of " + type + "s that drops every time you harvest one)");
            et.STMTCS(sender, "");
            et.STMTCS(sender, "&e/rcf set " + type + " max-drops [number]");
            et.STMTCS(sender, "&7(Change the maximum amount of " + type + "s that drops every time you harvest one)");
            et.STMTCS(sender, "");
            et.STMTCS(sender, footer);
        }
        else if (type.equals("potato")) {
            enableMessage(sender,type);
            et.STMTCS(sender, "&e/rcf set " + type + " min-drops [number]");
            et.STMTCS(sender, "&7(Change the minimum amount of " + type + "es that drops every time you harvest one)");
            et.STMTCS(sender, "");
            et.STMTCS(sender, "&e/rcf set " + type + " max-drops [number]");
            et.STMTCS(sender, "&7(Change the maximum amount of " + type + "es that drops every time you harvest one)");
            et.STMTCS(sender, "");
            et.STMTCS(sender, "&e/rcf set " + type + " poisonous-potato");
            et.STMTCS(sender, "&7(Shows commands about the poisonous-potato drops)");
            et.STMTCS(sender, "");
            et.STMTCS(sender, footer);
        }
        else if (type.equals("sugarcane") || type.equals("cactus") || type.equals("bamboo") || type.equals("kelp") || type.equals("pumpkin")) {
            enableMessage(sender,type);
            multiplierMessage(sender, type);
            et.STMTCS(sender, footer);
        }
        else if (type.equals("cocoabeans")) {
            enableMessage(sender,type);
            et.STMTCS(sender, "&e/rcf set " + type + " min-drops [number]");
            et.STMTCS(sender, "&7(Change the minimum amount of " + type + " that drops every time you harvest one)");
            et.STMTCS(sender, "");
            et.STMTCS(sender, "&e/rcf set " + type + " max-drops [number]");
            et.STMTCS(sender, "&7(Change the maximum amount of " + type + " that drops every time you harvest one)");
            et.STMTCS(sender, "");
            et.STMTCS(sender, footer);
        }
        else if (type.equals("sweetberries")) {
            enableMessage(sender, type);
            et.STMTCS(sender, "&e/rcf set " + type + " fully-grown min-drops [number]");
            et.STMTCS(sender, "&7(Change the minimum amount of " + type + " that drops when you harvest a fully grown sweet berry bush)");
            et.STMTCS(sender, "");
            et.STMTCS(sender, "&e/rcf set " + type + " fully-grown max-drops [number]");
            et.STMTCS(sender, "&7(Change the maximum amount of " + type + " that drops when you harvest a fully grown sweet berry bush)");
            et.STMTCS(sender, "");
            et.STMTCS(sender, "&e/rcf set " + type + " half-grown min-drops [number]");
            et.STMTCS(sender, "&7(Change the minimum amount of " + type + " that drops when you harvest a half grown sweet berry bush)");
            et.STMTCS(sender, "");
            et.STMTCS(sender, "&e/rcf set " + type + " half-grown max-drops [number]");
            et.STMTCS(sender, "&7(Change the maximum amount of " + type + " that drops when you harvest a half grown sweet berry bush)");
            et.STMTCS(sender, "");
            et.STMTCS(sender, footer);
        }
        else if (type.equals("poisonous-potato")) {
            et.STMTCS(sender, header);
            et.STMTCS(sender, "");
            et.STMTCS(sender, "&6Available commands:");
            et.STMTCS(sender, "&e/rcf set potato " + type + " enabled [true/false]");
            et.STMTCS(sender, "&7(Enable/disable poisonous potato drops)");
            et.STMTCS(sender, "");
            et.STMTCS(sender, "&e/rcf set potato " + type + " drop-percentage [number (max 100)]");
            et.STMTCS(sender, "&7(Change poisonous potato drop chance)");
            et.STMTCS(sender, "");
            et.STMTCS(sender, "&e/rcf set potato " + type + " min-drops [number]");
            et.STMTCS(sender, "&7(Change the minimum amount of poisonous potatoes an drop when you harvest a potato)");
            et.STMTCS(sender, "");
            et.STMTCS(sender, "&e/rcf set potato " + type + " max-drops [number]");
            et.STMTCS(sender, "&7(Change the maximum amount of poisonous potatoes can drop when you harvest a potato)");
            et.STMTCS(sender, "");
            et.STMTCS(sender, footer);
        }
        else if (type.equals("settings")) {
            et.STMTCS(sender, header);
            et.STMTCS(sender, "");
            et.STMTCS(sender, "&6Available commands:");
            et.STMTCS(sender, "&e/rcf settings hoe-required [true/false]");
            et.STMTCS(sender, "&7(Enable/disable the option to require a hoe to right-click farm a crop.)");
            et.STMTCS(sender, "");
            et.STMTCS(sender, footer);
        }
    }

    public void enableMessage(CommandSender sender, String type) {
        et.STMTCS(sender, header);
        et.STMTCS(sender, "");
        et.STMTCS(sender, "&6Available commands:");
        et.STMTCS(sender, "&e/rcf set " + type + " enabled [true/false]");
        et.STMTCS(sender, "&7(Enable/disable the ability to harvest " + type + " by right-clicking)");
        et.STMTCS(sender, "");
    }

    public void multiplierMessage(CommandSender sender, String type) {
        et.STMTCS(sender, "&e/rcf set " + type + " multiplier [number]");
        et.STMTCS(sender, "&7(Change the amount of " + type + " that drops every time you harvest beetroot)");
        et.STMTCS(sender, "");
    }
}
