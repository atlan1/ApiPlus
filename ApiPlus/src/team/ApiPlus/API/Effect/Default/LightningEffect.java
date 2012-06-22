package team.ApiPlus.API.Effect.Default;

import org.bukkit.Location;

import team.ApiPlus.API.Effect.EffectTarget;
import team.ApiPlus.API.Effect.LocationEffect;


public class LightningEffect implements LocationEffect{

	public LightningEffect(Object...args) {}

	private EffectTarget et;
	
	
	
	@Override
	public void performEffect(Object... arguments) {
		Location l = (Location) arguments[0];
		l.getWorld().strikeLightningEffect(l);
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
