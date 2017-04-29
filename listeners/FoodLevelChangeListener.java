package src.TierCraft.Minigame.SkyGiant.plugin.listeners;

import org.bukkit.event.entity.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.game.GameState;

import org.bukkit.event.*;

public class FoodLevelChangeListener implements Listener
{
    @EventHandler
    public void food(final FoodLevelChangeEvent foodLevelChangeEvent) {
        if (Main.STATUS != GameState.PLAYING) {
            foodLevelChangeEvent.setCancelled(true);
        }
        if (Main.spectators.contains(foodLevelChangeEvent.getEntity())) {
            foodLevelChangeEvent.setCancelled(true);
        }
    }
}
