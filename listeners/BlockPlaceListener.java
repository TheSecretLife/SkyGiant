package src.TierCraft.Minigame.SkyGiant.plugin.listeners;

import org.bukkit.event.block.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Messages;
import src.TierCraft.Minigame.SkyGiant.plugin.game.GameState;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class BlockPlaceListener implements Listener
{
    @EventHandler
    public void place(final BlockPlaceEvent blockPlaceEvent) {
        if (Main.MAINTENANCE) {
            return;
        }
        if (Main.STATUS != GameState.PLAYING && !blockPlaceEvent.getPlayer().isOp()) {
            blockPlaceEvent.setCancelled(true);
        }
        if (Main.respawning.contains(blockPlaceEvent.getPlayer())) {
            blockPlaceEvent.setCancelled(true);
            return;
        }
        if (blockPlaceEvent.getItemInHand().getType() == Material.SKULL_ITEM) {
            if (blockPlaceEvent.getItemInHand().getItemMeta().getDisplayName() == null) {
                return;
            }
            if (blockPlaceEvent.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("shop.respawn-beacon"))) {
                if (Main.respBeac.containsValue(blockPlaceEvent.getPlayer())) {
                    blockPlaceEvent.setCancelled(true);
                }
                else {
                    Main.respBeac.put(blockPlaceEvent.getBlock().getLocation(), blockPlaceEvent.getPlayer());
                    Main.respOwn.put(blockPlaceEvent.getPlayer(), blockPlaceEvent.getBlock().getLocation());
                }
            }
        }
        if (Main.GoldGiantRegion.isInRegion(blockPlaceEvent.getBlock().getLocation()) || Main.MagentaGiantRegion.isInRegion(blockPlaceEvent.getBlock().getLocation()) || Main.GreenGiantRegion.isInRegion(blockPlaceEvent.getBlock().getLocation()) || Main.BlueGiantRegion.isInRegion(blockPlaceEvent.getBlock().getLocation()) || Main.GoldVillagerRegion.isInRegion(blockPlaceEvent.getBlock().getLocation()) || Main.MagentaVillagerRegion.isInRegion(blockPlaceEvent.getBlock().getLocation()) || Main.GreenVillagerRegion.isInRegion(blockPlaceEvent.getBlock().getLocation()) || Main.BlueVillagerRegion.isInRegion(blockPlaceEvent.getBlock().getLocation()) || Main.MiddleRegion.isInRegion(blockPlaceEvent.getBlock().getLocation())) {
            Main.PlacedBlocks.add(blockPlaceEvent.getBlock().getLocation());
        }
        if (Main.BeastRegion.isInRegion(blockPlaceEvent.getBlock().getLocation())) {
            blockPlaceEvent.setCancelled(true);
            blockPlaceEvent.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("scared-region").replace('&', '?'));
        }
        if (blockPlaceEvent.getBlock().getType() == Material.TNT) {
            blockPlaceEvent.getBlock().getWorld().spawnEntity(blockPlaceEvent.getBlock().getLocation(), EntityType.PRIMED_TNT);
            blockPlaceEvent.getBlock().setType(Material.AIR);
        }
    }
}
