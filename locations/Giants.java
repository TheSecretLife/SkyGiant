package src.TierCraft.Minigame.SkyGiant.plugin.locations;

import org.bukkit.entity.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.ArenaCfg;

import org.bukkit.configuration.file.*;
import java.io.*;
import org.bukkit.*;

public class Giants
{
    public static void setGiant(final String s, final Player player) {
        final File file = new File("plugins/SkyGiants1058/Arenas/" + player.getWorld().getName() + ".yml");
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(file);
        if (s.equalsIgnoreCase("gold")) {
            loadConfiguration.set("Giant.Gold.X", (Object)player.getLocation().getX());
            loadConfiguration.set("Giant.Gold.Y", (Object)player.getLocation().getY());
            loadConfiguration.set("Giant.Gold.Z", (Object)player.getLocation().getZ());
            loadConfiguration.set("Giant.Gold.Yaw", (Object)player.getLocation().getYaw());
            loadConfiguration.set("Giant.Gold.Pitch", (Object)player.getLocation().getPitch());
            loadConfiguration.set("Region.Gold.Giant.Pos1.X", (Object)(player.getLocation().getX() - 4.0));
            loadConfiguration.set("Region.Gold.Giant.Pos1.Y", (Object)(player.getLocation().getY() - 4.0));
            loadConfiguration.set("Region.Gold.Giant.Pos1.Z", (Object)(player.getLocation().getZ() - 4.0));
            loadConfiguration.set("Region.Gold.Giant.Pos2.X", (Object)(player.getLocation().getX() + 4.0));
            loadConfiguration.set("Region.Gold.Giant.Pos2.Y", (Object)(player.getLocation().getY() + 4.0));
            loadConfiguration.set("Region.Gold.Giant.Pos2.Z", (Object)(player.getLocation().getZ() + 4.0));
            try {
                loadConfiguration.save(file);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            player.sendMessage(Main.PREFIX + "?6Gold ?bGiant set!");
        }
        else if (s.equalsIgnoreCase("magenta")) {
            loadConfiguration.set("Giant.Magenta.X", (Object)player.getLocation().getX());
            loadConfiguration.set("Giant.Magenta.Y", (Object)player.getLocation().getY());
            loadConfiguration.set("Giant.Magenta.Z", (Object)player.getLocation().getZ());
            loadConfiguration.set("Giant.Magenta.Yaw", (Object)player.getLocation().getYaw());
            loadConfiguration.set("Giant.Magenta.Pitch", (Object)player.getLocation().getPitch());
            loadConfiguration.set("Region.Magenta.Giant.Pos1.X", (Object)(player.getLocation().getX() - 4.0));
            loadConfiguration.set("Region.Magenta.Giant.Pos1.Y", (Object)(player.getLocation().getY() - 4.0));
            loadConfiguration.set("Region.Magenta.Giant.Pos1.Z", (Object)(player.getLocation().getZ() - 4.0));
            loadConfiguration.set("Region.Magenta.Giant.Pos2.X", (Object)(player.getLocation().getX() + 4.0));
            loadConfiguration.set("Region.Magenta.Giant.Pos2.Y", (Object)(player.getLocation().getY() + 4.0));
            loadConfiguration.set("Region.Magenta.Giant.Pos2.Z", (Object)(player.getLocation().getZ() + 4.0));
            try {
                loadConfiguration.save(file);
            }
            catch (IOException ex2) {
                ex2.printStackTrace();
            }
            player.sendMessage(Main.PREFIX + "?5Magenta ?bGiant set!");
        }
        else if (s.equalsIgnoreCase("green")) {
            loadConfiguration.set("Giant.Green.X", (Object)player.getLocation().getX());
            loadConfiguration.set("Giant.Green.Y", (Object)player.getLocation().getY());
            loadConfiguration.set("Giant.Green.Z", (Object)player.getLocation().getZ());
            loadConfiguration.set("Giant.Green.Yaw", (Object)player.getLocation().getYaw());
            loadConfiguration.set("Giant.Green.Pitch", (Object)player.getLocation().getPitch());
            loadConfiguration.set("Region.Green.Giant.Pos1.X", (Object)(player.getLocation().getX() - 4.0));
            loadConfiguration.set("Region.Green.Giant.Pos1.Y", (Object)(player.getLocation().getY() - 4.0));
            loadConfiguration.set("Region.Green.Giant.Pos1.Z", (Object)(player.getLocation().getZ() - 4.0));
            loadConfiguration.set("Region.Green.Giant.Pos2.X", (Object)(player.getLocation().getX() + 4.0));
            loadConfiguration.set("Region.Green.Giant.Pos2.Y", (Object)(player.getLocation().getY() + 4.0));
            loadConfiguration.set("Region.Green.Giant.Pos2.Z", (Object)(player.getLocation().getZ() + 4.0));
            try {
                loadConfiguration.save(file);
            }
            catch (IOException ex3) {
                ex3.printStackTrace();
            }
            player.sendMessage(Main.PREFIX + "?aGreen ?bGiant set!");
        }
        else if (s.equalsIgnoreCase("blue")) {
            loadConfiguration.set("Giant.Blue.X", (Object)player.getLocation().getX());
            loadConfiguration.set("Giant.Blue.Y", (Object)player.getLocation().getY());
            loadConfiguration.set("Giant.Blue.Z", (Object)player.getLocation().getZ());
            loadConfiguration.set("Giant.Blue.Yaw", (Object)player.getLocation().getYaw());
            loadConfiguration.set("Giant.Blue.Pitch", (Object)player.getLocation().getPitch());
            loadConfiguration.set("Region.Blue.Giant.Pos1.X", (Object)(player.getLocation().getX() - 4.0));
            loadConfiguration.set("Region.Blue.Giant.Pos1.Y", (Object)(player.getLocation().getY() - 4.0));
            loadConfiguration.set("Region.Blue.Giant.Pos1.Z", (Object)(player.getLocation().getZ() - 4.0));
            loadConfiguration.set("Region.Blue.Giant.Pos2.X", (Object)(player.getLocation().getX() + 4.0));
            loadConfiguration.set("Region.Blue.Giant.Pos2.Y", (Object)(player.getLocation().getY() + 4.0));
            loadConfiguration.set("Region.Blue.Giant.Pos2.Z", (Object)(player.getLocation().getZ() + 4.0));
            try {
                loadConfiguration.save(file);
            }
            catch (IOException ex4) {
                ex4.printStackTrace();
            }
            player.sendMessage(Main.PREFIX + "?9Blue ?bGiant set!");
        }
        else {
            player.sendMessage(Main.PREFIX + "?bChoices: &6Gold&b, &5Magenta&b, &aGreen&b, &9Blue&b.");
        }
    }
    
    public static void checkGiants(final Player player) {
        final String name = player.getLocation().getWorld().getName();
        if (ArenaCfg.getArena(name).get("Giant.Gold.X") == null && ArenaCfg.getArena(name).get("Giant.Magenta.X") == null && ArenaCfg.getArena(name).get("Giant.Green.X") != null && ArenaCfg.getArena(name).get("Giant.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setGiant ?6\u2588 ?5\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Giant.Gold.X") != null && ArenaCfg.getArena(name).get("Giant.Magenta.X") == null && ArenaCfg.getArena(name).get("Giant.Green.X") == null && ArenaCfg.getArena(name).get("Giant.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setGiant ?5\u2588 ?a\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Giant.Gold.X") != null && ArenaCfg.getArena(name).get("Giant.Magenta.X") != null && ArenaCfg.getArena(name).get("Giant.Green.X") == null && ArenaCfg.getArena(name).get("Giant.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setGiant ?a\u2588 ?9\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Giant.Gold.X") == null && ArenaCfg.getArena(name).get("Giant.Magenta.X") != null && ArenaCfg.getArena(name).get("Giant.Green.X") != null && ArenaCfg.getArena(name).get("Giant.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setGiant ?6\u2588 ?9\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Giant.Gold.X") != null && ArenaCfg.getArena(name).get("Giant.Magenta.X") == null && ArenaCfg.getArena(name).get("Giant.Green.X") == null && ArenaCfg.getArena(name).get("Giant.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setGiant ?5\u2588 ?a\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Giant.Gold.X") == null && ArenaCfg.getArena(name).get("Giant.Magenta.X") != null && ArenaCfg.getArena(name).get("Giant.Green.X") == null && ArenaCfg.getArena(name).get("Giant.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setGiant ?6\u2588 ?a\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Giant.Gold.X") != null && ArenaCfg.getArena(name).get("Giant.Magenta.X") == null && ArenaCfg.getArena(name).get("Giant.Green.X") != null && ArenaCfg.getArena(name).get("Giant.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setGiant ?5\u2588 ?9\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Giant.Gold.X") == null && ArenaCfg.getArena(name).get("Giant.Magenta.X") == null && ArenaCfg.getArena(name).get("Giant.Green.X") == null && ArenaCfg.getArena(name).get("Giant.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setGiant ?6\u2588 ?5\u2588 ?a\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Giant.Gold.X") != null && ArenaCfg.getArena(name).get("Giant.Magenta.X") == null && ArenaCfg.getArena(name).get("Giant.Green.X") == null && ArenaCfg.getArena(name).get("Giant.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setGiant ?5\u2588 ?a\u2588 ?9\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Giant.Gold.X") == null && ArenaCfg.getArena(name).get("Giant.Magenta.X") != null && ArenaCfg.getArena(name).get("Giant.Green.X") == null && ArenaCfg.getArena(name).get("Giant.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setGiant ?6\u2588 ?a\u2588 ?9\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Giant.Gold.X") == null && ArenaCfg.getArena(name).get("Giant.Magenta.X") == null && ArenaCfg.getArena(name).get("Giant.Green.X") == null && ArenaCfg.getArena(name).get("Giant.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setGiant ?6\u2588 ?a\u2588 ?5\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Giant.Gold.X") == null && ArenaCfg.getArena(name).get("Giant.Magenta.X") != null && ArenaCfg.getArena(name).get("Giant.Green.X") != null && ArenaCfg.getArena(name).get("Giant.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setGiant ?6\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Giant.Gold.X") != null && ArenaCfg.getArena(name).get("Giant.Magenta.X") == null && ArenaCfg.getArena(name).get("Giant.Green.X") != null && ArenaCfg.getArena(name).get("Giant.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setGiant ?5\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Giant.Gold.X") != null && ArenaCfg.getArena(name).get("Giant.Magenta.X") != null && ArenaCfg.getArena(name).get("Giant.Green.X") == null && ArenaCfg.getArena(name).get("Giant.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setGiant ?a\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Giant.Gold.X") != null && ArenaCfg.getArena(name).get("Giant.Magenta.X") != null && ArenaCfg.getArena(name).get("Giant.Green.X") != null && ArenaCfg.getArena(name).get("Giant.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setGiant ?9\u2588");
        }
        else if (ArenaCfg.getArena(name).get("Giant.Gold.X") == null && ArenaCfg.getArena(name).get("Giant.Magenta.X") == null && ArenaCfg.getArena(name).get("Giant.Green.X") == null && ArenaCfg.getArena(name).get("Giant.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setGiant ?6\u2588 ?5\u2588 ?a\u2588 ?9\u2588");
        }
    }
    
    public static Location getGiant(final String s) {
        return new Location(Bukkit.getWorld(Main.choosenMap), ArenaCfg.getArena(Main.choosenMap).getDouble("Giant." + s + ".X"), ArenaCfg.getArena(Main.choosenMap).getDouble("Giant." + s + ".Y"), ArenaCfg.getArena(Main.choosenMap).getDouble("Giant." + s + ".Z"), (float)ArenaCfg.getArena(Main.choosenMap).getDouble("Giant." + s + ".Yaw"), (float)ArenaCfg.getArena(Main.choosenMap).getDouble("Giant." + s + ".Pitch"));
    }
    
    private static Location getGiantPos1(final String s) {
        return new Location(Bukkit.getWorld(Main.choosenMap), ArenaCfg.getArena(Main.choosenMap).getDouble("Region." + s + ".Giant.Pos1.X"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region." + s + ".Giant.Pos1.Y"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region." + s + ".Giant.Pos1.Z"));
    }
    
    private static Location getGiantPos2(final String s) {
        return new Location(Bukkit.getWorld(Main.choosenMap), ArenaCfg.getArena(Main.choosenMap).getDouble("Region." + s + ".Giant.Pos2.X"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region." + s + ".Giant.Pos2.Y"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region." + s + ".Giant.Pos2.Z"));
    }
    
    public static void loadGiantsRegions() {
        if (ArenaCfg.getArena(Main.choosenMap).get("Region.Gold.Giant.Pos1.X") != null || ArenaCfg.getArena(Main.choosenMap).get("Region.Gold.Giant.Pos2.X") != null) {
            Main.GoldGiantRegion = new Region(getGiantPos1("Gold"), getGiantPos2("Gold"));
        }
        if (ArenaCfg.getArena(Main.choosenMap).get("Region.Magenta.Giant.Pos1.X") != null || ArenaCfg.getArena(Main.choosenMap).get("Region.Magenta.Giant.Pos2.X") != null) {
            Main.MagentaGiantRegion = new Region(getGiantPos1("Magenta"), getGiantPos2("Magenta"));
        }
        if (ArenaCfg.getArena(Main.choosenMap).get("Region.Green.Giant.Pos1.X") != null || ArenaCfg.getArena(Main.choosenMap).get("Region.Green.Giant.Pos2.X") != null) {
            Main.GreenGiantRegion = new Region(getGiantPos1("Green"), getGiantPos2("Green"));
        }
        if (ArenaCfg.getArena(Main.choosenMap).get("Region.Blue.Giant.Pos1.X") != null || ArenaCfg.getArena(Main.choosenMap).get("Region.Blue.Giant.Pos2.X") != null) {
            Main.BlueGiantRegion = new Region(getGiantPos1("Blue"), getGiantPos2("Blue"));
        }
    }
}
