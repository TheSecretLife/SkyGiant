package src.TierCraft.Minigame.SkyGiant.plugin.utils;

import org.bukkit.*;
import java.util.*;
import org.bukkit.plugin.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Settings;
import src.TierCraft.Minigame.SkyGiant.plugin.game.GameState;

import org.bukkit.entity.*;

public class Database
{
    public static String host;
    public static int port;
    public static String database;
    public static String username;
    public static String password;
    public static String table;
    
    public static void setupDatabase() {
        Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
            @Override
            public void run() {
                if (Main.DatabaseB) {
                    final ArrayList<String> list = new ArrayList<String>();
                    list.add("ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY");
                    list.add("Player char(36)");
                    list.add("Kills INT");
                    list.add("Deaths INT");
                    list.add("BeastsSlain INT");
                    list.add("GoldEarnt INT");
                    list.add("GamesPlayed INT");
                    list.add("Victories INT");
                    list.add("Shutdowns INT");
                    list.add("Rampages INT");
                    list.add("UUID char(36)");
                    new MySQL(Database.host, Database.port, Database.database, Database.username, Database.password).createTable(Database.table, list);
                    Main.plugin.getLogger().info("Database connected!");
                }
            }
        }, 7L);
    }
    
    public static void saveStats(final Player player) {
        if (Main.DatabaseB) {
            final MySQL mySQL = new MySQL(Database.host, Database.port, Database.database, Database.username, Database.password);
            mySQL.connect();
            final Object score = mySQL.getScore(Database.table, "Kills", player);
            final Object score2 = mySQL.getScore(Database.table, "Deaths", player);
            final Object score3 = mySQL.getScore(Database.table, "GoldEarnt", player);
            final Object score4 = mySQL.getScore(Database.table, "GamesPlayed", player);
            final Object score5 = mySQL.getScore(Database.table, "Victories", player);
            final Object score6 = mySQL.getScore(Database.table, "BeastsSlain", player);
            int intValue = (int)score;
            int intValue2 = (int)score2;
            int intValue3 = (int)score3;
            final int intValue4 = (int)score4;
            int intValue5 = (int)score6;
            if (Main.kills.containsKey(player)) {
                intValue += Main.kills.get(player);
            }
            if (Main.deaths.containsKey(player)) {
                intValue2 += Main.deaths.get(player);
            }
            if (Main.money.containsKey(player)) {
                intValue3 += Main.money.get(player) / 1000;
            }
            final int n = intValue4 + 1;
            if (Main.beastsSlain.containsKey(player)) {
                intValue5 += Main.beastsSlain.get(player);
            }
            int n2;
            if (Main.STATUS == GameState.RESTARTING) {
                if (Main.players.contains(player)) {
                    n2 = (int)score5 + 1;
                }
                else {
                    n2 = (int)score5;
                }
            }
            else {
                n2 = (int)score5;
            }
            final ArrayList<String> list = new ArrayList<String>();
            list.add("Player");
            list.add("Kills");
            list.add("Deaths");
            list.add("BeastsSlain");
            list.add("GoldEarnt");
            list.add("GamesPlayed");
            list.add("Victories");
            list.add("Shutdowns");
            list.add("Rampages");
            final ArrayList<String> list2 = new ArrayList<String>();
            list2.add(player.getName().toString());
            list2.add(String.valueOf(intValue));
            list2.add(String.valueOf(intValue2));
            list2.add(String.valueOf(intValue5));
            list2.add(String.valueOf(intValue3));
            list2.add(String.valueOf(n));
            list2.add(String.valueOf(n2));
            list2.add("0");
            list2.add("0");
            mySQL.setData(Database.table, list, list2, "UUID", "=", player.getUniqueId().toString());
        }
    }
    
    static {
        Database.host = Settings.getCfg().getString("Database.host");
        Database.port = Settings.getCfg().getInt("Database.port");
        Database.database = Settings.getCfg().getString("Database.database");
        Database.username = Settings.getCfg().getString("Database.username");
        Database.password = Settings.getCfg().getString("Database.password");
        Database.table = "SkyGiants_stats";
    }
}
