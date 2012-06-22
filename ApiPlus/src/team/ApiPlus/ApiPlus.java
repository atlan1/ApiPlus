package team.ApiPlus;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import team.ApiPlus.API.Effect.Default.BreakEffect;
import team.ApiPlus.API.Effect.Default.BurnEffect;
import team.ApiPlus.API.Effect.Default.LightningEffect;
import team.ApiPlus.API.Effect.Default.MoveEffect;
import team.ApiPlus.API.Effect.Default.ParticleEffect;
import team.ApiPlus.API.Effect.Default.PlaceEffect;
import team.ApiPlus.API.Effect.Default.PotionEffect;
import team.ApiPlus.API.Effect.Default.SpawnEffect;
import team.ApiPlus.API.Type.BlockType;
import team.ApiPlus.API.Type.BlockTypeEffect;
import team.ApiPlus.API.Type.BlockTypeEffectPlusProperty;
import team.ApiPlus.API.Type.BlockTypeProperty;
import team.ApiPlus.API.Type.ItemType;
import team.ApiPlus.API.Type.ItemTypeEffect;
import team.ApiPlus.API.Type.ItemTypeEffectPlusProperty;
import team.ApiPlus.API.Type.ItemTypeProperty;
import team.ApiPlus.Manager.BlockManager;
import team.ApiPlus.Manager.ConfigManager;
import team.ApiPlus.Manager.EffectManager;
import team.ApiPlus.Manager.ItemManager;
import team.ApiPlus.Manager.TypeManager;
import team.ApiPlus.Manager.Loadout.LoadoutManager;
import team.ApiPlus.Util.ConfigUtil;
import team.ApiPlus.Util.FileUtil;
import team.ApiPlus.Util.Utils;

@SuppressWarnings("unused")
public class ApiPlus extends JavaPlugin {
	
	private String version;
	private static ApiPlus instance;
	private ItemManager iManager;
	private BlockManager bManager;
	private LoadoutManager lManager;
	private TypeManager tManager;
	private EffectManager eManager;
	private ConfigManager cManager;
	public static Map<String,Plugin> hooks = new HashMap<String,Plugin>();
	public static List<Material> transparentMaterials = new ArrayList<Material>();
	
	@Override
	public void onEnable() {
		instance = this;
		version = getDescription().getVersion();
		iManager = ItemManager.getInstance();
		bManager = BlockManager.getInstance();
		lManager = LoadoutManager.getInstance();
		tManager = TypeManager.getInstance();
		eManager = EffectManager.getInstance();
		cManager = ConfigManager.getInstance();
		lManager.loadAll();
		Utils.debug(lManager.read());
		hook();
		loadGeneral();
		registerDefaultMaterialTypes();
		registerDefaultEffectTypes();
		Utils.info(String.format("API+ Version:%s Enabled.", version));
	}
	
	private void hook() {
		Plugin _wg = getServer().getPluginManager().getPlugin("WorldGuard");
		Plugin _fur = getServer().getPluginManager().getPlugin("FurnaceAPI");
		Plugin _lwc = getServer().getPluginManager().getPlugin("LWC");
		if(_wg != null) {
			hooks.put("WorldGuard", _wg);
			Utils.info("Hooked into WorldGuard");
		}
		if(_fur != null) {
			hooks.put("FurncaeAPI", _fur);
			Utils.info("Hooked into FurnaceAPI");
		}
		if(_lwc != null) {
			hooks.put("LWC", _lwc);
			Utils.info("Hooked into LWC");
		}
	}
	
	private void loadGeneral() {
		File general = new File(this.getDataFolder(), "general.yml");
		if(FileUtil.create(general))
			FileUtil.copy(this.getResource("general.yml"), general);
		cManager.add(general);
		FileConfiguration con = cManager.get(general);
		if(con != null) {
			Utils.setDebug(Boolean.valueOf(con.getString("debug","false")));
			for(ItemStack i:ConfigUtil.parseItems(con.getString("transparent-materials")))
				if(i.getType().isBlock())
					transparentMaterials.add(i.getType());
		} else return;
	}
	
	private void registerDefaultMaterialTypes(){
		TypeManager tm = TypeManager.getInstance();
		tm.registerBlockType("Block", BlockType.class);
		tm.registerBlockType("BlockProperty", BlockTypeProperty.class);
		tm.registerBlockType("BlockEffect", BlockTypeEffect.class);
		tm.registerBlockType("BlockEffectPlusProperty", BlockTypeEffectPlusProperty.class);
		tm.registerItemType("Item", ItemType.class);
		tm.registerItemType("ItemProperty", ItemTypeProperty.class);
		tm.registerItemType("ItemEffect", ItemTypeEffect.class);
		tm.registerItemType("ItemEffectPlusProperty", ItemTypeEffectPlusProperty.class);
	}
	
	private void registerDefaultEffectTypes(){
		EffectManager em = EffectManager.getInstance();
		em.registerEffectType("BREAK", BreakEffect.class);
		em.registerEffectType("PLACE", PlaceEffect.class);
		em.registerEffectType("EXPLOSION", BreakEffect.class);
		em.registerEffectType("LIGHTNING", LightningEffect.class);
		em.registerEffectType("SPAWN", SpawnEffect.class);
		em.registerEffectType("MOVE", MoveEffect.class);
		em.registerEffectType("PARTICLE", ParticleEffect.class);
		em.registerEffectType("BURN", BurnEffect.class);
		em.registerEffectType("POTION", PotionEffect.class);
	}
	
	/**
	 * Method used for adding a hook into API+.
	 * @param p Plugin to be added.
	 * @return boolean True if action completed successfully, False if not.
	 */
	public static boolean addHook(Plugin p) {
		if(hooks.containsValue(p)) return false;
		else {
			hooks.put(p.getName(), p);
			return true;
		}
	}
	
	/**
	 * Method used for getting Server's Instance of Api+.
	 * @return ApiPlus Instance of Api+.
	 */
	public static ApiPlus getInstance() {
		return instance;
	}
}
