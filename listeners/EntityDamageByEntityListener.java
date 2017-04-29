package src.TierCraft.Minigame.SkyGiant.plugin.listeners;

import org.bukkit.event.entity.*;
import org.bukkit.plugin.*;
import org.bukkit.*;
import org.bukkit.util.Vector;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Messages;
import src.TierCraft.Minigame.SkyGiant.plugin.game.GameState;
import src.TierCraft.Minigame.SkyGiant.plugin.utils.Titles;

import java.util.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class EntityDamageByEntityListener implements Listener
{
    @EventHandler
    public void damage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (Main.MAINTENANCE) {
            return;
        }
        if (Main.STATUS != GameState.PLAYING) {
            entityDamageByEntityEvent.setCancelled(true);
        }
        if (Main.respawning.contains(entityDamageByEntityEvent.getDamager())) {
            entityDamageByEntityEvent.setCancelled(true);
            return;
        }
        if (entityDamageByEntityEvent.getDamager() instanceof Player && entityDamageByEntityEvent.getEntity() instanceof Player) {
            if (Main.goldPlayers.contains(entityDamageByEntityEvent.getEntity()) && Main.goldPlayers.contains(entityDamageByEntityEvent.getDamager())) {
                entityDamageByEntityEvent.setCancelled(true);
            }
            else if (Main.magentaPlayers.contains(entityDamageByEntityEvent.getDamager()) && Main.magentaPlayers.contains(entityDamageByEntityEvent.getEntity())) {
                entityDamageByEntityEvent.setCancelled(true);
            }
            else if (Main.greenPlayers.contains(entityDamageByEntityEvent.getDamager()) && Main.greenPlayers.contains(entityDamageByEntityEvent.getEntity())) {
                entityDamageByEntityEvent.setCancelled(true);
            }
            else if (Main.bluePlayers.contains(entityDamageByEntityEvent.getDamager()) && Main.bluePlayers.contains(entityDamageByEntityEvent.getEntity())) {
                entityDamageByEntityEvent.setCancelled(true);
            }
            else if (Main.spectators.contains(entityDamageByEntityEvent.getDamager()) && Main.players.contains(entityDamageByEntityEvent.getEntity())) {
                entityDamageByEntityEvent.setCancelled(true);
            }
            else if (Main.players.contains(entityDamageByEntityEvent.getDamager()) && Main.spectators.contains(entityDamageByEntityEvent.getEntity())) {
                entityDamageByEntityEvent.setCancelled(true);
            }
        }
        else if (entityDamageByEntityEvent.getDamager() instanceof Projectile && entityDamageByEntityEvent.getEntity() instanceof Player) {
            final Projectile projectile = (Projectile)entityDamageByEntityEvent.getDamager();
            if (Main.goldPlayers.contains(entityDamageByEntityEvent.getEntity()) && Main.goldPlayers.contains(projectile.getShooter())) {
                entityDamageByEntityEvent.setCancelled(true);
            }
            else if (Main.magentaPlayers.contains(projectile.getShooter()) && Main.magentaPlayers.contains(entityDamageByEntityEvent.getEntity())) {
                entityDamageByEntityEvent.setCancelled(true);
            }
            else if (Main.greenPlayers.contains(projectile.getShooter()) && Main.greenPlayers.contains(entityDamageByEntityEvent.getEntity())) {
                entityDamageByEntityEvent.setCancelled(true);
            }
            else if (Main.bluePlayers.contains(projectile.getShooter()) && Main.bluePlayers.contains(entityDamageByEntityEvent.getEntity())) {
                entityDamageByEntityEvent.setCancelled(true);
            }
            else if (Main.spectators.contains(projectile.getShooter()) && Main.players.contains(entityDamageByEntityEvent.getEntity())) {
                entityDamageByEntityEvent.setCancelled(true);
            }
            else if (Main.players.contains(entityDamageByEntityEvent.getDamager()) && Main.spectators.contains(entityDamageByEntityEvent.getEntity())) {
                entityDamageByEntityEvent.setCancelled(true);
            }
            if (Main.respawning.contains(projectile.getShooter())) {
                entityDamageByEntityEvent.setCancelled(true);
            }
        }
        else if (entityDamageByEntityEvent.getEntity() instanceof Giant) {
            Player player;
            if (entityDamageByEntityEvent.getDamager() instanceof Player) {
                player = (Player)entityDamageByEntityEvent.getDamager();
            }
            else {
                if (!(entityDamageByEntityEvent.getDamager() instanceof Projectile)) {
                    return;
                }
                player = (Player)((Projectile)entityDamageByEntityEvent.getDamager()).getShooter();
            }
            if (Main.spectators.contains(player)) {
                entityDamageByEntityEvent.setCancelled(true);
                return;
            }
            if (Main.respawning.contains(player)) {
                entityDamageByEntityEvent.setCancelled(true);
                return;
            }
            if (Main.doubleDamage) {
                ((Giant)entityDamageByEntityEvent.getEntity()).damage(entityDamageByEntityEvent.getDamage() * 2.0);
            }
            if (Main.GoldGiantRegion.isInRegion(entityDamageByEntityEvent.getEntity().getLocation())) {
                if (Main.goldPlayers.contains(player)) {
                    entityDamageByEntityEvent.setCancelled(true);
                    if (!Main.cannotHitGiant.contains(player)) {
                        player.sendMessage(Main.PREFIX + Messages.getMsg().getString("cant-damage-giant").replace('&', '?'));
                        Main.cannotHitGiant.add(player);
                        Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                            @Override
                            public void run() {
                                Main.cannotHitGiant.remove(player);
                            }
                        }, 100L);
                    }
                    return;
                }
                if (!Main.goldHit) {
                    Main.goldHit = true;
                    entityDamageByEntityEvent.getEntity().teleport(new Location(entityDamageByEntityEvent.getEntity().getWorld(), entityDamageByEntityEvent.getEntity().getLocation().getX(), entityDamageByEntityEvent.getEntity().getLocation().getY() + 3.0, entityDamageByEntityEvent.getEntity().getLocation().getZ()));
                    Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            entityDamageByEntityEvent.getEntity().teleport(new Location(entityDamageByEntityEvent.getEntity().getWorld(), entityDamageByEntityEvent.getEntity().getLocation().getX(), entityDamageByEntityEvent.getEntity().getLocation().getY() + 3.0, entityDamageByEntityEvent.getEntity().getLocation().getZ()));
                            Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    entityDamageByEntityEvent.getEntity().teleport(new Location(entityDamageByEntityEvent.getEntity().getWorld(), entityDamageByEntityEvent.getEntity().getLocation().getX(), entityDamageByEntityEvent.getEntity().getLocation().getY() + 3.0, entityDamageByEntityEvent.getEntity().getLocation().getZ()));
                                    Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                                        @Override
                                        public void run() {
                                            entityDamageByEntityEvent.getEntity().teleport(new Location(entityDamageByEntityEvent.getEntity().getWorld(), entityDamageByEntityEvent.getEntity().getLocation().getX(), entityDamageByEntityEvent.getEntity().getLocation().getY() - 3.0, entityDamageByEntityEvent.getEntity().getLocation().getZ()));
                                            Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                                                @Override
                                                public void run() {
                                                    entityDamageByEntityEvent.getEntity().teleport(new Location(entityDamageByEntityEvent.getEntity().getWorld(), entityDamageByEntityEvent.getEntity().getLocation().getX(), entityDamageByEntityEvent.getEntity().getLocation().getY() - 3.0, entityDamageByEntityEvent.getEntity().getLocation().getZ()));
                                                    Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            entityDamageByEntityEvent.getEntity().teleport(new Location(entityDamageByEntityEvent.getEntity().getWorld(), entityDamageByEntityEvent.getEntity().getLocation().getX(), entityDamageByEntityEvent.getEntity().getLocation().getY() - 3.0, entityDamageByEntityEvent.getEntity().getLocation().getZ()));
                                                            final Location location = entityDamageByEntityEvent.getEntity().getLocation();
                                                            for (int i = 0; i < 360; i += 3) {
                                                                final Location location2 = location;
                                                                location2.setZ(location2.getZ() + Math.cos(i) * 3.0);
                                                                location2.setX(location2.getX() + Math.cos(i) * 3.0);
                                                                location.getWorld().playEffect(location2, Effect.CLOUD, 60);
                                                                location.getWorld().playEffect(location2, Effect.CLOUD, 60);
                                                                location.getWorld().playEffect(location2, Effect.CLOUD, 60);
                                                            }
                                                            for (final Entity entity : entityDamageByEntityEvent.getEntity().getNearbyEntities(4.0, 4.0, 4.0)) {
                                                                if (entity instanceof Player && !Main.goldPlayers.contains(entity)) {
                                                                    if (Main.spectators.contains(entity)) {
                                                                        return;
                                                                    }
                                                                    ((Player)entity).damage(1.0);
                                                                    entity.setVelocity(new Vector(1.0, 0.5, 1.0));
                                                                    ((Player)entity).playSound(entity.getLocation(), Main.nmsH.giantHurt(), 1.0f, 1.0f);
                                                                }
                                                            }
                                                        }
                                                    }, 4L);
                                                }
                                            }, 4L);
                                        }
                                    }, 4L);
                                }
                            }, 4L);
                        }
                    }, 4L);
                    for (final Player player2 : Main.goldPlayers) {
                        final Iterator<String> iterator2 = Messages.getMsg().getStringList("giant-attacked").iterator();
                        while (iterator2.hasNext()) {
                            player2.sendMessage(Main.PREFIX + iterator2.next().replace('&', '?'));
                            player2.playSound(player2.getLocation(), Main.nmsH.giantHurt(), 1.0f, 1.0f);
                        }
                    }
                    Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            Main.goldHit = false;
                        }
                    }, 150L);
                }
            }
            else if (Main.MagentaGiantRegion.isInRegion(entityDamageByEntityEvent.getEntity().getLocation())) {
                if (Main.magentaPlayers.contains(player)) {
                    entityDamageByEntityEvent.setCancelled(true);
                    if (!Main.cannotHitGiant.contains(player)) {
                        player.sendMessage(Main.PREFIX + Messages.getMsg().getString("cant-damage-giant").replace('&', '?'));
                        Main.cannotHitGiant.add(player);
                        Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                            @Override
                            public void run() {
                                Main.cannotHitGiant.remove(player);
                            }
                        }, 100L);
                    }
                    return;
                }
                if (!Main.magentaHit) {
                    Main.magentaHit = true;
                    entityDamageByEntityEvent.getEntity().teleport(new Location(entityDamageByEntityEvent.getEntity().getWorld(), entityDamageByEntityEvent.getEntity().getLocation().getX(), entityDamageByEntityEvent.getEntity().getLocation().getY() + 3.0, entityDamageByEntityEvent.getEntity().getLocation().getZ()));
                    Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            entityDamageByEntityEvent.getEntity().teleport(new Location(entityDamageByEntityEvent.getEntity().getWorld(), entityDamageByEntityEvent.getEntity().getLocation().getX(), entityDamageByEntityEvent.getEntity().getLocation().getY() + 3.0, entityDamageByEntityEvent.getEntity().getLocation().getZ()));
                            Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    entityDamageByEntityEvent.getEntity().teleport(new Location(entityDamageByEntityEvent.getEntity().getWorld(), entityDamageByEntityEvent.getEntity().getLocation().getX(), entityDamageByEntityEvent.getEntity().getLocation().getY() + 3.0, entityDamageByEntityEvent.getEntity().getLocation().getZ()));
                                    Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                                        @Override
                                        public void run() {
                                            entityDamageByEntityEvent.getEntity().teleport(new Location(entityDamageByEntityEvent.getEntity().getWorld(), entityDamageByEntityEvent.getEntity().getLocation().getX(), entityDamageByEntityEvent.getEntity().getLocation().getY() - 3.0, entityDamageByEntityEvent.getEntity().getLocation().getZ()));
                                            Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                                                @Override
                                                public void run() {
                                                    entityDamageByEntityEvent.getEntity().teleport(new Location(entityDamageByEntityEvent.getEntity().getWorld(), entityDamageByEntityEvent.getEntity().getLocation().getX(), entityDamageByEntityEvent.getEntity().getLocation().getY() - 3.0, entityDamageByEntityEvent.getEntity().getLocation().getZ()));
                                                    Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            entityDamageByEntityEvent.getEntity().teleport(new Location(entityDamageByEntityEvent.getEntity().getWorld(), entityDamageByEntityEvent.getEntity().getLocation().getX(), entityDamageByEntityEvent.getEntity().getLocation().getY() - 3.0, entityDamageByEntityEvent.getEntity().getLocation().getZ()));
                                                            final Location location = entityDamageByEntityEvent.getEntity().getLocation();
                                                            for (int i = 0; i < 360; i += 3) {
                                                                final Location location2 = location;
                                                                location2.setZ(location2.getZ() + Math.cos(i) * 3.0);
                                                                location2.setX(location2.getX() + Math.cos(i) * 3.0);
                                                                location.getWorld().playEffect(location2, Effect.CLOUD, 60);
                                                                location.getWorld().playEffect(location2, Effect.CLOUD, 60);
                                                                location.getWorld().playEffect(location2, Effect.CLOUD, 60);
                                                            }
                                                            for (final Entity entity : entityDamageByEntityEvent.getEntity().getNearbyEntities(4.0, 4.0, 4.0)) {
                                                                if (entity instanceof Player && !Main.magentaPlayers.contains(entity)) {
                                                                    if (Main.spectators.contains(entity)) {
                                                                        return;
                                                                    }
                                                                    ((Player)entity).damage(1.0);
                                                                    entity.setVelocity(new Vector(1.0, 0.5, 1.0));
                                                                    ((Player)entity).playSound(entity.getLocation(), Main.nmsH.giantHurt(), 1.0f, 1.0f);
                                                                }
                                                            }
                                                        }
                                                    }, 4L);
                                                }
                                            }, 4L);
                                        }
                                    }, 4L);
                                }
                            }, 4L);
                        }
                    }, 4L);
                    for (final Player player3 : Main.magentaPlayers) {
                        final Iterator<String> iterator4 = Messages.getMsg().getStringList("giant-attacked").iterator();
                        while (iterator4.hasNext()) {
                            player3.sendMessage(Main.PREFIX + iterator4.next().replace('&', '?'));
                            player3.playSound(player3.getLocation(), Main.nmsH.giantHurt(), 1.0f, 1.0f);
                        }
                    }
                    Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            Main.magentaHit = false;
                        }
                    }, 150L);
                }
            }
            else if (Main.GreenGiantRegion.isInRegion(entityDamageByEntityEvent.getEntity().getLocation())) {
                if (Main.greenPlayers.contains(player)) {
                    entityDamageByEntityEvent.setCancelled(true);
                    if (!Main.cannotHitGiant.contains(player)) {
                        player.sendMessage(Main.PREFIX + Messages.getMsg().getString("cant-damage-giant").replace('&', '?'));
                        Main.cannotHitGiant.add(player);
                        Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                            @Override
                            public void run() {
                                Main.cannotHitGiant.remove(player);
                            }
                        }, 100L);
                    }
                    return;
                }
                if (!Main.greenHit) {
                    Main.greenHit = true;
                    entityDamageByEntityEvent.getEntity().teleport(new Location(entityDamageByEntityEvent.getEntity().getWorld(), entityDamageByEntityEvent.getEntity().getLocation().getX(), entityDamageByEntityEvent.getEntity().getLocation().getY() + 3.0, entityDamageByEntityEvent.getEntity().getLocation().getZ()));
                    Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            entityDamageByEntityEvent.getEntity().teleport(new Location(entityDamageByEntityEvent.getEntity().getWorld(), entityDamageByEntityEvent.getEntity().getLocation().getX(), entityDamageByEntityEvent.getEntity().getLocation().getY() + 3.0, entityDamageByEntityEvent.getEntity().getLocation().getZ()));
                            Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    entityDamageByEntityEvent.getEntity().teleport(new Location(entityDamageByEntityEvent.getEntity().getWorld(), entityDamageByEntityEvent.getEntity().getLocation().getX(), entityDamageByEntityEvent.getEntity().getLocation().getY() + 3.0, entityDamageByEntityEvent.getEntity().getLocation().getZ()));
                                    Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                                        @Override
                                        public void run() {
                                            entityDamageByEntityEvent.getEntity().teleport(new Location(entityDamageByEntityEvent.getEntity().getWorld(), entityDamageByEntityEvent.getEntity().getLocation().getX(), entityDamageByEntityEvent.getEntity().getLocation().getY() - 3.0, entityDamageByEntityEvent.getEntity().getLocation().getZ()));
                                            Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                                                @Override
                                                public void run() {
                                                    entityDamageByEntityEvent.getEntity().teleport(new Location(entityDamageByEntityEvent.getEntity().getWorld(), entityDamageByEntityEvent.getEntity().getLocation().getX(), entityDamageByEntityEvent.getEntity().getLocation().getY() - 3.0, entityDamageByEntityEvent.getEntity().getLocation().getZ()));
                                                    Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            entityDamageByEntityEvent.getEntity().teleport(new Location(entityDamageByEntityEvent.getEntity().getWorld(), entityDamageByEntityEvent.getEntity().getLocation().getX(), entityDamageByEntityEvent.getEntity().getLocation().getY() - 3.0, entityDamageByEntityEvent.getEntity().getLocation().getZ()));
                                                            final Location location = entityDamageByEntityEvent.getEntity().getLocation();
                                                            for (int i = 0; i < 360; i += 3) {
                                                                final Location location2 = location;
                                                                location2.setZ(location2.getZ() + Math.cos(i) * 3.0);
                                                                location2.setX(location2.getX() + Math.cos(i) * 3.0);
                                                                location.getWorld().playEffect(location2, Effect.CLOUD, 60);
                                                                location.getWorld().playEffect(location2, Effect.CLOUD, 60);
                                                                location.getWorld().playEffect(location2, Effect.CLOUD, 60);
                                                            }
                                                            for (final Entity entity : entityDamageByEntityEvent.getEntity().getNearbyEntities(4.0, 4.0, 4.0)) {
                                                                if (entity instanceof Player && !Main.greenPlayers.contains(entity)) {
                                                                    if (Main.spectators.contains(entity)) {
                                                                        return;
                                                                    }
                                                                    ((Player)entity).damage(1.0);
                                                                    entity.setVelocity(new Vector(1.0, 0.5, 1.0));
                                                                    ((Player)entity).playSound(entity.getLocation(), Main.nmsH.giantHurt(), 1.0f, 1.0f);
                                                                }
                                                            }
                                                        }
                                                    }, 4L);
                                                }
                                            }, 4L);
                                        }
                                    }, 4L);
                                }
                            }, 4L);
                        }
                    }, 4L);
                    for (final Player player4 : Main.greenPlayers) {
                        final Iterator<String> iterator6 = Messages.getMsg().getStringList("giant-attacked").iterator();
                        while (iterator6.hasNext()) {
                            player4.sendMessage(Main.PREFIX + iterator6.next().replace('&', '?'));
                            player4.playSound(player4.getLocation(), Main.nmsH.giantHurt(), 1.0f, 1.0f);
                        }
                    }
                    Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            Main.greenHit = false;
                        }
                    }, 150L);
                }
            }
            else if (Main.BlueGiantRegion.isInRegion(entityDamageByEntityEvent.getEntity().getLocation())) {
                if (Main.bluePlayers.contains(player)) {
                    entityDamageByEntityEvent.setCancelled(true);
                    if (!Main.cannotHitGiant.contains(player)) {
                        player.sendMessage(Main.PREFIX + Messages.getMsg().getString("cant-damage-giant").replace('&', '?'));
                        Main.cannotHitGiant.add(player);
                        Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                            @Override
                            public void run() {
                                Main.cannotHitGiant.remove(player);
                            }
                        }, 100L);
                    }
                    return;
                }
                if (!Main.blueHit) {
                    Main.blueHit = true;
                    entityDamageByEntityEvent.getEntity().teleport(new Location(entityDamageByEntityEvent.getEntity().getWorld(), entityDamageByEntityEvent.getEntity().getLocation().getX(), entityDamageByEntityEvent.getEntity().getLocation().getY() + 3.0, entityDamageByEntityEvent.getEntity().getLocation().getZ()));
                    Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            entityDamageByEntityEvent.getEntity().teleport(new Location(entityDamageByEntityEvent.getEntity().getWorld(), entityDamageByEntityEvent.getEntity().getLocation().getX(), entityDamageByEntityEvent.getEntity().getLocation().getY() + 3.0, entityDamageByEntityEvent.getEntity().getLocation().getZ()));
                            Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    entityDamageByEntityEvent.getEntity().teleport(new Location(entityDamageByEntityEvent.getEntity().getWorld(), entityDamageByEntityEvent.getEntity().getLocation().getX(), entityDamageByEntityEvent.getEntity().getLocation().getY() + 3.0, entityDamageByEntityEvent.getEntity().getLocation().getZ()));
                                    Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                                        @Override
                                        public void run() {
                                            entityDamageByEntityEvent.getEntity().teleport(new Location(entityDamageByEntityEvent.getEntity().getWorld(), entityDamageByEntityEvent.getEntity().getLocation().getX(), entityDamageByEntityEvent.getEntity().getLocation().getY() - 3.0, entityDamageByEntityEvent.getEntity().getLocation().getZ()));
                                            Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                                                @Override
                                                public void run() {
                                                    entityDamageByEntityEvent.getEntity().teleport(new Location(entityDamageByEntityEvent.getEntity().getWorld(), entityDamageByEntityEvent.getEntity().getLocation().getX(), entityDamageByEntityEvent.getEntity().getLocation().getY() - 3.0, entityDamageByEntityEvent.getEntity().getLocation().getZ()));
                                                    Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            entityDamageByEntityEvent.getEntity().teleport(new Location(entityDamageByEntityEvent.getEntity().getWorld(), entityDamageByEntityEvent.getEntity().getLocation().getX(), entityDamageByEntityEvent.getEntity().getLocation().getY() - 3.0, entityDamageByEntityEvent.getEntity().getLocation().getZ()));
                                                            final Location location = entityDamageByEntityEvent.getEntity().getLocation();
                                                            for (int i = 0; i < 360; i += 3) {
                                                                final Location location2 = location;
                                                                location2.setZ(location2.getZ() + Math.cos(i) * 3.0);
                                                                location2.setX(location2.getX() + Math.cos(i) * 3.0);
                                                                location.getWorld().playEffect(location2, Effect.CLOUD, 60);
                                                                location.getWorld().playEffect(location2, Effect.CLOUD, 60);
                                                                location.getWorld().playEffect(location2, Effect.CLOUD, 60);
                                                            }
                                                            for (final Entity entity : entityDamageByEntityEvent.getEntity().getNearbyEntities(4.0, 4.0, 4.0)) {
                                                                if (entity instanceof Player && !Main.bluePlayers.contains(entity)) {
                                                                    if (Main.spectators.contains(entity)) {
                                                                        return;
                                                                    }
                                                                    ((Player)entity).damage(1.0);
                                                                    entity.setVelocity(new Vector(1.0, 0.5, 1.0));
                                                                    ((Player)entity).playSound(entity.getLocation(), Main.nmsH.giantHurt(), 1.0f, 1.0f);
                                                                }
                                                            }
                                                        }
                                                    }, 4L);
                                                }
                                            }, 4L);
                                        }
                                    }, 4L);
                                }
                            }, 4L);
                        }
                    }, 4L);
                    for (final Player player5 : Main.bluePlayers) {
                        final Iterator<String> iterator8 = Messages.getMsg().getStringList("giant-attacked").iterator();
                        while (iterator8.hasNext()) {
                            player5.sendMessage(Main.PREFIX + iterator8.next().replace('&', '?'));
                            player5.playSound(player5.getLocation(), Main.nmsH.giantHurt(), 1.0f, 1.0f);
                        }
                    }
                    Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            Main.blueHit = false;
                        }
                    }, 150L);
                }
            }
            final Giant giant = (Giant)entityDamageByEntityEvent.getEntity();
            if (giant.getHealth() == Main.GiantHealth) {
                Titles.sendTitle(player, 0, 10, 0, " ", "?7" + (int)giant.getHealth() + "?a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf?7 " + (int)giant.getHealth());
            }
            if (Main.GiantHealth * 90 / 100 < giant.getHealth() && giant.getHealth() < Main.GiantHealth) {
                Titles.sendTitle(player, 0, 10, 0, " ", "?7" + (int)giant.getHealth() + "?a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf?8\u25cb?7 " + (int)giant.getHealth());
            }
            if (Main.GiantHealth * 80 / 100 < giant.getHealth() && giant.getHealth() < Main.GiantHealth * 90 / 100) {
                Titles.sendTitle(player, 0, 10, 0, " ", "?7" + (int)giant.getHealth() + "?a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf?8\u25cb\u25cb?7 " + (int)giant.getHealth());
            }
            if (Main.GiantHealth * 70 / 100 < giant.getHealth() && giant.getHealth() < Main.GiantHealth * 80 / 100) {
                Titles.sendTitle(player, 0, 10, 0, " ", "?7" + (int)giant.getHealth() + "?a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf?8\u25cb\u25cb\u25cb?7 " + (int)giant.getHealth());
            }
            if (Main.GiantHealth * 60 / 100 < giant.getHealth() && giant.getHealth() < Main.GiantHealth * 70 / 100) {
                Titles.sendTitle(player, 0, 10, 0, " ", "?7" + (int)giant.getHealth() + "?a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf?8\u25cb\u25cb\u25cb\u25cb?7 " + (int)giant.getHealth());
            }
            if (Main.GiantHealth * 50 / 100 < giant.getHealth() && giant.getHealth() < Main.GiantHealth * 60 / 100) {
                Titles.sendTitle(player, 0, 10, 0, " ", "?7" + (int)giant.getHealth() + "?e \u25cf\u25cf\u25cf\u25cf\u25cf?8\u25cb\u25cb\u25cb\u25cb\u25cb?7 " + (int)giant.getHealth());
            }
            if (Main.GiantHealth * 40 / 100 < giant.getHealth() && giant.getHealth() < Main.GiantHealth * 50 / 100) {
                Titles.sendTitle(player, 0, 10, 0, " ", "?7" + (int)giant.getHealth() + "?e \u25cf\u25cf\u25cf\u25cf?8\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb?7 " + (int)giant.getHealth());
            }
            if (Main.GiantHealth * 30 / 100 < giant.getHealth() && giant.getHealth() < Main.GiantHealth * 40 / 100) {
                Titles.sendTitle(player, 0, 10, 0, " ", "?7" + (int)giant.getHealth() + "?c \u25cf\u25cf\u25cf?8\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb ?7" + (int)giant.getHealth());
            }
            if (Main.GiantHealth * 20 / 100 < giant.getHealth() && giant.getHealth() < Main.GiantHealth * 30 / 100) {
                Titles.sendTitle(player, 0, 10, 0, " ", "?7" + (int)giant.getHealth() + "?c \u25cf\u25cf?8\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb?7 " + (int)giant.getHealth());
            }
            if (Main.GiantHealth * 10 / 100 < giant.getHealth() && giant.getHealth() < Main.GiantHealth * 20 / 100) {
                Titles.sendTitle(player, 0, 10, 0, " ", "?7" + (int)giant.getHealth() + "?c \u25cf?8\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb?7 " + (int)giant.getHealth());
            }
            if (0.0 < giant.getHealth() && giant.getHealth() < Main.GiantHealth * 10 / 100) {
                Titles.sendTitle(player, 0, 10, 0, " ", "?7" + (int)giant.getHealth() + "?8 \u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb?7 " + (int)giant.getHealth());
            }
        }
        else if (entityDamageByEntityEvent.getEntity() instanceof Guardian) {
            final Guardian guardian = (Guardian)entityDamageByEntityEvent.getEntity();
            Player player6;
            if (entityDamageByEntityEvent.getDamager() instanceof Projectile) {
                player6 = (Player)((Projectile)entityDamageByEntityEvent.getDamager()).getShooter();
            }
            else {
                if (!(entityDamageByEntityEvent.getDamager() instanceof Player)) {
                    entityDamageByEntityEvent.setCancelled(true);
                    return;
                }
                player6 = (Player)entityDamageByEntityEvent.getDamager();
            }
            if (Main.spectators.contains(player6)) {
                entityDamageByEntityEvent.setCancelled(true);
            }
            if (guardian.getHealth() == Main.BeastHealth) {
                Titles.sendTitle(player6, 0, 10, 0, " ", "?7" + (int)guardian.getHealth() + "?a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf?7 " + (int)guardian.getHealth());
            }
            if (Main.BeastHealth * 90 / 100 < guardian.getHealth() && guardian.getHealth() < Main.BeastHealth) {
                Titles.sendTitle(player6, 0, 10, 0, " ", "?7" + (int)guardian.getHealth() + "?a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf?8\u25cb?7 " + (int)guardian.getHealth());
            }
            if (Main.BeastHealth * 80 / 100 < guardian.getHealth() && guardian.getHealth() < Main.BeastHealth * 90 / 100) {
                Titles.sendTitle(player6, 0, 10, 0, " ", "?7" + (int)guardian.getHealth() + "?a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf?8\u25cb\u25cb?7 " + (int)guardian.getHealth());
            }
            if (Main.BeastHealth * 70 / 100 < guardian.getHealth() && guardian.getHealth() < Main.BeastHealth * 80 / 100) {
                Titles.sendTitle(player6, 0, 10, 0, " ", "?7" + (int)guardian.getHealth() + "?a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf?8\u25cb\u25cb\u25cb?7 " + (int)guardian.getHealth());
            }
            if (Main.BeastHealth * 60 / 100 < guardian.getHealth() && guardian.getHealth() < Main.BeastHealth * 70 / 100) {
                Titles.sendTitle(player6, 0, 10, 0, " ", "?7" + (int)guardian.getHealth() + "?a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf?8\u25cb\u25cb\u25cb\u25cb?7 " + (int)guardian.getHealth());
            }
            if (Main.BeastHealth * 50 / 100 < guardian.getHealth() && guardian.getHealth() < Main.BeastHealth * 60 / 100) {
                Titles.sendTitle(player6, 0, 10, 0, " ", "?7" + (int)guardian.getHealth() + "?e \u25cf\u25cf\u25cf\u25cf\u25cf?8\u25cb\u25cb\u25cb\u25cb\u25cb?7 " + (int)guardian.getHealth());
            }
            if (Main.BeastHealth * 40 / 100 < guardian.getHealth() && guardian.getHealth() < Main.BeastHealth * 50 / 100) {
                Titles.sendTitle(player6, 0, 10, 0, " ", "?7" + (int)guardian.getHealth() + "?e \u25cf\u25cf\u25cf\u25cf?8\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb?7 " + (int)guardian.getHealth());
            }
            if (Main.BeastHealth * 30 / 100 < guardian.getHealth() && guardian.getHealth() < Main.BeastHealth * 40 / 100) {
                Titles.sendTitle(player6, 0, 10, 0, " ", "?7" + (int)guardian.getHealth() + "?c \u25cf\u25cf\u25cf?8\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb ?7" + (int)guardian.getHealth());
            }
            if (Main.BeastHealth * 20 / 100 < guardian.getHealth() && guardian.getHealth() < Main.BeastHealth * 30 / 100) {
                Titles.sendTitle(player6, 0, 10, 0, " ", "?7" + (int)guardian.getHealth() + "?c \u25cf\u25cf?8\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb?7 " + (int)guardian.getHealth());
            }
            if (Main.BeastHealth * 10 / 100 < guardian.getHealth() && guardian.getHealth() < Main.BeastHealth * 20 / 100) {
                Titles.sendTitle(player6, 0, 10, 0, " ", "?7" + (int)guardian.getHealth() + "?c \u25cf?8\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb?7 " + (int)guardian.getHealth());
            }
            if (0.0 < guardian.getHealth() && guardian.getHealth() < Main.BeastHealth * 10 / 100) {
                Titles.sendTitle(player6, 0, 10, 0, " ", "?7" + (int)guardian.getHealth() + "?8 \u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb?7 " + (int)guardian.getHealth());
            }
        }
        else if (entityDamageByEntityEvent.getEntity() instanceof Horse) {
            final Horse horse = (Horse)entityDamageByEntityEvent.getEntity();
            Player player7;
            if (entityDamageByEntityEvent.getDamager() instanceof Projectile) {
                player7 = (Player)((Projectile)entityDamageByEntityEvent.getDamager()).getShooter();
            }
            else {
                if (!(entityDamageByEntityEvent.getDamager() instanceof Player)) {
                    entityDamageByEntityEvent.setCancelled(true);
                    return;
                }
                player7 = (Player)entityDamageByEntityEvent.getDamager();
            }
            if (Main.spectators.contains(player7)) {
                entityDamageByEntityEvent.setCancelled(true);
            }
            if (horse.getHealth() == Main.BeastHealth) {
                Titles.sendTitle(player7, 0, 10, 0, " ", "?7" + (int)horse.getHealth() + "?a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf?7 " + (int)horse.getHealth());
            }
            if (Main.BeastHealth * 90 / 100 < horse.getHealth() && horse.getHealth() < Main.BeastHealth) {
                Titles.sendTitle(player7, 0, 10, 0, " ", "?7" + (int)horse.getHealth() + "?a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf?8\u25cb?7 " + (int)horse.getHealth());
            }
            if (Main.BeastHealth * 80 / 100 < horse.getHealth() && horse.getHealth() < Main.BeastHealth * 90 / 100) {
                Titles.sendTitle(player7, 0, 10, 0, " ", "?7" + (int)horse.getHealth() + "?a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf?8\u25cb\u25cb?7 " + (int)horse.getHealth());
            }
            if (Main.BeastHealth * 70 / 100 < horse.getHealth() && horse.getHealth() < Main.BeastHealth * 80 / 100) {
                Titles.sendTitle(player7, 0, 10, 0, " ", "?7" + (int)horse.getHealth() + "?a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf?8\u25cb\u25cb\u25cb?7 " + (int)horse.getHealth());
            }
            if (Main.BeastHealth * 60 / 100 < horse.getHealth() && horse.getHealth() < Main.BeastHealth * 70 / 100) {
                Titles.sendTitle(player7, 0, 10, 0, " ", "?7" + (int)horse.getHealth() + "?a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf?8\u25cb\u25cb\u25cb\u25cb?7 " + (int)horse.getHealth());
            }
            if (Main.BeastHealth * 50 / 100 < horse.getHealth() && horse.getHealth() < Main.BeastHealth * 60 / 100) {
                Titles.sendTitle(player7, 0, 10, 0, " ", "?7" + (int)horse.getHealth() + "?e \u25cf\u25cf\u25cf\u25cf\u25cf?8\u25cb\u25cb\u25cb\u25cb\u25cb?7 " + (int)horse.getHealth());
            }
            if (Main.BeastHealth * 40 / 100 < horse.getHealth() && horse.getHealth() < Main.BeastHealth * 50 / 100) {
                Titles.sendTitle(player7, 0, 10, 0, " ", "?7" + (int)horse.getHealth() + "?e \u25cf\u25cf\u25cf\u25cf?8\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb?7 " + (int)horse.getHealth());
            }
            if (Main.BeastHealth * 30 / 100 < horse.getHealth() && horse.getHealth() < Main.BeastHealth * 40 / 100) {
                Titles.sendTitle(player7, 0, 10, 0, " ", "?7" + (int)horse.getHealth() + "?c \u25cf\u25cf\u25cf?8\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb ?7" + (int)horse.getHealth());
            }
            if (Main.BeastHealth * 20 / 100 < horse.getHealth() && horse.getHealth() < Main.BeastHealth * 30 / 100) {
                Titles.sendTitle(player7, 0, 10, 0, " ", "?7" + (int)horse.getHealth() + "?c \u25cf\u25cf?8\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb?7 " + (int)horse.getHealth());
            }
            if (Main.BeastHealth * 10 / 100 < horse.getHealth() && horse.getHealth() < Main.BeastHealth * 20 / 100) {
                Titles.sendTitle(player7, 0, 10, 0, " ", "?7" + (int)horse.getHealth() + "?c \u25cf?8\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb?7 " + (int)horse.getHealth());
            }
            if (0.0 < horse.getHealth() && horse.getHealth() < Main.BeastHealth * 10 / 100) {
                Titles.sendTitle(player7, 0, 10, 0, " ", "?7" + (int)horse.getHealth() + "?8 \u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb?7 " + (int)horse.getHealth());
            }
        }
    }
}
