package src.TierCraft.Minigame.SkyGiant.plugin.commands;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Messages;
import src.TierCraft.Minigame.SkyGiant.plugin.game.GameState;
import src.TierCraft.Minigame.SkyGiant.plugin.utils.Misc;

public class GiveGold implements CommandExecutor
{
    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] array) {
        if (!(commandSender instanceof Player)) {
            return true;
        }
        if (Main.STATUS != GameState.PLAYING) {
            return true;
        }
        if (!Main.players.contains(commandSender)) {
            return true;
        }
        if (command.getName().equalsIgnoreCase("givegold")) {
            if (array.length == 2) {
                if (Bukkit.getPlayer(array[0]) == null) {
                    commandSender.sendMessage(Main.PREFIX + Messages.getMsg().getString("gold-usage").replace('&', '§'));
                    return true;
                }
                if (!Misc.isInt(array[1])) {
                    commandSender.sendMessage(Main.PREFIX + Messages.getMsg().getString("gold-usage").replace('&', '§'));
                    return true;
                }
                final Player player = (Player)commandSender;
                final Player player2 = Bukkit.getPlayer(array[0]);
                if (player2.getName() == player.getName()) {
                    commandSender.sendMessage(Main.PREFIX + Messages.getMsg().getString("gold-usage").replace('&', '§'));
                    return true;
                }
                if (!Main.players.contains(player2)) {
                    return true;
                }
                if (Main.money.get(player) >= Integer.valueOf(array[1])) {
                    Main.money.replace(player2, Main.money.get(player) + Integer.valueOf(array[1]));
                    Main.money.remove(player, Main.money.get(player) - Integer.valueOf(array[1]));
                    player.sendMessage(Main.PREFIX + Messages.getMsg().getString("gold-sent").replace("{amount}", array[1]).replace("{target}", array[0]).replace('&', '§'));
                    player2.sendMessage(Main.PREFIX + Messages.getMsg().getString("gold-received").replace("{amount}", array[1]).replace("{player}", player.getName()));
                }
                else {
                    player.sendMessage(Main.PREFIX + Messages.getMsg().getString("gold-to-send").replace('&', '§'));
                }
            }
            else {
                commandSender.sendMessage(Main.PREFIX + Messages.getMsg().getString("gold-usage").replace('&', '§'));
            }
        }
        return false;
    }
}
