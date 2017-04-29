package src.TierCraft.Minigame.SkyGiant.plugin.runnables;

import org.bukkit.scheduler.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Messages;
import src.TierCraft.Minigame.SkyGiant.plugin.game.GameState;
import src.TierCraft.Minigame.SkyGiant.plugin.game.RandomTeam;
import src.TierCraft.Minigame.SkyGiant.plugin.game.Scoreboard;
import src.TierCraft.Minigame.SkyGiant.plugin.locations.Beasts;
import src.TierCraft.Minigame.SkyGiant.plugin.locations.Giants;
import src.TierCraft.Minigame.SkyGiant.plugin.locations.Middle;
import src.TierCraft.Minigame.SkyGiant.plugin.locations.Spawns;
import src.TierCraft.Minigame.SkyGiant.plugin.locations.Villagers;
import src.TierCraft.Minigame.SkyGiant.plugin.protocolLib.Borders;
import src.TierCraft.Minigame.SkyGiant.plugin.utils.Titles;

import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.enchantments.*;
import org.bukkit.plugin.*;
import java.util.*;

public class Lobby<K> extends BukkitRunnable
{

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void run() {
        if (Main.LobbyCountdown != 0) {
            --Main.LobbyCountdown;
        }
        final Iterator<? extends Player> iterator = Bukkit.getOnlinePlayers().iterator();
        while (iterator.hasNext()) {
            iterator.next().setLevel(Main.LobbyCountdown);
        }
        if (Main.LobbyCountdown == 10) {
            Object o = null;
            for (final Map.Entry<String, Integer> entry : Main.mapsVoting.entrySet()) {
                if (o == null || entry.getValue() > ((Map.Entry<K, Integer>)o).getValue()) {
                    o = entry;
                    Main.choosenMap = (String)((Map.Entry)o).getKey();
                }
            }
            Bukkit.getServer().createWorld(new WorldCreator(Main.choosenMap));
            Bukkit.getWorld(Main.choosenMap).setAutoSave(false);
            Bukkit.getWorld(Main.choosenMap).setGameRuleValue("keepInventory", "true");
            Bukkit.broadcastMessage(Main.PREFIX + Messages.getMsg().getString("voting-ended").replace("{choosenMap}", Main.choosenMap).replace('&', '§'));
            for (final Entity entity : Bukkit.getWorld(Main.choosenMap).getEntities()) {
                if (entity.getType() != EntityType.PLAYER) {
                    entity.remove();
                }
            }
        }
        if (Main.LobbyCountdown == 0) {
            this.cancel();
            for (final Player player : Bukkit.getOnlinePlayers()) {
                RandomTeam.randomTeam(player);
                if (Main.spectators.contains(player)) {
                    return;
                }
                if (Main.magentaPlayers.contains(player)) {
                    player.teleport(Spawns.getSpawn("Magenta"));
                    player.sendMessage(Main.PREFIX + Messages.getMsg().getString("play-magenta").replace('&', '§'));
                    player.setDisplayName("§d" + player.getName());
                    Titles.sendTitle(player, 0, 20, 0, " ", Messages.getMsg().getString("title-play-magenta").replace('&', '§'));
                    Borders.magentaBorders(player);
                }
                else if (Main.goldPlayers.contains(player)) {
                    player.teleport(Spawns.getSpawn("Gold"));
                    player.sendMessage(Main.PREFIX + Messages.getMsg().getString("play-gold").replace('&', '§'));
                    player.setDisplayName("§6" + player.getName());
                    Titles.sendTitle(player, 0, 20, 0, " ", Messages.getMsg().getString("title-play-gold").replace('&', '§'));
                    Borders.goldBorders(player);
                }
                else if (Main.greenPlayers.contains(player)) {
                    player.teleport(Spawns.getSpawn("Green"));
                    player.sendMessage(Main.PREFIX + Messages.getMsg().getString("play-green").replace('&', '§'));
                    player.setDisplayName("§a" + player.getName());
                    Titles.sendTitle(player, 0, 20, 0, " ", Messages.getMsg().getString("title-play-green").replace('&', '§'));
                    Borders.greenBorders(player);
                }
                else if (Main.bluePlayers.contains(player)) {
                    player.teleport(Spawns.getSpawn("Blue"));
                    player.sendMessage(Main.PREFIX + Messages.getMsg().getString("play-blue").replace('&', '§'));
                    player.setDisplayName("§9" + player.getName());
                    Titles.sendTitle(player, 0, 20, 0, " ", Messages.getMsg().getString("title-play-blue").replace('&', '§'));
                    Borders.blueBorders(player);
                }
                player.setGameMode(GameMode.SURVIVAL);
                player.getInventory().clear();
                player.getInventory().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
                player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
                player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
                player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.STONE_SWORD) });
                final ItemStack itemStack = new ItemStack(Material.IRON_PICKAXE);
                itemStack.addEnchantment(Enchantment.DIG_SPEED, 1);
                player.getInventory().addItem(new ItemStack[] { itemStack });
                final ItemStack itemStack2 = new ItemStack(Material.STONE_AXE);
                itemStack2.addEnchantment(Enchantment.DIG_SPEED, 1);
                player.getInventory().addItem(new ItemStack[] { itemStack2 });
                final ItemStack itemStack3 = new ItemStack(Material.STONE_SPADE);
                itemStack3.addEnchantment(Enchantment.DIG_SPEED, 1);
                player.getInventory().addItem(new ItemStack[] { itemStack3 });
                player.setHealth(20.0);
                player.setFoodLevel(20);
                final Iterator<String> iterator5 = Messages.getMsg().getStringList("your-objective").iterator();
                while (iterator5.hasNext()) {
                    player.sendMessage(Main.PREFIX + iterator5.next().replace('&', '§'));
                }
            }
            Middle.loadMidRegion();
            Beasts.loadBeastRegion();
            Giants.loadGiantsRegions();
            Villagers.loadVillagersRegions();
            Main.nmsH.spawnGiants();
            Main.nmsH.spawnVillagers();
            Scoreboard.sendScoreboard();
            new Warmup().runTaskTimer((Plugin)Main.plugin, 20L, 20L);
            Main.STATUS = GameState.PLAYING;
        }
    }
    
    static {
    }
}
