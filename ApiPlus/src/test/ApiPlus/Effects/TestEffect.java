package test.ApiPlus.Effects;

import test.team.ApiPlus.API.Effect.Effect;
import test.team.ApiPlus.API.Effect.EffectTarget;

public class TestEffect implements Effect{

	public String getName() {
		return "TEST";
	}

	@Override
	public EffectTarget getEffectTarget() {
		return null;
	}

	@Override
	public void setEffectTarget(EffectTarget et) {
		
	}

	@Override
	public void performEffect(Object... arguments) {
		System.out.print("I am so cool!");
	}

}
