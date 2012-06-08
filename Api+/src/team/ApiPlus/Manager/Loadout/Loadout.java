package team.ApiPlus.Manager.Loadout;

import java.io.File;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import team.ApiPlus.ApiPlus;
import team.ApiPlus.Util.Utils;

/**
 * 
 * @author SirTyler (Tyler Martin)
 * @version 1.0
 */
public class Loadout {
	private String name;
	private List<FileConfiguration> configs;
	
	public Loadout(File location) {
		try {
			if(!location.getPath().endsWith(".zip")) return;
			ZipFile file = new ZipFile(location);
			name = file.getName();
			Enumeration<? extends ZipEntry> em = file.entries();
			while(em.hasMoreElements()) {
				ZipEntry ent = em.nextElement();
				if(!ent.toString().endsWith(".yml")) {
					Utils.debug("Not YAML: " + ent.toString());
					return;
				} else {
					FileConfiguration con = new YamlConfiguration();
					con.load(file.getInputStream(ent));
					configs.add(con);
				}
			}
			file.close();
			ApiPlus.lManager.addLoadout(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method used for getting list of FileConfiguration used in this Loadout.
	 * @return List<FileConfiguration> List of FileConfigurations.
	 */
	public List<FileConfiguration> getConfigs() {
		return configs;
	}
	
	/**
	 * Method used for getting FileConfigratuon used in this Loadout by name.
	 * @param name String name to look for.
	 * @return FileConfiguration FileConfiguration used in this Loadout.
	 */
	public FileConfiguration getConfig(String name) {
		for(FileConfiguration f : configs) {
			if(f.getName().equalsIgnoreCase(name)) return f;
		}
		return null;
	}
	
	/**
	 * Method used for getting Name of Loadout.
	 * @return String Name of Loadout.
	 */
	public String getName() {
		return name;
	}
}
