package src.TierCraft.Minigame.SkyGiant.plugin.locations;

import org.bukkit.entity.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;

import org.bukkit.configuration.file.*;
import java.io.*;
import org.bukkit.*;

public class Spectate
{
    public static void setSpect(final Player player) {
        final File file = new File("plugins/SkyGiants1058/Arenas/" + player.getLocation().getWorld().getName() + ".yml");
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(file);
        loadConfiguration.set("Spectate.X", (Object)player.getLocation().getX());
        loadConfiguration.set("Spectate.Y", (Object)player.getLocation().getY());
        loadConfiguration.set("Spectate.Z", (Object)player.getLocation().getZ());
        loadConfiguration.set("Spectate.Pitch", (Object)player.getLocation().getPitch());
        loadConfiguration.set("Spectate.Yaw", (Object)player.getLocation().getYaw());
        try {
            loadConfiguration.save(file);
            player.sendMessage(Main.PREFIX + "?aArena's spectate location set!");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static Location getSpect(final String s) {
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(new File("plugins/SkyGiants1058/Arenas/" + s + ".yml"));
        return new Location(Bukkit.getWorld(s), loadConfiguration.getDouble("Spectate.X"), loadConfiguration.getDouble("Spectate.Y"), loadConfiguration.getDouble("Spectate.Z"), (float)loadConfiguration.getDouble("Spectate.Yaw"), (float)loadConfiguration.getDouble("Spectate.Pitch"));
    }
}
