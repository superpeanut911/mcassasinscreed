package main.java.net.endercraftbuild.ac.utils;

import java.util.List;
import java.util.Map;

import main.java.net.endercraftbuild.ac.events.EnergyChangeEvent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils {
	
	
@SuppressWarnings("deprecation")
public static void giveKit(Player player, ConfigurationSection config) {//I messed this up
	
	PlayerInventory inventory = player.getInventory();

	for (String kitName : config.getConfigurationSection("kits.ac").getKeys(false)) {
		if (!player.hasPermission("ac.kits." + kitName))
			continue;

		List<Map<?, ?>> items = config.getMapList("kits.ac." + kitName);
		for (Map<?, ?> item : items) {
			ItemStack itemStack = new ItemStack((Integer) item.get("id"), 1);

			if (item.containsKey("quantity"))
				itemStack.setAmount((Integer) item.get("quantity"));
			if (item.containsKey("name"))
				setItemName(itemStack, (String) item.get("name"));

			if (item.containsKey("wear"))
				wearItem(player, itemStack, (String) item.get("wear"));
			else
				inventory.addItem(itemStack);
		}
	}

	player.updateInventory();
}

public static void wearItem(Player player, ItemStack itemStack, String location) {
	switch (location) {
	case "chest":
		player.getInventory().setChestplate(itemStack);
		break;
	case "head":
		player.getInventory().setHelmet(itemStack);
		break;
	case "legs":
		player.getInventory().setLeggings(itemStack);
		break;
	case "feet":
		player.getInventory().setBoots(itemStack);
		break;
	}
}
public static ItemStack setItemName(ItemStack is, String str) {
	ItemMeta im = is.getItemMeta();
	im.setDisplayName(str);
	is.setItemMeta(im);
	return is;
	}

public static void setEnergy(final Player player, Integer energy) {
	player.setTotalExperience(energy);
    player.setLevel(0);
    player.setExp(0);
    for(;energy > player.getExpToLevel();)
    {
        energy -= player.getExpToLevel();
        player.setLevel(player.getLevel()+1);
    }
    float xp = (float)energy / (float)player.getExpToLevel();
    player.setExp(xp);
	Integer playerexp = player.getTotalExperience();
	EnergyChangeEvent eevent = new EnergyChangeEvent(player);
	// Call the event
	Bukkit.getServer().getPluginManager().callEvent(eevent);
	if(playerexp == 4 || playerexp == 8 || playerexp == 12 || playerexp == 16) {
	player.sendMessage("[" + ChatColor.DARK_RED + ChatColor.BOLD + "ECB AC" + ChatColor.RESET + "] " + ChatColor.RED + "Energy: " + ChatColor.GOLD + playerexp.toString() + "/16");
	}
}
}
