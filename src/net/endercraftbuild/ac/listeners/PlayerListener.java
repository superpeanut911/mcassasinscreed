package net.endercraftbuild.ac.listeners;

import net.endercraftbuild.ac.ACMain;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class PlayerListener implements Listener {

	private ACMain plugin;

	public PlayerListener(ACMain plugin) {
		this.plugin = plugin;
	}
	//Just throwing down ideas, will become more classes and stuff later ;P
	@EventHandler(ignoreCancelled = true)
	public void onPlayerHitPlayer(EntityDamageByEntityEvent event) { 
		if (!(event.getEntity() instanceof Player))
			return;
			//If they are in the shadows? Hidden assasination?
			//If they have x amount of mana?
			//If they are holding an assasination weapon? (Hidden blade, dagger)
			//set damage to higher amount? Silent assasination?
		}
				
	@EventHandler(ignoreCancelled = true)
	public void SneakFall(EntityDamageEvent event) {
		Player player = (Player) event.getEntity();
		if(event.getEntity() instanceof Player)
			
		if(event.getCause() == DamageCause.FALL) 
			
		//check if they have enough mana (small amount)
		if(player.isSneaking() && event.getDamage() > 1)	
		{
			event.setCancelled(true);
			//subtract some mana
		}
	}
}
	
	
