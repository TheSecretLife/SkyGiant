package src.TierCraft.Minigame.SkyGiant.plugin.listeners;

import org.bukkit.event.entity.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Messages;
import src.TierCraft.Minigame.SkyGiant.plugin.game.Spectate;
import src.TierCraft.Minigame.SkyGiant.plugin.game.Winner;
import src.TierCraft.Minigame.SkyGiant.plugin.utils.Titles;

import java.util.*;
import org.bukkit.event.*;

public class EntityDeathListener implements Listener
{
    @EventHandler
    public void death(final EntityDeathEvent entityDeathEvent) {
        if (entityDeathEvent.getEntity() instanceof Giant) {
            if (Main.GoldGiantRegion.isInRegion(entityDeathEvent.getEntity().getLocation())) {
                if (Main.aliveTeams.contains("GOLD")) {
                    Main.aliveTeams.remove("GOLD");
                }
                for (final Player spectator : Bukkit.getOnlinePlayers()) {
                    Titles.sendTitle(spectator, 0, 20, 0, "?6\u2716", Messages.getMsg().getString("team-eliminated").replace("{team}", Messages.getMsg().getString("gold-team")).replace('&', '?'));
                    if (Main.goldPlayers.contains(spectator)) {
                        Spectate.setSpectator(spectator);
                        Main.respawning.remove(spectator);
                    }
                }
            }
            else if (Main.MagentaGiantRegion.isInRegion(entityDeathEvent.getEntity().getLocation())) {
                if (Main.aliveTeams.contains("MAGENTA")) {
                    Main.aliveTeams.remove("MAGENTA");
                }
                for (final Player spectator2 : Bukkit.getOnlinePlayers()) {
                    Titles.sendTitle(spectator2, 0, 20, 0, "?d\u2716", Messages.getMsg().getString("team-eliminated").replace("{team}", Messages.getMsg().getString("magenta-team")).replace('&', '?'));
                    if (Main.magentaPlayers.contains(spectator2)) {
                        Spectate.setSpectator(spectator2);
                        Main.respawning.remove(spectator2);
                    }
                }
            }
            else if (Main.GreenGiantRegion.isInRegion(entityDeathEvent.getEntity().getLocation())) {
                if (Main.aliveTeams.contains("GREEN")) {
                    Main.aliveTeams.remove("GREEN");
                }
                for (final Player spectator3 : Bukkit.getOnlinePlayers()) {
                    Titles.sendTitle(spectator3, 0, 20, 0, "?a\u2716", Messages.getMsg().getString("team-eliminated").replace("{team}", Messages.getMsg().getString("green-team")).replace('&', '?'));
                    if (Main.greenPlayers.contains(spectator3)) {
                        Spectate.setSpectator(spectator3);
                        Main.respawning.remove(spectator3);
                    }
                }
            }
            else if (Main.BlueGiantRegion.isInRegion(entityDeathEvent.getEntity().getLocation())) {
                if (Main.aliveTeams.contains("BLUE")) {
                    Main.aliveTeams.remove("BLUE");
                }
                for (final Player spectator4 : Bukkit.getOnlinePlayers()) {
                    Titles.sendTitle(spectator4, 0, 20, 0, "?9\u2716", Messages.getMsg().getString("team-eliminated").replace("{team}", Messages.getMsg().getString("blue-team")).replace('&', '?'));
                    if (Main.bluePlayers.contains(spectator4)) {
                        Spectate.setSpectator(spectator4);
                        Main.respawning.remove(spectator4);
                    }
                }
            }
            Winner.getWinner();
            entityDeathEvent.getDrops().clear();
            if (Main.goldPlayers.contains(entityDeathEvent.getEntity().getKiller())) {
                for (final Player player : Main.goldPlayers) {
                    player.sendMessage(Main.PREFIX + Messages.getMsg().getString("giant-slain").replace('&', '?'));
                    Main.money.replace(player, Main.money.get(player) + 1000);
                }
            }
            else if (Main.magentaPlayers.contains(entityDeathEvent.getEntity().getKiller())) {
                for (final Player player2 : Main.magentaPlayers) {
                    player2.sendMessage(Main.PREFIX + Messages.getMsg().getString("giant-slain").replace('&', '?'));
                    Main.money.replace(player2, Main.money.get(player2) + 1000);
                }
            }
            else if (Main.greenPlayers.contains(entityDeathEvent.getEntity().getKiller())) {
                for (final Player player3 : Main.greenPlayers) {
                    player3.sendMessage(Main.PREFIX + Messages.getMsg().getString("giant-slain").replace('&', '?'));
                    Main.money.replace(player3, Main.money.get(player3) + 1000);
                }
            }
            else if (Main.bluePlayers.contains(entityDeathEvent.getEntity().getKiller())) {
                for (final Player player4 : Main.bluePlayers) {
                    player4.sendMessage(Main.PREFIX + Messages.getMsg().getString("giant-slain").replace('&', '?'));
                    Main.money.replace(player4, Main.money.get(player4) + 1000);
                }
            }
            if (!Main.beastsSlain.containsKey(entityDeathEvent.getEntity().getKiller())) {}
        }
        if (entityDeathEvent.getEntity().getCustomName() != null && (entityDeathEvent.getEntity().getCustomName().equalsIgnoreCase(Messages.getMsg().getString("hydros").replace('&', '?')) || entityDeathEvent.getEntity().getCustomName().equalsIgnoreCase(Messages.getMsg().getString("centaur").replace('&', '?')))) {
            entityDeathEvent.getDrops().clear();
            Main.beastSpawned = false;
            Main.BeastCountDown = 180000L;
            final int n = new Random().nextInt(3) + 1;
            if (Main.beastsSlain.containsKey(entityDeathEvent.getEntity().getKiller())) {
                Main.beastsSlain.replace(entityDeathEvent.getEntity().getKiller(), Main.beastsSlain.get(entityDeathEvent.getEntity().getKiller()));
            }
            else {
                Main.beastsSlain.put(entityDeathEvent.getEntity().getKiller(), 1);
            }
            if (Main.goldPlayers.contains(entityDeathEvent.getEntity().getKiller())) {
                for (final Player player5 : Main.goldPlayers) {
                    player5.sendMessage(Main.PREFIX + Messages.getMsg().getString("beast-slain").replace('&', '?'));
                    Main.money.replace(player5, Main.money.get(player5) + 1000);
                    switch (n) {
                        case 1: {
                            player5.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 900, 2));
                            player5.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.speed")).replace('&', '?'));
                            continue;
                        }
                        case 2: {
                            player5.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 900, 2));
                            player5.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.damage")).replace('&', '?'));
                            continue;
                        }
                        case 3: {
                            player5.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 900, 2));
                            player5.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.jump")).replace('&', '?'));
                            continue;
                        }
                        case 4: {
                            player5.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 900, 2));
                            player5.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.jump")).replace('&', '?'));
                            continue;
                        }
                    }
                }
            }
            else if (Main.magentaPlayers.contains(entityDeathEvent.getEntity().getKiller())) {
                for (final Player player6 : Main.magentaPlayers) {
                    player6.sendMessage(Main.PREFIX + Messages.getMsg().getString("beast-slain").replace('&', '?'));
                    Main.money.replace(player6, Main.money.get(player6) + 1000);
                    switch (n) {
                        case 1: {
                            player6.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 900, 2));
                            player6.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.speed")).replace('&', '?'));
                            continue;
                        }
                        case 2: {
                            player6.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 900, 2));
                            player6.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.damage")).replace('&', '?'));
                            continue;
                        }
                        case 3: {
                            player6.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 900, 2));
                            player6.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.jump")).replace('&', '?'));
                            continue;
                        }
                        case 4: {
                            player6.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 900, 2));
                            player6.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.jump")).replace('&', '?'));
                            continue;
                        }
                    }
                }
            }
            else if (Main.bluePlayers.contains(entityDeathEvent.getEntity().getKiller())) {
                for (final Player player7 : Main.bluePlayers) {
                    player7.sendMessage(Main.PREFIX + Messages.getMsg().getString("beast-slain").replace('&', '?'));
                    Main.money.replace(player7, Main.money.get(player7) + 1000);
                    switch (n) {
                        case 1: {
                            player7.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 900, 2));
                            player7.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.speed")).replace('&', '?'));
                            continue;
                        }
                        case 2: {
                            player7.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 900, 2));
                            player7.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.damage")).replace('&', '?'));
                            continue;
                        }
                        case 3: {
                            player7.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 900, 2));
                            player7.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.jump")).replace('&', '?'));
                            continue;
                        }
                        case 4: {
                            player7.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 900, 2));
                            player7.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.jump")).replace('&', '?'));
                            continue;
                        }
                    }
                }
            }
            else if (Main.greenPlayers.contains(entityDeathEvent.getEntity().getKiller())) {
                for (final Player player8 : Main.greenPlayers) {
                    player8.sendMessage(Main.PREFIX + Messages.getMsg().getString("beast-slain").replace('&', '?'));
                    Main.money.replace(player8, Main.money.get(player8) + 1000);
                    switch (n) {
                        case 1: {
                            player8.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 900, 2));
                            player8.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.speed")).replace('&', '?'));
                            continue;
                        }
                        case 2: {
                            player8.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 900, 2));
                            player8.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.damage")).replace('&', '?'));
                            continue;
                        }
                        case 3: {
                            player8.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 900, 2));
                            player8.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.jump")).replace('&', '?'));
                            continue;
                        }
                        case 4: {
                            player8.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 900, 2));
                            player8.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.jump")).replace('&', '?'));
                            continue;
                        }
                    }
                }
            }
        }
    }
}
