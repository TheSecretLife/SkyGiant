package src.TierCraft.Minigame.SkyGiant.plugin.commands;

import java.util.*;
import org.bukkit.entity.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Messages;
import src.TierCraft.Minigame.SkyGiant.plugin.game.GameState;

import org.bukkit.command.*;
import org.bukkit.*;

public class Team implements CommandExecutor
{
    public static HashMap<Player, Player> isInvited;
    public static HashMap<Player, Player> hasInvited;
    
    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] array) {
        if (command.getName().equalsIgnoreCase("team")) {
            if (Main.STATUS != GameState.STARTING && Main.STATUS != GameState.LOBBY) {
                return true;
            }
            if (!Main.miniSG) {
                return true;
            }
            final Player player = (Player)commandSender;
            if (array.length == 1) {
                if (array[0].equalsIgnoreCase("leave")) {
                    hasLeft(player);
                }
                if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(array[0]))) {
                    if (Main.hasTeam.contains(player)) {
                        player.sendMessage(Main.PREFIX + Messages.getMsg().getString("already-in-team").replace('&', '§'));
                        return true;
                    }
                    if (Main.hasTeam.contains(Bukkit.getPlayer(array[0]))) {
                        player.sendMessage(Main.PREFIX + Messages.getMsg().getString("has-team").replace('&', '§'));
                        return true;
                    }
                    final Player player2 = Bukkit.getPlayer(array[0]);
                    final Player player3 = player;
                    if (!Team.isInvited.containsKey(player3)) {
                        player3.sendMessage(Main.PREFIX + Messages.getMsg().getString("no-invite").replace('&', '§'));
                        return true;
                    }
                    if (!Team.hasInvited.containsKey(player2)) {
                        player2.sendMessage(Main.PREFIX + Messages.getMsg().getString("no-invite").replace('&', '§'));
                        return true;
                    }
                    if (player2.getUniqueId().toString().equalsIgnoreCase(Team.isInvited.get(player).getUniqueId().toString())) {
                        Main.hasTeam.add(player2);
                        Main.hasTeam.add(player3);
                        player2.sendMessage(Main.PREFIX + Messages.getMsg().getString("teamed-up").replace("{name}", player.getName()).replace('&', '§'));
                        player3.sendMessage(Main.PREFIX + Messages.getMsg().getString("teamed-up").replace("{name}", player2.getName()).replace('&', '§'));
                        if (Main.goldPlayers.isEmpty()) {
                            Main.goldPlayers.add(player3);
                            Main.goldPlayers.add(player2);
                        }
                        else if (Main.magentaPlayers.isEmpty()) {
                            Main.magentaPlayers.add(player3);
                            Main.magentaPlayers.add(player2);
                        }
                        else if (Main.greenPlayers.isEmpty()) {
                            Main.greenPlayers.add(player3);
                            Main.greenPlayers.add(player2);
                        }
                        else if (Main.bluePlayers.isEmpty()) {
                            Main.bluePlayers.add(player3);
                            Main.bluePlayers.add(player2);
                        }
                    }
                    else {
                        player.sendMessage(Main.PREFIX + Messages.getMsg().getString("no-invite").replace('&', '§'));
                    }
                }
            }
        }
        return false;
    }
    
    public static void hasLeft(final Player player) {
        if (!Main.miniSG) {
            return;
        }
        if (Main.hasTeam.contains(player)) {
            Player player2 = null;
            if (Team.isInvited.containsKey(player)) {
                player2 = Team.isInvited.get(player);
            }
            if (Team.hasInvited.containsKey(player)) {
                player2 = Team.hasInvited.get(player);
            }
            player2.sendMessage(Main.PREFIX + Messages.getMsg().getString("partner-left").replace('&', '§'));
            Main.hasTeam.remove(player);
            Main.hasTeam.remove(player2);
            Main.goldPlayers.remove(player);
            Main.goldPlayers.remove(player2);
            Main.magentaPlayers.remove(player);
            Main.magentaPlayers.remove(player2);
            Main.greenPlayers.remove(player);
            Main.greenPlayers.remove(player2);
            Main.bluePlayers.remove(player);
            Main.bluePlayers.remove(player2);
            Team.isInvited.remove(player);
            Team.isInvited.remove(player2);
            Team.hasInvited.remove(player);
            Team.hasInvited.remove(player2);
        }
    }
    
    static {
        Team.isInvited = new HashMap<Player, Player>();
        Team.hasInvited = new HashMap<Player, Player>();
    }
}
