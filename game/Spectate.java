package src.TierCraft.Minigame.SkyGiant.plugin.game;

import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.plugin.*;
import org.bukkit.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Messages;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Settings;

import java.util.*;
import org.bukkit.inventory.meta.*;

public class Spectate
{
    public static void setSpectator(final Player player) {
        if (Settings.getCfg().getBoolean("gamemode-spectator")) {
            player.getInventory().clear();
            player.getInventory().setArmorContents((ItemStack[])null);
            player.setGameMode(GameMode.SPECTATOR);
            Main.spectators.add(player);
            Main.players.remove(player);
            Main.magentaPlayers.remove(player);
            Main.goldPlayers.contains(player);
            Main.bluePlayers.remove(player);
            Main.greenPlayers.remove(player);
        }
        else {
            player.setLevel(0);
            player.setGameMode(GameMode.ADVENTURE);
            player.setAllowFlight(true);
            player.setFlying(true);
            player.getInventory().setArmorContents((ItemStack[])null);
            player.getInventory().clear();
            player.setHealthScale(20.0);
            final Iterator<Player> iterator = Main.players.iterator();
            while (iterator.hasNext()) {
                iterator.next().hidePlayer(player);
            }
            Main.spectators.add(player);
            Main.players.remove(player);
            Main.magentaPlayers.remove(player);
            Main.goldPlayers.contains(player);
            Main.bluePlayers.remove(player);
            Main.greenPlayers.remove(player);
            Main.nmsH.transparent(player);
            Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
                @Override
                public void run() {
                    player.teleport(src.TierCraft.Minigame.SkyGiant.plugin.locations.Spectate.getSpect(Main.choosenMap));
                    spectateItems(player);
                }
            }, 2L);
        }
        player.setScoreboard(Main.sb);
    }
    
    private static void spectateItems(final Player player) {
        final ItemStack itemStack = new ItemStack(Material.SLIME_BALL);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Messages.getMsg().getString("back-to-hub.name").replace('&', '?'));
        final ArrayList<String> lore = new ArrayList<String>();
        final Iterator<String> iterator = Messages.getMsg().getStringList("back-to-hub.lore").iterator();
        while (iterator.hasNext()) {
            lore.add(iterator.next().replace('&', '?'));
        }
        itemMeta.setLore((List<String>)lore);
        itemStack.setItemMeta(itemMeta);
        player.getInventory().setItem(8, itemStack);
        final ItemStack itemStack2 = new ItemStack(Material.COMPASS);
        final ItemMeta itemMeta2 = itemStack2.getItemMeta();
        itemMeta2.setDisplayName(Messages.getMsg().getString("spectator-selector.name").replace('&', '?'));
        final ArrayList<String> lore2 = new ArrayList<String>();
        final Iterator<String> iterator2 = Messages.getMsg().getStringList("spectator-selector.lore").iterator();
        while (iterator2.hasNext()) {
            lore2.add(iterator2.next().replace('&', '?'));
        }
        itemMeta2.setLore((List<String>)lore2);
        itemStack2.setItemMeta(itemMeta2);
        player.getInventory().setItem(0, itemStack2);
    }
}
