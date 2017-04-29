package src.TierCraft.Minigame.SkyGiant.plugin.locations;

import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.plugin.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Messages;

public class Region
{
    private Location loc1;
    private Location loc2;
    private static boolean tick;
    private static boolean creeper;
    
    public Region(final Location loc1, final Location loc2) {
        this.loc1 = loc1;
        this.loc2 = loc2;
    }
    
    public boolean isInRegion(final Location location) {
        final Location location2 = new Location(this.loc1.getWorld(), (this.loc1.getX() > this.loc2.getX()) ? this.loc2.getX() : this.loc1.getX(), (this.loc1.getY() > this.loc2.getY()) ? this.loc2.getY() : this.loc1.getY(), (this.loc1.getZ() > this.loc2.getZ()) ? this.loc2.getZ() : this.loc1.getZ());
        final Location location3 = new Location(this.loc1.getWorld(), (this.loc1.getX() > this.loc2.getX()) ? this.loc1.getX() : this.loc2.getX(), (this.loc1.getY() > this.loc2.getY()) ? this.loc1.getY() : this.loc2.getY(), (this.loc1.getZ() > this.loc2.getZ()) ? this.loc1.getZ() : this.loc2.getZ());
        return location.getX() <= location3.getX() && location.getX() >= location2.getX() && location.getY() <= location3.getY() && location.getY() >= location2.getY() && location.getZ() <= location3.getZ() && location.getZ() >= location2.getZ();
    }
    
    public static String getRegion(final Player player) {
        final String replace = Messages.getMsg().getString("protected-land").replace('&', '?');
        final String replace2 = Messages.getMsg().getString("scared-land").replace('&', '?');
        final String replace3 = Messages.getMsg().getString("distructible-land").replace('&', '?');
        if (Main.BeastRegion.isInRegion(player.getLocation())) {
            if (Main.beastSpawned) {
                if (Region.tick) {
                    player.damage(1.0);
                    Region.tick = false;
                }
                else {
                    Region.tick = true;
                }
                if (Main.beastType.equalsIgnoreCase("CENTAUR") && !Region.creeper) {
                    Region.creeper = true;
                    ((Creeper)player.getWorld().spawnEntity(player.getLocation().add(1.0, 0.0, 0.0), EntityType.CREEPER)).setPowered(true);
                    Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            Region.creeper = false;
                        }
                    }, 100L);
                }
            }
            return replace2;
        }
        if (Main.MiddleRegion.isInRegion(player.getLocation())) {
            return replace;
        }
        if (Main.GoldVillagerRegion.isInRegion(player.getLocation())) {
            return replace;
        }
        if (Main.MagentaVillagerRegion.isInRegion(player.getLocation())) {
            return replace;
        }
        if (Main.GreenVillagerRegion.isInRegion(player.getLocation())) {
            return replace;
        }
        if (Main.BlueVillagerRegion.isInRegion(player.getLocation())) {
            return replace;
        }
        if (Main.GoldGiantRegion.isInRegion(player.getLocation())) {
            return replace;
        }
        if (Main.MagentaGiantRegion.isInRegion(player.getLocation())) {
            return replace;
        }
        if (Main.GreenGiantRegion.isInRegion(player.getLocation())) {
            return replace;
        }
        if (Main.BlueGiantRegion.isInRegion(player.getLocation())) {
            return replace;
        }
        return replace3;
    }
    
    static {
        Region.tick = false;
        Region.creeper = false;
    }
}
