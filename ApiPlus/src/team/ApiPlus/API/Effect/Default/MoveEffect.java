package team.ApiPlus.API.Effect.Default;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import team.ApiPlus.API.Effect.EffectTarget;
import team.ApiPlus.API.Effect.EntityEffect;
import team.ApiPlus.Util.Utils;

public class MoveEffect implements EntityEffect{

	private EffectTarget et;
	
	private float speed = 0f;
	private String direction;
	
	public MoveEffect(Object...args) {
		this.speed = (Float) args[0];
		this.direction = (String) args[1];
	}
	
	@Override
	public void performEffect(Object... arguments) {
		Entity e = (Entity) arguments[0];
		Location l = (Location) arguments[1];
		if(direction.equalsIgnoreCase("push")){
			move(l.getDirection(), e, speed);
		}else if(direction.equalsIgnoreCase("draw")){
			move(l.getDirection(), e, speed*-1);
		}else if(direction.equalsIgnoreCase("launch")){
			move(e.getLocation().toVector().add(new Vector(0, 5, 0)), e, speed);
		}else if(direction.equalsIgnoreCase("random")){
			int x = Utils.getRandomInteger(-5, 5);
			int y = Utils.getRandomInteger(0, 5);
			int z = Utils.getRandomInteger(-5, 5);
			move(e.getLocation().toVector().add(new Vector(x, y, z)), e, speed);
		}
	}
	
	private static void move(Vector dir, Entity e, float speed){
		dir.multiply(speed);
		e.setVelocity(dir);
	}


	@Override
	public EffectTarget getEffectTarget() {
		return et;
	}

	@Override
	public void setEffectTarget(EffectTarget et) {
		this.et = et;
	}
}
