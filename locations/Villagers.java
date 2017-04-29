package src.TierCraft.Minigame.SkyGiant.plugin.locations;

import org.bukkit.entity.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.ArenaCfg;

import org.bukkit.configuration.file.*;
import java.io.*;
import org.bukkit.*;

public class Villagers
{
    public static void setVillager(final String s, final Player player) {
        final File file = new File("plugins/SkyGiants1058/Arenas/" + player.getWorld().getName() + ".yml");
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(file);
        if (s.equalsIgnoreCase("gold")) {
            loadConfiguration.set("Villager.Gold.X", (Object)player.getLocation().getX());
            loadConfiguration.set("Villager.Gold.Y", (Object)player.getLocation().getY());
            loadConfiguration.set("Villager.Gold.Z", (Object)player.getLocation().getZ());
            loadConfiguration.set("Villager.Gold.Yaw", (Object)player.getLocation().getYaw());
            loadConfiguration.set("Villager.Gold.Pitch", (Object)player.getLocation().getPitch());
            loadConfiguration.set("Region.Gold.Villager.Pos1.X", (Object)(player.getLocation().getX() - 2.0));
            loadConfiguration.set("Region.Gold.Villager.Pos1.Y", (Object)(player.getLocation().getY() - 2.0));
            loadConfiguration.set("Region.Gold.Villager.Pos1.Z", (Object)(player.getLocation().getZ() - 2.0));
            loadConfiguration.set("Region.Gold.Villager.Pos2.X", (Object)(player.getLocation().getX() + 2.0));
            loadConfiguration.set("Region.Gold.Villager.Pos2.Y", (Object)(player.getLocation().getY() + 2.0));
            loadConfiguration.set("Region.Gold.Villager.Pos2.Z", (Object)(player.getLocation().getZ() + 2.0));
            try {
                loadConfiguration.save(file);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            player.sendMessage(Main.PREFIX + "?6Gold ?bVillager set!");
        }
        else if (s.equalsIgnoreCase("magenta")) {
            loadConfiguration.set("Villager.Magenta.X", (Object)player.getLocation().getX());
            loadConfiguration.set("Villager.Magenta.Y", (Object)player.getLocation().getY());
            loadConfiguration.set("Villager.Magenta.Z", (Object)player.getLocation().getZ());
            loadConfiguration.set("Villager.Magenta.Yaw", (Object)player.getLocation().getYaw());
            loadConfiguration.set("Villager.Magenta.Pitch", (Object)player.getLocation().getPitch());
            loadConfiguration.set("Region.Magenta.Villager.Pos1.X", (Object)(player.getLocation().getX() - 2.0));
            loadConfiguration.set("Region.Magenta.Villager.Pos1.Y", (Object)(player.getLocation().getY() - 2.0));
            loadConfiguration.set("Region.Magenta.Villager.Pos1.Z", (Object)(player.getLocation().getZ() - 2.0));
            loadConfiguration.set("Region.Magenta.Villager.Pos2.X", (Object)(player.getLocation().getX() + 2.0));
            loadConfiguration.set("Region.Magenta.Villager.Pos2.Y", (Object)(player.getLocation().getY() + 2.0));
            loadConfiguration.set("Region.Magenta.Villager.Pos2.Z", (Object)(player.getLocation().getZ() + 2.0));
            try {
                loadConfiguration.save(file);
            }
            catch (IOException ex2) {
                ex2.printStackTrace();
            }
            player.sendMessage(Main.PREFIX + "?5Magenta ?bVillager set!");
        }
        else if (s.equalsIgnoreCase("green")) {
            loadConfiguration.set("Villager.Green.X", (Object)player.getLocation().getX());
            loadConfiguration.set("Villager.Green.Y", (Object)player.getLocation().getY());
            loadConfiguration.set("Villager.Green.Z", (Object)player.getLocation().getZ());
            loadConfiguration.set("Villager.Green.Yaw", (Object)player.getLocation().getYaw());
            loadConfiguration.set("Villager.Green.Pitch", (Object)player.getLocation().getPitch());
            loadConfiguration.set("Region.Green.Villager.Pos1.X", (Object)(player.getLocation().getX() - 2.0));
            loadConfiguration.set("Region.Green.Villager.Pos1.Y", (Object)(player.getLocation().getY() - 2.0));
            loadConfiguration.set("Region.Green.Villager.Pos1.Z", (Object)(player.getLocation().getZ() - 2.0));
            loadConfiguration.set("Region.Green.Villager.Pos2.X", (Object)(player.getLocation().getX() + 2.0));
            loadConfiguration.set("Region.Green.Villager.Pos2.Y", (Object)(player.getLocation().getY() + 2.0));
            loadConfiguration.set("Region.Green.Villager.Pos2.Z", (Object)(player.getLocation().getZ() + 2.0));
            try {
                loadConfiguration.save(file);
            }
            catch (IOException ex3) {
                ex3.printStackTrace();
            }
            player.sendMessage(Main.PREFIX + "?aGreen ?bVillager set!");
        }
        else if (s.equalsIgnoreCase("blue")) {
            loadConfiguration.set("Villager.Blue.X", (Object)player.getLocation().getX());
            loadConfiguration.set("Villager.Blue.Y", (Object)player.getLocation().getY());
            loadConfiguration.set("Villager.Blue.Z", (Object)player.getLocation().getZ());
            loadConfiguration.set("Villager.Blue.Yaw", (Object)player.getLocation().getYaw());
            loadConfiguration.set("Villager.Blue.Pitch", (Object)player.getLocation().getPitch());
            loadConfiguration.set("Region.Blue.Villager.Pos1.X", (Object)(player.getLocation().getX() - 2.0));
            loadConfiguration.set("Region.Blue.Villager.Pos1.Y", (Object)(player.getLocation().getY() - 2.0));
            loadConfiguration.set("Region.Blue.Villager.Pos1.Z", (Object)(player.getLocation().getZ() - 2.0));
            loadConfiguration.set("Region.Blue.Villager.Pos2.X", (Object)(player.getLocation().getX() + 2.0));
            loadConfiguration.set("Region.Blue.Villager.Pos2.Y", (Object)(player.getLocation().getY() + 2.0));
            loadConfiguration.set("Region.Blue.Villager.Pos2.Z", (Object)(player.getLocation().getZ() + 2.0));
            try {
                loadConfiguration.save(file);
            }
            catch (IOException ex4) {
                ex4.printStackTrace();
            }
            player.sendMessage(Main.PREFIX + "?9Blue ?bVillager set!");
        }
        else {
            player.sendMessage(Main.PREFIX + "?bChoices: &6Gold&b, &5Magenta&b, &aGreen&b, &9Blue&b.");
        }
    }
    
    public static void checkVillagers(final Player player) {
        final String name = player.getLocation().getWorld().getName();
        if (ArenaCfg.getArena(name).get("Villager.Gold.X") == null && ArenaCfg.getArena(name).get("Villager.Magenta.X") == null && ArenaCfg.getArena(name).get("Villager.Green.X") != null && ArenaCfg.getArena(name).get("Villager.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setVillager ?6\u2588 ?5\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Villager.Gold.X") != null && ArenaCfg.getArena(name).get("Villager.Magenta.X") == null && ArenaCfg.getArena(name).get("Villager.Green.X") == null && ArenaCfg.getArena(name).get("Villager.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setVillager ?5\u2588 ?a\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Villager.Gold.X") != null && ArenaCfg.getArena(name).get("Villager.Magenta.X") != null && ArenaCfg.getArena(name).get("Villager.Green.X") == null && ArenaCfg.getArena(name).get("Villager.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setVillager ?a\u2588 ?9\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Villager.Gold.X") == null && ArenaCfg.getArena(name).get("Villager.Magenta.X") != null && ArenaCfg.getArena(name).get("Villager.Green.X") != null && ArenaCfg.getArena(name).get("Villager.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setVillager ?6\u2588 ?9\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Villager.Gold.X") != null && ArenaCfg.getArena(name).get("Villager.Magenta.X") == null && ArenaCfg.getArena(name).get("Villager.Green.X") == null && ArenaCfg.getArena(name).get("Villager.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setVillager ?5\u2588 ?a\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Villager.Gold.X") == null && ArenaCfg.getArena(name).get("Villager.Magenta.X") != null && ArenaCfg.getArena(name).get("Villager.Green.X") == null && ArenaCfg.getArena(name).get("Villager.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setVillager ?6\u2588 ?a\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Villager.Gold.X") != null && ArenaCfg.getArena(name).get("Villager.Magenta.X") == null && ArenaCfg.getArena(name).get("Villager.Green.X") != null && ArenaCfg.getArena(name).get("Villager.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setVillager ?5\u2588 ?9\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Villager.Gold.X") == null && ArenaCfg.getArena(name).get("Villager.Magenta.X") == null && ArenaCfg.getArena(name).get("Villager.Green.X") == null && ArenaCfg.getArena(name).get("Villager.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setVillager ?6\u2588 ?5\u2588 ?a\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Villager.Gold.X") != null && ArenaCfg.getArena(name).get("Villager.Magenta.X") == null && ArenaCfg.getArena(name).get("Villager.Green.X") == null && ArenaCfg.getArena(name).get("Villager.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setVillager ?5\u2588 ?a\u2588 ?9\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Villager.Gold.X") == null && ArenaCfg.getArena(name).get("Villager.Magenta.X") != null && ArenaCfg.getArena(name).get("Villager.Green.X") == null && ArenaCfg.getArena(name).get("Villager.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setVillager ?6\u2588 ?a\u2588 ?9\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Villager.Gold.X") == null && ArenaCfg.getArena(name).get("Villager.Magenta.X") == null && ArenaCfg.getArena(name).get("Villager.Green.X") == null && ArenaCfg.getArena(name).get("Villager.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setVillager ?6\u2588 ?a\u2588 ?5\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Villager.Gold.X") == null && ArenaCfg.getArena(name).get("Villager.Magenta.X") != null && ArenaCfg.getArena(name).get("Villager.Green.X") != null && ArenaCfg.getArena(name).get("Villager.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setVillager ?6\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Villager.Gold.X") != null && ArenaCfg.getArena(name).get("Villager.Magenta.X") == null && ArenaCfg.getArena(name).get("Villager.Green.X") != null && ArenaCfg.getArena(name).get("Villager.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setVillager ?5\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Villager.Gold.X") != null && ArenaCfg.getArena(name).get("Villager.Magenta.X") != null && ArenaCfg.getArena(name).get("Villager.Green.X") == null && ArenaCfg.getArena(name).get("Villager.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setVillager ?a\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Villager.Gold.X") != null && ArenaCfg.getArena(name).get("Villager.Magenta.X") != null && ArenaCfg.getArena(name).get("Villager.Green.X") != null && ArenaCfg.getArena(name).get("Villager.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setVillager ?9\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Villager.Gold.X") == null && ArenaCfg.getArena(name).get("Villager.Magenta.X") == null && ArenaCfg.getArena(name).get("Villager.Green.X") == null && ArenaCfg.getArena(name).get("Villager.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setVillager ?6\u2588 ?5\u2588 ?a\u2588 ?9\u2588");
        }
    }
    
    public static Location getVillager(final String s) {
        return new Location(Bukkit.getWorld(Main.choosenMap), ArenaCfg.getArena(Main.choosenMap).getDouble("Villager." + s + ".X"), ArenaCfg.getArena(Main.choosenMap).getDouble("Villager." + s + ".Y"), ArenaCfg.getArena(Main.choosenMap).getDouble("Villager." + s + ".Z"), (float)ArenaCfg.getArena(Main.choosenMap).getDouble("Villager." + s + ".Yaw"), (float)ArenaCfg.getArena(Main.choosenMap).getDouble("Villager." + s + ".Pitch"));
    }
    
    private static Location getVillagerPos1(final String s) {
        return new Location(Bukkit.getWorld(Main.choosenMap), ArenaCfg.getArena(Main.choosenMap).getDouble("Region." + s + ".Villager.Pos1.X"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region." + s + ".Villager.Pos1.Y"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region." + s + ".Villager.Pos1.Z"));
    }
    
    private static Location getVillagerPos2(final String s) {
        return new Location(Bukkit.getWorld(Main.choosenMap), ArenaCfg.getArena(Main.choosenMap).getDouble("Region." + s + ".Villager.Pos2.X"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region." + s + ".Villager.Pos2.Y"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region." + s + ".Villager.Pos2.Z"));
    }
    
    public static void loadVillagersRegions() {
        if (ArenaCfg.getArena(Main.choosenMap).get("Region.Gold.Villager.Pos1.X") != null || ArenaCfg.getArena(Main.choosenMap).get("Region.Gold.Villager.Pos2.X") != null) {
            Main.GoldVillagerRegion = new Region(getVillagerPos1("Gold"), getVillagerPos2("Gold"));
        }
        if (ArenaCfg.getArena(Main.choosenMap).get("Region.Magenta.Villager.Pos1.X") != null || ArenaCfg.getArena(Main.choosenMap).get("Region.Magenta.Villager.Pos2.X") != null) {
            Main.MagentaVillagerRegion = new Region(getVillagerPos1("Magenta"), getVillagerPos2("Magenta"));
        }
        if (ArenaCfg.getArena(Main.choosenMap).get("Region.Green.Villager.Pos1.X") != null || ArenaCfg.getArena(Main.choosenMap).get("Region.Green.Villager.Pos2.X") != null) {
            Main.GreenVillagerRegion = new Region(getVillagerPos1("Green"), getVillagerPos2("Green"));
        }
        if (ArenaCfg.getArena(Main.choosenMap).get("Region.Blue.Villager.Pos1.X") != null || ArenaCfg.getArena(Main.choosenMap).get("Region.Blue.Villager.Pos2.X") != null) {
            Main.BlueVillagerRegion = new Region(getVillagerPos1("Blue"), getVillagerPos2("Blue"));
        }
    }
}
