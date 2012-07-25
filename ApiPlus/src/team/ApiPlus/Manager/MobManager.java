package team.ApiPlus.Manager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import team.ApiPlus.API.Mob.APIEntityZombie;
import net.minecraft.server.EntityLiving;

public class MobManager {
	private static MobManager instance;
	private List<EntityLiving> newMobs = new ArrayList<EntityLiving>();
	
	private MobManager() {
		instance = this;
	}
	
	public static MobManager create() {
		if(instance==null) return new MobManager();
		else return MobManager.getInstance();
	}
	
	public void overwriteNewMob() {
		try{
			@SuppressWarnings("rawtypes")
			Class[] args = new Class[3];
			args[0] = Class.class;
			args[1] = String.class;
			args[2] = int.class;

			Method a = net.minecraft.server.EntityTypes.class.getDeclaredMethod("a", args);
			a.setAccessible(true);
			
			a.invoke(a, APIEntityZombie.class, "Zombie", 54);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public List<EntityLiving> getList() {
		return newMobs;
	}

	public static MobManager getInstance() {
		return instance;
	}
}
