package team.ApiPlus.API.Mob;
//
//import net.minecraft.server.v1_4_R1.*;
//
//public class NavigationOverride extends Navigation {
//
//	private EntityLiving entity;
//
//	public NavigationOverride(EntityLiving entity, World world, float f) {
//		super(entity, world, f);
//
//		this.entity = entity;
//	}
//
//	@Override
//	public boolean a(double d0, double d1, double d2, float f) {
//		PathEntity pathentity = this.a((double) MathHelper.floor(d0),
//				(double) ((int) d1), (double) MathHelper.floor(d2));
//
//		return this.a(pathentity, f);
//	}
//
//	@Override
//	public boolean a(EntityLiving entityliving, float f) {
//		PathEntity pathentity = this.a(entityliving);
//
//		return pathentity != null ? this.a(pathentity, f) : false;
//	}
//
//	@Override
//	public boolean a(PathEntity path, float speed) {
//		switch(entity.getBukkitEntity().getType()) {
//		case ZOMBIE:
//			speed *= ((APIEntityZombie) entity).getAPIMob().getBoost();
//			break;
//		case SKELETON:
//			speed *= ((APIEntitySkeleton) entity).getAPIMob().getBoost();
//			break;
//		case CREEPER:
//			speed *= ((APIEntityCreeper) entity).getAPIMob().getBoost();
//			break;
//		case SPIDER:
//			speed *= ((APIEntitySpider) entity).getAPIMob().getBoost();
//			break;
//		}
//
//		return super.a(path, speed);
//	}
//
//}