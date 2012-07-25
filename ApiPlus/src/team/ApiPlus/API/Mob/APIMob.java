package team.ApiPlus.API.Mob;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import team.ApiPlus.Enums.MobType;

public class APIMob {
	private MobType type;
	private EntityType base;
	private int maxHealth = 20;
	private int armor = 2;
	private ItemStack[] drops;
	private int light;
	private int chance;
	private double range;
	private int damage;
	private int xp;
	//private List<Goal> goal;
	//private List<Goal> targets;

	public APIMob(MobType type, int light, int health, int armor, int damage, 
			/*ItemStack[] drops,*/ int xp, double range, int chance /*List<Goal> goal, List<Goal> targets,*/) {
		this.type = type;
		this.base = EntityType.fromName(type.getName().toUpperCase());
		this.light = light;
		this.maxHealth = health;
		this.armor = armor;
		this.damage = damage;
		//this.drops = drops;
		this.xp = xp;
		this.range = range;
		this.chance = chance;
		//this.goal = goal;
		//this.targets = targets;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	
	public EntityType getBase() {
		return base;
	}
	
	public MobType getType() {
		return type;
	}

	public int getArmor() {
		return armor;
	}
	
	public ItemStack[] getDrops() {
		return drops;
	}
	
	public int getLight() {
		return light;
	}
	
	public int getChance() {
		return chance;
	}

	public double getRange() {
		return range;
	}

	/*public List<Goal> getTargets() {
		return targets;
	}

	public List<Goal> getGoal() {
		return goal;
	}*/

	public int getXp() {
		return xp;
	}

	public int getDamage() {
		return damage;
	}
}