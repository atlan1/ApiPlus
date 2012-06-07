package team.ApiPlus.Util;

import java.util.logging.Level;

import org.bukkit.Bukkit;

/**
 * Utility Class containing cross-class methods.
 * @author SirTyler (Tyler Martin)
 * @version 1.0
 */
public class Utils {
	
	/**
	 * Method used for writing a Info message to the Logger.
	 * @param message String message to write.
	 */
	public static void info(String message) {
		Bukkit.getLogger().log(Level.INFO, String.format("[API+] %s", message));
	}
	
	/**
	 * Method used for writing multiple Info messages to the Logger.
	 * @param message String Array of messages to write.
	 */
	public static void info(String[] message) {
		for(String s : message) {
			Bukkit.getLogger().log(Level.INFO, String.format("[API+] %s", s));
		}
	}
	
	/**
	 * Method used for writing a Warning message to the Logger.
	 * @param message String message to write
	 */
	public static void warning(String message) {
		Bukkit.getLogger().log(Level.WARNING, String.format("[API+] %s", message));
	}
	
	/**
	 * Method used for writing multiple Warning messages to the Logger.
	 * @param message
	 */
	public static void warning(String[] message) {
		for(String s : message) {
			Bukkit.getLogger().log(Level.WARNING, String.format("[API+] %s", s));
		}
	}
}
