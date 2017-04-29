package src.TierCraft.Minigame.SkyGiant.plugin.commands;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.scheduler.*;
import org.bukkit.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.game.GameState;
import src.TierCraft.Minigame.SkyGiant.plugin.locations.Spawns;

import org.bukkit.plugin.*;

public class Recall implements CommandExecutor
{
    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] array) {
        if (!(commandSender instanceof Player)) {
            return true;
        }
        final Player player = (Player)commandSender;
        if (command.getName().equalsIgnoreCase("recall")) {
            if (Main.STATUS != GameState.PLAYING) {
                return true;
            }
            if (Main.recall.contains(player)) {
                return true;
            }
            if (Main.respawning.contains(player)) {
                return true;
            }
            if (Main.spectators.contains(player)) {
                return true;
            }
            Main.recall.add(player);
            new BukkitRunnable() {
                int i = 40;
                
                public void run() {
                    if (this.i != 0) {
                        --this.i;
                    }
                    if (Main.recall.contains(player)) {
                        player.getWorld().playEffect(player.getLocation(), Effect.CLOUD, 1, 0);
                        player.getWorld().playEffect(player.getLocation(), Effect.CLOUD, 1, 0);
                        player.getWorld().playEffect(player.getLocation(), Effect.CLOUD, 1, 0);
                        player.getWorld().playEffect(player.getLocation().add(0.0, 1.0, 0.0), Effect.CLOUD, 1, 0);
                        player.getWorld().playEffect(player.getLocation().add(0.0, 1.0, 0.0), Effect.CLOUD, 1, 0);
                        player.getWorld().playEffect(player.getLocation().add(0.0, 1.0, 0.0), Effect.CLOUD, 1, 0);
                        player.getWorld().playEffect(player.getLocation().add(0.0, 1.8, 0.0), Effect.CLOUD, 1, 0);
                        player.getWorld().playEffect(player.getLocation().add(0.0, 1.8, 0.0), Effect.CLOUD, 1, 0);
                        player.getWorld().playEffect(player.getLocation().add(0.0, 1.8, 0.0), Effect.CLOUD, 1, 0);
                        player.getWorld().playEffect(player.getLocation().add(0.0, 1.8, 0.0), Effect.CLOUD, 1, 0);
                    }
                    else {
                        this.cancel();
                    }
                    if (this.i == 0) {
                        this.cancel();
                        if (Main.magentaPlayers.contains(player)) {
                            player.teleport(Spawns.getSpawn("Magenta"));
                        }
                        else if (Main.goldPlayers.contains(player)) {
                            player.teleport(Spawns.getSpawn("Gold"));
                        }
                        else if (Main.greenPlayers.contains(player)) {
                            player.teleport(Spawns.getSpawn("Green"));
                        }
                        else if (Main.bluePlayers.contains(player)) {
                            player.teleport(Spawns.getSpawn("Blue"));
                        }
                        Main.recall.remove(player);
                    }
                }
            }.runTaskTimer((Plugin)Main.plugin, 0L, 5L);
        }
        return false;
    }
}
