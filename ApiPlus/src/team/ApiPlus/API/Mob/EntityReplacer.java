package team.ApiPlus.API.Mob;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import team.ApiPlus.Enums.MobType;

public class EntityReplacer implements Listener {
	
	private APIMob test = new APIMob(MobType.Zombie, 10, 2, 0, 5, 100, 5.0, 50);
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		Location location = event.getLocation();
		Entity entity = event.getEntity();
		EntityType creatureType = event.getEntityType();
		World world = location.getWorld();
		
		net.minecraft.server.World mcWorld = ((CraftWorld) world).getHandle();
		net.minecraft.server.Entity mcEntity = (((CraftEntity) entity).getHandle());
		if (creatureType == EntityType.ZOMBIE && mcEntity instanceof APIEntityZombie == false){
			APIEntityZombie zed = new APIEntityZombie(mcWorld,test);
			System.out.println(test);
			if(zed != null && mcWorld != null) { 
				zed.setPosition(location.getX(), location.getY(), location.getZ());
				mcWorld.removeEntity(mcEntity);
				mcWorld.addEntity(zed, SpawnReason.CUSTOM);
			}
			return;
		}
	}
}
