package src.TierCraft.Minigame.SkyGiant.plugin.listeners;

import org.bukkit.event.*;
import org.bukkit.event.player.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Messages;

public class PlayerMoveListener implements Listener
{
    public void move(final PlayerMoveEvent playerMoveEvent) {
        if (Main.recall.contains(playerMoveEvent.getPlayer())) {
            Main.recall.remove(playerMoveEvent.getPlayer());
            playerMoveEvent.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("recall-cancelled").replace('&', '?'));
        }
    }
}
