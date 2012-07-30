package team.ApiPlus.API.Mob;

import java.util.List;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class APIMob {
	private EntityType base;
	private int maxHealth = 20;
	private int armor = 2;
	private ItemStack[] drops;
	private int light;
	private double range;
	private int damage;
	private int xp;
	private List<Goal> goal;
	private List<Goal> targets;

	public APIMob(EntityType base, int light, int health, int armor, int damage, 
			ItemStack[] drops, int xp, double range, List<Goal> goal, List<Goal> targets) {
		this.base = base;
		this.light = light;
		this.maxHealth = health;
		this.armor = armor;
		this.damage = damage;
		this.drops = drops;
		this.xp = xp;
		this.range = range;
		this.goal = goal;
		this.targets = targets;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	
	public EntityType getBase() {
		return base;
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

	public double getRange() {
		return range;
	}

	public List<Goal> getTargets() {
		return targets;
	}

	public List<Goal> getGoal() {
		return goal;
	}

	public int getExp() {
		return xp;
	}

	public int getDamage() {
		return damage;
	}
	
	@Override
	public String toString() {
		return String.format("{APIMob:%s}[HP:%s, Armr:%s, Lght:%s, Rnge:%s, Dmg:%s, Exp:%s]", 
				this.base.toString(), this.maxHealth, this.armor, this.light, this.range, this.damage, this.xp);
	}
}