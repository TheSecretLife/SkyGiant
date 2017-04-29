package src.TierCraft.Minigame.SkyGiant.plugin.nms.v1_8_R3;

import src.TierCraft.Minigame.SkyGiant.plugin.*;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.*;
import org.bukkit.entity.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.*;
import java.util.*;
import src.TierCraft.Minigame.SkyGiant.plugin.locations.*;
import src.TierCraft.Minigame.SkyGiant.plugin.nms.v1_8_R3.beasts.*;
import net.minecraft.server.v1_8_R3.*;
import net.minecraft.server.v1_8_R3.Entity;

import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.*;

public class Main implements NMS
{
    public static GiantNMS GoldGiant;
    public static GiantNMS MagentaGiant;
    public static GiantNMS GreenGiant;
    public static GiantNMS BlueGiant;
    
    @Override
    public void registerGiant() {
        new RegisterEntity().registerEntity("Giant", 53, EntityGiantZombie.class, GiantNMS.class);
    }
    
    @Override
    public void spawnGiants() {
        if (!src.TierCraft.Minigame.SkyGiant.plugin.Main.magentaPlayers.isEmpty()) {
            GiantNMS.spawnMagentaGiant(Giants.getGiant("Magenta"));
        }
        if (!src.TierCraft.Minigame.SkyGiant.plugin.Main.goldPlayers.isEmpty()) {
            GiantNMS.spawnOrangeGiant(Giants.getGiant("Gold"));
        }
        if (!src.TierCraft.Minigame.SkyGiant.plugin.Main.greenPlayers.isEmpty()) {
            GiantNMS.spawnGreenGiant(Giants.getGiant("Green"));
        }
        if (!src.TierCraft.Minigame.SkyGiant.plugin.Main.bluePlayers.isEmpty()) {
            GiantNMS.spawnBlueGiant(Giants.getGiant("Blue"));
        }
    }
    
    @Override
    public String gGH(final String s) {
        if (s.equalsIgnoreCase("Gold")) {
            if (!src.TierCraft.Minigame.SkyGiant.plugin.Main.aliveTeams.contains("GOLD")) {
                return Messages.getMsg().getString("scoreboard.dead").replace('&', '?');
            }
            return getGiantHealth(Main.GoldGiant);
        }
        else if (s.equalsIgnoreCase("Magenta")) {
            if (!src.TierCraft.Minigame.SkyGiant.plugin.Main.aliveTeams.contains("MAGENTA")) {
                return Messages.getMsg().getString("scoreboard.dead").replace('&', '?');
            }
            return getGiantHealth(Main.MagentaGiant);
        }
        else if (s.equalsIgnoreCase("Green")) {
            if (!src.TierCraft.Minigame.SkyGiant.plugin.Main.aliveTeams.contains("GREEN")) {
                return Messages.getMsg().getString("scoreboard.dead").replace('&', '?');
            }
            return getGiantHealth(Main.GreenGiant);
        }
        else {
            if (!s.equalsIgnoreCase("Blue")) {
                return Messages.getMsg().getString("scoreboard.dead").replace('&', '?');
            }
            if (!src.TierCraft.Minigame.SkyGiant.plugin.Main.aliveTeams.contains("BLUE")) {
                return Messages.getMsg().getString("scoreboard.dead").replace('&', '?');
            }
            return getGiantHealth(Main.BlueGiant);
        }
    }
    
    private static String getGiantHealth(final GiantNMS giantNMS) {
        return String.valueOf((int)(giantNMS.getHealth() * 100.0 / src.TierCraft.Minigame.SkyGiant.plugin.Main.GiantHealth) + "%");
    }
    
    @Override
    public int getGiantHealthInt(final String s) {
        if (s.equalsIgnoreCase("Gold")) {
            return (int)Main.GoldGiant.getHealth();
        }
        if (s.equalsIgnoreCase("Magenta")) {
            return (int)Main.MagentaGiant.getHealth();
        }
        if (s.equalsIgnoreCase("Green")) {
            return (int)Main.GreenGiant.getHealth();
        }
        if (s.equalsIgnoreCase("Blue")) {
            return (int)Main.BlueGiant.getHealth();
        }
        return 0;
    }
    
    @Override
    public void killGiant(final String s) {
        if (s.equalsIgnoreCase("Gold")) {
            for (final org.bukkit.entity.Entity entity : Bukkit.getWorld(src.TierCraft.Minigame.SkyGiant.plugin.Main.choosenMap).getEntities()) {
                if (entity instanceof Giant && src.TierCraft.Minigame.SkyGiant.plugin.Main.GoldGiantRegion.isInRegion(entity.getLocation())) {
                    ((Giant)entity).setHealth(0.0);
                }
            }
            src.TierCraft.Minigame.SkyGiant.plugin.Main.aliveTeams.remove("GOLD");
        }
        else if (s.equalsIgnoreCase("Magenta")) {
            for (final org.bukkit.entity.Entity entity2 : Bukkit.getWorld(src.TierCraft.Minigame.SkyGiant.plugin.Main.choosenMap).getEntities()) {
                if (entity2 instanceof Giant && src.TierCraft.Minigame.SkyGiant.plugin.Main.MagentaGiantRegion.isInRegion(entity2.getLocation())) {
                    ((Giant)entity2).setHealth(0.0);
                }
            }
            src.TierCraft.Minigame.SkyGiant.plugin.Main.aliveTeams.remove("MAGENTA");
        }
        else if (s.equalsIgnoreCase("Green")) {
            for (final org.bukkit.entity.Entity entity3 : Bukkit.getWorld(src.TierCraft.Minigame.SkyGiant.plugin.Main.choosenMap).getEntities()) {
                if (entity3 instanceof Giant && src.TierCraft.Minigame.SkyGiant.plugin.Main.GreenGiantRegion.isInRegion(entity3.getLocation())) {
                    ((Giant)entity3).setHealth(0.0);
                }
            }
            src.TierCraft.Minigame.SkyGiant.plugin.Main.aliveTeams.remove("GREEN");
        }
        else if (s.equalsIgnoreCase("Blue")) {
            for (final org.bukkit.entity.Entity entity4 : Bukkit.getWorld(src.TierCraft.Minigame.SkyGiant.plugin.Main.choosenMap).getEntities()) {
                if (entity4 instanceof Giant && src.TierCraft.Minigame.SkyGiant.plugin.Main.BlueGiantRegion.isInRegion(entity4.getLocation())) {
                    ((Giant)entity4).setHealth(0.0);
                }
            }
            src.TierCraft.Minigame.SkyGiant.plugin.Main.aliveTeams.remove("BLUE");
        }
    }
    
    @Override
    public void actionMsg(final Player player, final String s) {
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packe<?>)new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', s) + "\"}"), (byte)2));
    }
    
    @Override
    public void spawnVillagers() {
        if (!src.TierCraft.Minigame.SkyGiant.plugin.Main.magentaPlayers.isEmpty()) {
            VillagerNMS.spawnMagentaVillager(Villagers.getVillager("Magenta"));
        }
        if (!src.TierCraft.Minigame.SkyGiant.plugin.Main.goldPlayers.isEmpty()) {
            VillagerNMS.spawnGoldVillager(Villagers.getVillager("Gold"));
        }
        if (!src.TierCraft.Minigame.SkyGiant.plugin.Main.greenPlayers.isEmpty()) {
            VillagerNMS.spawnGreenVillager(Villagers.getVillager("Green"));
        }
        if (!src.TierCraft.Minigame.SkyGiant.plugin.Main.bluePlayers.isEmpty()) {
            VillagerNMS.spawnBlueVillager(Villagers.getVillager("Blue"));
        }
    }
    
    @Override
    public void registerVillager() {
        new RegisterEntity().registerEntity("Villager", 120, EntityVillager.class, VillagerNMS.class);
    }
    
    @Override
    public void spawnBeast() {
        switch (new Random().nextInt(2) + 1) {
            case 1: {
                GuardianNMS.spawnGuardian(Beasts.getBeastSpawn());
                break;
            }
            case 2: {
                HorseNMS.spawnHorse(Beasts.getBeastSpawn());
                break;
            }
        }
    }
    
    @Override
    public void registerBeast() {
        final RegisterEntity registerEntity = new RegisterEntity();
        registerEntity.registerEntity("Guardian", 68, EntityGuardian.class, GuardianNMS.class);
        registerEntity.registerEntity("IronGolem", 99, EntityIronGolem.class, GolemNMS.class);
        registerEntity.registerEntity("Horse", 100, EntityHorse.class, HorseNMS.class);
    }
    
    @Override
    public Sound catMeow() {
        return Sound.valueOf("CAT_MEOW");
    }
    
    @Override
    public Sound eggPop() {
        return Sound.valueOf("CHICKEN_EGG_POP");
    }
    
    @Override
    public Sound lvlUp() {
        return Sound.valueOf("LEVEL_UP");
    }
    
    @Override
    public Sound anvil() {
        return Sound.valueOf("ANVIL_LAND");
    }
    
    @Override
    public Sound villager() {
        return Sound.valueOf("VILLAGER_HAGGLE");
    }
    
    @Override
    public Sound splash() {
        return Sound.valueOf("SPLASH2");
    }
    
    @Override
    public Sound giantHurt() {
        return Sound.valueOf("ZOMBIE_PIG_HURT");
    }
    
    @Override
    public ItemStack getItm1(final Player player) {
        return player.getItemInHand();
    }
    
    @Override
    public ItemStack potion1(final PotionType potionType) {
        return new Potion(potionType, 1).toItemStack(1);
    }
    
    @Override
    public void transparent(final Player player) {
        player.spigot().setCollidesWithEntities(false);
    }
    
    @Override
    public Boolean getboats() {
        return false;
    }

	@Override
	public ItemStack getItm(Player p0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack potion(PotionType p0) {
		// TODO Auto-generated method stub
		return null;
	}
}
