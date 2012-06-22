package team.ApiPlus.API.Effect.Default;

import org.bukkit.Location;
import org.bukkit.Material;

import team.ApiPlus.API.Effect.Effect;
import team.ApiPlus.API.Effect.EffectTarget;
import team.ApiPlus.Util.Utils;

public class PlaceEffect implements Effect{

	private EffectTarget et;
	
	private Material m;

	public PlaceEffect(Object...args) {
		this.m =  (Material)args[0];
	}
	
	@Override
	public void performEffect(Object... arguments) {
		Location l = (Location) arguments[0];
		 if(Utils.isTransparent(l.getBlock())){
			 l.getBlock().setType(m);
		 }
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
