package src.TierCraft.Minigame.SkyGiant.plugin.listeners;

import org.bukkit.event.entity.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.game.GameState;

import org.bukkit.entity.*;
import org.bukkit.event.*;

public class EntityDamageListener implements Listener
{
    @EventHandler
    public void damage(final EntityDamageEvent entityDamageEvent) {
        if (Main.MAINTENANCE) {
            return;
        }
        if (Main.STATUS != GameState.PLAYING) {
            entityDamageEvent.setCancelled(true);
        }
        if (Main.spectators.contains(entityDamageEvent.getEntity())) {
            entityDamageEvent.setCancelled(true);
        }
        if (entityDamageEvent.getEntity() instanceof Giant && entityDamageEvent.getCause() != EntityDamageEvent.DamageCause.PROJECTILE && entityDamageEvent.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            entityDamageEvent.setCancelled(true);
        }
    }
}
