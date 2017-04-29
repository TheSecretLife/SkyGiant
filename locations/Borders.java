package src.TierCraft.Minigame.SkyGiant.plugin.locations;

import org.bukkit.entity.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.ArenaCfg;

import org.bukkit.configuration.file.*;
import java.io.*;
import org.bukkit.*;

public class Borders
{
    public static void setBorder(final String s, final Player player, final Integer n) {
        final File file = new File("plugins/SkyGiants1058/Arenas/" + player.getWorld().getName() + ".yml");
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(file);
        if (s.equalsIgnoreCase("gold")) {
            loadConfiguration.set("Border.Gold.X", (Object)player.getLocation().getX());
            loadConfiguration.set("Border.Gold.Z", (Object)player.getLocation().getZ());
            loadConfiguration.set("Border.Gold.Size", (Object)n);
            try {
                loadConfiguration.save(file);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            player.sendMessage(Main.PREFIX + "?6Gold ?bborder set!");
        }
        else if (s.equalsIgnoreCase("magenta")) {
            loadConfiguration.set("Border.Magenta.X", (Object)player.getLocation().getX());
            loadConfiguration.set("Border.Magenta.Z", (Object)player.getLocation().getZ());
            loadConfiguration.set("Border.Magenta.Size", (Object)n);
            try {
                loadConfiguration.save(file);
            }
            catch (IOException ex2) {
                ex2.printStackTrace();
            }
            player.sendMessage(Main.PREFIX + "?5Magenta ?bborder set!");
        }
        else if (s.equalsIgnoreCase("green")) {
            loadConfiguration.set("Border.Green.X", (Object)player.getLocation().getX());
            loadConfiguration.set("Border.Green.Z", (Object)player.getLocation().getZ());
            loadConfiguration.set("Border.Green.Size", (Object)n);
            try {
                loadConfiguration.save(file);
            }
            catch (IOException ex3) {
                ex3.printStackTrace();
            }
            player.sendMessage(Main.PREFIX + "?aGreen ?bborder set!");
        }
        else if (s.equalsIgnoreCase("blue")) {
            loadConfiguration.set("Border.Blue.X", (Object)player.getLocation().getX());
            loadConfiguration.set("Border.Blue.Z", (Object)player.getLocation().getZ());
            loadConfiguration.set("Border.Blue.Size", (Object)n);
            try {
                loadConfiguration.save(file);
            }
            catch (IOException ex4) {
                ex4.printStackTrace();
            }
            player.sendMessage(Main.PREFIX + "?9Blue ?bborder set!");
        }
        else {
            player.sendMessage(Main.PREFIX + "?bChoices: &6Gold&b, &5Magenta&b, &aGreen&b, &9Blue&b.");
        }
    }
    
    public static void checkBorders(final Player player) {
        final String name = player.getLocation().getWorld().getName();
        if (ArenaCfg.getArena(name).get("Border.Gold.X") == null && ArenaCfg.getArena(name).get("Border.Magenta.X") == null && ArenaCfg.getArena(name).get("Border.Green.X") != null && ArenaCfg.getArena(name).get("Border.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setBorder ?6\u2588 ?5\u2588 ?b<radius>");
        }
        else if (ArenaCfg.getArena(name).get("Border.Gold.X") != null && ArenaCfg.getArena(name).get("Border.Magenta.X") == null && ArenaCfg.getArena(name).get("Border.Green.X") == null && ArenaCfg.getArena(name).get("Border.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setBorder ?5\u2588 ?a\u2588 ?b<radius>");
        }
        else if (ArenaCfg.getArena(name).get("Border.Gold.X") != null && ArenaCfg.getArena(name).get("Border.Magenta.X") != null && ArenaCfg.getArena(name).get("Border.Green.X") == null && ArenaCfg.getArena(name).get("Border.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setBorder ?a\u2588 ?9\u2588 ?b<radius>");
        }
        else if (ArenaCfg.getArena(name).get("Border.Gold.X") == null && ArenaCfg.getArena(name).get("Border.Magenta.X") != null && ArenaCfg.getArena(name).get("Border.Green.X") != null && ArenaCfg.getArena(name).get("Border.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setBorder ?6\u2588 ?9\u2588 ?b<radius>");
        }
        else if (ArenaCfg.getArena(name).get("Border.Gold.X") != null && ArenaCfg.getArena(name).get("Border.Magenta.X") == null && ArenaCfg.getArena(name).get("Border.Green.X") == null && ArenaCfg.getArena(name).get("Border.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setBorder ?5\u2588 ?a\u2588 ?b<radius>");
        }
        else if (ArenaCfg.getArena(name).get("Border.Gold.X") == null && ArenaCfg.getArena(name).get("Border.Magenta.X") != null && ArenaCfg.getArena(name).get("Border.Green.X") == null && ArenaCfg.getArena(name).get("Border.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setBorder ?6\u2588 ?a\u2588 ?b<radius>");
        }
        else if (ArenaCfg.getArena(name).get("Border.Gold.X") != null && ArenaCfg.getArena(name).get("Border.Magenta.X") == null && ArenaCfg.getArena(name).get("Border.Green.X") != null && ArenaCfg.getArena(name).get("Border.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setBorder ?5\u2588 ?9\u2588 ?b<radius>");
        }
        else if (ArenaCfg.getArena(name).get("Border.Gold.X") == null && ArenaCfg.getArena(name).get("Border.Magenta.X") == null && ArenaCfg.getArena(name).get("Border.Green.X") == null && ArenaCfg.getArena(name).get("Border.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setBorder ?6\u2588 ?5\u2588 ?a\u2588 ?b<radius>");
        }
        else if (ArenaCfg.getArena(name).get("Border.Gold.X") != null && ArenaCfg.getArena(name).get("Border.Magenta.X") == null && ArenaCfg.getArena(name).get("Border.Green.X") == null && ArenaCfg.getArena(name).get("Border.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setBorder ?5\u2588 ?a\u2588 ?9\u2588 ?b<radius>");
        }
        else if (ArenaCfg.getArena(name).get("Border.Gold.X") == null && ArenaCfg.getArena(name).get("Border.Magenta.X") != null && ArenaCfg.getArena(name).get("Border.Green.X") == null && ArenaCfg.getArena(name).get("Border.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setBorder ?6\u2588 ?a\u2588 ?9\u2588 ?b<radius>");
        }
        else if (ArenaCfg.getArena(name).get("Border.Gold.X") == null && ArenaCfg.getArena(name).get("Border.Magenta.X") == null && ArenaCfg.getArena(name).get("Border.Green.X") == null && ArenaCfg.getArena(name).get("Border.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setBorder ?6\u2588 ?a\u2588 ?5\u2588 ?b<radius>");
        }
        else if (ArenaCfg.getArena(name).get("Border.Gold.X") == null && ArenaCfg.getArena(name).get("Border.Magenta.X") != null && ArenaCfg.getArena(name).get("Border.Green.X") != null && ArenaCfg.getArena(name).get("Border.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setBorder ?6\u2588 ?b<radius>");
        }
        else if (ArenaCfg.getArena(name).get("Border.Gold.X") != null && ArenaCfg.getArena(name).get("Border.Magenta.X") == null && ArenaCfg.getArena(name).get("Border.Green.X") != null && ArenaCfg.getArena(name).get("Border.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setBorder ?5\u2588 ?b<radius>");
        }
        else if (ArenaCfg.getArena(name).get("Border.Gold.X") != null && ArenaCfg.getArena(name).get("Border.Magenta.X") != null && ArenaCfg.getArena(name).get("Border.Green.X") == null && ArenaCfg.getArena(name).get("Border.Blue.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setBorder ?a\u2588 ?b<radius>");
        }
        else if (ArenaCfg.getArena(name).get("Border.Gold.X") != null && ArenaCfg.getArena(name).get("Border.Magenta.X") != null && ArenaCfg.getArena(name).get("Border.Green.X") != null && ArenaCfg.getArena(name).get("Border.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setBorder ?9\u2588 ?b<radius>");
        }
        else if (ArenaCfg.getArena(name).get("Border.Gold.X") == null && ArenaCfg.getArena(name).get("Border.Magenta.X") == null && ArenaCfg.getArena(name).get("Border.Green.X") == null && ArenaCfg.getArena(name).get("Border.Blue.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setBorder ?6\u2588 ?5\u2588 ?a\u2588 ?9\u2588 ?b<radius>");
        }
    }
    
    public static Location getBorder1(final String s) {
        return new Location(Bukkit.getWorld(Main.choosenMap), ArenaCfg.getArena(Main.choosenMap).getDouble("Border." + s + ".X"), ArenaCfg.getArena(Main.choosenMap).getDouble("Border." + s + ".Y"), ArenaCfg.getArena(Main.choosenMap).getDouble("Border." + s + ".Z"), (float)ArenaCfg.getArena(Main.choosenMap).get("Border." + s + ".Yaw"), (float)ArenaCfg.getArena(Main.choosenMap).get("Border." + s + ".Pitch"));
    }
}
