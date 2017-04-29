package src.TierCraft.Minigame.SkyGiant.plugin.listeners;

import org.bukkit.event.player.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.plugin.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.commands.Team;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Messages;
import src.TierCraft.Minigame.SkyGiant.plugin.game.GameState;
import src.TierCraft.Minigame.SkyGiant.plugin.game.Winner;
import src.TierCraft.Minigame.SkyGiant.plugin.utils.Database;

import java.util.*;
import org.bukkit.event.*;

public class PlayerQuitListener implements Listener
{
    @EventHandler
    public void quit(final PlayerQuitEvent playerQuitEvent) {
        playerQuitEvent.setQuitMessage((String)null);
        final Player player = playerQuitEvent.getPlayer();
        if (Main.players.contains(player)) {
            Main.players.remove(player);
        }
        if (Main.STATUS == GameState.RESTARTING || Main.STATUS == GameState.PLAYING) {
            if (Main.spectators.contains(player)) {
                Main.spectators.remove(player);
            }
            if (Main.magentaPlayers.contains(player)) {
                Main.magentaPlayers.remove(player);
                if (Main.magentaPlayers.isEmpty()) {
                    Main.aliveTeams.remove("MAGENTA");
                    Main.nmsH.killGiant("Magenta");
                }
                else {
                    final Iterator<Player> iterator = Main.magentaPlayers.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().sendMessage(Main.PREFIX + Messages.getMsg().getString("teammate-quit").replace("{name}", player.getName()).replace('&', '?'));
                    }
                }
            }
            if (Main.goldPlayers.contains(player)) {
                Main.goldPlayers.remove(player);
                if (Main.goldPlayers.isEmpty()) {
                    Main.aliveTeams.remove("GOLD");
                    Main.nmsH.killGiant("Gold");
                }
                else {
                    final Iterator<Player> iterator2 = Main.goldPlayers.iterator();
                    while (iterator2.hasNext()) {
                        iterator2.next().sendMessage(Main.PREFIX + Messages.getMsg().getString("teammate-quit").replace("{name}", player.getName()).replace('&', '?'));
                    }
                }
            }
            if (Main.greenPlayers.contains(player)) {
                Main.greenPlayers.remove(player);
                if (Main.greenPlayers.isEmpty()) {
                    Main.aliveTeams.remove("GREEN");
                    Main.nmsH.killGiant("Green");
                }
                else {
                    final Iterator<Player> iterator3 = Main.greenPlayers.iterator();
                    while (iterator3.hasNext()) {
                        iterator3.next().sendMessage(Main.PREFIX + Messages.getMsg().getString("teammate-quit").replace("{name}", player.getName()).replace('&', '?'));
                    }
                }
            }
            if (Main.bluePlayers.contains(player)) {
                Main.bluePlayers.remove(player);
                if (Main.bluePlayers.isEmpty()) {
                    Main.aliveTeams.remove("BLUE");
                    Main.nmsH.killGiant("Blue");
                }
                else {
                    final Iterator<Player> iterator4 = Main.bluePlayers.iterator();
                    while (iterator4.hasNext()) {
                        iterator4.next().sendMessage(Main.PREFIX + Messages.getMsg().getString("teammate-quit").replace("{name}", player.getName()).replace('&', '?'));
                    }
                }
            }
        }
        if (Main.STATUS == GameState.STARTING) {
            Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                @Override
                public void run() {
                    if (Main.miniSG) {
                        if (Bukkit.getOnlinePlayers().size() < Main.MaxInTeam * 2) {
                            Main.plugin.getServer().getScheduler().cancelTasks((Plugin)Main.plugin);
                            Main.LobbyCountdown = 300;
                            Main.STATUS = GameState.LOBBY;
                        }
                    }
                    else if (Bukkit.getOnlinePlayers().size() < Main.MaxInTeam) {
                        Main.plugin.getServer().getScheduler().cancelTasks((Plugin)Main.plugin);
                        Main.LobbyCountdown = 300;
                        Main.STATUS = GameState.LOBBY;
                    }
                }
            }, 5L);
        }
        if (Main.STATUS == GameState.PLAYING) {
            Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                @Override
                public void run() {
                    Winner.getWinner();
                }
            }, 10L);
            Main.gamesplayed.add(player);
            Database.saveStats(player);
        }
        if (Main.money.containsKey(player)) {
            Main.money.remove(player);
        }
        if (Main.kills.containsKey(player)) {
            Main.kills.remove(player);
        }
        if (Main.beastsSlain.containsKey(player)) {
            Main.beastsSlain.remove(player);
        }
        if (Main.deaths.containsKey(player)) {
            Main.deaths.remove(player);
        }
        if (Main.goldearnt.containsKey(player)) {
            Main.goldearnt.remove(player);
        }
        if (Main.gamesplayed.contains(player)) {
            Main.gamesplayed.remove(player);
        }
        if (Main.victories.contains(player)) {
            Main.victories.remove(player);
        }
        if (Main.lookingAtShop.contains(player)) {
            Main.lookingAtShop.remove(player);
        }
        if (Main.recall.contains(player)) {
            Main.recall.remove(player);
        }
        if (Main.STATUS == GameState.STARTING || Main.STATUS == GameState.LOBBY) {
            Team.hasLeft(player);
            if (Main.goldPlayers.contains(player)) {
                Main.goldPlayers.remove(player);
            }
            if (Main.magentaPlayers.contains(player)) {
                Main.magentaPlayers.remove(player);
            }
            if (Main.greenPlayers.contains(player)) {
                Main.greenPlayers.remove(player);
            }
            if (Main.bluePlayers.contains(player)) {
                Main.bluePlayers.remove(player);
            }
        }
    }
}
