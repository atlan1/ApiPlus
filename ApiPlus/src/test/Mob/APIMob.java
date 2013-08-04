package team.ApiPlus.API.Mob;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

/**
 * APIMob class that stores the data to be used by APIEntities.
 * @author SirTyler
 * @version 1.0
 */
public class APIMob {
	private EntityType base;
	private int maxHealth = 20;
	private int armor = 2;
	private ItemStack[] drops;
	private ItemStack[] raredrops;
	private int light;
	private double range;
	private int damage;
	private int xp;
	private int boost;

	public APIMob(EntityType base, int light, int health, int armor, int damage, 
			ItemStack[] drops, ItemStack[] raredrops, int xp, double range, int boost) {
		this.base = base;
		this.light = light;
		this.maxHealth = health;
		this.armor = armor;
		this.damage = damage;
		this.drops = drops;
		this.raredrops = raredrops;
		this.xp = xp;
		this.range = range;
		this.boost = boost;
	}
	/**
	 * Method used for getting the Max Health.
	 * @return int Max Health
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	
	/**
	 * Method used for getting the Base EntityType to use.
	 * @return EntityType Base Type
	 */
	public EntityType getBase() {
		return base;
	}
	
	/**
	 * Method used for getting the Armor.
	 * @return int Armor
	 */
	public int getArmor() {
		return armor;
	}
	
	/**
	 * Method used for getting the Item Drops.
	 * @return ItemStack[] ItemStack Array of Drops.
	 */
	public ItemStack[] getDrops() {
		return drops;
	}
	
	/**
	 * Method used for getting the Rare Item Drops.
	 * @return ItemStack[] ItemStack Array of Drops.
	 */
	public ItemStack[] getRareDrops() {
		return raredrops;
	}
	
	/**
	 * Method used for getting minimum light spawning condition.
	 * @return int Light Spawn Level
	 */
	public int getLight() {
		return light;
	}

	/**
	 * Method used for getting the Visual Range.
	 * @return double Range
	 */
	public double getRange() {
		return range;
	}
	
	/**
	 * Method used for getting Speed Boost.
	 * @return int Boost
	 */
	public int getBoost() {
		return boost;
	}


	/**
	 * Method used for getting Exp to drop.
	 * @return int Exp
	 */
	public int getExp() {
		return xp;
	}

	/**
	 * Method used for getting Damage.
	 * @return int Damage
	 */
	public int getDamage() {
		return damage;
	}
	
	@Override
	public String toString() {
		return String.format("{APIMob:%s}[HP:%s, Armr:%s, Lght:%s, Rnge:%s, Dmg:%s, Exp:%s]", 
				this.base.toString(), this.maxHealth, this.armor, this.light, this.range, this.damage, this.xp);
	}
}