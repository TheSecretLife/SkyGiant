package src.TierCraft.Minigame.SkyGiant.plugin.listeners;

import org.bukkit.event.entity.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;

import org.bukkit.block.*;
import org.bukkit.event.*;

public class EntityExplodeListener implements Listener
{
    @EventHandler
    public void explode(final EntityExplodeEvent entityExplodeEvent) {
        for (final Block block : entityExplodeEvent.blockList()) {
            if (Main.GoldGiantRegion.isInRegion(block.getLocation())) {
                entityExplodeEvent.setCancelled(true);
            }
            else if (Main.MagentaGiantRegion.isInRegion(block.getLocation())) {
                entityExplodeEvent.setCancelled(true);
            }
            else if (Main.GreenGiantRegion.isInRegion(block.getLocation())) {
                entityExplodeEvent.setCancelled(true);
            }
            else if (Main.BlueGiantRegion.isInRegion(block.getLocation())) {
                entityExplodeEvent.setCancelled(true);
            }
            else if (Main.GoldVillagerRegion.isInRegion(block.getLocation())) {
                entityExplodeEvent.setCancelled(true);
            }
            else if (Main.MagentaVillagerRegion.isInRegion(block.getLocation())) {
                entityExplodeEvent.setCancelled(true);
            }
            else if (Main.GreenVillagerRegion.isInRegion(block.getLocation())) {
                entityExplodeEvent.setCancelled(true);
            }
            else if (Main.BlueVillagerRegion.isInRegion(block.getLocation())) {
                entityExplodeEvent.setCancelled(true);
            }
            else if (Main.BeastRegion.isInRegion(block.getLocation())) {
                entityExplodeEvent.setCancelled(true);
            }
            else {
                if (!Main.MiddleRegion.isInRegion(block.getLocation())) {
                    continue;
                }
                entityExplodeEvent.setCancelled(true);
            }
        }
    }
}
