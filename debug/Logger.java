package src.TierCraft.Minigame.SkyGiant.plugin.debug;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;

public class Logger
{
    public static void debugMsg(final String s) {
        if (!Main.Debug) {
            return;
        }
        Main.plugin.getLogger().info(s);
    }
}
