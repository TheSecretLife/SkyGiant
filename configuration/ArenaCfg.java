package src.TierCraft.Minigame.SkyGiant.plugin.configuration;

import org.bukkit.entity.*;
import java.io.*;
import org.bukkit.configuration.file.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.plugin.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;

public class ArenaCfg
{
    public static void CreateArenaCfg(final String s, final Player player) {
        final File file = new File("plugins/SkyGiants1058/Arenas/" + s + ".yml");
        YamlConfiguration.loadConfiguration(file);
        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static FileConfiguration getArena(final String s) {
        return (FileConfiguration)YamlConfiguration.loadConfiguration(new File("plugins/SkyGiants1058/Arenas/" + s + ".yml"));
    }
    
    public static void registerArenas() {
        if (Settings.getCfg().get("Arenas") == null) {
            return;
        }
        final Iterator<String> iterator = Settings.getCfg().getStringList("Arenas").iterator();
        while (iterator.hasNext()) {
            Main.mapsVoting.put(iterator.next(), 0);
        }
    }
    
    public static void listArenaVotes(final Player player) {
        Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
            @Override
            public void run() {
                player.sendMessage(Main.PREFIX + Messages.getMsg().getString("vote-map").replace('&', '?'));
                player.sendMessage(Main.PREFIX + Messages.getMsg().getString("vote-map2").replace('&', '?'));
                int n = 0;
                for (final String s : Settings.getCfg().getStringList("Arenas")) {
                    player.sendMessage(Main.PREFIX + Messages.getMsg().getString("map-choices").replace("{number}", String.valueOf(++n)).replace("{map}", s).replace("{votes}", String.valueOf(Main.mapsVoting.get(s))).replace('&', '?'));
                }
            }
        }, 2L);
    }
}
