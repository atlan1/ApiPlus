package team.ApiPlus.API.Mob;
//
//import org.bukkit.Location;
//import org.bukkit.World;
//import org.bukkit.craftbukkit.v1_4_R1.*;
//import org.bukkit.entity.Entity;
//import org.bukkit.entity.EntityType;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.EventPriority;
//import org.bukkit.event.Listener;
//import org.bukkit.event.entity.CreatureSpawnEvent;
//import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
//
//import team.ApiPlus.Manager.MobManager;
//
///**
// * EntityReplacer Listener to replace Entities.
// * @author SirTyler
// * @version 1.0
// */
//public class EntityReplacer implements Listener {
//
//	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
//	public void onCreatureSpawn(CreatureSpawnEvent event) {
//		if (!MobManager.getInstance().getMap().isEmpty()) {
//			Location location = event.getLocation();
//			Entity entity = event.getEntity();
//			EntityType creatureType = event.getEntityType();
//			World world = location.getWorld();
//
////			World mcWorld = ((CraftWorld) world).getHandle();
////		Entity mcEntity = (((CraftEntity) entity).getHandle());
////			if (MobManager.getInstance().getMap().containsKey(EntityType.ZOMBIE) && creatureType == EntityType.ZOMBIE && mcEntity instanceof APIEntityZombie == false) {
////				APIEntityZombie zed = new APIEntityZombie(mcWorld);
////				if (zed != null && mcWorld != null) {
////					zed.setPosition(location.getX(), location.getY(), location.getZ());
////					mcWorld.removeEntity(mcEntity);
////					mcWorld.addEntity(zed, SpawnReason.CUSTOM);
////				}
////			}
////			if (MobManager.getInstance().getMap().containsKey(EntityType.SKELETON) && creatureType == EntityType.SKELETON && mcEntity instanceof APIEntitySkeleton == false) {
////				APIEntitySkeleton zed = new APIEntitySkeleton(mcWorld);
////				if (zed != null && mcWorld != null) {
////					zed.setPosition(location.getX(), location.getY(), location.getZ());
////					mcWorld.removeEntity(mcEntity);
////					mcWorld.addEntity(zed, SpawnReason.CUSTOM);
////				}
////			}
////			if (MobManager.getInstance().getMap().containsKey(EntityType.CREEPER) && creatureType == EntityType.CREEPER && mcEntity instanceof APIEntityCreeper == false) {
////				APIEntityCreeper zed = new APIEntityCreeper(mcWorld);
////				if (zed != null && mcWorld != null) {
////					zed.setPosition(location.getX(), location.getY(), location.getZ());
////					mcWorld.removeEntity(mcEntity);
////					mcWorld.addEntity(zed, SpawnReason.CUSTOM);
////				}
////			}
////			if (MobManager.getInstance().getMap().containsKey(EntityType.SPIDER) && creatureType == EntityType.SPIDER && mcEntity instanceof APIEntitySpider == false) {
////				APIEntitySpider zed = new APIEntitySpider(mcWorld);
////				if (zed != null && mcWorld != null) {
////					zed.setPosition(location.getX(), location.getY(), location.getZ());
////					mcWorld.removeEntity(mcEntity);
////					mcWorld.addEntity(zed, SpawnReason.CUSTOM);
////				}
////			}
//		}
//		return;
//	}
//}
