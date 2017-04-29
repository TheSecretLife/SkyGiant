package src.TierCraft.Minigame.SkyGiant.plugin.listeners;

import org.bukkit.event.server.*;
import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Messages;
import src.TierCraft.Minigame.SkyGiant.plugin.game.GameState;

import org.bukkit.event.*;

public class ServerPingListener implements Listener
{
    @EventHandler
    public void ping(final ServerListPingEvent serverListPingEvent) {
        if (!Main.loaded) {
            return;
        }
        if (Main.MAINTENANCE) {
            serverListPingEvent.setMotd("§4MAINTENANCE");
        }
        if (Main.miniSG) {
            serverListPingEvent.setMaxPlayers(8);
        }
        else {
            serverListPingEvent.setMaxPlayers(24);
        }
        if (Main.STATUS == null) {
            return;
        }
        if (Main.STATUS == GameState.LOBBY) {
            serverListPingEvent.setMotd(Messages.getMsg().getString("motd.lobby").replace('&', '§'));
        }
        if (Main.STATUS == GameState.STARTING) {
            serverListPingEvent.setMotd(Messages.getMsg().getString("motd.starting").replace('&', '§'));
        }
        if (Main.STATUS == GameState.PLAYING) {
            serverListPingEvent.setMotd(Messages.getMsg().getString("motd.playing").replace('&', '§'));
        }
        if (Main.STATUS == GameState.RESTARTING) {
            serverListPingEvent.setMotd(Messages.getMsg().getString("motd.restarting").replace('&', '§'));
        }
    }
}
