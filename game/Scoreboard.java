package src.TierCraft.Minigame.SkyGiant.plugin.game;

import java.text.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.scoreboard.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Messages;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Settings;
import src.TierCraft.Minigame.SkyGiant.plugin.utils.Database;
import src.TierCraft.Minigame.SkyGiant.plugin.utils.MySQL;

import org.bukkit.plugin.*;

public class Scoreboard
{
    private static SimpleDateFormat style;
    private static Date datew;
    private static Date dateg;
    private static Date dateb;
    public static String goldEntry;
    public static String magentaEntry;
    public static String greenEntry;
    public static String blueEntry;
    
    public static void sendScoreboard() {
        Main.sb = Main.sbmanager.getNewScoreboard();
        Main.obj = Main.sb.registerNewObjective("Test", "Test2");
        final Team registerNewTeam = Main.sb.registerNewTeam("countdown");
        registerNewTeam.addEntry(ChatColor.AQUA.toString());
        Main.sb.registerNewTeam("gold_health").addEntry(Scoreboard.goldEntry);
        Main.sb.registerNewTeam("magenta_health").addEntry(Scoreboard.magentaEntry);
        Main.sb.registerNewTeam("green_health").addEntry(Scoreboard.greenEntry);
        Main.sb.registerNewTeam("blue_health").addEntry(Scoreboard.blueEntry);
        final Team registerNewTeam2 = Main.sb.registerNewTeam("gametime");
        registerNewTeam2.addEntry(ChatColor.RED.toString());
        final Team registerNewTeam3 = Main.sb.registerNewTeam("beast_countdown");
        registerNewTeam3.addEntry(ChatColor.DARK_AQUA.toString());
        Main.obj.setDisplayName(Main.sbtitle);
        Main.obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        registerNewTeam2.setPrefix(Messages.getMsg().getString("scoreboard.warmup").replace('&', '§'));
        Main.obj.getScore(ChatColor.RED.toString()).setScore(14);
        registerNewTeam.setPrefix("03:00");
        Main.obj.getScore(ChatColor.AQUA.toString()).setScore(13);
        Main.obj.getScore("?5               ").setScore(12);
        Main.obj.getScore(Messages.getMsg().getString("scoreboard.giants").replace('&', '§')).setScore(11);
        Main.obj.getScore(Scoreboard.goldEntry).setScore(10);
        Main.obj.getScore(Scoreboard.magentaEntry).setScore(9);
        Main.obj.getScore(Scoreboard.greenEntry).setScore(8);
        Main.obj.getScore(Scoreboard.blueEntry).setScore(7);
        Main.obj.getScore(ChatColor.RED + "          ").setScore(6);
        Main.obj.getScore(Messages.getMsg().getString("scoreboard.beast-spawn").replace('&', '§')).setScore(5);
        registerNewTeam3.setPrefix("03:00");
        Main.obj.getScore(ChatColor.DARK_AQUA.toString()).setScore(4);
        Main.obj.getScore("?5               ").setScore(3);
        Main.obj.getScore("?7---------------").setScore(2);
        Main.obj.getScore(Messages.getMsg().getString("scoreboard.footer").replace('&', '§')).setScore(1);
        (Main.SbGold = Main.sb.registerNewTeam("Gold")).setPrefix("?6");
        (Main.SbMagenta = Main.sb.registerNewTeam("Magenta")).setPrefix("?d");
        (Main.SbGreen = Main.sb.registerNewTeam("Green")).setPrefix("?a");
        (Main.SbBlue = Main.sb.registerNewTeam("Blue")).setPrefix("?9");
        for (final Player player : Bukkit.getOnlinePlayers()) {
            player.setScoreboard(Main.sb);
            if (Main.magentaPlayers.contains(player)) {
                Main.SbMagenta.addEntry(player.getName());
            }
            else if (Main.goldPlayers.contains(player)) {
                Main.SbGold.addEntry(player.getName());
            }
            else if (Main.greenPlayers.contains(player)) {
                Main.SbGreen.addEntry(player.getName());
            }
            else {
                if (!Main.bluePlayers.contains(player)) {
                    continue;
                }
                Main.SbBlue.addEntry(player.getName());
            }
        }
    }
    
    public static void updateScoreboard() {
        if (Main.warmup) {
            Scoreboard.datew = new Date(Main.WarmupMilis -= 1000L);
        }
        else {
            Scoreboard.dateg = new Date(Main.GameMilis -= 1000L);
            if (Main.BeastCountDown != 0L) {
                Scoreboard.dateb = new Date(Main.BeastCountDown -= 1000L);
            }
        }
        for (final Player player : Bukkit.getOnlinePlayers()) {
            if (player.getScoreboard() == null) {
                return;
            }
            if (Main.warmup) {
                player.getScoreboard().getTeam("countdown").setPrefix(String.valueOf(Scoreboard.style.format(Scoreboard.datew)).replace(".", ":"));
            }
            else {
                player.getScoreboard().getTeam("countdown").setPrefix(String.valueOf(Scoreboard.style.format(Scoreboard.dateg)).replace(".", ":"));
                player.getScoreboard().getTeam("gametime").setPrefix(Messages.getMsg().getString("scoreboard.time-left").replace('&', '§'));
                if (Main.BeastCountDown == 0L) {
                    if (!Main.beastSpawned) {
                        Main.nmsH.spawnBeast();
                        Main.beastSpawned = true;
                        final Iterator<? extends Player> iterator2 = Bukkit.getOnlinePlayers().iterator();
                        while (iterator2.hasNext()) {
                            iterator2.next().sendMessage(Main.PREFIX + Messages.getMsg().getString("beast-spawned").replace('&', '§'));
                        }
                    }
                    else {
                        player.getScoreboard().getTeam("beast_countdown").setPrefix(Messages.getMsg().getString("scoreboard.spawned").replace('&', '§'));
                    }
                }
                else {
                    player.getScoreboard().getTeam("beast_countdown").setPrefix(String.valueOf(Scoreboard.style.format(Scoreboard.dateb)).replace(".", ":"));
                }
            }
            player.getScoreboard().getTeam("gold_health").setSuffix(Main.nmsH.gGH("Gold"));
            player.getScoreboard().getTeam("magenta_health").setSuffix(Main.nmsH.gGH("Magenta"));
            player.getScoreboard().getTeam("green_health").setSuffix(Main.nmsH.gGH("Green"));
            player.getScoreboard().getTeam("blue_health").setSuffix(Main.nmsH.gGH("Blue"));
        }
    }
    
    public static void sendStats(final Player player) {
        if (Main.DatabaseB) {
            if (!Settings.getCfg().getBoolean("Database.scoreboard")) {
                return;
            }
            Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                @Override
                public void run() {
                    final MySQL mySQL = new MySQL(Database.host, Database.port, Database.database, Database.username, Database.password);
                    mySQL.connect();
                    Main.sb = Main.sbmanager.getNewScoreboard();
                    (Main.obj = Main.sb.registerNewObjective("Test", "Test2")).setDisplayName(Messages.getMsg().getString("stats").replace('&', '§'));
                    Main.obj.setDisplaySlot(DisplaySlot.SIDEBAR);
                    final Score score = Main.obj.getScore(Messages.getMsg().getString("deaths").replace('&', '§'));
                    final Score score2 = Main.obj.getScore(Messages.getMsg().getString("gold-earnt").replace('&', '§'));
                    final Score score3 = Main.obj.getScore(Messages.getMsg().getString("games-played").replace('&', '§'));
                    final Score score4 = Main.obj.getScore(Messages.getMsg().getString("kills").replace('&', '§'));
                    final Score score5 = Main.obj.getScore(Messages.getMsg().getString("beasts-slain").replace('&', '§'));
                    final Score score6 = Main.obj.getScore(Messages.getMsg().getString("rampages").replace('&', '§'));
                    final Score score7 = Main.obj.getScore(Messages.getMsg().getString("shutdowns").replace('&', '§'));
                    final Score score8 = Main.obj.getScore(Messages.getMsg().getString("victories").replace('&', '§'));
                    score.setScore((int)mySQL.getScore(Database.table, "Deaths", player));
                    score2.setScore((int)mySQL.getScore(Database.table, "GoldEarnt", player));
                    score3.setScore((int)mySQL.getScore(Database.table, "GamesPlayed", player));
                    score4.setScore((int)mySQL.getScore(Database.table, "Kills", player));
                    score5.setScore((int)mySQL.getScore(Database.table, "BeastsSlain", player));
                    score6.setScore((int)mySQL.getScore(Database.table, "Rampages", player));
                    score7.setScore((int)mySQL.getScore(Database.table, "Shutdowns", player));
                    score8.setScore((int)mySQL.getScore(Database.table, "Victories", player));
                    player.setScoreboard(Main.sb);
                    if (!mySQL.isDataExists(Database.table, "UUID", player.getUniqueId().toString())) {
                        final ArrayList<String> list = new ArrayList<String>();
                        list.add("ID");
                        list.add("Player");
                        list.add("Kills");
                        list.add("Deaths");
                        list.add("BeastsSlain");
                        list.add("GoldEarnt");
                        list.add("GamesPlayed");
                        list.add("Victories");
                        list.add("Shutdowns");
                        list.add("Rampages");
                        list.add("UUID");
                        final ArrayList<String> list2 = new ArrayList<String>();
                        list2.add("0");
                        list2.add(player.getName().toString());
                        list2.add("0");
                        list2.add("0");
                        list2.add("0");
                        list2.add("0");
                        list2.add("0");
                        list2.add("0");
                        list2.add("0");
                        list2.add("0");
                        list2.add(player.getUniqueId().toString());
                        mySQL.createDate(Database.table, list, list2);
                    }
                    mySQL.close();
                }
            }, 10L);
        }
    }
    
    public static void restartSb() {
        Main.sb.clearSlot(DisplaySlot.SIDEBAR);
    }
    
    static {
        Scoreboard.style = new SimpleDateFormat("mm:ss");
    }
}
