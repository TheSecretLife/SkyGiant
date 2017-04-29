package src.TierCraft.Minigame.SkyGiant.plugin.utils;

public class Misc
{
    public static boolean isInt(final String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }
}
