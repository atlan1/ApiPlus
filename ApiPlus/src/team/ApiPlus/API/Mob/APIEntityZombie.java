package team.ApiPlus.API.Mob;

import org.bukkit.Location;
import team.ApiPlus.API.Event.MobMoveEvent;
import net.minecraft.server.Entity;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityZombie;
import net.minecraft.server.EnumSkyBlock;
import net.minecraft.server.MathHelper;
import net.minecraft.server.World;

public class APIEntityZombie extends EntityZombie {
	private APIMob mob;
	private static int healthS = 10;
	private int maxHealth = healthS;
	private int armor = 2;
	
	public APIEntityZombie(World world, APIMob _mob) {
		super(world);
		setAPIMob(mob);
	}
	
	public APIMob getAPIMob() {
		return mob;
	}

	public void setAPIMob(APIMob _mob) {
//		System.out.println(_mob);
		mob = _mob;
		//health = 2;
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
	protected Entity findTarget() {
        EntityHuman entityhuman = this.world.findNearbyVulnerablePlayer(this, mob.getRange());
        return entityhuman != null && this.h(entityhuman) ? entityhuman : null;
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
