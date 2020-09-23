package nl.thedutchmc.PexTeamLinker;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import nl.thedutchmc.PexTeamLinker.commands.LinkerCommandCompleter;
import nl.thedutchmc.PexTeamLinker.commands.LinkerCommandExecutor;
import nl.thedutchmc.PexTeamLinker.discord.JdaHandler;
import nl.thedutchmc.PexTeamLinker.prefabs.Prefabs;

public class PexTeamLinker extends JavaPlugin {

	public static PexTeamLinker INSTANCE;
	public static ConfigurationHandler CONFIG;
	public static Prefabs PREFABS;
	public static JdaHandler JDA;
	
	@Override
	public void onEnable() {
		INSTANCE = this;
		
		CONFIG = new ConfigurationHandler();
		CONFIG.loadConfig();
		PREFABS = new Prefabs();
		
		PREFABS.loadPrefabsFromStorage();
		
		JDA = new JdaHandler(ConfigurationHandler.discordToken);
		
		getCommand("linker").setExecutor(new LinkerCommandExecutor());
		getCommand("linker").setTabCompleter(new LinkerCommandCompleter());
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static String getPluginPrefix() {
		return ChatColor.GRAY + "[" + ChatColor.AQUA + "PTL" + ChatColor.GRAY + "] " + ChatColor.RESET;
	}
	
	public static void logInfo(String log) {
		Bukkit.getLogger().info("[" + PexTeamLinker.INSTANCE.getDescription().getName() + "] " + log);	
	}
	
	public static void logWarn(String log) {
		Bukkit.getLogger().warning("[" + PexTeamLinker.INSTANCE.getDescription().getName() + "] " + log);	
	}
}
