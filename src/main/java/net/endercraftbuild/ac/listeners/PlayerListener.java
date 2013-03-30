package net.endercraftbuild.ac.listeners;


import java.util.ArrayList;
import java.util.List;

import net.endercraftbuild.ac.ACMain;
import net.endercraftbuild.ac.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class PlayerListener implements Listener {


	private List<String> justJumped = new ArrayList<String>();

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
		if(event.getEntity() instanceof Player)
		{
		Player player = (Player) event.getEntity();
		Integer playerexp = player.getTotalExperience();


		if(event.getCause() == DamageCause.FALL) 
		{
		
			if(player.isSneaking() && event.getDamage() < 14)
				
			{		
			if(playerexp < 6)
				player.sendMessage(plugin.prefix + ChatColor.RED + "You need more energy!");
						else {
						Utils.setEnergy(player, playerexp -= 6);
						event.setCancelled(true);
						player.sendMessage(plugin.prefix + ChatColor.RED + "**ROLL**");
						}
				}
						else {
						if(event.getDamage() > 13) {
							player.sendMessage(plugin.prefix + ChatColor.RED + "ROLL FAILED!");
					}
				}
			}
		}
	}
	@EventHandler(ignoreCancelled = true)
	public void Cloak(PlayerMoveEvent event) { //Make them vanish in the shadows...


		Player p = event.getPlayer();
		Integer playerexp = p.getTotalExperience();

		Location l = p.getLocation();


		Location blockaboveplayer = new Location(l.getWorld(), l.getX(),
				l.getY() + 1, l.getZ());
		
		if(p.hasPotionEffect(PotionEffectType.INVISIBILITY))
			return;
		if(playerexp < 6) 
			return;
		
		if(blockaboveplayer != null)
		{
			int lightlevel = blockaboveplayer.getBlock().getLightLevel();
			if(lightlevel <= 1.5 && p.isSneaking())
			{
				
					Utils.setEnergy(p, playerexp -= 8);
					p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 160, 1));

					p.sendMessage(plugin.prefix + ChatColor.RED + "You are now hidden in the shadows");
				}
			}
		}
	
	@EventHandler(ignoreCancelled = true)
	public void JoinStartTask(PlayerLoginEvent event) { //player join - Start the EXP giving task for new joiners
		Player player = event.getPlayer();
		Integer playerexp = player.getTotalExperience();
		if(playerexp == 16)
			return;
		Utils.setEnergy(player, playerexp += 1);
		//Give kit on players first join or something
	}
	@EventHandler(ignoreCancelled = true)
	public void Respawn(PlayerRespawnEvent event) { //player dies and looses energy - start task
		Player player = event.getPlayer();
		Integer playerexp = player.getTotalExperience();
		if(playerexp >= 16)
			return;
		Utils.setEnergy(player, playerexp += 1);
		//Give kit when they respawn (The Feather for double jumps, etc.)
	}
	
	@EventHandler(ignoreCancelled = true)
	public void PreventNaturalGain(PlayerExpChangeEvent event) { //Prevent getting exp naturaly
		//event.setCancelled(true); This event isn't cancellable... 
		//TODO : Cancel all world exp drops
	}
}