package src.TierCraft.Minigame.SkyGiant.plugin.listeners;

import org.bukkit.event.player.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Messages;
import src.TierCraft.Minigame.SkyGiant.plugin.game.GameState;

import org.bukkit.*;
import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.event.*;

public class AsyncPlaterChatListener implements Listener
{
    @EventHandler
    public void chat(final AsyncPlayerChatEvent asyncPlayerChatEvent) {
        if (Main.MAINTENANCE) {
            return;
        }
        asyncPlayerChatEvent.setCancelled(true);
        if (Main.STATUS == GameState.PLAYING) {
            if (Main.players.contains(asyncPlayerChatEvent.getPlayer())) {
                if (asyncPlayerChatEvent.getMessage().startsWith("!")) {
                    Bukkit.broadcastMessage(Messages.getMsg().getString("chat.shout").replace("{player}", asyncPlayerChatEvent.getPlayer().getName()).replace("{message}", asyncPlayerChatEvent.getMessage()).replaceFirst("!", "").replace('&', '§'));
                }
                else if (Main.goldPlayers.contains(asyncPlayerChatEvent.getPlayer())) {
                    final Iterator<Player> iterator = Main.goldPlayers.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().sendMessage(Messages.getMsg().getString("chat.gold").replace("{player}", asyncPlayerChatEvent.getPlayer().getName()).replace("{message}", asyncPlayerChatEvent.getMessage()).replace('&', '§').replace("{prefix}", getPrefix(asyncPlayerChatEvent.getPlayer())).replace("{suffix}", getSuffix(asyncPlayerChatEvent.getPlayer())));
                    }
                    Bukkit.getLogger().info("GOLD | " + asyncPlayerChatEvent.getPlayer().getName() + " > " + asyncPlayerChatEvent.getMessage());
                }
                else if (Main.magentaPlayers.contains(asyncPlayerChatEvent.getPlayer())) {
                    final Iterator<Player> iterator2 = Main.magentaPlayers.iterator();
                    while (iterator2.hasNext()) {
                        iterator2.next().sendMessage(Messages.getMsg().getString("chat.magenta").replace("{player}", asyncPlayerChatEvent.getPlayer().getName()).replace("{message}", asyncPlayerChatEvent.getMessage()).replace('&', '§').replace("{prefix}", getPrefix(asyncPlayerChatEvent.getPlayer())).replace("{suffix}", getSuffix(asyncPlayerChatEvent.getPlayer())));
                    }
                    Bukkit.getLogger().info("MAGENTA | " + asyncPlayerChatEvent.getPlayer().getName() + " > " + asyncPlayerChatEvent.getMessage());
                }
                else if (Main.greenPlayers.contains(asyncPlayerChatEvent.getPlayer())) {
                    final Iterator<Player> iterator3 = Main.greenPlayers.iterator();
                    while (iterator3.hasNext()) {
                        iterator3.next().sendMessage(Messages.getMsg().getString("chat.green").replace("{player}", asyncPlayerChatEvent.getPlayer().getName()).replace("{message}", asyncPlayerChatEvent.getMessage()).replace('&', '§').replace("{prefix}", getPrefix(asyncPlayerChatEvent.getPlayer())).replace("{suffix}", getSuffix(asyncPlayerChatEvent.getPlayer())));
                    }
                    Bukkit.getLogger().info("GREEN | " + asyncPlayerChatEvent.getPlayer().getName() + " > " + asyncPlayerChatEvent.getMessage());
                }
                else if (Main.bluePlayers.contains(asyncPlayerChatEvent.getPlayer())) {
                    final Iterator<Player> iterator4 = Main.bluePlayers.iterator();
                    while (iterator4.hasNext()) {
                        iterator4.next().sendMessage(Messages.getMsg().getString("chat.blue").replace("{player}", asyncPlayerChatEvent.getPlayer().getName()).replace("{message}", asyncPlayerChatEvent.getMessage()).replace('&', '§').replace("{prefix}", getPrefix(asyncPlayerChatEvent.getPlayer())).replace("{suffix}", getSuffix(asyncPlayerChatEvent.getPlayer())));
                    }
                    Bukkit.getLogger().info("BLUE | " + asyncPlayerChatEvent.getPlayer().getName() + " > " + asyncPlayerChatEvent.getMessage());
                }
            }
            else {
                final Iterator<Player> iterator5 = Main.spectators.iterator();
                while (iterator5.hasNext()) {
                    iterator5.next().sendMessage(Messages.getMsg().getString("chat.spectators").replace("{player}", asyncPlayerChatEvent.getPlayer().getName()).replace("{message}", asyncPlayerChatEvent.getMessage()).replace('&', '§').replace("{prefix}", getPrefix(asyncPlayerChatEvent.getPlayer())).replace("{suffix}", getSuffix(asyncPlayerChatEvent.getPlayer())));
                }
                Bukkit.getLogger().info("DEAD | " + asyncPlayerChatEvent.getPlayer().getName() + " > " + asyncPlayerChatEvent.getMessage());
            }
        }
        if (Main.STATUS == GameState.LOBBY || Main.STATUS == GameState.STARTING || Main.STATUS == GameState.RESTARTING) {
            Bukkit.broadcastMessage(Messages.getMsg().getString("chat.lobby").replace("{player}", asyncPlayerChatEvent.getPlayer().getName()).replace("{message}", asyncPlayerChatEvent.getMessage()).replace('&', '§').replace("{prefix}", getPrefix(asyncPlayerChatEvent.getPlayer())).replace("{suffix}", getSuffix(asyncPlayerChatEvent.getPlayer())));
        }
    }
    
    private String getPrefix(final Player player) {
        if (Main.vaultHook) {
            return Main.chat.getPrefix(player).replace('&', '§');
        }
        return "";
    }

	private String getSuffix(final Player player) {
        if (Main.vaultHook) {
            return Main.chat.getSuffix(player).replace('&', '§');
        }
        return "";
    }
}
