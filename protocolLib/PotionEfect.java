package src.TierCraft.Minigame.SkyGiant.plugin.protocolLib;

import com.comphenix.protocol.*;
import com.comphenix.protocol.events.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;

public class PotionEfect
{
    public static void registerPotionEffects() {
        Main.protocolLib.addPacketListener((PacketListener)new PacketAdapter(Main.plugin, ListenerPriority.NORMAL, new PacketType[] { PacketType.Play.Server.ENTITY_EFFECT }) {
            public void onPacketSending(final PacketEvent packetEvent) {
                if (packetEvent.getPacketType() == PacketType.Play.Server.ENTITY_EFFECT && Main.spectators.contains(packetEvent.getPlayer())) {
                    packetEvent.setCancelled(true);
                }
            }
        });
    }
}
