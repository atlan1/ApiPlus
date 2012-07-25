package team.ApiPlus.API.Event;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MobMoveEvent extends Event {
	 private static final HandlerList handlers = new HandlerList();
	 private static boolean canceled = false;
	 private Entity ent = null;
	 private Location from = null;
	 private Location to = null;

	 public MobMoveEvent(Entity base, Location from, Location to) {
		setEnt(base);
		setFrom(from);
		setTo(to);
	 }

	public HandlerList getHandlers() {
		 return handlers;
	 }
	    
	 public static HandlerList getHandlerList() {
		 return handlers;
	 }

	 
	 public boolean isCancelled() {
		 return canceled;
	 }

	 public void setCancelled(boolean cancel) {
		 canceled = cancel;
	 }

	public Entity getEnt() {
		return ent;
	}

	public void setEnt(Entity ent) {
		this.ent = ent;
	}

	public Location getFrom() {
		return from;
	}

	public void setFrom(Location from) {
		this.from = from;
	}

	public Location getTo() {
		return to;
	}

	public void setTo(Location to) {
		this.to = to;
	}
}