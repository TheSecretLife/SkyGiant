package src.TierCraft.Minigame.SkyGiant.plugin.locations;

import org.bukkit.entity.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.ArenaCfg;

import org.bukkit.configuration.file.*;
import java.io.*;
import org.bukkit.*;

public class Beasts
{
    public static void setBeast(final Player player) {
        final File file = new File("plugins/SkyGiants1058/Arenas/" + player.getWorld().getName() + ".yml");
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(file);
        loadConfiguration.set("Beast.X", (Object)player.getLocation().getX());
        loadConfiguration.set("Beast.Y", (Object)player.getLocation().getY());
        loadConfiguration.set("Beast.Z", (Object)player.getLocation().getZ());
        loadConfiguration.set("Region.Beast.Pos1.X", (Object)(player.getLocation().getX() - 7.0));
        loadConfiguration.set("Region.Beast.Pos1.Y", (Object)(player.getLocation().getY() - 10.0));
        loadConfiguration.set("Region.Beast.Pos1.Z", (Object)(player.getLocation().getZ() - 7.0));
        loadConfiguration.set("Region.Beast.Pos2.X", (Object)(player.getLocation().getX() + 7.0));
        loadConfiguration.set("Region.Beast.Pos2.Y", (Object)(player.getLocation().getY() + 10.0));
        loadConfiguration.set("Region.Beast.Pos2.Z", (Object)(player.getLocation().getZ() + 7.0));
        try {
            loadConfiguration.save(file);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        player.sendMessage(Main.PREFIX + "?bBeast set!");
    }
    
    public static Location getBeastSpawn() {
        return new Location(Bukkit.getWorld(Main.choosenMap), ArenaCfg.getArena(Main.choosenMap).getDouble("Beast.X"), ArenaCfg.getArena(Main.choosenMap).getDouble("Beast.Y"), ArenaCfg.getArena(Main.choosenMap).getDouble("Beast.Z"));
    }
    
    private static Location getBeastPos1() {
        return new Location(Bukkit.getWorld(Main.choosenMap), ArenaCfg.getArena(Main.choosenMap).getDouble("Region.Beast.Pos1.X"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region.Beast.Pos1.Y"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region.Beast.Pos1.Z"));
    }
    
    private static Location getBeastPos2() {
        return new Location(Bukkit.getWorld(Main.choosenMap), ArenaCfg.getArena(Main.choosenMap).getDouble("Region.Beast.Pos2.X"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region.Beast.Pos2.Y"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region.Beast.Pos2.Z"));
    }
    
    public static void loadBeastRegion() {
        if (ArenaCfg.getArena(Main.choosenMap).get("Region.Beast.Pos1.X") != null || ArenaCfg.getArena(Main.choosenMap).get("Region.Beast.Pos2.X") != null) {
            Main.BeastRegion = new Region(getBeastPos1(), getBeastPos2());
        }
    }
}
