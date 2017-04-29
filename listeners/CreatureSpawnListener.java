package src.TierCraft.Minigame.SkyGiant.plugin.listeners;

import org.bukkit.event.entity.*;
import org.bukkit.event.*;

public class CreatureSpawnListener implements Listener
{
    @EventHandler
    public void spawn(final CreatureSpawnEvent creatureSpawnEvent) {
        if (creatureSpawnEvent.getSpawnReason() != CreatureSpawnEvent.SpawnReason.CUSTOM) {
            creatureSpawnEvent.setCancelled(true);
        }
    }
}
