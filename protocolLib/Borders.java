package src.TierCraft.Minigame.SkyGiant.plugin.protocolLib;

import org.bukkit.entity.*;
import com.comphenix.protocol.*;
import com.comphenix.protocol.wrappers.*;
import com.comphenix.protocol.wrappers.EnumWrappers.WorldBorderAction;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.ArenaCfg;

import java.lang.reflect.*;
import com.comphenix.protocol.events.*;
import org.bukkit.*;

public class Borders
{
    public static void magentaBorders(final Player player) {
        final PacketContainer packet = Main.protocolLib.createPacket(PacketType.Play.Server.WORLD_BORDER);
        packet.getWorldBorderActions().write(0, (WorldBorderAction)EnumWrappers.WorldBorderAction.INITIALIZE);
        packet.getDoubles().write(0, (Double)ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Magenta.X"))
        .write(1, (Double)ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Magenta.Z"))
        .write(2, (Double)ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Magenta.Size"))
        .write(3, (Double)ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Magenta.Size"));
        packet.getLongs().write(0, (Long)0L);
        packet.getIntegers().write(0, (Integer)29999984).write(1, (Integer)0).write(2, (Integer)0);
        try {
            Main.protocolLib.sendServerPacket(player, packet);
        }
        catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void goldBorders(final Player player) {
        final PacketContainer packet = Main.protocolLib.createPacket(PacketType.Play.Server.WORLD_BORDER);
        packet.getWorldBorderActions().write(0, (WorldBorderAction)EnumWrappers.WorldBorderAction.INITIALIZE);
        packet.getDoubles().write(0, (Double)ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Gold.X"))
        .write(1, (Double)ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Gold.Z"))
        .write(2, (Double)ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Gold.Size"))
        .write(3, (Double)ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Gold.Size"));
        packet.getLongs().write(0, (Long)0L);
        packet.getIntegers().write(0, (Integer)29999984).write(1, (Integer)0).write(2, (Integer)0);
        try {
            Main.protocolLib.sendServerPacket(player, packet);
        }
        catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void greenBorders(final Player player) {
        final PacketContainer packet = Main.protocolLib.createPacket(PacketType.Play.Server.WORLD_BORDER);
        packet.getWorldBorderActions().write(0, (WorldBorderAction)EnumWrappers.WorldBorderAction.INITIALIZE);
        packet.getDoubles().write(0, (Double)ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Green.X"))
        .write(1, (Double)ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Green.Z"))
        .write(2, (Double)ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Green.Size"))
        .write(3, (Double)ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Green.Size"));
        packet.getLongs().write(0, (Long)0L);
        packet.getIntegers().write(0, (Integer)29999984).write(1, (Integer)0).write(2, (Integer)0);
        try {
            Main.protocolLib.sendServerPacket(player, packet);
        }
        catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void blueBorders(final Player player) {
        final PacketContainer packet = Main.protocolLib.createPacket(PacketType.Play.Server.WORLD_BORDER);
        packet.getWorldBorderActions().write(0, (WorldBorderAction)EnumWrappers.WorldBorderAction.INITIALIZE);
        packet.getDoubles().write(0, (Double)ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Blue.X"))
        .write(1, (Double)ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Blue.Z"))
        .write(2, (Double)ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Blue.Size"))
        .write(3, (Double)ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Bluereload.Size"));
        packet.getLongs().write(0, (Long)0L);
        packet.getIntegers().write(0, (Integer)29999984).write(1, (Integer)0).write(2, (Integer)0);
        try {
            Main.protocolLib.sendServerPacket(player, packet);
        }
        catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void removeBorder() {
        final PacketContainer packet = Main.protocolLib.createPacket(PacketType.Play.Server.WORLD_BORDER);
        packet.getWorldBorderActions().write(0, (WorldBorderAction)EnumWrappers.WorldBorderAction.INITIALIZE);
        packet.getDoubles().write(0, (Double)Main.plugin.getConfig().getDouble("Border.Magenta.Z"))
        .write(1, (Double)Main.plugin.getConfig().getDouble("Border.Magenta.Z"))
        .write(2, (Double)3.0E7)
        .write(3, (Double)3.0E7);
        packet.getLongs().write(0, (Long)0L);
        packet.getIntegers().write(0, (Integer)29999984).write(1, (Integer)0).write(2, (Integer)0);
        try {
            for (final Player player : Bukkit.getOnlinePlayers()) {
                Main.protocolLib.sendServerPacket(player, packet);
            }
        }
        catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }
}
