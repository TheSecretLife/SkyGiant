package src.TierCraft.Minigame.SkyGiant.plugin.runnables;

import org.bukkit.scheduler.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Messages;
import src.TierCraft.Minigame.SkyGiant.plugin.game.Scoreboard;
import src.TierCraft.Minigame.SkyGiant.plugin.game.Winner;
import src.TierCraft.Minigame.SkyGiant.plugin.locations.Region;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;

public class Game extends BukkitRunnable
{
    public void run() {
        if (Main.GameCountdown != 0) {
            --Main.GameCountdown;
        }
        if (Main.GameCountdown == 1320) {
            Bukkit.broadcastMessage(Main.PREFIX + Messages.getMsg().getString("double-damage").replace("{time}", "2").replace('&', '§'));
        }
        if (Main.GameCountdown == 1260) {
            Bukkit.broadcastMessage(Main.PREFIX + Messages.getMsg().getString("double-damage").replace("{time}", "1").replace('&', '§'));
        }
        if (Main.GameCountdown == 1200) {
            Bukkit.broadcastMessage(Main.PREFIX + Messages.getMsg().getString("double-giant-damage").replace('&', '§'));
            Main.doubleDamage = true;
        }
        Scoreboard.updateScoreboard();
        for (final Player player : Main.players) {
            Main.money.put(player, Main.money.get(player) + 2);
            Main.nmsH.actionMsg(player, Messages.getMsg().getString("action-bar").replace("{gold}", String.valueOf(Main.money.get(player))).replace("{region}", Region.getRegion(player)).replace("{kills}", String.valueOf(Main.kills.get(player))).replace('&', '§'));
        }
        if (Main.GameCountdown == 0) {
            this.cancel();
            Winner.getWinner();
            new Restarting().runTaskTimer((Plugin)Main.plugin, 20L, 20L);
        }
    }
}
