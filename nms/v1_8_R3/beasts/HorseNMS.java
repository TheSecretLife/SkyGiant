package com.andrei1058.skygiants.nms.v1_8_R3.beasts;

import org.bukkit.craftbukkit.v1_8_R3.util.*;
import java.lang.reflect.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.craftbukkit.v1_8_R3.*;
import com.andrei1058.skygiants.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.*;
import org.bukkit.event.entity.*;
import com.andrei1058.skygiants.configuration.*;
import net.minecraft.server.v1_8_R3.*;

public class HorseNMS extends EntityHorse
{
    public HorseNMS(final World world) {
        super(world);
        try {
            final Field declaredField = PathfinderGoalSelector.class.getDeclaredField("b");
            declaredField.setAccessible(true);
            final Field declaredField2 = PathfinderGoalSelector.class.getDeclaredField("c");
            declaredField2.setAccessible(true);
            declaredField.set(this.goalSelector, new UnsafeList());
            declaredField.set(this.targetSelector, new UnsafeList());
            declaredField2.set(this.goalSelector, new UnsafeList());
            declaredField2.set(this.targetSelector, new UnsafeList());
        }
        catch (Exception ex) {}
        this.goalSelector.a(5, (PathfinderGoal)new PathfinderGoalMoveTowardsRestriction((EntityCreature)this, 0.6));
        this.goalSelector.a(9, (PathfinderGoal)new PathfinderGoalInteract((EntityInsentient)this, (Class)EntityHuman.class, 3.0f, 1.0f));
        this.goalSelector.a(9, (PathfinderGoal)new PathfinderGoalRandomStroll((EntityCreature)this, 0.6));
        this.goalSelector.a(10, (PathfinderGoal)new PathfinderGoalLookAtPlayer((EntityInsentient)this, (Class)EntityHuman.class, 8.0f));
        this.goalSelector.a(2, (PathfinderGoal)new PathfinderGoalHurtByTarget((EntityCreature)this, true, new Class[] { EntityHuman.class }));
    }
    
    public void move(final double n, final double n2, final double n3) {
    }
    
    public void collide(final Entity entity) {
    }
    
    public void g(final double n, final double n2, final double n3) {
    }
    
    public static Horse spawnHorse(final Location location) {
        final WorldServer handle = ((CraftWorld)location.getWorld()).getHandle();
        final HorseNMS horseNMS = new HorseNMS((World)handle);
        horseNMS.setTame(false);
        horseNMS.getAttributeInstance(GenericAttributes.maxHealth).setValue((double)Main.BeastHealth);
        horseNMS.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        ((CraftLivingEntity)horseNMS.getBukkitEntity()).setRemoveWhenFarAway(false);
        ((World)handle).addEntity((Entity)horseNMS, CreatureSpawnEvent.SpawnReason.CUSTOM);
        horseNMS.setCustomName(Messages.getMsg().getString("centaur").replace('&', '?'));
        Main.beastType = "CENTAUR";
        horseNMS.setHealth((float)Main.BeastHealth);
        return (Horse)horseNMS.getBukkitEntity();
    }
}
