package team.ApiPlus.API.Mob;

import org.bukkit.Location;
import team.ApiPlus.API.Event.MobMoveEvent;
import net.minecraft.server.EntityZombie;
import net.minecraft.server.EnumSkyBlock;
import net.minecraft.server.ItemStack;
import net.minecraft.server.MathHelper;
import net.minecraft.server.PathfinderGoalSelector;
import net.minecraft.server.World;

public class APIEntityZombie extends EntityZombie {
	private static APIMob sMob;
	private APIMob mob;
	private static int maxHealth = 10;
	private static int armor = 2;
	
	public APIEntityZombie(World world) {
		super(world);
		mob = sMob;
		this.al().b(true);
		this.damage = mob.getDamage();
		this.goalSelector = new PathfinderGoalSelector();
		for(Goal g : mob.getGoal()) {
			g.setEntity(this);
			g.build(g.goalID);
			this.goalSelector.a(g.getIndex(), g.getUse());
		}
		this.targetSelector = new PathfinderGoalSelector();
		for(Goal g : mob.getTargets()) {
			g.setEntity(this);
			g.build(g.goalID);
			this.targetSelector.a(g.getIndex(), g.getUse());
		}
	}
	
	public APIMob getAPIMob() {
		return mob;
	}

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
	public int T() {
		return armor;
	}
	
	@Override
	protected boolean C() {
        int i = MathHelper.floor(this.locX);
        int j = MathHelper.floor(this.boundingBox.b);
        int k = MathHelper.floor(this.locZ);

        if (this.world.a(EnumSkyBlock.SKY, i, j, k) > this.random.nextInt(32)) {
            return false;
        } else {
            int l = this.world.getLightLevel(i, j, k);

            if (this.world.w()) {
                int i1 = this.world.f;

                this.world.f = 10;
                l = this.world.getLightLevel(i, j, k);
                this.world.f = i1;
            }

            return l <= this.random.nextInt(mob.getLight());
        }
    }
	
	@Override
    protected int getLootId() {
        return mob.getDrops()[0].getTypeId();
    }
	
	@Override
	protected ItemStack b(int i) {
		System.out.println("APIEntityZombie (Method b):" + i);
		if(mob.getDrops().length <= i) i = mob.getDrops().length - 1;
		return new ItemStack(mob.getDrops()[i+1].getTypeId(), mob.getDrops()[i+1].getAmount(), 0);
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
	public void F_(){
		Location from = new Location(this.getBukkitEntity().getWorld(), this.lastX, this.lastY, this.lastZ, this.lastYaw, this.lastPitch);
		Location to = new Location(this.getBukkitEntity().getWorld(), this.locX, this.locY, this.locZ, this.yaw, this.pitch);
		MobMoveEvent event = new MobMoveEvent(this.getBukkitEntity(), from, to);
		this.world.getServer().getPluginManager().callEvent(event);
		if (event.isCancelled() && this.getBukkitEntity().isDead() == false){
			return;
		}
		super.F_();
	}
}
