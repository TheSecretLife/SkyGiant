package src.TierCraft.Minigame.SkyGiant.plugin.utils;

import org.bukkit.*;
import java.net.*;
import java.io.*;
import org.bukkit.plugin.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;

public class Updater
{
    public static void checkUpdates() {
        Bukkit.getScheduler().runTaskLater((Plugin)Main.plugin, (Runnable)new Runnable() {
            @Override
            public void run() {
                try {
                    final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL("http://www.spigotmc.org/api/general.php").openConnection();
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.getOutputStream().write("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource=29803".getBytes());
                    final String version = Main.plugin.getDescription().getVersion();
                    final String replaceAll = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())).readLine().replaceAll("[a-zA-Z ]", "");
                    if (!replaceAll.equalsIgnoreCase(version)) {
                        Main.updateAvailable = true;
                        Main.newVersion = replaceAll;
                        Main.plugin.getLogger().info("There is a nev version available!");
                    }
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }, 30L);
    }
}
