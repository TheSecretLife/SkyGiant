package src.TierCraft.Minigame.SkyGiant.plugin.utils;

import java.util.*;

public class RandomItem<E>
{
    private final NavigableMap<Double, E> map;
    private final Random random;
    private double total;
    
    public RandomItem() {
        this(new Random());
    }
    
    public RandomItem(final Random random) {
        this.map = new TreeMap<Double, E>();
        this.total = 0.0;
        this.random = random;
    }
    
    public void add(final double n, final E e) {
        if (n > 0.0) {
            this.total += n;
            this.map.put(this.total, e);
        }
    }
    
    public E next() {
        return this.map.ceilingEntry(this.random.nextDouble() * this.total).getValue();
    }
}
