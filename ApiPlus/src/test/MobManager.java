package team.ApiPlus.Manager;
//
//import java.lang.reflect.Method;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
//import net.minecraft.server.v1_4_R1.EntityTypes;
//
//import org.bukkit.entity.EntityType;
//
//import team.ApiPlus.API.Mob.APIEntitySkeleton;
//import team.ApiPlus.API.Mob.APIEntitySpider;
//import team.ApiPlus.API.Mob.APIEntityZombie;
//import team.ApiPlus.API.Mob.APIMob;
//import team.ApiPlus.Util.Utils;
//
///**
// * MobManager class for use with API+.
// * @author SirTyler
// * @version 1.0
// */
//public class MobManager {
//	private static MobManager instance;
//	private Map<EntityType,APIMob> map = new HashMap<EntityType, APIMob>();
//	
//	private MobManager() {
//		instance = this;
//	}
//	
//	private void overwriteNewMob(EntityType type) {
//		try{
//			@SuppressWarnings("rawtypes")
//			Class[] args = new Class[3];
//			args[0] = Class.class;
//			args[1] = String.class;
//			args[2] = int.class;
//
//			Method a = EntityTypes.class.getDeclaredMethod("a", args);
//			a.setAccessible(true);
//			
//			switch(type) {
//			case CREEPER:
//				a.invoke(a, APIEntityCreeper.class, "Creeper", 50);
//				break;
//			case SKELETON:
//				a.invoke(a, APIEntitySkeleton.class, "Skeleton", 51);
//				break;
//			case SPIDER:
//				a.invoke(a, APIEntitySpider.class, "Spider", 52);
//				break;
//			case ZOMBIE:
//				a.invoke(a, APIEntityZombie.class, "Zombie", 54);
//				break;
//			}
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//	}
//
//	private void overwriteDefault(EntityType type) {
//		try{
//			@SuppressWarnings("rawtypes")
//			Class[] args = new Class[3];
//			args[0] = Class.class;
//			args[1] = String.class;
//			args[2] = int.class;
//
//			Method a = EntityTypes.class.getDeclaredMethod("a", args);
//			a.setAccessible(true);
//			
//			a.invoke(a, type.getClass(), type.getName(), type.getTypeId());
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	 * Method used for loading a new APIMob into the server.
//	 * @param mob APIMob to be loaded.
//	 */
//	public void setNewAPIMob(APIMob mob) {
//		map.put(mob.getBase(), mob);
//		overwriteNewMob(mob.getBase());
//		switch(mob.getBase()) {
//		case ZOMBIE:
//			APIEntityZombie.setAPIMob(mob);
//			Utils.debug("APIEntityZombie new Mob set.");
//			break;
//		case SKELETON:
//			APIEntitySkeleton.setAPIMob(mob);
//			Utils.debug("APIEntitySkeleton new Mob set.");
//			break;
//		case CREEPER:
//			APIEntityCreeper.setAPIMob(mob);
//			Utils.debug("APIEntityCreeper new Mob set.");
//			break;
//		case SPIDER:
//			APIEntitySpider.setAPIMob(mob);
//			Utils.debug("APIEntitySpider new Mob set.");
//			break;
//		default:
//			Utils.debug("Not a Mutable Mob");
//		}
//	}
//	
//	/**
//	 * Method used for setting a Mob back to default.
//	 * @param mob APIMob to use as reference.
//	 */
//	public void setDefault(APIMob mob) {
//		map.remove(mob);
//		overwriteDefault(mob.getBase());
//		switch(mob.getBase()) {
//		case ZOMBIE:
//			APIEntityZombie.setAPIMob(null);
//			Utils.debug("APIEntityZombie Disabled.");
//			break;
//		case SKELETON:
//			APIEntitySkeleton.setAPIMob(null);
//			Utils.debug("APIEntitySkeleton Disabled.");
//			break;
//		case CREEPER:
//			APIEntityCreeper.setAPIMob(null);
//			Utils.debug("APIEntityCreeper Disabled.");
//			break;
//		case SPIDER:
//			APIEntitySpider.setAPIMob(null);
//			Utils.debug("APIEntitySpider Disabled.");
//			break;
//		default:
//			Utils.debug("Not a Mutable Mob");
//		}
//	}
//	
//	/**
//	 * Method used for getting the map of loaded APIMobs.
//	 * @return Map<EntityType, APIMob> Map of Loaded Mobs.
//	 */
//	public Map<EntityType, APIMob> getMap() {
//		return map;
//	}
//	
//	/**
//	 * Method used for getting a Collection of the loaded APIMobs.
//	 * @return Collection<APIMob> Collection of Loaded Mobs.
//	 */
//	public Collection<APIMob> loadedMobs() {
//		return map.values();
//	}
//	
//	/**
//	 * Method used for getting an Instance of the API's MobManager, Only one instance is allowed.
//	 * @return MobManager Instance of the API's MobManager.
//	 */
//	public static MobManager getInstance() {
//		if(instance == null) instance = new MobManager();
//		return instance;
//	}
//}
