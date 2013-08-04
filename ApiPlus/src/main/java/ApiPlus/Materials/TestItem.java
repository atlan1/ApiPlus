package ApiPlus.Materials;

import org.bukkit.plugin.Plugin;
import team.ApiPlus.API.Type.ItemType;

public abstract class TestItem extends ItemType {

	public TestItem(Plugin plugin, String name, String texture) {
		super(plugin, name, texture);
	}

	public void printShit(){System.out.print("SHIT!");}
	
}
