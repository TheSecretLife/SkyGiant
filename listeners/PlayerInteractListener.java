package src.TierCraft.Minigame.SkyGiant.plugin.listeners;

import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;
import org.bukkit.inventory.meta.*;
import com.google.common.io.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Messages;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Settings;
import src.TierCraft.Minigame.SkyGiant.plugin.game.GameState;

import java.util.*;
import org.bukkit.event.*;

public class PlayerInteractListener implements Listener
{
    public static HashMap<Location, Inventory> chest;
    
    @EventHandler
    public void interact(final PlayerInteractEvent playerInteractEvent) {
        if (playerInteractEvent.getAction() == Action.RIGHT_CLICK_BLOCK && playerInteractEvent.getClickedBlock().getType() == Material.CHEST) {
            playerInteractEvent.setCancelled(true);
            if (Main.spectators.contains(playerInteractEvent.getPlayer())) {
                return;
            }
            if (Main.STATUS == GameState.PLAYING) {
                if (PlayerInteractListener.chest.containsKey(playerInteractEvent.getClickedBlock().getLocation())) {
                    playerInteractEvent.getPlayer().openInventory((Inventory)PlayerInteractListener.chest.get(playerInteractEvent.getClickedBlock().getLocation()));
                }
                else {
                    int i = new Random().nextInt(6);
                    if (i < 3) {
                        i = 3;
                    }
                    final Inventory inventory = Bukkit.createInventory((InventoryHolder)playerInteractEvent.getPlayer(), InventoryType.CHEST);
                    final ArrayList<ItemStack> list = new ArrayList<ItemStack>();
                    list.add(new ItemStack(Material.GOLD_AXE));
                    list.add(new ItemStack(Material.STRING, 2));
                    list.add(new ItemStack(Material.STICK, 4));
                    list.add(new ItemStack(Material.STONE_SPADE));
                    list.add(new ItemStack(Material.IRON_HELMET));
                    list.add(new ItemStack(Material.IRON_SWORD));
                    list.add(new ItemStack(Material.WATER_BUCKET));
                    list.add(new ItemStack(Material.GOLD_BOOTS));
                    list.add(new ItemStack(Material.GOLDEN_APPLE));
                    list.add(new ItemStack(Material.STONE_SWORD));
                    list.add(new ItemStack(Material.CHAINMAIL_HELMET));
                    list.add(new ItemStack(Material.COOKED_BEEF));
                    list.add(new ItemStack(Material.CARROT, 2));
                    list.add(new ItemStack(Material.IRON_PICKAXE));
                    list.add(new ItemStack(Material.IRON_BOOTS));
                    list.add(new ItemStack(Material.GOLD_HELMET));
                    list.add(new ItemStack(Material.IRON_AXE));
                    list.add(new ItemStack(Material.FEATHER, 3));
                    list.add(new ItemStack(Material.RAW_FISH));
                    list.add(new ItemStack(Material.TORCH, 3));
                    list.add(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                    list.add(new ItemStack(Material.COOKED_RABBIT));
                    list.add(new ItemStack(Material.DIAMOND_SPADE));
                    list.add(new ItemStack(Material.IRON_SPADE));
                    list.add(new ItemStack(Material.STONE_AXE));
                    list.add(new ItemStack(Material.GOLD_CHESTPLATE));
                    list.add(new ItemStack(Material.COOKED_BEEF));
                    list.add(new ItemStack(Material.LAVA_BUCKET));
                    list.add(new ItemStack(Material.IRON_CHESTPLATE));
                    list.add(new ItemStack(Material.IRON_LEGGINGS));
                    list.add(new ItemStack(Material.LEATHER_CHESTPLATE));
                    list.add(new ItemStack(Material.WOOD, 12));
                    list.add(new ItemStack(Material.DIAMOND_PICKAXE));
                    list.add(new ItemStack(Material.COOKED_CHICKEN));
                    list.add(new ItemStack(Material.WEB));
                    while (i != 0) {
                        --i;
                        final Random random = new Random();
                        inventory.setItem(random.nextInt(27), (ItemStack)list.get(random.nextInt(list.size())));
                    }
                    PlayerInteractListener.chest.put(playerInteractEvent.getClickedBlock().getLocation(), inventory);
                    playerInteractEvent.getPlayer().openInventory((Inventory)PlayerInteractListener.chest.get(playerInteractEvent.getClickedBlock().getLocation()));
                }
            }
        }
        if (playerInteractEvent.getAction() == Action.RIGHT_CLICK_BLOCK && playerInteractEvent.getClickedBlock().getType() == Material.WORKBENCH && Main.spectators.contains(playerInteractEvent.getPlayer())) {
            playerInteractEvent.setCancelled(true);
        }
        if (playerInteractEvent.getAction() == Action.RIGHT_CLICK_BLOCK && playerInteractEvent.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE && Main.spectators.contains(playerInteractEvent.getPlayer())) {
            playerInteractEvent.setCancelled(true);
        }
        if (playerInteractEvent.getAction() == Action.RIGHT_CLICK_BLOCK && playerInteractEvent.getClickedBlock().getType() == Material.ENDER_CHEST && Main.spectators.contains(playerInteractEvent.getPlayer())) {
            playerInteractEvent.setCancelled(true);
        }
        if (playerInteractEvent.getAction() == Action.RIGHT_CLICK_BLOCK && playerInteractEvent.getClickedBlock().getType() == Material.ANVIL && Main.spectators.contains(playerInteractEvent.getPlayer())) {
            playerInteractEvent.setCancelled(true);
        }
        if (playerInteractEvent.getAction() == Action.LEFT_CLICK_BLOCK && playerInteractEvent.getClickedBlock().getType() == Material.SKULL && Main.respBeac.containsKey(playerInteractEvent.getClickedBlock().getLocation())) {
            playerInteractEvent.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("beacon-owner").replace("{player}", Main.respBeac.get(playerInteractEvent.getClickedBlock().getLocation()).getName()).replace('&', '?'));
        }
        if (playerInteractEvent.getAction() == Action.RIGHT_CLICK_AIR || playerInteractEvent.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (Main.nmsH.getItm(playerInteractEvent.getPlayer()).getType() == Material.SLIME_BALL && Main.nmsH.getItm(playerInteractEvent.getPlayer()).getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("back-to-hub.name").replace('&', '?'))) {
                final ByteArrayDataOutput dataOutput = ByteStreams.newDataOutput();
                dataOutput.writeUTF("Connect");
                dataOutput.writeUTF(Settings.getCfg().getString("lobby-server"));
                playerInteractEvent.getPlayer().sendPluginMessage((Plugin)Main.plugin, "BungeeCord", dataOutput.toByteArray());
            }
            if (Main.nmsH.getItm(playerInteractEvent.getPlayer()).getType() == Material.COMPASS && Main.nmsH.getItm(playerInteractEvent.getPlayer()).getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("spectator-selector.name").replace('&', '?'))) {
                final Inventory inventory2 = Bukkit.createInventory((InventoryHolder)null, 18, Messages.getMsg().getString("who-to-spectate").replace('&', '?'));
                for (final Player player : Main.players) {
                    final ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                    final SkullMeta itemMeta = (SkullMeta)itemStack.getItemMeta();
                    itemMeta.setOwner(player.getName());
                    itemMeta.setDisplayName(player.getName());
                    itemStack.setItemMeta((ItemMeta)itemMeta);
                    inventory2.addItem(new ItemStack[] { itemStack });
                }
                playerInteractEvent.getPlayer().openInventory(inventory2);
            }
            if (Main.nmsH.getItm(playerInteractEvent.getPlayer()).getType() == Material.SKULL_ITEM && Main.nmsH.getItm(playerInteractEvent.getPlayer()).getItemMeta().getDisplayName() != null && Main.nmsH.getItm(playerInteractEvent.getPlayer()).getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("mini-team").replace('&', '?'))) {
                if (Main.hasTeam.contains(playerInteractEvent.getPlayer())) {
                    playerInteractEvent.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("already-in-team").replace('&', '?'));
                    return;
                }
                PlayerJoinListener.miniTeams(playerInteractEvent.getPlayer());
            }
            if (Main.nmsH.getItm(playerInteractEvent.getPlayer()).getType() == Material.STAINED_CLAY && (Main.STATUS == GameState.STARTING || Main.STATUS == GameState.LOBBY) && Main.nmsH.getItm(playerInteractEvent.getPlayer()).getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("normal-team").replace('&', '?'))) {
                PlayerJoinListener.normalTeams(playerInteractEvent.getPlayer());
            }
        }
    }
    
    static {
        PlayerInteractListener.chest = new HashMap<Location, Inventory>();
    }
}
