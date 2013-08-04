package test.team.ApiPlus.API.Mob;

import java.lang.reflect.Field;
import java.util.List;

import test.team.ApiPlus.Util.Utils;

import net.minecraft.server.v1_4_R1.*;

/**
 * APIEntitySpider class that functions as Mutable EntitySpider
 * @author SirTyler
 * @version 1.0
 */

public class APIEntitySpider extends EntitySpider {
	private static APIMob sMob;
	private APIMob mob;
	private static int maxHealth = 10;
	private static int armor = 2;
	
    @SuppressWarnings("unchecked")
	public APIEntitySpider(World world) {
        super(world);
        if(sMob != null) {
        	mob = sMob;
        	this.damage = mob.getDamage();
        	try{
        		//Set Navigation
        		Field navigation = EntityLiving.class.getDeclaredField("navigation");
        		navigation.setAccessible(true);
        		navigation.set(this, new NavigationOverride(this, this.world, (float) mob.getRange()));
        		
        		//Clear Goals
        		Field goals = this.goalSelector.getClass().getDeclaredField("a");
        		goals.setAccessible(true);
        		((List<PathfinderGoal>) goals.get(this.goalSelector)).clear();
        		
        		//Clear Targets
        		Field targets = this.targetSelector.getClass().getDeclaredField("a");
        		targets.setAccessible(true);
        		((List<PathfinderGoal>) targets.get(this.targetSelector)).clear();
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
        	this.goalSelector.a(0, new PathfinderGoalFloat(this));
            this.goalSelector.a(1, new PathfinderGoalBreakDoor(this));
            this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, EntityHuman.class, this.bw, false));
            this.goalSelector.a(3, new PathfinderGoalRandomStroll(this, this.bw));
            this.goalSelector.a(4, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, ((float) mob.getRange()-8)));
            this.goalSelector.a(5, new PathfinderGoalRandomLookaround(this));
            this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false));
            this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, (float) mob.getRange(), 0, true));
        } else {
        	this.getBukkitEntity().remove();
        	Utils.debug("Creation Error: APIMob is Null");
        }
    }

    /**
     * Method used for getting APIMob Object associated with this Entity.
     * @return APIMob Used
     */
	public APIMob getAPIMob() {
		return mob;
	}
	
	/**
	 * Static Method used for setting the APIMob to be used BEFORE creation of Mob.
	 * @param _mob
	 */
	public static void setAPIMob(APIMob _mob) {
		sMob = _mob;
		maxHealth = sMob.getMaxHealth();
		armor = sMob.getArmor();
	}
	
	@Override
	public int getMaxHealth() {
		return maxHealth;
	}
	
	@Override
	public int aO() {
		return armor;
	}
	
	@Override
    protected int getLootId() {
        return mob.getDrops()[0].getTypeId();
    }
	
	@Override
	protected ItemStack l(int i) {
		System.out.println("APIEntitySpider (Method l):" + i);
		if(mob.getDrops().length <= i) i = mob.getRareDrops().length;
		return new ItemStack(mob.getRareDrops()[i].getTypeId(), mob.getRareDrops()[i].getAmount(), 0);
    }
	
	@Override
    public int getExpReward() {
        int exp = mob.getExp();

        if (!this.world.isStatic && (this.lastDamageByPlayerTime > 0 || this.alwaysGivesExp()) && !this.isBaby()) {
            return exp;
        } else {
            return 0;
        }
    }
	
	@Override
    protected Entity findTarget() {
        EntityHuman entityhuman = this.world.findNearbyVulnerablePlayer(this, mob.getRange());

        return entityhuman != null && this.l(entityhuman) ? entityhuman : null;
    }
	
	@Override
    protected void dropDeathLoot(boolean flag, int i) {
        java.util.List<org.bukkit.inventory.ItemStack> loot = new java.util.ArrayList<org.bukkit.inventory.ItemStack>();
        int j = this.getLootId();
        
        if (j > 0) {
        	for(org.bukkit.inventory.ItemStack itm : this.mob.getDrops()) {
        		int count = this.random.nextInt(3);
        		if(i > 0) {
        			count += this.random.nextInt(i + 1);
        		}
        		if(count > 0) {
        			loot.add(itm);
        		}
        	}
        }
        
        if (this.lastDamageByPlayerTime > 0) {
            int k = this.random.nextInt(200) - i;

            if (k < 5) {
                ItemStack itemstack = this.l(k <= 0 ? 1 : 0);
                if (itemstack != null) {
                    loot.add(new org.bukkit.craftbukkit.inventory.CraftItemStack(itemstack));
                }
            }
        }
        
        org.bukkit.craftbukkit.event.CraftEventFactory.callEntityDeathEvent(this, loot);
    }
}
