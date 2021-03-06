package test.team.ApiPlus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import test.team.ApiPlus.API.PluginPlus;
import test.team.ApiPlus.API.Effect.Effect;
import test.team.ApiPlus.API.Effect.Default.BreakEffect;
import test.team.ApiPlus.API.Effect.Default.BurnEffect;
import test.team.ApiPlus.API.Effect.Default.ExplosionEffect;
import test.team.ApiPlus.API.Effect.Default.LightningEffect;
import test.team.ApiPlus.API.Effect.Default.MoveEffect;
import test.team.ApiPlus.API.Effect.Default.ParticleEffect;
import test.team.ApiPlus.API.Effect.Default.PlaceEffect;
import test.team.ApiPlus.API.Effect.Default.PotionEffect;
import test.team.ApiPlus.API.Effect.Default.SpawnEffect;
import test.team.ApiPlus.API.Mob.EntityReplacer;
import test.team.ApiPlus.API.Type.BlockType;
import test.team.ApiPlus.API.Type.BlockTypeEffect;
import test.team.ApiPlus.API.Type.BlockTypeEffectPlusProperty;
import test.team.ApiPlus.API.Type.BlockTypeProperty;
import test.team.ApiPlus.API.Type.ItemType;
import test.team.ApiPlus.API.Type.ItemTypeEffect;
import test.team.ApiPlus.API.Type.ItemTypeEffectPlusProperty;
import test.team.ApiPlus.API.Type.ItemTypeProperty;
import test.team.ApiPlus.API.Type.MaterialType;
import test.team.ApiPlus.Manager.BlockManager;
import test.team.ApiPlus.Manager.ConfigManager;
import test.team.ApiPlus.Manager.EffectManager;
import test.team.ApiPlus.Manager.ItemManager;
import test.team.ApiPlus.Manager.MobManager;
import test.team.ApiPlus.Manager.TypeManager;
import test.team.ApiPlus.Manager.Loadout.Loadout;
import test.team.ApiPlus.Manager.Loadout.LoadoutManager;
import test.team.ApiPlus.Util.ConfigUtil;
import test.team.ApiPlus.Util.FileUtil;
import test.team.ApiPlus.Util.Utils;
import test.team.ApiPlus.Util.VersionChecker;

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
	private MobManager mManager;
	public static Map<String,Plugin> hooks = new HashMap<String,Plugin>();
	public static List<Material> transparentMaterials = new ArrayList<Material>();
	
	
	
	private boolean customMobs;
	
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
		hook();
		loadGeneral();
		beginMobAPI();
		registerDefaultMaterialTypes();
		registerDefaultEffectTypes();
		loadMaterialTypeClasses();
		loadEffectClasses();
		new ApiPlusListener(this);
		new VersionChecker(this, "http://dev.bukkit.org/server-mods/apiplus/files.rss");
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
		FileConfiguration con = new YamlConfiguration();
		try {
			con.load(general);
		} catch (FileNotFoundException e) {
			Utils.debug(e);
		} catch (IOException e) {
			Utils.debug(e);
		} catch (InvalidConfigurationException e) {
			Utils.debug(e);
		}
		if(con != null) {
			Utils.setDebug(Boolean.valueOf(con.getString("debug","false")));
			for(ItemStack i:ConfigUtil.parseItems(con.getString("transparent-materials"))) {
					transparentMaterials.add(i.getType());
			}
			customMobs = Boolean.valueOf(con.getString("mobs","false"));
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
		em.registerEffectType("EXPLOSION", ExplosionEffect.class);
		em.registerEffectType("LIGHTNING", LightningEffect.class);
		em.registerEffectType("SPAWN", SpawnEffect.class);
		em.registerEffectType("MOVE", MoveEffect.class);
		em.registerEffectType("PARTICLE", ParticleEffect.class);
		em.registerEffectType("BURN", BurnEffect.class);
		em.registerEffectType("POTION", PotionEffect.class);
	}
	
	private void loadEffectClasses() {
		String path = this.getDataFolder()+File.separator+"Effects";
		File dir = new File(path);
		if(!dir.exists())
			dir.mkdir();
		Utils.info("Loading external effect classes:");
		if(dir.listFiles().length<=0){
			Utils.info("---- EMPTY ----");
		}
		for(File f : dir.listFiles()){
			try {
				Class<? extends Effect> clazz = eManager.loadEffectClass(dir.getAbsolutePath(), f.getName());
				eManager.registerEffectType(clazz.newInstance().getName(), clazz);
			} catch (Exception e) {
				Utils.debug(e);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void loadMaterialTypeClasses() {
		String path = this.getDataFolder()+File.separator+"Materials";
		File dir = new File(path);
		if(!dir.exists())
			dir.mkdir();
		Utils.info(" Loading external material type classes:");
		if(dir.listFiles().length<=0){
			Utils.info("---- EMPTY ----");
		}
		for(File f : dir.listFiles()){
			try {
				Class<? extends MaterialType> clazz = tManager.loadTypeClass(dir.getAbsolutePath(), f.getName());
				if(clazz!=null)
					if(BlockType.class.isAssignableFrom(clazz)){
						tManager.registerBlockType(clazz.getName(), (Class<? extends BlockType>) clazz);
					}else if(ItemType.class.isAssignableFrom(clazz)){
						tManager.registerItemType(clazz.getName(), (Class<? extends ItemType>) clazz);
					}
			} catch (Exception e) {
				Utils.debug(e);
				e.printStackTrace();
			}
		}
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
			if(p instanceof PluginPlus) {
				LoadoutManager.getInstance().read(p);
			}
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
	
	private void beginMobAPI() {
		if(!customMobs) return;
		mManager = MobManager.getInstance();
		Bukkit.getPluginManager().registerEvents(new EntityReplacer(), getInstance());
		Bukkit.getLogger().log(Level.INFO, "-------MobAPI Enabled-------");
	}
	
	public boolean isMobAPI() {
		return customMobs;
	}
	
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length == 1) {
			Plugin plugin = Bukkit.getPluginManager().getPlugin(args[0]);
			if(plugin != null) {
				sender.sendMessage(ChatColor.BLUE + "--------Loadouts--------");
				for(Loadout l : lManager.getLoadouts(plugin)) {
					sender.sendMessage(ChatColor.GRAY + String.format("- %s:%s ~ %s", l.getName(), l.getVersion(), l.getAuthors()[0]));
				}
				if(lManager.getLoadouts(plugin) == null || lManager.getLoadouts(plugin).isEmpty()) sender.sendMessage(ChatColor.RED + "EMPTY");
				return true;
			} else sender.sendMessage(ChatColor.RED + "Error: No plugin by that name found");
		} else {
			sender.sendMessage(ChatColor.BLUE + "--------Loadouts--------");
			for(Loadout l : lManager.getAllLoadouts()) {
				sender.sendMessage(ChatColor.GRAY + String.format("- %s:%s ~ %s", l.getName(), l.getVersion(), l.getAuthors()[0]));
			}
			if(lManager.getAllLoadouts() == null || lManager.getAllLoadouts().isEmpty()) sender.sendMessage(ChatColor.RED + "EMPTY");
			return true;
		}
		return false;
	}
}
