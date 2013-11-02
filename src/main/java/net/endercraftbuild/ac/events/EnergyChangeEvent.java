package main.java.net.endercraftbuild.ac.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class EnergyChangeEvent extends Event {
	
	 private static final HandlerList handlers = new HandlerList();
	 private final Player player;
	    
	    public EnergyChangeEvent(Player player) {
	    	this.player = player;
	    }
	 
	    public HandlerList getHandlers() {
	        return handlers;
	    }
	 
	    public static HandlerList getHandlerList() {
	        return handlers;
	    }
	    public Player getPlayer() {
	    	return player;
	    }
	}


