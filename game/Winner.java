package src.TierCraft.Minigame.SkyGiant.plugin.game;

import org.bukkit.plugin.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Messages;
import src.TierCraft.Minigame.SkyGiant.plugin.runnables.Restarting;
import src.TierCraft.Minigame.SkyGiant.plugin.utils.Titles;

import org.bukkit.*;
import org.bukkit.entity.*;
import java.util.*;

public class Winner
{
    public static void getWinner() {
        if (Main.STATUS == GameState.RESTARTING) {
            return;
        }
        if (!Main.aliveTeams.contains("BLUE") && !Main.aliveTeams.contains("MAGENTA") && !Main.aliveTeams.contains("GREEN") && !Main.WINNER) {
            Main.plugin.getServer().getScheduler().cancelAllTasks();
            new Restarting().runTaskTimer((Plugin)Main.plugin, 20L, 20L);
            final Iterator<? extends Player> iterator = Bukkit.getOnlinePlayers().iterator();
            while (iterator.hasNext()) {
                Titles.sendTitle(iterator.next(), 0, 40, 0, Messages.getMsg().getString("gold-team").replace('&', '§'), Messages.getMsg().getString("champions").replace('&', '§'));
            }
            Main.WINNER = true;
            return;
        }
        if (!Main.aliveTeams.contains("GOLD") && !Main.aliveTeams.contains("MAGENTA") && !Main.aliveTeams.contains("BLUE") && !Main.WINNER) {
            Main.plugin.getServer().getScheduler().cancelAllTasks();
            final Iterator<? extends Player> iterator2 = Bukkit.getOnlinePlayers().iterator();
            while (iterator2.hasNext()) {
                Titles.sendTitle(iterator2.next(), 0, 40, 0, Messages.getMsg().getString("green-team").replace('&', '§'), Messages.getMsg().getString("champions").replace('&', '§'));
            }
            new Restarting().runTaskTimer((Plugin)Main.plugin, 20L, 20L);
            Main.WINNER = true;
            return;
        }
        if (!Main.aliveTeams.contains("GOLD") && !Main.aliveTeams.contains("BLUE") && !Main.aliveTeams.contains("GREEN") && !Main.WINNER) {
            Main.plugin.getServer().getScheduler().cancelAllTasks();
            new Restarting().runTaskTimer((Plugin)Main.plugin, 20L, 20L);
            final Iterator<? extends Player> iterator3 = Bukkit.getOnlinePlayers().iterator();
            while (iterator3.hasNext()) {
                Titles.sendTitle(iterator3.next(), 0, 40, 0, Messages.getMsg().getString("magenta-team").replace('&', '§'), Messages.getMsg().getString("champions").replace('&', '§'));
            }
            Main.WINNER = true;
            return;
        }
        if (!Main.aliveTeams.contains("GOLD") && !Main.aliveTeams.contains("MAGENTA") && !Main.aliveTeams.contains("GREEN") && !Main.WINNER) {
            Main.plugin.getServer().getScheduler().cancelAllTasks();
            new Restarting().runTaskTimer((Plugin)Main.plugin, 20L, 20L);
            final Iterator<? extends Player> iterator4 = Bukkit.getOnlinePlayers().iterator();
            while (iterator4.hasNext()) {
                Titles.sendTitle(iterator4.next(), 0, 40, 0, Messages.getMsg().getString("blue-team").replace('&', '§'), Messages.getMsg().getString("champions").replace('&', '§'));
            }
            Main.WINNER = true;
            return;
        }
        if (Main.players.isEmpty()) {
            Main.plugin.getServer().getScheduler().cancelAllTasks();
            new Restarting().runTaskTimer((Plugin)Main.plugin, 20L, 20L);
            return;
        }
        if (Main.GameCountdown == 0) {
            if (Main.nmsH.getGiantHealthInt("Gold") > Main.nmsH.getGiantHealthInt("Magenta") && Main.nmsH.getGiantHealthInt("Gold") > Main.nmsH.getGiantHealthInt("Green") && Main.nmsH.getGiantHealthInt("Gold") > Main.nmsH.getGiantHealthInt("Blue")) {
                Main.plugin.getServer().getScheduler().cancelAllTasks();
                new Restarting().runTaskTimer((Plugin)Main.plugin, 20L, 20L);
                final Iterator<? extends Player> iterator5 = Bukkit.getOnlinePlayers().iterator();
                while (iterator5.hasNext()) {
                    Titles.sendTitle(iterator5.next(), 0, 40, 0, Messages.getMsg().getString("gold-team").replace('&', '§'), Messages.getMsg().getString("champion").replace('&', '§'));
                }
                Main.WINNER = true;
            }
            else if (Integer.valueOf(Main.nmsH.getGiantHealthInt("Magenta")) > Integer.valueOf(Main.nmsH.getGiantHealthInt("Gold")) && Integer.valueOf(Main.nmsH.getGiantHealthInt("Magenta")) > Integer.valueOf(Main.nmsH.getGiantHealthInt("Green")) && Integer.valueOf(Main.nmsH.getGiantHealthInt("Magenta")) > Integer.valueOf(Main.nmsH.getGiantHealthInt("Blue"))) {
                Main.plugin.getServer().getScheduler().cancelAllTasks();
                new Restarting().runTaskTimer((Plugin)Main.plugin, 20L, 20L);
                final Iterator<? extends Player> iterator6 = Bukkit.getOnlinePlayers().iterator();
                while (iterator6.hasNext()) {
                    Titles.sendTitle(iterator6.next(), 0, 40, 0, Messages.getMsg().getString("magenta-team").replace('&', '§'), Messages.getMsg().getString("champion").replace('&', '§'));
                }
                Main.WINNER = true;
            }
            else if (Main.nmsH.getGiantHealthInt("Green") > Main.nmsH.getGiantHealthInt("Gold") && Main.nmsH.getGiantHealthInt("Green") > Main.nmsH.getGiantHealthInt("Magenta") && Main.nmsH.getGiantHealthInt("Green") > Main.nmsH.getGiantHealthInt("Blue")) {
                Main.plugin.getServer().getScheduler().cancelAllTasks();
                new Restarting().runTaskTimer((Plugin)Main.plugin, 20L, 20L);
                final Iterator<? extends Player> iterator7 = Bukkit.getOnlinePlayers().iterator();
                while (iterator7.hasNext()) {
                    Titles.sendTitle(iterator7.next(), 0, 40, 0, Messages.getMsg().getString("green-team").replace('&', '§'), Messages.getMsg().getString("champion").replace('&', '§'));
                }
                Main.WINNER = true;
            }
            else if (Main.nmsH.getGiantHealthInt("Blue") > Main.nmsH.getGiantHealthInt("Gold") && Main.nmsH.getGiantHealthInt("Blue") > Main.nmsH.getGiantHealthInt("Magenta") && Main.nmsH.getGiantHealthInt("Blue") > Main.nmsH.getGiantHealthInt("Green")) {
                Main.plugin.getServer().getScheduler().cancelAllTasks();
                new Restarting().runTaskTimer((Plugin)Main.plugin, 20L, 20L);
                final Iterator<? extends Player> iterator8 = Bukkit.getOnlinePlayers().iterator();
                while (iterator8.hasNext()) {
                    Titles.sendTitle(iterator8.next(), 0, 40, 0, Messages.getMsg().getString("blue-team").replace('&', '§'), Messages.getMsg().getString("champion").replace('&', '§'));
                }
                Main.WINNER = true;
            }
            else {
                Main.plugin.getServer().getScheduler().cancelAllTasks();
                new Restarting().runTaskTimer((Plugin)Main.plugin, 20L, 20L);
            }
        }
    }
}
