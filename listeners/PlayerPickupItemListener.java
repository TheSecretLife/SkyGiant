package src.TierCraft.Minigame.SkyGiant.plugin.listeners;

import org.bukkit.event.player.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;

import org.bukkit.event.*;

public class PlayerPickupItemListener implements Listener
{
    @EventHandler
    public void pickup(final PlayerPickupItemEvent playerPickupItemEvent) {
        if (Main.spectators.contains(playerPickupItemEvent.getPlayer())) {
            playerPickupItemEvent.setCancelled(true);
        }
    }
}
