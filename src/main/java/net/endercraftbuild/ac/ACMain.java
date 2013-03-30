package net.endercraftbuild.ac;

import java.io.File;

import net.endercraftbuild.ac.commands.ReloadCmd;
import net.endercraftbuild.ac.listeners.DoubleJump;
import net.endercraftbuild.ac.listeners.EnergyListener;
import net.endercraftbuild.ac.listeners.PlayerListener;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


public class ACMain extends JavaPlugin {
	
	public String prefix = "[" + ChatColor.DARK_RED + ChatColor.BOLD + "ECB AC" + ChatColor.RESET + "] ";
	
	private Economy economy;
	
	//Use RandomChests plugin to randomize items
	//Use gold is money plugin to make gold the physical econ
	//TODO Mana system, Parkour verticle climbing(Double jump is good enough for this), Assassinations.
	
	public void onEnable() {
		//if(!(getServer().getIp() == "127.0.0.1"))
			//setEnabled(false);
		//else
			//Will add in final release! Restrict the plugin for ECB only
		
		if (!new File(this.getDataFolder().getPath() + File.separatorChar + "config.yml").exists())
			saveDefaultConfig();
		
		if (!setupEconomy())
			getLogger().warning("Vault not found!");
		registerListeners();
		registerCommands();
		registerDynamicPermissions();
	}
	
	public void onDisable() {
		
	}
	
	public void reload() {
		reloadConfig();
	}
	
	
	private void registerListeners() {
		getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
		getServer().getPluginManager().registerEvents(new EnergyListener(this), this);
		getServer().getPluginManager().registerEvents(new DoubleJump(this), this);
	}
	
	private void registerCommands() {
		getCommand("acreload").setExecutor(new ReloadCmd(this));
	}
	
	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null)
			return false;
	
		RegisteredServiceProvider<Economy> provider = getServer().getServicesManager().getRegistration(Economy.class);
		if (provider == null)
			return false;
	
		economy = provider.getProvider();
		return economy != null;
	}
	
	public Economy getEconomy() {
		return economy;
	}

	private void registerDynamicPermissions() {
		ConfigurationSection allKits = getConfig().getConfigurationSection("kits.ac");
		for (String kitName : allKits.getKeys(false)) {
			Permission permission = new Permission("ac.kits." + kitName, PermissionDefault.FALSE);
			getServer().getPluginManager().addPermission(permission);
		}
	}

}

