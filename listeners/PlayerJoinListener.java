package src.TierCraft.Minigame.SkyGiant.plugin.listeners;

import org.bukkit.event.player.*;
import org.bukkit.potion.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.ArenaCfg;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Messages;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Settings;
import src.TierCraft.Minigame.SkyGiant.plugin.game.GameState;
import src.TierCraft.Minigame.SkyGiant.plugin.game.Scoreboard;
import src.TierCraft.Minigame.SkyGiant.plugin.game.Spectate;
import src.TierCraft.Minigame.SkyGiant.plugin.runnables.Lobby;

import org.bukkit.plugin.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.inventory.*;

public class PlayerJoinListener implements Listener
{
    @EventHandler
    public void onJoin(final PlayerJoinEvent playerJoinEvent) {
        final Player player = playerJoinEvent.getPlayer();
        if (player.getName().equalsIgnoreCase("andrei1058")) {
            player.sendMessage("§7Plugin version §a" + Main.plugin.getDescription().getVersion());
            player.sendMessage("§7ProtocolLib version §a" + Bukkit.getPluginManager().getPlugin("ProtocolLib").getDescription().getVersion());
            player.sendMessage("§7Server version §a" + Bukkit.getServer().getVersion());
        }
        if (Main.MAINTENANCE) {
            player.sendMessage(Main.PREFIX + "§cThe server is running in setup mode!");
            player.setGameMode(GameMode.CREATIVE);
            if (Settings.getCfg().getString("MainLobby.World") != null) {
                player.teleport(Settings.getMainLobby());
            }
            else {
                player.teleport(playerJoinEvent.getPlayer().getWorld().getSpawnLocation());
            }
            return;
        }
        player.getInventory().clear();
        player.getInventory().setArmorContents((ItemStack[])null);
        player.setFoodLevel(20);
        player.setHealth(20.0);
        final Iterator<PotionEffect> iterator = playerJoinEvent.getPlayer().getActivePotionEffects().iterator();
        while (iterator.hasNext()) {
            playerJoinEvent.getPlayer().removePotionEffect(iterator.next().getType());
        }
        if (playerJoinEvent.getPlayer().isOp() && Main.updateAvailable) {
            player.sendMessage(Main.PREFIX + " §bThere is a new version available! §c" + Main.newVersion);
            player.sendMessage(" ?ehttps://www.spigotmc.org/resources/29803/");
        }
        if (Main.STATUS == GameState.LOBBY) {
            playerJoinEvent.setJoinMessage(Main.PREFIX + Messages.getMsg().getString("wants-to-battle").replace("{player}", playerJoinEvent.getPlayer().getName()).replace('&', '§'));
            player.setGameMode(GameMode.ADVENTURE);
            player.setLevel(Main.LobbyCountdown);
            player.teleport(Settings.getMainLobby());
            if (!Main.miniSG) {
                if (Bukkit.getOnlinePlayers().size() >= Main.MaxInTeam - 2) {
                    new Lobby<Object>().runTaskTimer((Plugin)Main.plugin, 20L, 20L);
                    Main.STATUS = GameState.STARTING;
                    Bukkit.broadcastMessage(Main.PREFIX + Messages.getMsg().getString("current-players").replace("{amount}", String.valueOf(Bukkit.getOnlinePlayers().size())).replace('&', '§'));
                }
            }
            else if (Bukkit.getOnlinePlayers().size() >= Main.MaxInTeam - 6) {
                Main.LobbyCountdown = Settings.getCfg().getInt("countdowns.lobby.half");
                new Lobby<Object>().runTaskTimer((Plugin)Main.plugin, 20L, 20L);
                Main.STATUS = GameState.STARTING;
                Bukkit.broadcastMessage(Main.PREFIX + Messages.getMsg().getString("current-players").replace("{amount}", String.valueOf(Bukkit.getOnlinePlayers().size())).replace('&', '§'));
            }
            ArenaCfg.listArenaVotes(player);
            Scoreboard.sendStats(player);
            Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                @Override
                public void run() {
                    lobbyItems(player);
                }
            }, 5L);
        }
        if (Main.STATUS == GameState.STARTING) {
            playerJoinEvent.setJoinMessage(Main.PREFIX + Messages.getMsg().getString("wants-to-battle").replace("{player}", playerJoinEvent.getPlayer().getName()).replace('&', '§'));
            player.teleport(Settings.getMainLobby());
            player.setGameMode(GameMode.ADVENTURE);
            if (Bukkit.getOnlinePlayers().size() >= Main.MaxInTeam * 2 && Main.LobbyCountdown > Settings.getCfg().getInt("countdowns.lobby.half")) {
                Main.LobbyCountdown = Settings.getCfg().getInt("countdowns.lobby.half");
                Bukkit.broadcastMessage(Main.PREFIX + Messages.getMsg().getString("current-players").replace("{amount}", String.valueOf(Bukkit.getOnlinePlayers().size())).replace('&', '§'));
                Bukkit.broadcastMessage(Main.PREFIX + Messages.getMsg().getString("timer-shortened").replace("{timer}", String.valueOf(Main.LobbyCountdown)).replace('&', '§'));
            }
            if (Bukkit.getOnlinePlayers().size() == Main.MaxInTeam * 4) {
                if (Main.LobbyCountdown < Settings.getCfg().getInt("countdowns.lobby.full")) {
                    return;
                }
                Main.LobbyCountdown = Settings.getCfg().getInt("countdowns.lobby.full");
                Bukkit.broadcastMessage(Main.PREFIX + Messages.getMsg().getString("current-players").replace("{amount}", String.valueOf(Bukkit.getOnlinePlayers().size())).replace('&', '§'));
                Bukkit.broadcastMessage(Main.PREFIX + Messages.getMsg().getString("timer-shortened").replace("{timer}", String.valueOf(Main.LobbyCountdown)).replace('&', '§'));
            }
            ArenaCfg.listArenaVotes(player);
            Scoreboard.sendStats(player);
            Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                @Override
                public void run() {
                    lobbyItems(player);
                }
            }, 5L);
        }
        if (Main.STATUS == GameState.PLAYING || Main.STATUS == GameState.RESTARTING) {
            Spectate.setSpectator(player);
            playerJoinEvent.setJoinMessage((String)null);
        }
        Main.kills.put(player, 0);
        Main.money.put(player, 0);
        Main.deaths.put(player, 0);
    }
    
    private static void lobbyItems(final Player player) {
        final ItemStack itemStack = new ItemStack(Material.SLIME_BALL);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Messages.getMsg().getString("back-to-hub.name").replace('&', '§'));
        final ArrayList<String> lore = new ArrayList<String>();
        final Iterator<String> iterator = Messages.getMsg().getStringList("back-to-hub.lore").iterator();
        while (iterator.hasNext()) {
            lore.add(iterator.next().replace('&', '§'));
        }
        itemMeta.setLore((List<String>)lore);
        itemStack.setItemMeta(itemMeta);
        player.getInventory().setItem(8, itemStack);
        if (Main.miniSG) {
            final ItemStack itemStack2 = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
            final ItemMeta itemMeta2 = itemStack2.getItemMeta();
            itemMeta2.setDisplayName(Messages.getMsg().getString("mini-team").replace('&', '§'));
            final ArrayList<String> lore2 = new ArrayList<String>();
            final Iterator<String> iterator2 = Messages.getMsg().getStringList("choose-team").iterator();
            while (iterator2.hasNext()) {
                lore2.add(iterator2.next().replace('&', '§'));
            }
            itemMeta2.setLore((List<String>)lore2);
            itemStack2.setItemMeta(itemMeta2);
            player.getInventory().setItem(4, itemStack2);
        }
        else {
            final ItemStack itemStack3 = new ItemStack(Material.STAINED_CLAY, 1);
            final ItemMeta itemMeta3 = itemStack3.getItemMeta();
            itemMeta3.setDisplayName(Messages.getMsg().getString("normal-team").replace('&', '§'));
            final ArrayList<String> lore3 = new ArrayList<String>();
            final Iterator<String> iterator3 = Messages.getMsg().getStringList("choose-team").iterator();
            while (iterator3.hasNext()) {
                lore3.add(iterator3.next().replace('&', '§'));
            }
            itemMeta3.setLore((List<String>)lore3);
            itemStack3.setItemMeta(itemMeta3);
            player.getInventory().setItem(4, itemStack3);
        }
    }
    
    public static void miniTeams(final Player player) {
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 9, ChatColor.stripColor(Messages.getMsg().getString("mini-team").replace('&', '§')) + "!");
        for (final Player player2 : Bukkit.getOnlinePlayers()) {
            final ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
            final SkullMeta itemMeta = (SkullMeta)itemStack.getItemMeta();
            itemMeta.setOwner(player2.getName());
            itemMeta.setDisplayName("§7?l" + player2.getDisplayName());
            itemStack.setItemMeta((ItemMeta)itemMeta);
            if (!player2.getName().equalsIgnoreCase(player.getName())) {
                inventory.addItem(new ItemStack[] { itemStack });
            }
        }
        player.openInventory(inventory);
    }
    
    public static void normalTeams(final Player player) {
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 45, "?l" + ChatColor.stripColor(Messages.getMsg().getString("normal-team").replace('&', '§')));
        final ItemStack itemStack = new ItemStack(Material.STAINED_CLAY, 1, (short)4);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Messages.getMsg().getString("join").replace("{team}", Messages.getMsg().getString("gold-team")).replace('&', '§'));
        final ArrayList<String> lore = new ArrayList<String>();
        final Iterator<String> iterator = Messages.getMsg().getStringList("team-lore").iterator();
        while (iterator.hasNext()) {
            lore.add(iterator.next().replace("{amount}", String.valueOf(Main.goldPlayers.size())).replace("{team}", Messages.getMsg().getString("gold-team")).replace('&', '§'));
        }
        itemMeta.setLore((List<String>)lore);
        itemStack.setItemMeta(itemMeta);
        final ItemStack itemStack2 = new ItemStack(Material.STAINED_CLAY, 1, (short)2);
        final ItemMeta itemMeta2 = itemStack2.getItemMeta();
        itemMeta2.setDisplayName(Messages.getMsg().getString("join").replace("{team}", Messages.getMsg().getString("magenta-team")).replace('&', '§'));
        final ArrayList<String> lore2 = new ArrayList<String>();
        final Iterator<String> iterator2 = Messages.getMsg().getStringList("team-lore").iterator();
        while (iterator2.hasNext()) {
            lore2.add(iterator2.next().replace("{amount}", String.valueOf(Main.magentaPlayers.size())).replace("{team}", Messages.getMsg().getString("magenta-team")).replace('&', '§'));
        }
        itemMeta2.setLore((List<String>)lore2);
        itemStack2.setItemMeta(itemMeta2);
        final ItemStack itemStack3 = new ItemStack(Material.STAINED_CLAY, 1, (short)5);
        final ItemMeta itemMeta3 = itemStack3.getItemMeta();
        itemMeta3.setDisplayName(Messages.getMsg().getString("join").replace("{team}", Messages.getMsg().getString("green-team")).replace('&', '§'));
        final ArrayList<String> lore3 = new ArrayList<String>();
        final Iterator<String> iterator3 = Messages.getMsg().getStringList("team-lore").iterator();
        while (iterator3.hasNext()) {
            lore3.add(iterator3.next().replace("{amount}", String.valueOf(Main.greenPlayers.size())).replace("{team}", Messages.getMsg().getString("green-team")).replace('&', '§'));
        }
        itemMeta3.setLore((List<String>)lore3);
        itemStack3.setItemMeta(itemMeta3);
        final ItemStack itemStack4 = new ItemStack(Material.STAINED_CLAY, 1, (short)11);
        final ItemMeta itemMeta4 = itemStack4.getItemMeta();
        itemMeta4.setDisplayName(Messages.getMsg().getString("join").replace("{team}", Messages.getMsg().getString("blue-team")).replace('&', '§'));
        final ArrayList<String> lore4 = new ArrayList<String>();
        final Iterator<String> iterator4 = Messages.getMsg().getStringList("team-lore").iterator();
        while (iterator4.hasNext()) {
            lore4.add(iterator4.next().replace("{amount}", String.valueOf(Main.bluePlayers.size())).replace("{team}", Messages.getMsg().getString("blue-team")).replace('&', '§'));
        }
        itemMeta4.setLore((List<String>)lore4);
        itemStack4.setItemMeta(itemMeta4);
        final ItemStack itemStack5 = new ItemStack(Material.MAGMA_CREAM);
        final ItemMeta itemMeta5 = itemStack5.getItemMeta();
        itemMeta5.setDisplayName(Messages.getMsg().getString("random-team").replace('&', '§'));
        final ArrayList<String> lore5 = new ArrayList<String>();
        final Iterator<String> iterator5 = Messages.getMsg().getStringList("random-lore").iterator();
        while (iterator5.hasNext()) {
            lore5.add(iterator5.next().replace('&', '§'));
        }
        itemMeta5.setLore((List<String>)lore5);
        itemStack5.setItemMeta(itemMeta5);
        inventory.setItem(10, itemStack);
        inventory.setItem(12, itemStack2);
        inventory.setItem(14, itemStack3);
        inventory.setItem(16, itemStack4);
        inventory.setItem(31, itemStack5);
        player.openInventory(inventory);
    }
}
