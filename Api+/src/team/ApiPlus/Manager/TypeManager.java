package team.ApiPlus.Manager;

import java.util.HashMap;
import java.util.Map;

import org.getspout.spoutapi.material.item.GenericCustomItem;

import team.ApiPlus.Util.Utils;

public class TypeManager {
	private static TypeManager instance;
	private Map<String, Class<? extends GenericCustomItem>> types = new HashMap<String, Class<? extends GenericCustomItem>>();
	
	private TypeManager() {
		if(instance != null) Utils.info("Cannot have multiple Instances of the Type Manager.");
	}
	
	/**
	 * Method used to register Item Type to API+.
	 * @param name String name of Type.
	 * @param c GenericCustomItem of Type.
	 * @return boolean True if action completed successfully, false if not.
	 */
	public boolean register(String name, Class<? extends GenericCustomItem> c) {
		if(check(name)) return false;
		else {
			types.put(name, c);
			return true;
		}
	}
	
	/**
	 * Method used to unregister Item Type from API+.
	 * @param name String name of Type.
	 * @return
	 */
	public boolean unregister(String name) {
		if(check(name)) {
			types.remove(name);
			return true;
		} else return false;
	}
	
	/**
	 * Method used to check if Type name is already registered.
	 * @param name String name of Type to check.
	 * @return boolean True if already taken, false it not.
	 */
	public boolean check(String name) {
		if(types.containsKey(name)) return true;
		else return false;
	}
	
	/**
	 * Method used to get Item Type by name.
	 * @param name String name of Type to find.
	 * @return CustomItem Returns found CustomItem, null if none found.
	 */
	public GenericCustomItem getType(String name) {
		if(check(name)) return types.get(name).cast(GenericCustomItem.class);
		else return null;
	}
	
	/**
	 * Method used for getting an Instance of the API's ItemManager, Only one instance is allowed.
	 * @return ItemManager Instance of the API's ItemManager.
	 */
	public static TypeManager getInstance() {
		if(instance == null) instance = new TypeManager();
		return instance;
	}
}
