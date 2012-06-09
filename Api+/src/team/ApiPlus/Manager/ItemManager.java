package team.ApiPlus.Manager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.material.item.GenericCustomItem;

import team.ApiPlus.Item;
import team.ApiPlus.API.PluginPlus;
import team.ApiPlus.Util.Utils;

/**
 * API+ Custom Item Manager.
 * @author SirTyler (Tyler Martin)
 * @version 1.0
 */
public class ItemManager {
	
	private static ItemManager instance;
	private List<Item> list = new ArrayList<Item>();
	
	private ItemManager() {
		if(instance != null) Utils.info("Cannot have multiple Instances of the Item Manager.");
	}
	
	/**
	 * Method used for Adding Items to Manager.
	 * @param i Item to be added.
	 * @return boolean True if action was completed, False if not.
	 */
	public boolean addItem(Item i) {
		if(list.contains(i)) return false;
		else {
			list.add(i);
			return true;
		}
	}
	
	/**
	 * Method used for Removing Item from Manager.
	 * @param i Item to be removed.
	 * @return boolean True if action was completed, False if not.
	 */
	public boolean removeItem(Item i) {
		if(list.contains(i)) {
			list.remove(i);
			return true;
		} else return false;
	}
	
	/**
	 * Method used for Removing Item from Manager based on Name.
	 * @param name Name of Item to be removed.
	 * @return boolean True if action was completed, False if not.
	 */
	public boolean removeItem(String name) {
		Item i = getItem(name);
		if(i == null) return false;
		return removeItem(i);
	}
	
	/**
	 * Method used for getting Item from Manager based on Name.
	 * @param name Name of Item to look for.
	 * @return Item Item found, returns null if nothing.
	 */
	public Item getItem(String name) {
		for(Item i : list) {
			if(i.getName().equalsIgnoreCase(name)) return i;
		}
		return null;
	}
	
	/**
	 * Method used for building an Item based on supplied data.
	 * @param p Plugin+ to be used.
	 * @param name String name of new Item.
	 * @param texture String URL for Texture.
	 * @param baseType  String name of Type to Base off.
	 * @return Item Item created.
	 * @throws InvocationTargetException InvocationTargetException is a checked exception that wraps an exception thrown by an invoked method or constructor.
	 * @throws IllegalAccessException An IllegalAccessException is thrown when an application tries to reflectively create an instance (other than an array), set or get a field, or invoke a method, but the currently executing method does not have access to the definition of the specified class, field, method or constructor.
	 * @throws InstantiationException Thrown when an application tries to create an instance of a class using the newInstance method in class Class, but the specified class object cannot be instantiated.
	 * @throws IllegalArgumentException Thrown to indicate that a method has been passed an illegal or inappropriate argument.
	 * @throws NoSuchMethodException Thrown when a particular method cannot be found.
	 * @throws SecurityException Thrown by the security manager to indicate a security violation.
	 */
	public Item buildItem(PluginPlus p, String name, String texture, String baseType) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException {
		Item type = new Item(TypeManager.getInstance().getType(baseType));
		Item item;
		Constructor<? extends GenericCustomItem> con = type.getClass().getConstructor(Plugin.class, String.class, String.class);
		item = new Item(con.newInstance(p,name,texture));
		return item;
	}
	
	/**
	 * Method used for getting an Instance of the API's ItemManager, Only one instance is allowed.
	 * @return ItemManager Instance of the API's ItemManager.
	 */
	public static ItemManager getInstance() {
		if(instance == null) instance = new ItemManager();
		return instance;
	}
}
