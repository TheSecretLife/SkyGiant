package src.TierCraft.Minigame.SkyGiant.plugin.utils;

import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.scheduler.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Messages;

import org.bukkit.plugin.*;

public class Titles
{
    @Deprecated
    public static void sendTitle(final Player player, final Integer n, final Integer n2, final Integer n3, final String s) {
        sendTitle(player, n, n2, n3, s, null);
    }
    
    public static void sendSubtitle(final Player player, final Integer n, final Integer n2, final Integer n3, final String s) {
        sendTitle(player, n, n2, n3, null, s);
    }
    
    public static void sendFullTitle(final Player player, final Integer n, final Integer n2, final Integer n3, final String s, final String s2) {
        sendTitle(player, n, n2, n3, s, s2);
    }
    
    @Deprecated
    public static Integer getPlayerProtocol(final Player player) {
        return 47;
    }
    
    public static void sendPacket(final Player player, final Object o) {
        try {
            final Object invoke = player.getClass().getMethod("getHandle", (Class<?>[])new Class[0]).invoke(player, new Object[0]);
            final Object value = invoke.getClass().getField("playerConnection").get(invoke);
            value.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(value, o);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static Class<?> getNMSClass(final String s) {
        final String s2 = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            return Class.forName("net.minecraft.server." + s2 + "." + s);
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static void sendTitle(final Player player, final Integer n, final Integer n2, final Integer n3, final String s, final String s2) {
        try {
            if (s != null) {
                sendPacket(player, getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null), getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + s + "\"}"), n, n2, n3));
            }
            if (s2 != null) {
                sendPacket(player, getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null), getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + s2 + "\"}"), n, n2, n3));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void fight(final Player player, final Integer n, final Integer n2, final Integer n3, final String s, final String s2) {
        try {
            if (s != null) {
                sendPacket(player, getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null), getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + s + "\"}"), n, n2, n3));
            }
            if (s2 != null) {
                sendPacket(player, getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null), getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + s2 + "\"}"), n, n2, n3));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void fightTitle(final Player player) {
        new BukkitRunnable() {
            int countdown = Messages.getMsg().getStringList("fight").size();
            int string = 0;
            
            public void run() {
                if (this.countdown != 0) {
                    --this.countdown;
                }
                Titles.sendTitle(player, 1, 5, 1, Messages.getMsg().getStringList("fight").get(this.string).replace('&', '§'), Messages.getMsg().getString("good-luck").replace("{player}", player.getName()).replace('&', '§'));
                ++this.string;
                if (this.countdown == 0) {
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)Main.plugin, 0L, 5L);
    }
}
