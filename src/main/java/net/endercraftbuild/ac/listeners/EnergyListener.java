package main.java.net.endercraftbuild.ac.listeners;

import main.java.net.endercraftbuild.ac.ACMain;
import main.java.net.endercraftbuild.ac.events.EnergyChangeEvent;
import main.java.net.endercraftbuild.ac.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;

public class EnergyListener implements Listener {

	private ACMain plugin;
	
	public EnergyListener(ACMain plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(ignoreCancelled = true)
	public void ManaRegen(EnergyChangeEvent event) {
		final Player player = event.getPlayer();
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

			public void run() {
				Integer playerexp = player.getTotalExperience();
				if(playerexp >= 16)
					return;
			
				Utils.setEnergy(player, playerexp += 1);
			}
		}, 60L);
	}
}
