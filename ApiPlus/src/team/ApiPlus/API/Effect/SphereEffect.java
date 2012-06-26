package team.ApiPlus.API.Effect;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import team.ApiPlus.ApiPlus;
import team.ApiPlus.API.Effect.Effect;
import team.ApiPlus.API.Effect.LocationEffect;
import team.ApiPlus.Util.Utils;

/** SphereEffect - Used to perform an Effect on every Location within a Sphere of a given Radius
 * @author Atlan1
 *
 */
public class SphereEffect implements Runnable {

	private Effect effect;
	private Location l;
	private int radius;
	
	/** SphereEffect Constructor
	 * @param l The center of the Sphere as Location
	 * @param r The Radius to calculate
	 * @param e The LocationEffect to perform
	 */
	public SphereEffect(final Location l, int r, LocationEffect e) {
		this.effect = e;
		this.l = l;
		this.radius = r;
	}
	
	@Override
	public void run() {
		if(radius<=0) return;
		if(radius==1){
			effect.performEffect(l);
			return;
		}
		List<Block> list = Utils.getSphere(l, radius);
		for(Block b:list){
			effect.performEffect(b.getLocation());
		}
	}
	
	/**
	 * Starts the SphereEffect
	 */
	public void start(){
		Bukkit.getScheduler().scheduleSyncDelayedTask(ApiPlus.getInstance(), this);
	}
	
	public void setEffect(Effect e){
		effect = e;
	}
	
	public void setRadius(int e){
		radius = e;
	}
	
	public void setCenter(final Location e){
		l = e.clone();
	}

}
