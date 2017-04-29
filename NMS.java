package src.TierCraft.Minigame.SkyGiant.plugin;

import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.potion.*;

public interface NMS
{
    void registerGiant();
    
    void spawnGiants();
    
    String gGH(final String p0);
    
    int getGiantHealthInt(final String p0);
    
    void killGiant(final String p0);
    
    void actionMsg(final Player p0, final String p1);
    
    void spawnVillagers();
    
    void registerVillager();
    
    void spawnBeast();
    
    void registerBeast();
    
    Sound catMeow();
    
    Sound eggPop();
    
    Sound lvlUp();
    
    Sound anvil();
    
    Sound villager();
    
    Sound splash();
    
    Sound giantHurt();
    
    ItemStack getItm(final Player p0);
    
    ItemStack potion(final PotionType p0);
    
    void transparent(final Player p0);
    
    Boolean getboats();
}
