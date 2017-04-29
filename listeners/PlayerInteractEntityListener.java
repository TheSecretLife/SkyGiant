package src.TierCraft.Minigame.SkyGiant.plugin.listeners;

import org.bukkit.event.player.*;
import org.bukkit.entity.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Messages;
import src.TierCraft.Minigame.SkyGiant.plugin.game.Shop;

import org.bukkit.event.*;

public class PlayerInteractEntityListener implements Listener
{
    @EventHandler
    public void shop(final PlayerInteractEntityEvent playerInteractEntityEvent) {
        if (playerInteractEntityEvent.getRightClicked().getType() == EntityType.VILLAGER && ((Villager)playerInteractEntityEvent.getRightClicked()).getCustomName().equalsIgnoreCase(Messages.getMsg().getString("merchant").replace('&', '?'))) {
            if (Main.spectators.contains(playerInteractEntityEvent.getPlayer())) {
                playerInteractEntityEvent.setCancelled(true);
                return;
            }
            playerInteractEntityEvent.setCancelled(true);
            playerInteractEntityEvent.getPlayer().openInventory(Shop.mainShop());
        }
    }
}
