package src.TierCraft.Minigame.SkyGiant.plugin.game;

import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.inventory.meta.*;

import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Messages;

public class Shop
{
    public static void buy(final Integer n, final Player player, final ItemStack itemStack) {
        if (Main.money.get(player) < n) {
            final Iterator<String> iterator = Messages.getMsg().getStringList("insufficient-money").iterator();
            while (iterator.hasNext()) {
                player.sendMessage(Main.PREFIX + iterator.next().replace('&', '§'));
            }
            return;
        }
        Main.money.replace(player, Main.money.get(player) - n);
        player.sendMessage(Main.PREFIX + Messages.getMsg().getString("purchase").replace('&', '§'));
        player.getInventory().addItem(new ItemStack[] { itemStack });
    }
    
    public static Inventory mainShop() {
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 9, Messages.getMsg().getString("shop.main").replace('&', '§'));
        inventory.addItem(new ItemStack[] { mainItem(Messages.getMsg().getString("shop.gear"), Material.IRON_SWORD) });
        inventory.addItem(new ItemStack[] { mainItem(Messages.getMsg().getString("shop.food"), Material.APPLE) });
        inventory.addItem(new ItemStack[] { mainItem(Messages.getMsg().getString("shop.potions"), Material.POTION) });
        inventory.addItem(new ItemStack[] { mainItem(Messages.getMsg().getString("shop.vanity"), Material.SEA_LANTERN) });
        inventory.addItem(new ItemStack[] { mainItem(Messages.getMsg().getString("shop.specials"), Material.RAILS) });
        return inventory;
    }
    
    private static ItemStack mainItem(final String s, final Material material) {
        final ItemStack itemStack = new ItemStack(material);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(s.replace('&', '§'));
        final ArrayList<String> lore = new ArrayList<String>();
        final Iterator<String> iterator = Messages.getMsg().getStringList("shop.browse").iterator();
        while (iterator.hasNext()) {
            lore.add(iterator.next().replace("{category}", ChatColor.stripColor(s)).replace('&', '§'));
        }
        itemMeta.setLore((List<String>)lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    
    private static ItemStack shopGearItem(final String s, final Integer n, final Integer n2, final Material material) {
        final ItemStack itemStack = new ItemStack(material, (int)n);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Messages.getMsg().getString("shop.itemformat").replace("{item}", Messages.getMsg().getString(s)).replace("{amount}", String.valueOf(n)).replace('&', '§'));
        final ArrayList<String> lore = new ArrayList<String>();
        final Iterator<String> iterator = Messages.getMsg().getStringList("shop.itemlore").iterator();
        while (iterator.hasNext()) {
            lore.add(iterator.next().replace("{gold}", String.valueOf(n2)).replace('&', '§'));
        }
        itemMeta.setLore((List<String>)lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    
    private static ItemStack shoplapis() {
        final ItemStack itemStack = new ItemStack(Material.INK_SACK, 1, (short)4);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Messages.getMsg().getString("shop.itemformat").replace("{item}", Messages.getMsg().getString("shop.lapis")).replace("{amount}", String.valueOf(1)).replace('&', '§'));
        final ArrayList<String> lore = new ArrayList<String>();
        final Iterator<String> iterator = Messages.getMsg().getStringList("shop.itemlore").iterator();
        while (iterator.hasNext()) {
            lore.add(iterator.next().replace("{gold}", String.valueOf(1000)).replace('&', '§'));
        }
        itemMeta.setLore((List<String>)lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    
    private static ItemStack shopPotionsItem(final String s, final Integer n) {
        final ItemStack itemStack = new ItemStack(Material.POTION);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Messages.getMsg().getString("shop.potionformat").replace("{potion}", Messages.getMsg().getString(s)).replace("{amount}", "1").replace('&', '§'));
        final ArrayList<String> lore = new ArrayList<String>();
        final Iterator<String> iterator = Messages.getMsg().getStringList("shop.itemlore").iterator();
        while (iterator.hasNext()) {
            lore.add(iterator.next().replace("{gold}", String.valueOf(n)).replace('&', '§'));
        }
        itemMeta.setLore((List<String>)lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    
    private static ItemStack backItem() {
        final ItemStack itemStack = new ItemStack(Material.BARRIER);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Messages.getMsg().getString("shop.back").replace('&', '§'));
        final ArrayList<String> lore = new ArrayList<String>();
        final Iterator<String> iterator = Messages.getMsg().getStringList("shop.backlore").iterator();
        while (iterator.hasNext()) {
            lore.add(iterator.next().replace('&', '§'));
        }
        itemMeta.setLore((List<String>)lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    
    public static Inventory gearShop() {
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 27, "?7[M] " + ChatColor.stripColor(Messages.getMsg().getString("shop.gear").replace('&', '§')));
        inventory.addItem(new ItemStack[] { shopGearItem("shop.sword", 1, 250, Material.DIAMOND_SWORD) });
        inventory.addItem(new ItemStack[] { shopGearItem("shop.pickaxe", 1, 150, Material.DIAMOND_PICKAXE) });
        inventory.addItem(new ItemStack[] { shopGearItem("shop.helmet", 1, 250, Material.DIAMOND_HELMET) });
        inventory.addItem(new ItemStack[] { shopGearItem("shop.chestplate", 1, 350, Material.DIAMOND_CHESTPLATE) });
        inventory.addItem(new ItemStack[] { shopGearItem("shop.leggings", 1, 200, Material.DIAMOND_LEGGINGS) });
        inventory.addItem(new ItemStack[] { shopGearItem("shop.boots", 1, 150, Material.DIAMOND_BOOTS) });
        inventory.addItem(new ItemStack[] { shopGearItem("shop.bow", 1, 250, Material.BOW) });
        inventory.addItem(new ItemStack[] { shopGearItem("shop.arrows", 16, 400, Material.ARROW) });
        inventory.setItem(22, backItem());
        return inventory;
    }
    
    public static Inventory foodShop() {
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 27, "?7[M] " + ChatColor.stripColor(Messages.getMsg().getString("shop.food").replace('&', '§')));
        inventory.addItem(new ItemStack[] { shopGearItem("shop.melon", 15, 20, Material.MELON) });
        inventory.addItem(new ItemStack[] { shopGearItem("shop.carrot", 15, 25, Material.CARROT_ITEM) });
        inventory.addItem(new ItemStack[] { shopGearItem("shop.apple", 15, 30, Material.APPLE) });
        inventory.addItem(new ItemStack[] { shopGearItem("shop.bread", 5, 35, Material.BREAD) });
        inventory.addItem(new ItemStack[] { shopGearItem("shop.chicken", 5, 40, Material.COOKED_CHICKEN) });
        inventory.addItem(new ItemStack[] { shopGearItem("shop.steak", 5, 45, Material.COOKED_BEEF) });
        inventory.addItem(new ItemStack[] { shopGearItem("shop.cake", 3, 50, Material.CAKE) });
        inventory.addItem(new ItemStack[] { shopGearItem("shop.golden", 1, 1000, Material.GOLDEN_APPLE) });
        inventory.setItem(22, backItem());
        return inventory;
    }
    
    public static Inventory potionShop() {
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 27, "?7[M] " + ChatColor.stripColor(Messages.getMsg().getString("shop.potions").replace('&', '§')));
        inventory.addItem(new ItemStack[] { shopPotionsItem("shop.invisibility", 750) });
        inventory.addItem(new ItemStack[] { shopPotionsItem("shop.regeneration", 600) });
        inventory.addItem(new ItemStack[] { shopPotionsItem("shop.swiftness", 300) });
        inventory.addItem(new ItemStack[] { shopPotionsItem("shop.flamer", 200) });
        inventory.addItem(new ItemStack[] { shopPotionsItem("shop.rabbitp", 200) });
        inventory.addItem(new ItemStack[] { shopPotionsItem("shop.instanth", 250) });
        inventory.addItem(new ItemStack[] { shopPotionsItem("shop.hurting", 400) });
        inventory.addItem(new ItemStack[] { shopPotionsItem("shop.deadlyp", 1000) });
        inventory.addItem(new ItemStack[] { shopGearItem("shop.milk", 1, 25, Material.MILK_BUCKET) });
        inventory.addItem(new ItemStack[] { shopGearItem("shop.exp", 1, 2000, Material.EXP_BOTTLE) });
        inventory.addItem(new ItemStack[] { shoplapis() });
        inventory.setItem(22, backItem());
        return inventory;
    }
    
    public static Inventory vanityShop() {
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 45, "?7[M] " + ChatColor.stripColor(Messages.getMsg().getString("shop.vanity").replace('&', '§')));
        inventory.addItem(new ItemStack[] { shopGearItem("shop.prismarine", 32, 50, Material.PRISMARINE) });
        inventory.addItem(new ItemStack[] { shopGearItem("shop.lantern", 32, 50, Material.SEA_LANTERN) });
        inventory.addItem(new ItemStack[] { shopGearItem("shop.glowstone", 32, 50, Material.GLOWSTONE) });
        inventory.addItem(new ItemStack[] { shopItemShort("shop.orangeclay", 32, 50, Material.STAINED_CLAY, (short)1) });
        inventory.addItem(new ItemStack[] { shopItemShort("shop.lightblueclay", 32, 50, Material.STAINED_CLAY, (short)3) });
        inventory.addItem(new ItemStack[] { shopItemShort("shop.yellowclay", 32, 50, Material.STAINED_CLAY, (short)4) });
        inventory.addItem(new ItemStack[] { shopItemShort("shop.limeclay", 32, 50, Material.STAINED_CLAY, (short)5) });
        inventory.addItem(new ItemStack[] { shopItemShort("shop.pinkclay", 32, 50, Material.STAINED_CLAY, (short)6) });
        inventory.addItem(new ItemStack[] { shopItemShort("shop.cyanclay", 32, 50, Material.STAINED_CLAY, (short)9) });
        inventory.addItem(new ItemStack[] { shopItemShort("shop.purpleclay", 32, 50, Material.STAINED_CLAY, (short)10) });
        inventory.addItem(new ItemStack[] { shopItemShort("shop.blueclay", 32, 50, Material.STAINED_CLAY, (short)11) });
        inventory.addItem(new ItemStack[] { shopItemShort("shop.redclay", 32, 50, Material.STAINED_CLAY, (short)14) });
        inventory.addItem(new ItemStack[] { shopItemShort("shop.orangeglass", 32, 50, Material.STAINED_GLASS, (short)1) });
        inventory.addItem(new ItemStack[] { shopItemShort("shop.magentaglass", 32, 50, Material.STAINED_GLASS, (short)2) });
        inventory.addItem(new ItemStack[] { shopItemShort("shop.lightblueglass", 32, 50, Material.STAINED_GLASS, (short)3) });
        inventory.addItem(new ItemStack[] { shopItemShort("shop.yellowglass", 32, 50, Material.STAINED_GLASS, (short)4) });
        inventory.addItem(new ItemStack[] { shopItemShort("shop.limeglass", 32, 50, Material.STAINED_GLASS, (short)5) });
        inventory.addItem(new ItemStack[] { shopItemShort("shop.pinkglass", 32, 50, Material.STAINED_GLASS, (short)6) });
        inventory.addItem(new ItemStack[] { shopItemShort("shop.cyanglass", 32, 50, Material.STAINED_GLASS, (short)9) });
        inventory.addItem(new ItemStack[] { shopItemShort("shop.purpleglass", 32, 50, Material.STAINED_GLASS, (short)10) });
        inventory.addItem(new ItemStack[] { shopItemShort("shop.blueglass", 32, 50, Material.STAINED_GLASS, (short)11) });
        inventory.addItem(new ItemStack[] { shopItemShort("shop.redglass", 32, 50, Material.STAINED_GLASS, (short)14) });
        inventory.addItem(new ItemStack[] { shopItemShort("shop.redbanner", 5, 100, Material.BANNER, (short)1) });
        inventory.addItem(new ItemStack[] { shopItemShort("shop.greenbanner", 5, 100, Material.BANNER, (short)10) });
        inventory.addItem(new ItemStack[] { shopItemShort("shop.yellowbanner", 5, 100, Material.BANNER, (short)11) });
        inventory.addItem(new ItemStack[] { shopItemShort("shop.bluebanner", 5, 100, Material.BANNER, (short)4) });
        inventory.setItem(41, backItem());
        return inventory;
    }
    
    private static ItemStack shopItemShort(final String s, final Integer n, final Integer n2, final Material material, final Short n3) {
        final ItemStack itemStack = new ItemStack(material, (int)n, (short)n3);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Messages.getMsg().getString("shop.itemformat").replace("{item}", Messages.getMsg().getString(s)).replace("{amount}", String.valueOf(n)).replace('&', '§'));
        final ArrayList<String> lore = new ArrayList<String>();
        final Iterator<String> iterator = Messages.getMsg().getStringList("shop.itemlore").iterator();
        while (iterator.hasNext()) {
            lore.add(iterator.next().replace("{gold}", String.valueOf(n2)).replace('&', '§'));
        }
        itemMeta.setLore((List<String>)lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
