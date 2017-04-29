package src.TierCraft.Minigame.SkyGiant.plugin.configuration;

import java.io.*;
import org.bukkit.plugin.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.game.GameState;
import src.TierCraft.Minigame.SkyGiant.plugin.game.Scoreboard;

import org.bukkit.configuration.file.*;
import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.command.*;

public class Settings
{
    private static File file;
    private static YamlConfiguration yml;
    
    public static void setupConfig() {
        if (!Settings.file.exists()) {
            Settings.file.mkdir();
        }
        Settings.yml.options().header("SkyGiants Plugin by andrei1058");
        Settings.yml.addDefault("setup", (Object)true);
        Settings.yml.addDefault("language", (Object)"en");
        Settings.yml.addDefault("skygiants-mini", (Object)true);
        Settings.yml.addDefault("allow-spectate", (Object)true);
        Settings.yml.addDefault("countdowns.lobby.empty", (Object)300);
        Settings.yml.addDefault("countdowns.lobby.half", (Object)60);
        Settings.yml.addDefault("countdowns.lobby.full", (Object)25);
        Settings.yml.addDefault("countdowns.game", (Object)1800);
        Settings.yml.addDefault("countdowns.warmup", (Object)90);
        Settings.yml.addDefault("countdowns.restart", (Object)12);
        Settings.yml.addDefault("gamemode-spectator", (Object)false);
        Settings.yml.addDefault("lobby-server", (Object)"skygiants");
        Settings.yml.addDefault("restart-cmd", (Object)"restart");
        Settings.yml.addDefault("Database.enable", (Object)false);
        Settings.yml.addDefault("Database.host", (Object)"localhost");
        Settings.yml.addDefault("Database.port", (Object)3306);
        Settings.yml.addDefault("Database.database", (Object)"SkyGiants");
        Settings.yml.addDefault("Database.username", (Object)"root");
        Settings.yml.addDefault("Database.password", (Object)"pass");
        Settings.yml.addDefault("Database.scoreboard", (Object)true);
        Settings.yml.addDefault("reward-command.use", (Object)false);
        Settings.yml.addDefault("reward-command.cmd", (Object)"this command will be executed as console, for each winner, use %player% for payer name");
        Settings.yml.options().copyDefaults(true);
        try {
            Settings.yml.save(Settings.file);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
            @Override
            public void run() {
                if (!new File("plugins/SkyGiants1058/messages_" + Settings.getCfg().getString("language") + ".yml").exists()) {
                    Main.Language = "en";
                }
                else {
                    Main.Language = Settings.getCfg().getString("language");
                }
                if (Settings.getCfg().getBoolean("setup")) {
                    Main.MAINTENANCE = true;
                }
                else {
                    Main.MAINTENANCE = false;
                    Main.STATUS = GameState.LOBBY;
                }
                if (!Settings.getCfg().getBoolean("skygiants-mini")) {
                    if (Messages.getMsg().get("prefix") != null) {
                        Main.PREFIX = Messages.getMsg().getString("prefix");
                    }
                    else {
                        Main.PREFIX = "?8\u258e ?3?lSky?b?lGiants?8 \u258f ";
                    }
                    Main.miniSG = false;
                    Main.sbtitle = Messages.getMsg().getString("scoreboard.title1").replace('&', '§');
                    Main.BeastHealth = 350;
                }
                else {
                    Main.miniSG = true;
                    if (Messages.getMsg().get("mini-prefix") != null) {
                        Main.PREFIX = Messages.getMsg().getString("mini-prefix");
                    }
                    else {
                        Main.PREFIX = "?8\u258e ?3?lSky?b?lGiants?a?l:Mini?8 \u258f ";
                    }
                    Main.MaxInTeam = 2;
                    Main.GiantHealth = 510;
                    Main.sbtitle = Messages.getMsg().getString("scoreboard.title2").replace('&', '§');
                    Main.BeastHealth = 210;
                }
                if (Settings.getCfg().get("Database.enable") != null) {
                    Main.DatabaseB = Settings.getCfg().getBoolean("Database.enable");
                }
                if (Settings.getCfg().get("MainLobby") != null) {
                    for (final Entity entity : Bukkit.getWorld(Settings.getCfg().getString("MainLobby.World")).getEntities()) {
                        if (entity.getType() != EntityType.PLAYER) {
                            entity.remove();
                        }
                    }
                }
                if (Settings.getCfg().get("allow-spectate") != null) {
                    Main.allowSpectate = Settings.getCfg().getBoolean("allow-spectate");
                }
                Scoreboard.goldEntry = Messages.getMsg().getString("scoreboard.gold").replace('&', '§');
                Scoreboard.magentaEntry = Messages.getMsg().getString("scoreboard.magenta").replace('&', '§');
                Scoreboard.blueEntry = Messages.getMsg().getString("scoreboard.blue").replace('&', '§');
                Scoreboard.greenEntry = Messages.getMsg().getString("scoreboard.green").replace('&', '§');
                Main.LobbyCountdown = Settings.getCfg().getInt("countdowns.lobby.empty");
                Main.GameCountdown = Settings.getCfg().getInt("countdowns.game");
                Main.WarmupCountdown = Settings.getCfg().getInt("countdowns.warmup");
                Main.RestartCountdown = Settings.getCfg().getInt("countdowns.restart");
                Main.loaded = true;
            }
        }, 5L);
    }
    
    public static FileConfiguration getCfg() {
        return (FileConfiguration)YamlConfiguration.loadConfiguration(Settings.file);
    }
    
    public static File getCfgFile() {
        return Settings.file;
    }
    
    public static void addArenaToList(final Player player) {
        if (getCfg().get("Arenas") != null) {
            final List<String> stringList = getCfg().getStringList("Arenas");
            stringList.add(player.getLocation().getWorld().getName());
            Settings.yml.set("Arenas", (Object)stringList);
            try {
                Settings.yml.save(Settings.file);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else {
            final ArrayList<String> list = new ArrayList<String>();
            list.add(player.getLocation().getWorld().getName());
            Settings.yml.set("Arenas", (Object)list);
            try {
                Settings.yml.save(Settings.file);
            }
            catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
    }
    
    public static void setMainLobby(final Player player) {
        Settings.yml.set("MainLobby.World", (Object)player.getLocation().getWorld().getName());
        Settings.yml.set("MainLobby.X", (Object)player.getLocation().getX());
        Settings.yml.set("MainLobby.Y", (Object)player.getLocation().getY());
        Settings.yml.set("MainLobby.Z", (Object)player.getLocation().getZ());
        Settings.yml.set("MainLobby.Yaw", (Object)player.getLocation().getYaw());
        Settings.yml.set("MainLobby.Pitch", (Object)player.getLocation().getPitch());
        player.sendMessage(Main.PREFIX + "?bMain lobby set!");
        try {
            Settings.yml.save(Settings.file);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static Location getMainLobby() {
        return new Location(Bukkit.getWorld(getCfg().getString("MainLobby.World")), getCfg().getDouble("MainLobby.X"), getCfg().getDouble("MainLobby.Y"), getCfg().getDouble("MainLobby.Z"), (float)getCfg().getDouble("MainLobby.Yaw"), (float)getCfg().getDouble("MainLobby.Pitch"));
    }
    
    public static void removeArena(final String s) {
        final List<?> stringList = Settings.yml.getStringList("Arenas");
        stringList.remove(s);
        Settings.yml.set("Arenas", (Object)stringList);
        try {
            Settings.yml.save(Settings.file);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        new File("plugins/SkyGiants1058/Arenas/" + s + ".yml").delete();
    }
    
    public static void toggleSetup(final Player player) {
        if (Main.MAINTENANCE) {
            Main.MAINTENANCE = false;
            Settings.yml.set("setup", (Object)false);
            try {
                Settings.yml.save(Settings.file);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            player.sendMessage(Main.PREFIX + "?6Setup done!");
            player.sendMessage(Main.PREFIX + "?6The server is restarting!");
            Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                @Override
                public void run() {
                    Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "restart");
                }
            }, 60L);
        }
        else {
            Main.MAINTENANCE = true;
            Settings.yml.set("setup", (Object)true);
            try {
                Settings.yml.save(Settings.file);
            }
            catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
    }
    
    static {
        Settings.file = new File("plugins/SkyGiants1058/config.yml");
        Settings.yml = YamlConfiguration.loadConfiguration(Settings.file);
    }
}
