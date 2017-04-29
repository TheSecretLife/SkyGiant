package src.TierCraft.Minigame.SkyGiant.plugin.locations;

import org.bukkit.entity.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.ArenaCfg;

import org.bukkit.configuration.file.*;
import java.io.*;
import org.bukkit.*;

public class Spawns
{
    public static void setSpawn(final String s, final Player player) {
        final File file = new File("plugins/SkyGiants1058/Arenas/" + player.getWorld().getName() + ".yml");
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(file);
        if (s.equalsIgnoreCase("gold")) {
            loadConfiguration.set("Spawn.Gold.X", (Object)player.getLocation().getX());
            loadConfiguration.set("Spawn.Gold.Y", (Object)player.getLocation().getY());
            loadConfiguration.set("Spawn.Gold.Z", (Object)player.getLocation().getZ());
            loadConfiguration.set("Spawn.Gold.Yaw", (Object)player.getLocation().getYaw());
            loadConfiguration.set("Spawn.Gold.Pitch", (Object)player.getLocation().getPitch());
            try {
                loadConfiguration.save(file);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            player.sendMessage(Main.PREFIX + "?6Gold ?bspawn set!");
        }
        else if (s.equalsIgnoreCase("magenta")) {
            loadConfiguration.set("Spawn.Magenta.X", (Object)player.getLocation().getX());
            loadConfiguration.set("Spawn.Magenta.Y", (Object)player.getLocation().getY());
            loadConfiguration.set("Spawn.Magenta.Z", (Object)player.getLocation().getZ());
            loadConfiguration.set("Spawn.Magenta.Yaw", (Object)player.getLocation().getYaw());
            loadConfiguration.set("Spawn.Magenta.Pitch", (Object)player.getLocation().getPitch());
            try {
                loadConfiguration.save(file);
            }
            catch (IOException ex2) {
                ex2.printStackTrace();
            }
            player.sendMessage(Main.PREFIX + "?5Magenta ?bspawn set!");
        }
        else if (s.equalsIgnoreCase("green")) {
            loadConfiguration.set("Spawn.Green.X", (Object)player.getLocation().getX());
            loadConfiguration.set("Spawn.Green.Y", (Object)player.getLocation().getY());
            loadConfiguration.set("Spawn.Green.Z", (Object)player.getLocation().getZ());
            loadConfiguration.set("Spawn.Green.Yaw", (Object)player.getLocation().getYaw());
            loadConfiguration.set("Spawn.Green.Pitch", (Object)player.getLocation().getPitch());
            try {
                loadConfiguration.save(file);
            }
            catch (IOException ex3) {
                ex3.printStackTrace();
            }
            player.sendMessage(Main.PREFIX + "?aGreen ?bspawn set!");
        }
        else if (s.equalsIgnoreCase("blue")) {
            loadConfiguration.set("Spawn.Blue.X", (Object)player.getLocation().getX());
            loadConfiguration.set("Spawn.Blue.Y", (Object)player.getLocation().getY());
            loadConfiguration.set("Spawn.Blue.Z", (Object)player.getLocation().getZ());
            loadConfiguration.set("Spawn.Blue.Yaw", (Object)player.getLocation().getYaw());
            loadConfiguration.set("Spawn.Blue.Pitch", (Object)player.getLocation().getPitch());
            try {
                loadConfiguration.save(file);
            }
            catch (IOException ex4) {
                ex4.printStackTrace();
            }
            player.sendMessage(Main.PREFIX + "?9Blue ?bspawn set!");
        }
        else {
            player.sendMessage(Main.PREFIX + "?bChoices: &6Gold&b, &5Magenta&b, &aGreen&b, &9Blue&b.");
        }
    }
    
    public static void checkSpawns(final Player player) {
        final String name = player.getLocation().getWorld().getName();
        if (ArenaCfg.getArena(name).get("Spawn.Gold.X") == null && ArenaCfg.getArena(name).get("Spawn.Magenta.X") == null && ArenaCfg.getArena(name).get("Spawn.Green.X") != null && ArenaCfg.getArena(name).get("Spawn.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setSpawn ?6\u2588 ?5\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Spawn.Gold.X") != null && ArenaCfg.getArena(name).get("Spawn.Magenta.X") == null && ArenaCfg.getArena(name).get("Spawn.Green.X") == null && ArenaCfg.getArena(name).get("Spawn.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setSpawn ?5\u2588 ?a\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Spawn.Gold.X") != null && ArenaCfg.getArena(name).get("Spawn.Magenta.X") != null && ArenaCfg.getArena(name).get("Spawn.Green.X") == null && ArenaCfg.getArena(name).get("Spawn.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setSpawn ?a\u2588 ?9\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Spawn.Gold.X") == null && ArenaCfg.getArena(name).get("Spawn.Magenta.X") != null && ArenaCfg.getArena(name).get("Spawn.Green.X") != null && ArenaCfg.getArena(name).get("Spawn.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setSpawn ?6\u2588 ?9\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Spawn.Gold.X") != null && ArenaCfg.getArena(name).get("Spawn.Magenta.X") == null && ArenaCfg.getArena(name).get("Spawn.Green.X") == null && ArenaCfg.getArena(name).get("Spawn.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setSpawn ?5\u2588 ?a\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Spawn.Gold.X") == null && ArenaCfg.getArena(name).get("Spawn.Magenta.X") != null && ArenaCfg.getArena(name).get("Spawn.Green.X") == null && ArenaCfg.getArena(name).get("Spawn.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setSpawn ?6\u2588 ?a\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Spawn.Gold.X") != null && ArenaCfg.getArena(name).get("Spawn.Magenta.X") == null && ArenaCfg.getArena(name).get("Spawn.Green.X") != null && ArenaCfg.getArena(name).get("Spawn.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setSpawn ?5\u2588 ?9\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Spawn.Gold.X") == null && ArenaCfg.getArena(name).get("Spawn.Magenta.X") == null && ArenaCfg.getArena(name).get("Spawn.Green.X") == null && ArenaCfg.getArena(name).get("Spawn.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setSpawn ?6\u2588 ?5\u2588 ?a\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Spawn.Gold.X") != null && ArenaCfg.getArena(name).get("Spawn.Magenta.X") == null && ArenaCfg.getArena(name).get("Spawn.Green.X") == null && ArenaCfg.getArena(name).get("Spawn.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setSpawn ?5\u2588 ?a\u2588 ?9\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Spawn.Gold.X") == null && ArenaCfg.getArena(name).get("Spawn.Magenta.X") != null && ArenaCfg.getArena(name).get("Spawn.Green.X") == null && ArenaCfg.getArena(name).get("Spawn.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setSpawn ?6\u2588 ?a\u2588 ?9\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Spawn.Gold.X") == null && ArenaCfg.getArena(name).get("Spawn.Magenta.X") == null && ArenaCfg.getArena(name).get("Spawn.Green.X") == null && ArenaCfg.getArena(name).get("Spawn.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setSpawn ?6\u2588 ?a\u2588 ?5\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Spawn.Gold.X") == null && ArenaCfg.getArena(name).get("Spawn.Magenta.X") != null && ArenaCfg.getArena(name).get("Spawn.Green.X") != null && ArenaCfg.getArena(name).get("Spawn.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setSpawn ?6\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Spawn.Gold.X") != null && ArenaCfg.getArena(name).get("Spawn.Magenta.X") == null && ArenaCfg.getArena(name).get("Spawn.Green.X") != null && ArenaCfg.getArena(name).get("Spawn.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setSpawn ?5\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Spawn.Gold.X") != null && ArenaCfg.getArena(name).get("Spawn.Magenta.X") != null && ArenaCfg.getArena(name).get("Spawn.Green.X") == null && ArenaCfg.getArena(name).get("Spawn.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setSpawn ?a\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Spawn.Gold.X") != null && ArenaCfg.getArena(name).get("Spawn.Magenta.X") != null && ArenaCfg.getArena(name).get("Spawn.Green.X") != null && ArenaCfg.getArena(name).get("Spawn.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setSpawn ?9\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Spawn.Gold.X") == null && ArenaCfg.getArena(name).get("Spawn.Magenta.X") == null && ArenaCfg.getArena(name).get("Spawn.Green.X") == null && ArenaCfg.getArena(name).get("Spawn.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setSpawn ?6\u2588 ?5\u2588 ?a\u2588 ?9\u2588");
        }
    }
    
    public static Location getSpawn(final String s) {
        return new Location(Bukkit.getWorld(Main.choosenMap), ArenaCfg.getArena(Main.choosenMap).getDouble("Spawn." + s + ".X"), ArenaCfg.getArena(Main.choosenMap).getDouble("Spawn." + s + ".Y"), ArenaCfg.getArena(Main.choosenMap).getDouble("Spawn." + s + ".Z"), (float)ArenaCfg.getArena(Main.choosenMap).getDouble("Spawn." + s + ".Yaw"), (float)ArenaCfg.getArena(Main.choosenMap).getDouble("Spawn." + s + ".Pitch"));
    }
}
