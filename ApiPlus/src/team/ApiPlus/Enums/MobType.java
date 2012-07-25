package team.ApiPlus.Enums;

import net.minecraft.server.EntityZombie;

public enum MobType {
	Zombie(EntityZombie.class,"Zombie",54);
	
	private int i;
	private String name;
	private Class<?> c;
	
	private MobType(Class<?> c, String name,int i) {
		setName(name);
		setData(i);
		setMobClass(c);
	}
	
	private void setData(int i) {
		this.i = i;
	}
	
	public int getData() {
		return i;
	}
	
	private void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public Class<?> getMobClass() {
		return c;
	}

	public void setMobClass(Class<?> c) {
		this.c = c;
	}
	
	public static MobType getByName(String name) {
		for(MobType type : MobType.values()) {
			if(type.getName().equalsIgnoreCase(name)) return type;
		}
		return MobType.Zombie;
	}
}
