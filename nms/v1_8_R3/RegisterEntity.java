package src.TierCraft.Minigame.SkyGiant.plugin.nms.v1_8_R3;

import java.util.*;
import net.minecraft.server.v1_8_R3.*;
import java.lang.reflect.*;

public class RegisterEntity
{
    public void registerEntity(final String s, final int n, final Class<?> clazz, final Class<?> clazz2) {
        try {
            @SuppressWarnings("rawtypes")
			final ArrayList<Map> list = new ArrayList<Map>();
            for (final Field field : EntityTypes.class.getDeclaredFields()) {
                if (field.getType().getSimpleName().equals(Map.class.getSimpleName())) {
                    field.setAccessible(true);
                    list.add((Map<?, ?>)field.get(null));
                }
            }
            if (((Map<?, ?>)list.get(2)).containsKey(n)) {
                ((Map<?, ?>)list.get(0)).remove(s);
                ((Map<?, ?>)list.get(2)).remove(n);
            }
            final Method declaredMethod = EntityTypes.class.getDeclaredMethod("a", Class.class, String.class, Integer.TYPE);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(null, clazz2, s, n);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
