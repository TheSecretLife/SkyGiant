package src.TierCraft.Minigame.SkyGiant.plugin.listeners;

import org.bukkit.entity.*;
import org.bukkit.scheduler.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Messages;
import src.TierCraft.Minigame.SkyGiant.plugin.game.Spectate;
import src.TierCraft.Minigame.SkyGiant.plugin.locations.Spawns;
import src.TierCraft.Minigame.SkyGiant.plugin.protocolLib.Borders;
import src.TierCraft.Minigame.SkyGiant.plugin.utils.Titles;

import org.bukkit.*;
import java.util.*;
import org.bukkit.plugin.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.*;

public class PlayerDeathListener implements Listener
{
    @EventHandler
    public void death(final PlayerDeathEvent playerDeathEvent) {
        if (Main.MAINTENANCE) {
            return;
        }
        if (Main.spectators.contains(playerDeathEvent.getEntity())) {
            playerDeathEvent.getEntity().spigot().respawn();
            return;
        }
        playerDeathEvent.getDrops().clear();
        playerDeathEvent.setDeathMessage((String)null);
        if (!Main.deaths.containsKey(playerDeathEvent.getEntity())) {
            Main.deaths.put(playerDeathEvent.getEntity(), 1);
        }
        else {
            Main.deaths.replace(playerDeathEvent.getEntity(), Main.deaths.get(playerDeathEvent.getEntity()) + 1);
        }
        Main.respawning.add(playerDeathEvent.getEntity());
        if (playerDeathEvent.getEntity().getKiller() instanceof Player) {
            playerDeathEvent.getEntity().getKiller().sendMessage(Main.PREFIX + Messages.getMsg().getString("gold-for-killing").replace("{player}", playerDeathEvent.getEntity().getName()).replace('&', '?'));
            if (Main.money.containsKey(playerDeathEvent.getEntity())) {
                Main.money.replace(playerDeathEvent.getEntity(), Main.money.get(playerDeathEvent.getEntity()) + 250);
            }
            if (Main.kills.containsKey(playerDeathEvent.getEntity())) {
                Main.kills.replace(playerDeathEvent.getEntity(), Main.kills.get(playerDeathEvent.getEntity()) + 1);
            }
            playerDeathEvent.getEntity().spigot().respawn();
            if (!Main.respawningKill.containsKey(playerDeathEvent.getEntity())) {
                Main.respawningKill.put(playerDeathEvent.getEntity(), 3);
            }
            else if (Main.respawningKill.get(playerDeathEvent.getEntity()) < 15) {
                Main.respawningKill.replace(playerDeathEvent.getEntity(), Main.respawningKill.get(playerDeathEvent.getEntity()) + 4);
            }
            final Iterator<Player> iterator = Main.players.iterator();
            while (iterator.hasNext()) {
                iterator.next().hidePlayer(playerDeathEvent.getEntity());
            }
            new BukkitRunnable() {
                int i = Main.respawningKill.get(playerDeathEvent.getEntity());
                
                public void run() {
                    if (this.i != 0) {
                        --this.i;
                    }
                    if (!Main.respawning.contains(playerDeathEvent.getEntity())) {
                        this.cancel();
                        Spectate.setSpectator(playerDeathEvent.getEntity());
                    }
                    if (this.i == 15 || this.i == 14 || this.i == 13 || this.i == 12 || this.i == 11 || this.i == 10 || this.i == 9 || this.i == 8 || this.i == 7 || this.i == 6 || this.i == 5 || this.i == 4) {
                        Titles.sendTitle(playerDeathEvent.getEntity(), 0, 14, 0, " ", Messages.getMsg().getString("respawning-in").replace("{time}".replace("&l", ""), String.valueOf(this.i)).replace('&', '?'));
                    }
                    if (this.i == 3) {
                        Titles.sendTitle(playerDeathEvent.getEntity(), 0, 14, 0, " ", Messages.getMsg().getString("respawning-in").replace("{time}".replace("&l", ""), "\u278c").replace('&', '?'));
                    }
                    if (this.i == 2) {
                        Titles.sendTitle(playerDeathEvent.getEntity(), 0, 14, 0, " ", Messages.getMsg().getString("respawning-in").replace("{time}".replace("&l", ""), "\u278b").replace('&', '?'));
                    }
                    if (this.i == 1) {
                        Titles.sendTitle(playerDeathEvent.getEntity(), 0, 14, 0, " ", Messages.getMsg().getString("respawning-in").replace("{time}".replace("&l", ""), "\u278a").replace('&', '?'));
                    }
                    if (this.i == 0) {
                        this.cancel();
                        Titles.fightTitle(playerDeathEvent.getEntity());
                        Main.respawning.remove(playerDeathEvent.getEntity());
                        if (Main.respBeac.containsValue(playerDeathEvent.getEntity())) {
                            playerDeathEvent.getEntity().teleport((Location)Main.respOwn.get(playerDeathEvent.getEntity().getName()));
                        }
                        else if (Main.goldPlayers.contains(playerDeathEvent.getEntity())) {
                            playerDeathEvent.getEntity().teleport(Spawns.getSpawn("Gold"));
                        }
                        else if (Main.magentaPlayers.contains(playerDeathEvent.getEntity())) {
                            playerDeathEvent.getEntity().teleport(Spawns.getSpawn("Magenta"));
                        }
                        else if (Main.greenPlayers.contains(playerDeathEvent.getEntity())) {
                            playerDeathEvent.getEntity().teleport(Spawns.getSpawn("Green"));
                        }
                        else if (Main.bluePlayers.contains(playerDeathEvent.getEntity())) {
                            playerDeathEvent.getEntity().teleport(Spawns.getSpawn("Blue"));
                        }
                        final Iterator<? extends Player> iterator = Bukkit.getOnlinePlayers().iterator();
                        while (iterator.hasNext()) {
                            iterator.next().showPlayer(playerDeathEvent.getEntity());
                        }
                        playerDeathEvent.getEntity().setAllowFlight(false);
                    }
                }
            }.runTaskTimer((Plugin)Main.plugin, 0L, 20L);
            return;
        }
        if (playerDeathEvent.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.VOID) {
            playerDeathEvent.getEntity().spigot().respawn();
            if (!Main.respawningVoid.containsKey(playerDeathEvent.getEntity())) {
                Main.respawningVoid.put(playerDeathEvent.getEntity(), 3);
            }
            else if (Main.respawningVoid.get(playerDeathEvent.getEntity()) < 15) {
                Main.respawningVoid.replace(playerDeathEvent.getEntity(), Main.respawningVoid.get(playerDeathEvent.getEntity()) + 2);
            }
            final Iterator<Player> iterator2 = Main.players.iterator();
            while (iterator2.hasNext()) {
                iterator2.next().hidePlayer(playerDeathEvent.getEntity());
            }
            new BukkitRunnable() {
                int i = Main.respawningVoid.get(playerDeathEvent.getEntity());
                
                public void run() {
                    if (this.i != 0) {
                        --this.i;
                    }
                    if (!Main.respawning.contains(playerDeathEvent.getEntity())) {
                        this.cancel();
                        Spectate.setSpectator(playerDeathEvent.getEntity());
                    }
                    if (this.i == 15 || this.i == 14 || this.i == 13 || this.i == 12 || this.i == 11 || this.i == 10 || this.i == 9 || this.i == 8 || this.i == 7 || this.i == 6 || this.i == 5 || this.i == 4) {
                        Titles.sendTitle(playerDeathEvent.getEntity(), 0, 14, 0, " ", Messages.getMsg().getString("respawning-in").replace("{time}", String.valueOf(this.i)).replace('&', '?'));
                    }
                    if (this.i == 3) {
                        Titles.sendTitle(playerDeathEvent.getEntity(), 0, 14, 0, " ", Messages.getMsg().getString("respawning-in").replace("{time}", "\u278c").replace('&', '?'));
                    }
                    if (this.i == 2) {
                        Titles.sendTitle(playerDeathEvent.getEntity(), 0, 14, 0, " ", Messages.getMsg().getString("respawning-in").replace("{time}", "\u278b").replace('&', '?'));
                    }
                    if (this.i == 1) {
                        Titles.sendTitle(playerDeathEvent.getEntity(), 0, 14, 0, " ", Messages.getMsg().getString("respawning-in").replace("{time}", "\u278a").replace('&', '?'));
                    }
                    if (this.i == 0) {
                        this.cancel();
                        Titles.fightTitle(playerDeathEvent.getEntity());
                        Main.respawning.remove(playerDeathEvent.getEntity());
                        if (Main.respBeac.containsValue(playerDeathEvent.getEntity())) {
                            playerDeathEvent.getEntity().teleport((Location)Main.respOwn.get(playerDeathEvent.getEntity().getName()));
                        }
                        else if (Main.goldPlayers.contains(playerDeathEvent.getEntity())) {
                            playerDeathEvent.getEntity().teleport(Spawns.getSpawn("Gold"));
                            if (Main.warmup) {
                                Borders.goldBorders(playerDeathEvent.getEntity());
                            }
                        }
                        else if (Main.magentaPlayers.contains(playerDeathEvent.getEntity())) {
                            playerDeathEvent.getEntity().teleport(Spawns.getSpawn("Magenta"));
                            if (Main.warmup) {
                                Borders.magentaBorders(playerDeathEvent.getEntity());
                            }
                        }
                        else if (Main.greenPlayers.contains(playerDeathEvent.getEntity())) {
                            playerDeathEvent.getEntity().teleport(Spawns.getSpawn("Green"));
                            if (Main.warmup) {
                                Borders.greenBorders(playerDeathEvent.getEntity());
                            }
                        }
                        else if (Main.bluePlayers.contains(playerDeathEvent.getEntity())) {
                            playerDeathEvent.getEntity().teleport(Spawns.getSpawn("Blue"));
                            if (Main.warmup) {
                                Borders.blueBorders(playerDeathEvent.getEntity());
                            }
                        }
                        final Iterator<? extends Player> iterator = Bukkit.getOnlinePlayers().iterator();
                        while (iterator.hasNext()) {
                            iterator.next().showPlayer(playerDeathEvent.getEntity());
                        }
                        playerDeathEvent.getEntity().setAllowFlight(false);
                    }
                }
            }.runTaskTimer((Plugin)Main.plugin, 0L, 20L);
        }
        else {
            playerDeathEvent.getEntity().spigot().respawn();
            if (!Main.respawningKill.containsKey(playerDeathEvent.getEntity())) {
                Main.respawningKill.put(playerDeathEvent.getEntity(), 3);
            }
            else if (Main.respawningKill.get(playerDeathEvent.getEntity()) < 15) {
                Main.respawningKill.replace(playerDeathEvent.getEntity(), Main.respawningKill.get(playerDeathEvent.getEntity()) + 4);
            }
            final Iterator<Player> iterator3 = Main.players.iterator();
            while (iterator3.hasNext()) {
                iterator3.next().hidePlayer(playerDeathEvent.getEntity());
            }
            new BukkitRunnable() {
                int i = Main.respawningKill.get(playerDeathEvent.getEntity());
                
                public void run() {
                    if (this.i != 0) {
                        --this.i;
                    }
                    if (!Main.respawning.contains(playerDeathEvent.getEntity())) {
                        this.cancel();
                        Spectate.setSpectator(playerDeathEvent.getEntity());
                    }
                    if (this.i == 15 || this.i == 14 || this.i == 13 || this.i == 12 || this.i == 11 || this.i == 10 || this.i == 9 || this.i == 8 || this.i == 7 || this.i == 6 || this.i == 5 || this.i == 4) {
                        Titles.sendTitle(playerDeathEvent.getEntity(), 0, 14, 0, " ", Messages.getMsg().getString("respawning-in").replace("{time}".replace("&l", ""), String.valueOf(this.i)).replace('&', '?'));
                    }
                    if (this.i == 3) {
                        Titles.sendTitle(playerDeathEvent.getEntity(), 0, 14, 0, " ", Messages.getMsg().getString("respawning-in").replace("{time}".replace("&l", ""), "\u278c").replace('&', '?'));
                    }
                    if (this.i == 2) {
                        Titles.sendTitle(playerDeathEvent.getEntity(), 0, 14, 0, " ", Messages.getMsg().getString("respawning-in").replace("{time}".replace("&l", ""), "\u278b").replace('&', '?'));
                    }
                    if (this.i == 1) {
                        Titles.sendTitle(playerDeathEvent.getEntity(), 0, 14, 0, " ", Messages.getMsg().getString("respawning-in").replace("{time}".replace("&l", ""), "\u278a").replace('&', '?'));
                    }
                    if (this.i == 0) {
                        this.cancel();
                        Titles.fightTitle(playerDeathEvent.getEntity());
                        Main.respawning.remove(playerDeathEvent.getEntity());
                        if (Main.respBeac.containsValue(playerDeathEvent.getEntity())) {
                            playerDeathEvent.getEntity().teleport((Location)Main.respOwn.get(playerDeathEvent.getEntity().getName()));
                        }
                        else if (Main.goldPlayers.contains(playerDeathEvent.getEntity())) {
                            playerDeathEvent.getEntity().teleport(Spawns.getSpawn("Gold"));
                        }
                        else if (Main.magentaPlayers.contains(playerDeathEvent.getEntity())) {
                            playerDeathEvent.getEntity().teleport(Spawns.getSpawn("Magenta"));
                        }
                        else if (Main.greenPlayers.contains(playerDeathEvent.getEntity())) {
                            playerDeathEvent.getEntity().teleport(Spawns.getSpawn("Green"));
                        }
                        else if (Main.bluePlayers.contains(playerDeathEvent.getEntity())) {
                            playerDeathEvent.getEntity().teleport(Spawns.getSpawn("Blue"));
                        }
                        final Iterator<? extends Player> iterator = Bukkit.getOnlinePlayers().iterator();
                        while (iterator.hasNext()) {
                            iterator.next().showPlayer(playerDeathEvent.getEntity());
                        }
                        playerDeathEvent.getEntity().setAllowFlight(false);
                    }
                }
            }.runTaskTimer((Plugin)Main.plugin, 0L, 20L);
        }
    }
}
