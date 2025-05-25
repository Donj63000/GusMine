package org.example;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Commande /armure : donne l'Armure du roi GIDON.
 */
public final class Armure implements CommandExecutor {

    private final JavaPlugin plugin;

    public Armure(JavaPlugin plugin) {
        this.plugin = plugin;
        if (plugin.getCommand("armure") != null) {
            plugin.getCommand("armure").setExecutor(this);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Cette commande doit être exécutée par un joueur.");
            return true;
        }

        if (!command.getName().equalsIgnoreCase("armure")) {
            return false;
        }

        giveArmor(player);
        player.sendMessage(ChatColor.GREEN + "Tu as reçu l'Armure du roi GIDON !");
        return true;
    }

    /**
     * Crée et donne l'armure au joueur.
     */
    private void giveArmor(Player player) {
        player.getInventory().addItem(createPiece(Material.NETHERITE_HELMET, ChatColor.GOLD + "Casque du roi GIDON"));
        player.getInventory().addItem(createPiece(Material.NETHERITE_CHESTPLATE, ChatColor.GOLD + "Plastron du roi GIDON"));
        player.getInventory().addItem(createPiece(Material.NETHERITE_LEGGINGS, ChatColor.GOLD + "Pantalon du roi GIDON"));
        player.getInventory().addItem(createPiece(Material.NETHERITE_BOOTS, ChatColor.GOLD + "Chaussures du roi GIDON"));
    }

    /**
     * Construit une pièce d'armure enchantée.
     */
    private ItemStack createPiece(Material material, String name) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.addEnchant(Enchantment.PROTECTION, 4, true);
            meta.addEnchant(Enchantment.UNBREAKING, 3, true);
            meta.addEnchant(Enchantment.MENDING, 1, true);
            item.setItemMeta(meta);
        }
        return item;
    }
}
