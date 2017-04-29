package src.TierCraft.Minigame.SkyGiant.plugin;

import org.bukkit.plugin.java.*;
import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.scoreboard.*;
import org.bukkit.*;

import com.comphenix.protocol.*;

import src.TierCraft.Minigame.SkyGiant.plugin.commands.GiveGold;
import src.TierCraft.Minigame.SkyGiant.plugin.commands.Leave;
import src.TierCraft.Minigame.SkyGiant.plugin.commands.Recall;
import src.TierCraft.Minigame.SkyGiant.plugin.commands.SetupCmds;
import src.TierCraft.Minigame.SkyGiant.plugin.commands.Vote;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.ArenaCfg;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Messages;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Settings;
import src.TierCraft.Minigame.SkyGiant.plugin.game.GameState;
import src.TierCraft.Minigame.SkyGiant.plugin.listeners.AsyncPlaterChatListener;
import src.TierCraft.Minigame.SkyGiant.plugin.listeners.BlockBreakListener;
import src.TierCraft.Minigame.SkyGiant.plugin.listeners.BlockPlaceListener;
import src.TierCraft.Minigame.SkyGiant.plugin.listeners.CreatureSpawnListener;
import src.TierCraft.Minigame.SkyGiant.plugin.listeners.EntityDamageByEntityListener;
import src.TierCraft.Minigame.SkyGiant.plugin.listeners.EntityDamageListener;
import src.TierCraft.Minigame.SkyGiant.plugin.listeners.EntityDeathListener;
import src.TierCraft.Minigame.SkyGiant.plugin.listeners.EntityExplodeListener;
import src.TierCraft.Minigame.SkyGiant.plugin.listeners.FoodLevelChangeListener;
import src.TierCraft.Minigame.SkyGiant.plugin.listeners.InventoryClickListener;
import src.TierCraft.Minigame.SkyGiant.plugin.listeners.PlayerDeathListener;
import src.TierCraft.Minigame.SkyGiant.plugin.listeners.PlayerDropItemListener;
import src.TierCraft.Minigame.SkyGiant.plugin.listeners.PlayerInteractEntityListener;
import src.TierCraft.Minigame.SkyGiant.plugin.listeners.PlayerInteractListener;
import src.TierCraft.Minigame.SkyGiant.plugin.listeners.PlayerJoinListener;
import src.TierCraft.Minigame.SkyGiant.plugin.listeners.PlayerLoginListener;
import src.TierCraft.Minigame.SkyGiant.plugin.listeners.PlayerPickupItemListener;
import src.TierCraft.Minigame.SkyGiant.plugin.listeners.PlayerQuitListener;
import src.TierCraft.Minigame.SkyGiant.plugin.listeners.PlayerRespawnListener;
import src.TierCraft.Minigame.SkyGiant.plugin.listeners.PrepareItemCraftListener;
import src.TierCraft.Minigame.SkyGiant.plugin.listeners.ServerPingListener;
import src.TierCraft.Minigame.SkyGiant.plugin.listeners.WeatherChangeListener;
import src.TierCraft.Minigame.SkyGiant.plugin.locations.Region;
import src.TierCraft.Minigame.SkyGiant.plugin.protocolLib.PotionEfect;
import src.TierCraft.Minigame.SkyGiant.plugin.utils.Database;
import src.TierCraft.Minigame.SkyGiant.plugin.utils.Updater;

import org.bukkit.command.*;
import org.bukkit.event.*;
import org.bukkit.plugin.*;

public class Main extends JavaPlugin
{
    public static ProtocolManager protocolLib;
    public static Main plugin;
    public static NMS nmsH;
    public static String Language;
    public static Boolean MAINTENANCE;
    public static Boolean miniSG;
    public static boolean Debug;
    public static Boolean DatabaseB;
    public static String choosenMap;
    public static String sbtitle;
    public static ArrayList<Player> players;
    public static ArrayList<Player> spectators;
    public static Boolean allowSpectate;
    public static ArrayList<Player> magentaPlayers;
    public static ArrayList<Player> goldPlayers;
    public static ArrayList<Player> greenPlayers;
    public static ArrayList<Player> bluePlayers;
    public static ArrayList<String> aliveTeams;
    public static HashMap<Player, Integer> money;
    public static HashMap<Player, Integer> kills;
    public static HashMap<Player, Integer> beastsSlain;
    public static HashMap<Player, Integer> deaths;
    public static HashMap<Player, Integer> goldearnt;
    public static ArrayList<Player> gamesplayed;
    public static ArrayList<Player> victories;
    public static ArrayList<Player> lookingAtShop;
    public static HashMap<Player, Integer> respawningVoid;
    public static HashMap<Player, Integer> respawningKill;
    public static ArrayList<Player> recall;
    public static ArrayList<Player> respawning;
    public static HashMap<Player, Location> respOwn;
    public static ArrayList<Player> hasTeam;
    public static Region GoldVillagerRegion;
    public static Region MagentaVillagerRegion;
    public static Region GreenVillagerRegion;
    public static Region BlueVillagerRegion;
    public static Region GoldGiantRegion;
    public static Region MagentaGiantRegion;
    public static Region GreenGiantRegion;
    public static Region BlueGiantRegion;
    public static Region BeastRegion;
    public static Region MiddleRegion;
    public static Team SbMagenta;
    public static Team SbGold;
    public static Team SbGreen;
    public static Team SbBlue;
    public static String PREFIX;
    public static int LobbyCountdown;
    public static int GameCountdown;
    public static long BeastCountDown;
    public static int RestartCountdown;
    public static int WarmupCountdown;
    public static int GiantHealth;
    public static int MaxInTeam;
    public static Boolean warmup;
    public static GameState STATUS;
    public static Boolean beastSpawned;
    public static String beastType;
    public static int BeastHealth;
    public static Boolean loaded;
    public static ArrayList<Location> PlacedBlocks;
    public static HashMap<Location, Player> respBeac;
    public static Boolean goldHit;
    public static Boolean magentaHit;
    public static Boolean greenHit;
    public static Boolean blueHit;
    public static ArrayList<Player> cannotHitGiant;
    public static ArrayList<Player> voted;
    public static Boolean doubleDamage;
    public static String nmsver;
    public static ScoreboardManager sbmanager;
    public static Scoreboard sb;
    public static Objective obj;
    public static Boolean WINNER;
    public static long WarmupMilis;
    public static long GameMilis;
    public static Boolean updateAvailable;
    public static String newVersion;
    public static AsyncPlaterChatListener chat;
    public static Boolean vaultHook;
    public static HashMap<String, Integer> mapsVoting;
    
    public void onEnable() {
        Main.nmsver = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            final Class<?> forName = Class.forName("src.TierCraft.Minigame.SkyGiant.plugin.nms." + Main.nmsver + ".Main");
            if (NMS.class.isAssignableFrom(forName)) {
                Main.nmsH = (NMS)forName.getConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]);
            }
        }
        catch (Exception ex) {
            this.getLogger().severe("Could not find support for this Spigot version.");
            this.setEnabled(false);
            return;
        }
        this.getLogger().info("Loading support for " + Main.nmsver);
        Main.plugin = this;
        Main.protocolLib = ProtocolLibrary.getProtocolManager();
        Settings.setupConfig();
        Messages.setupMessages();
        Database.setupDatabase();
        ArenaCfg.registerArenas();
        this.getCommand("sgs").setExecutor((CommandExecutor)new SetupCmds());
        this.getCommand("leave").setExecutor((CommandExecutor)new Leave());
        this.getCommand("givegold").setExecutor((CommandExecutor)new GiveGold());
        this.getCommand("recall").setExecutor((CommandExecutor)new Recall());
        this.getCommand("vote").setExecutor((CommandExecutor)new Vote());
        this.getCommand("team").setExecutor((CommandExecutor)new src.TierCraft.Minigame.SkyGiant.plugin.commands.Team());
        final PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents((Listener)new PlayerJoinListener(), (Plugin)this);
        pluginManager.registerEvents((Listener)new PlayerLoginListener(), (Plugin)this);
        pluginManager.registerEvents((Listener)new ServerPingListener(), (Plugin)this);
        pluginManager.registerEvents((Listener)new PlayerQuitListener(), (Plugin)this);
        pluginManager.registerEvents((Listener)new BlockBreakListener(), (Plugin)this);
        pluginManager.registerEvents((Listener)new BlockPlaceListener(), (Plugin)this);
        pluginManager.registerEvents((Listener)new EntityExplodeListener(), (Plugin)this);
        pluginManager.registerEvents((Listener)new EntityDamageListener(), (Plugin)this);
        pluginManager.registerEvents((Listener)new EntityDeathListener(), (Plugin)this);
        pluginManager.registerEvents((Listener)new EntityDamageByEntityListener(), (Plugin)this);
        pluginManager.registerEvents((Listener)new PlayerDeathListener(), (Plugin)this);
        pluginManager.registerEvents((Listener)new PlayerRespawnListener(), (Plugin)this);
        pluginManager.registerEvents((Listener)new CreatureSpawnListener(), (Plugin)this);
        pluginManager.registerEvents((Listener)new FoodLevelChangeListener(), (Plugin)this);
        pluginManager.registerEvents((Listener)new AsyncPlaterChatListener(), (Plugin)this);
        pluginManager.registerEvents((Listener)new PlayerInteractListener(), (Plugin)this);
        pluginManager.registerEvents((Listener)new PlayerInteractEntityListener(), (Plugin)this);
        pluginManager.registerEvents((Listener)new InventoryClickListener(), (Plugin)this);
        pluginManager.registerEvents((Listener)new PlayerPickupItemListener(), (Plugin)this);
        pluginManager.registerEvents((Listener)new PlayerDropItemListener(), (Plugin)this);
        pluginManager.registerEvents((Listener)new PrepareItemCraftListener(), (Plugin)this);
        pluginManager.registerEvents((Listener)new WeatherChangeListener(), (Plugin)this);
        Main.nmsH.registerGiant();
        Main.nmsH.registerVillager();
        Main.nmsH.registerBeast();
        Main.plugin.getServer().getMessenger().registerOutgoingPluginChannel((Plugin)Main.plugin, "BungeeCord");
        Main.sbmanager = Bukkit.getScoreboardManager();
        Updater.checkUpdates();
        Main.updateAvailable = false;
        PotionEfect.registerPotionEffects();
        this.setupChat();
    }
    
    public void onDisable() {
        if (Main.choosenMap != null) {
            Bukkit.unloadWorld(Main.choosenMap, true);
        }
    }
    
    private boolean setupChat() {
        if (this.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        Main.chat = this.getServer().getServicesManager().getRegistration(AsyncPlaterChatListener.class).getProvider();
        Main.vaultHook = true;
        Main.plugin.getLogger().info("Loaded Vault support!");
        return Main.chat != null;
    }
    
    static {
        Main.Debug = false;
        Main.DatabaseB = false;
        Main.players = new ArrayList<Player>();
        Main.spectators = new ArrayList<Player>();
        Main.allowSpectate = false;
        Main.magentaPlayers = new ArrayList<Player>();
        Main.goldPlayers = new ArrayList<Player>();
        Main.greenPlayers = new ArrayList<Player>();
        Main.bluePlayers = new ArrayList<Player>();
        Main.aliveTeams = new ArrayList<String>();
        Main.money = new HashMap<Player, Integer>();
        Main.kills = new HashMap<Player, Integer>();
        Main.beastsSlain = new HashMap<Player, Integer>();
        Main.deaths = new HashMap<Player, Integer>();
        Main.goldearnt = new HashMap<Player, Integer>();
        Main.gamesplayed = new ArrayList<Player>();
        Main.victories = new ArrayList<Player>();
        Main.lookingAtShop = new ArrayList<Player>();
        Main.respawningVoid = new HashMap<Player, Integer>();
        Main.respawningKill = new HashMap<Player, Integer>();
        Main.recall = new ArrayList<Player>();
        Main.respawning = new ArrayList<Player>();
        Main.respOwn = new HashMap<Player, Location>();
        Main.hasTeam = new ArrayList<Player>();
        Main.LobbyCountdown = 300;
        Main.GameCountdown = 1800;
        Main.BeastCountDown = 180000L;
        Main.RestartCountdown = 12;
        Main.WarmupCountdown = 90;
        Main.GiantHealth = 850;
        Main.MaxInTeam = 6;
        Main.warmup = true;
        Main.beastSpawned = false;
        Main.beastType = null;
        Main.BeastHealth = 350;
        Main.loaded = false;
        Main.PlacedBlocks = new ArrayList<Location>();
        Main.respBeac = new HashMap<Location, Player>();
        Main.goldHit = false;
        Main.magentaHit = false;
        Main.greenHit = false;
        Main.blueHit = false;
        Main.cannotHitGiant = new ArrayList<Player>();
        Main.voted = new ArrayList<Player>();
        Main.doubleDamage = false;
        Main.WINNER = false;
        Main.WarmupMilis = 90000L;
        Main.newVersion = null;
        Main.chat = null;
        Main.vaultHook = false;
        Main.mapsVoting = new HashMap<String, Integer>();
    }
}
