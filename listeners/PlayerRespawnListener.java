package src.TierCraft.Minigame.SkyGiant.plugin.listeners;

import org.bukkit.event.player.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.locations.Spectate;

import org.bukkit.event.*;

public class PlayerRespawnListener implements Listener
{
    @EventHandler
    public void respawn(final PlayerRespawnEvent playerRespawnEvent) {
        if (Main.MAINTENANCE) {
            return;
        }
        if (Main.spectators.contains(playerRespawnEvent.getPlayer())) {
            playerRespawnEvent.getPlayer().teleport(Spectate.getSpect(Main.choosenMap));
            return;
        }
        playerRespawnEvent.setRespawnLocation(Spectate.getSpect(Main.choosenMap));
        playerRespawnEvent.getPlayer().setAllowFlight(true);
        playerRespawnEvent.getPlayer().setFlying(true);
    }
}
