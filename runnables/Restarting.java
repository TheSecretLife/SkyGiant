package src.TierCraft.Minigame.SkyGiant.plugin.runnables;

import org.bukkit.scheduler.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.command.*;
import org.bukkit.plugin.*;
import java.util.*;
import com.google.common.io.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Messages;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Settings;
import src.TierCraft.Minigame.SkyGiant.plugin.game.GameState;
import src.TierCraft.Minigame.SkyGiant.plugin.game.Scoreboard;
import src.TierCraft.Minigame.SkyGiant.plugin.utils.Database;

public class Restarting extends BukkitRunnable
{
    public void run() {
        if (Main.RestartCountdown != 0) {
            --Main.RestartCountdown;
        }
        if (Main.RestartCountdown == 12 || Main.RestartCountdown == 11 || Main.RestartCountdown == 10) {
            Scoreboard.restartSb();
        }
        if (Main.RestartCountdown == 10) {
            Main.STATUS = GameState.RESTARTING;
            Bukkit.broadcastMessage(Main.PREFIX + Messages.getMsg().getString("game-over").replace('&', '§'));
            final Iterator<? extends Player> iterator = Bukkit.getOnlinePlayers().iterator();
            while (iterator.hasNext()) {
                Database.saveStats(iterator.next());
            }
            if (Settings.getCfg().getBoolean("reward-command.use")) {
                final Iterator<Player> iterator2 = Main.players.iterator();
                while (iterator2.hasNext()) {
                    Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), Settings.getCfg().getString("reward-command.cmd").replace("%player%", iterator2.next().getName()));
                }
            }
        }
        if (Main.RestartCountdown == 2) {
            final ByteArrayDataOutput dataOutput = ByteStreams.newDataOutput();
            dataOutput.writeUTF("Connect");
            dataOutput.writeUTF(Settings.getCfg().getString("lobby-server"));
            final Iterator<? extends Player> iterator3 = Bukkit.getOnlinePlayers().iterator();
            while (iterator3.hasNext()) {
                iterator3.next().sendPluginMessage((Plugin)Main.plugin, "BungeeCord", dataOutput.toByteArray());
            }
            Bukkit.unloadWorld(Main.choosenMap, false);
        }
        if (Main.RestartCountdown == 0) {
            Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), Settings.getCfg().getString("restart-cmd"));
            this.cancel();
        }
    }
}
