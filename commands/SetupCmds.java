package src.TierCraft.Minigame.SkyGiant.plugin.commands;

import org.bukkit.entity.*;
import org.bukkit.command.*;
import org.bukkit.*;
import java.io.*;
import org.bukkit.plugin.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.ArenaCfg;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Settings;
import src.TierCraft.Minigame.SkyGiant.plugin.locations.Beasts;
import src.TierCraft.Minigame.SkyGiant.plugin.locations.Borders;
import src.TierCraft.Minigame.SkyGiant.plugin.locations.Giants;
import src.TierCraft.Minigame.SkyGiant.plugin.locations.Middle;
import src.TierCraft.Minigame.SkyGiant.plugin.locations.Spawns;
import src.TierCraft.Minigame.SkyGiant.plugin.locations.Spectate;
import src.TierCraft.Minigame.SkyGiant.plugin.locations.Villagers;

import java.util.*;

public class SetupCmds implements CommandExecutor
{
    private static HashMap<Player, String> createArena;
    private static HashMap<Player, Location> setupLocation;
    private static HashMap<Player, String> editArena;
    
    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] array) {
        if (command.getName().equalsIgnoreCase("sgs")) {
            if (!(commandSender instanceof Player)) {
                return true;
            }
            final Player midPos2 = (Player)commandSender;
            if (!midPos2.isOp()) {
                return true;
            }
            if (!Main.MAINTENANCE) {
                midPos2.sendMessage(Main.PREFIX + "§cThis arena in not in setup mode!");
                midPos2.sendMessage(Main.PREFIX + "§7Go in your config file and set setup-mode = true and restart the server.");
                return true;
            }
            if (array.length == 0) {
                if (midPos2.getLocation().getWorld().getName().equalsIgnoreCase(SetupCmds.createArena.get(midPos2))) {
                    remainingCmds(midPos2);
                    return true;
                }
                if (midPos2.getWorld().getName().equalsIgnoreCase(SetupCmds.editArena.get(midPos2))) {
                    listArenaCmds(midPos2);
                    return true;
                }
                listCmds(midPos2);
            }
            else {
                if (array[0].equalsIgnoreCase("addarena")) {
                    if (array.length == 2) {
                        Bukkit.getServer().createWorld(new WorldCreator(array[1]));
                        midPos2.sendMessage(Main.PREFIX + "§cPlease wait!");
                        SetupCmds.setupLocation.put(midPos2, midPos2.getLocation());
                        ArenaCfg.CreateArenaCfg(array[1], midPos2);
                        Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                            @Override
                            public void run() {
                                midPos2.teleport(Bukkit.getWorld(array[1]).getSpawnLocation());
                                midPos2.setGameMode(GameMode.CREATIVE);
                                Bukkit.getWorld(array[1]).setAutoSave(true);
                                SetupCmds.createArena.put(midPos2, midPos2.getLocation().getWorld().getName());
                                midPos2.sendMessage(Main.PREFIX + "§aYou were teleported to the " + midPos2.getWorld().getName() + "'s spawn.");
                                if (Settings.getCfg().get("Arenas") != null) {
                                    final List<String> stringList = Settings.getCfg().getStringList("Arenas");
                                    stringList.add(midPos2.getLocation().getWorld().getName());
                                    Settings.getCfg().set("Arenas", (Object)stringList);
                                    try {
                                        Settings.getCfg().save(Settings.getCfgFile());
                                    }
                                    catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                else {
                                    Settings.addArenaToList(midPos2);
                                }
                            }
                        }, 60L);
                    }
                    else {
                        midPos2.sendMessage(Main.PREFIX + "§cUsage: /sgs addArena <worldName>");
                    }
                    return true;
                }
                if (array[0].equalsIgnoreCase("setmainlobby")) {
                    if (array.length == 1) {
                        Settings.setMainLobby(midPos2);
                    }
                    else {
                        midPos2.sendMessage(Main.PREFIX + "§cUsage: /sgs setMainLobby");
                    }
                    return true;
                }
                if (array[0].equalsIgnoreCase("setspectate")) {
                    if (array.length == 1) {
                        Spectate.setSpect(midPos2);
                        remainingCmds(midPos2);
                    }
                    else {
                        midPos2.sendMessage(Main.PREFIX + "§cUsage: /sgs setSpectate");
                    }
                    return true;
                }
                if (array[0].equalsIgnoreCase("setspawn")) {
                    if (array.length == 2) {
                        Spawns.setSpawn(array[1], midPos2);
                        remainingCmds(midPos2);
                    }
                    else {
                        midPos2.sendMessage(Main.PREFIX + "§cUsage: /sgs setSpawn <team>");
                    }
                    return true;
                }
                if (array[0].equalsIgnoreCase("setgiant")) {
                    if (array.length == 2) {
                        Giants.setGiant(array[1], midPos2);
                        remainingCmds(midPos2);
                    }
                    else {
                        midPos2.sendMessage(Main.PREFIX + "§cUsage: /sgs setGiant <team>");
                    }
                    return true;
                }
                if (array[0].equalsIgnoreCase("setvillager")) {
                    if (array.length == 2) {
                        Villagers.setVillager(array[1], midPos2);
                        remainingCmds(midPos2);
                    }
                    else {
                        midPos2.sendMessage(Main.PREFIX + "§cUsage: /sgs setVillager <team>");
                    }
                    return true;
                }
                if (array[0].equalsIgnoreCase("setborder")) {
                    if (array.length == 3) {
                        if (!isInt(array[2])) {
                            midPos2.sendMessage(Main.PREFIX + "?cUsage: /sgs setBorder <team> ?4<radius>");
                            return true;
                        }
                        Borders.setBorder(array[1], midPos2, Integer.valueOf(array[2]) * 2);
                        remainingCmds(midPos2);
                    }
                    else {
                        midPos2.sendMessage(Main.PREFIX + "?cUsage: /sgs setBorder <team> <radius>");
                    }
                }
                else if (array[0].equalsIgnoreCase("setbeast")) {
                    if (array.length == 1) {
                        Beasts.setBeast(midPos2);
                        remainingCmds(midPos2);
                    }
                    else {
                        midPos2.sendMessage(Main.PREFIX + "?cUsage: /sgs setBeast");
                    }
                }
                else if (array[0].equalsIgnoreCase("setmidpos")) {
                    if (array.length == 2) {
                        if (array[1].equalsIgnoreCase("1")) {
                            Middle.setMidPos1(midPos2);
                            remainingCmds(midPos2);
                        }
                        else if (array[1].equalsIgnoreCase("2")) {
                            Middle.setMidPos2(midPos2);
                            remainingCmds(midPos2);
                        }
                        else {
                            midPos2.sendMessage(Main.PREFIX + "?cUsage: /setMidPos 1/2");
                        }
                    }
                    else {
                        midPos2.sendMessage(Main.PREFIX + "?cUsage: /setMidPos 1/2");
                    }
                }
                else {
                    if (array[0].equalsIgnoreCase("finisharena")) {
                        if (array.length == 1) {
                            if (Settings.getCfg().getString("MainLobby.World") != null) {
                                midPos2.teleport(Settings.getMainLobby());
                            }
                            else {
                                midPos2.teleport((Location)SetupCmds.setupLocation.get(midPos2));
                            }
                            if (SetupCmds.createArena.containsKey(midPos2)) {
                                midPos2.sendMessage(Main.PREFIX + "?b" + SetupCmds.createArena.get(midPos2) + " was saved!");
                            }
                            else if (SetupCmds.editArena.containsKey(midPos2)) {
                                midPos2.sendMessage(Main.PREFIX + "?b" + SetupCmds.editArena.get(midPos2) + " was saved!");
                            }
                            Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    if (SetupCmds.createArena.containsKey(midPos2)) {
                                        Bukkit.getServer().unloadWorld((String)SetupCmds.createArena.get(midPos2), true);
                                    }
                                    else if (SetupCmds.editArena.containsKey(midPos2)) {
                                        Bukkit.getServer().unloadWorld((String)SetupCmds.editArena.get(midPos2), true);
                                    }
                                }
                            }, 20L);
                            if (SetupCmds.createArena.containsKey(midPos2)) {
                                SetupCmds.createArena.remove(midPos2);
                            }
                            else if (SetupCmds.editArena.containsKey(midPos2)) {
                                SetupCmds.editArena.remove(midPos2);
                            }
                        }
                        else {
                            midPos2.sendMessage(Main.PREFIX + "?cUsage: /sgs finishArena");
                        }
                        return true;
                    }
                    if (array[0].equalsIgnoreCase("arenas")) {
                        if (array.length == 1) {
                            final List<String> stringList = Settings.getCfg().getStringList("Arenas");
                            midPos2.sendMessage(Main.PREFIX + "?6\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d");
                            midPos2.sendMessage(Main.PREFIX + "?e?lAvailable arenas");
                            final Iterator<String> iterator = stringList.iterator();
                            while (iterator.hasNext()) {
                                midPos2.sendMessage(Main.PREFIX + "?b" + iterator.next());
                            }
                            midPos2.sendMessage(Main.PREFIX + "?6\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d");
                        }
                        else {
                            midPos2.sendMessage(Main.PREFIX + "?cUsage: /sgs arenas");
                        }
                    }
                    else if (array[0].equalsIgnoreCase("deletearena")) {
                        if (array.length == 2) {
                            if (Settings.getCfg().getString("Arenas").contains(array[1])) {
                                Settings.removeArena(array[1]);
                                midPos2.sendMessage(Main.PREFIX + "?b" + array[1] + " was removed from the config!");
                            }
                            else {
                                midPos2.sendMessage(Main.PREFIX + "?cUnknown arena!");
                            }
                        }
                        else {
                            midPos2.sendMessage(Main.PREFIX + "?cUsage: /sgs deleteArena <worldName>");
                        }
                    }
                    else if (array[0].equalsIgnoreCase("editarena")) {
                        if (array.length == 2) {
                            if (Settings.getCfg().getStringList("Arenas").contains(array[1])) {
                                if (SetupCmds.createArena.containsKey(midPos2)) {
                                    midPos2.sendMessage(Main.PREFIX + "?cYou can't use this command now because you're creating a new arena!");
                                    return true;
                                }
                                SetupCmds.setupLocation.put(midPos2, midPos2.getLocation());
                                SetupCmds.editArena.put(midPos2, array[1]);
                                Bukkit.getServer().createWorld(new WorldCreator(array[1]));
                                midPos2.sendMessage(Main.PREFIX + "?cPlease wait!");
                                Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                                    @Override
                                    public void run() {
                                        if (Spectate.getSpect(array[1]) != null) {
                                            midPos2.teleport(Spectate.getSpect(array[1]));
                                        }
                                        else {
                                            midPos2.teleport(Bukkit.getWorld(array[1]).getSpawnLocation());
                                        }
                                    }
                                }, 20L);
                            }
                            else {
                                midPos2.sendMessage(Main.PREFIX + "?c" + array[1] + " doesn't exist!");
                            }
                        }
                        else {
                            midPos2.sendMessage(Main.PREFIX + "?cUsage: /sgs editArena <worldName>");
                        }
                    }
                }
                if (array[0].equalsIgnoreCase("togglesetup")) {
                    if (array.length == 1) {
                        Settings.toggleSetup(midPos2);
                    }
                    else {
                        midPos2.sendMessage(Main.PREFIX + "?cUsage: /sgs toggleSetup");
                    }
                }
            }
        }
        return false;
    }
    
    private static void listCmds(final Player player) {
        player.sendMessage(Main.PREFIX + "?6\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d");
        player.sendMessage(Main.PREFIX + "?e?lSetup Commands");
        player.sendMessage(Main.PREFIX + "?b/sgs arenas");
        player.sendMessage(Main.PREFIX + "?b/sgs setMainLobby");
        player.sendMessage(Main.PREFIX + "?b/sgs addArena <worldName>");
        player.sendMessage(Main.PREFIX + "?b/sgs deleteArena <worldName>");
        player.sendMessage(Main.PREFIX + "?b/sgs editArena <worldName>");
        player.sendMessage(Main.PREFIX + "?b/sgs toggleSetup");
        player.sendMessage(Main.PREFIX + "?6\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d");
    }
    
    private static void remainingCmds(final Player player) {
        final String name = player.getLocation().getWorld().getName();
        player.sendMessage(Main.PREFIX + "?6\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d");
        player.sendMessage(Main.PREFIX + "?e?l" + name + " remaining cmds");
        if (ArenaCfg.getArena(name).get("Spectate") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setSpectate");
        }
        if (ArenaCfg.getArena(name).get("Beast") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setBeast");
        }
        Spawns.checkSpawns(player);
        Giants.checkGiants(player);
        Villagers.checkVillagers(player);
        Borders.checkBorders(player);
        if (ArenaCfg.getArena(player.getWorld().getName()).get("Region.Middle.Pos1.X") == null && ArenaCfg.getArena(player.getWorld().getName()).get("Region.Middle.Pos2.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setMidPos ?91 ?22");
        }
        else if (ArenaCfg.getArena(player.getWorld().getName()).get("Region.Middle.Pos1.X") == null && ArenaCfg.getArena(player.getWorld().getName()).get("Region.Middle.Pos2.X") != null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setMidPos ?91");
        }
        else if (ArenaCfg.getArena(player.getWorld().getName()).get("Region.Middle.Pos1.X") != null && ArenaCfg.getArena(player.getWorld().getName()).get("Region.Middle.Pos2.X") == null) {
            player.sendMessage(Main.PREFIX + "?b/sgs setMidPos ?22");
        }
        player.sendMessage(Main.PREFIX + "?7/sgs finishArena");
        player.sendMessage(Main.PREFIX + "?6\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d");
    }
    
    private static void listArenaCmds(final Player player) {
        player.sendMessage(Main.PREFIX + "?6\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d");
        player.sendMessage(Main.PREFIX + "?b/sgs setSpectate");
        player.sendMessage(Main.PREFIX + "?b/sgs setBeast");
        player.sendMessage(Main.PREFIX + "?b/sgs setSpawn <team>");
        player.sendMessage(Main.PREFIX + "?b/sgs setGiant <team>");
        player.sendMessage(Main.PREFIX + "?b/sgs setVillager <team>");
        player.sendMessage(Main.PREFIX + "?b/sgs setBorder <team> <radius>");
        player.sendMessage(Main.PREFIX + "?b/sgs setMidPos 1/2");
        player.sendMessage(Main.PREFIX + "?7/sgs finishArena");
        player.sendMessage(Main.PREFIX + "?6\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d");
    }
    
    private static boolean isInt(final String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }
    
    static {
        SetupCmds.createArena = new HashMap<Player, String>();
        SetupCmds.setupLocation = new HashMap<Player, Location>();
        SetupCmds.editArena = new HashMap<Player, String>();
    }
}
