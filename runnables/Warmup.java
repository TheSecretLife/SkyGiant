package src.TierCraft.Minigame.SkyGiant.plugin.runnables;

import org.bukkit.scheduler.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Messages;
import src.TierCraft.Minigame.SkyGiant.plugin.game.Scoreboard;
import src.TierCraft.Minigame.SkyGiant.plugin.locations.Region;
import src.TierCraft.Minigame.SkyGiant.plugin.protocolLib.Borders;
import src.TierCraft.Minigame.SkyGiant.plugin.utils.Titles;

import org.bukkit.entity.*;
import org.bukkit.plugin.*;
import org.bukkit.*;
import java.util.*;

public class Warmup extends BukkitRunnable
{
    public void run() {
        if (Main.WarmupCountdown != 0) {
            --Main.WarmupCountdown;
        }
        Scoreboard.updateScoreboard();
        for (final Player player : Main.players) {
            if (!Main.money.containsKey(player)) {
                Main.money.put(player, 2);
            }
            Main.money.replace(player, Main.money.get(player) + 2);
            Main.nmsH.actionMsg(player, Messages.getMsg().getString("action-bar").replace("{gold}", String.valueOf(Main.money.get(player))).replace("{region}", Region.getRegion(player)).replace("{kills}", String.valueOf(Main.kills.get(player))).replace('&', '§'));
        }
        if (Main.WarmupCountdown == 0) {
            Borders.removeBorder();
            Main.warmup = false;
            new Game().runTaskTimer((Plugin)Main.plugin, 20L, 20L);
            this.cancel();
            Main.GameMilis = 1800000L;
            final Iterator<? extends Player> iterator2 = Bukkit.getOnlinePlayers().iterator();
            while (iterator2.hasNext()) {
                Titles.fightTitle(iterator2.next());
            }
        }
    }
}
