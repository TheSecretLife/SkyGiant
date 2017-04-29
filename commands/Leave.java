package src.TierCraft.Minigame.SkyGiant.plugin.commands;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;
import com.google.common.io.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Settings;

public class Leave implements CommandExecutor
{
    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] array) {
        if (!(commandSender instanceof Player)) {
            return true;
        }
        final Player player = (Player)commandSender;
        if (command.getName().equalsIgnoreCase("leave")) {
            final ByteArrayDataOutput dataOutput = ByteStreams.newDataOutput();
            dataOutput.writeUTF("Connect");
            dataOutput.writeUTF(Settings.getCfg().getString("lobby-server"));
            player.sendPluginMessage((Plugin)Main.plugin, "BungeeCord", dataOutput.toByteArray());
            return true;
        }
        return false;
    }
}
