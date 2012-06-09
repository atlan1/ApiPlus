package team.ApiPlus.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.getspout.spoutapi.inventory.SpoutItemStack;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;

import team.ApiPlus.ApiPlus;

/**
 * Utility Class containing cross-class methods.
 * @author SirTyler (Tyler Martin)
 * @version 1.0
 */
public class Utils {
	private static boolean useDebug = true;
	
	/**
	 * Method used for writing a Info message to the Logger.
	 * @param message String message to write.
	 */
	public synchronized static void info(String message) {
		Bukkit.getLogger().log(Level.INFO, String.format("[API+] %s", message));
	}
	
	/**
	 * Method used for writing multiple Info messages to the Logger.
	 * @param message String Array of messages to write.
	 */
	public synchronized static void info(String[] message) {
		for(String s : message) {
			Bukkit.getLogger().log(Level.INFO, String.format("[API+] %s", s));
		}
	}
	
	/**
	 * Method used for writing a Warning message to the Logger.
	 * @param message String message to write.
	 */
	public synchronized static void warning(String message) {
		Bukkit.getLogger().log(Level.WARNING, String.format("[API+] %s", message));
	}
	
	/**
	 * Method used for writing multiple Warning messages to the Logger.
	 * @param message String Array of messages to write.
	 */
	public synchronized static void warning(String[] message) {
		for(String s : message) {
			Bukkit.getLogger().log(Level.WARNING, String.format("[API+] %s", s));
		}
	}
	
	/**
	 * Method used for setting Debug mode of API.
	 * @param b Boolean value to be set.
	 */
	public synchronized static void setDebug(boolean b) {
		useDebug = b;
	}
	
	/**
	 * Method used for a writing Debug message to the Logger.
	 * @param message String message to write.
	 */
	public synchronized static void debug(String message) {
		if(useDebug == false) return;
		Bukkit.getLogger().log(Level.INFO, String.format("[API+][Debug] %s", message));
	}
	
	public synchronized static void debug(Exception e) {
		if(useDebug == false) return;
		Bukkit.getLogger().log(Level.INFO, String.format("[API+][Debug] %s %s", e.getLocalizedMessage(), e.getCause()));
	}
	
	/**
	 * Method used for writing multiple Debug messages to the Logger.
	 * @param message String Array of messages to write. 
	 */
	public synchronized static void debug(String[] message) {
		if(useDebug == false) return;
		for(String s : message) {
			Bukkit.getLogger().log(Level.INFO, String.format("[API+][Debug] %s", s));
		}
	}
	
	/**
	 * Method used for checking if List contains CustomItems.
	 * @param items List<ItemStack> to check.
	 * @return boolean True if contains CustomItems, False if not.
	 */
	public synchronized static boolean containsCustomItems(List<ItemStack> items){
		for(ItemStack i : items){
			if(isCustomItem(i)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method used for checking if ItemStack is CustomItem.
	 * @param item ItemStack to check.
	 * @return boolean True if is CustomItem, False if not.
	 */
	public synchronized static boolean isCustomItem(ItemStack item){
		return new SpoutItemStack(item).isCustomItem();
	}
	
	/**
	 * Method used for getting List<Entity> of Nearby Entities of Location.
	 * @param loc Location to use as reference.
	 * @param radiusX Double value to use as max radius on X axis.
	 * @param radiusY Double value to use as max radius on Y axis.
	 * @param radiusZ Double value to use as max radius on Z axis.
	 * @return List<Entity> List of Entities within range.
	 */
	public synchronized static List<Entity> getNearbyEntities(Location loc, double radiusX, double radiusY, double radiusZ) {
		Entity e = loc.getWorld().spawn(loc, ExperienceOrb.class);
		@SuppressWarnings("unchecked")
		List<Entity> entities = (List<Entity>) ((ArrayList<Entity>) e.getNearbyEntities(radiusX, radiusY, radiusZ)).clone();
		e.remove();
		return entities;
	}
	
	/**
	 * Method used for getting RandomInteger based on start and stop positions.
	 * @param start Integer to start with.
	 * @param end Integer to end with.
	 * @return Integer Random Integer created.
	 */
	public synchronized static int getRandomInteger(int start, int end) {
		Random rand = new Random();
		return start + rand.nextInt(end + 1);
	}
	
	/**
	 * Method used for getting the Direction of Location as Vector.
	 * @param l Location to be referenced.
	 * @return Vector Vector of Direction.
	 */
	public synchronized static Vector getDirection(Location l) {
		Vector vector = new Vector();

		double rotX = l.getYaw();
		double rotY = l.getPitch();

		vector.setY(-Math.sin(Math.toRadians(rotY)));

		double h = Math.cos(Math.toRadians(rotY));

		vector.setX(-h * Math.sin(Math.toRadians(rotX)));
		vector.setZ(h * Math.cos(Math.toRadians(rotX)));

		return vector;
	}
	
	/**
	 * Method used for setting the Location to look at another Location.
	 * @param loc Location to be referenced.
	 * @param lookat Location to look at.
	 * @return Location Location facing lookat Location.
	 */
	public synchronized static Location setLookingAt(final Location loc, final Location lookat) {
		Location location = loc.clone();
		double dx = lookat.getX() - location.getX();
		double dy = lookat.getY() - location.getY();
		double dz = lookat.getZ() - location.getZ();

		if (dx != 0) {
			if (dx < 0) {
				location.setYaw((float) (1.5 * Math.PI));
			} else {
				location.setYaw((float) (0.5 * Math.PI));
			}
			location.setYaw((float) location.getYaw() - (float) Math.atan(dz / dx));
		} else if (dz < 0) {
			location.setYaw((float) Math.PI);
		}
		double dxz = Math.sqrt(Math.pow(dx, 2) + Math.pow(dz, 2));
		location.setPitch((float) - Math.atan(dy / dxz));
		location.setYaw(-location.getYaw() * 180f / (float) Math.PI);
		location.setPitch(location.getPitch() * 180f / (float) Math.PI);

		return location;
	}
	
	/**
	 * Method used for getting location of Player's Hand.
	 * @param p Player to be referenced.
	 * @return Location of Player's Hand.
	 */
	public synchronized static Location getHandLocation(Player p) {
		Location loc = p.getLocation().clone();

		double a = loc.getYaw() / 180D * Math.PI + Math.PI / 2;
		double l = Math.sqrt(0.8D * 0.8D + 0.4D * 0.4D);

		loc.setX(loc.getX() + l * Math.cos(a) - 0.8D * Math.sin(a));
		loc.setY(loc.getY() + p.getEyeHeight() - 0.2D);
		loc.setZ(loc.getZ() + l * Math.sin(a) + 0.8D * Math.cos(a));
		return loc;
	}
	
	/**
	 * Method used for getting a Sphere around given Location with given radius.
	 * @param center Location to use as center of sphere.
	 * @param radius Double to use as radius around center.
	 * @return List<Block> List of blocks that make of Sphere.
	 */
	public synchronized static List<Block> getSphere(Location center, double radius) {
		List<Block> blockList = new ArrayList<Block>();
	    radius += 0.5;
	    final double radSquare = Math.pow(2, radius);
	    final int radCeil = (int) Math.ceil(radius);
	    final double centerX = center.getX();
	    final double centerY = center.getY();
	    final double centerZ = center.getZ();
	 
	    for(double x = centerX - radCeil; x <= centerX + radCeil; x++) {
	        for(double y = centerY - radCeil; y <= centerY + radCeil; y++) {
	            for(double z = centerZ - radCeil; z <= centerZ + radCeil; z++) {
	                double distSquare = Math.pow(2, x - centerX) + Math.pow(2,y - centerY) + Math.pow(2,z - centerZ);
	                if (distSquare > radSquare)
	                    continue;
	                Location currPoint = new Location(center.getWorld(), x, y, z);
	                blockList.add(currPoint.getBlock());
	            }
	        }
	    }
	    return blockList;
	}
	
	/**
	 * Method used for checking if TNT is allowed in given Region.
	 * @param loc Location to be referenced.
	 * @return boolean True if allowed, False if not.
	 */
	public synchronized static boolean tntIsAllowedInRegion(Location loc) {
		if (ApiPlus.hooks.containsKey("WorldGuard")) {
			WorldGuardPlugin wg = (WorldGuardPlugin) ApiPlus.hooks.get("WorldGuard");
			if (!wg.getGlobalRegionManager().allows(DefaultFlag.TNT, loc)) {
				return false;
			} else
				return true;
		} else
			return true;
	}
}
