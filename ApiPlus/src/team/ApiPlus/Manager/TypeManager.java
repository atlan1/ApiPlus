package team.ApiPlus.Manager;

import java.util.HashMap;
import java.util.Map;

import org.getspout.spoutapi.material.block.GenericCustomBlock;
import org.getspout.spoutapi.material.item.GenericCustomItem;

import team.ApiPlus.Util.Utils;

public class TypeManager {
	private static TypeManager instance;
	private Map<String, Class<? extends GenericCustomItem>> itemtypes = new HashMap<String, Class<? extends GenericCustomItem>>();
	private Map<String, Class<? extends GenericCustomBlock>> blocktypes = new HashMap<String, Class<? extends GenericCustomBlock>>();
	
	private TypeManager() {
		if(instance != null) Utils.info("Cannot have multiple Instances of the Type Manager.");
	}
	
	/**
	 * Method used to register Item Type to API+.
	 * @param name String name of Type.
	 * @param c GenericCustomItem of Type.
	 * @return boolean True if action completed successfully, false if not.
	 */
	public boolean registerItemType(String name, Class<? extends GenericCustomItem> c) {
		if(checkItemType(name)) return false;
		else {
			itemtypes.put(name, c);
			return true;
		}
	}
	
	/**
	 * Method used to unregister Item Type from API+.
	 * @param name String name of Type.
	 * @return
	 */
	public boolean unregisterItemType(String name) {
		if(checkItemType(name)) {
			itemtypes.remove(name);
			return true;
		} else return false;
	}
	
	/**
	 * Method used to check if Type name is already registered.
	 * @param name String name of Type to check.
	 * @return boolean True if already taken, false it not.
	 */
	public boolean checkItemType(String name) {
		if(itemtypes.containsKey(name)) return true;
		else return false;
	}
	
	/**
	 * Method used to get Item Type by name.
	 * @param name String name of Type to find.
	 * @return CustomItem Returns found CustomItem, null if none found.
	 */
	public GenericCustomItem getItemType(String name) {
		if(checkItemType(name)) return itemtypes.get(name).cast(GenericCustomItem.class);
		else return null;
	}
	
	/**
	 * Method used to register Block Type to API+.
	 * @param name String name of Type.
	 * @param c GenericCustomBlock of Type.
	 * @return boolean True if action completed successfully, false if not.
	 */
	public boolean registerBlockType(String name, Class<? extends GenericCustomBlock> c) {
		if(checkBlockType(name)) return false;
		else {
			blocktypes.put(name, c);
			return true;
		}
	}
	
	/**
	 * Method used to unregister Block Type from API+.
	 * @param name String name of Type.
	 * @return
	 */
	public boolean unregisterBlockType(String name) {
		if(checkBlockType(name)) {
			blocktypes.remove(name);
			return true;
		} else return false;
	}
	
	/**
	 * Method used to check if Type name is already registered.
	 * @param name String name of Type to check.
	 * @return boolean True if already taken, false it not.
	 */
	public boolean checkBlockType(String name) {
		if(blocktypes.containsKey(name)) return true;
		else return false;
	}
	
	/**
	 * Method used to get Block Type by name.
	 * @param name String name of Type to find.
	 * @return CustomBlock Returns found Custom Block, null if none found.
	 */
	public GenericCustomBlock getBlockType(String name) {
		if(checkBlockType(name)) return blocktypes.get(name).cast(GenericCustomBlock.class);
		else return null;
	}
	
	/**
	 * Method used for getting an Instance of the API's TypeManager, Only one instance is allowed.
	 * @return TypeManager Instance of the API's TypeManager.
	 */
	public static TypeManager getInstance() {
		if(instance == null) instance = new TypeManager();
		return instance;
	}
}
