package src.TierCraft.Minigame.SkyGiant.plugin.listeners;

import org.bukkit.event.player.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.game.GameState;

import org.bukkit.event.*;

public class PlayerDropItemListener implements Listener
{
    @EventHandler
    public void drop(final PlayerDropItemEvent playerDropItemEvent) {
        if (Main.spectators.contains(playerDropItemEvent.getPlayer())) {
            playerDropItemEvent.setCancelled(true);
        }
        if (Main.STATUS == GameState.STARTING || Main.STATUS == GameState.LOBBY) {
            playerDropItemEvent.setCancelled(true);
        }
    }
}
