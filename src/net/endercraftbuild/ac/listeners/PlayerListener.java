package net.endercraftbuild.ac.listeners;

import net.endercraftbuild.ac.ACMain;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
			{
			//check if they have enough mana (small amount)
			if(player.isSneaking() && event.getDamage() > 1)	
			{
			event.setCancelled(true);
			//subtract some mana
			}
		}
}
	@EventHandler(ignoreCancelled = true)
	public void Cloak(PlayerMoveEvent event) { //Make them vanish in the shadows...


		Player p = event.getPlayer();

		Location l = p.getLocation();


		Location blockbellowplayer = new Location(l.getWorld(), l.getX(),
				l.getY() - 1, l.getZ());
		if(p.hasPotionEffect(PotionEffectType.INVISIBILITY))
			return;

		if(blockbellowplayer != null)
		{
			int lightlevel = blockbellowplayer.getBlock().getLightLevel();
			if(lightlevel < 1 && p.isSneaking())
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 240, 1));
				p.sendMessage(plugin.prefix + ChatColor.RED + "You are now hidden in the shadows");
			}
		}
	}
}





