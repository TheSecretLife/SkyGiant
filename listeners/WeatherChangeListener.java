package src.TierCraft.Minigame.SkyGiant.plugin.listeners;

import org.bukkit.event.weather.*;
import org.bukkit.event.*;

public class WeatherChangeListener implements Listener
{
    @EventHandler
    public void change(final WeatherChangeEvent weatherChangeEvent) {
        weatherChangeEvent.setCancelled(true);
    }
}
