package team.ApiPlus.API.Type;

import java.util.List;

import org.bukkit.plugin.Plugin;

import team.ApiPlus.API.EffectHolder;
import team.ApiPlus.API.Effect.Effect;
import team.ApiPlus.API.Property.CollectionProperty;

/**
 * @author Atlan1
 * @version 1.0
 */
public abstract class ItemTypeEffectPlusProperty extends ItemTypeProperty implements
		EffectHolder {

	public ItemTypeEffectPlusProperty(Plugin plugin, String name, String texture) {
		super(plugin, name, texture);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Effect> getEffects() {
		return (List<Effect>) getProperty("EFFECTS");
	}

	@Override
	public void setEffects(List<Effect> effects) {
		editProperty("EFFECTS", new CollectionProperty<Effect>(effects));
		addProperty("EFFECTS", new CollectionProperty<Effect>(effects));
	}

}
