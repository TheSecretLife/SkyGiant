package src.TierCraft.Minigame.SkyGiant.plugin.listeners;

import org.bukkit.event.block.*;
import org.bukkit.inventory.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.potion.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Messages;
import src.TierCraft.Minigame.SkyGiant.plugin.game.GameState;
import src.TierCraft.Minigame.SkyGiant.plugin.utils.RandomItem;

import org.bukkit.entity.*;
import org.bukkit.event.*;

public class BlockBreakListener implements Listener
{
    @EventHandler
    public void ores(final BlockBreakEvent blockBreakEvent) {
        if (Main.MAINTENANCE) {
            return;
        }
        if (Main.STATUS != GameState.PLAYING) {
            if (!blockBreakEvent.getPlayer().isOp()) {
                blockBreakEvent.setCancelled(true);
            }
            return;
        }
        if (Main.respawning.contains(blockBreakEvent.getPlayer())) {
            blockBreakEvent.setCancelled(true);
            return;
        }
        if (Main.GoldGiantRegion.isInRegion(blockBreakEvent.getBlock().getLocation())) {
            if (!Main.PlacedBlocks.contains(blockBreakEvent.getBlock().getLocation())) {
                blockBreakEvent.setCancelled(true);
                blockBreakEvent.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("protected-region").replace('&', '?'));
            }
        }
        else if (Main.MagentaGiantRegion.isInRegion(blockBreakEvent.getBlock().getLocation())) {
            if (!Main.PlacedBlocks.contains(blockBreakEvent.getBlock().getLocation())) {
                blockBreakEvent.setCancelled(true);
                blockBreakEvent.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("protected-region").replace('&', '?'));
            }
        }
        else if (Main.GreenGiantRegion.isInRegion(blockBreakEvent.getBlock().getLocation())) {
            if (!Main.PlacedBlocks.contains(blockBreakEvent.getBlock().getLocation())) {
                blockBreakEvent.setCancelled(true);
                blockBreakEvent.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("protected-region").replace('&', '?'));
            }
        }
        else if (Main.BlueGiantRegion.isInRegion(blockBreakEvent.getBlock().getLocation())) {
            if (!Main.PlacedBlocks.contains(blockBreakEvent.getBlock().getLocation())) {
                blockBreakEvent.setCancelled(true);
                blockBreakEvent.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("protected-region").replace('&', '?'));
            }
        }
        else if (Main.BeastRegion.isInRegion(blockBreakEvent.getBlock().getLocation())) {
            blockBreakEvent.setCancelled(true);
            blockBreakEvent.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("scared-region").replace('&', '?'));
        }
        else if (Main.MiddleRegion.isInRegion(blockBreakEvent.getBlock().getLocation())) {
            if (Main.PlacedBlocks.contains(blockBreakEvent.getBlock().getLocation())) {
                blockBreakEvent.setCancelled(false);
            }
            else if (blockBreakEvent.getBlock().getType() != Material.DIAMOND_ORE || blockBreakEvent.getBlock().getType() != Material.GOLD_BLOCK || blockBreakEvent.getBlock().getType() != Material.GOLD_ORE || blockBreakEvent.getBlock().getType() != Material.IRON_ORE || blockBreakEvent.getBlock().getType() != Material.LAPIS_ORE || blockBreakEvent.getBlock().getType() != Material.REDSTONE_ORE || blockBreakEvent.getBlock().getType() != Material.GLOWING_REDSTONE_ORE || blockBreakEvent.getBlock().getType() != Material.EMERALD_ORE) {
                blockBreakEvent.setCancelled(true);
            }
        }
        else if (Main.BlueVillagerRegion.isInRegion(blockBreakEvent.getBlock().getLocation())) {
            blockBreakEvent.setCancelled(true);
            blockBreakEvent.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("protected-region").replace('&', '?'));
        }
        else if (Main.GoldVillagerRegion.isInRegion(blockBreakEvent.getBlock().getLocation())) {
            blockBreakEvent.setCancelled(true);
            blockBreakEvent.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("protected-region").replace('&', '?'));
        }
        else if (Main.MagentaVillagerRegion.isInRegion(blockBreakEvent.getBlock().getLocation())) {
            blockBreakEvent.setCancelled(true);
            blockBreakEvent.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("protected-region").replace('&', '?'));
        }
        else if (Main.GreenVillagerRegion.isInRegion(blockBreakEvent.getBlock().getLocation())) {
            blockBreakEvent.setCancelled(true);
            blockBreakEvent.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("protected-region").replace('&', '?'));
        }
        if (blockBreakEvent.getBlock().getType() == Material.GOLD_BLOCK) {
            if (Main.MiddleRegion.isInRegion(blockBreakEvent.getBlock().getLocation())) {
                blockBreakEvent.setCancelled(true);
                blockBreakEvent.getBlock().setType(Material.STONE);
            }
            else {
                blockBreakEvent.getBlock().breakNaturally(new ItemStack(Material.STICK));
            }
            final int n = new Random().nextInt(121) + 80;
            Main.money.replace(blockBreakEvent.getPlayer(), Main.money.get(blockBreakEvent.getPlayer()) + n);
            blockBreakEvent.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("gold-gained").replace("{amount}", String.valueOf(n)).replace('&', '?'));
            blockBreakEvent.getPlayer().playSound(blockBreakEvent.getPlayer().getLocation(), Main.nmsH.catMeow(), 1.0f, 1.0f);
            blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.2, 0.2, 0.2), Effect.FLAME, 1);
            blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.6, 0.6, 0.6), Effect.FLAME, 1);
            blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.9, 0.9, 0.9), Effect.FLAME, 1);
        }
        else if (blockBreakEvent.getBlock().getType() == Material.DIAMOND_ORE) {
            if (Main.MiddleRegion.isInRegion(blockBreakEvent.getBlock().getLocation())) {
                blockBreakEvent.setCancelled(true);
                blockBreakEvent.getBlock().setType(Material.STONE, true);
            }
            else {
                blockBreakEvent.getBlock().breakNaturally(new ItemStack(Material.STICK));
            }
            final RandomItem<ItemStack> randomItem = new RandomItem<ItemStack>();
            randomItem.add(16.0, new ItemStack(Material.DIAMOND_HELMET));
            randomItem.add(16.0, new ItemStack(Material.DIAMOND_CHESTPLATE));
            randomItem.add(16.0, new ItemStack(Material.DIAMOND_LEGGINGS));
            randomItem.add(16.0, new ItemStack(Material.DIAMOND_BOOTS));
            randomItem.add(16.0, new ItemStack(Material.DIAMOND_SWORD));
            randomItem.add(16.0, new ItemStack(Material.DIAMOND_PICKAXE));
            blockBreakEvent.getPlayer().getWorld().dropItemNaturally(blockBreakEvent.getBlock().getLocation(), (ItemStack)randomItem.next());
            blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.2, 0.2, 0.2), Effect.ENDER_SIGNAL, 3);
            blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.6, 0.6, 0.6), Effect.ENDER_SIGNAL, 3);
            blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.9, 0.9, 0.9), Effect.ENDER_SIGNAL, 3);
            blockBreakEvent.getPlayer().getWorld().playSound(blockBreakEvent.getPlayer().getLocation(), Main.nmsH.eggPop(), 1.0f, 1.0f);
        }
        else if (blockBreakEvent.getBlock().getType() == Material.GOLD_ORE) {
            if (Main.MiddleRegion.isInRegion(blockBreakEvent.getBlock().getLocation())) {
                blockBreakEvent.setCancelled(true);
                blockBreakEvent.getBlock().setType(Material.STONE, true);
            }
            else {
                blockBreakEvent.getBlock().breakNaturally(new ItemStack(Material.STICK));
            }
            final RandomItem<ItemStack> randomItem2 = new RandomItem<ItemStack>();
            randomItem2.add(8.0, new ItemStack(Material.FLINT_AND_STEEL));
            randomItem2.add(8.0, new ItemStack(Material.COOKED_CHICKEN));
            randomItem2.add(8.0, new ItemStack(Material.DIAMOND_HELMET));
            randomItem2.add(8.0, new ItemStack(Material.LEATHER_BOOTS));
            randomItem2.add(8.0, new ItemStack(Material.TNT));
            randomItem2.add(8.0, new ItemStack(Material.IRON_HOE));
            randomItem2.add(8.0, new ItemStack(Material.TORCH, 2));
            randomItem2.add(8.0, new ItemStack(Material.DIAMOND_SWORD));
            randomItem2.add(8.0, new ItemStack(Material.IRON_AXE));
            randomItem2.add(8.0, new ItemStack(Material.LEATHER_LEGGINGS));
            randomItem2.add(8.0, new ItemStack(Material.STONE, 12));
            randomItem2.add(8.0, new ItemStack(Material.IRON_PICKAXE));
            randomItem2.add(8.0, new ItemStack(Material.WATER_BUCKET));
            randomItem2.add(8.0, new ItemStack(Material.WOOD, 12));
            randomItem2.add(8.0, new ItemStack(Material.YELLOW_FLOWER));
            randomItem2.add(8.0, new ItemStack(Material.LEATHER_CHESTPLATE));
            randomItem2.add(8.0, new ItemStack(Material.DIAMOND_PICKAXE));
            randomItem2.add(8.0, new ItemStack(Material.GOLD_HELMET));
            final ItemStack itemStack = randomItem2.next();
            final ItemStack itemStack2 = randomItem2.next();
            final ItemStack itemStack3 = randomItem2.next();
            if (itemStack.getType() == Material.YELLOW_FLOWER) {
                ((Creeper)blockBreakEvent.getPlayer().getWorld().spawnEntity(blockBreakEvent.getBlock().getLocation(), EntityType.CREEPER)).setPowered(true);
                blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.2, 0.2, 0.2), Effect.VILLAGER_THUNDERCLOUD, 5);
                blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.2, 1.0, 0.2), Effect.VILLAGER_THUNDERCLOUD, 5);
                blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.3, 1.2, 0.3), Effect.VILLAGER_THUNDERCLOUD, 5);
                blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation(), Effect.VILLAGER_THUNDERCLOUD, 5);
                blockBreakEvent.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("creeper-spawn").replace('&', '?'));
            }
            if (itemStack2.getType() == Material.YELLOW_FLOWER) {
                ((Creeper)blockBreakEvent.getPlayer().getWorld().spawnEntity(blockBreakEvent.getBlock().getLocation(), EntityType.CREEPER)).setPowered(true);
                blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.2, 0.2, 0.2), Effect.VILLAGER_THUNDERCLOUD, 5);
                blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.2, 1.0, 0.2), Effect.VILLAGER_THUNDERCLOUD, 5);
                blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.3, 1.2, 0.3), Effect.VILLAGER_THUNDERCLOUD, 5);
                blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation(), Effect.VILLAGER_THUNDERCLOUD, 5);
                blockBreakEvent.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("creeper-spawn").replace('&', '?'));
            }
            if (itemStack3.getType() == Material.YELLOW_FLOWER) {
                ((Creeper)blockBreakEvent.getPlayer().getWorld().spawnEntity(blockBreakEvent.getBlock().getLocation(), EntityType.CREEPER)).setPowered(true);
                blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.2, 0.2, 0.2), Effect.VILLAGER_THUNDERCLOUD, 5);
                blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.2, 1.0, 0.2), Effect.VILLAGER_THUNDERCLOUD, 5);
                blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.3, 1.2, 0.3), Effect.VILLAGER_THUNDERCLOUD, 5);
                blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation(), Effect.VILLAGER_THUNDERCLOUD, 5);
                blockBreakEvent.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("creeper-spawn").replace('&', '?'));
            }
            blockBreakEvent.getPlayer().getWorld().dropItemNaturally(blockBreakEvent.getBlock().getLocation(), itemStack);
            blockBreakEvent.getPlayer().getWorld().dropItemNaturally(blockBreakEvent.getBlock().getLocation(), itemStack2);
            blockBreakEvent.getPlayer().getWorld().dropItemNaturally(blockBreakEvent.getBlock().getLocation(), itemStack3);
            blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.20000000298023224, 0.20000000298023224, 0.20000000298023224), Effect.FLAME, 5);
            blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.5, 0.5, 0.5), Effect.FLAME, 5);
            blockBreakEvent.getPlayer().getWorld().playSound(blockBreakEvent.getPlayer().getLocation(), Main.nmsH.lvlUp(), 1.0f, 1.0f);
        }
        else if (blockBreakEvent.getBlock().getType() == Material.IRON_ORE) {
            if (Main.MiddleRegion.isInRegion(blockBreakEvent.getBlock().getLocation())) {
                blockBreakEvent.setCancelled(true);
                blockBreakEvent.getBlock().setType(Material.STONE, true);
            }
            else {
                blockBreakEvent.getBlock().breakNaturally(new ItemStack(Material.STICK));
            }
            final int n2 = new Random().nextInt(3) + 1;
            final RandomItem<ItemStack> randomItem3 = new RandomItem<ItemStack>();
            randomItem3.add(10.0, new ItemStack(Material.COAL, 2));
            randomItem3.add(10.0, new ItemStack(Material.STICK, 2));
            randomItem3.add(10.0, new ItemStack(Material.FLINT));
            randomItem3.add(10.0, new ItemStack(Material.DIAMOND));
            randomItem3.add(10.0, new ItemStack(Material.IRON_INGOT));
            randomItem3.add(10.0, new ItemStack(Material.IRON_INGOT, 2));
            randomItem3.add(10.0, new ItemStack(Material.STRING, 2));
            randomItem3.add(10.0, new ItemStack(Material.ARROW, 2));
            final ItemStack itemStack4 = randomItem3.next();
            final ItemStack itemStack5 = randomItem3.next();
            final ItemStack itemStack6 = randomItem3.next();
            switch (n2) {
                case 1: {
                    blockBreakEvent.getPlayer().getWorld().dropItemNaturally(blockBreakEvent.getBlock().getLocation(), itemStack4);
                    break;
                }
                case 2: {
                    blockBreakEvent.getPlayer().getWorld().dropItemNaturally(blockBreakEvent.getBlock().getLocation(), itemStack4);
                    blockBreakEvent.getPlayer().getWorld().dropItemNaturally(blockBreakEvent.getBlock().getLocation(), itemStack5);
                    break;
                }
                case 3: {
                    blockBreakEvent.getPlayer().getWorld().dropItemNaturally(blockBreakEvent.getBlock().getLocation(), itemStack4);
                    blockBreakEvent.getPlayer().getWorld().dropItemNaturally(blockBreakEvent.getBlock().getLocation(), itemStack5);
                    blockBreakEvent.getPlayer().getWorld().dropItemNaturally(blockBreakEvent.getBlock().getLocation(), itemStack6);
                    break;
                }
            }
            blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.2, 0.5, 0.2), Effect.SLIME, 5);
            blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.2, 0.4, 0.2), Effect.SLIME, 5);
            blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.5, 0.6, 0.5), Effect.SLIME, 5);
            blockBreakEvent.getPlayer().getWorld().playSound(blockBreakEvent.getPlayer().getLocation(), Main.nmsH.anvil(), 1.0f, 1.0f);
        }
        else if (blockBreakEvent.getBlock().getType() == Material.REDSTONE_ORE || blockBreakEvent.getBlock().getType() == Material.GLOWING_REDSTONE_ORE) {
            if (Main.MiddleRegion.isInRegion(blockBreakEvent.getBlock().getLocation())) {
                blockBreakEvent.setCancelled(true);
                blockBreakEvent.getBlock().setType(Material.STONE, true);
            }
            else {
                blockBreakEvent.getBlock().breakNaturally(new ItemStack(Material.STICK));
            }
            blockBreakEvent.getPlayer().setHealthScale((double)(new Random().nextInt(9) + 16));
            blockBreakEvent.getPlayer().getWorld().playSound(blockBreakEvent.getPlayer().getLocation(), Main.nmsH.villager(), 1.0f, 1.0f);
            blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.2, 0.2, 0.2), Effect.HEART, 5);
            blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.5, 0.5, 0.5), Effect.HEART, 5);
            blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.8, 0.8, 0.8), Effect.HEART, 5);
        }
        else if (blockBreakEvent.getBlock().getType() == Material.LAPIS_ORE) {
            if (Main.MiddleRegion.isInRegion(blockBreakEvent.getBlock().getLocation())) {
                blockBreakEvent.setCancelled(true);
                blockBreakEvent.getBlock().setType(Material.STONE, true);
            }
            else {
                blockBreakEvent.getBlock().breakNaturally(new ItemStack(Material.STICK));
            }
            final Random random = new Random();
            final int n3 = random.nextInt(8) + 1;
            final int n4 = random.nextInt(50) + 1;
            switch (n3) {
                case 1: {
                    blockBreakEvent.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, n4 * 20, 1));
                    blockBreakEvent.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("potion-added").replace("{type}", Messages.getMsg().getString("potions.absorption")).replace("{time}", String.valueOf(n4)).replace('&', '?'));
                    break;
                }
                case 2: {
                    blockBreakEvent.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, n4 * 20, 1));
                    blockBreakEvent.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("potion-added").replace("{type}", Messages.getMsg().getString("potions.fire-resistence")).replace("{time}", String.valueOf(n4)).replace('&', '?'));
                    break;
                }
                case 3: {
                    blockBreakEvent.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, n4 * 20, 1));
                    blockBreakEvent.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("potion-added").replace("{type}", Messages.getMsg().getString("potions.regeneration")).replace("{time}", String.valueOf(n4)).replace('&', '?'));
                    break;
                }
                case 4: {
                    blockBreakEvent.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, n4 * 20, 1));
                    blockBreakEvent.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("potion-added").replace("{type}", Messages.getMsg().getString("potions.slow")).replace("{time}", String.valueOf(n4)).replace('&', '?'));
                    break;
                }
                case 5: {
                    blockBreakEvent.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, n4 * 20, 1));
                    blockBreakEvent.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("potion-added").replace("{type}", Messages.getMsg().getString("potions.fast-digging")).replace("{time}", String.valueOf(n4)).replace('&', '?'));
                    break;
                }
                case 6: {
                    blockBreakEvent.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, n4 * 20, 1));
                    blockBreakEvent.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("potion-added").replace("{type}", Messages.getMsg().getString("potions.blindness")).replace("{time}", String.valueOf(n4)).replace('&', '?'));
                    break;
                }
                case 7: {
                    blockBreakEvent.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, n4 * 20, 1));
                    blockBreakEvent.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("potion-added").replace("{type}", Messages.getMsg().getString("potions.confusion")).replace("{time}", String.valueOf(n4)).replace('&', '?'));
                    break;
                }
                case 8: {
                    blockBreakEvent.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, n4 * 20, 1));
                    blockBreakEvent.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("potion-added").replace("{type}", Messages.getMsg().getString("potions.damage-resistance")).replace("{time}", String.valueOf(n4)).replace('&', '?'));
                    break;
                }
            }
            blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.2, 0.2, 0.2), Effect.SPLASH, 5);
            blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.5, 0.5, 0.5), Effect.SPLASH, 5);
            blockBreakEvent.getPlayer().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.9, 0.9, 0.9), Effect.SPLASH, 5);
            blockBreakEvent.getPlayer().getWorld().playSound(blockBreakEvent.getPlayer().getLocation(), Main.nmsH.splash(), 1.0f, 1.0f);
        }
        else if (blockBreakEvent.getBlock().getType() == Material.EMERALD_ORE) {
            if (Main.MiddleRegion.isInRegion(blockBreakEvent.getBlock().getLocation())) {
                blockBreakEvent.setCancelled(true);
                blockBreakEvent.getBlock().setType(Material.STONE);
                blockBreakEvent.getBlock().getWorld().dropItemNaturally(blockBreakEvent.getBlock().getLocation(), new ItemStack(Material.INK_SACK, 2, (short)4));
                blockBreakEvent.getPlayer().setLevel(blockBreakEvent.getPlayer().getLevel() + 1);
                blockBreakEvent.getBlock().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.20000000298023224, 0.20000000298023224, 0.20000000298023224), Effect.HAPPY_VILLAGER, 5);
                blockBreakEvent.getBlock().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.5, 0.5, 0.5), Effect.HAPPY_VILLAGER, 5);
                blockBreakEvent.getBlock().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.8999999761581421, 0.8999999761581421, 0.8999999761581421), Effect.HAPPY_VILLAGER, 5);
                blockBreakEvent.getPlayer().getWorld().playSound(blockBreakEvent.getPlayer().getLocation(), Main.nmsH.lvlUp(), 1.0f, 1.0f);
            }
            else {
                blockBreakEvent.getBlock().breakNaturally(new ItemStack(Material.STICK));
                blockBreakEvent.getBlock().getWorld().dropItemNaturally(blockBreakEvent.getBlock().getLocation(), new ItemStack(Material.INK_SACK, 2, (short)4));
                blockBreakEvent.getPlayer().setLevel(blockBreakEvent.getPlayer().getLevel() + 1);
                blockBreakEvent.getBlock().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.20000000298023224, 0.20000000298023224, 0.20000000298023224), Effect.HAPPY_VILLAGER, 5);
                blockBreakEvent.getBlock().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.5, 0.5, 0.5), Effect.HAPPY_VILLAGER, 5);
                blockBreakEvent.getBlock().getWorld().playEffect(blockBreakEvent.getBlock().getLocation().add(0.8999999761581421, 0.8999999761581421, 0.8999999761581421), Effect.HAPPY_VILLAGER, 5);
                blockBreakEvent.getPlayer().getWorld().playSound(blockBreakEvent.getPlayer().getLocation(), Main.nmsH.lvlUp(), 1.0f, 1.0f);
            }
        }
        else if (blockBreakEvent.getBlock().getType() == Material.SKULL && Main.respBeac.containsValue(blockBreakEvent.getBlock().getLocation())) {
            Main.respBeac.get(blockBreakEvent.getBlock().getLocation()).sendMessage(Main.PREFIX + Messages.getMsg().getString("beacon-removed").replace('&', '?'));
            Main.respBeac.remove(blockBreakEvent.getBlock().getLocation());
            Main.respOwn.remove(blockBreakEvent.getPlayer());
            blockBreakEvent.setCancelled(true);
            blockBreakEvent.getBlock().setType(Material.AIR);
        }
    }
}
