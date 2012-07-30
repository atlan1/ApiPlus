package team.ApiPlus.Manager;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.EntityType;

import team.ApiPlus.API.Mob.APIEntityZombie;
import team.ApiPlus.API.Mob.APIMob;
import team.ApiPlus.Util.Utils;

/**
 * MobManager class for use with API+.
 * @author SirTyler, Atlan1
 * @version 1.0
 */
public class MobManager {
	private static MobManager instance;
	private Map<EntityType,APIMob> map = new HashMap<EntityType, APIMob>();
	
	private MobManager() {
		instance = this;
	}
	
	public static MobManager create() {
		if(instance==null) return new MobManager();
		else return MobManager.getInstance();
	}
	
	private void overwriteNewMob(EntityType type) {
		try{
			@SuppressWarnings("rawtypes")
			Class[] args = new Class[3];
			args[0] = Class.class;
			args[1] = String.class;
			args[2] = int.class;

			Method a = net.minecraft.server.EntityTypes.class.getDeclaredMethod("a", args);
			a.setAccessible(true);
			
			switch(type) {
			case ZOMBIE:
				a.invoke(a, APIEntityZombie.class, "Zombie", 54);
				break;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public Map<EntityType,APIMob> getMap() {
		return map;
	}
	
	public void setNewAPIMob(APIMob mob) {
		switch(mob.getBase()) {
		case ZOMBIE:
			map.put(mob.getBase(), mob);
			overwriteNewMob(mob.getBase());
			APIEntityZombie.setAPIMob(mob);
			Utils.debug("APIEntityZombie new Mob set.");
			break;
		default:
			Utils.debug("Not a changeable Mob");
		}
	}

	public static MobManager getInstance() {
		return instance;
	}
}
