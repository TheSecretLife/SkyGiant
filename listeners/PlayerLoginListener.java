package src.TierCraft.Minigame.SkyGiant.plugin.listeners;

import org.bukkit.event.player.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.game.GameState;

import org.bukkit.*;
import org.bukkit.event.*;

public class PlayerLoginListener implements Listener
{
    @EventHandler
    public void login(final PlayerLoginEvent playerLoginEvent) {
        if (!Main.loaded) {
            playerLoginEvent.disallow(PlayerLoginEvent.Result.KICK_OTHER, "?6Loading. . .");
            return;
        }
        if (playerLoginEvent.getResult() == PlayerLoginEvent.Result.KICK_FULL) {
            playerLoginEvent.allow();
        }
        if (Main.miniSG) {
            if (Bukkit.getOnlinePlayers().size() >= 8) {
                if (Main.STATUS == GameState.LOBBY || Main.STATUS == GameState.STARTING) {
                    playerLoginEvent.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Full");
                }
                else if (!Main.allowSpectate) {
                    playerLoginEvent.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Full");
                }
            }
        }
        else if (Bukkit.getOnlinePlayers().size() >= 16) {
            if (Main.STATUS == GameState.LOBBY || Main.STATUS == GameState.STARTING) {
                playerLoginEvent.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Full");
            }
            else if (!Main.allowSpectate) {
                playerLoginEvent.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Full");
            }
        }
    }
}
