package src.TierCraft.Minigame.SkyGiant.plugin.locations;

import org.bukkit.entity.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.ArenaCfg;

import org.bukkit.configuration.file.*;
import java.io.*;
import org.bukkit.*;

public class Middle
{
    public static void setMidPos1(final Player player) {
        final File file = new File("plugins/SkyGiants1058/Arenas/" + player.getWorld().getName() + ".yml");
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(file);
        loadConfiguration.set("Region.Middle.Pos1.X", (Object)player.getLocation().getX());
        loadConfiguration.set("Region.Middle.Pos1.Y", (Object)player.getLocation().getY());
        loadConfiguration.set("Region.Middle.Pos1.Z", (Object)player.getLocation().getZ());
        try {
            loadConfiguration.save(file);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        player.sendMessage(Main.PREFIX + "?bPos1 set!");
    }
    
    public static void setMidPos2(final Player player) {
        final File file = new File("plugins/SkyGiants1058/Arenas/" + player.getWorld().getName() + ".yml");
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(file);
        loadConfiguration.set("Region.Middle.Pos2.X", (Object)player.getLocation().getX());
        loadConfiguration.set("Region.Middle.Pos2.Y", (Object)player.getLocation().getY());
        loadConfiguration.set("Region.Middle.Pos2.Z", (Object)player.getLocation().getZ());
        try {
            loadConfiguration.save(file);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        player.sendMessage(Main.PREFIX + "?bPos2 set!");
    }
    
    private static Location getMidPos1() {
        return new Location(Bukkit.getWorld(Main.choosenMap), ArenaCfg.getArena(Main.choosenMap).getDouble("Region.Middle.Pos1.X"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region.Middle.Pos1.Y"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region.Middle.Pos1.Z"));
    }
    
    private static Location getMidPos2() {
        return new Location(Bukkit.getWorld(Main.choosenMap), ArenaCfg.getArena(Main.choosenMap).getDouble("Region.Middle.Pos2.X"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region.Middle.Pos2.Y"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region.Middle.Pos2.Z"));
    }
    
    public static void loadMidRegion() {
        if (ArenaCfg.getArena(Main.choosenMap).get("Region.Middle.Pos1.X") != null || ArenaCfg.getArena(Main.choosenMap).get("Region.Middle.Pos2.X") != null) {
            Main.MiddleRegion = new Region(getMidPos1(), getMidPos2());
        }
    }
}
