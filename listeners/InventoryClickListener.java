package src.TierCraft.Minigame.SkyGiant.plugin.listeners;

import org.bukkit.event.inventory.*;
import org.bukkit.*;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.entity.*;
import net.md_5.bungee.api.chat.*;
import src.TierCraft.Minigame.SkyGiant.plugin.Main;
import src.TierCraft.Minigame.SkyGiant.plugin.commands.Team;
import src.TierCraft.Minigame.SkyGiant.plugin.configuration.Messages;
import src.TierCraft.Minigame.SkyGiant.plugin.game.GameState;
import src.TierCraft.Minigame.SkyGiant.plugin.game.Shop;

import org.bukkit.inventory.*;
import org.bukkit.potion.*;
import org.bukkit.inventory.meta.*;
import java.util.*;
import org.bukkit.event.*;

public class InventoryClickListener implements Listener
{
    @EventHandler
    public void inv(final InventoryClickEvent inventoryClickEvent) {
        if (inventoryClickEvent.getCurrentItem() == null) {
            return;
        }
        if (inventoryClickEvent.getCurrentItem().getType() == Material.AIR) {
            return;
        }
        if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName() == null) {
            return;
        }
        if (inventoryClickEvent.getCurrentItem().getType() == Material.SKULL_ITEM) {
            if ((Main.STATUS == GameState.PLAYING || Main.STATUS == GameState.RESTARTING) && inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName() != null) {
                if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Beacon")) {
                    inventoryClickEvent.setCancelled(true);
                }
                else {
                    final Player player = Bukkit.getPlayer(inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName());
                    if (Main.players.contains(player)) {
                        inventoryClickEvent.getWhoClicked().teleport((Entity)Bukkit.getPlayer(player.getName()));
                        inventoryClickEvent.setCancelled(true);
                    }
                    else {
                        inventoryClickEvent.setCancelled(true);
                        inventoryClickEvent.getWhoClicked().closeInventory();
                    }
                }
            }
            if (Main.STATUS == GameState.LOBBY || Main.STATUS == GameState.STARTING) {
                inventoryClickEvent.setCancelled(true);
                if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(ChatColor.stripColor(inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName())))) {
                    final Player player2 = Bukkit.getPlayer(ChatColor.stripColor(inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName()));
                    if (Main.hasTeam.contains(player2)) {
                        inventoryClickEvent.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("has-team").replace('&', '?'));
                        return;
                    }
                    if (Team.hasInvited.containsKey(player2)) {
                        if (inventoryClickEvent.getWhoClicked().getUniqueId().toString() == Team.hasInvited.get(player2).getUniqueId().toString()) {
                            player2.sendMessage(Main.PREFIX + Messages.getMsg().getString("teamed-up").replace("{name}", inventoryClickEvent.getWhoClicked().getName()).replace('&', '?'));
                            Team.hasInvited.get(player2).sendMessage(Main.PREFIX + Messages.getMsg().getString("teamed-up").replace("{name}", player2.getName()).replace('&', '?'));
                            Main.hasTeam.add(player2);
                            Main.hasTeam.add((Player)inventoryClickEvent.getWhoClicked());
                            if (Main.goldPlayers.isEmpty()) {
                                Main.goldPlayers.add(player2);
                                Main.goldPlayers.add(Team.hasInvited.get(player2));
                            }
                            else if (Main.magentaPlayers.isEmpty()) {
                                Main.magentaPlayers.add(player2);
                                Main.magentaPlayers.add(Team.hasInvited.get(player2));
                            }
                            else if (Main.greenPlayers.isEmpty()) {
                                Main.greenPlayers.add(player2);
                                Main.greenPlayers.add(Team.hasInvited.get(player2));
                            }
                            else if (Main.bluePlayers.isEmpty()) {
                                Main.bluePlayers.add(player2);
                                Main.bluePlayers.add(Team.hasInvited.get(player2));
                            }
                            inventoryClickEvent.setCancelled(true);
                            inventoryClickEvent.getWhoClicked().closeInventory();
                            return;
                        }
                        inventoryClickEvent.setCancelled(true);
                    }
                    final TextComponent textComponent = new TextComponent(Main.PREFIX + Messages.getMsg().getString("invite-click").replace('&', '?'));
                    textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/team " + inventoryClickEvent.getWhoClicked().getName()));
                    player2.sendMessage(Main.PREFIX + Messages.getMsg().getString("invite-received").replace("{name}", inventoryClickEvent.getWhoClicked().getName()).replace('&', '?'));
                    player2.spigot().sendMessage((BaseComponent)textComponent);
                    inventoryClickEvent.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("invite-sent").replace("{name}", player2.getName()).replace('&', '?'));
                    if (Team.hasInvited.containsKey(inventoryClickEvent.getWhoClicked())) {
                        Team.hasInvited.get(inventoryClickEvent.getWhoClicked()).sendMessage(Main.PREFIX + Messages.getMsg().getString("invite-revoked").replace("{player}", inventoryClickEvent.getWhoClicked().getName()).replace('&', '?'));
                    }
                    Team.isInvited.put(player2, (Player)inventoryClickEvent.getWhoClicked());
                    Team.hasInvited.put((Player)inventoryClickEvent.getWhoClicked(), player2);
                    inventoryClickEvent.setCancelled(true);
                    inventoryClickEvent.getWhoClicked().closeInventory();
                }
                else {
                    inventoryClickEvent.setCancelled(true);
                    inventoryClickEvent.getWhoClicked().closeInventory();
                }
            }
        }
        if (inventoryClickEvent.getCurrentItem().getType() == Material.IRON_SWORD) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("shop.gear").replace('&', '?'))) {
                inventoryClickEvent.getWhoClicked().openInventory(Shop.gearShop());
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.APPLE) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("shop.food").replace('&', '?'))) {
                inventoryClickEvent.getWhoClicked().openInventory(Shop.foodShop());
                inventoryClickEvent.setCancelled(true);
            }
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.apple", 15))) {
                Shop.buy(25, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.APPLE, 15));
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.POTION) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("shop.potions").replace('&', '?'))) {
                inventoryClickEvent.getWhoClicked().openInventory(Shop.potionShop());
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(potionName("shop.invisibility"))) {
                Shop.buy(750, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Main.nmsH.potion(PotionType.INVISIBILITY)));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(potionName("shop.regeneration"))) {
                Shop.buy(600, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Main.nmsH.potion(PotionType.REGEN)));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(potionName("shop.swiftness"))) {
                Shop.buy(350, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Main.nmsH.potion(PotionType.SPEED)));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(potionName("shop.flamer"))) {
                Shop.buy(200, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Main.nmsH.potion(PotionType.FIRE_RESISTANCE)));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(potionName("shop.rabbitp"))) {
                Shop.buy(200, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Main.nmsH.potion(PotionType.JUMP)));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(potionName("shop.instanth"))) {
                Shop.buy(250, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Main.nmsH.potion(PotionType.INSTANT_HEAL)));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(potionName("shop.hurting"))) {
                Shop.buy(400, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Main.nmsH.potion(PotionType.INSTANT_DAMAGE)));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(potionName("shop.deadlyp"))) {
                Shop.buy(1000, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Main.nmsH.potion(PotionType.POISON)));
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.SEA_LANTERN) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("shop.vanity").replace('&', '?'))) {
                inventoryClickEvent.getWhoClicked().openInventory(Shop.vanityShop());
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.lantern", 32))) {
                Shop.buy(50, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.SEA_LANTERN, 32));
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.RAILS) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("shop.specials").replace('&', '?'))) {
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.sword", 1))) {
                Shop.buy(250, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.DIAMOND_SWORD, 1));
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.DIAMOND_PICKAXE) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.pickaxe", 1))) {
                Shop.buy(150, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.DIAMOND_PICKAXE, 1));
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.DIAMOND_HELMET) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.helmet", 1))) {
                Shop.buy(250, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.DIAMOND_HELMET, 1));
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.DIAMOND_CHESTPLATE) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.chestplate", 1))) {
                Shop.buy(350, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.DIAMOND_LEGGINGS) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.leggings", 1))) {
                Shop.buy(200, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.DIAMOND_LEGGINGS, 1));
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.DIAMOND_BOOTS) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.boots", 1))) {
                Shop.buy(150, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.DIAMOND_BOOTS, 1));
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.BOW) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.bow", 1))) {
                Shop.buy(250, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.BOW, 1));
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.ARROW) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.arrows", 16))) {
                Shop.buy(400, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.ARROW, 16));
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.MELON) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.melon", 15))) {
                Shop.buy(20, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.MELON, 15));
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.CARROT_ITEM) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.carrot", 15))) {
                Shop.buy(25, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.CARROT_ITEM, 15));
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.BREAD) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.bread", 5))) {
                Shop.buy(35, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.BREAD, 5));
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.COOKED_CHICKEN) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.chicken", 5))) {
                Shop.buy(40, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.COOKED_CHICKEN, 5));
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.COOKED_BEEF) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.steak", 5))) {
                Shop.buy(45, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.COOKED_BEEF, 5));
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.CAKE) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.cake", 3))) {
                Shop.buy(50, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.CAKE, 3));
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.GOLDEN_APPLE) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.golden", 1))) {
                Shop.buy(1000, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.GOLDEN_APPLE));
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.MILK_BUCKET) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.milk", 1))) {
                Shop.buy(25, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.MILK_BUCKET));
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.EXP_BOTTLE) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.exp", 1))) {
                Shop.buy(2000, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.EXP_BOTTLE));
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.PRISMARINE) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.prismarine", 32))) {
                Shop.buy(50, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.PRISMARINE, 32));
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.GLOWSTONE) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.glowstone", 32))) {
                Shop.buy(50, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.GLOWSTONE, 32));
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.STAINED_CLAY) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("join").replace("{team}", Messages.getMsg().getString("gold-team")).replace('&', '?'))) {
                if (Main.goldPlayers.size() > Main.magentaPlayers.size() || Main.goldPlayers.size() > Main.greenPlayers.size() || Main.goldPlayers.size() > Main.bluePlayers.size()) {
                    inventoryClickEvent.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("cannot-join-team").replace('&', '?'));
                    inventoryClickEvent.setCancelled(true);
                    inventoryClickEvent.getWhoClicked().closeInventory();
                    return;
                }
                Main.goldPlayers.remove(inventoryClickEvent.getWhoClicked());
                Main.bluePlayers.remove(inventoryClickEvent.getWhoClicked());
                Main.magentaPlayers.remove(inventoryClickEvent.getWhoClicked());
                Main.greenPlayers.remove(inventoryClickEvent.getWhoClicked());
                Main.goldPlayers.add((Player)inventoryClickEvent.getWhoClicked());
                inventoryClickEvent.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("team-queued").replace("{team}", Messages.getMsg().getString("gold-team")).replace('&', '?'));
                inventoryClickEvent.setCancelled(true);
                final ItemStack itemStack = new ItemStack(Material.STAINED_CLAY, 1, (short)4);
                final ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName(Messages.getMsg().getString("normal-team").replace('&', '?'));
                final ArrayList<String> lore = new ArrayList<String>();
                final Iterator<String> iterator = Messages.getMsg().getStringList("choose-team").iterator();
                while (iterator.hasNext()) {
                    lore.add(iterator.next().replace('&', '?'));
                }
                itemMeta.setLore((List<String>)lore);
                itemStack.setItemMeta(itemMeta);
                inventoryClickEvent.getWhoClicked().closeInventory();
                inventoryClickEvent.getWhoClicked().getInventory().setItem(4, itemStack);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("join").replace("{team}", Messages.getMsg().getString("magenta-team")).replace('&', '?'))) {
                if (Main.magentaPlayers.size() > Main.goldPlayers.size() || Main.magentaPlayers.size() > Main.greenPlayers.size() || Main.magentaPlayers.size() > Main.bluePlayers.size()) {
                    inventoryClickEvent.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("cannot-join-team").replace('&', '?'));
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                Main.goldPlayers.remove(inventoryClickEvent.getWhoClicked());
                Main.bluePlayers.remove(inventoryClickEvent.getWhoClicked());
                Main.magentaPlayers.remove(inventoryClickEvent.getWhoClicked());
                Main.greenPlayers.remove(inventoryClickEvent.getWhoClicked());
                Main.magentaPlayers.add((Player)inventoryClickEvent.getWhoClicked());
                inventoryClickEvent.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("team-queued").replace("{team}", Messages.getMsg().getString("magenta-team")).replace('&', '?'));
                final ItemStack itemStack2 = new ItemStack(Material.STAINED_CLAY, 1, (short)2);
                final ItemMeta itemMeta2 = itemStack2.getItemMeta();
                itemMeta2.setDisplayName(Messages.getMsg().getString("normal-team").replace('&', '?'));
                final ArrayList<String> lore2 = new ArrayList<String>();
                final Iterator<String> iterator2 = Messages.getMsg().getStringList("choose-team").iterator();
                while (iterator2.hasNext()) {
                    lore2.add(iterator2.next().replace('&', '?'));
                }
                itemMeta2.setLore((List<String>)lore2);
                itemStack2.setItemMeta(itemMeta2);
                inventoryClickEvent.getWhoClicked().getInventory().setItem(4, itemStack2);
                inventoryClickEvent.setCancelled(true);
                inventoryClickEvent.getWhoClicked().closeInventory();
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("join").replace("{team}", Messages.getMsg().getString("green-team")).replace('&', '?'))) {
                if (Main.greenPlayers.size() > Main.goldPlayers.size() || Main.greenPlayers.size() > Main.magentaPlayers.size() || Main.greenPlayers.size() > Main.bluePlayers.size()) {
                    inventoryClickEvent.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("cannot-join-team").replace('&', '?'));
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                Main.goldPlayers.remove(inventoryClickEvent.getWhoClicked());
                Main.bluePlayers.remove(inventoryClickEvent.getWhoClicked());
                Main.magentaPlayers.remove(inventoryClickEvent.getWhoClicked());
                Main.greenPlayers.remove(inventoryClickEvent.getWhoClicked());
                Main.greenPlayers.add((Player)inventoryClickEvent.getWhoClicked());
                inventoryClickEvent.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("team-queued").replace("{team}", Messages.getMsg().getString("green-team")).replace('&', '?'));
                final ItemStack itemStack3 = new ItemStack(Material.STAINED_CLAY, 1, (short)5);
                final ItemMeta itemMeta3 = itemStack3.getItemMeta();
                itemMeta3.setDisplayName(Messages.getMsg().getString("normal-team").replace('&', '?'));
                final ArrayList<String> lore3 = new ArrayList<String>();
                final Iterator<String> iterator3 = Messages.getMsg().getStringList("choose-team").iterator();
                while (iterator3.hasNext()) {
                    lore3.add(iterator3.next().replace('&', '?'));
                }
                itemMeta3.setLore((List<String>)lore3);
                itemStack3.setItemMeta(itemMeta3);
                inventoryClickEvent.getWhoClicked().getInventory().setItem(4, itemStack3);
                inventoryClickEvent.setCancelled(true);
                inventoryClickEvent.getWhoClicked().closeInventory();
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("join").replace("{team}", Messages.getMsg().getString("blue-team")).replace('&', '?'))) {
                if (Main.bluePlayers.size() > Main.goldPlayers.size() || Main.bluePlayers.size() > Main.magentaPlayers.size() || Main.bluePlayers.size() > Main.greenPlayers.size()) {
                    inventoryClickEvent.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("cannot-join-team").replace('&', '?'));
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                Main.goldPlayers.remove(inventoryClickEvent.getWhoClicked());
                Main.bluePlayers.remove(inventoryClickEvent.getWhoClicked());
                Main.magentaPlayers.remove(inventoryClickEvent.getWhoClicked());
                Main.greenPlayers.remove(inventoryClickEvent.getWhoClicked());
                Main.bluePlayers.add((Player)inventoryClickEvent.getWhoClicked());
                inventoryClickEvent.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("team-queued").replace("{team}", Messages.getMsg().getString("blue-team")).replace('&', '?'));
                final ItemStack itemStack4 = new ItemStack(Material.STAINED_CLAY, 1, (short)11);
                final ItemMeta itemMeta4 = itemStack4.getItemMeta();
                itemMeta4.setDisplayName(Messages.getMsg().getString("normal-team").replace('&', '?'));
                final ArrayList<String> lore4 = new ArrayList<String>();
                final Iterator<String> iterator4 = Messages.getMsg().getStringList("choose-team").iterator();
                while (iterator4.hasNext()) {
                    lore4.add(iterator4.next().replace('&', '?'));
                }
                itemMeta4.setLore((List<String>)lore4);
                itemStack4.setItemMeta(itemMeta4);
                inventoryClickEvent.getWhoClicked().getInventory().setItem(4, itemStack4);
                inventoryClickEvent.setCancelled(true);
                inventoryClickEvent.getWhoClicked().closeInventory();
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.orangeclay", 32))) {
                Shop.buy(50, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.STAINED_CLAY, 32, (short)1));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.magentaclay", 32))) {
                Shop.buy(50, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.STAINED_CLAY, 32, (short)2));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.lightblueclay", 32))) {
                Shop.buy(50, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.STAINED_CLAY, 32, (short)3));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.yellowclay", 32))) {
                Shop.buy(50, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.STAINED_CLAY, 32, (short)4));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.limeclay", 32))) {
                Shop.buy(50, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.STAINED_CLAY, 32, (short)5));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.pinkclay", 32))) {
                Shop.buy(50, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.STAINED_CLAY, 32, (short)6));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.cyanclay", 32))) {
                Shop.buy(50, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.STAINED_CLAY, 32, (short)9));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.purpleclay", 32))) {
                Shop.buy(50, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.STAINED_CLAY, 32, (short)10));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.blueclay", 32))) {
                Shop.buy(50, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.STAINED_CLAY, 32, (short)11));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.redclay", 32))) {
                Shop.buy(50, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.STAINED_CLAY, 32, (short)14));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.yellowclay", 32))) {
                Shop.buy(50, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.STAINED_CLAY, 32, (short)4));
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.STAINED_GLASS) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.orangeglass", 32))) {
                Shop.buy(50, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.STAINED_GLASS, 32, (short)1));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.magentaglass", 32))) {
                Shop.buy(50, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.STAINED_GLASS, 32, (short)2));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.lightblueglass", 32))) {
                Shop.buy(50, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.STAINED_GLASS, 32, (short)3));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.yellowglass", 32))) {
                Shop.buy(50, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.STAINED_GLASS, 32, (short)4));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.limeglass", 32))) {
                Shop.buy(50, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.STAINED_GLASS, 32, (short)5));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.pinkglass", 32))) {
                Shop.buy(50, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.STAINED_GLASS, 32, (short)6));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.cyanglass", 32))) {
                Shop.buy(50, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.STAINED_GLASS, 32, (short)9));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.purpleglass", 32))) {
                Shop.buy(50, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.STAINED_GLASS, 32, (short)10));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.blueglass", 32))) {
                Shop.buy(50, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.STAINED_GLASS, 32, (short)11));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.redglass", 32))) {
                Shop.buy(50, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.STAINED_GLASS, 32, (short)14));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.yellowglass", 32))) {
                Shop.buy(50, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.STAINED_GLASS, 32, (short)4));
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.BANNER) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.redbanner", 5))) {
                Shop.buy(100, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.BANNER, 5, (short)1));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.greenbanner", 5))) {
                Shop.buy(100, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.BANNER, 5, (short)10));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.yellowbanner", 5))) {
                Shop.buy(100, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.BANNER, 5, (short)11));
                inventoryClickEvent.setCancelled(true);
            }
            else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.bluebanner", 5))) {
                Shop.buy(100, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.BANNER, 5, (short)4));
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.MAGMA_CREAM) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("random-team").replace('&', '?'))) {
                Main.goldPlayers.remove(inventoryClickEvent.getWhoClicked());
                Main.magentaPlayers.remove(inventoryClickEvent.getWhoClicked());
                Main.greenPlayers.remove(inventoryClickEvent.getWhoClicked());
                Main.bluePlayers.remove(inventoryClickEvent.getWhoClicked());
                if (Main.goldPlayers.size() < Main.magentaPlayers.size() && Main.goldPlayers.size() < Main.bluePlayers.size() && Main.goldPlayers.size() < Main.greenPlayers.size()) {
                    Main.goldPlayers.add((Player)inventoryClickEvent.getWhoClicked());
                    inventoryClickEvent.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("team-queued").replace("{team}", Messages.getMsg().getString("gold-team")).replace('&', '?'));
                }
                if (Main.magentaPlayers.size() < Main.greenPlayers.size() && Main.magentaPlayers.size() < Main.goldPlayers.size() && Main.magentaPlayers.size() < Main.bluePlayers.size()) {
                    Main.magentaPlayers.add((Player)inventoryClickEvent.getWhoClicked());
                    inventoryClickEvent.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("team-queued").replace("{team}", Messages.getMsg().getString("magenta-team")).replace('&', '?'));
                }
                if (Main.greenPlayers.size() < Main.magentaPlayers.size() && Main.greenPlayers.size() < Main.goldPlayers.size() && Main.greenPlayers.size() < Main.bluePlayers.size()) {
                    Main.greenPlayers.add((Player)inventoryClickEvent.getWhoClicked());
                    inventoryClickEvent.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("team-queued").replace("{team}", Messages.getMsg().getString("green-team")).replace('&', '?'));
                }
                if (Main.bluePlayers.size() < Main.magentaPlayers.size() && Main.bluePlayers.size() < Main.goldPlayers.size() && Main.bluePlayers.size() < Main.greenPlayers.size()) {
                    Main.bluePlayers.add((Player)inventoryClickEvent.getWhoClicked());
                    inventoryClickEvent.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("team-queued").replace("{team}", Messages.getMsg().getString("blue-team")).replace('&', '?'));
                }
                inventoryClickEvent.setCancelled(true);
                inventoryClickEvent.getWhoClicked().closeInventory();
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.BARRIER) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("shop.back").replace('&', '?'))) {
                inventoryClickEvent.getWhoClicked().openInventory(Shop.mainShop());
                inventoryClickEvent.setCancelled(true);
            }
        }
        else if (inventoryClickEvent.getCurrentItem().getType() == Material.INK_SACK && inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.lapis", 1))) {
            Shop.buy(1000, (Player)inventoryClickEvent.getWhoClicked(), new ItemStack(Material.INK_SACK, 1, (short)4));
            inventoryClickEvent.setCancelled(true);
        }
    }
    
    private static String itemName(final String s, final Integer n) {
        return Messages.getMsg().getString("shop.itemformat").replace("{item}", Messages.getMsg().getString(s)).replace("{amount}", String.valueOf(n)).replace('&', '?');
    }
    
    private static String potionName(final String s) {
        return Messages.getMsg().getString("shop.potionformat").replace("{potion}", Messages.getMsg().getString(s)).replace("{amount}", String.valueOf(1)).replace('&', '?');
    }
}
