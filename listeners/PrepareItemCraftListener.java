package src.TierCraft.Minigame.SkyGiant.plugin.listeners;

import org.bukkit.event.inventory.*;
import org.bukkit.*;
import org.bukkit.inventory.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;

import org.bukkit.event.*;

public class PrepareItemCraftListener implements Listener
{
    @EventHandler
    public void craft(final PrepareItemCraftEvent prepareItemCraftEvent) {
        if (Main.nmsH.getboats()) {
            if (prepareItemCraftEvent.getRecipe().getResult().getType() == Material.BOAT) {
                prepareItemCraftEvent.getInventory().setResult(new ItemStack(Material.AIR));
            }
        }
        else if (prepareItemCraftEvent.getRecipe().getResult().getType() == Material.BOAT) {
            prepareItemCraftEvent.getInventory().setResult(new ItemStack(Material.AIR));
        }
    }
}
