package src.TierCraft.Minigame.SkyGiant.plugin.game;

import org.bukkit.entity.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;

public class RandomTeam
{
    public static void randomTeam(final Player player) {
        Main.players.add(player);
        if (Main.magentaPlayers.contains(player) || Main.goldPlayers.contains(player) || Main.greenPlayers.contains(player) || Main.bluePlayers.contains(player)) {
            return;
        }
        if (Main.miniSG) {
            if (Main.magentaPlayers.isEmpty()) {
                Main.magentaPlayers.add(player);
                return;
            }
            if (Main.goldPlayers.isEmpty()) {
                Main.goldPlayers.add(player);
                return;
            }
            if (Main.greenPlayers.isEmpty()) {
                Main.greenPlayers.add(player);
                return;
            }
            if (Main.bluePlayers.isEmpty()) {
                Main.bluePlayers.add(player);
                return;
            }
            if (Main.magentaPlayers.size() == 1) {
                Main.magentaPlayers.add(player);
                return;
            }
            if (Main.goldPlayers.size() == 1) {
                Main.goldPlayers.add(player);
                return;
            }
            if (Main.greenPlayers.size() == 1) {
                Main.greenPlayers.add(player);
                return;
            }
            if (Main.bluePlayers.size() == 1) {
                Main.bluePlayers.add(player);
                return;
            }
            Spectate.setSpectator(player);
        }
        else {
            if (Main.magentaPlayers.isEmpty()) {
                Main.magentaPlayers.add(player);
                return;
            }
            if (Main.goldPlayers.isEmpty()) {
                Main.goldPlayers.add(player);
                return;
            }
            if (Main.greenPlayers.isEmpty()) {
                Main.greenPlayers.add(player);
                return;
            }
            if (Main.bluePlayers.isEmpty()) {
                Main.bluePlayers.add(player);
                return;
            }
            if (Main.magentaPlayers.size() == 1) {
                Main.magentaPlayers.add(player);
                return;
            }
            if (Main.goldPlayers.size() == 1) {
                Main.goldPlayers.add(player);
                return;
            }
            if (Main.greenPlayers.size() == 1) {
                Main.greenPlayers.add(player);
                return;
            }
            if (Main.bluePlayers.size() == 1) {
                Main.bluePlayers.add(player);
                return;
            }
            if (Main.magentaPlayers.size() == 2) {
                Main.magentaPlayers.add(player);
                return;
            }
            if (Main.goldPlayers.size() == 2) {
                Main.goldPlayers.add(player);
                return;
            }
            if (Main.greenPlayers.size() == 2) {
                Main.greenPlayers.add(player);
                return;
            }
            if (Main.bluePlayers.size() == 2) {
                Main.bluePlayers.add(player);
                return;
            }
            if (Main.magentaPlayers.size() == 3) {
                Main.magentaPlayers.add(player);
                return;
            }
            if (Main.goldPlayers.size() == 3) {
                Main.goldPlayers.add(player);
                return;
            }
            if (Main.greenPlayers.size() == 3) {
                Main.greenPlayers.add(player);
                return;
            }
            if (Main.bluePlayers.size() == 3) {
                Main.bluePlayers.add(player);
                return;
            }
            if (Main.magentaPlayers.size() == 4) {
                Main.magentaPlayers.add(player);
                return;
            }
            if (Main.goldPlayers.size() == 4) {
                Main.goldPlayers.add(player);
                return;
            }
            if (Main.greenPlayers.size() == 4) {
                Main.greenPlayers.add(player);
                return;
            }
            if (Main.bluePlayers.size() == 4) {
                Main.bluePlayers.add(player);
                return;
            }
            if (Main.magentaPlayers.size() == 5) {
                Main.magentaPlayers.add(player);
                return;
            }
            if (Main.goldPlayers.size() == 5) {
                Main.goldPlayers.add(player);
                return;
            }
            if (Main.greenPlayers.size() == 5) {
                Main.greenPlayers.add(player);
                return;
            }
            if (Main.bluePlayers.size() == 5) {
                Main.bluePlayers.add(player);
                return;
            }
            Spectate.setSpectator(player);
        }
    }
}
